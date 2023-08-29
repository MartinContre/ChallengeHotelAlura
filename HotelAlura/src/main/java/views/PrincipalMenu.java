package views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;

public class PrincipalMenu extends JFrame {
	private static final Logger LOGGER = LogManager.getLogger(PrincipalMenu.class);

	private final JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                PrincipalMenu frame = new PrincipalMenu();
                frame.setVisible(true);
            } catch (Exception e) {
				LOGGER.error(e.getMessage());
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public PrincipalMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalMenu.class.getResource("/images/aH-40px.png")));
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
		backGroundImage.setIcon(new ImageIcon(Objects.requireNonNull(PrincipalMenu.class.getResource("/images/menu-img.png"))));
		panel.add(backGroundImage);
		
		JLabel logo = new JLabel("");
		logo.setBounds(722, 80, 150, 156);
		logo.setIcon(new ImageIcon(Objects.requireNonNull(PrincipalMenu.class.getResource("/images/aH-150px.png"))));
		panel.add(logo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 500, 910, 37);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCopyR = new JLabel("Developed by Martin P © 2023");
		lblCopyR.setBounds(315, 11, 284, 19);
		lblCopyR.setForeground(new Color(240, 248, 255));
		lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel_1.add(lblCopyR);
		
		//Bar window control
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
		header.setBackground(Color.WHITE);
		panel.add(header);

		JPanel exitBtn = getjPanel();
		header.add(exitBtn);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		exitBtn.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnLogin = new JPanel();
		btnLogin.setBounds(754, 300, 83, 70);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.setBackground(SystemColor.window);
		panel.add(btnLogin);
		
		JLabel loginImage = new JLabel("");
		loginImage.setBounds(0, 0, 80, 70);
		btnLogin.add(loginImage);
		loginImage.setHorizontalAlignment(SwingConstants.CENTER);
		loginImage.setIcon(new ImageIcon(Objects.requireNonNull(PrincipalMenu.class.getResource("/images/login.png"))));
		
		JLabel titleLabel = new JLabel("LOGIN");
		titleLabel.setBounds(754, 265, 83, 24);
		titleLabel.setBackground(SystemColor.window);
		panel.add(titleLabel);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(SystemColor.textHighlight);
		titleLabel.setFont(new Font("Roboto Light", Font.PLAIN, 20));
	}

	private JPanel getjPanel() {
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
