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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle.Control;

import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class IniciarSesion extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pswClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream clinica;
				FileOutputStream clinica_2;
				ObjectInputStream clinicaRead;
				ObjectOutputStream clinicaWrite;
				try {
					clinica = new FileInputStream("clinica.dat");
					clinicaRead = new ObjectInputStream(clinica);
					Clinica temp = (Clinica)clinicaRead.readObject();
					Clinica.setClinica(temp);
					clinica.close();
					clinicaRead.close();
				} catch (FileNotFoundException e) {
					try {
						clinica_2 = new FileOutputStream("clinica.dat");
						clinicaWrite = new ObjectOutputStream(clinica_2);
						clinicaWrite.writeObject(Clinica.getInstance());
						clinica_2.close();
						clinicaWrite.close();
					} catch (FileSystemNotFoundException e1) {
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		
		JLabel lblCedula = new JLabel("Usuario:");
		lblCedula.setBounds(25, 19, 56, 16);
		panel.add(lblCedula);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(124, 16, 130, 22);
		panel.add(txtUsuario);
		
		JLabel label_1 = new JLabel("Contrase\u00F1a:");
		label_1.setBounds(24, 57, 75, 16);
		panel.add(label_1);
		
		pswClave = new JPasswordField();
		pswClave.setBounds(124, 54, 130, 22);
		panel.add(pswClave);
		
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
				Usuario user = Clinica.getInstance().buscarUsiarioByNombreAndClave(txtUsuario.getText(), pswClave.getText());
				if(user == null) {
					if(!user.getClave().equals(pswClave.getText()))
						JOptionPane.showMessageDialog(null, "La contraseña ingresada es incorrecta", "Error", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "El Usario ingresadd no esta registrado", "Error", JOptionPane.INFORMATION_MESSAGE);
				}else {
					if(user.isAdmin()) {
						PrincipalAdmin menu = new PrincipalAdmin();
						menu.setVisible(true);
						dispose();
					}else if(user.isPaciente()) {
							PrincipalPaciente menu = new PrincipalPaciente((Paciente)user.getPersona());
							menu.setVisible(true);
							dispose();
					} else {
							PrincipalMedico menu = new PrincipalMedico((Doctor)user.getPersona());
							menu.setVisible(true);
							dispose();
					}
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(171, 47, 99, 25);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario reg = new RegUsuario(false, true, null);
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
