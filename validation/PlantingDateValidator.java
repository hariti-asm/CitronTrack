package ma.hariti.asmaa.wrm.citrontrack.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PlantingDateValidator  implements ConstraintValidator<ValidPlantingDate, LocalDate> {
    @Override
    public void initialize(ValidPlantingDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate plantingDate, ConstraintValidatorContext context) {
        if (plantingDate == null) {
            return true;
        }
        int month = plantingDate.getMonthValue();
        return month >= 3 && month <= 5;
    }
}