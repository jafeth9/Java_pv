package Punto_de_venta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;


import java.awt.Color;

public class Invetarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTable TablaInvetario;
	static Invetarios dialog = new Invetarios();
	Statement stmnt=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Invetarios() {
setTitle("INVENTARIO");
		
		String Usuario="Alejandro";
		String Contrasenia="12345";
		String URL="jdbc:mysql://localhost/tienda2015";
		
	    java.sql.Connection conn=null;
	    
	    ResultSet rs=null;
	    try {
			conn=DriverManager.getConnection(URL,Usuario,Contrasenia);
			stmnt=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setBounds(100, 100, 440, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 397, 387);
		contentPane.add(scrollPane);
		
		TablaInvetario = new JTable();
		scrollPane.setViewportView(TablaInvetario);
		

		try {
			ConexionTableModel ctm = new ConexionTableModel("select CANTIDAD,DESCRIPCION from productos");
			TablaInvetario.setModel(ctm.getTablemodel());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblInventario = new JLabel("INVENTARIO");
		lblInventario.setForeground(Color.RED);
		lblInventario.setBackground(Color.RED);
		lblInventario.setFont(new Font("Aharoni", Font.PLAIN, 17));
		lblInventario.setBounds(168, 11, 164, 19);
		contentPane.add(lblInventario);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Program Files\\Abarrotes El Atoron\\Imagenes\\fondo_inicio.jpg"));
		label.setBounds(0, 0, 424, 451);
		contentPane.add(label);
	}

}
