package com.da_java.p8_gpsutil.controller;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Api("API which provides locations of users or attractions.")

@RestController
@RequestMapping("/api/v1/gpsUtil/")
public class GpsUtilController {

    private final GpsUtil gpsUtil;

    public GpsUtilController(GpsUtil gpsUtil) {
        this.gpsUtil = gpsUtil;
    }

    @ApiOperation(value = "Get a visitedLocation by userId on parameter.")
    @GetMapping("userLocation/{userId}")
    public ResponseEntity<VisitedLocation> getUserLocation(@PathVariable String userId) {
        UUID userUuid;
        try {
            userUuid = UUID.fromString(userId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(gpsUtil.getUserLocation(userUuid));
    }

    @ApiOperation(value = "Return all attraction on data base.")
    @GetMapping("attractions")
    public List<Attraction> getAttractions() {
        return gpsUtil.getAttractions();
    }

}
