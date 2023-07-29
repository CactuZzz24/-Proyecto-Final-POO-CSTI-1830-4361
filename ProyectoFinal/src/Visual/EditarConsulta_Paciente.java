package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Enfermedad;

import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditarConsulta_Paciente extends JDialog {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textFecha;
	private JTextField textNombre;
	private JTextField textGender;
	private JTextField textEdad;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private DefaultTableModel enfermedadesTableModel;
	private Enfermedad selectedEnfermedad;
	private Consulta miConsulta;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public EditarConsulta_Paciente(Consulta cons) {
		miConsulta = cons;
		loadDatos();
		setResizable(false);
		setBounds(100, 100, 677, 503);
		setLocationRelativeTo(null);		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(45, 28, 588, 391);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0, 0, 588, 391);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(15, 16, 69, 20);
		panel.add(lblNewLabel);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(15, 52, 146, 26);
		panel.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(533, 16, 40, 20);
		panel.add(lblNewLabel_1);
		
		textFecha = new JTextField();
		textFecha.setBounds(427, 52, 146, 26);
		panel.add(textFecha);
		textFecha.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(15, 94, 558, 281);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre Completo");
		lblNewLabel_2.setBounds(15, 16, 136, 20);
		panel_1.add(lblNewLabel_2);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(15, 52, 146, 26);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Genero");
		lblNewLabel_3.setBounds(492, 16, 51, 20);
		panel_1.add(lblNewLabel_3);
		
		textGender = new JTextField();
		textGender.setEditable(false);
		textGender.setText("");
		textGender.setBounds(397, 52, 146, 26);
		panel_1.add(textGender);
		textGender.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Edad");
		lblNewLabel_4.setBounds(258, 16, 69, 20);
		panel_1.add(lblNewLabel_4);
		
		textEdad = new JTextField();
		textEdad.setEditable(false);
		textEdad.setText("");
		textEdad.setBounds(211, 52, 146, 26);
		panel_1.add(textEdad);
		textEdad.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(15, 146, 267, 119);
		panel_1.add(textArea);
		
		JLabel lblNewLabel_5 = new JLabel("Observaciones");
		lblNewLabel_5.setBounds(15, 110, 124, 20);
		panel_1.add(lblNewLabel_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(396, 120, 147, 145);
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
                if (index >= 0) {
                	selectedEnfermedad = Clinica.getInstance().buscarEnfermedadByCode(table.
                			getValueAt(index,0 ).toString());
                }
			}
		});
		enfermedadesTableModel = new DefaultTableModel();
		String[] enfermedadHeaders = {"Codigo", "Nombre", "Gravedad"};
		enfermedadesTableModel.setColumnIdentifiers(enfermedadHeaders);
		table.setModel(enfermedadesTableModel);
		loadEnfermedades();
		scrollPane.setViewportView(table);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(526, 426, 107, 21);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(401, 426, 107, 21);
		contentPane.add(btnCancelar);
	}

	private void loadDatos() {
		// TODO Auto-generated method stub
		textNombre.setText(miConsulta.getMiPersona().getNombre());
		// rellenar demas campos
		
	}

	private void loadEnfermedades() {
		// TODO Auto-generated method stub
		ArrayList<Enfermedad> misEnfermedades = Clinica.getInstance().getMisEnfermedades();
		enfermedadesTableModel.setRowCount(0);
		for (Enfermedad enfermedad : misEnfermedades) {
			Object[] row = {enfermedad.getCodigo(), enfermedad.getNombre(), enfermedad.getGravedad()};
			enfermedadesTableModel.addRow(row);
			
		}
		
	}
}