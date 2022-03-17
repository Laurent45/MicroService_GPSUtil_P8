package com.da_java.p8_gpsutil.controller;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/gpsUtil/")
public class GpsUtilController {

    private final GpsUtil gpsUtil;

    public GpsUtilController(GpsUtil gpsUtil) {
        this.gpsUtil = gpsUtil;
    }

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

    @GetMapping("attractions")
    public List<Attraction> getAttractions() {
        return gpsUtil.getAttractions();
    }

}
