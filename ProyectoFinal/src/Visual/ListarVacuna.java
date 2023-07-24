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

import Logic.Clinica;
import Logic.Enfermedad;
import Logic.Vacuna;

public class ListarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private Vacuna selectedVacuna = null;
	private static DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnActualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarVacuna dialog = new ListarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarVacuna() {
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
								selectedVacuna = Clinica.getInstance().buscarVacunaByCode(table.getValueAt(index, 0)
										.toString());
							}
							
						}
					});
					
					modelo = new DefaultTableModel();
					String[] headers = {"Código", "Nombre", "Cantidad en existencia"};
					scrollPane.setViewportView(table);
					modelo.setColumnIdentifiers(headers);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
					loadVacunas();			}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Cancelar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnNewButton);
			}
			{
				btnActualizar = new JButton("Actualizar");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegVacuna uptade = new RegVacuna(selectedVacuna);
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
						if (selectedVacuna != null) {
							int option = JOptionPane.showConfirmDialog(null,
									"Esta seguro que desea eliminar la Vacuna con código "
											+ selectedVacuna.getCodigo(),
									"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION) {
								Clinica.getInstance().eliminarVacuna(selectedVacuna);
								loadVacunas();
							}
						}
					}
						
					
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("Cancel");
				buttonPane.add(btnEliminar);
			}
		}
	}

	public static void loadVacunas() {
		modelo.setRowCount(0);
		row = new Object[table.getColumnCount()];
		
		for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
			
				row[0] = vacuna.getCodigo();
				row[1] = vacuna.getNombre();
				row[2] = vacuna.getCantidadDisponible();
				modelo.addRow(row);
				
			
		}
			
		
	}

}
