package Punto_de_venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Actualizar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static JTextField BARRA = new JTextField();
	static JTextField Nombre = new JTextField();
	static JTextField Precio = new JTextField();
	static Actualizar actualizar = new Actualizar();
	static JTextField Cantidad;
	private JTable tableforModificar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			actualizar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			actualizar.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Actualizar() {
		setTitle("ACTUALIZAR DATOS");
		setBounds(100, 100, 435, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel bARRA = new JLabel("CODIGO_BARRA");
		bARRA.setFont(new Font("Aharoni", Font.PLAIN, 13));
		bARRA.setForeground(Color.WHITE);
		bARRA.setBounds(94, 24, 147, 14);
		contentPanel.add(bARRA);
		BARRA.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		BARRA.setBounds(56, 49, 216, 20);
		contentPanel.add(BARRA);
		BARRA.setColumns(10);
		BARRA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if (validarProductos(BARRA.getText())) {
						ConexionTableModel ctm=new ConexionTableModel("select * from productos where CODIGO_BARRA='"+BARRA.getText()+"';");
						tableforModificar.setModel(ctm.getTablemodel());
						String codigo = tableforModificar.getValueAt(0, 0).toString();
						String cantidad=tableforModificar.getValueAt(0, 1).toString();
						String descripcion = tableforModificar.getValueAt(0, 2).toString();
						String precio=tableforModificar.getValueAt(0, 3).toString();
						Actualizar.BARRA.setText(""+codigo);
						Actualizar.Nombre.setText(""+descripcion);
						Actualizar.Cantidad.setText(""+cantidad);
						Actualizar.Precio.setText(""+precio);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblIn = new JLabel("NOMBRE");
		lblIn.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lblIn.setForeground(Color.WHITE);
		lblIn.setBounds(10, 94, 58, 14);
		contentPanel.add(lblIn);
		Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		Nombre.setBounds(83, 91, 216, 20);
		contentPanel.add(Nombre);
		Nombre.setColumns(10);
		
		JLabel lblPrecio = new JLabel("PRECIO");
		lblPrecio.setFont(new Font("Aharoni", Font.PLAIN, 11));
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setBounds(10, 193, 58, 14);
		contentPanel.add(lblPrecio);
		Precio.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		Precio.setBounds(83, 190, 216, 20);
		contentPanel.add(Precio);
		Precio.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Cantidad.getText().equals("") || Nombre.getText().equals("") || Precio.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"VERIFICA QUE LOS CAMPO ESTEN LLENOS","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				}
				String Usuario="Alejandro";
				String Contraseña="12345";
				String URL="jdbc:mysql://localhost/tienda2015";
				
			    java.sql.Connection conn=null;
			    Statement stmnt=null;
			    ResultSet rs=null;
			    try {
			    	conn=DriverManager.getConnection(URL,Usuario,Contraseña);
					stmnt=conn.createStatement();
					//para insertar 
					stmnt.executeUpdate("UPDATE `tienda2015`.`productos` SET  `CANTIDAD` =  '"+Cantidad.getText()+"',`DESCRIPCION` =  '"+Nombre.getText()+"',`PRECIO_UNITARIO` =  '"+Precio.getText()+"' WHERE  `productos`.`CODIGO_BARRA` ="+BARRA.getText()+";");
					ConexionTableModel ctm=new ConexionTableModel("select * from productos");
					PuntoDeVenta.JTResultado1.setModel(ctm.getTablemodel());
					Nombre.setText("");
					Precio.setText("");
					Cantidad.setText("");
					BARRA.setText("");
					PuntoDeVenta.CodigoBarra.setText("");
					actualizar.setVisible(false);
					JOptionPane.showMessageDialog(null, "SE A ACTUALIZADO CORRECTAMENTE EL PRODUCTO");  
					ConexionTableModel ctm2=new ConexionTableModel("select CANTIDAD,DESCRIPCION from productos WHERE CANTIDAD='0'");
					ProductosAgotados.ProductosAgotados.setModel(ctm2.getTablemodel());
					
				} catch (SQLException e1){
					e1.printStackTrace();
				}catch (ClassCastException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}finally
				{
					if(rs!=null)
						try {
							rs.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					if(conn!=null)
						try {
							conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					if(conn !=null)
						try {
							conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
			
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Program Files\\Abarrotes El Atoron\\Imagenes\\editar.jpg"));
		btnNewButton.setBounds(309, 84, 89, 123);
		contentPanel.add(btnNewButton);
		
		JLabel lblCantidad = new JLabel("CANTIDAD:");
		lblCantidad.setFont(new Font("Aharoni", Font.PLAIN, 12));
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setBounds(10, 148, 75, 14);
		contentPanel.add(lblCantidad);
		
		Cantidad = new JTextField();
		Cantidad.setHorizontalAlignment(SwingConstants.CENTER);
		Cantidad.setBounds(83, 145, 216, 20);
		contentPanel.add(Cantidad);
		Cantidad.setColumns(10);
		
		tableforModificar = new JTable();
		tableforModificar.setBounds(379, 56, 5, 2);
		try {
			ConexionTableModel ctm=new ConexionTableModel("select * from productos");
			tableforModificar.setModel(ctm.getTablemodel());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		contentPanel.add(tableforModificar);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 419, 243);
			label.setIcon(new ImageIcon("C:\\Program Files\\Abarrotes El Atoron\\Imagenes\\fondo_inicio.jpg"));
			contentPanel.add(label);
		}
	}
	boolean validarProductos(String des)  throws IOException
    {
    	try
		{
    		java.sql.Connection conn=null;
    	     Statement stmnt=null;
    	    ResultSet rs=null;
    	    String Usuario="Alejandro";
    		String Contraseña="12345";
    		String URL="jdbc:mysql://localhost/tienda2015";
    	    conn=(Connection) DriverManager.getConnection(URL,Usuario,Contraseña);
    	    stmnt=conn.createStatement();
    	    	 ResultSet resultadosConsulta = stmnt.executeQuery ("SELECT * FROM productos WHERE CODIGO_BARRA='"+des+"'");
    	    	  if( resultadosConsulta.first() )        
    	                return true;        
    	            else
    	                return false;                 
		} catch (Exception e)
		{
   			e.printStackTrace();
            return false;
		}
    }
}