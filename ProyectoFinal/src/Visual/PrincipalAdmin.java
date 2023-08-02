package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



import Logic.Clinica;
import Logic.Enfermedad;
import Logic.UptadeGraficas;
import Logic.Vacuna;
import Logic.Clinica;
import Logic.Enfermedad;
import Logic.UptadeGraficas;
import Logic.Clinica;
import Logic.Enfermedad;
import Logic.UptadeGraficas;
import server.Servidor;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class PrincipalAdmin extends JFrame {

	private Dimension dim;
	
	private static JPanel grafUsarios;
	private static JPanel grafPacientesVigilancia;
	private static JPanel grafEdadpacientes;
	private static JPanel grafEdadDoctor;
	private static JPanel panelCantPacienteEnfermedad;
	private static JPanel contentPane;
	private static JPanel panelCantPorEnfermedad;
	private static JScrollPane panelVacunas;
	private static JPanel panelEspecialidades;
	
	private static DefaultPieDataset data;
	private static DefaultPieDataset dataPacientesVigilancia;
	private static DefaultPieDataset dataEdadpacientes;
	private static DefaultPieDataset dataEdadDoctor;
	
	private static DefaultCategoryDataset dataConsultas;
	private static DefaultCategoryDataset dataVacunas = new DefaultCategoryDataset();
	private static DefaultCategoryDataset dataEspecialidades = new DefaultCategoryDataset();
	
	private static JComboBox boxEnfermedades;
	
	private static JLabel lblCantPacienEferme;

	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
    private UptadeGraficas updateThread;
    
	public PrincipalAdmin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
		
			 try {
			        if (sfd != null) {
			            SalidaSocket.writeUTF("EXIT"); 
			            SalidaSocket.flush();
			            sfd.close();
			        }
			    } catch (IOException ioe) {
			        System.out.println("Error al cerrar el socket del servidor: " + ioe);
			    }
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
			    Servidor.stopServer();
			    dispose();
				
			}
		});
		
		updateThread = new UptadeGraficas(null, null);
        updateThread.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height-100);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 240));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Citas");
		mnNewMenu.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
		menuBar.add(mnNewMenu);
		
		JMenuItem btnRegistrarConsulta = new JMenuItem("Registrar");
		btnRegistrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCita reg = new RegCita(false, true, null, null);
				reg.setModal(true);
				reg.setVisible(true);
			
			}
		});
		mnNewMenu.add(btnRegistrarConsulta);
		
		JMenuItem btnListarConsulta = new JMenuItem("Listar");
		btnListarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCitas listar = new ListarCitas(false, null, null);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu.add(btnListarConsulta);
		
		JMenu mnNewMenu_6 = new JMenu("Consultas");
		mnNewMenu_6.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar");
		mnNewMenu_6.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Listar");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarConsultas listar = new ListarConsultas(true, null, null);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("Medicos");
		mnNewMenu_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
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
		mnNewMenu_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
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
		mnNewMenu_3.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
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
		mnNewMenu_4.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
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
		mnNewMenu_5.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
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
				ListarEnfermedad listar = new ListarEnfermedad(true, null);
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		crearGraficas();
	}
	
	private void crearGraficas() {
		createGraficaUsarios();
		createGraficaEdadPacientes();
		createGraficaEdadDoctores();
		createGraficaConsultas();
		createVacunas();
		createEnfermedades();
		createPacientesVigilacia();
		createEspecialidades();
	}

	
	public static void actualizarGraficas() {
        actualizarGraficaUsarios();
        actualizarEdadPacientes();
        actualizarEdadDoctores();
        actualizarConsultas();
        actualizarEnfermedades();
        actualizarVacunas();
		actualizarPacientesVigilancia();
		actualizarEspecialidades();
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
    
    private static void actualizarEdadPacientes() {
    	dataEdadpacientes.clear();
    	dataEdadpacientes.setValue("-18", Clinica.getInstance().calcCantEdadInRangePaciente(0, 17));
    	dataEdadpacientes.setValue("18-21", Clinica.getInstance().calcCantEdadInRangePaciente(18, 21));
    	dataEdadpacientes.setValue("21-30", Clinica.getInstance().calcCantEdadInRangePaciente(22, 30));
    	dataEdadpacientes.setValue("30-50", Clinica.getInstance().calcCantEdadInRangePaciente(31, 50));
    	dataEdadpacientes.setValue("+50", Clinica.getInstance().calcCantEdadInRangePaciente(51, 3000));
    	grafEdadpacientes.repaint();
    }

    private void createGraficaEdadPacientes() {
    	dataEdadpacientes = new DefaultPieDataset();
    	dataEdadpacientes.setValue("-18", Clinica.getInstance().calcCantEdadInRangePaciente(0, 17));
    	dataEdadpacientes.setValue("18-21", Clinica.getInstance().calcCantEdadInRangePaciente(18, 21));
    	dataEdadpacientes.setValue("21-30", Clinica.getInstance().calcCantEdadInRangePaciente(22, 30));
    	dataEdadpacientes.setValue("30-50", Clinica.getInstance().calcCantEdadInRangePaciente(31, 50));
    	dataEdadpacientes.setValue("+50", Clinica.getInstance().calcCantEdadInRangePaciente(51, 3000));

        JFreeChart chart = ChartFactory.createPieChart(
                "Edad Pacientes",
                dataEdadpacientes,
                true,
                true,
                false
        );
        contentPane.setLayout(null);

        ChartPanel chartPanelEdadpacientes = new ChartPanel(chart);
        chartPanelEdadpacientes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartPanelEdadpacientes.setBounds(12, 629, 347, 265);
        contentPane.add(chartPanelEdadpacientes);

        grafEdadpacientes = new JPanel();
        chartPanelEdadpacientes.add(grafEdadpacientes);
        grafEdadpacientes.setLayout(null);
    }
    
    private static void actualizarEdadDoctores() {
    	dataEdadpacientes.clear();
    	dataEdadpacientes.setValue("18-21", Clinica.getInstance().calcCantEdadInRangeDoctor(18, 21));
    	dataEdadpacientes.setValue("21-30", Clinica.getInstance().calcCantEdadInRangeDoctor(22, 30));
    	dataEdadpacientes.setValue("30-50", Clinica.getInstance().calcCantEdadInRangeDoctor(31, 50));
    	dataEdadpacientes.setValue("+50", Clinica.getInstance().calcCantEdadInRangeDoctor(51, 3000));
    	grafEdadpacientes.repaint();
    }

    private void createGraficaEdadDoctores() {
    	dataEdadDoctor = new DefaultPieDataset();
    	dataEdadDoctor.setValue("-18", Clinica.getInstance().calcCantEdadInRangeDoctor(0, 17));
    	dataEdadDoctor.setValue("18-21", Clinica.getInstance().calcCantEdadInRangeDoctor(18, 21));
    	dataEdadDoctor.setValue("21-30", Clinica.getInstance().calcCantEdadInRangeDoctor(22, 30));
    	dataEdadDoctor.setValue("30-50", Clinica.getInstance().calcCantEdadInRangeDoctor(31, 50));
    	dataEdadDoctor.setValue("+50", Clinica.getInstance().calcCantEdadInRangeDoctor(51, 3000));

        JFreeChart chart = ChartFactory.createPieChart(
                "Edad Doctores",
                dataEdadDoctor,
                true,
                true,
                false
        );
        contentPane.setLayout(null);

        ChartPanel chartPanelEdadDoctor = new ChartPanel(chart);
        chartPanelEdadDoctor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartPanelEdadDoctor.setBounds(12, 321, 347, 265);
        contentPane.add(chartPanelEdadDoctor);

        grafEdadDoctor = new JPanel();
        chartPanelEdadDoctor.add(grafEdadDoctor);
        grafEdadDoctor.setLayout(null);
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
        panelConsultas.setBounds(397, 321, 719, 573);
        contentPane.add(panelConsultas);
        panelConsultas.setLayout(new BorderLayout(0, 0));

        ChartPanel grafConsultas = new ChartPanel(chart);
        panelConsultas.add(grafConsultas, BorderLayout.CENTER);
        grafConsultas.setPreferredSize(new Dimension(450, 350));
        grafConsultas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    }
    
    private static void actualizarEnfermedades() {
    	if(!Clinica.getInstance().existenEfermedades())
    		return;
    	
        boxEnfermedades.setModel(new DefaultComboBoxModel(Clinica.getInstance().getArrayNombreEnfermedades()));
        lblCantPacienEferme = new JLabel(String.valueOf(Clinica.getInstance().calcCantEnfermedad(Clinica.getInstance().getMisEnfermedades().get(boxEnfermedades.getSelectedIndex()))));
    	int cant = Clinica.getInstance().calcCantEnfermedad(Clinica.getInstance().getMisEnfermedades().get(boxEnfermedades.getSelectedIndex()));
        if(cant < 10)
        	panelCantPorEnfermedad.setBackground(new Color(152, 251, 152));
        else if(cant < 25)
        	panelCantPorEnfermedad.setBackground(new Color(240, 230, 140));
        else
        	panelCantPorEnfermedad.setBackground(new Color(205, 92, 92));
        panelCantPacienteEnfermedad.repaint();
    }

    private void createEnfermedades() { 
    	if(!Clinica.getInstance().existenEfermedades())
    		return;
    	panelCantPacienteEnfermedad = new JPanel();
        panelCantPacienteEnfermedad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelCantPacienteEnfermedad.setBounds(769, 13, 347, 265);
        contentPane.add(panelCantPacienteEnfermedad);
        panelCantPacienteEnfermedad.setLayout(null);
        
        boxEnfermedades = new JComboBox();
        boxEnfermedades.setModel(new DefaultComboBoxModel(Clinica.getInstance().getArrayNombreEnfermedades()));
        boxEnfermedades.setSelectedIndex(0);
        boxEnfermedades.setBounds(12, 38, 323, 22);
        panelCantPacienteEnfermedad.add(boxEnfermedades);

    	int cant = Clinica.getInstance().calcCantEnfermedad(Clinica.getInstance().getMisEnfermedades().get(boxEnfermedades.getSelectedIndex()));
        
        JLabel lblNewLabel = new JLabel("Cantidad de Pacientes con Efermedad");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel.setBounds(12, 13, 323, 16);
        panelCantPacienteEnfermedad.add(lblNewLabel);
        
        panelCantPorEnfermedad = new JPanel();
        panelCantPorEnfermedad.setForeground(new Color(0, 0, 0));
        if(cant < 10)
        	panelCantPorEnfermedad.setBackground(new Color(152, 251, 152));
        else if(cant < 25)
            panelCantPorEnfermedad.setBackground(new Color(240, 230, 140));
        else
            panelCantPorEnfermedad.setBackground(new Color(205, 92, 92));
        
        panelCantPorEnfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelCantPorEnfermedad.setBounds(12, 73, 323, 179);
        panelCantPacienteEnfermedad.add(panelCantPorEnfermedad);
        panelCantPorEnfermedad.setLayout(new BorderLayout(0, 0));
        
        lblCantPacienEferme = new JLabel(String.valueOf(cant));
        panelCantPorEnfermedad.add(lblCantPacienEferme, BorderLayout.CENTER);
        lblCantPacienEferme.setFont(new Font("Segoe UI", Font.PLAIN, 55));
        lblCantPacienEferme.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private static void actualizarVacunas() {
        dataVacunas.clear();
        for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
            dataVacunas.addValue(Clinica.getInstance().calcCantVacuna(vacuna), "Cantidad", vacuna.getNombre());
        }
        panelVacunas.repaint();
    }

    private static void createVacunas() {
        for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
            dataVacunas.addValue(Clinica.getInstance().calcCantVacuna(vacuna), "Cantidad", vacuna.getNombre());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Cantidad de Vacunas",
                "Vacuna",
                "Cantidad",
                dataVacunas,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        NumberAxis yAxis = (NumberAxis)chart.getCategoryPlot().getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(1));

        CategoryPlot plot = chart.getCategoryPlot();
        Color color = new Color(25, 169, 25); 
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        panelVacunas = new JScrollPane();
        panelVacunas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelVacunas.setBounds(1158, 478, 732, 416);
        contentPane.add(panelVacunas);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(730, 416);
        chartPanel.setPreferredSize(new Dimension(400, 400));

        panelVacunas.setViewportView(chartPanel);
    }
    

    private static void actualizarPacientesVigilancia() {
    	dataPacientesVigilancia.clear();
    	dataPacientesVigilancia.setValue("En Vigilancia", Clinica.getInstance().calcCantPacientesEnVigilancia());
    	dataPacientesVigilancia.setValue("De alta", Clinica.getInstance().calcCantPacientesDeAlta());
    	grafPacientesVigilancia.repaint();
    }
    
    private void createPacientesVigilacia() {
    	dataPacientesVigilancia = new DefaultPieDataset();
    	dataPacientesVigilancia.setValue("En Vigilancia", Clinica.getInstance().calcCantPacientesEnVigilancia());
    	dataPacientesVigilancia.setValue("De alta", Clinica.getInstance().calcCantPacientesDeAlta());

        JFreeChart chart = ChartFactory.createPieChart(
                "Pacientes en Vigilancia",
                dataPacientesVigilancia,
                true,
                true,
                false
        );
        contentPane.setLayout(null);

        ChartPanel chartPanelPacientesVigilancia = new ChartPanel(chart);
        chartPanelPacientesVigilancia.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartPanelPacientesVigilancia.setBounds(397, 13, 347, 265);
        contentPane.add(chartPanelPacientesVigilancia);
        
        grafPacientesVigilancia = new JPanel();
        chartPanelPacientesVigilancia.add(grafPacientesVigilancia);
        grafPacientesVigilancia.setLayout(null);
    }
    
    private static void actualizarEspecialidades() {
    	dataEspecialidades.clear();
    	ArrayList<String> especialidades = Clinica.getClinica().getEspecialidades();
    	for (String especialidad : especialidades) {
    		dataEspecialidades.addValue(Clinica.getInstance().calcCantEspecialidad(especialidad), "Cantidad", especialidad);
        }
    	panelEspecialidades.repaint();
    }
    
    private void createEspecialidades() {
    	ArrayList<String> especialidades = Clinica.getClinica().getEspecialidades();
    	
    	for (String especialidad : especialidades) {
    		dataEspecialidades.addValue(Clinica.getInstance().calcCantEspecialidad(especialidad), "Cantidad", especialidad);
        }
    	
        JFreeChart chart = ChartFactory.createBarChart(
                "Cantidad de Medicos por Especializacion",
                "Especialidad",
                "Cantidad",
                dataEspecialidades,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        NumberAxis yAxis = (NumberAxis)chart.getCategoryPlot().getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(1));

        CategoryPlot plot = chart.getCategoryPlot();
        Color color = new Color(25, 169, 25); 
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        panelEspecialidades = new JPanel();
        panelEspecialidades.setBounds(1158, 21, 732, 428);
        contentPane.add(panelEspecialidades);
        panelEspecialidades.setLayout(new BorderLayout(0, 0));

        ChartPanel chartPanelEspecialidades = new ChartPanel(chart);
        chartPanelEspecialidades.setPreferredSize(new Dimension(400, 400));
        panelEspecialidades.add(chartPanelEspecialidades);
    }
    
    
    // Detener el servidor antes de cerrar la aplicación
    public void dispose() {
        updateThread.stopUpdating();
        Servidor.stopServer();
        super.dispose();
    }
}

