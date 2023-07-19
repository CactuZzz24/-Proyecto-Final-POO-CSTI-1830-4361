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

import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Enfermedad;
import Logic.Paciente;
import Logic.Persona;
import Logic.ResumenClinico;
import Logic.Vacuna;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField$AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatter;


public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JRadioButton btnMasculino;
	private JRadioButton btnFemenino;
	private JTextField txtEspecialidad;
	private static Persona miPersona;
	private JPasswordField pswClave;
	private JPasswordField pswConfirmar;
	private JSpinner spnSangre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegUsuario dialog = new RegUsuario(true, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUsuario(boolean esPaciente, boolean esAdmin) {
		
		if(esPaciente) {
			setTitle("Registrar Usuario Paciente Nuevo");
			setBounds(100, 100, 450, 440);
		}else if(esAdmin) {
			setTitle("Registrar Usuario Admin Nuevo");
			setBounds(100, 100, 450, 383);
		}else {
			setTitle("Registrar Usuario Docor Nuevo");
			setBounds(100, 100, 450, 440);
		}
		
		getContentPane().setLayout(new BorderLayout());
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
		lblNewLabel_2.setBounds(208, 23, 56, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("fch nacimineto:");
		lblNewLabel_3.setBounds(12, 68, 100, 16);
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
		txtDireccion.setBounds(12, 128, 381, 56);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(276, 20, 116, 22);
		panel.add(formattedTextField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(12, 13, 408, 66);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(15, 31, 116, 22);
		panel_1.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cedula:");
		lblNewLabel.setBounds(15, 13, 56, 16);
		panel_1.add(lblNewLabel);
		
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
			panel_2.setBounds(12, 302, 408, 42);
			contentPanel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_6 = new JLabel("Tipo de Sangre:");
			lblNewLabel_6.setBounds(12, 13, 102, 16);
			panel_2.add(lblNewLabel_6);
			
			spnSangre = new JSpinner();
			spnSangre.setModel(new SpinnerListModel(new String[] {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"}));
			spnSangre.setBounds(114, 10, 44, 22);
			panel_2.add(spnSangre);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!pswClave.getText().equals(pswConfirmar.getText())){
							JOptionPane.showMessageDialog(null, "Las claves NO coinciden", "Error", JOptionPane.INFORMATION_MESSAGE);
						}else {
							if(!txtCedula.equals("") && !txtNombre.equals("") && !pswClave.getText().equals("") && !pswConfirmar.getText().equals("") && !txtDireccion.equals("") 
								&& (btnMasculino.isSelected() || btnFemenino.isSelected()) && (!Clinica.getInstance().seRepiteCedula(txtCedula.getText())/*|| calcEdad(fchNacim) < 16*/)) {
								if(esPaciente)
									crearPaciente();
								else if(esAdmin)
									crearPersona();
								else
									crerDoctor();
							}else {
								JOptionPane.showMessageDialog(null, "Porfavor complete todos los campos", "Error", JOptionPane.INFORMATION_MESSAGE);
							}
						}						
					}

					private void crerDoctor() {
						Doctor doctor = new Doctor(txtCedula.getText(), 
								txtNombre.getText(), 
								null /*fchNacim*/, 
								null /*numero Telefono*/, 
								txtDireccion.getText(), 
								charGenero(),
								txtEspecialidad.getText());
						Clinica.getInstance().insertarPersona(doctor);
						JOptionPane.showMessageDialog(null, "Registro de Usuario Doctor Exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}

					private void crearPersona() {
						// TODO Auto-generated method stub
						
					}

					private void crearPaciente() {
						Paciente paciente = new Paciente(txtCedula.getText(), 
								txtNombre.getText(), 
								null /*fchNacim*/, 
								null /*numero Telefono*/, 
								txtDireccion.getText(), 
								charGenero(), 
								new ResumenClinico(new ArrayList<Enfermedad>(), new ArrayList<Vacuna>(), new ArrayList<String>()),
								new ArrayList<Consulta>(),
								spnSangre.getValue().toString(), 
								false);
						Clinica.getInstance().insertarPersona(paciente);
						JOptionPane.showMessageDialog(null, "Registro de Usuario Paciente Exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}

					private char charGenero() {
						if(btnMasculino.isSelected())
							return 'M';
						else
							return 'F';
					}
				});
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
	}
	
	private int calcEdad(Date fchNacim) {
		Date curr = new Date();
		int edad = curr.getYear() - fchNacim.getYear();
		if(curr.getMonth() < fchNacim.getMonth() || (curr.getMonth() == fchNacim.getMonth() && curr.getDay() < fchNacim.getDay()))
			edad--;

        return edad;
	}
	
	private void loadPaciente() {
		//txtNombre.setText();
	}
}
