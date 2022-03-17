package com.da_java.p8_gpsutil;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GpsUtilUT {

    @Autowired
    private GpsUtil gpsUtilUT;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void whenGetUserLocationThenReturnVisitedLocation() {
        UUID userId = UUID.randomUUID();
        VisitedLocation visitedLocation =
                gpsUtilUT.getUserLocation(userId);
        assertThat(visitedLocation).isNotNull();
        assertThat(visitedLocation.userId).isEqualByComparingTo(userId);
    }

    @Test
    void whenGetAttractionsThenReturnAnAttractionList() {
        List<Attraction> attractions = gpsUtilUT.getAttractions();
        assertThat(attractions.size()).isEqualTo(26);
    }
}
