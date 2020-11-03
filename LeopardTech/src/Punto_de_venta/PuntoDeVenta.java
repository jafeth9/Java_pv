package Punto_de_venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.Vector;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jpedal.PdfDecoder;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileReader;
import java.io.IOException;




import Punto_de_venta.Facturas.Hilo2;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;

import java.awt.Toolkit;
import javax.swing.JProgressBar;
import javax.swing.JInternalFrame;
import java.awt.Canvas;
import javax.swing.JToggleButton;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JSlider;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;


public class PuntoDeVenta extends JFrame {

	private JPanel getcontentPane;
	Statement stmnt=null;
	static JLabel TOTAL = new JLabel(" $     0.0");
	
	
	public static JTable JTResultado1;
	static int ca;
	
	static JMenuItem mntmVentas = new JMenuItem("TICKETS");
	static int cb;
	static Eliminar dialog1 = new Eliminar();
	static JButton btnAgregar = new JButton("AGREGAR PRODUCTO");
	/*------------jafeth8******************----------------------------*/
	static JButton btnApartar = new JButton("APARTAR PRODUCTOS");
	public static int setClienteId=0;
	/*------------******************-----------------------------------*/
	static JTextField TFQuery1;
	static int codigodebarra;
	static JLabel UsuarioLabel;
	static JMenuItem EliminarUsuarios = new JMenuItem("Eliminar");
	static JLabel Cambio; 
	static JMenuItem mntmAgregar= new JMenuItem("Agregar");;
	static JButton btnNewButton = new JButton("NUEVA VENTA");
	static JButton btnActualizar = new JButton("ACTUALIZAR PRODUCTO");
	static JTextField CodigoBarra = new JTextField();
	static DefaultTableModel model ;
	static JMenu mnUsuarios= new JMenu("USUARIOS");
	static JMenu mnClientes= new JMenu("CLIENTES");
	static JButton btnEliminar = new JButton("ELIMINAR PRODUCTO");
	static float total=0;
	static String subtotaltabla;
	static String cant;
	static JComboBox comboBox;
	static Login frame1=new Login();
	static JButton AgregarCarrito = new JButton("AGREGAR AL CARRITO");
	static String descripciontablacompras;

	static String codigo;
	static String descripcion;
	static String precio;
	static String cantidad;
	static String Sub_Total;
	
	static double calcula;
	static double calcula2;
	
	static double x;
	static PuntoDeVenta frame = new PuntoDeVenta();
	static int z;
	File archivo;
	JFileChooser FC;
	Formatter formater;	
	Vector<String> vee = new Vector<String>();
	
	JTextField textField = new JTextField();
	static JTable table;
	private JTable table2;
	private JTextField CantidadProductos;
	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public PuntoDeVenta() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\ordenador-icono-8301-96.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1050, 730);
		getcontentPane = new JPanel();
		getcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(getcontentPane);
		
		
		
		
		
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
	    getcontentPane.setLayout(null);
	    
	    TOTAL.setBounds(180, 567, 374, 98);
	    TOTAL.setFont(new Font("Dialog", Font.PLAIN, 40));
		TOTAL.setForeground(Color.BLACK);
		TOTAL.setBackground(new Color(0, 0, 0));
		getContentPane().add(TOTAL);
		
		CodigoBarra = new JTextField();
		CodigoBarra.setForeground(Color.BLUE);
		CodigoBarra.setFont(new Font("Tahoma", Font.PLAIN, 17));
		CodigoBarra.setHorizontalAlignment(SwingConstants.CENTER);
		CodigoBarra.setBounds(422, 142, 580, 30);
		CodigoBarra.setToolTipText("Ingrese el Codigo De barras del producto a buscar");
		getContentPane().add(CodigoBarra);
		CodigoBarra.setColumns(10);
		
		CodigoBarra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
             // TODO Auto-generated method stub
			
				try {
					//if (validarProductos(CodigoBarra.getText())||validarProductos2(CodigoBarra.getText())) {
						ConexionTableModel ctm,ctm2;
						int Nproductos;
						if (CantidadProductos.getText().equals("")) 
						{
							JOptionPane.showMessageDialog(null,"DEVES DE PONER UNA CANTIDAD","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
						}else{
						String Metodo_Busqueda=(String)comboBox.getSelectedItem();
						
						if (Metodo_Busqueda.equals("CODIGO DE BARRA")) {
							
							if(CodigoBarra.getText().equals("")) {
								JOptionPane.showMessageDialog(null,"EL CAMPO ESTA VACIO","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
								//CodigoBarra.setText("");
							} else if (!validarProductos(CodigoBarra.getText())){
								JOptionPane.showMessageDialog(null,"EL PRODUCTO NO EXISTE","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
							}else {
								ctm=new ConexionTableModel("select * from productos where CODIGO_BARRA='"+PuntoDeVenta.CodigoBarra.getText()+"';");
								PuntoDeVenta.JTResultado1.setModel(ctm.getTablemodel());
								
								
								/*------------------------------jafeth8-------------------------------------------*/
								final int flsel=0;
								try{
								
										
								Nproductos=Integer.parseInt(CantidadProductos.getText());
								
								cb=Nproductos;
								model = (DefaultTableModel) JTResultado1.getModel();
								
								cantidad=JTResultado1.getValueAt(flsel, 2).toString();
								descripcion = JTResultado1.getValueAt(flsel, 3).toString();
								precio=JTResultado1.getValueAt(flsel, 4).toString();
								
								int tabla = Integer.parseInt(cantidad);
								if (cb<=0) {
									JOptionPane.showMessageDialog(null,"EL DATO ES INCORRECTO","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
									ConexionTableModel ctm1 = new ConexionTableModel("select * from productos");
									JTResultado1.setModel(ctm1.getTablemodel());
								}else{
								if (cb>tabla) {
									JOptionPane.showMessageDialog(null,"PRODUCTO INSUFICIENTE","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
									ConexionTableModel ctm1 = new ConexionTableModel("select * from productos");
									JTResultado1.setModel(ctm1.getTablemodel());
								}
								else{
										if(validarUsuario(descripcion) )
					              		{
											for (int j = 0; j < table.getRowCount(); j++) {
													String cantidad1=table.getValueAt(j, 0).toString();
													String descripcioncompras=table.getValueAt(j, 1).toString();
													if (descripcioncompras.equals(descripcion)) {
													x=(Double.parseDouble(cantidad1)+cb);
													double a=((Double.parseDouble(cantidad1)+cb)*Double.parseDouble(precio));
														if (tabla<x) {
															JOptionPane.showMessageDialog(null,"PRODUCTO INSUFICIENTE","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
															ConexionTableModel ctm1 = new ConexionTableModel("select * from productos");
															JTResultado1.setModel(ctm1.getTablemodel());
														}else{
															stmnt.executeUpdate("UPDATE  `tienda2015`.`tcompras` SET  `CANTIDAD` =  '"+x+"',`SUB_TOTAL` =  '"+a+"' WHERE DESCRIPCION='"+descripcion+"'");
															ctm=new ConexionTableModel("select * from tcompras");
															table.setModel(ctm.getTablemodel());
															calcula = (Double.parseDouble(precio)*cb);
															total=(float) (total+calcula);
															ConexionTableModel ctm1 = new ConexionTableModel("select * from productos");
															JTResultado1.setModel(ctm1.getTablemodel());
															CodigoBarra.setText("");
														}
													}
													}
					              		 }else{
					              			
					              		  			x=(cb*Double.parseDouble(precio));
					              		  			Sub_Total= String.valueOf(x);
					              		  			stmnt.executeUpdate("INSERT INTO `tienda2015`.`tcompras` (`CANTIDAD`, `DESCRIPCION`, `PRECIO_UNITARIO`, `SUB_TOTAL`) VALUES ('"+cb+"', '"+descripcion+"', '"+precio+"', '"+Sub_Total+"');");
					              		  			ctm=new ConexionTableModel("select * from tcompras");
					              		  			table.setModel(ctm.getTablemodel());
					              		  			calcula = (Double.parseDouble(precio)*cb);
					              		  		    total=(float) (total+calcula);
					              		  		    ConexionTableModel ctm1 = new ConexionTableModel("select * from productos");
						    					    JTResultado1.setModel(ctm1.getTablemodel());
						    					    CodigoBarra.setText("");
									          }
								TOTAL.setText("$ "+total);
								}
								}
								}
							 catch (Exception e) {
								 e.printStackTrace();
							}	
						    /*-----------------------------------JAFETH8---------------------------------------*/
							}
								
							
							
						}
						
						
						if (Metodo_Busqueda.equals("DESCRIPCION")) {
							//ctm2=new ConexionTableModel("select * from productos where DESCRIPCION='"+PuntoDeVenta.CodigoBarra.getText()+"';");
							//ctm2=new ConexionTableModel("select * from productos where DESCRIPCION like '%"+PuntoDeVenta.CodigoBarra.getText()+"'");
							//PuntoDeVenta.JTResultado1.setModel(ctm2.getTablemodel());
							ctm2=new ConexionTableModel();
							ctm2.mostrardatos(PuntoDeVenta.CodigoBarra.getText());
							
							
						}
							
						}
										
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
			
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(27, 379, 980, 187);
		scrollPane_1.setToolTipText("Mostrar Todo los productos");
		getContentPane().add(scrollPane_1);
		
		try {
			ConexionTableModel ctm = new ConexionTableModel("TRUNCATE tcompras");
			ConexionTableModel ctm1 =new ConexionTableModel("Select * from tcompras");
			table.setModel(ctm1.getTablemodel());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cantidad", "Descripcion", "Precio_Unitario", "Sub_Total"
			}
		));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setViewportView(table);
		
		TFQuery1 = new JTextField();
		TFQuery1.setBounds(0, 0, 0, 0);
		getContentPane().add(TFQuery1);
		TFQuery1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 183, 980, 125);
		getContentPane().add(scrollPane);
		
		JTResultado1 = new JTable();
		
		JTResultado1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTResultado1.setToolTipText("Tabla de productos");
		scrollPane.setViewportView(JTResultado1);
		JTResultado1.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), new Integer(186), "DORITOS", null},
				{new Integer(2), new Integer(2), "CHETOS", null},
				{new Integer(4), new Integer(2), "LECHE", null},
				{new Integer(6), new Integer(10), "PASTA DENTAL", null},
				{new Integer(7), new Integer(2), "ATUN", null},
				{new Integer(10), new Integer(2), "PAN BIMBO", null},
			},
			new String[] {
				"CODIGO_BARRA", "CANTIDAD", "DESCRIPCION", "PRECIO_UNITARIO"
			}
		) {
			
		});
		
		JTResultado1.getColumnModel().getColumn(0).setResizable(false);
		JTResultado1.getColumnModel().getColumn(1).setResizable(false);
		JTResultado1.getColumnModel().getColumn(2).setResizable(false);
		JTResultado1.getColumnModel().getColumn(3).setResizable(false);
		JTResultado1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		try {
			ConexionTableModel ctm = new ConexionTableModel("select * from productos");
			JTResultado1.setModel(ctm.getTablemodel());
			ConexionTableModel ctm1=new ConexionTableModel("select * from tcompras");
			table.setModel(ctm1.getTablemodel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnAgregar.setBounds(27, 79, 200, 52);
		btnAgregar.setToolTipText("Agrega Productos ");
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarDatos.dialog.setVisible(true);
			}
		});
		btnAgregar.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\anadir-registro-icono-8419-32.png"));
		getContentPane().add(btnAgregar);
		/*----------------jafeth8:btnApartar------------------------------*/
		btnApartar.setBounds(277, 79, 200, 52);
		getContentPane().add(btnApartar);
		btnApartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Metodos instancia=new Metodos();
				int fila=JTResultado1.getSelectedRow();
				String resultadoString;
				boolean entradaValida=true;
				int cantidadTabla;
				double precioTabla;
				
				/*---VARIABLES: PARA INSERTAR EN TABLA APARTADOS*/
				int idCliente;
				int id_producto;// perfectamente se podria utilizar (setClienteId) la variable estatica de la clase, pero para no revolver variables la dejo XD
				int cantidadIngresada;
				double total;
				//LocalDate.now();
				String fecha;
				String estado;
				
				/*---FIN DE VARIABLES:PARA INSERTAR EN TABLA APARTADOS */
				if (fila>=0) {
					try {
						do {
							resultadoString=JOptionPane.showInputDialog("CANTIDAD DE PRODUCTOS A APARTAR");
							entradaValida=instancia.validarEntradaCantidad(resultadoString);
							if(entradaValida) { //<----ultimo filtro a evaluar: que la cantidad  ingresada no sobrepase a la cantidad de la tala 
								cantidadTabla=Integer.parseInt(JTResultado1.getValueAt(fila, 2).toString());
								entradaValida=instancia.validarCantidadProducto(resultadoString, cantidadTabla);
							}
							
						} while (entradaValida==false);
						/*----SI LA ENTRADA ES VALIDA:CONTINUAR---*/
						JOptionPane.showMessageDialog(null,"muy bien!, enseguida seleccione al cliente que hara el apartado");
						MostrarClientes datosClientes=new MostrarClientes();
						datosClientes.setVisible(true);
					    
						idCliente=setClienteId;//setIdCliente es una variable estatica para recibir datos de la ventana MostrarClientes
						if(idCliente==0) {//si id cliente es igual a cero significa que no se establecio un idCliente, por lo tanto la ventana fue cerrada
							JOptionPane.showMessageDialog(null, "operacion cancelada" ,"NO SELECCIONO AL CLIENTE!!", JOptionPane.WARNING_MESSAGE);
						}else {
							precioTabla=Double.parseDouble(JTResultado1.getValueAt(fila,4).toString());
							/*se inicializan las variables restantes para insertar a tabla apartados*/
							id_producto=Integer.parseInt(JTResultado1.getValueAt(fila,0).toString());
							cantidadIngresada=Integer.parseInt(resultadoString);
							total=precioTabla*cantidadIngresada;
							fecha=LocalDate.now().toString();
							/*ahora insertamos los datos a la tabla XD*/
							SqlOperaciones objeto =new SqlOperaciones();
							objeto.insertApartados(idCliente, id_producto, cantidadIngresada, total, fecha, "en deuda");
							//objeto.obtenerCantidadTablaProducto(id_producto);
							
							int cantidaTabla=Integer.parseInt(JTResultado1.getValueAt(fila, 2).toString());
							objeto.actualizarCantidadProductos(id_producto, cantidaTabla,cantidadIngresada);
							JOptionPane.showMessageDialog(null,"operacion realizada correctamente");
							ConexionTableModel datos = new ConexionTableModel();
							datos.mostrardatos("");
							/*System.out.println("el id del cliente es: "+idCliente);
							System.out.println("el id del producto es : "+id_producto);
							System.out.println("cantidad: "+cantidadIngresada);
							System.out.println("total: "+total);
							System.out.println("fecha: "+fecha);*/
							
							
						}
						setClienteId=0;//esta variable deber ser reseteada a cero al final de cada operacion
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else {
					JOptionPane.showMessageDialog(null,"no selecciono fila","Atencion",JOptionPane.WARNING_MESSAGE);
				}

			}//llaveActionPerformand
		});
		/*----------------jafeth8: fin de btnApartar-----------------------*/
		btnEliminar.setBounds(577, 79, 180, 52);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final int flsel=JTResultado1.getSelectedRow();
				if (flsel==-1) {
					JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR UN PRODUCTO","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				}else{
					EliminarDatos.dialog.setVisible(true);
					descripcion = JTResultado1.getValueAt(flsel, 2).toString();
					EliminarDatos.producto.setText(descripcion);
					
				}	
			}
		});
		btnEliminar.setToolTipText("Eliminar Productos");
		
		btnEliminar.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\ic_menu_trash_holo_light.png"));
		getContentPane().add(btnEliminar);
		
		
		try {
			AgregarCarrito.setBounds(400, 316, 232, 52);
			AgregarCarrito.addActionListener(new ActionListener() {
				ConexionTableModel ctm=new ConexionTableModel("select * from productos");
				public void actionPerformed(ActionEvent arg0) {
					
					
					final int flsel=JTResultado1.getSelectedRow();
					Metodos instancia = new Metodos();
					String Nproductos;
					if (flsel==-1) {
						JOptionPane.showMessageDialog(null,"NO SELECCIONO PRODUCTO","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
					}else{
						try {
						do{
							Nproductos=JOptionPane.showInputDialog("INGRESA LA CANTIDAD DE PRODUCTOS");
							/*----------------------------JAFETH8:CODIGO AGREGADO-------------------------------------------*/
							if(instancia.isNumeric(Nproductos)==false && !Nproductos.equals("")) {
								JOptionPane.showMessageDialog(null, "Igrese un numero!","error",JOptionPane.ERROR_MESSAGE);
								
							}
							/*------------------------FIN CODIGO AGREGADO-----------------------------------------------*/
						}while(Nproductos.equals("") || instancia.isNumeric(Nproductos)==false);//jafeth8:metodo agregado isNumeric	
						cb=Integer.parseInt(Nproductos);
						model = (DefaultTableModel) JTResultado1.getModel();
						
						cantidad=JTResultado1.getValueAt(flsel, 2).toString();
						descripcion = JTResultado1.getValueAt(flsel, 3).toString();
						precio=JTResultado1.getValueAt(flsel, 4).toString();
						
						int tabla = Integer.parseInt(cantidad);
						if (cb<=0) {
							JOptionPane.showMessageDialog(null,"EL DATO ES INCORRECTO","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
						}else{
						if (cb>tabla) {
							JOptionPane.showMessageDialog(null,"PRODUCTO INSUFICIENTE","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
								if(validarUsuario(descripcion) )
			              		{
									for (int j = 0; j < table.getRowCount(); j++) {
											String cantidad1=table.getValueAt(j, 0).toString();
											String descripcioncompras=table.getValueAt(j, 1).toString();
											if (descripcioncompras.equals(descripcion)) {
											    x=(Double.parseDouble(cantidad1)+cb);
											    double a=((Double.parseDouble(cantidad1)+cb)*Double.parseDouble(precio));
												if (tabla<x) {
													JOptionPane.showMessageDialog(null,"PRODUCTO INSUFICIENTE","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
												}else{
													stmnt.executeUpdate("UPDATE  `tienda2015`.`tcompras` SET  `CANTIDAD` =  '"+x+"',`SUB_TOTAL` =  '"+a+"' WHERE DESCRIPCION='"+descripcion+"'");
													ctm=new ConexionTableModel("select * from tcompras");
													table.setModel(ctm.getTablemodel());
													calcula = (Double.parseDouble(precio)*cb);
													total=(float) (total+calcula);
													ConexionTableModel ctm = new ConexionTableModel("select * from productos");
													JTResultado1.setModel(ctm.getTablemodel());
													CodigoBarra.setText("");
												}
											}
											}
			              		 }else{
			              			
			              		  			x=(cb*Double.parseDouble(precio));
			              		  			Sub_Total= String.valueOf(x);
			              		  			stmnt.executeUpdate("INSERT INTO `tienda2015`.`tcompras` (`CANTIDAD`, `DESCRIPCION`, `PRECIO_UNITARIO`, `SUB_TOTAL`) VALUES ('"+cb+"', '"+descripcion+"', '"+precio+"', '"+Sub_Total+"');");
			              		  			ctm=new ConexionTableModel("select * from tcompras");
			              		  			table.setModel(ctm.getTablemodel());
			              		  			calcula = (Double.parseDouble(precio)*cb);
			              		  		    total=(float) (total+calcula);
			              		  		    ConexionTableModel ctm = new ConexionTableModel("select * from productos");
				    					    JTResultado1.setModel(ctm.getTablemodel());
				    					    CodigoBarra.setText("");
							          }
						TOTAL.setText("$ "+total);
						}
						}
						
					} catch (Exception e) {
						
					}
					
					}
			}
			});
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		AgregarCarrito.setToolTipText("Agrega Productos a tu compra");
		
		AgregarCarrito.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\carrito-de-compras-de-comercio-electronico-comprar-icono-4574-48.png"));
		getContentPane().add(AgregarCarrito);
		
		JLabel lblAbarrotesDonPepe = new JLabel("");
		lblAbarrotesDonPepe.setBounds(150, 11, 753, 68);
		lblAbarrotesDonPepe.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\LETRAS2.png"));
		lblAbarrotesDonPepe.setFont(new Font("Angsana New", Font.ITALIC, 67));
		getContentPane().add(lblAbarrotesDonPepe);
		
		JButton button = new JButton("");
		button.setBounds(415, 587, 150, 52);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Calendar fecha = new GregorianCalendar();
				int anio = fecha.get(Calendar.YEAR);
		        int mes = fecha.get(Calendar.MONTH)+1;
		        int dia = fecha.get(Calendar.DAY_OF_MONTH);
		        int hora = fecha.get(Calendar.HOUR_OF_DAY);
		        int minuto = fecha.get(Calendar.MINUTE);
		        int segundo = fecha.get(Calendar.SECOND);	

		        String pagar;
				
				if(total==0)
				{
					JOptionPane.showMessageDialog(null,"DEBES GENERAR UNA VENTA PARA PAGAR","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				}else {
				
				pagar=JOptionPane.showInputDialog("Ingrese con cuanto va a pagar");
				ca=Integer.parseInt(pagar);
				if (total>ca){
					JOptionPane.showMessageDialog(null,"PAGO INSUFICIENTE","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				}else
				{
						float TotalApagar;
						TotalApagar=ca-total;
						String cantidad1;
						String descripcionCompras;			
						String descripcionProductos;
						String CantidadPro = null;
							for (int j = 0; j < table.getRowCount(); j++) {
								cantidad1=table.getValueAt(j, 0).toString();
								descripcionCompras=table.getValueAt(j,1).toString();
									try {
										for (int i = 0; i < JTResultado1.getRowCount(); i++) {
											descripcionProductos=JTResultado1.getValueAt(i,2).toString();
											if (descripcionCompras.equals(descripcionProductos)) {
												CantidadPro=JTResultado1.getValueAt(i,1).toString();
												int a=Integer.parseInt(cantidad1);
												int b=Integer.parseInt(CantidadPro);
												z=(b-a);
												stmnt.executeUpdate("UPDATE  `tienda2015`.`productos` SET  `CANTIDAD` =  '"+z+"' WHERE  `productos`.`DESCRIPCION` ='"+descripcionCompras+"';");
												ConexionTableModel ctm=new ConexionTableModel("select * from productos");
												JTResultado1.setModel(ctm.getTablemodel());
											}
										}
										
									} 
										catch (SQLException e2) {
										// TODO Auto-generated catch block
                                                                                    e2.printStackTrace();
										}							
							}
							
							DefaultTableModel modelo = (DefaultTableModel) table2.getModel ();
							for (int i = 0; i <table.getRowCount (); i ++) 
						    {Object[] fila = new Object [table.getColumnCount ()]; 
						    for (int j = 0; j <table.getColumnCount (); j ++) 
						          {fila [j] = table.getValueAt (i, j);} 
						   table2.setModel(table.getModel());
						    }
						    
						    					
									
							try {
								//'"+a�o+"-"+mes+"-"+dia+"'
								stmnt.executeUpdate("INSERT INTO `tienda2015`.`facturas` (`Numero_ticket`, `Fecha`, `HORA`, `Pago`) VALUES (NULL, '"+anio+"-"+(mes+1)+"-"+dia+"', '"+hora+":"+minuto+":"+segundo+"', '"+total+"');");
								
								ConexionTableModel ctm=new ConexionTableModel("select * from facturas");
								Facturas.TFacturas.setModel(ctm.getTablemodel());
								
							} catch (Exception e2) {
								// TODO: handle exception
								e2.printStackTrace();
							}
							
							Cambio.setText("$ "+TotalApagar+"");
							total=0;
							
							
													
							int pre=JOptionPane.showConfirmDialog(null, "� DESEA IMPRIMIR TICKET ?");
							 if (pre==0) {							
							try {
								 PrinterMatrix printer = new PrinterMatrix();
								 Extenso e1 = new Extenso();

								 
								   e1.setNumber(10);
								    //Definir el tamanho del papel para la impresion de dinamico y 32 columnas
								    int filas = table.getRowCount();
								    int tamanio = filas+15;
								    printer.setOutSize(tamanio, 80);

								    //Imprimir = 1ra linea de la columa de 1 a 32
								    printer.printTextWrap(0, 1, 5, 80, "=======================================================");
								    printer.printTextWrap(1, 1, 40, 80, "5 de mayo No 20"); //Nombre establecimiento
								    printer.printTextWrap(1, 1, 5, 80, "Construcciones Silva"); //Barrio
								    printer.printTextWrap(2, 1, 40, 80, "Tel. 452-194-6841"); //Direccion
								    printer.printTextWrap(2, 1, 10, 80, "Ahuiran,Mich."); //Codigo Postal
								    
								    printer.printTextWrap(3, 1, 0, 40, "Fecha: "+dia+"/"+mes+"/"+anio); //Aqui va la fecha de recibo
								    printer.printTextWrap(3, 1, 40, 80, "Hora"+hora+":"+minuto+":"+segundo); //Aqui va la hora de recibo
								    
								   
								    //printer.printTextWrap(9, 1, 3, 80, "Cliente");//Nombre del Cliente
								    //printer.printTextWrap(10,1, 5, 80, "������������������������������������������������������������������");
								    printer.printTextWrap(4,1, 0, 80, "Cant.   Producto    P/U   Sub.T");
								    //printer.printTextWrap(12,1, 0, 80, "## ");

								    for (int i = 0; i < filas; i++) {
								        int p = 5+i; //Fila

								        printer.printTextWrap(p , 1, 0, 19 , table.getValueAt(i,0).toString());
								        printer.printTextWrap(p , 1, 5, 42 , table.getValueAt(i,1).toString());
								        printer.printTextWrap(p , 1, 20, 49, table.getValueAt(i,2).toString());

								        String pre1= printer.alinharADireita(10, table.getValueAt(i,3).toString());
								        printer.printTextWrap(p , 1, 54, 80, pre1);

								        //String inp= printer.alinharADireita(7,punto_Venta.jtbl_venta.getValueAt(i,6).toString());
								        //printer.printTextWrap(p , 1, 25, 32, inp);
								    }
								    
								    DecimalFormat formateador = new DecimalFormat("#.###");

								   
								    printer.printTextWrap(filas+6, 1, 5, 80, "Subtotal: ");
								    printer.printTextWrap(filas+6, 1, 20, 80, "$"+TOTAL.getText());

								   // String tot= printer.alinharADireita(10, total);
								    printer.printTextWrap(filas+7, 1, 5, 80, "Total a pagar: ");
								    printer.printTextWrap(filas+7, 1, 20, 80, "$"+TOTAL.getText());

								    //String efe= printer.alinharADireita(10,90);
								    printer.printTextWrap(filas+8, 1, 5, 80, "Efectivo : ");
								    printer.printTextWrap(filas+8, 1, 20, 80, "$"+pagar);

								    //String cam= printer.alinharADireita(10,9);
								    printer.printTextWrap(filas+9, 1, 5, 80, "Cambio : ");
								    printer.printTextWrap(filas+9, 1, 20, 80, "$"+ Cambio.getText());

								    //printer.printTextWrap(filas+21, 1, 5, 80, "������������������������������������������������������������������");
								    printer.printTextWrap(filas+10, 1, 5,80, "!Gracias por su Compra!");
								    printer.printTextWrap(filas+11, 1, 5, 80, "Constructora silva");
								    printer.printTextWrap(filas+12, 1, 5, 80, "Atendido por : "+UsuarioLabel.getText());
								    //printer.printTextWrap(filas+13, 1, 3, 80, "Contacto: workitapp@gmail.com");
								    printer.printTextWrap(filas+13, 1, 3,80, "===================================================================");

							      //222222222222222222222222222222222222222222222222222222}
//							       int filas = tblVentas.getRowCount();
					//
//							        for (int i = 0; i < filas; i++) {
//							         printer.printTextWrap(9 + i, 10, 1, 80, tblVentas.getValueAt(i,0).toString()+"|"+tblVentas.getValueAt(i,1).toString()+"| "+tblVentas.getValueAt(i,2).toString()+"| "+tblVentas.getValueAt(i,3).toString()+"|"+ tblVentas.getValueAt(i,4).toString());
//							         }
					//
//							        if(filas > 15){
//							        printer.printCharAtCol(filas + 1, 1, 80, "=");
//							        printer.printTextWrap(filas + 1, filas + 2, 1, 80, "TOTAL A PAGAR " + txtVentaTotal.getText());
//							        printer.printCharAtCol(filas + 2, 1, 80, "=");
//							        printer.printTextWrap(filas + 2, filas + 3, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");
//							        }else{
//							        printer.printCharAtCol(25, 1, 80, "=");
//							        printer.printTextWrap(26, 26, 1, 80, "TOTAL A PAGAR " + txtVentaTotal.getText());
//							        printer.printCharAtCol(27, 1, 80, "=");
//							        printer.printTextWrap(27, 28, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");
					//
//							        }
							      //222222222222222222222222222222222222222222222222222222 
							       printer.toFile("impresion.txt");
							       
							      FileInputStream inputStream = null;
							        try {
							            inputStream = new FileInputStream("impresion.txt");
							        } catch (FileNotFoundException ex) {
							            ex.printStackTrace();
							        }
							        if (inputStream == null) {
							            return;
							        }

							        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
							        Doc document = new SimpleDoc(inputStream, docFormat, null);

							        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

							        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();


							        if (defaultPrintService != null) {
							            DocPrintJob printJob = defaultPrintService.createPrintJob();
							            try {
							                printJob.print(document, attributeSet);

							            } catch (Exception ex) {
							                ex.printStackTrace();
							            }
							        } else {
							            System.err.println("No existen impresoras instaladas");
							        }

							        //inputStream.close();
									
									
								
							} catch (Exception e2) {
								// TODO: handle exception
							}
							   
							 }
							 else{
								 JOptionPane.showMessageDialog(null, "GRACIAS POR SU COMPRA"); 
							 }
							 
							 try {
									
									ConexionTableModel ctm = new ConexionTableModel("TRUNCATE tcompras");
									ConexionTableModel ctm1 =new ConexionTableModel("Select * from tcompras");
									table.setModel(ctm1.getTablemodel());
									ConexionTableModel ctm2=new ConexionTableModel("select CANTIDAD,DESCRIPCION from productos WHERE CANTIDAD='0'");
									ProductosAgotados.ProductosAgotados.setModel(ctm2.getTablemodel());
									
									
								} catch (SQLException e2) {
									System.err.println(e2);
								}
							 
							 TOTAL.setText(" $ 0.0");
							 Cambio.setText(" $ 0.0");
			  }
			 }		
			}
		});
			
		button.setToolTipText("PAGAR EL TOTAL");
		
		button.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\boton-pagar.jpg"));
		getContentPane().add(button);
		
		btnNewButton.setBounds(27, 316, 155, 52);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConexionTableModel ctm = new ConexionTableModel("TRUNCATE tcompras");
					ConexionTableModel ctm1 =new ConexionTableModel("Select * from tcompras");
					table.setModel(ctm1.getTablemodel());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				TOTAL.setText("$      0.0");
				Cambio.setText("$  0.0");
				total=0;
				JOptionPane.showMessageDialog(null, "SE GENERO UNA NUEVA VENTA :D");
			
			}
		});
		
		
		btnNewButton.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\22editar.png"));
		getContentPane().add(btnNewButton);
		
		JLabel holamundo = new JLabel("TOTAL :");
		holamundo.setBounds(15, 575, 202, 77);
		holamundo.setForeground(Color.BLACK);
		holamundo.setFont(new Font("Dialog", Font.PLAIN, 38));
		holamundo.setBackground(Color.BLACK);
		getContentPane().add(holamundo);
		btnActualizar.setBounds(800, 79, 200, 52);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int flsel=JTResultado1.getSelectedRow();
				if (flsel==-1) {
					JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR UN PRODUCTO","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				}else{
				Actualizar.actualizar.setVisible(true);
				codigo = JTResultado1.getValueAt(flsel, 0).toString();
				cantidad=JTResultado1.getValueAt(flsel, 1).toString();
				descripcion = JTResultado1.getValueAt(flsel, 2).toString();
				precio=JTResultado1.getValueAt(flsel, 3).toString();
				Actualizar.BARRA.setText(""+codigo);
				Actualizar.Nombre.setText(""+descripcion);
				Actualizar.Cantidad.setText(""+cantidad);
				Actualizar.Precio.setText(""+precio);
				    
				}
			}
		});
		btnActualizar.setToolTipText("Actulizacion de productos");
		
		
		
		btnActualizar.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\22editar.png"));
		getContentPane().add(btnActualizar);
		
		JButton btnCancelarProducto = new JButton("CANCELAR PRODUCTO");
		btnCancelarProducto.setBounds(800, 316, 200, 52);
		btnCancelarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             final int seleccionado=table.getSelectedRow();
				
				if (seleccionado==-1) {
					JOptionPane.showMessageDialog(null,"NO SELECCIONO PRODUCTO","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						if(total<=0){
							ConexionTableModel ctm = new ConexionTableModel("TRUNCATE tcompras");
							ConexionTableModel ctm1 =new ConexionTableModel("Select * from tcompras");
							table.setModel(ctm1.getTablemodel());
						}
						descripciontablacompras=table.getValueAt(seleccionado, 1).toString();
						subtotaltabla=table.getValueAt(seleccionado, 3).toString();
						calcula2 = (Double.parseDouble(subtotaltabla));
						total=(float) (total-calcula2);
						stmnt.executeUpdate("delete from tcompras where DESCRIPCION='"+descripciontablacompras+"';");
						ConexionTableModel ctm=new ConexionTableModel("select * from tcompras");
						PuntoDeVenta.table.setModel(ctm.getTablemodel());
						TOTAL.setText("$ "+total);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnCancelarProducto.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\ic_menu_trash_holo_light.png"));
		getContentPane().add(btnCancelarProducto);
		
		
		JLabel lblCambio = new JLabel("CAMBIO :");
		lblCambio.setBounds(630, 587, 200, 52);
		lblCambio.setFont(new Font("Dialog", Font.PLAIN, 38));
		lblCambio.setBackground(Color.BLACK);
		lblCambio.setForeground(Color.BLACK);
		getContentPane().add(lblCambio);
		
		Cambio = new JLabel("$  0.0");
		Cambio.setBounds(820, 577, 250, 77);
		Cambio.setFont(new Font("Dialog", Font.PLAIN, 40));
		Cambio.setForeground(Color.BLACK);
		getContentPane().add(Cambio);
		
		table2 = new JTable();
		table2.setBounds(22, 379, 1, 1);
		table2.setEnabled(false);
		getcontentPane.add(table2);
		
		
		
		comboBox = new JComboBox();
		comboBox.setBounds(267, 142, 145, 30);
		getcontentPane.add(comboBox);
		comboBox.addItem("CODIGO DE BARRA");
		comboBox.addItem("DESCRIPCION");
		
		CantidadProductos = new JTextField();
		CantidadProductos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CantidadProductos.setForeground(Color.RED);
		CantidadProductos.setHorizontalAlignment(SwingConstants.CENTER);
		CantidadProductos.setText("1");
		CantidadProductos.setBounds(159, 142, 84, 30);
		getcontentPane.add(CantidadProductos);
		CantidadProductos.setColumns(10);
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCantidad.setForeground(Color.BLACK);
		lblCantidad.setBounds(57, 148, 92, 24);
		getcontentPane.add(lblCantidad);
		
		
		UsuarioLabel = new JLabel("");
		UsuarioLabel.setForeground(Color.BLUE);
		UsuarioLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		UsuarioLabel.setBounds(0, 661, 149, 30);
		getcontentPane.add(UsuarioLabel);
		
		UsuarioLabel.setText(""+Login.usuarioadentro);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(0, -6, 1346, 697);
		label_4.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\Abarrotes El Atoron\\Imagenes\\fondo_inicio.jpg"));
		getContentPane().add(label_4);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("ARCHIVO");
		mnArchivo.setFont(new Font("Andalus", Font.BOLD, 15));
		mnArchivo.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\presidencia\\Imagenes\\ICONOS\\inicio.png"));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");		
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int pre=JOptionPane.showConfirmDialog(null, "� DESEAS SALIR DEL PROGRAMA ?");
				 if (pre==0) {
				System.exit(1);
				 }
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		JMenu menu_1 = new JMenu("                                  ");
		menu_1.setEnabled(false);
		menuBar.add(menu_1);
		
		JMenu mnProductos = new JMenu("PRODUCTOS");
		mnProductos.setFont(new Font("Andalus", Font.BOLD, 15));
		mnProductos.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\presidencia\\Imagenes\\ICONOS\\anadir-carrito-icono-3759-32.png"));
		menuBar.add(mnProductos);
		
		JMenuItem AgregarP = new JMenuItem("AGREGAR");
		mnProductos.add(AgregarP);
		AgregarP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AgregarDatos.dialog.setVisible(true);
			}
		});
		
		JMenuItem EliminarP = new JMenuItem("ELIMINAR");
		mnProductos.add(EliminarP);
		EliminarP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				EliminarDatos.dialog.setVisible(true);
			}
		});
		
		JMenuItem ModificarP = new JMenuItem("MODIFICAR");
		mnProductos.add(ModificarP);
		
		ModificarP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Actualizar.actualizar.setVisible(true);
			}
		});
		
		JMenu menu_2 = new JMenu("                                  ");
		menu_2.setEnabled(false);
		menuBar.add(menu_2);
		mnUsuarios.setFont(new Font("Andalus", Font.BOLD, 15));
		mnUsuarios.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\presidencia\\Imagenes\\ICONOS\\usuario-icono-9502-16.png"));
		
		menuBar.add(mnUsuarios);
		
		mntmAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				 try {
						final ConexionTableModel ctm1=new ConexionTableModel("SELECT USUARIOS FROM usuarios");
						AgregarUsuarios.tableUser.setModel(ctm1.getTablemodel());
						
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
				AgregarUsuarios.dialog.setVisible(true);
				Eliminar.dialog.dispose();		
			}
		});
		mnUsuarios.add(mntmAgregar);
		
        EliminarUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AgregarUsuarios.vee.removeAllElements();
							
				for (int i = 1; i <Login.JTResultadoUser.getRowCount(); i++) {
			      	String Usuariosss = Login.JTResultadoUser.getValueAt(i, 0).toString();
			  		AgregarUsuarios.vee.add(Usuariosss);
				}
				
				dialog1.setVisible(true);	
			}
		});
        
 
		mnUsuarios.add(EliminarUsuarios);
		
		JMenu mnNewMenu = new JMenu("                                  ");
		mnNewMenu.setEnabled(false);
		menuBar.add(mnNewMenu);
		
		JMenu mnFacturas = new JMenu("REPORTES");
		mnFacturas.setFont(new Font("Andalus", Font.BOLD, 15));
		mnFacturas.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\presidencia\\Imagenes\\ICONOS\\rep.png"));
		menuBar.add(mnFacturas);
		
		
		
		mnFacturas.add(mntmVentas);
		
		JMenuItem mntmInventario = new JMenuItem("INVENTARIO");
		mnFacturas.add(mntmInventario);
		
		JMenuItem mntmProductosAgotados = new JMenuItem("PRODUCTOS AGOTADOS");
		mnFacturas.add(mntmProductosAgotados);
		
		mntmProductosAgotados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ProductosAgotados.dialog.setVisible(true);
			}
		});
		
		mntmInventario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Invetarios.dialog.setVisible(true);
			}
		});
		
		JMenu menu_3 = new JMenu("                                  ");
		menu_3.setEnabled(false);
		menuBar.add(menu_3);
		
		
		JMenu mnCerrarSesion = new JMenu("CERRAR SESION");
		mnCerrarSesion.setFont(new Font("Andalus", Font.BOLD, 15));
		mnCerrarSesion.setIcon(new ImageIcon("C:\\"+Ruta.imagen+"\\presidencia\\Imagenes\\ICONOS\\e.png"));
		menuBar.add(mnCerrarSesion);
		
		JMenuItem mntmSalir_1 = new JMenuItem("Salir");
		mnCerrarSesion.add(mntmSalir_1);
		
		JMenuItem menuItem = new JMenuItem("");
		menuBar.add(menuItem);
		
		mntmVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Facturas.dialog.setVisible(true);
			}
		});
		
		mntmSalir_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int pre=JOptionPane.showConfirmDialog(null, "� DESEA CERRAR SESION ?");
				 if (pre==0) {
				// TODO Auto-generated method stub
					 
				frame.dispose();
				frame1.setVisible(true);
				UsuarioLabel.setText("");
				try {
					ConexionTableModel ctm = new ConexionTableModel("TRUNCATE tcompras");
					ConexionTableModel ctm1 =new ConexionTableModel("Select * from tcompras");
					table.setModel(ctm1.getTablemodel());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				ConexionTableModel ctm;
				try {
					ctm = new ConexionTableModel("select * from productos");
					JTResultado1.setModel(ctm.getTablemodel());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
				}
				
				
				TOTAL.setText("$      0.0");
				Cambio.setText("$  0.0");
				total=0;
				AgregarUsuarios.vee.removeAllElements();
				
				for (int i = 0; i <Login.JTResultadoUser.getRowCount(); i++) {
			      	String Usuariosss = Login.JTResultadoUser.getValueAt(i, 0).toString();
			  		AgregarUsuarios.vee.add(Usuariosss);
				}
				
				 }
			}
		});
	}//termina constructor
	
	
	boolean validarUsuario(String des)  throws IOException
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
            ResultSet resultadosConsulta = stmnt.executeQuery ("SELECT * FROM tcompras WHERE DESCRIPCION='"+des+"'");
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
	
	boolean validarProductos(String des)  throws IOException
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
    	    	 ResultSet resultadosConsulta = stmnt.executeQuery ("select * FROM productos WHERE CODIGO_BARRA='"+des+"'");
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
    	    	 ResultSet resultadosConsulta = stmnt.executeQuery ("select * FROM productos WHERE DESCRIPCION='"+des+"'");
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
	
	public void imprimir() throws IOException
	{
		FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Productos.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }
 
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);
 
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
 
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
 
 
        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }
 
        inputStream.close();
	}
	
	
	
	public static class Hilo implements Runnable {

		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i <= 101; i++) {
				Imprimir.barra.setValue(i);
				Imprimir.barra.repaint();
				Imprimir.Por.setText("% "+i);
				if (i==101) {
					Imprimir.dialog.setVisible(false);
					JOptionPane.showMessageDialog(null, " ***** GRACIAS POR SU COMPRA **** ");
				}
				try {
					Thread.sleep(200);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		
	}
}

