package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.Clinica;

public class PrincipalAdmin extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalAdmin frame = new PrincipalAdmin();
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
	public PrincipalAdmin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream clinica_2;
				ObjectOutputStream clinicaWrite;
				try {
					clinica_2 = new  FileOutputStream("clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinica_2);
					clinicaWrite.writeObject(Clinica.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height-100);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Consultas");
		menuBar.add(mnNewMenu);
		
		JMenuItem btnRegistrarConsulta = new JMenuItem("Registrar");
		btnRegistrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCita_Consulta reg = new RegCita_Consulta(false, true, null);
				reg.setModal(true);
				reg.setVisible(true);
			
			}
		});
		mnNewMenu.add(btnRegistrarConsulta);
		
		JMenuItem btnListarConsulta = new JMenuItem("Listar");
		btnListarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarConsultas listar = new ListarConsultas();
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu.add(btnListarConsulta);
		
		JMenu mnNewMenu_1 = new JMenu("Medicos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem btnRegistrarMedico = new JMenuItem("Registrar");
		btnRegistrarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario reg = new RegUsuario(false, false, null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnNewMenu_1.add(btnRegistrarMedico);
		
		JMenuItem btnListarMedico = new JMenuItem("Listar");
		btnListarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarDoctor listar = new ListarDoctor();
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_1.add(btnListarMedico);
		
		JMenu mnNewMenu_2 = new JMenu("Pacientes");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem btnRegistrarPaciente = new JMenuItem("Registrar");
		btnRegistrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario reg = new RegUsuario(true, false, null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnNewMenu_2.add(btnRegistrarPaciente);
		
		JMenuItem btnListarPaciente = new JMenuItem("Listar");
		btnListarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPaciente listar = new ListarPaciente();
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_2.add(btnListarPaciente);
		
		JMenu mnNewMenu_3 = new JMenu("Administrador");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem btnRegistrarAdmin = new JMenuItem("Registrar");
		btnRegistrarAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario reg = new RegUsuario(false, true, null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnNewMenu_3.add(btnRegistrarAdmin);
		
		JMenuItem btnListarAdmin = new JMenuItem("Listar");
		btnListarAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarAdministrador listar = new ListarAdministrador();
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_3.add(btnListarAdmin);
		
		JMenu mnNewMenu_4 = new JMenu("Vacunas");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem btnRegVacuna = new JMenuItem("Registrar");
		btnRegVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna reg = new RegVacuna(null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnNewMenu_4.add(btnRegVacuna);
		
		JMenuItem btnListarVacuna = new JMenuItem("Listar");
		btnListarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarVacuna listar = new ListarVacuna();
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_4.add(btnListarVacuna);
		
		JMenu mnNewMenu_5 = new JMenu("Enfermedades");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedad reg = new RegEnfermedad(null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEnfermedad listar = new ListarEnfermedad();
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
