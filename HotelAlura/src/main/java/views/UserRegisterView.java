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
import java.util.Arrays;
import java.util.Objects;

public class UserRegisterView extends JFrame {

    private final UserController userController;
    private final JLabel exitLabel;
    private final JLabel backLabel;
    private final JTextField userNameTxt;
    private final JPasswordField passwordTxt;
    private final JComboBox<String> employeeCategoryTxt;

    int xMouse, yMouse;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserRegisterView frame = new UserRegisterView();
                frame.setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public UserRegisterView() {
        this.userController = new UserController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(UserRegisterView.class.getResource("/images/lOGO-50PX.png")));
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

        JLabel titleLabel = new JLabel("USER REGISTRATION");
        titleLabel.setBounds(606, 55, 234, 42);
        titleLabel.setForeground(new Color(12, 138, 199));
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
        saveBtn.setBackground(new Color(12, 138, 199));
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
        panel.setBackground(new Color(12, 138, 199));
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel backgroundLabel = new JLabel("");
        backgroundLabel.setBounds(0, 121, 479, 502);
        panel.add(backgroundLabel);
        backgroundLabel.setIcon(new ImageIcon(Objects.requireNonNull(GuestRegisterView.class.getResource("/images/registro.png"))));

        JLabel logo = new JLabel("");
        logo.setBounds(194, 39, 104, 107);
        panel.add(logo);
        logo.setIcon(new ImageIcon(Objects.requireNonNull(GuestRegisterView.class.getResource("/images/Ha-100px.png"))));

        JPanel exitBtn = new JPanel();
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        separator_1_2.setForeground(ViewColors.separatorColor());
        separator_1_2.setBackground(ViewColors.separatorColor());
        contentPane.add(separator_1_2);

        JSeparator separator_1_2_1 = new JSeparator();
        separator_1_2_1.setBounds(560, 240, SeparatorUtils.getWidth(), SeparatorUtils.getHeight());
        separator_1_2_1.setForeground(ViewColors.separatorColor());
        separator_1_2_1.setBackground(ViewColors.separatorColor());
        contentPane.add(separator_1_2_1);


    }

    private JPanel getjPanel() {
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
                backBtn.setBackground(new Color(12, 138, 199));
                backLabel.setForeground(Color.white);
            }
        });
        backBtn.setLayout(null);
        backBtn.setBackground(new Color(12, 138, 199));
        backBtn.setBounds(0, 0, 53, 36);
        return backBtn;
    }

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
            user.setPassword(Arrays.toString(passwordTxt.getPassword()));

            this.userController.save(user);
            showSaveMessage();
        }
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

    private void showSaveMessage() {
        Successful success = new Successful();
        success.setVisible(true);
        this.dispose();
    }

}
