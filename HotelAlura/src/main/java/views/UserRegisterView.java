package views;

import controller.UserController;
import model.User;
import utilities.enums.EmployeeCategory;
import utilities.validation.FormValidationUtility;
import utilities.views.colors.ViewColors;
import utilities.views.separator.SeparatorUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;

/**
 * The graphical user interface for registering new users in the Hotel Alura application.
 * This view allows administrators to create new user accounts with different access privileges.
 * Usage:
 * - Create an instance of this class to display the user registration interface.
 * Example:
 * UserRegisterView 'userRegisterView' = new UserRegisterView();
 * userRegisterView.setVisible(true);
 */
public class UserRegisterView extends JFrame {

    private final UserController userController;
    private final JLabel exitLabel;
    private final JLabel backLabel;
    private final JTextField userNameTxt;
    private final JPasswordField passwordTxt;
    private final JComboBox<String> employeeCategoryTxt;

    int xMouse, yMouse;

    /**
     * Creates the user registration view.
     */
    public UserRegisterView() {
        this.userController = new UserController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(UserRegisterView.class.getResource("/images/Logo50px.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 634);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(SystemColor.text);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane.setLayout(null);

        JPanel header = getHeader();
        contentPane.add(header);

        JPanel backBtn = getBackBtn();
        header.add(backBtn);

        backLabel = new JLabel("<");
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backLabel.setForeground(Color.WHITE);
        backLabel.setFont(new Font("Roboto", Font.PLAIN, 23));
        backLabel.setBounds(0, 0, 53, 36);
        backBtn.add(backLabel);

        JLabel titleLabel = new JLabel("USER REGISTRATION");
        titleLabel.setBounds(606, 55, 234, 42);
        titleLabel.setForeground(ViewColors.vividSkyBlue());
        titleLabel.setFont(new Font("Roboto Black", Font.PLAIN, 23));
        contentPane.add(titleLabel);


        JPanel saveBtn = new JPanel();
        saveBtn.setBounds(723, 560, 122, 35);
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
                saveUser();
            }
        });
        saveBtn.setLayout(null);
        saveBtn.setBackground(ViewColors.vividSkyBlue());
        contentPane.add(saveBtn);
        saveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel saveLabel = new JLabel("SAVE");
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

        JPanel exitBtn = getExitBtn();
        contentPane.add(exitBtn);

        exitLabel = new JLabel("X");
        exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exitLabel.setForeground(Color.BLACK);
        exitLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        exitLabel.setBounds(0, 0, 53, 36);
        exitBtn.add(exitLabel);

        userNameTxt = new JTextField();
        userNameTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
        userNameTxt.setBounds(560, 135, 285, 33);
        userNameTxt.setBackground(Color.WHITE);
        userNameTxt.setColumns(10);
        userNameTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(userNameTxt);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(562, 119, 253, 14);
        nameLabel.setForeground(SystemColor.textInactiveText);
        nameLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(nameLabel);

        passwordTxt = new JPasswordField();
        passwordTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
        passwordTxt.setBounds(560, 204, 285, 33);
        passwordTxt.setColumns(10);
        passwordTxt.setBackground(Color.WHITE);
        passwordTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(passwordTxt);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(560, 189, 255, 14);
        passwordLabel.setForeground(SystemColor.textInactiveText);
        passwordLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(passwordLabel);

        employeeCategoryTxt = new JComboBox<>(EmployeeCategory.getAllDisplayNames());
        employeeCategoryTxt.setBounds(560, 350, 289, 36);
        employeeCategoryTxt.setBackground(SystemColor.text);
        employeeCategoryTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
        contentPane.add(employeeCategoryTxt);

        JLabel employeeCategoryLabel = new JLabel("EMPLOYEE CATEGORY");
        employeeCategoryLabel.setBounds(560, 326, 255, 14);
        employeeCategoryLabel.setForeground(SystemColor.textInactiveText);
        employeeCategoryLabel.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(employeeCategoryLabel);


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
                JFrame principal = new MainMenu();
                principal.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitBtn.setBackground(Color.red);
                exitLabel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn.setBackground(Color.white);
                exitLabel.setForeground(Color.black);
            }
        });
        exitBtn.setLayout(null);
        exitBtn.setBackground(Color.white);
        exitBtn.setBounds(857, 0, 53, 36);
        return exitBtn;
    }

    /**
     * Creates and returns a JPanel representing the header area.
     * This header area is designed to support mouse dragging for window movement.
     *
     * @return A JPanel containing the header with appropriate properties and actions.
     */
    private JPanel getHeader() {
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
        return header;
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

    /**
     * Saves the user in the database
     */
    private void saveUser() {
        String name = userNameTxt.getText();

        if (FormValidationUtility.isUserFormValid(
                name,
                employeeCategoryTxt,
                passwordTxt
        )) {
            User user = new User();
            user.setName(name);
            EmployeeCategory category = EmployeeCategory.valueOf(Objects.requireNonNull(employeeCategoryTxt.getSelectedItem()).toString());
            user.setCategory(category);

            char[] passwordChars = passwordTxt.getPassword();

            String password = new String(passwordChars);

            user.setPassword(password);

            this.userController.save(user);
            showSaveMessage();
        }
    }

    /**
     * If the user was saved successfully, show a message.
     */
    private void showSaveMessage() {
        SuccessDialog success = new SuccessDialog();
        success.setVisible(true);
        this.dispose();
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
