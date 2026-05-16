package com.ridex.ridex.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,
            unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    // PASSWORD

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String licenseNumber;

    @Column(nullable = false)
    private String vehicleModel;

    @Column(nullable = false)
    private String vehicleNumber;

    @Column
    private Double rating;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @Column
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){

        createdAt =
                LocalDateTime.now();

        if(status == null){

            status =
                    DriverStatus.AVAILABLE;

        }

        if(rating == null){

            rating = 5.0;

        }

    }

    public enum DriverStatus{

        AVAILABLE,
        ON_TRIP,
        OFFLINE

    }

}