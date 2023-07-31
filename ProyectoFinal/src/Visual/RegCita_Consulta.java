package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Logic.Cita;
import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Persona;




public class RegCita_Consulta extends JDialog {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textSecretaria;
	private JTextField textCedula;
	private JTextField textNombre;
	private JTextField textTel;
	private JDatePickerImpl datePicker;
	private JTextField textFechaConsulta;
	private JButton btnCancelar;
	private JButton btnNewButton;
	private JButton btnRegistrar;
	private JButton btnAtras;
	private JDatePickerImpl datePickerCita;
	private JTextArea textDir;
	private JTextArea textmotivoConsulta;
	private JButton btnBuscar;
	private JTextField txtFechaNacim;
	private JRadioButton rdbHombre;
	private JRadioButton rdbMujer;
	private JTable table;
    private DefaultTableModel doctoresTableModel;
	private Doctor selectedDoctor;
	private JPanel primera_pagina;
	private JPanel segunda_pagina;
	private Consulta miConsulta = null;




	



	/**
	 * Create the frame.
	 * @param miConsulta 
	 * @param esAdmin 
	 * @param esDoctor 
	 */
	public RegCita_Consulta(boolean esDoctor, boolean esAdmin, Consulta consulta) {
		miConsulta = consulta;
		if(miConsulta == null) {
			setTitle("Agendar Cita");

		}
		
		else {
			setTitle("Editar Cita");
		}
		
		setResizable(false);
		setBounds(100, 100, 677, 503);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(45, 28, 588, 391);
		contentPane.add(layeredPane);
		
		primera_pagina = new JPanel();
		primera_pagina.setBounds(374, 425, 588, 391);
		primera_pagina.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.add(primera_pagina);
		primera_pagina.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(15, 16, 69, 20);
		primera_pagina.setBounds(0, 0, 588, 391);
		primera_pagina.add(lblNewLabel);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(15, 39, 146, 26);
		primera_pagina.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(533, 16, 40, 20);
		primera_pagina.add(lblNewLabel_1);
		datePickerCita = createDatePickerCita();
		datePickerCita.setBounds(427, 39, 146, 26);
		primera_pagina.add(datePickerCita);
		
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
		textNombre.setEditable(false);
		textNombre.setBounds(15, 130, 146, 26);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Direcci\u00F3n");
		lblNewLabel_5.setBounds(15, 172, 69, 20);
		panel_1.add(lblNewLabel_5);
		
		textDir = new JTextArea();
		textDir.setEditable(false);
		textDir.setBounds(15, 208, 528, 70);
		panel_1.add(textDir);
		
		JLabel lblNewLabel_6 = new JLabel("Telefono");
		lblNewLabel_6.setBounds(481, 16, 62, 20);
		panel_1.add(lblNewLabel_6);
		
		  textTel = new JFormattedTextField(createPhoneFormatter());
		  textTel.setEditable(false);
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
		    
		    segunda_pagina = new JPanel();
		    segunda_pagina.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		    segunda_pagina.setBounds(0, 0, 588, 391);
		    layeredPane.add(segunda_pagina);
		    segunda_pagina.setLayout(null);
		    
		    JPanel panel = new JPanel();
		    panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		    panel.setBounds(19, 205, 543, 164);
		    segunda_pagina.add(panel);
		    panel.setLayout(null);
		    
		    JLabel lblNewLabel_8 = new JLabel("Motivo de la Consulta");
		    lblNewLabel_8.setBounds(23, 6, 169, 20);
		    panel.add(lblNewLabel_8);
		    
		    textmotivoConsulta = new JTextArea();
		    textmotivoConsulta.setBounds(23, 42, 506, 108);
		    panel.add(textmotivoConsulta);
		    
		    JLabel lblNewLabel_9 = new JLabel("Doctor a cargo");
		    lblNewLabel_9.setBounds(19, 16, 132, 20);
		    segunda_pagina.add(lblNewLabel_9);
		    
		    JLabel lblNewLabel_10 = new JLabel("Fecha");
		    lblNewLabel_10.setBounds(533, 16, 40, 20);
		    segunda_pagina.add(lblNewLabel_10);
		    
		    textFechaConsulta = new JTextField();
		    textFechaConsulta.setEditable(false);
		    textFechaConsulta.setBounds(427, 46, 146, 26);
		    segunda_pagina.add(textFechaConsulta);
		    textFechaConsulta.setColumns(10);
		    
		    JPanel panel_2 = new JPanel();
		    panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		    panel_2.setBounds(19, 46, 213, 143);
		    segunda_pagina.add(panel_2);
		    panel_2.setLayout(new BorderLayout(0, 0));
		    
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		    panel_2.add(scrollPane, BorderLayout.CENTER);
		    
		    table = new JTable();
		    doctoresTableModel = new DefaultTableModel(); 

		    String[] doctorHeaders = { "Cédula", "Nombre", "Especialidad" };
		    doctoresTableModel.setColumnIdentifiers(doctorHeaders);
		    table.setModel(doctoresTableModel);
	        loadDoctores();

		    table.addMouseListener(new MouseAdapter() {

				@Override
		    	public void mouseClicked(MouseEvent e) {
		    		int index = table.getSelectedRow();
	                if (index >= 0) {
	                	selectedDoctor = Clinica.getInstance().buscarMedicoByCedula(table.
	                			getValueAt(index,0 ).toString());
	                }
		    	}
		    });
		    scrollPane.setViewportView(table);
		    textFechaConsulta.setVisible(false);
		
		btnNewButton = new JButton("Siguiente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                primera_pagina.setVisible(false);
                segunda_pagina.setVisible(true);
                btnNewButton.setVisible(false); 
                table.setVisible(true);
                textFechaConsulta.setVisible(true);
                btnAtras.setVisible(true); 
                btnRegistrar.setVisible(true); 
        		btnCancelar.setBounds(55, 426, 105, 21);

			}
		});
		btnNewButton.setBounds(526, 426, 107, 21);
		contentPane.add(btnNewButton);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(404, 426, 105, 21);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        btnRegistrar = new JButton("Registrar");
        if(miConsulta != null) {
        	btnRegistrar.setText("Actualizar");
        	
        }
        btnRegistrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!validateFields()) {
        			return;
        		}
        		if(miConsulta== null) {
        		     java.util.Date currentDate = new java.util.Date();

        		        java.util.Date birthDate = (java.util.Date) datePicker.getModel().getValue();
        		        if (birthDate == null || birthDate.after(currentDate)) {
        		            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida.",
        		                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        		            return; 
        		        }

        		        java.util.Date selectedDate = (java.util.Date) datePickerCita.getModel().getValue();
        		        if (selectedDate == null || selectedDate.before(currentDate)) {
        		            JOptionPane.showMessageDialog(null, "Seleccione una fecha válida para la cita.",
        		                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        		            return; 
        		        }
        	  		String cedula = textCedula.getText();
            		String nombre = textNombre.getText();
            		java.util.Date fchNacim = (java.util.Date) datePicker.getModel().getValue();

            		String telefono = textTel.getText();
            		String direccion = textDir.getText();
            	
            		
            		Persona persona = new Persona(cedula, nombre, fchNacim, telefono, direccion, charGenero());
            		Clinica.getInstance().insertarPersona(persona);
            		
            		
            		String codigoCitaString = textCodigo.getText();
            		String secretaria = textSecretaria.getText();
            		java.util.Date fechaCita = (java.util.Date) datePickerCita.getModel().getValue();

            		Doctor doctor = selectedDoctor;
       
            		
            		Cita cita = new Cita(secretaria, codigoCitaString, fechaCita, persona, doctor);
            		Consulta miConsulta_2 = new Consulta(fechaCita, persona, doctor, " ", textCodigo.getText(), cita);
            		
            		Clinica.getInstance().insertarConsulta(miConsulta_2);
            		 JOptionPane.showMessageDialog(null, "Registro exitoso", "Registro",
                             JOptionPane.INFORMATION_MESSAGE);
        		}
        		else {
        		     java.util.Date currentDate = new java.util.Date();

        		        java.util.Date birthDate = (java.util.Date) datePicker.getModel().getValue();
        		        if (birthDate == null || birthDate.after(currentDate)) {
        		            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida.",
        		                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        		            return; 
        		        }

        		        java.util.Date selectedDate = (java.util.Date) datePickerCita.getModel().getValue();
        		        if (selectedDate == null) {
        		            JOptionPane.showMessageDialog(null, "Seleccione una fecha válida para la cita.",
        		                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        		            return; 
        		        }
        			miConsulta.setMiDoctor(selectedDoctor);
        			miConsulta.setFecha((java.util.Date) datePickerCita.getModel().getValue());
        			miConsulta.setObservaciones("");
        			miConsulta.setCodigo(textCodigo.getText());
        			miConsulta.getMiPersona().setCedula
        			(textCedula.getText());
        			miConsulta.getMiPersona().setDireccion
        			(textDir.getText());
        			miConsulta.getMiPersona().setFchNacim((java.util.Date) datePicker.getModel().getValue());
        			miConsulta.getMiPersona().setGenero(charGenero());
        			miConsulta.getMiPersona().setNombre(textNombre.getText());
        			miConsulta.getMiPersona().setTelefono(textTel.getText());
        			Clinica.getInstance().modificarConsulta(miConsulta);
        			JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Uptade",
                            JOptionPane.INFORMATION_MESSAGE);
        			dispose();
        			ListarConsultas.loadConsultas();
        			
        			
					
				}
        		
      
        		
        		
        		clear();
        		
        	}


        });
        btnRegistrar.setBounds(526, 426, 107, 21);
        contentPane.add(btnRegistrar);
        btnRegistrar.setVisible(false); 
		btnCancelar.setBounds(404, 426, 107, 21);
		contentPane.add(btnCancelar);
        btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                primera_pagina.setVisible(true);
                segunda_pagina.setVisible(false);
                btnNewButton.setVisible(true); 
                btnAtras.setVisible(false); 
                btnRegistrar.setVisible(false); 
        		btnCancelar.setBounds(404, 426, 105, 21);
                textFechaConsulta.setVisible(false);
                table.setVisible(false);


            }
        });
        btnAtras.setBounds(419, 426, 107, 21);
        contentPane.add(btnAtras);
        btnAtras.setVisible(false); 
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbHombre);
        buttonGroup.add(rdbMujer);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Persona personaEncontrada = Clinica.getInstance().buscarPersonaByCedula(textCedula.getText());
        		
        		if (personaEncontrada != null) {
                    textNombre.setText(personaEncontrada.getNombre());
                    textTel.setText(personaEncontrada.getTelefono());
                    textDir.setText(personaEncontrada.getDireccion());
                    if (personaEncontrada.getGenero() == 'M') {
                        rdbHombre.setSelected(true);
                    } else if (personaEncontrada.getGenero() == 'F') {
                        rdbMujer.setSelected(true);
                    }

                    UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
                    modelNacim.setValue(personaEncontrada.getFchNacim());
                    datePicker.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").format(personaEncontrada.getFchNacim()));


                    
                    datePicker.getJFormattedTextField().setVisible(true);
                } else {
                    textNombre.setEditable(true);
                    textTel.setEditable(true);
                    textDir.setEditable(true);

                    txtFechaNacim.setText("");
                    datePicker.setVisible(true);
                    datePicker.getModel().setValue(null);
                    txtFechaNacim.setVisible(false);
                }
        	}
        });
        btnBuscar.setBounds(73, 14, 88, 25);
        panel_1.add(btnBuscar);
        loadConsultas();


      
		
		
		
	
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
	 private JDatePickerImpl createDatePickerCita() {
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
		            datePickerCita.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDate));
		            textFechaConsulta.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDate));

		        }
		    });

		    return new JDatePickerImpl(datePanel, null);
		}
	 private void clear() {
	        textCodigo.setText("");
	        textSecretaria.setText("");
	        textCedula.setText("");
	        textNombre.setText("");
	        textDir.setText("");
	        textTel.setText("");
	        datePicker.getModel().setValue(null);
	        datePickerCita.getModel().setValue(null);
	        textFechaConsulta.setText("");
	        btnRegistrar.setVisible(false);
	        textmotivoConsulta.setText("");
	        rdbHombre.setSelected(false);
	        rdbMujer.setSelected(false);
	        
	        
	    }
	 
	 private void loadDoctores() {
		    ArrayList<Persona> personas = Clinica.getInstance().getMisPersonas();

		    doctoresTableModel.setRowCount(0);

		    for (Persona persona : personas) {
		    	if(persona instanceof Doctor) {
		    		 Object[] row = { persona.getCedula(), persona.getNombre(), ((Doctor) persona).getEspecialidad() };
				        doctoresTableModel.addRow(row);
		    	}
		       
		    }
		}
	 
	 private void loadConsultas() {
		 if(miConsulta != null) {
			 textCedula.setText(miConsulta.getMiPersona().getCedula());
			 textCodigo.setText(miConsulta.getCodigo());
			 textNombre.setText(miConsulta.getMiPersona().getNombre());
			 textDir.setText(miConsulta.getMiPersona().getDireccion());
			 textTel.setText(miConsulta.getMiPersona().getTelefono());
	         textFechaConsulta.setText(new SimpleDateFormat("yyyy-MM-dd").format(miConsulta.getFecha())); 
	         
	         UtilDateModel modelConsulta = (UtilDateModel) datePickerCita.getModel();
	         modelConsulta.setValue(miConsulta.getFecha());
	         datePickerCita.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd")
	        		 .format(miConsulta.getFecha()));
	         
	         UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
             modelNacim.setValue(miConsulta.getMiPersona().getFchNacim());
             datePicker.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").
            		 format(miConsulta.getMiPersona().getFchNacim()));
             
             textCedula.setEditable(true);
             textCodigo.setEditable(true);
             textNombre.setEditable(true);
             textDir.setEditable(true);
             textTel.setEditable(true);
             datePicker.getJFormattedTextField().setVisible(true);
             datePicker.setVisible(true);
			 
		 }
		 else {
			
		}
	 }
		
		private char charGenero() {
			if(rdbHombre.isSelected())
				return 'M';
			else
				return 'F';
		}
		private boolean validateFields() {
		    if (textCodigo.getText().isEmpty() || textSecretaria.getText().isEmpty() ||
		        textCedula.getText().isEmpty() || textNombre.getText().isEmpty() ||
		        textDir.getText().isEmpty() || textTel.getText().isEmpty() ||
		        datePicker.getModel().getValue() == null || datePickerCita.getModel().getValue() == null ||
		        (!rdbHombre.isSelected() && !rdbMujer.isSelected())) {
		        
		        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Por favor, complete todos los campos.",
		                "Campos vacíos", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    return true;
		}

}
