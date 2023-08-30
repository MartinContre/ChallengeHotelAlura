package controller;

import dao.GuestDao;
import factory.ConnectionFactory;
import model.Guest;

import java.util.List;

/**
 * The controller class for managing guest operations.
 * This class acts as an intermediary between the user interface and the data access layer (DAO)
 * for performing CRUD (Create, Read, Update, Delete) operations on guest entities.
 */
public class GuestController {

    /**
     * The data access object for guest operations.
     */
    private final GuestDao guestDao;

    /**
     * Constructs a new instance of the GuestController.
     * Initializes the GuestDao using a database connection from the ConnectionFactory.
     */
    public GuestController() {
        this.guestDao = new GuestDao(ConnectionFactory.getConnection());
    }

    /**
     * Retrieves a list of all guests stored in the system.
     *
     * @return A list of Guest objects representing all guests.
     */
    public List<Guest> list() {
        return guestDao.getAll();
    }

    /**
     * Retrieves a list of guests matching the provided surname.
     *
     * @param surname The surname of the guests to search for.
     * @return A list of Guest objects matching the provided surname.
     */
    public List<Guest> list(String surname) {
        return guestDao.searchGuest(surname);
    }

    /**
     * Inserts a new guest into the system.
     *
     * @param guest     The Guest object representing the new guest.
     * @param bookingId The ID of the associated booking for the guest.
     */
    public void insert(Guest guest, String bookingId) {
        guest.setBookingId(bookingId);
        guestDao.insert(guest);
    }

    /**
     * Deletes a guest and its associated bookings from the system.
     *
     * @param guestId   The ID of the guest to delete.
     * @param bookingId The ID of the associated booking to delete embedded bookings.
     * @return An array of Integer values indicating the number of affected rows in different tables.
     */
    public Integer[] delete(int guestId, String bookingId) {
        return guestDao.deleteEmbeddedBookings(guestId, bookingId);
    }

    /**
     * Updates guest information in the system.
     *
     * @param guest The Guest object representing the updated guest information.
     * @return The number of affected rows in the database after the update operation.
     */
    public Integer update(Guest guest) {
        return guestDao.update(guest);
    }
}
