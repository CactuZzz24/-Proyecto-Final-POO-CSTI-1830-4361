package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class ListarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private Enfermedad selectedEnfermedad = null;
	private static DefaultTableModel modelo;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private static boolean privil = false;
	private static Paciente miPaciente = null;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ListarEnfermedad(boolean tienePermisos, Paciente paciente) {
		miPaciente = paciente;
		privil = tienePermisos;
		setResizable(false);
		setBounds(100, 100, 679, 469);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(0, 5, 673, 426);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 57, 674, 318);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
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
								if(!privil) {
									btnActualizar.setEnabled(false);
									btnEliminar.setEnabled(false);
								}
							}
							
						}
					});
					
					modelo = new DefaultTableModel();
					String[] headers = {"Código", "Nombre", "Gravedad"};
					scrollPane.setViewportView(table);
					modelo.setColumnIdentifiers(headers);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
					{
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						panel_1.setBounds(0, 0, 674, 59);
						panel.add(panel_1);
						panel_1.setLayout(null);
						{
							JComboBox comboBox = new JComboBox();
							comboBox.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
					                String selectedSeverity = (String) comboBox.getSelectedItem();

					                if (!privil) {
					                    filterEnfermedadesPaciente(miPaciente, selectedSeverity);
					                } else {
					                    filterEnfermedades(selectedSeverity);
					                }
					            
								}
							});
							comboBox.setBounds(535, 16, 124, 26);
							 String[] severityOptions = {"Todas", "Leve", "Moderada", "Alta"};
						        comboBox.setModel(new DefaultComboBoxModel<>(severityOptions));
							panel_1.add(comboBox);
						}
					}
					{
						JPanel buttonPane = new JPanel();
						buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
						FlowLayout fl_buttonPane = (FlowLayout) buttonPane.getLayout();
						fl_buttonPane.setAlignment(FlowLayout.RIGHT);
						fl_buttonPane.setVgap(15);
						getContentPane().add(buttonPane, BorderLayout.SOUTH);
						{
							btnActualizar = new JButton("Actualizar");
							btnActualizar.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									RegEnfermedad reg = new RegEnfermedad(selectedEnfermedad);
									reg.setModal(true);
									reg.setVisible(true);
								}
							});
							btnActualizar.setEnabled(false);
							btnActualizar.setHorizontalAlignment(SwingConstants.RIGHT);
							buttonPane.add(btnActualizar);
						}
						{
							btnEliminar = new JButton("Eliminar");
							btnEliminar.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									
									if (selectedEnfermedad != null && tienePermisos) {
										int option = JOptionPane.showConfirmDialog(null,
												"Esta seguro que desea eliminar la enfermedad con código: "
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
							btnEliminar.setHorizontalAlignment(SwingConstants.RIGHT);
							buttonPane.add(btnEliminar);
						}
						{
							btnCancelar = new JButton("Cancelar");
							btnCancelar.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									dispose();
								}
							});
							btnCancelar.setHorizontalAlignment(SwingConstants.RIGHT);
							buttonPane.add(btnCancelar);
						}
					}
			        if (!privil) {
			        	
			            loadEnfermedadesPaciente(miPaciente);
			        } else {
			            loadEnfermedades();
			        }
			    }

								
			}
			}
		}
		{
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
	    public static void filterEnfermedades(String severity) {
	        modelo.setRowCount(0);
	        row = new Object[table.getColumnCount()];

	        for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
	            if (enfermedad.getGravedad().equals(severity)) {
	                row[0] = enfermedad.getCodigo();
	                row[1] = enfermedad.getNombre();
	                row[2] = enfermedad.getGravedad();
	                modelo.addRow(row);
	            }
	        }
	    }

	    public static void filterEnfermedadesPaciente(Paciente miPaciente, String severity) {
	        modelo.setRowCount(0);
	        row = new Object[table.getColumnCount()];

	        if (miPaciente != null && miPaciente.getResumenClinico() != null) {
	            ArrayList<Enfermedad> misEnfermedades = miPaciente.getResumenClinico().getMisEnfermedades();
	            if (misEnfermedades == null || misEnfermedades.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "El paciente no tiene enfermedades registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            } else {
	                for (Enfermedad enfermedad : misEnfermedades) {
	                    if (enfermedad.getGravedad().equals(severity)) {
	                        row[0] = enfermedad.getCodigo();
	                        row[1] = enfermedad.getNombre();
	                        row[2] = enfermedad.getGravedad();
	                        modelo.addRow(row);
	                    }
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "El paciente no tiene resumen clínico registrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
	        }
	        return;
	    }
	}
	


