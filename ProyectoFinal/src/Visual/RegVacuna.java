package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

public class RegVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtLote;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacuna dialog = new RegVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVacuna() {
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 450, 336);
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
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(318, 16, 78, 22);
		panel_1.add(spinner_1);
		
		JLabel lblNewLabel_4 = new JLabel("Cantidad:");
		lblNewLabel_4.setBounds(253, 19, 56, 16);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblDistribiudor = new JLabel("Distribiudor:");
		lblDistribiudor.setBounds(12, 56, 85, 16);
		panel_1.add(lblDistribiudor);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(91, 54, 116, 22);
		panel_1.add(textField);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(348, 51, 48, 22);
		panel_1.add(spinner);
		
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
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
