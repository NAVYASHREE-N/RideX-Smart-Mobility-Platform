package com.ridex.ridex.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridex.ridex.entity.Ride;
import com.ridex.ridex.service.RideService;

@RestController
@RequestMapping("/rides")
@CrossOrigin("*")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {

        this.rideService = rideService;

    }

    // GET ALL RIDES

    @GetMapping
    public List<Ride> getAllRides() {

        return rideService.getAllRides();

    }

    // BOOK RIDE

    @PostMapping("/book")
    public Ride bookRide(@RequestParam Long customerId,
                         @RequestParam String pickup,
                         @RequestParam String drop,
                         @RequestParam Double distanceKm) {

        return rideService.bookRide(
                customerId,
                pickup,
                drop,
                distanceKm
        );

    }

    // UPDATE STATUS

    @PutMapping("/{rideId}/status")
    public Ride updateRideStatus(@PathVariable Long rideId,
                                 @RequestParam Ride.RideStatus status) {

        return rideService.updateRideStatus(
                rideId,
                status
        );

    }

    // CANCEL RIDE

    @PutMapping("/{rideId}/cancel")
    public Ride cancelRide(@PathVariable Long rideId){

        return rideService.cancelRide(rideId);

    }

    // ACCEPT RIDE

@PutMapping("/{rideId}/accept")
public Ride acceptRide(@PathVariable Long rideId,
                       @RequestParam Long driverId){

    return rideService.acceptRide(
            rideId,
            driverId
    );

}

    // GET RIDES BY CUSTOMER

    @GetMapping("/customer/{customerId}")
    public List<Ride> getRidesByCustomer(
            @PathVariable Long customerId
    ) {

        return rideService.getRidesByCustomer(
                customerId
        );

    }

    // REVENUE

    @GetMapping("/revenue")
    public Double getTotalRevenue() {

        return rideService.getTotalRevenue();

    }

    // RATING

    @PutMapping("/{rideId}/rating")
    public Ride rateDriver(@PathVariable Long rideId,
                           @RequestParam Integer rating){

        return rideService.rateDriver(
                rideId,
                rating
        );

    }

    // PAYMENT

    @PutMapping("/{rideId}/payment")
    public Ride makePayment(@PathVariable Long rideId,
                            @RequestParam String paymentMethod){

        return rideService.makePayment(
                rideId,
                paymentMethod
        );

    }

}