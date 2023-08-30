package utilities.views.separator;

/**
 * Utility class for providing dimensions of separators to be used in graphical user interfaces.
 * This class defines static methods to retrieve width and height values for separators.
 * This class is intended to centralize separator dimensions and provide a convenient way to access
 * consistent values for creating separators with consistent sizes across different parts of the user interface.
 * Usage:
 * - Use the static methods in this class to obtain width and height values for separators.
 * - These predefined values can be used when creating separators between different sections or elements
 * within the user interface to achieve consistent visual spacing.
 * Example:
 * To obtain the width of a separator, use the getWidth method as follows:
 * int separatorWidth = SeparatorUtils.getWidth();
 * To obtain the height of a separator, use the getHeight method as follows:
 * int separatorHeight = SeparatorUtils.getHeight();
 * Note: The dimensions provided in this class are examples and can be customized as needed.
 * Ensure that the width and height values obtained from this class are used appropriately when creating separators.
 */
public class SeparatorUtils {

    private SeparatorUtils() {

    }

    public static Integer getWidth() {
        return 289;
    }

    public static Integer getHeight() {
        return 2;
    }

}
