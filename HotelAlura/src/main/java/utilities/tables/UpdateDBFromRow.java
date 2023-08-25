package utilities.tables;

import controller.BookingController;
import controller.UserController;
import model.User;
import utilities.JOptionPane.UserShowMessages;
import utilities.enums.EmployeeCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;

public class UpdateDBFromRow {

    public UpdateDBFromRow() {

    }

    public static void updateBooking(DefaultTableModel tableModel, JTable table, BookingController controller) {
        int id = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        Date checkIn;
        Date checkOut;

    }

    public static void updateUser(DefaultTableModel tableModel, JTable table, UserController controller) {
        int id = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        String userName = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
        String userCategoryStr = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
        EmployeeCategory userCategory = EmployeeCategory.valueOf(userCategoryStr);
        String userPassword = tableModel.getValueAt(table.getSelectedRow(), 3).toString();

        User user = new User(id, userName, userCategory, userPassword);

        int userUpdateCount = controller.update(user);

        UserShowMessages.showMessage(
                null,
                String.format(
                        "%s User update", userUpdateCount
                )
        );
    }
}
