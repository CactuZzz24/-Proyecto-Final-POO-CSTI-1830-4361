package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ParseConversionEvent;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Paciente;
import Logic.Persona;
import Logic.Vacuna;

public class ListarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private Paciente selectedPaciente = null;
	private static DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private static Doctor doc = null;
	private static boolean esDoctor = false;
	 

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ListarPaciente(boolean Doc, Doctor doctor) {
		setResizable(false);
		esDoctor = Doc;
		 doc = doctor;
		setBounds(100, 100, 679, 469);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index >= 0) {
								btnActualizar.setEnabled(true);
								btnEliminar.setEnabled(true);
								selectedPaciente = Clinica.getInstance().buscarPacienteByCedula(table.getValueAt(index, 0)
										.toString());
							}
							
						}
					});
					
					modelo = new DefaultTableModel();
					String[] headers = {"Cédula", "Nombre","Tel.", "Estado"};
					scrollPane.setViewportView(table);
					modelo.setColumnIdentifiers(headers);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
					loadPacientes();			}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnActualizar = new JButton("Actualizar");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegUsuario actualizar = new RegUsuario(true, false, Clinica.getInstance().buscarUsuarioByPersona(selectedPaciente));
						actualizar.setModal(true);
						actualizar.setVisible(true);
					}
				});
				btnActualizar.setEnabled(false);
				btnActualizar.setActionCommand("OK");
				buttonPane.add(btnActualizar);
				getRootPane().setDefaultButton(btnActualizar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selectedPaciente != null) {
							int option = JOptionPane.showConfirmDialog(null,
									"Esta seguro que desea eliminar al Paciente con nombre "
											+ selectedPaciente.getNombre(),
									"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION) {
								Clinica.getInstance().eliminarPersona(selectedPaciente);
								Clinica.getInstance().eliminarUsuario(Clinica.getInstance().buscarUsuarioByPersona(selectedPaciente));
								loadPacientes();
								PrincipalAdmin.actualizarGraficas();
							}
						}
					}
						
					
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("Cancel");
				buttonPane.add(btnEliminar);
			}
			{
				JButton btnNewButton = new JButton("Cancelar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnNewButton);
			}
		}
	}

	public static void loadPacientes() {
	    modelo.setRowCount(0);
	    row = new Object[table.getColumnCount()];

	    if (esDoctor && doc != null) {
	        for (Consulta consulta : Clinica.getInstance().getMisConsultas()) {
	            Doctor attendingDoctor = consulta.getMiDoctor();
	            if (attendingDoctor != null && attendingDoctor.equals(doc)) {
	                Persona persona = consulta.getMiPersona();
	                if (persona != null && persona instanceof Paciente) {
	                    row[0] = persona.getCedula();
	                    row[1] = persona.getNombre();
	                    row[2] = persona.getTelefono();
	                    row[3] = ((Paciente) persona).isVigilancia();
	                    modelo.addRow(row);
	                }
	            }
	        }
	    } else {
	        for (Persona persona : Clinica.getInstance().getMisPersonas()) {
	            if (persona instanceof Paciente) {
	                row[0] = persona.getCedula();
	                row[1] = persona.getNombre();
	                row[2] = persona.getTelefono();
	                row[3] = ((Paciente) persona).isVigilancia();
	                modelo.addRow(row);
	            }
	        }
	    }
	}


}
