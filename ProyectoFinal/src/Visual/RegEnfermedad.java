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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class RegEnfermedad extends JDialog {

	private JPanel contentPane;
	private JButton btnRegistrar;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextArea textAreaDetalles;
	private JComboBox comboGravedad;
	private Enfermedad miEnfermedad = null;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;


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
		setBounds(100, 100, 457, 472);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 451, 432);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(15, 16, 69, 20);
		panel.add(lblNewLabel);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(15, 37, 146, 26);
		textCodigo.setEditable(false);
		textCodigo.setText("E-" + Clinica.codEfermedad);
		panel.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(15, 79, 69, 20);
		panel.add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(15, 100, 146, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Detalles de la enfermedad");
		lblNewLabel_2.setBounds(128, 163, 212, 26);
		panel.add(lblNewLabel_2);
		
		textAreaDetalles = new JTextArea();
		textAreaDetalles.setBounds(112, 199, 245, 159);
		panel.add(textAreaDetalles);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(242, 387, 115, 29);
		panel.add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(112, 387, 115, 29);
		panel.add(btnCancelar);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 151, 443, 220);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 369, 451, 63);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 443, 147);
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		comboGravedad = new JComboBox();
		comboGravedad.setBounds(189, 37, 239, 26);
		panel_3.add(comboGravedad);
		comboGravedad.setModel(new DefaultComboBoxModel(new String[] {"<Gravedad de la Enfermedad>", "Leve", "Moderada", "Alta"}));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
	            JOptionPane.showMessageDialog(null, "El código ya está en uso por otra enfermedad.", "Error de registro",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        return true;
	    }

}