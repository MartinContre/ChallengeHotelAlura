package views;

import controller.BookingController;
import controller.GuestController;
import controller.UserController;
import utilities.JOptionPane.UserShowMessages;
import utilities.columns.CopyableCellEditor;
import utilities.enums.TablesColumns;
import utilities.tables.DeleteRowsFromTable;
import utilities.tables.LoadTableUtility;
import utilities.tables.UpdateDBFromRow;
import utilities.validation.FormValidationUtility;
import utilities.views.colors.ViewColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;


/**
 * The graphical user interface for searching and managing bookings, guests, and users.
 * This class provides a comprehensive interface for searching, editing, and managing booking, guest, and user data.
 * Users can perform various actions such as search, edit, and delete on different tabs.
 * Usage:
 * - Create an instance of this class to display the search and management interface.
 * Example:
 * SearchView 'searchView' = new SearchView();
 * searchView.setVisible(true);
 */
public class SearchView extends JFrame {

    private final GuestController guestController;
    private final BookingController bookingController;
    private final UserController userController;
    private final JTextField searchTxt;
    private final JTable guestTable;
    private final JTable bookingTable;
    private final JTable userTable;
    private final DefaultTableModel tableModelBooking;
    private final DefaultTableModel tableModelGuest;
    private final DefaultTableModel tableModelUsers;
    private final JLabel backLabel;
    private final JLabel labelExit;
    int xMouse, yMouse;


    /**
     * Creates the frame and initializes the necessary components.
     */
    public SearchView() {
        this.guestController = new GuestController();
        this.bookingController = new BookingController();
        this.userController = new UserController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(SearchView.class.getResource("/images/MagnifyingGlass.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        searchTxt = new JTextField();
        searchTxt.setBounds(536, 127, 193, 31);
        searchTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(searchTxt);
        searchTxt.setColumns(10);


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(ViewColors.vividSkyBlue());
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.addChangeListener(e -> {
            int selectedIndex = panel.getSelectedIndex();
            switch (selectedIndex) {
                case 0 -> loadBookingTable();
                case 1 -> loadGuestTable();
                case 2 -> loadUserTable();
            }
        });
        panel.setBackground(ViewColors.vividSkyBlue());
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        bookingTable = new JTable();
        bookingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookingTable.setFont(new Font("Roboto", Font.PLAIN, 16));
        tableModelBooking = (DefaultTableModel) bookingTable.getModel();
        tableModelBooking.addColumn(TablesColumns.ID.getKey());
        tableModelBooking.addColumn(TablesColumns.BOOKING_ID.getKey());
        tableModelBooking.addColumn(TablesColumns.CHECK_IN.getKey());
        tableModelBooking.addColumn(TablesColumns.CHECK_OUT.getKey());
        tableModelBooking.addColumn(TablesColumns.VALUE.getKey());
        tableModelBooking.addColumn(TablesColumns.PAYMENT_METHOD.getKey());
        JScrollPane scroll_tableBooking = new JScrollPane(bookingTable);
        panel.addTab("Reservas", new ImageIcon(Objects.requireNonNull(SearchView.class.getResource("/images/Booking.png"))), scroll_tableBooking, null);
        scroll_tableBooking.setVisible(true);

        TableColumn idColumnBooking = bookingTable.getColumnModel().getColumn(0);
        idColumnBooking.setCellEditor(new CopyableCellEditor());

        TableColumn bookingIdColumnBooking = bookingTable.getColumnModel().getColumn(1);
        bookingIdColumnBooking.setCellEditor(new CopyableCellEditor());

        TableColumn valueColumnBooking = bookingTable.getColumnModel().getColumn(4);
        valueColumnBooking.setCellEditor(new CopyableCellEditor());

        guestTable = new JTable();
        guestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        guestTable.setFont(new Font("Roboto", Font.PLAIN, 16));
        tableModelGuest = (DefaultTableModel) guestTable.getModel();
        tableModelGuest.addColumn(TablesColumns.GUEST_NUMBER.getKey());
        tableModelGuest.addColumn(TablesColumns.NAME.getKey());
        tableModelGuest.addColumn(TablesColumns.SURNAME.getKey());
        tableModelGuest.addColumn(TablesColumns.BIRTHDATE.getKey());
        tableModelGuest.addColumn(TablesColumns.NATIONALITY.getKey());
        tableModelGuest.addColumn(TablesColumns.PHONE.getKey());
        tableModelGuest.addColumn(TablesColumns.BOOKING_ID.getKey());
        JScrollPane scroll_tableGuests = new JScrollPane(guestTable);
        panel.addTab("Huéspedes", new ImageIcon(Objects.requireNonNull(SearchView.class.getResource("/images/SearchPeople.png"))), scroll_tableGuests, null);
        scroll_tableGuests.setVisible(true);

        TableColumn bookingIdColumnGuest = guestTable.getColumnModel().getColumn(6);
        bookingIdColumnGuest.setCellEditor(new CopyableCellEditor());

        TableColumn idColumnGuest = guestTable.getColumnModel().getColumn(0);
        idColumnGuest.setCellEditor(new CopyableCellEditor());

        userTable = new JTable();
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setFont(new Font("Roboto", Font.PLAIN, 16));
        tableModelUsers = (DefaultTableModel) userTable.getModel();
        tableModelUsers.addColumn(TablesColumns.USER_ID.getKey());
        tableModelUsers.addColumn(TablesColumns.USER_NAME.getKey());
        tableModelUsers.addColumn(TablesColumns.USER_CATEGORY.getKey());
        tableModelUsers.addColumn(TablesColumns.USER_PASSWORD.getKey());
        JScrollPane scroll_tableUsers = new JScrollPane(userTable);
        panel.addTab("Users", new ImageIcon(Objects.requireNonNull(SearchView.class.getResource("/images/Employee.png"))), scroll_tableUsers, null);
        scroll_tableUsers.setVisible(true);

        TableColumn idColumnUser = userTable.getColumnModel().getColumn(0);
        idColumnUser.setCellEditor(new CopyableCellEditor());

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Objects.requireNonNull(SearchView.class.getResource("/images/AH100px.png"))));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = getHeader();
        contentPane.add(header);

        JPanel backBtn = getBackBtn();
        header.add(backBtn);

        backLabel = new JLabel("<");
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backLabel.setFont(new Font("Roboto", Font.PLAIN, 23));
        backLabel.setBounds(0, 0, 53, 36);
        backBtn.add(backLabel);

        JPanel exitBtn = getExitBtn();
        header.add(exitBtn);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        exitBtn.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(ViewColors.vividSkyBlue());
        separator_1_2.setBackground(ViewColors.vividSkyBlue());
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel searchBtn = getHeader(panel);
        contentPane.add(searchBtn);

        JLabel searchLabel = new JLabel("SEARCH");
        searchLabel.setBounds(0, 0, 122, 35);
        searchBtn.add(searchLabel);
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel editBtn = getEditBtn(panel);
        contentPane.add(editBtn);

        JLabel editLabel = new JLabel("EDIT");
        editLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editLabel.setForeground(Color.WHITE);
        editLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        editLabel.setBounds(0, 0, 122, 35);
        editBtn.add(editLabel);

        JPanel deleteBtn = getDeleteBtn(panel);
        contentPane.add(deleteBtn);

        JLabel deleteLabel = new JLabel("DELETE");
        deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteLabel.setForeground(Color.WHITE);
        deleteLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        deleteLabel.setBounds(0, 0, 122, 35);
        deleteBtn.add(deleteLabel);
        setResizable(false);
    }

    /**
     * Creates and returns a JPanel representing the exit button.
     * This button, when clicked, navigates to a user menu view and changes appearance on hover.
     *
     * @return A JPanel containing the exit button with appropriate properties and actions.
     */
    private JPanel getExitBtn() {
        JPanel exitBtn = new JPanel();
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserMenuView userMenu = new UserMenuView();
                userMenu.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitBtn.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        exitBtn.setLayout(null);
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setBounds(857, 0, 53, 36);
        return exitBtn;
    }

    /**
     * Creates and returns a JPanel representing the back button.
     * This button provides navigation functionality to a user menu view and changes appearance on hover.
     *
     * @return A JPanel containing the edit (back) button with appropriate properties and actions.
     */

    private JPanel getBackBtn() {
        JPanel backBtn = new JPanel();
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserMenuView userMenuView = new UserMenuView();
                userMenuView.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backBtn.setBackground(ViewColors.vividSkyBlue());
                backLabel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBtn.setBackground(Color.white);
                backLabel.setForeground(Color.black);
            }
        });
        backBtn.setLayout(null);
        backBtn.setBackground(Color.WHITE);
        backBtn.setBounds(0, 0, 53, 36);
        return backBtn;
    }

    /**
     * Creates and returns a JPanel representing the deleted button.
     * This button, when clicked, performs different delete actions based on the selected tab.
     *
     * @param panel The JTabbedPane instance from which the current selected tab index is determined.
     * @return A JPanel containing the deleted button with appropriate properties and actions.
     */
    private JPanel getDeleteBtn(JTabbedPane panel) {
        JPanel deleteBtn = new JPanel();
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = panel.getSelectedIndex();
                switch (selectedIndex) {
                    case 0 -> deleteBooking();
                    case 1 -> deleteGuest();
                    case 2 -> deleteUser();
                }
            }
        });
        deleteBtn.setLayout(null);
        deleteBtn.setBackground(ViewColors.vividSkyBlue());
        deleteBtn.setBounds(767, 508, 122, 35);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return deleteBtn;
    }

    /**
     * Creates and returns a JPanel representing the edit button.
     * This button, when clicked, performs different update actions based on the selected tab.
     *
     * @param panel The JTabbedPane instance from which the current selected tab index is determined.
     * @return A JPanel containing the edit button with appropriate properties and actions.
     */
    private JPanel getEditBtn(JTabbedPane panel) {
        JPanel editBtn = new JPanel();
        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = panel.getSelectedIndex();
                switch (selectedIndex) {
                    case 0 -> updateBooking();
                    case 1 -> updateGuest();
                    case 2 -> updateUser();
                }
            }
        });
        editBtn.setLayout(null);
        editBtn.setBackground(ViewColors.vividSkyBlue());
        editBtn.setBounds(635, 508, 122, 35);
        editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return editBtn;
    }

    /**
     * Creates and returns a JPanel representing the search button in the header.
     * This button, when clicked, triggers table loading based on the selected tab and the provided search text.
     *
     * @param panel The JTabbedPane instance from which the current selected tab index is determined.
     * @return A JPanel containing the search button with appropriate properties and actions.
     */
    private JPanel getHeader(JTabbedPane panel) {
        JPanel searchBtn = new JPanel();
        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (FormValidationUtility.validateSearchField(searchTxt)) {
                    int selectedIndex = panel.getSelectedIndex();
                    switch (selectedIndex) {
                        case 0 -> loadBookingTable(searchTxt);
                        case 1 -> loadGuestTable(searchTxt);
                        case 2 -> loadUserTable(searchTxt);
                    }
                }
                e.consume();
            }
        });
        searchBtn.setLayout(null);
        searchBtn.setBackground(ViewColors.vividSkyBlue());
        searchBtn.setBounds(748, 125, 122, 35);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return searchBtn;
    }

    /**
     * Creates and returns a JPanel representing the header area.
     * This header area is designed to support mouse dragging for window movement.
     *
     * @return A JPanel containing the header with appropriate properties and actions.
     */
    private JPanel getHeader() {
        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        return header;
    }

    /**
     * Loads the data in the guest table from the DB.
     */
    private void loadGuestTable() {
        LoadTableUtility.guestTable(tableModelGuest, this.guestController);
        clearSearchTxt();
    }

    /**
     * Loads the data searched by Surname in the guest table from the DB.
     */
    private void loadGuestTable(JTextField searchField) {
        LoadTableUtility.guestTable(tableModelGuest, this.guestController, searchField);
    }

    /**
     * Loads the data in the booking table from the DB.
     */
    private void loadBookingTable() {
        LoadTableUtility.bookingTable(tableModelBooking, this.bookingController, bookingTable);
        clearSearchTxt();
    }

    /**
     * Loads the data searched by booking id in the booking table from the DB.
     */
    private void loadBookingTable(JTextField searchField) {
        LoadTableUtility.bookingTable(tableModelBooking, this.bookingController, bookingTable, searchField);
    }

    /**
     * Load the data in the user table.
     */
    private void loadUserTable() {
        LoadTableUtility.userTable(tableModelUsers, this.userController, userTable);
        clearSearchTxt();
    }

    /**
     * Loads the data searched by category in the user table from the DB.
     */
    private void loadUserTable(JTextField searchTxt) {
        LoadTableUtility.userTable(
                tableModelUsers,
                this.userController,
                userTable,
                searchTxt
        );
    }

    /**
     * Clears the search text field.
     */
    private void clearSearchTxt() {
        searchTxt.setText("");
    }

    /**
     * Deletes a guest from the table.
     */
    private void deleteGuest() {
        int selectedRow = guestTable.getSelectedRow();

        if (selectedRow != -1) {
            DeleteRowsFromTable.deleteGuest(tableModelGuest, guestTable, this.guestController);
        } else {
            UserShowMessages.showErrorMessage(
                    "Row not selected",
                    "Please select a row to be deleted"
            );
        }
    }

    /**
     * Deletes a booking from the table.
     */
    private void deleteBooking() {
        int selectedRow = bookingTable.getSelectedRow();

        if (selectedRow != -1) {
            DeleteRowsFromTable.deleteBooking(tableModelBooking, bookingTable, this.bookingController);
        } else {
            UserShowMessages.showErrorMessage(
                    "Row not selected",
                    "Please select a row to be deleted"
            );
        }
    }

    /**
     * Deletes a user from the table.
     */
    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();

        if (selectedRow != -1) {
            DeleteRowsFromTable.deleteUser(tableModelUsers, userTable, this.userController);
        } else {
            UserShowMessages.showErrorMessage(
                    "Row not selected",
                    "Please select a row to be deleted"
            );
        }
    }

    /**
     * Updates a booking in the table.
     */
    private void updateBooking() {
        int selectedRow = bookingTable.getSelectedRow();

        if (selectedRow != -1) {
            UpdateDBFromRow.updateBooking(tableModelBooking, bookingTable, this.bookingController);
            loadBookingTable();

        } else {
            UserShowMessages.showErrorMessage(
                    "Row not selected",
                    "Please select a row to be deleted"
            );
        }
    }

    /**
     * Updates a guest in the table.
     */
    private void updateGuest() {
        int selectedRow = guestTable.getSelectedRow();

        if (selectedRow != -1) {
            UpdateDBFromRow.updateGuest(tableModelGuest, guestTable, this.guestController);
            loadGuestTable();

        } else {
            UserShowMessages.showErrorMessage(
                    "Row not selected",
                    "Please select a row to be deleted"
            );
        }
    }

    /**
     * Updates a user in the table.
     */
    private void updateUser() {
        int selectedRow = userTable.getSelectedRow();

        if (selectedRow != -1) {
            UpdateDBFromRow.updateUser(tableModelUsers, userTable, this.userController);
            loadUserTable();

        } else {
            UserShowMessages.showErrorMessage(
                    "Row not selected",
                    "Please select a row to be deleted"
            );
        }
    }

    /**
     * Handles the mouse-dragged event on the header for window dragging.
     *
     * @param evt The mouse event
     */
    private void headerMouseDragged(MouseEvent evt) {

        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    /**
     * Handles the mouse-pressed event on the header.
     *
     * @param evt The mouse event
     */
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }
}
