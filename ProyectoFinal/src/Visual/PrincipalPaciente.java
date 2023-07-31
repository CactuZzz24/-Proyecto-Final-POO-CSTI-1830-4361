package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Paciente;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;

public class PrincipalPaciente extends JFrame {

	private JPanel contentPane = new JPanel();
	private Dimension dim;
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel modelo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalPaciente frame = new PrincipalPaciente(null);
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
	public PrincipalPaciente(Paciente paciente) {		
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
		
		JMenu mnMiInformacion = new JMenu("Mi Informacion");
		menuBar.add(mnMiInformacion);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Hoja de Vacunacion");
		mnMiInformacion.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Mis Enfermedades");
		mnMiInformacion.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Historial de Consultas");
		mnMiInformacion.add(mntmNewMenuItem_3);
		
		JButton btnNewButton = new JButton("Agendar Cita");
		menuBar.add(btnNewButton);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(720, 356, 279, 343);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setText("Resumen Clinico:\n" + Clinica.getInstance().generarResumenClinico(paciente));
		
		JScrollPane scrollPaneCitasProximas = new JScrollPane();
		scrollPaneCitasProximas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneCitasProximas.setBounds(12, 13, 314, 374);
		contentPane.add(scrollPaneCitasProximas);
		
		JTable table = new JTable();
		modelo = new DefaultTableModel();
		String[] headers = {"Fecha", "Doctor", "Codigo"};
		scrollPaneCitasProximas.setViewportView(table);
		modelo.setColumnIdentifiers(headers);
		table.setModel(modelo);
		scrollPaneCitasProximas.setViewportView(table);
		loadConsultasFuturo(paciente);
	}
	
	public static void loadConsultasFuturo(Paciente paciente) {
	    if (Clinica.getInstance() == null || Clinica.getInstance().getMisConsultas() == null) {
	        return; 
	    }

	    modelo.setRowCount(0);
	    row = new Object[table.getColumnCount()];

	    for (Consulta consulta : Clinica.getInstance().getMisConsultas()) {
	    	Date date = new Date();
	        if (consulta.getMiPersona().equals(paciente) && consulta.getFecha().compareTo(date) > 0){
	            row[0] = consulta.getCodigo();
	            row[1] = consulta.getMiPersona().getNombre();
	            row[2] = consulta.getMiDoctor().getNombre();
	            modelo.addRow(row);
	        }
	    }
	}
}
