package utilities.tables;

import controller.BookingController;
import controller.GuestController;
import controller.UserController;
import utilities.JOptionPane.UserShowMessages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DeleteRowsFromTable {

    private DeleteRowsFromTable() {

    }

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
