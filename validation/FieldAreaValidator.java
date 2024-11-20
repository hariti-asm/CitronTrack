package ma.hariti.asmaa.wrm.citrontrack.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ma.hariti.asmaa.wrm.citrontrack.entity.Field;

public class FieldAreaValidator implements ConstraintValidator<ValidFieldArea, Field> {

    @Override
    public boolean isValid(Field field, ConstraintValidatorContext context) {
        if (field == null || field.getFarm() == null) {
            return true;
        }

        double farmTotalArea = field.getFarm().getTotalArea();
        return field.getArea() <= (farmTotalArea / 2);
    }
}
