package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtClave;
	private JTextField txtConfirmarClave;
	private JRadioButton btnMasculino;
	private JRadioButton btnFemenino;
	private String clave;
	private String confirmarClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegUsuario dialog = new RegUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUsuario() {
		setTitle("Registrar Usuario Paciente Nuevo");
		setBounds(100, 100, 450, 383);
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
		
		txtClave = new JTextField();
		txtClave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				
				String especiales = "!@#$%^&*()-_=+[]{}|;:,.<>?";
				if(!Character.isLetter(key) && !Character.isDigit(key) && especiales.indexOf(key) == -1) {
					e.consume();
				}else {
					if(clave == null) 
						clave = e.toString();
					else 
						clave+=key;
					
					txtClave.setText(txtClave.getText() + '*');
					e.consume();
				}
			}
		});
		txtClave.setColumns(10);
		txtClave.setBounds(146, 31, 116, 22);
		panel_1.add(txtClave);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(146, 13, 56, 16);
		panel_1.add(lblClave);
		
		JLabel lblConfirmarClave = new JLabel("Confirmar clave:");
		lblConfirmarClave.setBounds(277, 13, 101, 16);
		panel_1.add(lblConfirmarClave);
		
		txtConfirmarClave = new JTextField();
		txtConfirmarClave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				
				String especiales = "!@#$%^&*()-_=+[]{}|;:,.<>?";
				if(!Character.isLetter(key) && !Character.isDigit(key) && especiales.indexOf(key) == -1) {
					e.consume();
				}else {
					if(confirmarClave  == null) 
						confirmarClave = e.toString();
					else 
						confirmarClave += key;
					
					txtConfirmarClave.setText(txtConfirmarClave.getText() + '*');
					e.consume();
				}
			}
		});
		txtConfirmarClave.setColumns(10);
		txtConfirmarClave.setBounds(277, 31, 116, 22);
		panel_1.add(txtConfirmarClave);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!clave.equals(confirmarClave)){
							JOptionPane.showMessageDialog(null, "No // clave 1 = "+ clave + " calve 2 = " + confirmarClave, "Error", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Las claves coinciden", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
						
//						else if(calcEdad(/*fchNacim*/) < 16 && txtCedula.equals("")) {
//							JOptionPane.showMessageDialog(null, "Porfavor ingrese la cedula de un padre o tutor", "Error", JOptionPane.INFORMATION_MESSAGE);
//						}else if(!txtCedula.equals("") && !txtNombre.equals("") && !txtClave.equals("") && !txtConfirmarClave.equals("") && !txtDireccion.equals("") 
//								&& (btnMasculino.isSelected() || btnFemenino.isSelected()) && (!Clinica.getInstance().seRepiteCedula(txtCedula.getText())|| calcEdad(/*fchNacim*/) < 16)) {
//							
//						}else {
//							JOptionPane.showMessageDialog(null, "Porfavor complete todos los campos", "Error", JOptionPane.INFORMATION_MESSAGE);
//						}
						
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
}
