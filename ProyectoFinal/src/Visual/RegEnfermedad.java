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
import javax.swing.SwingConstants;

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
	private JPanel panel_5;
	private JPanel panel_4;


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
		setBounds(100, 100, 568, 516);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 562, 505);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(0, 134, 562, 292);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textAreaDetalles = new JTextArea();
		textAreaDetalles.setBounds(159, 70, 245, 159);
		panel_1.add(textAreaDetalles);
		
		JLabel lblNewLabel_2 = new JLabel("Detalles de la enfermedad");
		lblNewLabel_2.setBounds(176, 36, 212, 26);
		panel_1.add(lblNewLabel_2);
		
		panel_5 = new JPanel();
		panel_5.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.setBounds(43, 16, 479, 243);
		panel_1.add(panel_5);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 428, 562, 50);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnRegistrar);
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
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(0, 0, 562, 147);
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		panel_4 = new JPanel();
		panel_4.setBorder(null);
		panel_4.setBounds(0, 0, 562, 131);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		comboGravedad = new JComboBox();
		comboGravedad.setBounds(145, 71, 239, 26);
		panel_4.add(comboGravedad);
		comboGravedad.setModel(new DefaultComboBoxModel(new String[] {"<Gravedad de la Enfermedad>", "Leve", "Moderada", "Alta"}));
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(407, 8, 69, 20);
		panel_4.add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(330, 29, 146, 26);
		panel_4.add(textNombre);
		textNombre.setColumns(10);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(45, 29, 146, 26);
		panel_4.add(textCodigo);
		textCodigo.setEditable(false);
		textCodigo.setText("E-" + Clinica.codEfermedad);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(45, 8, 69, 20);
		panel_4.add(lblNewLabel);
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