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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;

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
     * Create the frame.
     */
    public SearchView() {
        this.guestController = new GuestController();
        this.bookingController = new BookingController();
        this.userController = new UserController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(SearchView.class.getResource("/images/lupa2.png")));
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
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
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
        panel.setBackground(new Color(12, 138, 199));
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
        panel.addTab("Reservas", new ImageIcon(Objects.requireNonNull(SearchView.class.getResource("/images/reservado.png"))), scroll_tableBooking, null);
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
        panel.addTab("Huéspedes", new ImageIcon(Objects.requireNonNull(SearchView.class.getResource("/images/pessoas.png"))), scroll_tableGuests, null);
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
        panel.addTab("Users", new ImageIcon(Objects.requireNonNull(SearchView.class.getResource("/images/employee.png"))), scroll_tableUsers, null);
        scroll_tableUsers.setVisible(true);

        TableColumn idColumnUser = userTable.getColumnModel().getColumn(0);
        idColumnUser.setCellEditor(new CopyableCellEditor());

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Objects.requireNonNull(SearchView.class.getResource("/images/Ha-100px.png"))));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = getjPanel();
        contentPane.add(header);

        JPanel backBtn = getPanel();
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
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel searchBtn = getjPanel(panel);
        contentPane.add(searchBtn);

        JLabel searchLabel = new JLabel("SEARCH");
        searchLabel.setBounds(0, 0, 122, 35);
        searchBtn.add(searchLabel);
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel editBtn = getPanel(panel);
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

    private JPanel getPanel() {
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
                backBtn.setBackground(new Color(12, 138, 199));
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
        deleteBtn.setBackground(new Color(12, 138, 199));
        deleteBtn.setBounds(767, 508, 122, 35);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return deleteBtn;
    }

    private JPanel getPanel(JTabbedPane panel) {
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
        editBtn.setBackground(new Color(12, 138, 199));
        editBtn.setBounds(635, 508, 122, 35);
        editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return editBtn;
    }

    private JPanel getjPanel(JTabbedPane panel) {
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
        searchBtn.setBackground(new Color(12, 138, 199));
        searchBtn.setBounds(748, 125, 122, 35);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return searchBtn;
    }

    private JPanel getjPanel() {
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


    private void loadGuestTable() {
        LoadTableUtility.guestTable(tableModelGuest, this.guestController);
        clearSearchTxt();
    }

    private void loadGuestTable(JTextField searchField) {
        LoadTableUtility.guestTable(tableModelGuest, this.guestController, searchField);
    }

    private void loadBookingTable() {
        LoadTableUtility.bookingTable(tableModelBooking, this.bookingController, bookingTable);
        clearSearchTxt();
    }

    private void loadBookingTable(JTextField searchField) {
        LoadTableUtility.bookingTable(tableModelBooking, this.bookingController, bookingTable, searchField);
    }

    private void loadUserTable() {
        LoadTableUtility.userTable(tableModelUsers, this.userController, userTable);
        clearSearchTxt();
    }

    private void loadUserTable(JTextField searchTxt) {
        LoadTableUtility.userTable(
                tableModelUsers,
                this.userController,
                userTable,
                searchTxt
        );
    }

    private void clearSearchTxt() {
        searchTxt.setText("");
    }

    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

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

    private void headerMouseDragged(MouseEvent evt) {

        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
