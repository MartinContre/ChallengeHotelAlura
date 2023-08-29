package utilities.enums;

public enum EmployeeCategory {
    Manager,
    Receptionist;

    public static String[] getAllDisplayNames() {
        String[] displayNames = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            displayNames[i] = values()[i].name();
        }
        return displayNames;
    }

}
