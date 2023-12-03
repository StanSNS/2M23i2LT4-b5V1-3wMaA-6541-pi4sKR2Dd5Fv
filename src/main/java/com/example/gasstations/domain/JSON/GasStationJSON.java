package com.example.gasstations.domain.JSON;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GasStationJSON {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String brand;

    @NotNull
    private String street;

    @NotNull
    private String place;

    @NotNull
    private double lat;

    @NotNull
    private double lng;

    @NotNull
    private double dist;

    @NotNull
    private double diesel;

    @NotNull
    private double e5;

    @NotNull
    private double e10;

    @NotNull
    private boolean isOpen;

    @NotNull
    private String houseNumber;

    @NotNull
    private int postCode;
}

