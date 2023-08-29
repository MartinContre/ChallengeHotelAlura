package controller;

import dao.GuestDao;
import factory.ConnectionFactory;
import model.Guest;

import java.util.List;

public class GuestController {
    private final GuestDao guestDao;
    public GuestController() {
        this.guestDao = new GuestDao(ConnectionFactory.getConnection());
    }

    public List<Guest> list() {
        return guestDao.getAll();
    }

    public List<Guest> list(String surname) {
        return guestDao.searchGuest(surname);
    }

    public void insert(Guest guest, String bookingId) {
        guest.setBookingId(bookingId);
        guestDao.insert(guest);
    }

    public Integer[] delete(int guestId, String bookingId) {
        return guestDao.deleteEmbeddedBookings(guestId, bookingId);
    }

    public Integer update(Guest guest) {
        return guestDao.update(guest);
    }
}
