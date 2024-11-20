package ma.hariti.asmaa.wrm.citrontrack.enums;

import lombok.Getter;

@Getter
public enum TreeProductivity {
    YOUNG(2.5),
    MATURE(12),
    OLD(20),
    NON_PRODUCTIVE(0);

    private final double productivityAge;

    TreeProductivity(double productivityAge) {
        this.productivityAge = productivityAge;
    }

    public static TreeProductivity fromAge(int age) {
        if (age < 3) {
            return YOUNG;
        } else if (age >= 3 && age <= 10) {
            return MATURE;
        } else if (age > 10 && age < 20) {
            return OLD;
        } else {
            return NON_PRODUCTIVE;
        }
    }
}
