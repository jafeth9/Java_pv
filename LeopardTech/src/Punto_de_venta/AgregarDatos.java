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
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AgregarDatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static AgregarDatos dialog = new AgregarDatos();
	private JTextField producto;
	private JTextField precio;
	private JTextField CANTIDAD;
	private JTextField Codigo_BarraProductos;

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
	public AgregarDatos() {
		setTitle("Agregar Producto");
		setBounds(100, 100, 281, 269);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblIngreseProducto = new JLabel("INGRESE PRODUCTO:");
		lblIngreseProducto.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lblIngreseProducto.setForeground(Color.WHITE);
		
		lblIngreseProducto.setBounds(10, 68, 141, 14);
		contentPanel.add(lblIngreseProducto);
		
		producto = new JTextField();
		producto.setHorizontalAlignment(SwingConstants.CENTER);
		producto.setBounds(10, 93, 131, 20);
		contentPanel.add(producto);
		producto.setColumns(10);
		
		JLabel lblIngresePrecio = new JLabel("INGRESE PRECIO:");
		lblIngresePrecio.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lblIngresePrecio.setBounds(10, 180, 131, 14);
		lblIngresePrecio.setForeground(Color.WHITE);
		contentPanel.add(lblIngresePrecio);
		
		precio = new JTextField();
		precio.setHorizontalAlignment(SwingConstants.CENTER);
		precio.setBounds(10, 205, 131, 20);
		contentPanel.add(precio);
		precio.setColumns(10);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Usuario="Alejandro";
				String Contrasenia="12345";
				String URL="jdbc:mysql://localhost/tienda2015";
				
			    java.sql.Connection conn=null;
			    Statement stmnt=null;
			    ResultSet rs=null;
			    
			    if (producto.getText().equals("") || precio.getText().equals("")||CANTIDAD.getText().equals("")||Codigo_BarraProductos.getText().equals("")) {
					 JOptionPane.showMessageDialog(null,"DATOS INCOMPLETOS ASEGURE QUE TODO LOS CAMPOS ESTEN RELLENOS","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			    }
				else
				{
				try{
				conn=DriverManager.getConnection(URL,Usuario,Contrasenia);
				stmnt=conn.createStatement();
				//para insertar 
				if (validarProductos(Codigo_BarraProductos.getText(),producto.getText()) || validarProductos1(Codigo_BarraProductos.getText()) ||  validarProductos2(producto.getText()))  {
					JOptionPane.showMessageDialog(null,"EL PRODUCTO YA EXISTE","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
					producto.setText("");
					precio.setText("");
					CANTIDAD.setText("");
					Codigo_BarraProductos.setText("");
				}else{
				stmnt.executeUpdate("insert into productos(CODIGO_BARRA,CANTIDAD,DESCRIPCION,PRECIO_UNITARIO)values(\'"+Codigo_BarraProductos.getText()+"\',\'"+CANTIDAD.getText()+"\',\'"+producto.getText()+"\',"+precio.getText()+");");
				ConexionTableModel ctm=new ConexionTableModel("select * from productos");
				PuntoDeVenta.JTResultado1.setModel(ctm.getTablemodel());
				JOptionPane.showMessageDialog(null, "EL PRODUCTO FUE AGREGADO CON EXITO");
				producto.setText("");
				precio.setText("");
				CANTIDAD.setText("");
				Codigo_BarraProductos.setText("");
				AgregarDatos.dialog.setVisible(false);
				
				}
				//para borrar
				//stmnt.executeUpdate("delete from productos where id_producto=12;");
				}catch (SQLException e1){
					e1.printStackTrace();
				}catch (ClassCastException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
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
			}
		});
		button.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\agregar-los-datos-basicos-icono-4876-96.png"));
		button.setBounds(151, 68, 114, 152);
		contentPanel.add(button);
		
		JLabel lblCantidad = new JLabel("CANTIDAD:");
		lblCantidad.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setBounds(10, 124, 69, 14);
		contentPanel.add(lblCantidad);
		
		CANTIDAD = new JTextField();
		CANTIDAD.setHorizontalAlignment(SwingConstants.CENTER);
		CANTIDAD.setBounds(10, 149, 131, 20);
		contentPanel.add(CANTIDAD);
		CANTIDAD.setColumns(10);
		
		JLabel lblCodigodebarra = new JLabel("CODIGO_DE_BARRA");
		lblCodigodebarra.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lblCodigodebarra.setBounds(10, 11, 141, 14);
		lblCodigodebarra.setForeground(Color.WHITE);
		contentPanel.add(lblCodigodebarra);
		
		Codigo_BarraProductos = new JTextField();
		Codigo_BarraProductos.setHorizontalAlignment(SwingConstants.CENTER);
		Codigo_BarraProductos.setBounds(10, 36, 131, 20);
		contentPanel.add(Codigo_BarraProductos);
		Codigo_BarraProductos.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\fondo_inicio.jpg"));
		label.setBounds(0, 0, 265, 231);
		contentPanel.add(label);
	    }
	boolean validarProductos(String des,String asd)  throws IOException
    {
    	try
		{
    		java.sql.Connection conn=null;
    	     Statement stmnt=null;
    	    ResultSet rs=null;
    	    String Usuario="Alejandro";
    		String Contrasenia="12345";
    		String URL="jdbc:mysql://localhost/tienda2015";
    	    conn=(Connection) DriverManager.getConnection(URL,Usuario,Contrasenia);
    	    stmnt=conn.createStatement();
            ResultSet resultadosConsulta = stmnt.executeQuery ("SELECT * FROM productos WHERE CODIGO_BARRA='"+des+"'AND DESCRIPCION='"+asd+"'");
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
	
	boolean validarProductos1(String des)  throws IOException
    {
    	try
		{
    		java.sql.Connection conn=null;
    	     Statement stmnt=null;
    	    ResultSet rs=null;
    	    String Usuario="Alejandro";
    		String Contrasenia="12345";
    		String URL="jdbc:mysql://localhost/tienda2015";
    	    conn=(Connection) DriverManager.getConnection(URL,Usuario,Contrasenia);
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
	
	boolean validarProductos2(String des)  throws IOException
    {
    	try
		{
    		java.sql.Connection conn=null;
    	     Statement stmnt=null;
    	    ResultSet rs=null;
    	    String Usuario="Alejandro";
    		String Contrasenia="12345";
    		String URL="jdbc:mysql://localhost/tienda2015";
    	    conn=(Connection) DriverManager.getConnection(URL,Usuario,Contrasenia);
    	    stmnt=conn.createStatement();
            ResultSet resultadosConsulta = stmnt.executeQuery ("SELECT * FROM productos WHERE DESCRIPCION='"+des+"'");
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

