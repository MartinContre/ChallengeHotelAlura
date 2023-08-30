package utilities.enums;

/**
 * Enum representing categories for employees.
 */
public enum EmployeeCategory {
    Manager,
    Receptionist;

    /**
     * Get an array of all display names for employee categories.
     *
     * @return An array containing all display names.
     */
    public static String[] getAllDisplayNames() {
        String[] displayNames = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            displayNames[i] = values()[i].name();
        }
        return displayNames;
    }

}
