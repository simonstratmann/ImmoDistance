package com.example.immodistance;

import com.example.immodistance.pojo.Direction;
import com.example.immodistance.pojo.Leg;
import com.example.immodistance.pojo.Route;
import com.example.immodistance.pojo.Step;
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
import java.util.List;

@RestController
@Log4j2
public class Proxy {

    @Value("${googleApiKey}")
    private String googleApiKey;
    @Value("${destination}")
    private String destination;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String URL_TEMPLATE = "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&mode=transit&key=%s&departure_time=1670253003";

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
        final File file = new File("data/" + (from + to).hashCode() + ".json");
        if (file.exists()) {
            return objectMapper.readValue(file, DistanceData.class);
        }

        final String URL = URL_TEMPLATE.formatted(URLEncoder.encode(from, StandardCharsets.UTF_8), URLEncoder.encode(to, StandardCharsets.UTF_8), googleApiKey);
        final Direction direction = objectMapper.readValue(new URL(URL), Direction.class);


        DistanceData distanceData = convert(direction);
        objectMapper.writeValue(file, distanceData);
        return distanceData;
    }

    @GetMapping("/mock")
    public DistanceData mock(@RequestParam String from, @RequestParam String to) throws Exception {
        log.info("Loading mock data from " + from + " to " + to);
        final Direction direction = objectMapper.readValue(getClass().getResourceAsStream("/googleResponse.json"), Direction.class);
        return convert(direction);
    }

    static DistanceData convert(Direction direction) {
        final Route route = direction.routes.get(0);
        final Leg leg = route.legs.get(0);
        final int distance = leg.distance.value;
        final int duration = leg.duration.value / 60;
        final List<Step> transitSteps = leg.steps.stream().filter(x -> x.travelMode.equals("TRANSIT")).toList();
        final List<Step> walkingSteps = leg.steps.stream().filter(x -> x.travelMode.equals("WALKING")).toList();
        final int minutesTransit = transitSteps.stream().mapToInt(x -> x.duration.value).sum() / 60;
        final int minutesWalking = walkingSteps.stream().mapToInt(x -> x.duration.value).sum() / 60;
        return new DistanceData(distance, duration, transitSteps.size(), walkingSteps.size(), minutesTransit, minutesWalking);
    }

}
