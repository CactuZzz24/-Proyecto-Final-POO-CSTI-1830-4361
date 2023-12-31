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

import javax.print.event.PrintJobAttributeEvent;
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
import Logic.Paciente;
import Logic.Persona;




public class RegCita extends JDialog {

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
	private Cita miCita = null;
	private static Persona personaEncontrada = null;
	private static Paciente paciente = null; 




	public RegCita(boolean esDoctor, boolean esAdmin, Cita cita, Paciente miPaciente) {
		miCita = cita;
		paciente = miPaciente;
		
	
		
		if(miCita == null) {
			setTitle("Agendar Cita");

		}
		
		else {
			setTitle("Editar Cita");
		}
		
		setResizable(false);
		setBounds(100, 100, 620, 482);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 13, 588, 391);
		contentPane.add(layeredPane);
		
		primera_pagina = new JPanel();
		primera_pagina.setBounds(0, 0, 616, 409);
		primera_pagina.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.add(primera_pagina);
		primera_pagina.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(15, 16, 69, 20);
		primera_pagina.setBounds(0, 0, 588, 391);
		primera_pagina.add(lblNewLabel);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setText("C-" + Clinica.codCita);
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
		lblNewLabel_3.setBounds(30, 12, 69, 20);
		panel_1.add(lblNewLabel_3);
		
		textCedula = new JTextField();
		textCedula.setBounds(30, 48, 146, 26);
		panel_1.add(textCedula);
		textCedula.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre");
		lblNewLabel_4.setBounds(383, 16, 69, 20);
		panel_1.add(lblNewLabel_4);
		
		textNombre = new JTextField();
		textNombre.setBounds(382, 48, 146, 26);
		textNombre.setEditable(false);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Direcci\u00F3n");
		lblNewLabel_5.setBounds(15, 172, 69, 20);
		panel_1.add(lblNewLabel_5);
		
		textDir = new JTextArea();
		textDir.setBounds(15, 208, 528, 70);
		textDir.setEditable(false);
		panel_1.add(textDir);
		
		JLabel lblNewLabel_6 = new JLabel("Telefono");
		lblNewLabel_6.setBounds(206, 16, 62, 20);
		panel_1.add(lblNewLabel_6);
		
		  textTel = new JFormattedTextField(createPhoneFormatter());
		  textTel.setBounds(206, 48, 146, 26);
		  textTel.setEditable(false);
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
		    lblNewLabel_11.setBounds(30, 94, 69, 20);
		    panel_1.add(lblNewLabel_11);
		    
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

		    String[] doctorHeaders = { "C�dula", "Nombre", "Especialidad" };
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
		btnNewButton.setBounds(493, 413, 107, 21);
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
		contentPane.add(btnNewButton);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(371, 413, 107, 21);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(493, 413, 107, 21);
        if(miCita != null) {
        	btnRegistrar.setText("Actualizar");
        	
        	
        }
        btnRegistrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!validateFields()) {
        			return;
        		}
        		if(miCita== null) {
       		     
        			

        			if(personaEncontrada != null) {
           		     java.util.Date currentDate = new java.util.Date();

        			    java.util.Date selectedDate = (java.util.Date) datePickerCita.getModel().getValue();
        		        if (selectedDate == null || selectedDate.before(currentDate)) {
        		            JOptionPane.showMessageDialog(null, "Seleccione una fecha v�lida para la cita.",
        		                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        		            return; 
        		        }
        				Cita cita = new Cita(textSecretaria.getText(), textCodigo.getText(), 
        						(java.util.Date) datePickerCita.getModel().getValue(), personaEncontrada, selectedDoctor, false);
        				Clinica.getInstance().insertarCita(cita);
        						
        				
        			}
        			else {
						
					
        			
        		     java.util.Date currentDate = new java.util.Date();

        		        java.util.Date birthDate = (java.util.Date) datePicker.getModel().getValue();
        		        if (birthDate == null || birthDate.after(currentDate)) {
        		            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es v�lida.",
        		                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        		            return; 
        		        }

        		        java.util.Date selectedDate = (java.util.Date) datePickerCita.getModel().getValue();
        		        if (selectedDate == null || selectedDate.before(currentDate)) {
        		            JOptionPane.showMessageDialog(null, "Seleccione una fecha v�lida para la cita.",
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
       
            		
            		Cita appointment = new Cita(secretaria, codigoCitaString, fechaCita, persona, doctor, false);
            		Clinica.getInstance().insertarCita(appointment);
            		
            	
        			}
        		}
        		else {
        		     java.util.Date currentDate = new java.util.Date();

        		        java.util.Date birthDate = (java.util.Date) datePicker.getModel().getValue();
        		        if (birthDate == null || birthDate.after(currentDate)) {
        		            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es v�lida.",
        		                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        		            return; 
        		        }

        		        java.util.Date selectedDate = (java.util.Date) datePickerCita.getModel().getValue();
        		        if (selectedDate == null) {
        		            JOptionPane.showMessageDialog(null, "Seleccione una fecha v�lida para la cita.",
        		                    "Error de fecha", JOptionPane.ERROR_MESSAGE);
        		            return; 
        		        }
        			miCita.setMiDoctor(selectedDoctor);
        			miCita.setFecha((java.util.Date) datePickerCita.getModel().getValue());
        			miCita.setCodigo(textCodigo.getText());
        			miCita.getMiPersona().setCedula
        			(textCedula.getText());
        			miCita.getMiPersona().setDireccion
        			(textDir.getText());
        			miCita.getMiPersona().setFchNacim((java.util.Date) datePicker.getModel().getValue());
        			miCita.getMiPersona().setGenero(charGenero());
        			miCita.getMiPersona().setNombre(textNombre.getText());
        			miCita.getMiPersona().setTelefono(textTel.getText());
        			Clinica.getInstance().modificarCita(miCita);
        			JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Uptade",
                            JOptionPane.INFORMATION_MESSAGE);
        			dispose();
        			ListarCitas.loadCitas();
        			
        			
					
				}
        		
      
        		
        		JOptionPane.showMessageDialog(null, "Registro exitoso", "Registro",
                        JOptionPane.INFORMATION_MESSAGE);
        		clear();
        		dispose();
        		
        	}


        });
        contentPane.add(btnRegistrar);
        btnRegistrar.setVisible(false);
		contentPane.add(btnCancelar);
        btnAtras = new JButton("Atras");
        btnAtras.setBounds(386, 413, 107, 21);
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
        contentPane.add(btnAtras);
        btnAtras.setVisible(false); 
        ButtonGroup buttonGroup = new ButtonGroup();
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(88, 10, 88, 25);
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		personaEncontrada = Clinica.getInstance().buscarPersonaByCedula(textCedula.getText());
        		
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
        panel_1.add(btnBuscar);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_3.setBackground(new Color(255, 255, 255));
        panel_3.setBounds(30, 113, 220, 46);
        panel_1.add(panel_3);
        panel_3.setLayout(null);
        
        rdbMujer = new JRadioButton("Mujer");
        rdbMujer.setBackground(new Color(255, 255, 255));
        rdbMujer.setBounds(112, 9, 100, 29);
        panel_3.add(rdbMujer);
        buttonGroup.add(rdbMujer);
        
        rdbHombre = new JRadioButton("Hombre");
        rdbHombre.setBackground(new Color(255, 255, 255));
        rdbHombre.setBounds(8, 9, 93, 29);
        panel_3.add(rdbHombre);
        buttonGroup.add(rdbHombre);
        loadCitas();
    	if(!esAdmin && !esDoctor && paciente != null) {
			loadPacienteMenu();
		}


      
		
		
		
	
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
	 
	 private void loadCitas() {
		 if(miCita != null) {
			 textCedula.setText(miCita.getMiPersona().getCedula());
			 textCodigo.setText(miCita.getCodigo());
			 textNombre.setText(miCita.getMiPersona().getNombre());
			 textDir.setText(miCita.getMiPersona().getDireccion());
			 textTel.setText(miCita.getMiPersona().getTelefono());
	         textFechaConsulta.setText(new SimpleDateFormat("yyyy-MM-dd").format(miCita.getFecha())); 
	         
	         UtilDateModel modelConsulta = (UtilDateModel) datePickerCita.getModel();
	         modelConsulta.setValue(miCita.getFecha());
	         datePickerCita.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd")
	        		 .format(miCita.getFecha()));
	         
	         UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
             modelNacim.setValue(miCita.getMiPersona().getFchNacim());
             datePicker.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").
            		 format(miCita.getMiPersona().getFchNacim()));
             
             textCedula.setEditable(true);
             textCodigo.setEditable(true);
             textNombre.setEditable(true);
             textDir.setEditable(true);
             textTel.setEditable(true);
             datePicker.getJFormattedTextField().setVisible(true);
             datePicker.setVisible(true);
             
             if(miCita.getMiPersona().getGenero() == 'M') {
            	 rdbHombre.doClick();
             }
             else {
				rdbMujer.doClick();
			}
			 
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
		                "Campos vac�os", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    return true;
		}
		 private void loadPacienteMenu() {
		        if (paciente != null) {
		            textCedula.setText(paciente.getCedula());
		            textNombre.setText(paciente.getNombre());
		            textDir.setText(paciente.getDireccion());
		            textTel.setText(paciente.getTelefono());

		            UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
		            modelNacim.setValue(paciente.getFchNacim());
		            datePicker.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").format(paciente.getFchNacim()));

		            if (paciente.getGenero() == 'M') {
		                rdbHombre.setSelected(true);
		            } else if (paciente.getGenero() == 'F') {
		                rdbMujer.setSelected(true);
		            }

		            textCedula.setEditable(false);
		            textNombre.setEditable(false);
		            textDir.setEditable(false);
		            textTel.setEditable(false);

		            datePicker.getJFormattedTextField().setVisible(false); 
		            datePicker.setVisible(false); 

		        
		            personaEncontrada = paciente;
		        } else {
		            textCedula.setText("");
		            textNombre.setText("");
		            textDir.setText("");
		            textTel.setText("");

		            datePicker.getJFormattedTextField().setVisible(true);
		            datePicker.setVisible(true);

		            rdbHombre.setSelected(false);
		            rdbMujer.setSelected(false);

		            btnRegistrar.setText("Registrar");
		            btnNewButton.setVisible(true);
		        }
		    }
}

