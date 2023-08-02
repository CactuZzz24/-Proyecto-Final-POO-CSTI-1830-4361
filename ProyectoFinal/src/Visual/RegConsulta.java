package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.sound.sampled.Mixer;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.omg.CORBA.TRANSACTION_REQUIRED;

import Logic.Cita;
import Logic.Clinica;
import Logic.Consulta;
import Logic.Enfermedad;
import Logic.Paciente;
import Logic.Persona;
import Logic.ResumenClinico;
import Logic.Usuario;
import Logic.Vacuna;
import javax.swing.JCheckBox;

public class RegConsulta extends JDialog {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textGender;
	private JTextField textEdad;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private DefaultTableModel enfermedadesTableModel;
	private Enfermedad selectedEnfermedad = null;
	private Consulta miConsulta;
	private Cita miCita;
	private JDatePickerImpl datePicker;
	private JList listSangre;
	private String[] tiposDeSangre = new String[] {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
	private JCheckBox checkVigilancia;
	private JTextArea textObservaciones;
	


	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @param actualizando 
	 */
	public RegConsulta(Consulta cons, Cita cita, boolean actualizando) {
		setTitle("Detalles de la Consulta");
		miCita = cita;
		miConsulta = cons;
		setBounds(100, 100, 779, 576);
		setLocationRelativeTo(null);		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(45, 28, 630, 433);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0, 0, 630, 433);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		datePicker = createDatePicker();
	    datePicker.setBounds(447, 52, 146, 26);
	    panel.add(datePicker);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(35, 16, 69, 20);
		panel.add(lblNewLabel);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		if(miCita!= null) {
			
		textCodigo.setText(miCita.getCodigo());
		}
		if(miConsulta != null) {
			textCodigo.setText(miConsulta.getCodigo());
		}
		textCodigo.setBounds(35, 52, 146, 26);
		panel.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(553, 16, 40, 20);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(35, 94, 558, 323);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre Completo");
		lblNewLabel_2.setBounds(15, 16, 136, 20);
		panel_1.add(lblNewLabel_2);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(15, 52, 146, 26);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Genero");
		lblNewLabel_3.setBounds(492, 16, 51, 20);
		panel_1.add(lblNewLabel_3);
		
		textGender = new JTextField();
		textGender.setEditable(false);
		textGender.setText("");
		textGender.setBounds(492, 52, 51, 26);
		panel_1.add(textGender);
		textGender.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Edad");
		lblNewLabel_4.setBounds(211, 16, 35, 20);
		panel_1.add(lblNewLabel_4);
		
		textEdad = new JTextField();
		textEdad.setEditable(false);
		textEdad.setText("");
		textEdad.setBounds(211, 52, 51, 26);
		panel_1.add(textEdad);
		textEdad.setColumns(10);
		
		textObservaciones = new JTextArea();
		textObservaciones.setBounds(15, 188, 267, 119);
		panel_1.add(textObservaciones);
		
		JLabel lblNewLabel_5 = new JLabel("Observaciones");
		lblNewLabel_5.setBounds(15, 152, 124, 20);
		panel_1.add(lblNewLabel_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(327, 188, 216, 119);
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
                if (index >= 0) {
                	selectedEnfermedad = Clinica.getInstance().buscarEnfermedadByCode(table.
                			getValueAt(index,0 ).toString());
                }
			}
		});
		enfermedadesTableModel = new DefaultTableModel();
		String[] enfermedadHeaders = {"Codigo", "Nombre", "Gravedad"};
		enfermedadesTableModel.setColumnIdentifiers(enfermedadHeaders);
		table.setModel(enfermedadesTableModel);
		loadEnfermedades();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_6 = new JLabel("Tipo de Sangre");
		lblNewLabel_6.setBounds(333, 16, 108, 20);
		panel_1.add(lblNewLabel_6);
		
		listSangre = new JList();
        listSangre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSangre.setModel(new AbstractListModel() {
            public int getSize() {
                return tiposDeSangre.length;
            }

            public Object getElementAt(int index) {
                return tiposDeSangre[index];
            }
        });
        listSangre.setSelectedIndex(0);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(327, 54, 124, 42);
        panel_1.add(scrollPane_1);
        scrollPane_1.setViewportView(listSangre);
        
        checkVigilancia = new JCheckBox("Vigilancia");
        checkVigilancia.setBounds(131, 148, 139, 29);
        panel_1.add(checkVigilancia);

		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      if (miConsulta != null) {
			            miConsulta.setFecha((Date) datePicker.getModel().getValue());
			            miConsulta.setCodigo(textCodigo.getText());
			            miConsulta.getMiPersona().setGenero(textGender.getText().charAt(0));
			            miConsulta.setObservaciones(textObservaciones.getText());
			            ((Paciente) miConsulta.getMiPersona()).setvigilancia(vigilancia());
			            ((Paciente) miConsulta.getMiPersona()).setTipoSangre(listSangre.getSelectedValue().toString());


			            miConsulta.setStatus(true);
			            Clinica.getInstance().modificarConsulta(miConsulta);
			            Clinica.getInstance().actualizaRegistroPaciente((Paciente) miConsulta.getMiPersona(), selectedEnfermedad, null, textObservaciones.getText(), miConsulta);
			            dispose();
			            
			            return;
			        }
				if(miCita.getMiPersona() instanceof Paciente) {
					Consulta nuevaConsulta = new Consulta(miCita.getFecha(), miCita.getMiPersona(), miCita.getMiDoctor(), 
							textObservaciones.getText(), miCita.getCodigo(), miCita, true);
					
					Clinica.getInstance().insertarConsulta(nuevaConsulta);
					
					Clinica.getInstance().actualizaRegistroPaciente((Paciente) miCita.getMiPersona(), selectedEnfermedad, null, 
							textObservaciones.getText(), nuevaConsulta);
					
					
					
				    if(Clinica.getInstance().buscarUsuarioByPersona(miCita.getMiPersona()) == null) {
			        	
				        
				        int option = JOptionPane.showConfirmDialog(null, 
				        		"¿Quieres crear un usuario para el Paciente?", "Registro", JOptionPane.OK_CANCEL_OPTION);
				        if(option == JOptionPane.OK_OPTION) {
				        	RegUsuarioParaPaciente reg = new RegUsuarioParaPaciente((Paciente) miCita.getMiPersona());
				        	reg.setModal(true);
				        	reg.setVisible(true);
				        }
				        else {
				        	miCita.setStatus(true);
							Clinica.getInstance().modificarCita(miCita);
							dispose();
						}

						    }
				    miCita.setStatus(true);
					Clinica.getInstance().modificarCita(miCita);
					
					dispose();
					
					
					
					
				}
				else {
					
				
				
				Paciente paciente = new Paciente(miCita.getMiPersona().getCedula(), 
						miCita.getMiPersona().getNombre(), miCita.getMiDoctor().getFchNacim(), miCita.getMiPersona().getTelefono(), 
						miCita.getMiPersona().getDireccion(), miCita.getMiPersona().getGenero(), 
						new ResumenClinico(new ArrayList<Enfermedad>(), new ArrayList<Vacuna>(), new ArrayList<String>()), 
						new ArrayList<Consulta>(), listSangre.getSelectedValue().toString(), vigilancia());
				
				Consulta nuevaConsulta = new Consulta(miCita.getFecha(), paciente, miCita.getMiDoctor(), 
						textObservaciones.getText(), miCita.getCodigo(), miCita, true);
				
				Clinica.getInstance().insertarConsulta(nuevaConsulta);
				
				
				Clinica.getInstance().actualizaRegistroPaciente(paciente, selectedEnfermedad, null, 
						textObservaciones.getText(), nuevaConsulta);
				
		        JOptionPane.showMessageDialog(null, "Registro de Paciente exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
		        
		        if(Clinica.getInstance().buscarUsuarioByPersona(paciente) == null) {
		        	
		        

		        int option = JOptionPane.showConfirmDialog(null, 
		        		"¿Quieres crear un usuario para el Paciente?", "Registro", JOptionPane.OK_CANCEL_OPTION);
		        if(option == JOptionPane.OK_OPTION) {
		        	RegUsuarioParaPaciente reg = new RegUsuarioParaPaciente(paciente);
		        	reg.setModal(true);
		        	reg.setVisible(true);
		        }
		        else {
		        	miCita.setStatus(true);
					Clinica.getInstance().modificarCita(miCita);
					dispose();
				}

				    }
				}
				miCita.setStatus(true);
				Clinica.getInstance().modificarCita(miCita);
		        dispose();
			}

		
			}
	

		);
		btnAceptar.setBounds(582, 483, 107, 21);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(457, 483, 107, 21);
		contentPane.add(btnCancelar);
		loadDatos();

	}

	private void loadDatos() {
	    if (miCita != null) {
	        String nombre = miCita.getMiPersona().getNombre();
	        Date fecha = miCita.getFecha();

	        textNombre.setText(nombre);

	        if (fecha != null) {
	            UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
	            modelNacim.setValue(fecha);
	            datePicker.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").format(fecha));
	            textEdad.setText(edad());
	        } else {
	            UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
	            modelNacim.setValue(new Date()); 
	            datePicker.getJFormattedTextField().setText("");
	            textEdad.setText("");
	        }

	        textCodigo.setText(miCita.getCodigo());
	        textGender.setText(String.valueOf(miCita.getMiPersona().getGenero()));
	    } else if (miConsulta != null) {
	        String nombre = miConsulta.getMiPersona().getNombre();
	        Date fecha = miConsulta.getFecha();

	        textNombre.setText(nombre);

	        if (fecha != null) {
	            UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
	            modelNacim.setValue(fecha);
	            datePicker.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").format(fecha));
	            textEdad.setText(edad());
	        } else {
	            UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
	            modelNacim.setValue(new Date()); 
	            datePicker.getJFormattedTextField().setText("");
	            textEdad.setText("");
	        }

	        textCodigo.setText(miConsulta.getCodigo());
	        textGender.setText(String.valueOf(miConsulta.getMiPersona().getGenero()));
	        textObservaciones.setText(miConsulta.getObservaciones());
	        
	    } else {
	        textNombre.setText("");
	        UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
	        modelNacim.setValue(new Date()); 
	        datePicker.getJFormattedTextField().setText("");
	        textEdad.setText("");
	        textCodigo.setText("");
	        textGender.setText("");
	    }
	}


	private String edad() {
		
	    Date fechaNacimiento = null;
	    
		if(miCita != null) {
			
	     fechaNacimiento = miCita.getMiPersona().getFchNacim();
		}
		else if (miConsulta != null) {
			
		     fechaNacimiento = miConsulta.getMiPersona().getFchNacim();

		}

	    Calendar fechaActual = Calendar.getInstance();

	    Calendar fechaNacimientoCalendar = Calendar.getInstance();
	    fechaNacimientoCalendar.setTime(fechaNacimiento);

	    int edad = fechaActual.get(Calendar.YEAR) - fechaNacimientoCalendar.get(Calendar.YEAR);
	    if (fechaActual.get(Calendar.MONTH) < fechaNacimientoCalendar.get(Calendar.MONTH) ||
	        (fechaActual.get(Calendar.MONTH) == fechaNacimientoCalendar.get(Calendar.MONTH) &&
	         fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNacimientoCalendar.get(Calendar.DAY_OF_MONTH))) {
	        edad--;
	    }

	    return String.valueOf(edad);
	}

	private void loadEnfermedades() {
		ArrayList<Enfermedad> misEnfermedades = Clinica.getInstance().getMisEnfermedades();
		enfermedadesTableModel.setRowCount(0);
		for (Enfermedad enfermedad : misEnfermedades) {
			Object[] row = {enfermedad.getCodigo(), enfermedad.getNombre(), enfermedad.getGravedad()};
			enfermedadesTableModel.addRow(row);
			
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
	private boolean vigilancia() {
		
		if (checkVigilancia.isSelected()) {
			return true;
			
		}
		else {
		
		return false;
		}
	}
	
}