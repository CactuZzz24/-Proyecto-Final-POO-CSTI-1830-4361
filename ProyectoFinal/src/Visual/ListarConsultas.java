package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Doctor;
import Logic.Paciente;
import Logic.Persona;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarConsultas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private Consulta selectedConsulta = null;
	private static DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnActualizar;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 * @param medico 
	 */
	public ListarConsultas(boolean esDoctor, Paciente paciente, Doctor medico) {
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
								selectedConsulta = Clinica.getInstance().buscarConsultaByCode(table.getValueAt(index, 0)
										.toString());
							}
							
						}
					});
					
					modelo = new DefaultTableModel();
					String[] headers = {"C�digo", "Persona", "Doctor"};
					scrollPane.setViewportView(table);
					modelo.setColumnIdentifiers(headers);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
					   if (esDoctor && medico != null) {
				            loadConsultasDoctor(medico);
				        } else if (!esDoctor && paciente != null) {
				            loadConsultasPaciente(paciente);
				        } else if (!esDoctor) {
				            loadConsultas();
				        }
				}
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
							RegConsulta uptade = new RegConsulta(selectedConsulta, null, true);
							uptade.setModal(true);
							uptade.setVisible(true);
						
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
						if (selectedConsulta != null) {
							int option = JOptionPane.showConfirmDialog(null,
									"Esta seguro que desea eliminar la consulta con codigo: "
											+ selectedConsulta.getCodigo(),
									"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION) {
								Clinica.getInstance().eliminarConsulta(selectedConsulta);
								loadConsultas();
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

	public static void loadConsultasPaciente(Paciente paciente) {
        modelo.setRowCount(0);
        row = new Object[table.getColumnCount()];

        for (Consulta consulta : paciente.getMisConsultas()) {
            if (consulta != null && consulta.getMiPersona() != null && consulta.getMiDoctor() != null) {
                row[0] = consulta.getCodigo();
                row[1] = consulta.getMiPersona().getNombre();
                row[2] = consulta.getMiDoctor().getNombre();
                modelo.addRow(row);
            }
        }
    }


	public static void loadConsultas() {
	    if (Clinica.getInstance() == null || Clinica.getInstance().getMisConsultas() == null) {
	        return; 
	    }

	    modelo.setRowCount(0);
	    row = new Object[table.getColumnCount()];

	    for (Consulta consulta : Clinica.getInstance().getMisConsultas()) {
	        if (consulta != null && consulta.getMiPersona() != null && consulta.getMiDoctor() != null) {
	            row[0] = consulta.getCodigo();
	            row[1] = consulta.getMiPersona().getNombre();
	            row[2] = consulta.getMiDoctor().getNombre();
	            modelo.addRow(row);
	        }
	    }
	}
	public static void loadConsultasDoctor(Doctor medico) {
	    if (Clinica.getInstance() == null || Clinica.getInstance().getMisConsultas() == null) {
	        return; 
	    }

	    modelo.setRowCount(0);
	    row = new Object[table.getColumnCount()];

	    for (Consulta consulta : Clinica.getInstance().getMisConsultas()) {
	        if (consulta != null && consulta.getMiPersona() != null && consulta.getMiDoctor() != null) {
	            if (consulta.getMiDoctor().equals(medico)) {
	                row[0] = consulta.getCodigo();
	                row[1] = consulta.getMiPersona().getNombre();
	                row[2] = consulta.getMiDoctor().getNombre();
	                modelo.addRow(row);
	            }
	        }
	    }
	}



}
