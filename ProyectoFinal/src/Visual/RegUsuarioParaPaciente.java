package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.Clinica;
import Logic.Paciente;
import Logic.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegUsuarioParaPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Paciente selectedPaciente = null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public RegUsuarioParaPaciente(Paciente miPaciente) {
		selectedPaciente = miPaciente;
		setBounds(100, 100, 612, 435);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(15, 16, 400, 297);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Usuario\r\n");
				lblNewLabel.setBounds(15, 16, 69, 20);
				panel.add(lblNewLabel);
			}
			{
				txtUsuario = new JTextField();
				txtUsuario.setBounds(15, 51, 146, 26);
				panel.add(txtUsuario);
				txtUsuario.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a\r\n");
				lblNewLabel_1.setBounds(15, 93, 101, 20);
				panel.add(lblNewLabel_1);
			}
			{
				passwordField = new JPasswordField();
				passwordField.setBounds(15, 129, 146, 26);
				panel.add(passwordField);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Confirmar Contrase\u00F1a\r\n");
				lblNewLabel_2.setBounds(25, 171, 155, 20);
				panel.add(lblNewLabel_2);
			}
			{
				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(15, 194, 136, 26);
				panel.add(passwordField_1);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Usuario aux = new Usuario(selectedPaciente, txtUsuario.getText(), 
								passwordField.getText(), true, false);
						Clinica.getInstance().agregarUsuario(aux);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
