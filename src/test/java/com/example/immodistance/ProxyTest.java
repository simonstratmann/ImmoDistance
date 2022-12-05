package com.example.immodistance;

import com.example.immodistance.pojo.Direction;
import com.example.immodistance.pojo.Leg;
import com.example.immodistance.pojo.Route;
import com.example.immodistance.pojo.Step;
import com.example.immodistance.pojo.Vehicle;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith({MockitoExtension.class})
class ProxyTest {

    @Test
    public void should() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final Direction directionTransit = objectMapper.readValue(getClass().getResourceAsStream("/googleResponseTransit.json"), Direction.class);
        final Direction directionBike = objectMapper.readValue(getClass().getResourceAsStream("/googleResponseBike.json"), Direction.class);
        final DistanceData distanceData = Proxy.convert(directionTransit, directionBike);

        System.out.println(distanceData);
    }

}
