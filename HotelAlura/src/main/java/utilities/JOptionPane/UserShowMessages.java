package utilities.JOptionPane;

import javax.swing.*;
import java.awt.*;

public class UserShowMessages {

    public static void showErrorMessage(String title, String msg) {
        JOptionPane.showMessageDialog(
                null,
                msg,
                title,
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void showMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(
                parentComponent,
                message
        );
    }
}
