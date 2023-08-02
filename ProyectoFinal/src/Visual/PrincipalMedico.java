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
import Logic.Enfermedad;
import Logic.Paciente;
import Logic.Vacuna;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class PrincipalMedico extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JTable tableFuturasConsultas;
	private static JTable tableConsultasFuturas;
	private static Object[] rowConsultasFuturas;
	private static DefaultTableModel modeloConsultasFuturas;
	private static DefaultCategoryDataset dataConsultas;
	private static DefaultCategoryDataset dataEdadPacientes;
	private static JPanel panelEdadPacientes;
	private static JTextField txtCedulaNota;
	private static JTextPane textPane;
	private static Paciente pacienteNotas;
	private static Paciente pacienteVacuna;
	private static JTextField txtCedulaVacuna;
	private JPanel panelHNota;
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
		menuBar.setBackground(new Color(245, 255, 250));
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
		contentPane.setBackground(new Color(95, 158, 160));
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
		grafEdadPaciente(doctor);
		createResumenPaciente();
		createVacunaPaciente();
	}
	
	public static void actualizar(Doctor doctor) {
		loadConsultasFuturas(doctor);
		actualizarEdadPaciente(doctor);
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
	

	private static void actualizarEdadPaciente(Doctor doctor) {
		if (doctor == null)
			return;
		 	
		dataEdadPacientes.clear();
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 0 , 17), "Cantidad", "-18");
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 18 , 21), "Cantidad", "18-21");
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 22 , 30), "Cantidad", "22-30");
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 31, 50), "Cantidad", "31-50");
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 51, 3000), "Cantidad", "+50");
		panelEdadPacientes.repaint();
	}
		
	private void grafEdadPaciente(Doctor doctor) {
		if (doctor == null) {
			return;
		}
	
		dataEdadPacientes = new DefaultCategoryDataset();
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 0 , 17), "Cantidad", "-18");
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 18 , 21), "Cantidad", "18-21");
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 22 , 30), "Cantidad", "22-30");
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 31, 50), "Cantidad", "31-50");
		dataEdadPacientes.addValue(Clinica.getInstance().calcCantEdadInRangePacientesAtendidosByDoc(doctor, 51, 3000), "Cantidad", "+50");
	  
	
		JFreeChart chart = ChartFactory.createBarChart(
		    "Cantidad de Enfermedades por Gravedad",
		    "Gravedad",
		    "Cantidad",
	        dataEdadPacientes,
	        PlotOrientation.VERTICAL,
	        true,
	        true,
	        false
	    );
		
        NumberAxis yAxis = (NumberAxis)chart.getCategoryPlot().getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(1));
	
	    CategoryPlot plot = chart.getCategoryPlot();
	    Color color = new Color(47, 79, 79);
	    plot.setBackgroundPaint(Color.lightGray);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	    org.jfree.chart.renderer.category.CategoryItemRenderer renderer = plot.getRendererForDataset(plot.getDataset(0));
	    renderer.setSeriesPaint(0, color);
	
	    panelEdadPacientes = new JPanel();
	    panelEdadPacientes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    panelEdadPacientes.setBounds(1211, 424, 679, 470);
	    contentPane.add(panelEdadPacientes);
	    panelEdadPacientes.setLayout(new BorderLayout(0, 0));
	
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new Dimension(400, 400));
	
	    panelEdadPacientes.removeAll();
	    panelEdadPacientes.add(chartPanel);
	    panelEdadPacientes.revalidate();
	    panelEdadPacientes.repaint();
	}
	
	private static void actualizarResumenPaciente() {
		pacienteNotas = Clinica.getInstance().buscarPacienteByCedula(pacienteNotas.getCedula());
		if(pacienteNotas != null)
			textPane.setText(Clinica.getInstance().generarResumenClinico(pacienteNotas));
	}
	
	private void createResumenPaciente() {
		JPanel panelNota = new JPanel();
	    panelNota.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    panelNota.setBounds(480, 13, 719, 396);
	    contentPane.add(panelNota);
	    panelNota.setLayout(null);
	    
	    JScrollPane scrollNota = new JScrollPane();
	    scrollNota.setBounds(12, 66, 695, 317);
	    panelNota.add(scrollNota);
	    
	    textPane = new JTextPane();
	    scrollNota.setViewportView(textPane);
		textPane.setEditable(false);
	    
	    panelHNota = new JPanel();
	    panelHNota.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    panelHNota.setBounds(12, 13, 695, 40);
	    panelNota.add(panelHNota);
	    panelHNota.setLayout(null);
	    
	    JLabel lblCedulaNota = new JLabel("Cedula:");
	    lblCedulaNota.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    lblCedulaNota.setBounds(382, 12, 56, 16);
	    panelHNota.add(lblCedulaNota);
	    
	    txtCedulaNota = new JTextField();
	    txtCedulaNota.setBounds(437, 9, 116, 22);
	    panelHNota.add(txtCedulaNota);
	    txtCedulaNota.setColumns(10);
	    
	    JButton btnBuscarNota = new JButton("Buscar");
	    btnBuscarNota.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pacienteNotas = Clinica.getInstance().buscarPacienteByCedula(txtCedulaNota.getText());
	    		if(pacienteNotas == null)
					JOptionPane.showMessageDialog(null, "No exite un paciente con esta cedula", "Error", JOptionPane.INFORMATION_MESSAGE);
	    		else
	    			actualizarResumenPaciente();
	    	}
	    });
	    btnBuscarNota.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    btnBuscarNota.setForeground(new Color(255, 255, 240));
	    btnBuscarNota.setBackground(new Color(95, 158, 160));
	    btnBuscarNota.setBounds(560, 10, 97, 21);
	    panelHNota.add(btnBuscarNota);
	    
	    JLabel lblNotaPac = new JLabel("Notas Sobre Paciente");
	    lblNotaPac.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
	    lblNotaPac.setBounds(95, 11, 140, 16);
	    panelHNota.add(lblNotaPac);
	}
	
	private static void loadVacunaPaciente() {
		if(pacienteVacuna == null)
			return;
		
		modelo.setRowCount(0);
		row = new Object[table.getColumnCount()];
		for (Vacuna vacuna : Clinica.getInstance().getVacunasPaciente(pacienteVacuna)) {
				row[0] = vacuna.getCodigo();
				row[1] = vacuna.getNombre();
				row[2] = vacuna.getDescripcion();
				modelo.addRow(row);
		}
	}
	
	private void createVacunaPaciente() {
		JPanel panelVacuna = new JPanel();
	    panelVacuna.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    panelVacuna.setBounds(1211, 13, 679, 398);
	    contentPane.add(panelVacuna);
	    panelVacuna.setLayout(null);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(12, 66, 655, 317);
	    panelVacuna.add(scrollPane);
	    
	    table = new JTable();
	    modelo = new DefaultTableModel();
		String[] headers = {"Código", "Nombre", "Descripción"};
	    scrollPane.setViewportView(table);
	    modelo.setColumnIdentifiers(headers);
		table.setModel(modelo);
		scrollPane.setViewportView(table);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    panel_1.setBounds(12, 13, 655, 40);
	    panelVacuna.add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Cedula:");
	    lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    lblNewLabel.setBounds(344, 12, 56, 16);
	    panel_1.add(lblNewLabel);
	    
	    txtCedulaVacuna = new JTextField();
	    txtCedulaVacuna.setBounds(399, 9, 116, 22);
	    panel_1.add(txtCedulaVacuna);
	    txtCedulaVacuna.setColumns(10);
	    
	    JButton btnBuscarVacunas = new JButton("Buscar");
	    btnBuscarVacunas.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pacienteVacuna = Clinica.getInstance().buscarPacienteByCedula(txtCedulaVacuna.getText());
	    		if(pacienteVacuna == null)
					JOptionPane.showMessageDialog(null, "No exite un paciente con esta cedula", "Error", JOptionPane.INFORMATION_MESSAGE);
	    		else
	    			loadVacunaPaciente();
	    	}
	    });
	    btnBuscarVacunas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    btnBuscarVacunas.setForeground(new Color(255, 255, 240));
	    btnBuscarVacunas.setBackground(new Color(95, 158, 160));
	    btnBuscarVacunas.setBounds(522, 9, 97, 21);
	    panel_1.add(btnBuscarVacunas);
	    
	    JLabel lblNewLabel_1 = new JLabel("Vacunas De Paciente:");
	    lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
	    lblNewLabel_1.setBounds(57, 12, 140, 16);
	    panel_1.add(lblNewLabel_1);
	}
}
