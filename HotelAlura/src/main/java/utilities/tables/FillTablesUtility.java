package utilities.tables;

import model.Booking;
import model.Guest;
import model.User;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FillTablesUtility {

    private FillTablesUtility() {

    }
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

    public static void fillGuestTable(List<Guest> guestList, DefaultTableModel tableModel) {
        guestList.forEach(guest ->
                tableModel.addRow(
                        new Object[] {
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
