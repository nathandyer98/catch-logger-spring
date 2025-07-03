package com.catchlogger.backend.dto;

import com.catchlogger.backend.model.FishSpecies;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CatchUpdateRequest {

    @NotNull(message = "Species is required.")
    private FishSpecies species;

    @NotNull(message = "Name is required.")
    private String name;

    @Positive(message = "Weight must be a positive number if provided.")
    private Double weight;

    @Positive(message = "Length must be a positive number if provided.")
    private Double distance;

    @Size(max = 255, message = "Location cannot be more than 255 characters.")
    private String location;

    @Size(max = 1024, message = "Notes cannot be more than 1024 characters.")
    private String notes;

    @Size(max = 255, message = "Location cannot be more than 255 characters.")
    private String rigInfo;

    @Size(max = 255, message = "Location cannot be more than 255 characters.")
    private String baitInfo;
}