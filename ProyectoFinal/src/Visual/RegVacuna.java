package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import Logic.Clinica;
import Logic.Vacuna;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class RegVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtLote;
	private JTextField txtDistribuidor;
	private JSpinner spnEdad;
	private JSpinner spnCant;
	private Vacuna miVacuna = null;




	/**
	 * Create the dialog.
	 * @param selectedVacuna 
	 */
	public RegVacuna(Vacuna selectedVacuna) {
		miVacuna = selectedVacuna;
		if(miVacuna == null) {
			
			setTitle("Registrar Vacuna");
		
		}
		else {
			setTitle("Modificar Vacuna");
		}
		setBounds(100, 100, 450, 336);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(12, 13, 408, 117);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(12, 18, 56, 16);
		panel.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(60, 15, 116, 22);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(222, 15, 56, 16);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(280, 12, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descripcion:");
		lblNewLabel_2.setBounds(12, 50, 80, 16);
		panel.add(lblNewLabel_2);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(12, 66, 384, 38);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(12, 143, 408, 93);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		txtLote = new JTextField();
		txtLote.setBounds(109, 16, 116, 22);
		panel_1.add(txtLote);
		txtLote.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Codigo del Lote:");
		lblNewLabel_5.setBounds(12, 20, 98, 16);
		panel_1.add(lblNewLabel_5);
		
		spnCant = new JSpinner();
		spnCant.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spnCant.setBounds(318, 16, 78, 22);
		panel_1.add(spnCant);
		
		JLabel lblNewLabel_4 = new JLabel("Cantidad:");
		lblNewLabel_4.setBounds(253, 19, 56, 16);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblDistribiudor = new JLabel("Distribiudor:");
		lblDistribiudor.setBounds(12, 56, 85, 16);
		panel_1.add(lblDistribiudor);
		
		txtDistribuidor = new JTextField();
		txtDistribuidor.setColumns(10);
		txtDistribuidor.setBounds(91, 54, 116, 22);
		panel_1.add(txtDistribuidor);
		
		spnEdad = new JSpinner();
		spnEdad.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spnEdad.setBounds(348, 51, 48, 22);
		panel_1.add(spnEdad);
		
		JLabel lblNewLabel_3 = new JLabel("Edad Requerida:");
		lblNewLabel_3.setBounds(243, 54, 107, 16);
		panel_1.add(lblNewLabel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(miVacuna == null) {
							
						
						if(!txtCodigo.equals("") && !txtNombre.equals("") && !txtDescripcion.equals("") && !txtLote.equals("") && !txtDistribuidor.equals("")) {
							Vacuna vacuna = new Vacuna(
									txtCodigo.getText(),
									txtNombre.getText(),
									txtDescripcion.getText(),
									Integer.parseInt(spnEdad.getValue().toString()),
									Integer.parseInt(spnCant.getValue().toString()),
									txtLote.getText(),
									txtDistribuidor.getText());
							Clinica.getInstance().insertarVacuna(vacuna);
							JOptionPane.showMessageDialog(null, "Registro de Vacuna Exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
							

						}else {
							JOptionPane.showMessageDialog(null, "Favor llenar todos los campos", "ERROR", JOptionPane.INFORMATION_MESSAGE);
						}
						}
						else {
							miVacuna.setCodigo(txtCodigo.getText());
							miVacuna.setNombre(txtNombre.getText());
							miVacuna.setDescripcion(txtDescripcion.getText());
							miVacuna.setEdadRequerida(Integer.parseInt(spnEdad.getValue().toString()));
							miVacuna.setCantidadDisponible(Integer.parseInt(spnCant.getValue().toString()));
							miVacuna.setCodLote(txtLote.getText());
							miVacuna.setDistribuidor(txtDistribuidor.getText());
							
							Clinica.getInstance().uptadeVacuna(miVacuna);
							dispose();
							ListarVacuna.loadVacunas();
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				loadVacuna();
			}
		}
	}




	private void loadVacuna() {
		if(miVacuna != null) {
			txtCodigo.setText(miVacuna.getCodigo());
			txtNombre.setText(miVacuna.getNombre());
			txtDescripcion.setText(miVacuna.getDistribuidor());
			txtDistribuidor.setText(miVacuna.getDistribuidor());
			txtLote.setText(miVacuna.getCodLote());
			spnEdad.setValue(miVacuna.getEdadRequerida());
			spnCant.setValue(miVacuna.getCantidadDisponible());
			
		}
		else {
			// TODO nothing;
		}
		
	}
}
