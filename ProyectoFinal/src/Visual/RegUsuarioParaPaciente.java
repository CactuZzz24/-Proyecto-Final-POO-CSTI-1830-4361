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
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

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
		setResizable(false);
		selectedPaciente = miPaciente;
		setBounds(100, 100, 254, 384);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(95, 158, 160));
			panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			panel.setBounds(0, 0, 274, 304);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel_1.setBounds(29, 16, 188, 274);
			panel.add(panel_1);
			panel_1.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Usuario\r\n");
				lblNewLabel.setBounds(58, 29, 69, 20);
				panel_1.add(lblNewLabel);
			}
			{
				txtUsuario = new JTextField();
				txtUsuario.setBounds(19, 64, 146, 26);
				panel_1.add(txtUsuario);
				txtUsuario.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a\r\n");
				lblNewLabel_1.setBounds(42, 106, 101, 20);
				panel_1.add(lblNewLabel_1);
			}
			{
				passwordField = new JPasswordField();
				passwordField.setBounds(19, 142, 146, 26);
				panel_1.add(passwordField);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Confirmar Contrase\u00F1a\r\n");
				lblNewLabel_2.setBounds(15, 184, 155, 20);
				panel_1.add(lblNewLabel_2);
			}
			{
				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(24, 220, 136, 26);
				panel_1.add(passwordField_1);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				{
				    JButton okButton = new JButton("Registrar");
				    okButton.addActionListener(new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
				            String username = txtUsuario.getText();
				            String password = new String(passwordField.getPassword());
				            String confirmPassword = new String(passwordField_1.getPassword());

				            if (password.length() < 8) {
				                JOptionPane.showMessageDialog(contentPanel, "La contraseña debe tener al menos 8 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
				            } else if (!password.equals(confirmPassword)) {
				                JOptionPane.showMessageDialog(contentPanel, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
				            } else {
				                Usuario aux = new Usuario(selectedPaciente, username, password, true, false);
				                Clinica.getInstance().agregarUsuario(aux);
				                dispose();
				            }
				        }
				    });
				    okButton.setActionCommand("OK");
				    buttonPane.add(okButton);
				    getRootPane().setDefaultButton(okButton);
				}

			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
