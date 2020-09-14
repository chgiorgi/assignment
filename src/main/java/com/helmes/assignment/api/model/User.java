package com.helmes.assignment.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long id;

    @NotNull(message = "Name is be empty")
    @Size(min = 4, max = 50, message = "Name length should be between 4 - 50 characters")
    private String name;

    @NotNull
    @AssertTrue(message = "Please Agree to terms")
    private boolean agreeToTerms;

    @NotEmpty(message = "Sector is empty")
    @NotNull(message = "Sector is null")
    private List<Long> sectors;

}
