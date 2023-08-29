package utilities.tables;

import controller.BookingController;
import controller.GuestController;
import controller.UserController;
import model.Booking;
import model.Guest;
import model.User;
import utilities.CalculateValue;
import utilities.JOptionPane.UserShowMessages;
import utilities.enums.EmployeeCategory;
import utilities.enums.PaymentMethod;
import utilities.validation.SQLDataValidator;
import utilities.validation.TxtValidation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.Date;

public class UpdateDBFromRow {

    private UpdateDBFromRow() {

    }

    public static void updateGuest(DefaultTableModel tableModel, JTable table, GuestController controller) {
        int id = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        String guestName = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
        String surname = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
        String birthdateStr = tableModel.getValueAt(table.getSelectedRow(), 3).toString();

        Date birthdate = SQLDataValidator.isBirthdateValid(birthdateStr);
        String nationality = tableModel.getValueAt(table.getSelectedRow(), 4).toString();
        String phoneStr = tableModel.getValueAt(table.getSelectedRow(), 5).toString();

        String phone = TxtValidation.getValidPhoneNumber(phoneStr);

        String bookingId = tableModel.getValueAt(table.getSelectedRow(), 6).toString();

        Guest guest = new Guest(
                id,
                guestName,
                surname,
                birthdate,
                nationality,
                phone,
                bookingId
        );

        int guestUpdateCount = controller.update(guest);

        UserShowMessages.showMessage(
                null,
                String.format(
                        "%s User update", guestUpdateCount
                )
        );
    }

    public static void updateBooking(DefaultTableModel tableModel, JTable table, BookingController controller) {
        int id = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        String bookingId = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
        String strDateCheckIn = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
        Date checkIn = SQLDataValidator.isCheckInValid(strDateCheckIn);
        String strDateCheckOut = tableModel.getValueAt(table.getSelectedRow(), 3).toString();
        Date checkOut = SQLDataValidator.isCheckOutValid(strDateCheckOut, checkIn);
        BigDecimal value = CalculateValue.getBookingValue(checkIn, checkOut);
        String paymentMethodStr = tableModel.getValueAt(table.getSelectedRow(), 5).toString();
        PaymentMethod paymentMethod = PaymentMethod.valueOf(paymentMethodStr);

        Booking booking = new Booking(id, bookingId, checkIn, checkOut, value, paymentMethod);

        int bookingUpdateCount = controller.update(booking);

        UserShowMessages.showMessage(
                null,
                String.format(
                        "%s Booking update", bookingUpdateCount
                )
        );
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
