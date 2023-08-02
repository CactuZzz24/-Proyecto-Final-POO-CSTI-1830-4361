package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.renderer.category.StatisticalBarRenderer;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Paciente;
import Logic.Persona;
import Logic.Vacuna;

import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.KeyStore.PrivateKeyEntry;

public class AplicarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tablePacientes;
	private static JTable tableVacunas;
	private static DefaultTableModel modeloPacientes;
	private static DefaultTableModel modeloVacunas;
	private static Object[] rowVacuna;
	private static Object[] rowPaciente;
	private static Doctor miDoctor = null;
	private Paciente selectedPaciente = null;
	private Vacuna selectedVacuna = null;
	private JButton btnAsignar;
	private JButton btnCancelar;
	private static ArrayList<Paciente> pacientesDoctor = new ArrayList<>();

	/**
	 * Create the dialog.
	 */
	public AplicarVacuna(Doctor doc) {
		setTitle("Asignar Vacuna");
		setResizable(false);

		miDoctor = doc;
		setBounds(100, 100, 672, 435);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0, 0, 326, 340);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);

		tablePacientes = new JTable();
		tablePacientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablePacientes.getSelectedRow();
				if (index >= 0) {
					btnAsignar.setEnabled(true);
					selectedPaciente = Clinica.getInstance()
							.buscarPacienteByCedula(tablePacientes.getValueAt(index, 0).toString());
				}
			}
		});
		scrollPane.setViewportView(tablePacientes);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(327, 0, 338, 340);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		tableVacunas = new JTable();
		tableVacunas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableVacunas.getSelectedRow();
				if (index >= 0) {
					btnAsignar.setEnabled(true);
					selectedVacuna = Clinica.getInstance()
							.buscarVacunaByCode(tableVacunas.getValueAt(index, 0).toString());
					if (selectedVacuna.getCantidadDisponible() <= 0) {
						btnAsignar.setEnabled(false);
					}
				}
			}
		});
		scrollPane_1.setViewportView(tableVacunas);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAsignar = new JButton("Asignar");
				btnAsignar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selectedVacuna.getEdadRequerida() <= edad()) {

							Clinica.getInstance().actualizaRegistroPaciente(selectedPaciente, null, selectedVacuna,
									null, null);
							selectedVacuna.setCantidadDisponible(selectedVacuna.getCantidadDisponible() - 1);
							Clinica.getInstance().uptadeVacuna(selectedVacuna);
							JOptionPane.showMessageDialog(null, "Vacuna Asignada", "Vacuna",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							if (selectedVacuna.getEdadRequerida() > edad()) {
								JOptionPane.showMessageDialog(null, "El Paciente no acepta esta vacuna",
										"Edad del Paciente", JOptionPane.ERROR_MESSAGE);
							}

							return;
						}

					}
				});
				btnAsignar.setActionCommand("OK");
				buttonPane.add(btnAsignar);
				getRootPane().setDefaultButton(btnAsignar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			modeloPacientes = new DefaultTableModel();
			String[] headersPacientes = { "Cédula", "Nombre", "Tel.", "Estado" };
			scrollPane.setViewportView(tablePacientes);
			modeloPacientes.setColumnIdentifiers(headersPacientes);
			tablePacientes.setModel(modeloPacientes);

			modeloVacunas = new DefaultTableModel();
			String[] headersVacunas = { "Código", "Nombre", "Cantidad" };
			scrollPane_1.setViewportView(tableVacunas);
			modeloVacunas.setColumnIdentifiers(headersVacunas);
			tableVacunas.setModel(modeloVacunas);
			loadPacientes();
			loadVacunas();
		}
	}

	public static void loadVacunas() {
		modeloVacunas.setRowCount(0);
		rowVacuna = new Object[modeloVacunas.getColumnCount()];

		for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {

			rowVacuna[0] = vacuna.getCodigo();
			rowVacuna[1] = vacuna.getNombre();
			rowVacuna[2] = vacuna.getCantidadDisponible();
			modeloVacunas.addRow(rowVacuna);

		}

	}

	public static void loadPacientes() {
		modeloPacientes.setRowCount(0);
		rowPaciente = new Object[modeloPacientes.getColumnCount()];

		pacientesDoctor.clear();

		System.out.println("Cantidad de consultas: " + Clinica.getInstance().getMisConsultas().size());

		HashSet<Paciente> uniquePacientes = new HashSet<>();

		for (Consulta consulta : Clinica.getInstance().getMisConsultas()) {
			if (consulta.getMiPersona() instanceof Paciente && consulta.getMiDoctor().equals(miDoctor)) {
				Paciente paciente = (Paciente) consulta.getMiPersona();
				uniquePacientes.add(paciente);
			}
		}

		for (Paciente paciente : uniquePacientes) {
			System.out.println("Encontrado paciente: " + paciente.getNombre());
			rowPaciente[0] = paciente.getCedula();
			rowPaciente[1] = paciente.getNombre();
			rowPaciente[2] = paciente.getTelefono();
			rowPaciente[3] = paciente.isVigilancia();
			modeloPacientes.addRow(rowPaciente);
		}
	}

	private int edad() {
		Date fechaNacimiento = selectedPaciente.getFchNacim();

		Calendar fechaActual = Calendar.getInstance();

		Calendar fechaNacimientoCalendar = Calendar.getInstance();
		fechaNacimientoCalendar.setTime(fechaNacimiento);

		int edad = fechaActual.get(Calendar.YEAR) - fechaNacimientoCalendar.get(Calendar.YEAR);
		if (fechaActual.get(Calendar.MONTH) < fechaNacimientoCalendar.get(Calendar.MONTH)
				|| (fechaActual.get(Calendar.MONTH) == fechaNacimientoCalendar.get(Calendar.MONTH) && fechaActual
						.get(Calendar.DAY_OF_MONTH) < fechaNacimientoCalendar.get(Calendar.DAY_OF_MONTH))) {
			edad--;
		}

		return edad;
	}

}
