package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Paciente;
import Logic.ResumenClinico;
import Logic.Usuario;

import java.awt.FlowLayout;

import javax.print.attribute.standard.Media;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class IniciarSesion extends JFrame {

	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtContra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion();
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
	public IniciarSesion() {
		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 263);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(12, 13, 282, 94);
		contentPane.add(panel);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(25, 19, 56, 16);
		panel.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(124, 16, 130, 22);
		panel.add(txtCedula);
		
		JLabel label_1 = new JLabel("Contrase\u00F1a:");
		label_1.setBounds(24, 57, 75, 16);
		panel.add(label_1);
		
		txtContra = new JTextField();
		txtContra.setColumns(10);
		txtContra.setBounds(124, 54, 130, 22);
		panel.add(txtContra);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(12, 122, 282, 81);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Iniciar Sesion");
		btnNewButton_2.setBounds(12, 47, 119, 25);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCedula.getText().equals("") && !txtContra.getText().equals("")) {
					Usuario user = Clinica.getInstance().buscarUsiarioByCedula(txtCedula.getText());
					if(user!=null || user.getClave().equalsIgnoreCase(txtContra.getText())) {
						if(user.isAdmin()) {
							PrincipalAdmin menu = new PrincipalAdmin();
							menu.setVisible(true);
							dispose();
						}else if(user.isPaciente()) {
							Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(user.getCedula());
							/*
							 * Crear menu para aquellos pacientes que se volvieron mayores de edady necesitan ingresar su cedula
							 */
							if(paciente!=null) {
								PrincipalPaciente menu = new PrincipalPaciente(paciente);
								menu.setVisible(true);
								dispose();
							}
	
						}else if(!user.isPaciente()){
							Doctor doc = Clinica.getInstance().buscarMedicoByCedula(user.getCedula());
							if(doc!=null) {
								PrincipalMedico menu = new PrincipalMedico(doc);
								menu.setVisible(true);
								dispose();
							}
						}
					}else {
						if(!user.getClave().equalsIgnoreCase(txtContra.getText()))
							JOptionPane.showMessageDialog(null, "La contraseña ingresada es incorrecta", "Error", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "La cedula ingresada no esta registrada", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Profavor llenar todos los campos", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
					
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(171, 47, 99, 25);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario reg = new RegUsuario(true, false);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		btnNewButton.setBounds(171, 13, 99, 25);
		panel_1.add(btnNewButton);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setForeground(SystemColor.info);
		
		JLabel lblNewLabel_1 = new JLabel("\u00BFNo tienes un Usuario?:");
		lblNewLabel_1.setBounds(12, 17, 147, 16);
		panel_1.add(lblNewLabel_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
