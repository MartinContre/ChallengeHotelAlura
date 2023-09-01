package utilities.tables;

import model.Booking;
import model.Guest;
import model.User;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Utility class for filling JTables with data from model objects.
 * This utility class provides methods to populate JTables with
 * data from lists of Booking, Guest, and User objects.
 */
public class FillTablesUtility {

    private FillTablesUtility() {

    }

    /**
     * Fills the booking table with data from a list of Booking objects.
     *
     * @param bookingList The list of Booking objects.
     * @param tableModel  The DefaultTableModel of the table.
     */
    public static void fillBookingTable(List<Booking> bookingList, DefaultTableModel tableModel) {
        bookingList.forEach(booking ->
                tableModel.addRow(
                        new Object[]{
                                booking.getId(),
                                booking.getBookingId(),
                                booking.getCheckIn(),
                                booking.getCheckOut(),
                                booking.getValue(),
                                booking.getPaymentMethod()
                        }
                )
        );
    }

    /**
     * Fills the guest table with data from a list of Guest objects.
     *
     * @param guestList  The list of Guest objects.
     * @param tableModel The DefaultTableModel of the table.
     */
    public static void fillGuestTable(List<Guest> guestList, DefaultTableModel tableModel) {
        guestList.forEach(guest ->
                tableModel.addRow(
                        new Object[]{
                                guest.getId(),
                                guest.getName(),
                                guest.getSurname(),
                                guest.getBirthdate(),
                                guest.getNationality(),
                                guest.getPhone(),
                                guest.getBookingId()
                        }
                )
        );
    }

    /**
     * Fills the user table with data from a list of User objects.
     *
     * @param userList   The list of User objects.
     * @param tableModel The DefaultTableModel of the table.
     */
    public static void fillUserTable(List<User> userList, DefaultTableModel tableModel) {
        userList.forEach(user ->
                tableModel.addRow(
                        new Object[]{
                                user.getId(),
                                user.getName(),
                                user.getCategory(),
                                user.getPassword()
                        }
                )
        );
    }
}
