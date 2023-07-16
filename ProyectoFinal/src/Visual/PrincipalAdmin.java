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
		mnNewMenu.add(btnRegistrarConsulta);
		
		JMenuItem btnListarConsulta = new JMenuItem("Listar");
		mnNewMenu.add(btnListarConsulta);
		
		JMenu mnNewMenu_1 = new JMenu("Medicos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem btnRegistrarMedico = new JMenuItem("Registrar");
		mnNewMenu_1.add(btnRegistrarMedico);
		
		JMenuItem btnListarMedico = new JMenuItem("Listar");
		mnNewMenu_1.add(btnListarMedico);
		
		JMenu mnNewMenu_2 = new JMenu("Pacientes");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem btnRegistrarPaciente = new JMenuItem("Registrar");
		mnNewMenu_2.add(btnRegistrarPaciente);
		
		JMenuItem btnListarPaciente = new JMenuItem("Listar");
		mnNewMenu_2.add(btnListarPaciente);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
