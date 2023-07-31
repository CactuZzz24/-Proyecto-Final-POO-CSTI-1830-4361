package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.omg.CORBA.TRANSACTION_REQUIRED;

import Logic.Clinica;
import Logic.Consulta;
import Logic.Enfermedad;
import Logic.Persona;
import Logic.Usuario;

public class EditarConsulta_Medico extends JDialog {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textGender;
	private JTextField textEdad;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private DefaultTableModel enfermedadesTableModel;
	private Enfermedad selectedEnfermedad;
	private Consulta miConsulta;
	private JDatePickerImpl datePicker;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public EditarConsulta_Medico(Consulta cons) {
		setTitle("Detalles de la Consulta");
		miConsulta = cons;
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
		
		datePicker = createDatePicker();
	    datePicker.setBounds(427, 52, 146, 26);
	    panel.add(datePicker);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(15, 16, 69, 20);
		panel.add(lblNewLabel);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setBounds(15, 52, 146, 26);
		panel.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(533, 16, 40, 20);
		panel.add(lblNewLabel_1);
		
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
		
		JTextArea textObservaciones = new JTextArea();
		textObservaciones.setBounds(15, 146, 267, 119);
		panel_1.add(textObservaciones);
		
		JLabel lblNewLabel_5 = new JLabel("Observaciones");
		lblNewLabel_5.setBounds(15, 110, 124, 20);
		panel_1.add(lblNewLabel_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(327, 120, 216, 145);
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
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miConsulta.setObservaciones(textObservaciones.getText());
				miConsulta.setFecha((java.util.Date) datePicker.getModel().getValue());
				Clinica.getInstance().modificarConsulta(miConsulta);
				
				int option = JOptionPane.showConfirmDialog(null,
						"¿Quiere colocar el sujeto como paciente?",
						"Confirmación", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					RegUsuario reg = new RegUsuario(true, false, Clinica.getInstance().buscarUsuarioByPersona(miConsulta.getMiPersona()));
					reg.setModal(true);
					reg.setVisible(true);
					
				}
				else {
					
				}
				
       		 JOptionPane.showMessageDialog(null, "Registro exitoso", "Registro",
                     JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
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
		loadDatos();

	}

	private void loadDatos() {
		   textNombre.setText(miConsulta.getMiPersona().getNombre());
		   UtilDateModel modelNacim = (UtilDateModel) datePicker.getModel();
           modelNacim.setValue(miConsulta.getFecha());
           datePicker.getJFormattedTextField().setText
           (new SimpleDateFormat("yyyy-MM-dd").format(miConsulta.getFecha()));
           textEdad.setText(edad());
           textCodigo.setText(miConsulta.getCodigo());
           textGender.setText(String.valueOf(miConsulta.getMiPersona().getGenero()));
           
	}

	private String edad() {
	    Date fechaNacimiento = miConsulta.getMiPersona().getFchNacim();

	    Calendar fechaActual = Calendar.getInstance();

	    Calendar fechaNacimientoCalendar = Calendar.getInstance();
	    fechaNacimientoCalendar.setTime(fechaNacimiento);

	    int edad = fechaActual.get(Calendar.YEAR) - fechaNacimientoCalendar.get(Calendar.YEAR);
	    if (fechaActual.get(Calendar.MONTH) < fechaNacimientoCalendar.get(Calendar.MONTH) ||
	        (fechaActual.get(Calendar.MONTH) == fechaNacimientoCalendar.get(Calendar.MONTH) &&
	         fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNacimientoCalendar.get(Calendar.DAY_OF_MONTH))) {
	        edad--;
	    }

	    return String.valueOf(edad);
	}

	private void loadEnfermedades() {
		ArrayList<Enfermedad> misEnfermedades = Clinica.getInstance().getMisEnfermedades();
		enfermedadesTableModel.setRowCount(0);
		for (Enfermedad enfermedad : misEnfermedades) {
			Object[] row = {enfermedad.getCodigo(), enfermedad.getNombre(), enfermedad.getGravedad()};
			enfermedadesTableModel.addRow(row);
			
		}
		
	}
	private JDatePickerImpl createDatePicker() {
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
	            datePicker.getJFormattedTextField().setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDate));
	        }
	    });

	    return new JDatePickerImpl(datePanel, null);
	}
	
}