package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import controller.BookingController;
import controller.GuestController;
import model.Guest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.DateConvertor;
import utilities.FormValidationUtility;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.text.Format;
import java.awt.Toolkit;
import java.util.Objects;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class RegistroHuesped extends JFrame {
	private static final Logger LOGGER = LogManager.getLogger(JFrame.class);

	private final JPanel contentPane;
	private final JTextField nameTxt;
	private final JTextField surnameTxt;
	private final JTextField phoneTxt;
	private final JTextField bookingIdText;
	private final JDateChooser birthdateTxt;
	private final JComboBox<Format> nationalityTxt;
	private final JLabel exitLabel;
	private final JLabel backLabel;
	int xMouse, yMouse;

	ReservasView reservasView = new ReservasView();
	private final GuestController guestController;
	private final BookingController bookingController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                RegistroHuesped frame = new RegistroHuesped();
                frame.setVisible(true);
            } catch (Exception e) {
				LOGGER.error(e.getMessage());
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public RegistroHuesped() {
		this.guestController = new GuestController();
		this.bookingController = new BookingController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/images/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
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
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				backLabel.setForeground(Color.black);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(new Color(12, 138, 199));
			     backLabel.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		backLabel = new JLabel("<");
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backLabel.setForeground(Color.WHITE);
		backLabel.setFont(new Font("Roboto", Font.PLAIN, 23));
		backLabel.setBounds(0, 0, 53, 36);
		btnAtras.add(backLabel);
		
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
		nameTxt.setBounds(560, 135, 285, 33);
		nameTxt.setBackground(Color.WHITE);
		nameTxt.setColumns(10);
		nameTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(nameTxt);
		
		surnameTxt = new JTextField();
		surnameTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
		surnameTxt.setBounds(560, 204, 285, 33);
		surnameTxt.setColumns(10);
		surnameTxt.setBackground(Color.WHITE);
		surnameTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(surnameTxt);
		
		birthdateTxt = new JDateChooser();
		birthdateTxt.setBounds(560, 278, 285, 36);
		birthdateTxt.getCalendarButton().setIcon(new ImageIcon(Objects.requireNonNull(RegistroHuesped.class.getResource("/images/icon-reservas.png"))));
		birthdateTxt.getCalendarButton().setBackground(SystemColor.textHighlight);
		birthdateTxt.setDateFormatString("yyyy-MM-dd");
		contentPane.add(birthdateTxt);
		
		nationalityTxt = new JComboBox<>();
		nationalityTxt.setBounds(560, 350, 289, 36);
		nationalityTxt.setBackground(SystemColor.text);
		nationalityTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
		nationalityTxt.setModel(new DefaultComboBoxModel(new String[] {"afgano-afgana", "alemán-", "alemana", "árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china", "colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana", "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña", "escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa", "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa", "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní", "irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí", "mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa", "panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa", "puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana", "uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita"}));
		contentPane.add(nationalityTxt);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(562, 119, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 189, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);
		
		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 256, 255, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblFechaN);
		
		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 326, 255, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidad);
		
		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 406, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);
		
		phoneTxt = new JTextField();
		phoneTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
		phoneTxt.setBounds(560, 424, 285, 33);
		phoneTxt.setColumns(10);
		phoneTxt.setBackground(Color.WHITE);
		phoneTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(phoneTxt);
		
		JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
		lblTitulo.setBounds(606, 55, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);
		
		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);
		lblNumeroReserva.setText(reservasView.getBooking().getBookingId());

		bookingIdText = new JTextField();
		bookingIdText.setFont(new Font("Roboto", Font.PLAIN, 16));
		bookingIdText.setBounds(560, 495, 285, 33);
		bookingIdText.setColumns(10);
		bookingIdText.setBackground(Color.WHITE);
		bookingIdText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(bookingIdText);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);
		
		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);
		
		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);
		
		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);
		
		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);
		
		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(723, 560, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e.consume();
				saveBooking();
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(Objects.requireNonNull(RegistroHuesped.class.getResource("/images/registro.png"))));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(Objects.requireNonNull(RegistroHuesped.class.getResource("/images/Ha-100px.png"))));
		
		JPanel btnExit = new JPanel();
		btnExit.setBounds(857, 0, 53, 36);
		contentPane.add(btnExit);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame principal = new MenuPrincipal();
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

	private void saveBooking() {
		if (FormValidationUtility.isGuestFormValid(
				nameTxt.getText(),
				surnameTxt.getText(),
				birthdateTxt,
				phoneTxt.getText()
				)) {
			this.bookingController.insert(reservasView.getBooking());
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
		this.guestController.insert(guest, reservasView.getBooking().getBookingId());
		showSaveMessage();
	}

	private void showSaveMessage() {
		Exito success = new Exito();
		success.setVisible(true);
		this.dispose();
	}

	private void clearFields() {
		nameTxt.setText("");
		surnameTxt.setText("");
		birthdateTxt.setDate(null);
		nationalityTxt.setSelectedIndex(0);
		phoneTxt.setText("");
	}
	
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
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
