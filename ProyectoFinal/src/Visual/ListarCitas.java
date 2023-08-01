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
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class ListarCitas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private Cita selectedCita = null;
	private static DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JDatePickerImpl datePickerCita;
	private JPanel panel_1;
	private ArrayList<Cita> citasFiltradas = new ArrayList<>();


	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 * @param medico 
	 */
	public ListarCitas(boolean esDoctor, Persona persona, Doctor doctor) {
		setBounds(100, 100, 679, 469);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 51, 647, 313);
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
								selectedCita = Clinica.getInstance().buscarCitaByCode(table.getValueAt(index, 0)
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
				
				}
			}
			
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(0, 0, 647, 51);
			panel.add(panel_1);
			panel_1.setLayout(null);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnActualizar = new JButton("Actualizar");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegCita uptade = new RegCita(false, true, selectedCita);
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
						filterCitasByDate();

					}
				});
				datePickerCita.getJFormattedTextField().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						filterCitasByDate();
					}
				});
				datePickerCita.setBounds(506, 16, 126, 27);
				panel_1.add(datePickerCita);
				
			}
			{
				
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selectedCita != null) {
							int option = JOptionPane.showConfirmDialog(null,
									"Esta seguro que desea eliminar la consulta con codigo: "
											+ selectedCita.getCodigo(),
									"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION) {
								Clinica.getInstance().eliminarCita(selectedCita);
								loadCitas();
							}
						}
					}

				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("Cancel");
				buttonPane.add(btnEliminar);
				 JButton btnMostrarTodas = new JButton("Mostrar Todas");
			        btnMostrarTodas.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	   citasFiltradas.clear(); // Limpiar la lista de citas filtradas
			                   loadCitas(); // Cargar todas las citas sin filtrar en la tabla	
			                   }
			        });
			        buttonPane.add(btnMostrarTodas);
			    
			}
			{
				JButton btnNewButton = new JButton("Cancelar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnNewButton);
			    if (esDoctor && doctor != null) {
			        // Si esDoctor es true y el doctor es diferente de null, filtrar las citas por el doctor
			        loadCitasByDoctor(doctor);
			    } else {
			        // Si no, cargar todas las citas
			        loadCitas();
			    }
			}
		}
	}



	public static void loadCitas() {
	    if (Clinica.getInstance() == null || Clinica.getInstance().getMisCitas() == null) {
	        return; 
	    }

	    modelo.setRowCount(0);
	    row = new Object[table.getColumnCount()];

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    for (Cita cita : Clinica.getInstance().getMisCitas()) {
	        if (cita != null && cita.getMiPersona() != null && cita.getMiDoctor() != null) {
	            row[0] = cita.getCodigo();
	            row[1] = cita.getMiPersona().getNombre();
	            row[2] = cita.getMiDoctor().getNombre();
	            row[3] = dateFormat.format(cita.getFecha()); // Formatea la fecha antes de agregarla al row
	            modelo.addRow(row);
	        }
	    }
	}

	private void filterCitasByDate() {
	    if (Clinica.getInstance() == null || Clinica.getInstance().getMisCitas() == null) {
	        return; 
	    }

	    citasFiltradas.clear(); // Limpiar la lista de citas filtradas

	    java.util.Date selectedDate = (java.util.Date) datePickerCita.getModel().getValue();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String selectedFecha = dateFormat.format(selectedDate);

	    for (Cita cita : Clinica.getInstance().getMisCitas()) {
	        if (cita != null && cita.getMiPersona() != null && cita.getMiDoctor() != null) {
	            String citaFecha = dateFormat.format(cita.getFecha());
	            if (citaFecha.equals(selectedFecha)) {
	                citasFiltradas.add(cita); // Agregar la cita filtrada a la lista
	            }
	        }
	    }

	    loadCitasFiltradas(); // Cargar las citas filtradas en la tabla
	}
	private void loadCitasFiltradas() {
	    modelo.setRowCount(0);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    for (Cita cita : citasFiltradas) {
	        if (cita != null && cita.getMiPersona() != null && cita.getMiDoctor() != null) {
	            Object[] row = {
	                cita.getCodigo(),
	                cita.getMiPersona().getNombre(),
	                cita.getMiDoctor().getNombre(),
	                dateFormat.format(cita.getFecha())
	            };
	            modelo.addRow(row);
	        }
	    }
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
		            datePickerCita.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDate));

		        }
		    });

		    return new JDatePickerImpl(datePanel, null);
		}
	 private void loadCitasByDoctor(Doctor doctor) {
		    if (Clinica.getInstance() == null || Clinica.getInstance().getMisCitas() == null) {
		        return;
		    }

		    citasFiltradas.clear(); // Limpiar la lista de citas filtradas

		    java.util.Date selectedDate = (java.util.Date) datePickerCita.getModel().getValue();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String selectedFecha = dateFormat.format(selectedDate);

		    for (Cita cita : Clinica.getInstance().getMisCitas()) {
		        if (cita != null && cita.getMiPersona() != null && cita.getMiDoctor() != null) {
		            String citaFecha = dateFormat.format(cita.getFecha());
		            if (citaFecha.equals(selectedFecha) && cita.getMiDoctor().equals(doctor)) {
		                citasFiltradas.add(cita); // Agregar la cita filtrada a la lista
		            }
		        }
		    }

		    loadCitasFiltradas(); // Cargar las citas filtradas en la tabla
		}

}

