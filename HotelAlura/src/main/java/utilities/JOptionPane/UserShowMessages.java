package utilities.JOptionPane;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for showing user messages using JOptionPane.
 */
public class UserShowMessages {

    /**
     * Displays an error message dialog box with the specified title and message.
     *
     * @param title The title of the dialog box.
     * @param msg   The message to display.
     */
    public static void showErrorMessage(String title, String msg) {
        JOptionPane.showMessageDialog(
                null,
                msg,
                title,
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Displays a message dialog box with the specified message.
     *
     * @param parentComponent The parent component of the dialog box.
     * @param message         The message to display.
     */
    public static void showMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(
                parentComponent,
                message
        );
    }
}
