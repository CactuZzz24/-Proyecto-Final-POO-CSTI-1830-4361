package Visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;




public class RegCita_Consulta extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textFecha;
	private JTextField textSecretaria;
	private JTextField textCedula;
	private JTextField textNombre;
	private JTextField textTel;
	private JDatePickerImpl datePicker;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegCita_Consulta frame = new RegCita_Consulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegCita_Consulta() {
		setTitle("Agendar Cita");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 503);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(45, 28, 588, 391);
		contentPane.add(layeredPane);
		
		JPanel primera_pagina = new JPanel();
		primera_pagina.setBounds(0, 0, 588, 391);
		primera_pagina.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.add(primera_pagina);
		primera_pagina.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(15, 16, 69, 20);
		primera_pagina.add(lblNewLabel);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(15, 39, 146, 26);
		primera_pagina.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(533, 16, 40, 20);
		primera_pagina.add(lblNewLabel_1);
		
		textFecha = new JTextField();
		textFecha.setBounds(427, 39, 146, 26);
		primera_pagina.add(textFecha);
		textFecha.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Secretaria");
		lblNewLabel_2.setBounds(270, 16, 69, 20);
		primera_pagina.add(lblNewLabel_2);
		
		textSecretaria = new JTextField();
		textSecretaria.setBounds(228, 39, 146, 26);
		primera_pagina.add(textSecretaria);
		textSecretaria.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(15, 81, 558, 294);
		primera_pagina.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Cedula");
		lblNewLabel_3.setBounds(15, 16, 69, 20);
		panel_1.add(lblNewLabel_3);
		
		textCedula = new JTextField();
		textCedula.setBounds(15, 52, 146, 26);
		panel_1.add(textCedula);
		textCedula.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre");
		lblNewLabel_4.setBounds(15, 94, 69, 20);
		panel_1.add(lblNewLabel_4);
		
		textNombre = new JTextField();
		textNombre.setBounds(15, 130, 146, 26);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Direcci\u00F3n");
		lblNewLabel_5.setBounds(15, 172, 69, 20);
		panel_1.add(lblNewLabel_5);
		
		JTextArea textDir = new JTextArea();
		textDir.setBounds(15, 208, 528, 70);
		panel_1.add(textDir);
		
		JLabel lblNewLabel_6 = new JLabel("Telefono");
		lblNewLabel_6.setBounds(481, 16, 62, 20);
		panel_1.add(lblNewLabel_6);
		
		  textTel = new JFormattedTextField(createPhoneFormatter());
	        textTel.setBounds(397, 52, 146, 26);
	        panel_1.add(textTel);
	        textTel.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_7.setBounds(397, 94, 146, 20);
		panel_1.add(lblNewLabel_7);
		
		 datePicker = createDatePicker();
		    datePicker.setBounds(397, 130, 146, 26);
		    panel_1.add(datePicker);
		    
		    JPanel segunda_pagina = new JPanel();
		    segunda_pagina.setBounds(0, 0, 10, 10);
		    layeredPane.add(segunda_pagina);
		
		JButton btnNewButton = new JButton("Siguiente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(526, 426, 107, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(404, 426, 107, 21);
		contentPane.add(btnNewButton_1);
		
		
		
	
	}
	private JDatePickerImpl createDatePicker() {
	    UtilDateModel model = new UtilDateModel();
	    Properties properties = new Properties();
	    properties.put("text.today", "Today");
	    properties.put("text.month", "Month");
	    properties.put("text.year", "Year");
	    JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);

	    datePanel.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            java.util.Date selectedDate = (java.util.Date) model.getValue();
	            datePicker.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDate));
	        }
	    });

	    return new JDatePickerImpl(datePanel, null);
	}
	 private MaskFormatter createPhoneFormatter() {
	        MaskFormatter formatter = null;
	        try {
	            formatter = new MaskFormatter("(###) ###-####");
	            formatter.setPlaceholderCharacter('_');
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return formatter;
	    }
	


}
