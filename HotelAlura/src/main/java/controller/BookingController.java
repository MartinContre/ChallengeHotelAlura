package controller;

import dao.BookingDao;
import factory.ConnectionFactory;
import model.Booking;

import java.util.List;

/**
 * The controller class for managing booking operations.
 * This class acts as an intermediary between the user interface and the data access layer (DAO)
 * for performing CRUD (Create, Read, Update, Delete) operations on booking entities.
 */
public class BookingController {

    /**
     * The data access object for booking operations.
     */
    private final BookingDao bookingDao;

    /**
     * Constructs a new instance of the BookingController.
     * Initializes the BookingDao using a database connection from the ConnectionFactory.
     */
    public BookingController() {
        this.bookingDao = new BookingDao(ConnectionFactory.getConnection());
    }

    /**
     * Retrieves a list of all bookings stored in the system.
     *
     * @return A list of Booking objects representing all bookings.
     */
    public List<Booking> list() {
        return bookingDao.getAll();
    }

    /**
     * Retrieves a list of bookings matching the provided booking ID.
     *
     * @param bookingId The ID of the booking to search for.
     * @return A list of Booking objects matching the provided booking ID.
     */
    public List<Booking> list(String bookingId) {
        return bookingDao.list(bookingId);
    }

    /**
     * Inserts a new booking into the system.
     *
     * @param booking The Booking object representing the new booking.
     */
    public void insert(Booking booking) {
        bookingDao.insert(booking);
    }

    /**
     * Deletes embedded guest information associated with a booking.
     *
     * @param bookingId The ID of the booking for which embedded guest information should be deleted.
     * @return An array of Integer values indicating the number of affected rows in different tables.
     */
    public Integer[] deleteEmbeddedGuest(String bookingId) {
        return bookingDao.deleteEmbeddedGuest(bookingId);
    }

    /**
     * Updates an existing booking in the system.
     *
     * @param booking The Booking object representing the updated booking information.
     * @return The number of affected rows in the database after the update operation.
     */
    public Integer update(Booking booking) {
        return bookingDao.update(booking);
    }
}
