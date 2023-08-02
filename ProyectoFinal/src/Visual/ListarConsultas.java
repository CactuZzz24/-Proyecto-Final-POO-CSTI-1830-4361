package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Logic.Cita;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class ListarConsultas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private Consulta selectedConsulta = null;
	private static DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JPanel buttonPane;
	private JPanel panel_1;
	private JDatePickerImpl datePickerCita;
	private static ArrayList<Consulta> consultasFiltradas = new ArrayList<>();


	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 * @param medico 
	 */
	public ListarConsultas(boolean tienePermisos, Paciente paciente, Doctor medico) {
		setResizable(false);
		setBounds(100, 100, 679, 469);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 47, 673, 343);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index >= 0 && tienePermisos) {
								btnActualizar.setEnabled(true);
								btnEliminar.setEnabled(true);
								selectedConsulta = Clinica.getInstance().buscarConsultaByCode(table.getValueAt(index, 0)
										.toString());
							}
							
						}
					});
					
					modelo = new DefaultTableModel();
					String[] headers = {"Código", "Persona", "Doctor", "Fecha"};
					scrollPane.setViewportView(table);
					modelo.setColumnIdentifiers(headers);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
					{
						panel_1 = new JPanel();
						panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
						panel_1.setBounds(0, 0, 673, 51);
						panel.add(panel_1);
						panel_1.setLayout(null);
					}
					   if (tienePermisos && medico != null) {
				
					        
				            loadConsultasDoctor(medico);
				        } else if (!tienePermisos && paciente != null) {
				        	
				 		 
				            loadConsultasPaciente(paciente);
				            
				            
				        } else if (tienePermisos) {
				   
				            loadConsultas();
				   
				        }
				}
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
				datePickerCita = createDatePickerCita();
				datePickerCita.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						filterConsultasByDate();

					}
				});
				datePickerCita.getJFormattedTextField().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						filterConsultasByDate();
					}

					
				});
				datePickerCita.setBounds(506, 16, 126, 27);
				panel_1.add(datePickerCita);
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
        consultasFiltradas.clear();

        for (Consulta consulta : paciente.getMisConsultas()) {
            if (consulta != null && consulta.getMiPersona() != null && consulta.getMiDoctor() != null) {
               consultasFiltradas.add(consulta);
            }
        }
        loadConsultasFiltradas();
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
	private static void loadConsultasFiltradas() {
		modelo.setRowCount(0);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    for (Consulta consulta : consultasFiltradas) {
	        if (consulta != null && consulta.getMiPersona() != null && consulta.getMiDoctor() != null) {
	            Object[] row = {
	                consulta.getCodigo(),
	                consulta.getMiPersona().getNombre(),
	                consulta.getMiDoctor().getNombre(),
	                dateFormat.format(consulta.getFecha())
	            };
	            modelo.addRow(row);
	        }
	    }			
	}
	
	public static void loadConsultasDoctor(Doctor medico) {
	    if (Clinica.getInstance() == null || Clinica.getInstance().getMisConsultas() == null) {
	        return; 
	    }
	    consultasFiltradas.clear();
	    modelo.setRowCount(0);
	    row = new Object[table.getColumnCount()];

	    for (Consulta consulta : Clinica.getInstance().getMisConsultas()) {
	        if (consulta != null && consulta.getMiPersona() != null && consulta.getMiDoctor() != null) {
	            if (consulta.getMiDoctor().equals(medico)) {
	               consultasFiltradas.add(consulta);
	               }
	        }
	    }
	    loadConsultasFiltradas(); 

	    
	    
	}
	 private JDatePickerImpl createDatePickerCita() {
		    UtilDateModel model = new UtilDateModel();
		    Properties properties = new Properties();
		    properties.put("text.today", "Today");
		    properties.put("text.month", "Month");
		    properties.put("text.year", "Year");
		    JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);

		    datePanel.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            java.util.Date selectedDate = (java.util.Date) model.getValue();

		        }
		    });

		    return new JDatePickerImpl(datePanel, null);
		}
		private void filterConsultasByDate() {
		    if (Clinica.getInstance() == null || Clinica.getInstance().getMisCitas() == null) {
		        return; 
		    }

		    consultasFiltradas.clear(); 

		    java.util.Date selectedDate = (java.util.Date) datePickerCita.getModel().getValue();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String selectedFecha = dateFormat.format(selectedDate);

		    for (Consulta consulta : Clinica.getInstance().getMisConsultas()) {
		        if (consulta != null && consulta.getMiPersona() != null && consulta.getMiDoctor() != null) {
		            String citaFecha = dateFormat.format(consulta.getFecha());
		            if (citaFecha.equals(selectedFecha)) {
		                consultasFiltradas.add(consulta); 
		            }
		        }
		    }

		    loadConsultasFiltradas(); 
		}

}
