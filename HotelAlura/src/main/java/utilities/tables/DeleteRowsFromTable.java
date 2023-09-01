package utilities.tables;

import controller.BookingController;
import controller.GuestController;
import controller.UserController;
import utilities.JOptionPane.UserShowMessages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Utility class for deleting rows from JTables and associated controllers.
 * This utility class provides methods for deleting rows from JTables and their associated controllers. It is designed
 * to handle the deletion of guest, booking, and user records while also providing user-friendly feedback through
 * messages.
 */
public class DeleteRowsFromTable {

    private DeleteRowsFromTable() {

    }

    /**
     * Deletes a guest and its associated booking from the table and controller.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param table      The JTable from which the row will be deleted.
     * @param controller The GuestController instance.
     */
    public static void deleteGuest(DefaultTableModel tableModel, JTable table, GuestController controller) {
        int guestId = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        String bookingId = tableModel.getValueAt(table.getSelectedRow(), 6).toString();

        tableModel.removeRow(table.getSelectedRow());

        Integer[] deletedRows = controller.delete(guestId, bookingId);

        UserShowMessages.showMessage(
                null,
                String.format("%d Guest deleted\n" +
                                "%d Booking deleted",
                        deletedRows[0], deletedRows[1]
                )
        );
    }

    /**
     * Deletes a booking and its associated guests from the table and controller.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param table      The JTable from which the row will be deleted.
     * @param controller The BookingController instance.
     */
    public static void deleteBooking(DefaultTableModel tableModel, JTable table, BookingController controller) {
        String bookingId = table.getValueAt(table.getSelectedRow(), 1).toString();

        Integer[] deletedRows = controller.deleteEmbeddedGuest(bookingId);

        tableModel.removeRow(table.getSelectedRow());

        UserShowMessages.showMessage(
                null,
                String.format("%d Booking deleted\n" +
                                "%d Guest deleted",
                        deletedRows[0], deletedRows[1]
                )
        );
    }

    /**
     * Deletes a user from the table and controller.
     *
     * @param tableModel The DefaultTableModel of the table.
     * @param table      The JTable from which the row will be deleted.
     * @param controller The UserController instance.
     */
    public static void deleteUser(DefaultTableModel tableModel, JTable table, UserController controller) {
        int id = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        int deletedRows = controller.delete(id);

        tableModel.removeRow(table.getSelectedRow());

        UserShowMessages.showMessage(
                null,
                String.format("%d Item successfully deleted", deletedRows)
        );
    }
}
