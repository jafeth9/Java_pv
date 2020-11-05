package Punto_de_venta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerApartados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	Conectar cc= new Conectar();
    Connection cn= cc.conexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerApartados dialog = new VerApartados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarApartados() {
		Statement st=null;
		ResultSet rs=null;
    	DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("Id apartado");
        modelo.addColumn("Fecha de Apartado");
        modelo.addColumn("Id Cliente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Segundo Nombre");
        modelo.addColumn("Id producto");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        modelo.addColumn("Deuda");
       
       
        String sql;
        sql="SELECT apartados.id_apartado, apartados.fecha_de_apartado,clientes.id_cliente,clientes.nombre,"
        		+ "clientes.segundo_nombre,productos.ID,productos.DESCRIPCION,productos.PRECIO_UNITARIO,apartados.cantidad,apartados.total,apartados.deuda "
        		+ "FROM apartados JOIN clientes ON apartados.fk_id_cliente=clientes.id_cliente JOIN productos ON apartados.fk_id_producto=productos.ID WHERE apartados.estado='en deuda'";
        String []datos = new String [11];
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
            	
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                datos[8]=rs.getString(9);
                datos[9]=rs.getString(10);
                datos[10]=rs.getString(11);
                modelo.addRow(datos);
               
            }
            table.setModel(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }finally {
        	try {
        		st.close();
        		rs.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
    }
	

	/**
	 * Create the dialog.
	 */
	public VerApartados() {
		setResizable(false);
		setBounds(100, 100, 800, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 764, 255);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//para scroll horinzontal
		scrollPane.setViewportView(table);
		
		JLabel labelTituloApartados = new JLabel("Productos apartados");
		labelTituloApartados.setForeground(Color.BLUE);
		labelTituloApartados.setBounds(10, 11, 123, 14);
		contentPanel.add(labelTituloApartados);
		
		JButton btnIngresarAbono = new JButton("Ingresar Abono ");
		btnIngresarAbono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodos validar=new Metodos();
				SqlOperaciones operacion =new SqlOperaciones();
				int fila=table.getSelectedRow();
				String entradaDineroRecibido,entradaCantidadAbono;
				
				
				boolean cantidadAbono=true,CantidadDineroRecibido=true,cantidadesCorrectas=true;
				int idApartado;//variable para ingresar a la tabla registro_apartados
				double dineroRecibido,abono,cambio;//variables para ingresar a la tabla registro_apartados
				String fecha;//variable para ingresar a la tabla registro_apartados
				
				
				if (fila>=0) {
					try {// evita que se caiga la ejecucion si las entradas son nulas a causa de que se cierren los JOptionPane
						do {
							int idApartadoAuxliar=Integer.parseInt(table.getValueAt(fila, 0).toString());//esta linea tiene que estar dentro del try catch ya que al no seleccionar ninguna fila el resultado sera null y lanzara un excepcion
							
							do {
								entradaDineroRecibido = JOptionPane.showInputDialog("Dinero recibido");
								
							} while (validar.validarEntradaApartados(entradaDineroRecibido) == false);
							
							do {
								entradaCantidadAbono = JOptionPane.showInputDialog("Cantidad que sera abonada");
								if(validar.isDouble(entradaCantidadAbono)) {
									cantidadAbono=validar.validarCantidadAbono(entradaCantidadAbono,idApartadoAuxliar);
								}
							} while (validar.validarEntradaApartados(entradaCantidadAbono)==false || cantidadAbono==false);
						} while (validar.entradasValidas(entradaDineroRecibido,entradaCantidadAbono)==false);
					
						/*---------------ENTRADA VALIDA:CONTINUAR-----------------------------*/
						double precioTotal;
						double abonoTotal;
						double updateAbonoTotal;
						double updateDeuda;
						//double abonoAcumulado;
						//double deuda;
						idApartado=Integer.parseInt(table.getValueAt(fila, 0).toString());
						abono=Double.parseDouble(entradaCantidadAbono);
						dineroRecibido=Double.parseDouble(entradaDineroRecibido);
						cambio=dineroRecibido-abono;
						fecha=LocalDate.now().toString();
						operacion.insertTablaRegistroApartados(idApartado,dineroRecibido, abono, cambio, fecha);
						
						
						precioTotal=operacion.obtenerPrecioTotalTablaApartados(idApartado);
						abonoTotal=operacion.obtenerTotalAbonoTablaApartados(idApartado);
						updateAbonoTotal=abonoTotal+abono;
					    operacion.actualizarTotalAbonoTablaApartados(idApartado,updateAbonoTotal);
					    updateDeuda=precioTotal-updateAbonoTotal;
					    operacion.actualizarDeudaTablaApartados(idApartado, updateDeuda);
					    if(updateDeuda==0) {
					    	operacion.actualizarEstadoTablaApartados(idApartado,"Pagado");
					    	JOptionPane.showMessageDialog(null,"Este Cliente ha completado los pagos :)");
					    }
					    
					    JOptionPane.showMessageDialog(null,"operacion realizada correctamente");
					    mostrarApartados();
					    dispose();
//						System.out.println("id apartado: "+idApartado);
//						System.out.println("abono: "+abono);
//						System.out.println("cambio: "+cambio);
//						System.out.println("fecha: "+fecha);
//						System.out.println("------------------------------");
//						System.out.println("Precio total: "+operacion.obtenerPrecioTotalTablaApartados(idApartado));
//						System.out.println("Total abono: "+operacion.obtenerTotalAbonoTablaApartados(idApartado));
						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.getMessage();
					}

				}else {
					JOptionPane.showMessageDialog(null,"no selecciono ningun registro","Error",JOptionPane.ERROR_MESSAGE );
				}
				//dineroRecibido=Double.parseDouble(JOptionPane.showInputDialog("Dinero recibido"));
				
			}
		});
		btnIngresarAbono.setBounds(10, 297, 140, 36);
		contentPanel.add(btnIngresarAbono);
		
		JButton btnRegistroApartados = new JButton("Ver registros");
		btnRegistroApartados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila=table.getSelectedRow();
				if(fila>=0) {
					int id_apartado=Integer.parseInt(table.getValueAt(fila,0).toString());
					VerRegistrosApartados.idApartado=id_apartado;
					VerRegistrosApartados instancia = new VerRegistrosApartados();
					instancia.setVisible(true);
					
					
				}else {
					JOptionPane.showMessageDialog(null,"No selecciono ningun registro");
				}
				
			}
		});
		btnRegistroApartados.setBounds(651, 297, 123, 36);
		contentPanel.add(btnRegistroApartados);
		
		mostrarApartados();

	}
}
