package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logic.Clinica;
import Logic.Enfermedad;
import Logic.Paciente;
import Logic.ResumenClinico;

public class ListarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private Enfermedad selectedEnfermedad = null;
	private static DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnActualizar;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ListarEnfermedad(boolean esPaciente, Paciente miPaciente) {
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
								selectedEnfermedad = Clinica.getInstance().buscarEnfermedadByCode(table.getValueAt(index, 0)
										.toString());
							}
							
						}
					});
					
					modelo = new DefaultTableModel();
					String[] headers = {"Código", "Nombre", "Gravedad"};
					scrollPane.setViewportView(table);
					modelo.setColumnIdentifiers(headers);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
			        if (esPaciente) {
			        	
			            loadEnfermedadesPaciente(miPaciente);
			        } else {
			            loadEnfermedades();
			        }
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
						RegEnfermedad uptadeEnfermedad = new RegEnfermedad(selectedEnfermedad);
						uptadeEnfermedad.setModal(true);
						uptadeEnfermedad.setVisible(true);
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
						if (selectedEnfermedad != null) {
							int option = JOptionPane.showConfirmDialog(null,
									"Esta seguro que desea eliminar el Doctor con cedula: "
											+ selectedEnfermedad.getCodigo(),
									"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION) {
								Clinica.getInstance().eliminarEnfermedad(selectedEnfermedad);
								loadEnfermedades();
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
	

	public static void loadEnfermedades() {
		modelo.setRowCount(0);
row = new Object[table.getColumnCount()];
		
		for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
			
				row[0] = enfermedad.getCodigo();
				row[1] = enfermedad.getNombre();
				row[2] = enfermedad.getGravedad();
				modelo.addRow(row);
				
			
		}
			
		
	}
	  public static void loadEnfermedadesPaciente(Paciente miPaciente) {
	        modelo.setRowCount(0);
	        row = new Object[table.getColumnCount()];

	        if (miPaciente != null && miPaciente.getResumenClinico() != null) {
	            ArrayList<Enfermedad> misEnfermedades = miPaciente.getResumenClinico().getMisEnfermedades();
	            if (misEnfermedades == null || misEnfermedades.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "El paciente no tiene enfermedades registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            } else {
	                for (Enfermedad enfermedad : misEnfermedades) {
	                    row[0] = enfermedad.getCodigo();
	                    row[1] = enfermedad.getNombre();
	                    row[2] = enfermedad.getGravedad();
	                    modelo.addRow(row);
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "El paciente no tiene resumen clínico registrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
	            
	        }
	        return;
	        
	    }
	}


