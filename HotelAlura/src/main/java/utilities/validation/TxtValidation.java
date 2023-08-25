package utilities.validation;

import utilities.StringUtilities;
import utilities.enums.EmployeeCategory;

public class TxtValidation {

    private TxtValidation() {

    }

    public static Boolean validateEmployeeCategory(String search) {

        EmployeeCategory employeeCategory = StringUtilities.convertUserCategoryStrToEmployeeCategory(search);

        return employeeCategory.equals(EmployeeCategory.Receptionist) || employeeCategory.equals(EmployeeCategory.Manager);
    }

}
