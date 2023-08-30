package views;

import controller.UserController;
import model.User;
import utilities.JOptionPane.UserShowMessages;
import utilities.views.colors.ViewColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serial;
import java.util.List;
import java.util.Objects;


/**
 * The graphical user interface for the login view of the Hotel Alura application.
 * This class provides a form for users to input their login credentials and perform authentication.
 * The class also handles user validation and navigation.
 * Usage:
 * - Create an instance of this class to display the login form.
 * Example:
 * LoginView 'loginView' = new LoginView();
 * loginView.setVisible(true);
 */
public class LoginView extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private static User user;
    private final JTextField userField;
    private final JPasswordField passwordField;
    private final JLabel exitLabel;
    private final UserController userController;
    int xMouse, yMouse;


    /**
     * Create the login view frame.
     */
    public LoginView() {
        this.userController = new UserController();
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 527);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 788, 527);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(ViewColors.vividSkyBlue());
        panel_1.setBounds(484, 0, 304, 527);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel imgHotel = new JLabel("");
        imgHotel.setBounds(0, 0, 304, 538);
        panel_1.add(imgHotel);
        imgHotel.setIcon(new ImageIcon(Objects.requireNonNull(LoginView.class.getResource("/images/LoginHotel.png"))));

        JPanel exitBtn = getExitBtn();
        panel_1.add(exitBtn);

        exitLabel = new JLabel("X");
        exitLabel.setBounds(0, 0, 53, 36);
        exitBtn.add(exitLabel);
        exitLabel.setForeground(SystemColor.text);
        exitLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        exitLabel.setHorizontalAlignment(SwingConstants.CENTER);

        userField = new JTextField();
        userField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (userField.getText().equals("Enter your username")) {
                    userField.setText("");
                    userField.setForeground(Color.black);
                }
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.gray);
                }
            }
        });
        userField.setFont(new Font("Roboto", Font.PLAIN, 16));
        userField.setText("Enter your username");
        userField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        userField.setForeground(SystemColor.activeCaptionBorder);
        userField.setBounds(65, 256, 324, 32);
        panel.add(userField);
        userField.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 120, 215));
        separator.setBounds(65, 292, 324, 2);
        panel.add(separator);

        JLabel titleLabel = new JLabel("LOG IN");
        titleLabel.setForeground(SystemColor.textHighlight);
        titleLabel.setFont(new Font("Roboto Black", Font.PLAIN, 26));
        titleLabel.setBounds(65, 149, 202, 26);
        panel.add(titleLabel);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.textHighlight);
        separator_1.setBounds(65, 393, 324, 2);
        panel.add(separator_1);

        passwordField = new JPasswordField();
        passwordField.setText("********");
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("********")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);
                }
                if (userField.getText().isEmpty()) {
                    userField.setText("");
                    userField.setForeground(Color.gray);
                }
            }
        });
        passwordField.setForeground(SystemColor.activeCaptionBorder);
        passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        passwordField.setBounds(65, 353, 324, 32);
        panel.add(passwordField);

        JLabel userLabel = new JLabel("USER");
        userLabel.setForeground(SystemColor.textInactiveText);
        userLabel.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        userLabel.setBounds(65, 219, 107, 26);
        panel.add(userLabel);

        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setForeground(SystemColor.textInactiveText);
        passwordLabel.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        passwordLabel.setBounds(65, 316, 140, 26);
        panel.add(passwordLabel);

        JPanel btnLogin = getBtnLogin();
        panel.add(btnLogin);

        JLabel lblNewLabel = new JLabel("CONTINUE");
        lblNewLabel.setBounds(0, 0, 122, 44);
        btnLogin.add(lblNewLabel);
        lblNewLabel.setForeground(SystemColor.controlLtHighlight);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon(Objects.requireNonNull(LoginView.class.getResource("/images/Logo50px.png"))));
        lblNewLabel_1.setBounds(65, 65, 48, 59);
        panel.add(lblNewLabel_1);

        JPanel header = getHeader();
        panel.add(header);
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
        header.setBackground(SystemColor.window);
        header.setBounds(0, 0, 784, 36);
        return header;
    }

    /**
     * Creates and returns a JPanel representing the login button.
     * This button, when clicked, navigates to a user menu view and changes appearance on hover.
     *
     * @return A JPanel containing the login button with appropriate properties and actions.
     */
    private JPanel getBtnLogin() {
        JPanel btnLogin = new JPanel();
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 156, 223));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(SystemColor.textHighlight);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
                validateUser();
            }
        });
        btnLogin.setBackground(SystemColor.textHighlight);
        btnLogin.setBounds(65, 431, 122, 44);
        btnLogin.setLayout(null);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btnLogin;
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
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitBtn.setBackground(Color.red);
                exitLabel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn.setBackground(ViewColors.vividSkyBlue());
                exitLabel.setForeground(Color.white);
            }
        });
        exitBtn.setLayout(null);
        exitBtn.setBackground(ViewColors.vividSkyBlue());
        exitBtn.setBounds(251, 0, 53, 36);
        exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return exitBtn;
    }

    /**
     * Sets the user associated with this login view.
     *
     * @param user The user to set
     */
    public void setUser(User user) {
        LoginView.user = user;
    }

    /**
     * Clears the text fields.
     */
    private void clearFields() {
        userField.setText("");
        passwordField.setText("");
    }

    /**
     * Validates the user's input and perform login.
     */
    private void validateUser() {
        // ... [Method body, handling user validation and navigation to the user menu]
        if (userField.getText().isEmpty() && String.valueOf(passwordField.getPassword()).isEmpty()) {
            UserShowMessages.showErrorMessage(
                    "Empty form",
                    "Please field the form");
            clearFields();
        } else if (String.valueOf(passwordField.getPassword()).isEmpty()) {
            UserShowMessages.showErrorMessage(
                    "Password empty",
                    "Please fill password field");
            clearFields();
        } else if (userField.getText().isEmpty()) {
            UserShowMessages.showErrorMessage(
                    "User empty",
                    "Please fill user field");
            clearFields();
        } else {
            String userName = userField.getText();
            String password = String.valueOf(passwordField.getPassword());

            User userEnter = new User();
            userEnter.setName(userName);
            userEnter.setPassword(password);

            password = userEnter.getPassword();

            List<User> users = this.userController.list(userName, password);
            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "User or password incorrect"
                );
                clearFields();
            } else {
                users.forEach(this::setUser);
                this.dispose();

                UserMenuView userMenuView = new UserMenuView();
                userMenuView.setVisible(true);
            }
        }


    }

    /**
     * Handles the header mouse-pressed event.
     *
     * @param evt The mouse event
     */
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    /**
     * Handles the header mouse-dragged event.
     *
     * @param evt The mouse event
     */
    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
