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
import java.util.ArrayList;
import java.util.Date;

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
<<<<<<< HEAD
import Logic.Enfermedad;
=======
import Logic.UptadeGraficas;
>>>>>>> branch 'main' of https://github.com/CactuZzz24/-Proyecto-Final-POO-CSTI-1830-4361.git

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PrincipalAdmin extends JFrame {
	private static JPanel grafUsarios;
	private static JPanel grafGeneroPacientes;
	private static JPanel grafGeneroPersonal;
	private static DefaultPieDataset data;
	private static DefaultPieDataset dataGeneroPacientes;
	private static DefaultPieDataset dataGeneroPersonal;
	private static DefaultCategoryDataset dataEdadpacientes;
	private static DefaultCategoryDataset dataEdadDoctores;
	private static DefaultCategoryDataset dataConsultas;
	private static DefaultCategoryDataset dataEnfermedades;
	private static JPanel contentPane;
	
	private Dimension dim;

	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	
    private UptadeGraficas updateThread;



	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PrincipalAdmin frame = new PrincipalAdmin();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		updateThread = new UptadeGraficas();
        updateThread.start();
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
				ListarConsultas listar = new ListarConsultas(false, null, null);
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
				ListarPaciente listar = new ListarPaciente(false, null);
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
				ListarEnfermedad listar = new ListarEnfermedad(false, null);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		crearGraficas();
	}
	
	private void crearGraficas() {
		createGraficaUsarios();
		createGraficaGeneroPacientes();
		createGraficaGeneroPersonal() ;
		createGraficaEdadPacientes();
		createGraficaEdadDoctores();
		createGraficaConsultas();
	}

	public static void actualizarGraficas() {
        actualizarGraficaUsarios();
        actualizarGeneroPacientes();
        actualizarGeneroPersonal();
        actualizarEdadPacientes();
        actualizarEdadDoctores();
        actualizarConsultas();
    }

    private static void actualizarGraficaUsarios() {
    	data.clear();
        data.setValue("Pacientes", Clinica.getInstance().calcCantPacientes());
        data.setValue("Doctores", Clinica.getInstance().calcCantDoctores());
        data.setValue("Administradores", Clinica.getInstance().calcCantAdmins());

        grafUsarios.repaint();
    }

    private void createGraficaUsarios() {
        data = new DefaultPieDataset();
        data.setValue("Pacientes", Clinica.getInstance().calcCantPacientes());
        data.setValue("Doctores", Clinica.getInstance().calcCantDoctores());
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

    private static void actualizarGeneroPacientes() {
        dataGeneroPacientes.clear();
        dataGeneroPacientes.setValue("Hombres", Clinica.getInstance().calcCantGeneroByCond(true, false, true));
        dataGeneroPacientes.setValue("Mujeres", Clinica.getInstance().calcCantGeneroByCond(true, false, false));

        grafGeneroPacientes.repaint();
    }

    private void createGraficaGeneroPacientes() {
        dataGeneroPacientes = new DefaultPieDataset();
        dataGeneroPacientes.setValue("Hombres", Clinica.getInstance().calcCantGeneroByCond(true, false, true));
        dataGeneroPacientes.setValue("Mujeres", Clinica.getInstance().calcCantGeneroByCond(true, false, false));

        JFreeChart chart = ChartFactory.createPieChart(
                "Genero de Pacientes",
                dataGeneroPacientes,
                true,
                true,
                false
        );
        contentPane.setLayout(null);

        ChartPanel chartPanelGeneroPacientes = new ChartPanel(chart);
        chartPanelGeneroPacientes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartPanelGeneroPacientes.setBounds(12, 291, 347, 265);
        contentPane.add(chartPanelGeneroPacientes);

        grafGeneroPacientes = new JPanel();
        chartPanelGeneroPacientes.add(grafGeneroPacientes);
        grafGeneroPacientes.setLayout(null);
    }
        
    private static void actualizarGeneroPersonal() {
        dataGeneroPersonal.clear();
        dataGeneroPersonal.setValue("Hombres", Clinica.getInstance().calcCantGeneroByCond(false, false, true) + Clinica.getInstance().calcCantGeneroByCond(false, true, true));
        dataGeneroPersonal.setValue("Mujeres", Clinica.getInstance().calcCantGeneroByCond(false, false, false) + Clinica.getInstance().calcCantGeneroByCond(false, true, false));

        grafGeneroPacientes.repaint();
    }

    private void createGraficaGeneroPersonal() {
        dataGeneroPersonal = new DefaultPieDataset();
        dataGeneroPersonal.setValue("Hombres", Clinica.getInstance().calcCantGeneroByCond(false, false, true) + Clinica.getInstance().calcCantGeneroByCond(false, true, true));
        dataGeneroPersonal.setValue("Mujeres", Clinica.getInstance().calcCantGeneroByCond(false, false, false) + Clinica.getInstance().calcCantGeneroByCond(false, true, true));

        JFreeChart chart = ChartFactory.createPieChart(
                "Genero de Personal",
                dataGeneroPersonal,
                true,
                true,
                false
        );
        contentPane.setLayout(null);

        ChartPanel chartPanelGeneroPersonal = new ChartPanel(chart);
        chartPanelGeneroPersonal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartPanelGeneroPersonal.setBounds(12, 572, 347, 265);
        contentPane.add(chartPanelGeneroPersonal);

        grafGeneroPersonal = new JPanel();
        chartPanelGeneroPersonal.add(grafGeneroPersonal);
        grafGeneroPersonal.setLayout(null);
    }
    
    private static void actualizarEdadPacientes() {
        dataEdadpacientes.clear();
        ArrayList<Integer> edades = Clinica.getInstance().getEdadesPacientes();
        if (edades == null)
            return;

        ArrayList<Integer> uniqueEdades = new ArrayList<>();
        ArrayList<Integer> countEdades = new ArrayList<>();

        for (Integer edad : edades) {
            if (!uniqueEdades.contains(edad)) {
                uniqueEdades.add(edad);
                countEdades.add(1);
            } else {
                int index = uniqueEdades.indexOf(edad);
                int count = countEdades.get(index);
                countEdades.set(index, count + 1);
            }
        }

        for (int i = 0; i < uniqueEdades.size(); i++) {
            int edad = uniqueEdades.get(i);
            int cantidad = countEdades.get(i);
            dataEdadpacientes.addValue(cantidad, "Pacientes", String.valueOf(edad));
        }
    }

    private void createGraficaEdadPacientes() {
        dataEdadpacientes = new DefaultCategoryDataset();

        actualizarEdadPacientes();

        JFreeChart chart = ChartFactory.createLineChart(
                "Pacientes por Edades",
                "Edad", "Cantidad",
                dataEdadpacientes,
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
    
    private static void actualizarEdadDoctores() {
        dataEdadDoctores.clear();
        ArrayList<Integer> edades = Clinica.getInstance().getEdadDoctores();
        if (edades == null)
            return;

        ArrayList<Integer> uniqueEdades = new ArrayList<>();
        ArrayList<Integer> countEdades = new ArrayList<>();

        for (Integer edad : edades) {
            if (!uniqueEdades.contains(edad)) {
                uniqueEdades.add(edad);
                countEdades.add(1);
            } else {
                int index = uniqueEdades.indexOf(edad);
                int count = countEdades.get(index);
                countEdades.set(index, count + 1);
            }
        }

        for (int i = 0; i < uniqueEdades.size(); i++) {
            int edad = uniqueEdades.get(i);
            int cantidad = countEdades.get(i);
            dataEdadDoctores.addValue(cantidad, "Doctores", String.valueOf(edad));
        }
    }

    private void createGraficaEdadDoctores() {
        dataEdadDoctores = new DefaultCategoryDataset();

        actualizarEdadDoctores();

        JFreeChart chart = ChartFactory.createLineChart(
                "Doctores por Edades",
                "Edad", "Cantidad",
                dataEdadDoctores,
                PlotOrientation.VERTICAL,
                true, true, false);

        JPanel panelEdadDoctores = new JPanel();
        panelEdadDoctores.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelEdadDoctores.setBounds(371, 291, 732, 265);
        contentPane.add(panelEdadDoctores);
        panelEdadDoctores.setLayout(new BorderLayout(0, 0));

        ChartPanel grafEdadDoctores = new ChartPanel(chart);
        panelEdadDoctores.add(grafEdadDoctores, BorderLayout.CENTER);
        grafEdadDoctores.setPreferredSize(new Dimension(450, 350));
        grafEdadDoctores.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    }
    
    private static void actualizarConsultas() {
        dataConsultas.clear();
        ArrayList<Date> fechas = Clinica.getInstance().getDiasConsultas();
        if (fechas == null)
            return;

        ArrayList<Date> uniqueFechas = new ArrayList<>();
        ArrayList<Integer> countFechas = new ArrayList<>();

        for (Date fecha : fechas) {
            if (!uniqueFechas.contains(fecha)) {
                uniqueFechas.add(fecha);
                countFechas.add(1);
            } else {
                int index = uniqueFechas.indexOf(fecha);
                int count = countFechas.get(index);
                countFechas.set(index, count + 1);
            }
        }

        for (int i = 0; i < uniqueFechas.size(); i++) {
            Date fecha = uniqueFechas.get(i);
            int cantidad = countFechas.get(i);
            dataConsultas.addValue(cantidad, "Citas", fecha.toString());
        }
    }

    private void createGraficaConsultas() {
        dataConsultas = new DefaultCategoryDataset();

        actualizarConsultas();

        JFreeChart chart = ChartFactory.createLineChart(
                "Consultas",
                "Fecha", "Cantidad",
                dataConsultas,
                PlotOrientation.VERTICAL,
                true, true, false);

        JPanel panelConsultas = new JPanel();
        panelConsultas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelConsultas.setBounds(371, 572, 732, 265);
        contentPane.add(panelConsultas);
        panelConsultas.setLayout(new BorderLayout(0, 0));

        ChartPanel grafConsultas = new ChartPanel(chart);
        panelConsultas.add(grafConsultas, BorderLayout.CENTER);
        grafConsultas.setPreferredSize(new Dimension(450, 350));
        grafConsultas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    }
<<<<<<< HEAD
    
    private void actualizarEnfermedades() {
    	
    }
    
    private void createEnfermedades() {
    	
    	dataEnfermedades = new DefaultCategoryDataset();
    	
    	ArrayList<Integer> cantPorEnferemdad = new ArrayList<Integer>();
    	
    	for(Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
    		dataEnfermedades.addValue(Clinica.getInstance().calcCantEnfermedad(enfermedad), "Cantidad", enfermedad.getNombre());
    	}
    	
    	JFreeChart chart = ChartFactory.createBarChart(
    	        "Cantidad de Enfermedades",
    	        "Enfermedad",
    	        "Cantidad",
    	        dataEnfermedades,
    	        PlotOrientation.VERTICAL,
    	        true,
    	        true,
    	        false
    	);
    	
    	JScrollPane panelEnfermedades = new JScrollPane();
        panelEnfermedades.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelEnfermedades.setBounds(1115, 13, 762, 490);
        contentPane.add(panelEnfermedades);
    }
    
    
=======
    @Override
    public void dispose() {
        // Stop the update thread when the application is closing
        updateThread.stopUpdating();
        super.dispose();
    }
>>>>>>> branch 'main' of https://github.com/CactuZzz24/-Proyecto-Final-POO-CSTI-1830-4361.git
}

