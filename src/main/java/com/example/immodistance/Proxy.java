package com.example.immodistance;

import com.example.immodistance.pojo.Direction;
import com.example.immodistance.pojo.Leg;
import com.example.immodistance.pojo.Route;
import com.example.immodistance.pojo.Step;
import com.example.immodistance.pojo.Vehicle;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class Proxy {

    @Value("${googleApiKey}")
    private String googleApiKey;
    @Value("${destination}")
    private String destination;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String URL_TEMPLATE = "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&mode=%s&key=%s";

    public Proxy() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    @GetMapping("/directions")
    public DistanceData get(@RequestParam String from, @RequestParam(required = false) String to) throws Exception {
        if (to == null) {
            if (destination == null) {
                throw new RuntimeException("No destination and no default destination");
            }
            to = destination;
        }

        log.info("Loading directions from " + from + " to " + to);

        final Direction directionTransit = loadDirection(from, to, "transit");
        final Direction directionBike = loadDirection(from, to, "bicycling");
        return convert(directionTransit, directionBike);
    }

    private Direction loadDirection(String from, String to, String type) throws Exception {
        final File file = new File("data/" + (from + to).hashCode() + "-" + type + ".json");
        if (file.exists()) {
            return objectMapper.readValue(file, Direction.class);
        }
        log.info("Accessing Google Directions API");
        final String URL = URL_TEMPLATE.formatted(URLEncoder.encode(from, StandardCharsets.UTF_8), URLEncoder.encode(to, StandardCharsets.UTF_8), type, googleApiKey);
        final Direction direction = objectMapper.readValue(new URL(URL), Direction.class);
        objectMapper.writeValue(file, direction);
        return direction;
    }

    @GetMapping("/mock")
    public DistanceData mock(@RequestParam String from, @RequestParam(required = false) String to) throws Exception {
        log.info("Loading mock data from " + from + " to " + to);
        final Direction directionTransit = objectMapper.readValue(getClass().getResourceAsStream("/googleResponseTransit.json"), Direction.class);
        final Direction directionBike = objectMapper.readValue(getClass().getResourceAsStream("/googleResponseBike.json"), Direction.class);
        return convert(directionTransit, directionBike);
    }

    static DistanceData convert(Direction directionTransit, Direction directionBike) {
        final Route transitRoute = directionTransit.routes.get(0);
        final Leg transitLeg = transitRoute.legs.get(0);
        final int transitDuration = transitLeg.duration.value / 60;

        final Leg bikeLeg = directionBike.routes.get(0).legs.get(0);
        final int bikeMeters = bikeLeg.distance.value;
        final int bikeMinutes = bikeLeg.duration.value / 60;

        final List<String> transitStepIcons = new ArrayList<>();
        for (Step step : transitLeg.steps) {
            final String typeIcon;
            if (step.travelMode.equals("WALKING")) {
                typeIcon = "//maps.gstatic.com/mapfiles/transit/iw2/svg/walk.svg";
            } else {
                final Vehicle vehicle = step.transitDetails.line.vehicle;
                typeIcon = vehicle.localIcon != null ? vehicle.localIcon : vehicle.icon;
            }
            final int durationMinutes = step.duration.value / 60;
            transitStepIcons.add("<img width=\"15\" height=\"15\" src=\"http:" + typeIcon + "\"/> (" + (durationMinutes == 0 ? "<1" : durationMinutes) + "m)");
        }
        final String transit = String.join(" &bull; ", transitStepIcons);

        return new DistanceData(transitDuration, transit, bikeMinutes, bikeMeters);
    }

}
