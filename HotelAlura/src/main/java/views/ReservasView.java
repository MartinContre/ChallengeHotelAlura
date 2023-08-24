package views;

import com.toedter.calendar.JDateChooser;
import model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.DateConvertor;
import utilities.FormValidationUtility;
import utilities.JOptionPane.UserShowMessages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


public class ReservasView extends JFrame {

	private final static Logger LOGGER = LogManager.getLogger(ReservasView.class);
	private final JPanel contentPane;
	public static JTextField valueTxt;
	public static JDateChooser checkInTxt;
	public static JDateChooser checkOutTxt;
	public static JComboBox<String> paymentMethodTxt;
	int xMouse, yMouse;
	private final JLabel exitLabel;
	private final JLabel backLabel;
	private static Booking booking;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                ReservasView frame = new ReservasView();
                frame.setVisible(true);
            } catch (Exception e) {
				LOGGER.error(e.getMessage());
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public ReservasView() {

		super("Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/images/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		

		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Código que crea los elementos de la interfáz gráfica
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);
		
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 169, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);
		
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 187, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);
		
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 60, 219, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(Objects.requireNonNull(ReservasView.class.getResource("/images/Ha-100px.png"))));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(Objects.requireNonNull(ReservasView.class.getResource("/images/reservas-img-3.png"))));
		
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 196, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);
												
		JPanel exitBtn = new JPanel();
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame principal = new MenuPrincipal();
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
				 exitBtn.setBackground(new Color(12, 138, 199));
			     exitLabel.setForeground(Color.white);
			}
		});
		exitBtn.setLayout(null);
		exitBtn.setBackground(new Color(12, 138, 199));
		exitBtn.setBounds(429, 0, 53, 36);
		panel_1.add(exitBtn);
		
		exitLabel = new JLabel("X");
		exitLabel.setForeground(Color.WHITE);
		exitLabel.setBounds(0, 0, 53, 36);
		exitBtn.add(exitLabel);
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		
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
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserMenuView usuario = new UserMenuView();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				backLabel.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     backLabel.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		backLabel = new JLabel("<");
		backLabel.setBounds(0, 0, 53, 36);
		btnAtras.add(backLabel);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backLabel.setFont(new Font("Roboto", Font.PLAIN, 23));
		
		
		//Campos que guardaremos en la base de datos
		checkInTxt = new JDateChooser();
		checkInTxt.getCalendarButton().setBackground(SystemColor.textHighlight);
		checkInTxt.getCalendarButton().setIcon(new ImageIcon(Objects.requireNonNull(ReservasView.class.getResource("/images/icon-reservas.png"))));
		checkInTxt.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		checkInTxt.setBounds(68, 161, 289, 35);
		checkInTxt.getCalendarButton().setBounds(268, 0, 21, 33);
		checkInTxt.setBackground(Color.WHITE);
		checkInTxt.setBorder(new LineBorder(SystemColor.window));
		checkInTxt.setDateFormatString("yyyy-MM-dd");
		checkInTxt.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(checkInTxt);

		checkOutTxt = new JDateChooser();
		checkOutTxt.getCalendarButton().setIcon(new ImageIcon(Objects.requireNonNull(ReservasView.class.getResource("/images/icon-reservas.png"))));
		checkOutTxt.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		checkOutTxt.setBounds(68, 246, 289, 35);
		checkOutTxt.getCalendarButton().setBounds(267, 1, 21, 31);
		checkOutTxt.setBackground(Color.WHITE);
		checkOutTxt.setFont(new Font("Roboto", Font.PLAIN, 18));

		checkOutTxt.setDateFormatString("yyyy-MM-dd");
		checkOutTxt.getCalendarButton().setBackground(SystemColor.textHighlight);
		checkOutTxt.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(checkOutTxt);

		valueTxt = new JTextField();
		valueTxt.setBackground(SystemColor.text);
		valueTxt.setHorizontalAlignment(SwingConstants.CENTER);
		valueTxt.setForeground(Color.BLACK);
		valueTxt.setBounds(78, 328, 43, 33);
		valueTxt.setEditable(false);
		valueTxt.setFont(new Font("Roboto Black", Font.BOLD, 17));
		valueTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(valueTxt);
		valueTxt.setColumns(10);

		checkOutTxt.addPropertyChangeListener(evt -> getBookingValue());


		paymentMethodTxt = new JComboBox<>();
		paymentMethodTxt.setBounds(68, 417, 289, 38);
		paymentMethodTxt.setBackground(SystemColor.text);
		paymentMethodTxt.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		paymentMethodTxt.setFont(new Font("Roboto", Font.PLAIN, 16));
		paymentMethodTxt.setModel(new DefaultComboBoxModel<>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(paymentMethodTxt);

		JPanel nextBtn = new JPanel();
		nextBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveBookingReference();
			}						
		});
		nextBtn.setLayout(null);
		nextBtn.setBackground(SystemColor.textHighlight);
		nextBtn.setBounds(238, 493, 122, 35);
		panel.add(nextBtn);
		nextBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		nextBtn.add(lblSiguiente);
	}

	public Booking getBooking() {
		if (ReservasView.booking == null) {
			throw new RuntimeException("Empty form, please fill");
		}
		return ReservasView.booking;
	}

	public void setBooking(Booking booking) {
		ReservasView.booking = booking;
	}

	public void saveBookingReference() {
		if (FormValidationUtility.isBookingFormValid(checkInTxt, checkOutTxt, valueTxt.getText())){
			Date dateCheckIn = Date.valueOf(DateConvertor.convertDateToLocalDate(checkInTxt.getDate()));
			Date dateCheckOut = Date.valueOf(DateConvertor.convertDateToLocalDate(checkOutTxt.getDate()));
			String valorReservaString = valueTxt.getText();
			BigDecimal valueBookingToBigDecimal = new BigDecimal(valorReservaString);

			setBooking(new Booking(
					dateCheckIn,
					dateCheckOut,
					valueBookingToBigDecimal,
					Objects.requireNonNull(paymentMethodTxt.getSelectedItem()).toString()
			));
			clearFields();
			this.dispose();
			RegistroHuesped guestRegister = new RegistroHuesped();
			guestRegister.setVisible(true);
		}
	}

	private void clearFields() {
		checkInTxt.setCalendar(null);
		checkOutTxt.setCalendar(null);
		valueTxt.setText("0.0");
		paymentMethodTxt.setSelectedIndex(0);
	}

	private	void getBookingValue() {
		BigDecimal priceNight = BigDecimal.valueOf(15.5);
		BigDecimal totalPrice;
		if (checkInTxt.getDate() == null && checkOutTxt.getDate() == null) {
			valueTxt.setText("");
		} else {

			var checkIn = DateConvertor.convertDateToLocalDate(checkInTxt.getDate());
			var checkOut = DateConvertor.convertDateToLocalDate(checkOutTxt.getDate());
			var today = LocalDate.now();

			var days = ChronoUnit.DAYS.between(checkIn, checkOut);
			var checkInActual = ChronoUnit.DAYS.between(today, checkIn);
			var checkOutActual = ChronoUnit.DAYS.between(today, checkOut);

			if (days > 0 && checkInActual >= 0 && checkOutActual >= 0) {
				BigDecimal bookingValue = priceNight.multiply(BigDecimal.valueOf(days));
				totalPrice = bookingValue.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();

				valueTxt.setText(String.valueOf(totalPrice));
			} else {
				UserShowMessages.showErrorMessage(
						"Dates no correct",
						"Dates selected not match pattern or may be before today");
				checkInTxt.setDate(null);
				checkOutTxt.setDate(null);
			}
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
}
