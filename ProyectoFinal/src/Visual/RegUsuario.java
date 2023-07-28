package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.print.attribute.standard.Media;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Enfermedad;
import Logic.Paciente;
import Logic.Persona;
import Logic.ResumenClinico;
import Logic.Usuario;
import Logic.Vacuna;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;



public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JRadioButton btnMasculino;
	private JRadioButton btnFemenino;
	private JTextField txtEspecialidad;
	private static Usuario miPersona;
	private JPasswordField pswClave;
	private JPasswordField pswConfirmar;
	private JFormattedTextField fmtTelefono;
	private JList listSangre;
	private JDatePickerImpl datePicker;
	String[] tiposDeSangre = new String[] {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
	private JTextField textFechaNacim;
	private JTextField txtNombreUsuario;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegUsuario dialog = new RegUsuario(true, false, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUsuario(boolean esPaciente, boolean esAdmin, Usuario persona) {	
		String titulo = null;
		miPersona = persona;
		
		if((miPersona.getPersona() instanceof Paciente && (!esPaciente || esAdmin)) || 
				(miPersona.getPersona() instanceof Doctor && (esPaciente || esAdmin)) || (esPaciente && esAdmin)){
			JOptionPane.showMessageDialog(null, "Se a producido un Error posiblemente en el codigo Fuente", "ERROR 404", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
		
		
		if(miPersona == null)
			titulo = "Registrar";
		else
			titulo = "Modificar";
		
		if(esPaciente) {
			setTitle(titulo + " Usuario Paciente Nuevo");
			setBounds(100, 100, 450, 474);
		}else if(esAdmin) {
			setTitle(titulo + " Registrar Usuario Admin Nuevo");
			setBounds(100, 100, 450, 383);
		}else {
			setTitle(titulo + " Registrar Usuario Docor Nuevo");
			setBounds(100, 100, 450, 440);
		}
		
		setBackground(new Color(102, 153, 153));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(102, 153, 153));
		contentPanel.setForeground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(12, 92, 408, 197);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(12, 23, 56, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono:");
		lblNewLabel_2.setBounds(12, 71, 56, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("fch nacimineto:");
		lblNewLabel_3.setBounds(192, 107, 100, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Genero:");
		lblNewLabel_4.setBounds(265, 68, 56, 16);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Direccion:");
		lblNewLabel_5.setBounds(12, 111, 90, 16);
		panel.add(lblNewLabel_5);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(69, 20, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnMasculino = new JRadioButton("M");
		btnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMasculino.isSelected())
					btnFemenino.setSelected(false);
				else
					btnFemenino.setSelected(true);
			}
		});
		btnMasculino.setBounds(319, 63, 41, 25);
		panel.add(btnMasculino);
		
		btnFemenino = new JRadioButton("F");
		btnFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnFemenino.isSelected())
					btnMasculino.setSelected(false);
				else
					btnMasculino.setSelected(true);
			}
		});
		btnFemenino.setBounds(359, 64, 41, 25);
		panel.add(btnFemenino);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(12, 128, 173, 56);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		fmtTelefono = new JFormattedTextField(createPhoneFormatter());
		fmtTelefono.setBounds(80, 68, 116, 22);
		panel.add(fmtTelefono);
		
		datePicker = createDatePicker();
	    datePicker.setBounds(284, 102, 116, 25);
		
		panel.add(datePicker);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(284, 20, 116, 22);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cedula:");
		lblNewLabel.setBounds(226, 23, 56, 16);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(12, 13, 408, 66);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(146, 13, 56, 16);
		panel_1.add(lblClave);
		
		JLabel lblConfirmarClave = new JLabel("Confirmar clave:");
		lblConfirmarClave.setBounds(277, 13, 101, 16);
		panel_1.add(lblConfirmarClave);
		
		pswClave = new JPasswordField();
		pswClave.setBounds(146, 31, 116, 22);
		panel_1.add(pswClave);
		
		pswConfirmar = new JPasswordField();
		pswConfirmar.setBounds(274, 31, 116, 22);
		panel_1.add(pswConfirmar);		
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(12, 13, 122, 16);
		panel_1.add(lblNombreDeUsuario);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(12, 31, 116, 22);
		panel_1.add(txtNombreUsuario);
		
		
		if(!esPaciente && !esAdmin) {
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_3.setBounds(12, 302, 408, 47);
			contentPanel.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_7 = new JLabel("Especialidad:");
			lblNewLabel_7.setBounds(12, 15, 83, 16);
			panel_3.add(lblNewLabel_7);
			
			txtEspecialidad = new JTextField();
			txtEspecialidad.setBounds(95, 12, 301, 22);
			panel_3.add(txtEspecialidad);
			txtEspecialidad.setColumns(10);
		}
		
		
		if(esPaciente) {
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_2.setBounds(12, 302, 408, 66);
			contentPanel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_6 = new JLabel("Tipo de Sangre:");
			lblNewLabel_6.setBounds(12, 25, 102, 16);
			panel_2.add(lblNewLabel_6);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(126, 12, 73, 42);
			panel_2.add(scrollPane_1);
			
			listSangre = new JList();
			listSangre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_1.setViewportView(listSangre);
			listSangre.setModel(new AbstractListModel() {
				public int getSize() {
					return tiposDeSangre.length;
				}
				public Object getElementAt(int index) {
					return tiposDeSangre[index];
				}
			});
			listSangre.setSelectedIndex(0);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(204, 204, 204));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(miPersona==null && condicionesDeRegistro()) {
							if(esPaciente) {
								crearPaciente();
								JOptionPane.showMessageDialog(null, "Registro de Usuario Paciente Exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
							}
							else if(esAdmin) {
								crearPersona();
								JOptionPane.showMessageDialog(null, "Registro de Usuario Administrador Exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								crearDoctor();
								JOptionPane.showMessageDialog(null, "Registro de Usuario Doctor Exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
							}
							
							Usuario usuario = new Usuario(Clinica.getInstance().buscarPersonaByCedula(txtCedula.getText()), 
									txtCedula.getText(), 
									pswClave.getText(), 
									esPaciente, 
									esAdmin);
							Clinica.getInstance().agregarUsuario(usuario);	
							dispose();
						}else {
							miPersona.setNombre(pswClave.getText());
							miPersona.setNombre(txtNombreUsuario.getText());
							
							if(esPaciente) {
								actualizarPersona();
								
							}else if(esAdmin) {
								actualizarPersona();
							}else {
								actualizarPersona();
							}
						}
				}
				
					
				private void actualizarPersona(){
					miPersona.getPersona().setCedula(txtCedula.getText());
					miPersona.getPersona().setDireccion(txtDireccion.getText());
					miPersona.getPersona().setFchNacim((java.util.Date) datePicker.getModel().getValue());
					miPersona.getPersona().setGenero(charGenero());
					miPersona.getPersona().setNombre(txtNombre.getText());
					miPersona.getPersona().setTelefono(fmtTelefono.getText());
				}
				
				private boolean condicionesDeRegistro() {
						if(!pswClave.getText().equals(pswConfirmar.getText())){
							JOptionPane.showMessageDialog(null, "Las claves NO coinciden", "Error", JOptionPane.INFORMATION_MESSAGE);
							return false;
						}else if(!txtNombreUsuario.getText().equals("") && !txtCedula.equals("") && !txtNombre.equals("") && !pswClave.getText().equals("") && !pswConfirmar.getText().equals("") && !txtDireccion.equals("") 
								&& (btnMasculino.isSelected() || btnFemenino.isSelected()) && !Clinica.getInstance().existeCedulaRol(txtCedula.getText(), esPaciente, esAdmin) && !Clinica.getInstance().existeNombreUsuario(txtNombreUsuario.getText())) {
								return true;
						}
						return false;
					}
				
				private char charGenero() {
					if(btnMasculino.isSelected())
						return 'M';
					else
						return 'F';
				}
				
				private void crearDoctor() {
						Doctor doctor = new Doctor(txtCedula.getText(), 
								txtNombre.getText(), 
								(java.util.Date) datePicker.getModel().getValue(),  
								fmtTelefono.getText(), 
								txtDireccion.getText(), 
								charGenero(),
								txtEspecialidad.getText());
						Clinica.getInstance().insertarPersona(doctor);
					}

				private void crearPersona() {
					Persona persona = new Persona(
							txtCedula.getText(), 
							txtNombre.getText(), 
							(java.util.Date) datePicker.getModel().getValue(), 
							fmtTelefono.getText(), 
							txtDireccion.getText(),  	
							charGenero());
					Clinica.getInstance().insertarPersona(persona);
				}

				private void crearPaciente() {
					Paciente paciente = new Paciente(
							txtCedula.getText(), 
							txtNombre.getText(), 
							(java.util.Date) datePicker.getModel().getValue(), 
							fmtTelefono.getText(), 
							txtDireccion.getText(), 
							charGenero(), 
							new ResumenClinico(new ArrayList<Enfermedad>(), new ArrayList<Vacuna>(), new ArrayList<String>()),
							new ArrayList<Consulta>(),
							listSangre.getSelectedValue().toString(), 
							false);
					Clinica.getInstance().insertarPersona(paciente);
				}});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnCacncelar = new JButton("Cancelar");
				btnCacncelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});	
				btnCacncelar.setActionCommand("Cancel");
				buttonPane.add(btnCacncelar);
			}
		}
			
		if(miPersona != null) {			
			txtCedula.setEnabled(false);
			datePicker.setEnabled(false);
			loadPersona();
			if(miPersona.getPersona() instanceof Paciente)
				loadPaciente();
			else if(miPersona.getPersona() instanceof Doctor)
				loadDoctor();
		}
	}

	
	private void loadPersona() {
		txtCedula.setText(miPersona.getPersona().getCedula());
		txtNombre.setText(miPersona.getNombre());
		//datePicker
		fmtTelefono.setText(miPersona.getPersona().getTelefono());
		txtDireccion.setText(miPersona.getPersona().getDireccion());
		if(miPersona.getPersona().getGenero() == 'M') {
			btnMasculino.setSelected(true);
			btnFemenino.setSelected(false);
		}else {
			btnFemenino.setSelected(true);
			btnMasculino.setSelected(false);
		}
	}
	
	private void loadPaciente() {
		for(int i = 0; tiposDeSangre[i]!=null; i++) {
			if(((Paciente)miPersona.getPersona()).getTipoSangre().equals(tiposDeSangre[i]))
				listSangre.setSelectedIndex(i);
			return;
		}
	}
	
	private void loadDoctor() {
		txtEspecialidad.setText(((Doctor)miPersona.getPersona()).getEspecialidad());
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
}