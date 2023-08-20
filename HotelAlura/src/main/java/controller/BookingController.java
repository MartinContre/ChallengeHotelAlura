package controller;

import dao.BookingDao;
import factory.ConnectionFactory;
import model.Booking;

import java.util.List;

public class BookingController {

    private final BookingDao bookingDao;

    public BookingController() {
        this.bookingDao = new BookingDao(ConnectionFactory.getConnection());
    }

    public List<Booking> list() {
        return bookingDao.getAll();
    }

    public List<Booking> list(String bookingId) {
        return bookingDao.list(bookingId);
    }

    public void insert(Booking booking) {
        bookingDao.insert(booking);
    }

    public Integer update(Booking booking) {
        return bookingDao.update(booking);
    }
}
