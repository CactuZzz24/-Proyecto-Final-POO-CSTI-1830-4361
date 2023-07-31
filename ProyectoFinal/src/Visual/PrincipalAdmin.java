package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Logic.Clinica;
import javax.swing.border.EtchedBorder;

public class PrincipalAdmin extends JFrame {
	static JPanel grafUsarios;
	private static DefaultPieDataset data;
	private JPanel contentPane;
	
	private Dimension dim;

	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;


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
				ListarConsultas listar = new ListarConsultas(false);
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
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Realizar Respaldo");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Clinica clinicaActual = Clinica.getInstance();

		        try {
		            Socket socket = new Socket("127.0.0.1", 7000);
		            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		            oos.writeObject(clinicaActual);
		            oos.flush();
		            oos.close();
		            socket.close();

		            System.out.println("Clínica enviada al servidor para respaldo.");
		            JOptionPane.showMessageDialog(null, "Clínica enviada al servidor para respaldo.",
		                    "Respaldo exitoso", JOptionPane.INFORMATION_MESSAGE);
		        } catch (UnknownHostException uhe) {
		            System.out.println("No se puede acceder al servidor.");
		            JOptionPane.showMessageDialog(null, "No se puede acceder al servidor.",
		                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
		        } catch (IOException ioe) {
		            System.out.println("Fallo en la comunicación: " + ioe.getMessage());
		            JOptionPane.showMessageDialog(null, "Fallo en la comunicación: " + ioe.getMessage(),
		                    "Error de comunicación", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		mnNewMenu_3.add(mntmNewMenuItem_2);
		
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
		setContentPane(contentPane);
		contentPane.setLayout(null);
		graficaUsarios();
		graficaTrafico();
	}
	
	public static void actualizarGraficas() {
		actualizarGraficaUsarios();
	}
	
	private static void actualizarGraficaUsarios() {
		data.clear();
	    data.setValue("Pacientes", Clinica.getInstance().calcCantPacientes());
	    data.setValue("Doctores",  Clinica.getInstance().calcCantDoctores());
	    data.setValue("Administradores", Clinica.getInstance().calcCantAdmins());

	    grafUsarios.repaint();
	}

	private void graficaUsarios() {
		data = new DefaultPieDataset();
	    data.setValue("Pacientes", Clinica.getInstance().calcCantPacientes());
	    data.setValue("Doctores",  Clinica.getInstance().calcCantDoctores());
	    data.setValue("Administradores", Clinica.getInstance().calcCantAdmins());

	    JFreeChart chart = ChartFactory.createPieChart(
	        "Usuarios en la Plataforma",
	        data,
	        true,
	        true,
	        false
	    );
	    contentPane.setLayout(null);

	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    chartPanel.setBounds(12, 13, 347, 265);
	    contentPane.add(chartPanel);
	    
	    grafUsarios = new JPanel();
	    chartPanel.add(grafUsarios);
	    grafUsarios.setLayout(null);
    }
	
	private void graficaTrafico() {
		 DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
		 line_chart_dataset.addValue(80, "visitas", "Julio");
		 line_chart_dataset.addValue(300, "visitas", "Agosto");
		 line_chart_dataset.addValue(600, "visitas", "Septiembre");
		 line_chart_dataset.addValue(1200, "visitas", "Octubre");
		 line_chart_dataset.addValue(2400, "visitas", "Noviembre");

		 // Create the Chart
		 JFreeChart chart = ChartFactory.createLineChart(
	            "Trafico",
	            "Mes", "Visitas",
	            line_chart_dataset,
	            PlotOrientation.VERTICAL,
	            true, true, false);
	    
	    JPanel panelTrafico = new JPanel();
	    panelTrafico.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    panelTrafico.setBounds(371, 13, 732, 265);
	    contentPane.add(panelTrafico);
	    panelTrafico.setLayout(new BorderLayout(0, 0));
    
	    ChartPanel grafTrafico = new ChartPanel(chart);
	    panelTrafico.add(grafTrafico, BorderLayout.CENTER);
	    grafTrafico.setPreferredSize(new Dimension(450, 350));
	    grafTrafico.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    }
}
