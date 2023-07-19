package Visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.metal.MetalDesktopIconUI;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.omg.CORBA.PRIVATE_MEMBER;

import Logic.Cita;
import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Persona;




public class RegDoctor extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textCedula;
	private JTextField textNombre;
	private JTextField textTel;
	private JDatePickerImpl datePicker;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JTextArea textDir;
	private JTextField txtFechaNacim;
	private JTextField textEspecialidad;
	private Doctor miDoctor = null;
	private JRadioButton rdbHombre;
	private JRadioButton rdbMujer;
	private char genero = ' ';

	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public RegDoctor(Doctor miDoctor) {
		this.miDoctor = miDoctor;
		loadDoctor();
		setTitle("Registrar Doctor");
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
		
		textDir = new JTextArea();
		textDir.setEnabled(true);
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
		    
		    txtFechaNacim = new JTextField();
		    txtFechaNacim.setBounds(397, 130, 146, 26);
		    txtFechaNacim.setEditable(false);
		    txtFechaNacim.setVisible(false);
		    panel_1.add(txtFechaNacim);
		    txtFechaNacim.setColumns(10);
		    
		    JLabel lblNewLabel_11 = new JLabel("G\u00E9nero");
		    lblNewLabel_11.setBounds(252, 16, 69, 20);
		    panel_1.add(lblNewLabel_11);
		    
		    rdbHombre = new JRadioButton("Hombre");
		    rdbHombre.setBounds(212, 51, 155, 29);
		    panel_1.add(rdbHombre);
		    
		    rdbMujer = new JRadioButton("Mujer");
		    rdbMujer.setBounds(212, 129, 155, 29);
		    panel_1.add(rdbMujer);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(404, 426, 105, 21);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(miDoctor == null ) {

            		String cedula = textCedula.getText();
            		String nombre = textNombre.getText();
            		java.util.Date fchNacim = (java.util.Date) datePicker.getModel().getValue();
            		String especialidad = textEspecialidad.getText();

            		String telefono = textTel.getText();
            		String direccion = textDir.getText();
            		determinarGenero();
            		
            		Doctor doctor = new Doctor(cedula, nombre, fchNacim, telefono, direccion, genero, especialidad);
            		Clinica.getInstance().insertarPersona(doctor);
            		
            		
            		 JOptionPane.showMessageDialog(null, "Registro exitoso", "Registro",
                             JOptionPane.INFORMATION_MESSAGE);
            		
            		clear();
        		}
        		
        		else if(miDoctor != null && miDoctor instanceof Doctor){
					miDoctor.setCedula(textCedula.getText());
					miDoctor.setDireccion(textDir.getText());
					determinarGenero();
					miDoctor.setGenero(genero);
					((Doctor) miDoctor).setEspecialidad(textEspecialidad.getText());
					miDoctor.setFchNacim((java.util.Date) datePicker.getModel().getValue());
					miDoctor.setNombre(textNombre.getText());
					miDoctor.setTelefono(textTel.getText());
					Clinica.getInstance().uptadePersona(miDoctor);
					dispose();
					ListarDoctor.loadDoctores();
				}
        		
        		
        	}


        });
        btnRegistrar.setBounds(526, 426, 107, 21);
        contentPane.add(btnRegistrar);
		btnCancelar.setBounds(404, 426, 107, 21);
		contentPane.add(btnCancelar);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbHombre);
        buttonGroup.add(rdbMujer);
        
        JLabel lblNewLabel_1 = new JLabel("Especialidad");
        lblNewLabel_1.setBounds(480, 16, 93, 20);
        primera_pagina.add(lblNewLabel_1);
        
        textEspecialidad = new JTextField();
        textEspecialidad.setBounds(427, 39, 146, 26);
        primera_pagina.add(textEspecialidad);
        textEspecialidad.setColumns(10);
        loadDoctor();


     
		
	
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

	 private void clear() {
	        textCodigo.setText("");
	        textCedula.setText("");
	        textNombre.setText("");
	        textDir.setText("");
	        textTel.setText("");
	        datePicker.getModel().setValue(null);
	        textEspecialidad.setText("");

	        
	    }
	 private void determinarGenero (){
		 if(rdbHombre.isSelected()) {
 			genero = 'H';
 			
 		}
 		else if (rdbMujer.isSelected()) {
 			genero = 'M';
				
			}
	 }
	 
	 private void loadDoctor() {
		   
	 }
}
