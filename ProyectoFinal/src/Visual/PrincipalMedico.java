package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.Clinica;
import Logic.Doctor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PrincipalMedico extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalMedico frame = new PrincipalMedico(null);
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
	public PrincipalMedico(Doctor medico) {
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
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Historial");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarConsultas listarConsultas = new ListarConsultas(true, null, medico);
				listarConsultas.setModal(true);
				listarConsultas.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCitas listar = new ListarCitas(true, null, medico);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Pacientes");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario regUsuario = new RegUsuario(true, false, null);
				regUsuario.setModal(true);
				regUsuario.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPaciente listar = new ListarPaciente(true, medico);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
