package com.example.immodistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
final class DistanceData {
    int distanceMeters;
    int durationMinutes;
    int numberOfTransitStops;
    int numberOfWalkingStops;
    int minutesTransit;
    int minutesWalking;
}
