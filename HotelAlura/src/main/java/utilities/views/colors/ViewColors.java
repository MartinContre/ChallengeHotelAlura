package utilities.views.colors;

import java.awt.*;


/**
 * Utility class for providing custom colors to be used in graphical user interfaces.
 * This class defines static methods to generate Color objects for specific shades of colors.
 * This class is intended to centralize color definitions and provide a convenient way to access
 * custom color values for consistent styling across different parts of the user interface.
 * Usage:
 * - Use the static methods in this class to obtain Color objects for specific shades of colors.
 * - These predefined colors can be used to set backgrounds, text colors, and other visual elements
 * within the user interface.
 * Example:
 * To obtain the Alice Blue color, use the aliceBlue method as follows:
 * Color aliceBlueColor = ViewColors.aliceBlue();
 * Note: The color values provided in this class are examples and can be customized as needed.
 * Ensure that the Color objects obtained from this class are used appropriately in UI components
 */
public class ViewColors {

    private ViewColors() {

    }

    public static Color aliceBlue() {
        return new Color(240, 248, 255);
    }

    public static Color sereneOceanBlue() {
        return new Color(118, 187, 223);
    }

    public static Color vividSkyBlue() {
        return new Color(12, 138, 199);
    }

}
