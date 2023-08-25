package utilities.tables;

import controller.BookingController;
import controller.GuestController;
import controller.UserController;
import model.Booking;
import model.Guest;
import utilities.columns.EmployeeCategoryColum;
import utilities.JOptionPane.UserShowMessages;
import utilities.validation.TxtValidation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.List;

public class LoadTableUtility {

    public static void guestTable(DefaultTableModel tableModel, GuestController controller) {
        tableModel.getDataVector().clear();
        FillTablesUtility.fillGuestTable(controller.list(), tableModel);
    }

    public static void guestTable(DefaultTableModel tableModel, GuestController controller, JTextField search) {
        tableModel.getDataVector().clear();
        String surname = search.getText();

        List<Guest> guestList = controller.list(surname);

        if (guestList.isEmpty()) {
            UserShowMessages.showErrorMessage(
                    "Error typing",
                    "You can only search by surname.\n" +
                            "Please enter a valid surname or first letters."
            );
        } else {
            FillTablesUtility.fillGuestTable(guestList, tableModel);
        }
    }

    public static void bookingTable(DefaultTableModel tableModel, BookingController controller) {
        tableModel.getDataVector().clear();
        FillTablesUtility.fillBookingTable(controller.list(), tableModel);
    }

    public static void bookingTable(DefaultTableModel tableModel, BookingController controller, JTextField search) {
        tableModel.getDataVector().clear();
        String bookingId = search.getText();

        List<Booking> bookingList = controller.list(bookingId);

        if (bookingList.isEmpty()) {
            UserShowMessages.showErrorMessage(
                    "Error typing",
                    "You must search by booking id, it could be just the first latter.\n" +
                            "Verify the text you entered"
            );
        } else {
            FillTablesUtility.fillBookingTable(bookingList, tableModel);
        }
    }

    public static void userTable(DefaultTableModel tableModel, UserController controller, JTable jTable) {
        tableModel.getDataVector().clear();
        FillTablesUtility.fillUserTable(controller.list(), tableModel);

        userCategoryColumn(jTable);

        jTable.repaint();
    }

    public static void userTable(DefaultTableModel tableModel, UserController controller, JTable jTable, JTextField search) {
        tableModel.getDataVector().clear();
        String userCategory = search.getText();
        if (TxtValidation.validateEmployeeCategory(userCategory)) {
            FillTablesUtility.fillUserTable(controller.list(userCategory), tableModel);
            userCategoryColumn(jTable);
        }
    }

    private static void userCategoryColumn(JTable jTable) {
        TableColumn userCategoryColumn = jTable.getColumnModel().getColumn(2);
        userCategoryColumn.setCellRenderer(new EmployeeCategoryColum());
        userCategoryColumn.setCellEditor(new EmployeeCategoryColum());
    }
}
