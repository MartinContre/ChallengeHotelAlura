package controller;

import dao.GuestDao;
import factory.ConnectionFactory;
import model.Guest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class GuestController {
    private static final Logger LOGGER = LogManager.getLogger(GuestController.class);
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

    public Map<String, Integer> delete(Integer guestId, String bookingId) {
        LOGGER.info("Deleted guest: ");
        return guestDao.deleteEmbeddedBookings(guestId, bookingId);
    }

    public Integer update(Guest guest) {
        return guestDao.update(guest);
    }
}
