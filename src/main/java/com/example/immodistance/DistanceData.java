package com.example.immodistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
final class DistanceData {
    int transitMinutes;
   String transitHtml;
   int bikeMinutes;
   int bikeMeters;
}
