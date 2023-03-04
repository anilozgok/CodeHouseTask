package org.anilcan.model.domain;

import jakarta.validation.constraints.NotBlank;
import org.anilcan.utility.Gender;

public record ContactRecord(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        Phone phone,
        Gender gender
) {


}
