package Visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystemNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Logic.Clinica;
import Logic.Doctor;
import Logic.Paciente;
import Logic.Usuario;

public class IniciarSesion extends JFrame {
	private Image imgLogo = new ImageIcon(IniciarSesion.class.getResource("/Icons/iconoSalud.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
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
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
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
		setResizable(false);
		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 335);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(12, 86, 282, 94);
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
		panel_1.setBounds(12, 194, 282, 81);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Iniciar Sesion");
		btnNewButton_2.setBounds(12, 47, 119, 25);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = Clinica.getInstance().buscarUsiarioByNombreAndClave(txtUsuario.getText(), pswClave.getText());
				if(user == null) {
					JOptionPane.showMessageDialog(null, "El Usuario ingresado no esta registrado", "Error", JOptionPane.INFORMATION_MESSAGE);
				}else {
					if(!user.getClave().equals(pswClave.getText())) {
						JOptionPane.showMessageDialog(null, "La contraseña ingresada es incorrecta", "Error", JOptionPane.INFORMATION_MESSAGE);
					}else if(!user.getNombre().equals(txtUsuario.getText())){
						JOptionPane.showMessageDialog(null, "El Usuario ingresado no esta registrado", "Error", JOptionPane.INFORMATION_MESSAGE);
					}else if(user.isAdmin()) {
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 240));
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(12, 13, 282, 60);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(22, 6, 54, 47);
		panel_2.add(lblLogo);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(imgLogo));
		
		JLabel lblNewLabel = new JLabel("Clinica POO");
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(124, 22, 106, 16);
		panel_2.add(lblNewLabel);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
