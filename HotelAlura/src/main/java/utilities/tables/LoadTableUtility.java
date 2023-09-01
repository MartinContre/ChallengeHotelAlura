package utilities.tables;

import controller.BookingController;
import controller.GuestController;
import controller.UserController;
import model.Booking;
import model.Guest;
import utilities.JOptionPane.UserShowMessages;
import utilities.columns.EmployeeCategoryColum;
import utilities.columns.PaymentMethodColumn;
import utilities.validation.TxtValidation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.List;


/**
 * Utility class for loading data into JTables and configuring table columns.
 * This class provides methods to load guest, booking, and user data into specified table models,
 * and it can also configure special rendering and editing for certain table columns.
 * It supports both general data loading and loading based on search parameters.
 * Usage:
 * - Use the provided methods to load data into your JTable components.
 * - You can load data into a table model for guests, bookings, or users, with or without search parameters.
 * - Special column rendering and editing are configured for payment methods and user categories.
 * - Error messages are displayed if invalid search parameters are provided.
 */
public class LoadTableUtility {

    /**
     * Loads guest data into the specified table model using the provided controller.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param controller The GuestController to retrieve guest data.
     */
    public static void guestTable(DefaultTableModel tableModel, GuestController controller) {
        tableModel.getDataVector().clear();
        FillTablesUtility.fillGuestTable(controller.list(), tableModel);
    }

    /**
     * Loads guest data into the specified table model using the provided controller, based on a search parameter.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param controller The GuestController to retrieve guest data.
     * @param search     The JTextField containing the search parameter.
     */
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

    /**
     * Loads booking data into the specified table model using the provided controller, without a search parameter.
     * Also configure the payment method column for rendering and editing.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param controller The BookingController to retrieve booking data.
     * @param table      The JTable to which the data will be loaded.
     */
    public static void bookingTable(DefaultTableModel tableModel, BookingController controller, JTable table) {
        tableModel.getDataVector().clear();
        FillTablesUtility.fillBookingTable(controller.list(), tableModel);

        paymentMethodColumn(table);
    }

    /**
     * Loads booking data into the specified table model using the provided controller, based on a search parameter.
     * Also configure the payment method column for rendering and editing.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param controller The BookingController to retrieve booking data.
     * @param table      The JTable to which the data will be loaded.
     * @param search     The JTextField containing the search parameter.
     */
    public static void bookingTable(DefaultTableModel tableModel, BookingController controller, JTable table, JTextField search) {
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
            paymentMethodColumn(table);
        }
    }

    /**
     * Loads user data into the specified table model using the provided controller, without a search parameter.
     * Also configure the user category column for rendering and editing.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param controller The UserController to retrieve user data.
     * @param jTable     The JTable to which the data will be loaded.
     */
    public static void userTable(DefaultTableModel tableModel, UserController controller, JTable jTable) {
        tableModel.getDataVector().clear();
        FillTablesUtility.fillUserTable(controller.list(), tableModel);

        userCategoryColumn(jTable);

        jTable.repaint();
    }

    /**
     * Loads user data into the specified table model using the provided controller, based on a search parameter.
     * Also configure the user category column for rendering and editing.
     * Displays an error message if the entered user category is invalid.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param controller The UserController to retrieve user data.
     * @param jTable     The JTable to which the data will be loaded.
     * @param search     The JTextField containing the search parameter.
     */
    public static void userTable(DefaultTableModel tableModel, UserController controller, JTable jTable, JTextField search) {
        tableModel.getDataVector().clear();
        String userCategory = search.getText();

        if (TxtValidation.validateEmployeeCategory(userCategory)) {
            FillTablesUtility.fillUserTable(controller.list(userCategory), tableModel);
            userCategoryColumn(jTable);
        }
    }

    /**
     * Configures the payment method column for a JTable.
     *
     * @param jTable The JTable containing the payment method column.
     */
    private static void paymentMethodColumn(JTable jTable) {
        TableColumn paymentMethodColumn = jTable.getColumnModel().getColumn(5);
        paymentMethodColumn.setCellRenderer(new PaymentMethodColumn());
        paymentMethodColumn.setCellEditor(new PaymentMethodColumn());
    }

    /**
     * Configures the user category column for a JTable.
     *
     * @param jTable The JTable containing the user category column.
     */
    private static void userCategoryColumn(JTable jTable) {
        TableColumn userCategoryColumn = jTable.getColumnModel().getColumn(2);
        userCategoryColumn.setCellRenderer(new EmployeeCategoryColum());
        userCategoryColumn.setCellEditor(new EmployeeCategoryColum());
    }
}
