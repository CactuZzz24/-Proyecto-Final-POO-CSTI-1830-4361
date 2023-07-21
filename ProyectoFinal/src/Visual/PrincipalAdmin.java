package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.Persona;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
				RegCita_Consulta reg = new RegCita_Consulta();
				reg.setVisible(true);
			}
		});
		mnNewMenu.add(btnRegistrarConsulta);
		
		JMenuItem btnListarConsulta = new JMenuItem("Listar");
		btnListarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarConsulta listar = new ListarConsulta();
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
				RegUsuario reg = new RegUsuario(false, false);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnNewMenu_1.add(btnRegistrarMedico);
		
		JMenuItem btnListarMedico = new JMenuItem("Listar");
		btnListarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarMedico listar = new ListarMedico();
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
				RegUsuario reg = new RegUsuario(true, false);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnNewMenu_2.add(btnRegistrarPaciente);
		
		JMenuItem btnListarPaciente = new JMenuItem("Listar");
		btnListarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPaciente listar = new ListarPAciente();
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
				RegUsuario reg = new RegUsuario(false, true);
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
				RegVacuna reg = new RegVacuna();
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnNewMenu_4.add(btnRegVacuna);
		
		JMenuItem btnListarVacuna = new JMenuItem("Listar");
		mnNewMenu_4.add(btnListarVacuna);
		
		JMenu mnNewMenu_5 = new JMenu("Enfermedades");
		menuBar.add(mnNewMenu_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
