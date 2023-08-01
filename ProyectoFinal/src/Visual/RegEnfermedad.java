package Visual;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Logic.Clinica;
import Logic.Enfermedad;

public class RegEnfermedad extends JDialog {

	private JPanel contentPane;
	private JButton btnRegistrar;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextArea textAreaDetalles;
	private JComboBox comboGravedad;
	private Enfermedad miEnfermedad = null;


	/**
	 * Create the frame.
	 */
	public RegEnfermedad(Enfermedad enf) {
		
		setResizable(false);
		miEnfermedad = enf;

		if(miEnfermedad == null) {
			setTitle("Registrar Enfermedad");
		}
		else {
			setTitle("Editar Enfermedad");

		}
		setBounds(100, 100, 639, 472);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 32, 530, 322);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(15, 16, 69, 20);
		panel.add(lblNewLabel);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setText("E-" + Clinica.codEfermedad);
		textCodigo.setBounds(15, 37, 146, 26);
		panel.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(446, 16, 69, 20);
		panel.add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(369, 37, 146, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Detalles de la enfermedad");
		lblNewLabel_2.setBounds(15, 172, 428, 26);
		panel.add(lblNewLabel_2);
		
		textAreaDetalles = new JTextArea();
		textAreaDetalles.setBounds(15, 208, 500, 98);
		panel.add(textAreaDetalles);
		
		comboGravedad = new JComboBox();
		comboGravedad.setModel(new DefaultComboBoxModel(new String[] {"<Gravedad de la Enfermedad>", "Leve", "Moderada", "Alta"}));
		comboGravedad.setBounds(264, 97, 251, 26);
		
		panel.add(comboGravedad);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validateFields()) {
					return;
				}
				if(miEnfermedad == null) {
					
				
				String codigo = textCodigo.getText();
				String nombre = textNombre.getText();
				String desc = textAreaDetalles.getText();
				String gravedad = (String) comboGravedad.getSelectedItem();
				
				Enfermedad enfermedad = new Enfermedad(codigo, nombre, desc, gravedad);
				Clinica.getInstance().agregarEnfermedad(enfermedad);
			}
				else {
	
					miEnfermedad.setCodigo(textCodigo.getText());
					miEnfermedad.setNombre(textNombre.getText());
					miEnfermedad.setGravedad((String)comboGravedad.getSelectedItem());
					miEnfermedad.setDescripcion(textAreaDetalles.getText());
					Clinica.getInstance().modificarEnfermedad(miEnfermedad);
					dispose();
					ListarEnfermedad.loadEnfermedades();
					
					
				}
			    JOptionPane.showMessageDialog(null, "Registro exitoso", "Registro",
                        JOptionPane.INFORMATION_MESSAGE);
                clear();
			}

		
			
		});
		btnRegistrar.setBounds(460, 371, 115, 29);
		contentPane.add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(55, 370, 115, 29);
		contentPane.add(btnCancelar);
		loadEnf();

	}
	private void loadEnf() {
	    if (miEnfermedad != null) {
	        textCodigo.setText(miEnfermedad.getCodigo());
	        textNombre.setText(miEnfermedad.getNombre());
	        textAreaDetalles.setText(miEnfermedad.getDescripcion());

	        String gravedad = miEnfermedad.getGravedad();
	        comboGravedad.setSelectedItem(gravedad);
	    } else {

	    }
	}


	private void clear() {
		textCodigo.setText("");
		textNombre.setText("");
		textAreaDetalles.setText("");
		comboGravedad.setSelectedIndex(0);
	}
	 private boolean validateFields() {
	        String codigo = textCodigo.getText().trim();
	        String nombre = textNombre.getText().trim();
	        String detalles = textAreaDetalles.getText().trim();
	        String gravedad = (String) comboGravedad.getSelectedItem();

	        if (codigo.isEmpty() || nombre.isEmpty() || detalles.isEmpty() || gravedad.equals("<Gravedad de la Enfermedad>")) {
	            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error de registro",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	        if (miEnfermedad == null && Clinica.getInstance().buscarEnfermedadByCode(codigo) != null) {
	            JOptionPane.showMessageDialog(null, "El c�digo ya est� en uso por otra enfermedad.", "Error de registro",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        return true;
	    }

}