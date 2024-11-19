package ma.hariti.asmaa.wrm.citrontrack.validation;

import jakarta.validation.Payload;

public @interface ValidPlantingDate {
    String message() default "Planting date must be between March and May";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
