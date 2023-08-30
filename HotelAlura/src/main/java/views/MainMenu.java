package views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.views.colors.ViewColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;

/**
 * The graphical user interface for the main menu of the Hotel Alura application.
 * This class provides the main menu interface with options for user interaction.
 * Users can access login functionality and exit the application from this view.
 * Usage:
 * - Create an instance of this class to display the main menu.
 * Example:
 * MainMenu 'mainMenu' = new MainMenu();
 * mainMenu.setVisible(true);
 */
public class MainMenu extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(MainMenu.class);

    private final JLabel labelExit;
    int xMouse, yMouse;

    /**
     * Creates the main menu frame.
     * Initializes the user interface components and controllers.
     */
    public MainMenu() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/images/AH40px.png")));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 910, 537);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);


        Panel panel = new Panel();
        panel.setBackground(SystemColor.window);
        panel.setBounds(0, 0, 910, 537);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel backGroundImage = new JLabel("");
        backGroundImage.setBounds(-50, 0, 732, 501);
        backGroundImage.setIcon(new ImageIcon(Objects.requireNonNull(MainMenu.class.getResource("/images/Menu.png"))));
        panel.add(backGroundImage);

        JLabel logo = new JLabel("");
        logo.setBounds(722, 80, 150, 156);
        logo.setIcon(new ImageIcon(Objects.requireNonNull(MainMenu.class.getResource("/images/AH150px.png"))));
        panel.add(logo);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 500, 910, 37);
        panel_1.setBackground(ViewColors.vividSkyBlue());
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblCopyR = new JLabel("Developed by Martin P © 2023");
        lblCopyR.setBounds(315, 11, 284, 19);
        lblCopyR.setForeground(ViewColors.aliceBlue());
        lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel_1.add(lblCopyR);

        JPanel header = getHeader();
        panel.add(header);

        JPanel exitBtn = getExitBtn();
        header.add(exitBtn);

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        exitBtn.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel loginBnt = getLoginBtn();
        panel.add(loginBnt);

        JLabel loginImage = new JLabel("");
        loginImage.setBounds(0, 0, 80, 70);
        loginBnt.add(loginImage);
        loginImage.setHorizontalAlignment(SwingConstants.CENTER);
        loginImage.setIcon(new ImageIcon(Objects.requireNonNull(MainMenu.class.getResource("/images/LoginIcon.png"))));

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setBounds(754, 265, 83, 24);
        titleLabel.setBackground(SystemColor.window);
        panel.add(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(SystemColor.textHighlight);
        titleLabel.setFont(new Font("Roboto Light", Font.PLAIN, 20));
    }

    /**
     * Launches the Hotel Alura application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainMenu frame = new MainMenu();
                frame.setVisible(true);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        });
    }

    /**
     * Creates and returns a JPanel representing the login button.
     * This button, when clicked, opens the login view and changes appearance on hover.
     *
     * @return A JPanel containing the login button with appropriate properties and actions.
     */
    private JPanel getLoginBtn() {
        JPanel loginBtn = new JPanel();
        loginBtn.setBounds(754, 300, 83, 70);
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView login = new LoginView();
                login.setVisible(true);
                dispose();
            }
        });
        loginBtn.setLayout(null);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setBackground(SystemColor.window);
        return loginBtn;
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
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        exitBtn.setLayout(null);
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setBounds(857, 0, 53, 36);
        return exitBtn;
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
