package com.da_java.p8_gpsutil;

import com.da_java.p8_gpsutil.controller.GpsUtilController;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GpsUtilControllerUT {

    @Mock
    private GpsUtil gpsUtil;

    @InjectMocks
    private GpsUtilController gpsUtilController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetUserLocationWithUserIdValidThenReturnVisitedLocation() {
        String uuidString = "123e4567-e89b-12d3-a456-426614174000";
        UUID uuid = UUID.fromString(uuidString);
        VisitedLocation visitedLocation = new VisitedLocation(
                uuid
                , new Location(12, 12)
                , Date.valueOf(LocalDate.now()));
        when(gpsUtil.getUserLocation(UUID.fromString(uuidString))).thenReturn(visitedLocation);

        ResponseEntity<VisitedLocation> response =
                gpsUtilController.getUserLocation(uuidString);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(visitedLocation);
    }

    @Test
    void whenGetUserLocationWithInvalidUserIdThenReturnBadRequest() {
        String uuidString = "INVALID_UUID";

        ResponseEntity<VisitedLocation> response =
                gpsUtilController.getUserLocation(uuidString);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void whenGetAttractionThenReturnAJSONAttractionList() {
        List<Attraction> attractions = Arrays.asList(
                new Attraction("Disneyland"
                        , "Anaheim"
                        , "CA"
                        , 33.817595D
                        , -117.922008D)
                , new Attraction("Jackson Hole"
                        , "Jackson Hole"
                        , "WY"
                        , 43.582767D
                        , -110.821999D)
                , new Attraction("Mojave National Preserve"
                        , "Kelso"
                        , "CA"
                        , 35.141689D
                        , -115.510399D)
        );
        when(gpsUtil.getAttractions()).thenReturn(attractions);

        assertThat(gpsUtilController.getAttractions()).isEqualTo(
                attractions
        );
    }




}
