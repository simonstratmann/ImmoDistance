package com.example.immodistance;

import com.example.immodistance.pojo.Direction;
import com.example.immodistance.pojo.Leg;
import com.example.immodistance.pojo.Route;
import com.example.immodistance.pojo.Step;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith({MockitoExtension.class})
class ProxyTest {

    @Test
    public void should() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final Direction direction = objectMapper.readValue(getClass().getResourceAsStream("/googleResponse.json"), Direction.class);
        final Route route = direction.routes.get(0);
        final Leg leg = route.legs.get(0);
        final int distance = leg.distance.value;
        final int duration = leg.duration.value / 60;
        final List<Step> transitSteps = leg.steps.stream().filter(x -> x.travelMode.equals("TRANSIT")).toList();
        final List<Step> walkingSteps = leg.steps.stream().filter(x -> x.travelMode.equals("WALKING")).toList();
        final int minutesTransit = transitSteps.stream().mapToInt(x -> x.duration.value).sum() / 60;
        final int minutesWalking = walkingSteps.stream().mapToInt(x -> x.duration.value).sum() / 60;
        DistanceData distanceData = new DistanceData(distance, duration, transitSteps.size(), walkingSteps.size(), minutesTransit, minutesWalking);

        System.out.println("proxyData = " + direction);
    }

}
