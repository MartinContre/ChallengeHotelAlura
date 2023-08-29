package views;

import utilities.views.colors.ViewColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UserMenuView extends JFrame {

	int xMouse, yMouse;
	private final JLabel labelExit;


	public UserMenuView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserMenuView.class.getResource("/images/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
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
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(12, 138, 199));
		panelMenu.setBounds(0, 0, 257, 609);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(50, 58, 150, 150);
		panelMenu.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(Objects.requireNonNull(UserMenuView.class.getResource("/images/aH-150px.png"))));
		
		JPanel registerBtn = new JPanel();
		registerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				registerBtn.setBackground(ViewColors.backgroundColorBtn());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				registerBtn.setBackground(ViewColors.darkBackGroundColorBtn());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				BookingsView bookingsView = new BookingsView();
				bookingsView.setVisible(true);
				dispose();
			}
		});
		registerBtn.setBounds(0, 255, 257, 56);
		registerBtn.setBackground(ViewColors.darkBackGroundColorBtn());
		panelMenu.add(registerBtn);
		registerBtn.setLayout(null);

		JLabel registerLabel = new JLabel("Registro de reservas");
		registerLabel.setIcon(new ImageIcon(Objects.requireNonNull(UserMenuView.class.getResource("/images/reservado.png"))));
		registerLabel.setForeground(SystemColor.text);
		registerLabel.setBounds(25, 11, 205, 34);
		registerLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		registerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		registerBtn.add(registerLabel);
		
		JPanel searchBtn = new JPanel();
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchBtn.setBackground(ViewColors.backgroundColorBtn());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				searchBtn.setBackground(ViewColors.darkBackGroundColorBtn());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchView searchView = new SearchView();
				searchView.setVisible(true);
				dispose();
			}
		});
		searchBtn.setBounds(0, 312, 257, 56);
		searchBtn.setBackground(ViewColors.darkBackGroundColorBtn());
		panelMenu.add(searchBtn);
		searchBtn.setLayout(null);
		
		JLabel searchLabel = new JLabel("Búsqueda");
		searchLabel.setIcon(new ImageIcon(Objects.requireNonNull(UserMenuView.class.getResource("/images/pessoas.png"))));
		searchLabel.setBounds(27, 11, 200, 34);
		searchLabel.setHorizontalAlignment(SwingConstants.LEFT);
		searchLabel.setForeground(Color.WHITE);
		searchLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		searchBtn.add(searchLabel);

		JPanel registerUserBtn = new JPanel();
		registerUserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserRegisterView userRegisterView = new UserRegisterView();
				userRegisterView.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				registerUserBtn.setBackground(ViewColors.backgroundColorBtn());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				registerUserBtn.setBackground(ViewColors.darkBackGroundColorBtn());
			}
		});
		registerUserBtn.setBounds(0, 369, 257, 56);
		registerUserBtn.setBackground(ViewColors.darkBackGroundColorBtn());
		panelMenu.add(registerUserBtn);
		registerUserBtn.setLayout(null);

		JLabel registerUserLabel = new JLabel("Register user");
		registerUserLabel.setIcon(new ImageIcon(Objects.requireNonNull(UserMenuView.class.getResource("/images/registerUser.png"))));
		registerUserLabel.setBounds(29, 11, 200, 34);
		registerUserLabel.setForeground(SystemColor.text);
		registerUserLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		registerUserLabel.setHorizontalAlignment(SwingConstants.LEFT);
		registerUserBtn.add(registerUserLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		panelMenu.add(separator);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);


		JPanel exitBtn = getjPanel();
		header.add(exitBtn);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		exitBtn.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
	    JPanel datePanel = new JPanel();
	    datePanel.setBackground(new Color(118, 187, 223));
	    datePanel.setBounds(256, 84, 688, 121);
	    contentPane.add(datePanel);
	    datePanel.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("Sistema de reservas Hotel Alura");
	    lblNewLabel_1.setBounds(180, 11, 356, 42);
	    datePanel.add(lblNewLabel_1);
	    lblNewLabel_1.setForeground(Color.WHITE);
	    lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 24));
	    
	    JLabel dateLabel = new JLabel("");
	    dateLabel.setBounds(35, 64, 294, 36);
	    datePanel.add(dateLabel);
	    dateLabel.setForeground(Color.WHITE);
	    dateLabel.setFont(new Font("Roboto", Font.PLAIN, 33));
	    Date actualDate = new Date();
	    String date = new SimpleDateFormat("dd/MM/yyyy").format(actualDate);
	    dateLabel.setText("Today is " + date);
	    
	    JLabel lblNewLabel = new JLabel("Bienvenido");
	    lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 24));
	    lblNewLabel.setBounds(302, 234, 147, 46);
	    contentPane.add(lblNewLabel);
	    
	    String descriptionTxt = "<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
	    JLabel descriptionLabel = new JLabel(descriptionTxt);
	    descriptionLabel.setFont(new Font("Roboto", Font.PLAIN, 17));
	   
	    descriptionLabel.setBounds(312, 291, 598, 66);
	    contentPane.add(descriptionLabel);
	    
	    String descriptionText1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
	    JLabel descriptionLabel1 = new JLabel(descriptionText1);
	    descriptionLabel1.setFont(new Font("Roboto", Font.PLAIN, 17));
	    descriptionLabel1.setBounds(311, 345, 569, 88);
	    contentPane.add(descriptionLabel1);
	    
	    JLabel firstUsage = new JLabel("- Registro de Reservas y Huéspedes");
	    firstUsage.setFont(new Font("Roboto", Font.PLAIN, 17));
	    firstUsage.setBounds(312, 444, 295, 27);
	    contentPane.add(firstUsage);
	    
	    JLabel secondUsage = new JLabel("- Edición de Reservas y Huéspedes existentes");
	    secondUsage.setFont(new Font("Roboto", Font.PLAIN, 17));
	    secondUsage.setBounds(312, 482, 355, 27);
	    contentPane.add(secondUsage);
	    
	    JLabel thirdUsage = new JLabel("- Eliminar todo tipo de registros");
	    thirdUsage.setFont(new Font("Roboto", Font.PLAIN, 17));
	    thirdUsage.setBounds(312, 520, 295, 27);
	    contentPane.add(thirdUsage);
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
		exitBtn.setBackground(Color.WHITE);
		exitBtn.setBounds(891, 0, 53, 36);
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
