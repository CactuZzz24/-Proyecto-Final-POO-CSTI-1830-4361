package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Paciente;

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
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PrincipalMedico extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JTable tableFuturasConsultas;
	private static JTable tableConsultasFuturas;
	private static Object[] rowConsultasFuturas;
	private static DefaultTableModel modeloConsultasFuturas;
	private static DefaultCategoryDataset dataConsultas;


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
	public PrincipalMedico(Doctor doctor) {
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
				ListarConsultas listarConsultas = new ListarConsultas(true, null, doctor);
				listarConsultas.setModal(true);
				listarConsultas.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCitas listar = new ListarCitas(true, null, doctor);
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
				AplicarVacuna listar = new AplicarVacuna(doctor);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelFuturasConsultas = new JPanel();
		panelFuturasConsultas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelFuturasConsultas.setBounds(12, 13, 456, 881);
		contentPane.add(panelFuturasConsultas);
		panelFuturasConsultas.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelFuturasConsultas.add(scrollPane, BorderLayout.CENTER);

		tableConsultasFuturas  = new JTable();
		modeloConsultasFuturas = new DefaultTableModel();
		String[] headersConsultasFuturas = {"Fecha", "Doctor", "Codigo"};
		scrollPane.setViewportView(tableConsultasFuturas );
		modeloConsultasFuturas.setColumnIdentifiers(headersConsultasFuturas);
		tableConsultasFuturas .setModel(modeloConsultasFuturas);
		scrollPane.setViewportView(tableConsultasFuturas );
		createGrafConsultas(doctor);
	}
	
	public static void actualizar(Doctor doctor) {
		loadConsultasFuturas(doctor);
	}
	
	public static void loadConsultasFuturas(Doctor doctor) {
		if(Clinica.getClinica()==null || Clinica.getInstance().getMisConsultas() == null || doctor == null)
			return;
		modeloConsultasFuturas.setRowCount(0);
		for (Consulta consulta : Clinica.getInstance().getConsultasOrdenadasPorFechaDoc(doctor)) {
	    	Date date = new Date();
	        if (consulta.getMiPersona().equals(doctor) && consulta.getFecha().compareTo(date) > 0){
	            rowConsultasFuturas[2] = consulta.getCodigo();
	            rowConsultasFuturas[1] = consulta.getMiPersona().getNombre();
	            rowConsultasFuturas[0] = consulta.getFecha();
	            modeloConsultasFuturas.addRow(rowConsultasFuturas);
	        }
	    }
	}

	private static void actualizarConsultas(Doctor doctor) {
		dataConsultas.clear();
		ArrayList<String> fchConsultas = Clinica.getInstance().getFechasConsultasFormateadas(doctor); 
		ArrayList<Integer> cantConsultasPorDia = Clinica.getInstance().getConsultasPorDiaDoctor(doctor);
		for(int i = 0; i < fchConsultas.size(); i++) {
            dataConsultas.addValue(cantConsultasPorDia.get(i), "Citas", fchConsultas.get(i));
		}
	}
	
	public void createGrafConsultas(Doctor doctor) {
		dataConsultas = new DefaultCategoryDataset();

        actualizarConsultas(doctor);

        JFreeChart chart = ChartFactory.createLineChart(
                "Consultas",
                "Fecha", "Cantidad",
                dataConsultas,
                PlotOrientation.VERTICAL,
                true, true, false);

        JPanel panelConsultas = new JPanel();
        panelConsultas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelConsultas.setBounds(480, 424, 719, 470);
        contentPane.add(panelConsultas);
        panelConsultas.setLayout(new BorderLayout(0, 0));

        ChartPanel grafConsultas = new ChartPanel(chart);
        panelConsultas.add(grafConsultas, BorderLayout.CENTER);
        grafConsultas.setPreferredSize(new Dimension(450, 350));
        grafConsultas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    
	}
}
