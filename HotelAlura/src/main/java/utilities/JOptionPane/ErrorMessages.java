package utilities.JOptionPane;

import javax.swing.*;

public class ErrorMessages {

    public static void showErrorMessage(String title, String msg) {
        JOptionPane.showMessageDialog(
                null,
                msg,
                title,
                JOptionPane.ERROR_MESSAGE
        );
    }
}
