package views;

import com.toedter.calendar.JDateChooser;
import controller.BookingController;
import controller.GuestController;
import model.Guest;
import utilities.DateConvertor;
import utilities.enums.Nationality;
import utilities.validation.FormValidationUtility;
import utilities.views.colors.ViewColors;
import utilities.views.separator.SeparatorUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.Objects;

public class GuestRegisterView extends JFrame {

    private final JTextField nameTxt;
    private final JTextField surnameTxt;
    private final JTextField phoneTxt;
    private final JDateChooser birthdateTxt;
    private final JComboBox<String> nationalityTxt;
    private final JLabel exitLabel;
    private final JLabel backLabel;
    private final GuestController guestController;
    private final BookingController bookingController;
    int xMouse, yMouse;
    BookingsView bookingsView = new BookingsView();


    /**
     * Create the frame.
     */
    public GuestRegisterView() {
        this.guestController = new GuestController();
        this.bookingController = new BookingController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(GuestRegisterView.class.getResource("/images/Logo50px.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 634);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(SystemColor.text);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane.setLayout(null);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 910, 36);
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
        header.setBackground(SystemColor.text);
        header.setOpaque(false);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel backBtn = getjPanel();
        header.add(backBtn);

        backLabel = new JLabel("<");
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backLabel.setForeground(Color.WHITE);
        backLabel.setFont(new Font("Roboto", Font.PLAIN, 23));
        backLabel.setBounds(0, 0, 53, 36);
        backBtn.add(backLabel);

        JLabel titleLabel = new JLabel("REGISTRO HUÉSPED");
        titleLabel.setBounds(606, 55, 234, 42);
        titleLabel.setForeground(ViewColors.vividSkyBlue());
        titleLabel.setFont(new Font("Roboto Black", Font.PLAIN, 23));
        contentPane.add(titleLabel);

        nameTxt = new JTextField();
        nameTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
        nameTxt.setBounds(560, 135, 285, 33);
        nameTxt.setBackground(Color.WHITE);
        nameTxt.setColumns(10);
        nameTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(nameTxt);

        JLabel nameLabel = new JLabel("NOMBRE");
        nameLabel.setBounds(562, 119, 253, 14);
        nameLabel.setForeground(SystemColor.textInactiveText);
        nameLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(nameLabel);

        surnameTxt = new JTextField();
        surnameTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
        surnameTxt.setBounds(560, 204, 285, 33);
        surnameTxt.setColumns(10);
        surnameTxt.setBackground(Color.WHITE);
        surnameTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(surnameTxt);

        JLabel surnameLabel = new JLabel("APELLIDO");
        surnameLabel.setBounds(560, 189, 255, 14);
        surnameLabel.setForeground(SystemColor.textInactiveText);
        surnameLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(surnameLabel);

        birthdateTxt = new JDateChooser();
        birthdateTxt.setBounds(560, 278, 285, 36);
        birthdateTxt.getCalendarButton().setIcon(new ImageIcon(Objects.requireNonNull(GuestRegisterView.class.getResource("/images/BookingIcon.png"))));
        birthdateTxt.getCalendarButton().setBackground(SystemColor.textHighlight);
        birthdateTxt.setDateFormatString("yyyy-MM-dd");
        contentPane.add(birthdateTxt);

        JLabel birthdateLabel = new JLabel("FECHA DE NACIMIENTO");
        birthdateLabel.setBounds(560, 256, 255, 14);
        birthdateLabel.setForeground(SystemColor.textInactiveText);
        birthdateLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(birthdateLabel);

        nationalityTxt = new JComboBox<>(Nationality.getAllDisplayNames());
        nationalityTxt.setBounds(560, 350, 289, 36);
        nationalityTxt.setBackground(SystemColor.text);
        nationalityTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
        contentPane.add(nationalityTxt);

        JLabel nationalityLabel = new JLabel("NACIONALIDAD");
        nationalityLabel.setBounds(560, 326, 255, 14);
        nationalityLabel.setForeground(SystemColor.textInactiveText);
        nationalityLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(nationalityLabel);


        phoneTxt = new JTextField();
        phoneTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
        phoneTxt.setBounds(560, 424, 285, 33);
        phoneTxt.setColumns(10);
        phoneTxt.setBackground(Color.WHITE);
        phoneTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(phoneTxt);

        JLabel phoneLabel = new JLabel("TELÉFONO");
        phoneLabel.setBounds(562, 406, 253, 14);
        phoneLabel.setForeground(SystemColor.textInactiveText);
        phoneLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(phoneLabel);

        JLabel bookingIdLabel = new JLabel("NÚMERO DE RESERVA");
        bookingIdLabel.setBounds(560, 474, 253, 14);
        bookingIdLabel.setForeground(SystemColor.textInactiveText);
        bookingIdLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(bookingIdLabel);
        bookingIdLabel.setText(bookingsView.getBooking().getBookingId());

        JTextField bookingIdText = new JTextField();
        bookingIdText.setFont(new Font("Roboto", Font.PLAIN, 16));
        bookingIdText.setBounds(560, 495, 285, 33);
        bookingIdText.setColumns(10);
        bookingIdText.setBackground(Color.WHITE);
        bookingIdText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(bookingIdText);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBounds(560, 170, SeparatorUtils.getWidth(), SeparatorUtils.getHeight());
        separator_1_2.setForeground(ViewColors.vividSkyBlue());
        separator_1_2.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(separator_1_2);

        JSeparator separator_1_2_1 = new JSeparator();
        separator_1_2_1.setBounds(560, 240, SeparatorUtils.getWidth(), SeparatorUtils.getHeight());
        separator_1_2_1.setForeground(ViewColors.vividSkyBlue());
        separator_1_2_1.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(separator_1_2_1);

        JSeparator separator_1_2_2 = new JSeparator();
        separator_1_2_2.setBounds(560, 314, SeparatorUtils.getWidth(), SeparatorUtils.getHeight());
        separator_1_2_2.setForeground(ViewColors.vividSkyBlue());
        separator_1_2_2.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(separator_1_2_2);

        JSeparator separator_1_2_3 = new JSeparator();
        separator_1_2_3.setBounds(560, 386, SeparatorUtils.getWidth(), SeparatorUtils.getHeight());
        separator_1_2_3.setForeground(ViewColors.vividSkyBlue());
        separator_1_2_3.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(separator_1_2_3);

        JSeparator separator_1_2_4 = new JSeparator();
        separator_1_2_4.setBounds(560, 457, SeparatorUtils.getWidth(), SeparatorUtils.getHeight());
        separator_1_2_4.setForeground(ViewColors.vividSkyBlue());
        separator_1_2_4.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(separator_1_2_4);

        JSeparator separator_1_2_5 = new JSeparator();
        separator_1_2_5.setBounds(560, 529, SeparatorUtils.getWidth(), SeparatorUtils.getHeight());
        separator_1_2_5.setForeground(ViewColors.vividSkyBlue());
        separator_1_2_5.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(separator_1_2_5);

        JPanel saveBtn = new JPanel();
        saveBtn.setBounds(723, 560, 122, 35);
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
                saveBooking();
            }
        });
        saveBtn.setLayout(null);
        saveBtn.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(saveBtn);
        saveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel saveLabel = new JLabel("GUARDAR");
        saveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        saveLabel.setForeground(Color.WHITE);
        saveLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        saveLabel.setBounds(0, 0, 122, 35);
        saveBtn.add(saveLabel);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 489, 634);
        panel.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel backgroundLabel = new JLabel("");
        backgroundLabel.setBounds(0, 121, 479, 502);
        panel.add(backgroundLabel);
        backgroundLabel.setIcon(new ImageIcon(Objects.requireNonNull(GuestRegisterView.class.getResource("/images/Register.png"))));

        JLabel logo = new JLabel("");
        logo.setBounds(194, 39, 104, 107);
        panel.add(logo);
        logo.setIcon(new ImageIcon(Objects.requireNonNull(GuestRegisterView.class.getResource("/images/AH100px.png"))));

        JPanel btnExit = new JPanel();
        btnExit.setBounds(857, 0, 53, 36);
        contentPane.add(btnExit);
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame principal = new PrincipalMenu();
                principal.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnExit.setBackground(Color.red);
                exitLabel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnExit.setBackground(Color.white);
                exitLabel.setForeground(Color.black);
            }
        });
        btnExit.setLayout(null);
        btnExit.setBackground(Color.white);

        exitLabel = new JLabel("X");
        exitLabel.setBounds(0, 0, 53, 36);
        btnExit.add(exitLabel);
        exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exitLabel.setForeground(SystemColor.black);
        exitLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
    }

    private JPanel getjPanel() {
        JPanel backBtn = new JPanel();
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BookingsView bookingsView = new BookingsView();
                bookingsView.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backBtn.setBackground(Color.white);
                backLabel.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBtn.setBackground(ViewColors.vividSkyBlue());
                backLabel.setForeground(Color.white);
            }
        });
        backBtn.setLayout(null);
        backBtn.setBackground(ViewColors.vividSkyBlue());
        backBtn.setBounds(0, 0, 53, 36);
        return backBtn;
    }

    private void saveBooking() {
        if (FormValidationUtility.isGuestFormValid(
                nameTxt.getText(),
                surnameTxt.getText(),
                birthdateTxt,
                phoneTxt.getText()
        )) {
            this.bookingController.insert(bookingsView.getBooking());
            saveGuest();
        }
    }

    private void saveGuest() {
        Date birthDate = Date.valueOf(DateConvertor.convertDateToLocalDate(birthdateTxt.getDate()));
        Guest guest = new Guest(
                nameTxt.getText(),
                surnameTxt.getText(),
                birthDate,
                Objects.requireNonNull(nationalityTxt.getSelectedItem()).toString(),
                phoneTxt.getText()
        );
        this.guestController.insert(guest, bookingsView.getBooking().getBookingId());
        showSaveMessage();
    }

    private void showSaveMessage() {
        Successful success = new Successful();
        success.setVisible(true);
        this.dispose();
    }

    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

}
