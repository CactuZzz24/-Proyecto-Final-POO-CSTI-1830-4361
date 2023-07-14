package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Logic.Clinica;
import Logic.Usuario;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 213);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(12, 13, 282, 103);
		contentPane.add(panel);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(24, 16, 56, 16);
		panel.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(124, 13, 130, 22);
		panel.add(txtCedula);
		
		JLabel label_1 = new JLabel("Contrase\u00F1a:");
		label_1.setBounds(24, 62, 75, 16);
		panel.add(label_1);
		
		txtContra = new JTextField();
		txtContra.setColumns(10);
		txtContra.setBounds(124, 59, 130, 22);
		panel.add(txtContra);
		
		JButton btnNewButton_1 = new JButton("Registrar");
		btnNewButton_1.setBounds(189, 129, 93, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Iniciar Sesion");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = Clinica.getInstance().buscarUsiarioByCedula(txtCedula.getText());
				if(user!=null) {
					if(user.isAdmin()) {
						//
					}else if(user.isPaciente()) {
						//
					}else if(!user.isPaciente()){
						//				
					}
				}else {
					//mensaje de error
				}
			}
		});
		btnNewButton_2.setBounds(22, 129, 109, 25);
		contentPane.add(btnNewButton_2);
	}

}
