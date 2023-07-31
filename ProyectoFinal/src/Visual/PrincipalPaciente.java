package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
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
import Logic.Enfermedad;
import Logic.Paciente;
import Logic.Vacuna;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalPaciente extends JFrame {

	private JPanel contentPane = new JPanel();
	private static JTable tableConsultasFuturas;
	private Dimension dimConsultasFuturas;
	private static Object[] rowConsultasFuturas;
	private static DefaultTableModel modeloConsultasFuturas;
	
	private static JTable tableVacunacion;
	private Dimension dimVacunacion;
	private static Object[] rowVacunacion;
	private static DefaultTableModel modeloVacunacion;
	
	private Paciente miPaciente = null;

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
		miPaciente = paciente;
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
		dimConsultasFuturas = super.getToolkit().getScreenSize();
		super.setSize(dimConsultasFuturas.width, dimConsultasFuturas.height-100);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMiInformacion = new JMenu("Mi Informacion");
		menuBar.add(mnMiInformacion);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Hoja de Vacunacion");
		mnMiInformacion.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Mis Enfermedades");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEnfermedad listar = new ListarEnfermedad(true, miPaciente);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnMiInformacion.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Historial de Consultas");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarConsultas listar = new ListarConsultas(false, miPaciente);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnMiInformacion.add(mntmNewMenuItem_3);
		
		JButton btnNewButton = new JButton("Agendar Cita");
		menuBar.add(btnNewButton);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(955, 13, 342, 374);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		if(paciente!=null)
			textPane.setText("Resumen Clinico:\n" + Clinica.getInstance().generarResumenClinico(paciente));
		
		JScrollPane scrollPaneCitasProximas = new JScrollPane();
		scrollPaneCitasProximas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneCitasProximas.setBounds(602, 13, 314, 374);
		contentPane.add(scrollPaneCitasProximas);
		
		tableConsultasFuturas  = new JTable();
		modeloConsultasFuturas = new DefaultTableModel();
		String[] headersConsultasFuturas = {"Fecha", "Doctor", "Codigo"};
		scrollPaneCitasProximas.setViewportView(tableConsultasFuturas );
		modeloConsultasFuturas.setColumnIdentifiers(headersConsultasFuturas);
		tableConsultasFuturas .setModel(modeloConsultasFuturas);
		scrollPaneCitasProximas.setViewportView(tableConsultasFuturas );
		
		JScrollPane scrollPaneVacunacion = new JScrollPane();
		scrollPaneVacunacion.setBounds(602, 400, 314, 493);
		contentPane.add(scrollPaneVacunacion);
		
		tableVacunacion = new JTable();
		modeloVacunacion = new DefaultTableModel();
		String[] headersVacunacion= {"Nombre", "Codigo"};
		scrollPaneVacunacion.setViewportView(tableVacunacion);
		modeloVacunacion.setColumnIdentifiers(headersVacunacion);
		tableVacunacion.setModel(modeloVacunacion);
		scrollPaneVacunacion.setViewportView(tableVacunacion);
		
		loadVacunacion(paciente);
		loadConsultasFuturo(paciente);
		grafEnfermedadesPaciente(paciente);
	}
	
	private void loadVacunacion(Paciente paciente) {
		if (Clinica.getInstance() == null || Clinica.getInstance().getMisVacunas() == null || paciente == null) {
	        return; 
	    }
		 modeloVacunacion.setRowCount(0);
	    rowVacunacion = new Object[tableVacunacion.getColumnCount()];
	    for (Vacuna vacuna : paciente.getResumenClinico().getHojaVacunacion()) {
            rowConsultasFuturas[0] = vacuna.getNombre();
            rowConsultasFuturas[1] = vacuna.getCodigo();
            modeloVacunacion.addRow(rowVacunacion);
	    }	
	}

	public static void loadConsultasFuturo(Paciente paciente) {
	    if (Clinica.getInstance() == null || Clinica.getInstance().getMisConsultas() == null || paciente == null) {
	        return; 
	    }
	    modeloConsultasFuturas.setRowCount(0);
	    rowConsultasFuturas = new Object[tableConsultasFuturas.getColumnCount()];
	    for (Consulta consulta : Clinica.getInstance().getMisConsultas()) {
	    	Date date = new Date();
	        if (consulta.getMiPersona().equals(paciente) && consulta.getFecha().compareTo(date) > 0){
	            rowConsultasFuturas[0] = consulta.getCodigo();
	            rowConsultasFuturas[1] = consulta.getMiPersona().getNombre();
	            rowConsultasFuturas[2] = consulta.getMiDoctor().getNombre();
	            modeloConsultasFuturas.addRow(rowConsultasFuturas);
	        }
	    }
	}
	
	 private void grafEnfermedadesPaciente(Paciente paciente) {
		if (paciente == null || paciente.getResumenClinico() == null || paciente.getResumenClinico().getMisEnfermedades() == null) {
			return;
	    }

	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    int cantidadLeves = 0;
	    int cantidadModeradas = 0;
	    int cantidadGraves = 0;

	    for (Enfermedad enfermedad : paciente.getResumenClinico().getMisEnfermedades()) {
	        if (enfermedad.getGravedad().equalsIgnoreCase("Leve")) {
	            cantidadLeves++;
	        } else if (enfermedad.getGravedad().equalsIgnoreCase("Moderada")) {
	            cantidadModeradas++;
	        } else if (enfermedad.getGravedad().equalsIgnoreCase("Grave")) {
	            cantidadGraves++;
	        }
	    }

	    dataset.addValue(cantidadLeves, "Cantidad", "Leve");
	    dataset.addValue(cantidadModeradas, "Cantidad", "Moderada");
	    dataset.addValue(cantidadGraves, "Cantidad", "Grave");

	    JFreeChart chart = ChartFactory.createBarChart(
	        "Cantidad de Enfermedades por Gravedad",
	        "Gravedad",
	        "Cantidad",
	        dataset,
	        PlotOrientation.VERTICAL,
	        true,
	        true,
	        false
	    );

	    CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.lightGray);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);

		JPanel panelEnfermedades = new JPanel();
        panelEnfermedades.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelEnfermedades.setBounds(33, 37, 439, 549);
        contentPane.add(panelEnfermedades);
	    panelEnfermedades.setLayout(new BorderLayout(0, 0));
		
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new Dimension(400, 400));

	    // Limpiar el panel de enfermedades antes de agregar la nueva gr�fica
	    panelEnfermedades.removeAll();
	    panelEnfermedades.add(chartPanel);
	    panelEnfermedades.revalidate();
	    panelEnfermedades.repaint();
	 }
}
