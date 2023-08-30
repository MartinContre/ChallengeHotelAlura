package utilities;

/**
 * Utility class for generating SQL insert and update fields strings.
 * This class provides methods to generate insert and update fields strings
 * that can be used in SQL statements.
 * Usage:
 * - Use the static methods in this class to generate insert and update fields strings.
 * Example:
 * To generate an insert fields string for an SQL statement, use the generateInsertFields method as follows:
 * String[] columns = {"column1", "column2", "column3"};
 * String insertFields = InsetFieldGenerator.generateInsertFields(columns);
 * Similarly, to generate an update fields string for an SQL statement, use the generateUpdateFields method:
 * String[] columnsToUpdate = {"column1", "column2", "column3"};
 * String updateFields = InsetFieldGenerator.generateUpdateFields(columnsToUpdate);
 */
public class InsetFieldGenerator {

    /**
     * Generates the insert fields string for SQL statements.
     *
     * @param columns The array of columns.
     * @return The generated insert fields string.
     */
    public static String generateInsertFields(String[] columns) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < columns.length; i++) {
            sb.append("`").append(columns[i]).append("`");
            if (i < columns.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Generates the update fields string for SQL statements.
     *
     * @param columns The array of columns.
     * @return The generated update fields string.
     */
    public static String generateUpdateFields(String[] columns) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            sb.append("`").append(columns[i]).append("` = ?");
            if (i < columns.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
