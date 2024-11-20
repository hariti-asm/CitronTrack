package ma.hariti.asmaa.wrm.citrontrack.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldAreaValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFieldArea {
    String message() default "Field area must be less than or equal to half of the farm's total area";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}