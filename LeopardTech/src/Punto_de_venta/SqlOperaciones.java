package Punto_de_venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class SqlOperaciones {
	Conectar cc= new Conectar();
    Connection cn= cc.conexion();
    
    public void insertApartados(int idCliente, int idProducto,int cantidad,double total,String fecha_apartado,double deudaInicial,String estado) {
    	try {
    		PreparedStatement pst = cn.prepareStatement("INSERT INTO apartados"
    				+ "(fk_id_cliente,fk_id_producto,cantidad,total,fecha_de_apartado,deuda,estado) VALUES (?,?,?,?,?,?,?)");
    		        pst.setInt(1, idCliente);
    		        pst.setInt(2, idProducto);
    		        pst.setInt(3, cantidad);
    		        pst.setDouble(4, total);
    		        pst.setString(5, fecha_apartado);
    		        pst.setDouble(6, deudaInicial);
    		        pst.setString(7, estado);
    		        pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}finally {
			//Aqui no se cierra la conexion para permitir mas operaciones
		}
    }
    
    public void actualizarCantidadProductos(int idProducto,int CantidadTablaProductos,int  cantidadDeApartados) {
    	int cantidadRestante=CantidadTablaProductos-cantidadDeApartados;
    	PreparedStatement pst;
		try {
			pst = cn.prepareStatement("UPDATE productos SET cantidad='"+cantidadRestante+"' WHERE ID='"+idProducto+"'");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public int obtenerCantidadTablaProducto(int idProducto) {   //ESTE METODO NO SE OCUPO XD
		    	 
    	String sql="SELECT CANTIDAD FROM productos WHERE ID = '"+idProducto+"'";
		int cantidad=0;    	 
		    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	
                cantidad=rs.getInt(1);
              
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
        	//Aqui no se cierra la conexion para permitir mas operaciones
		}
        
        return cantidad;
    }
    
	public double obtenerPrecioTotalTablaApartados(int id_apartado) {
		String sql="SELECT total FROM apartados WHERE id_apartado = '"+id_apartado+"'";
		double total = 0;
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				total = rs.getDouble(1);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			// Aqui no se cierra la conexion para permitir mas operaciones
		}

		return total;

	}
	
	public double obtenerDeudaTablaApartados(int id_apartado) {
		String sql="SELECT deuda FROM apartados WHERE id_apartado = '"+id_apartado+"'";
		double total = 0;
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				total = rs.getDouble(1);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			// Aqui no se cierra la conexion para permitir mas operaciones
		}

		return total;

	}
	
	public double obtenerTotalAbonoTablaApartados(int id_apartado) {
		String sql="SELECT total_abono FROM apartados WHERE id_apartado = '"+id_apartado+"'";
		double total = 0;
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				total = rs.getDouble(1);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			// Aqui no se cierra la conexion para permitir mas operaciones
		}

		return total;

	}
	
	public void actualizarTotalAbonoTablaApartados(int id_apartatado,double updateAbonoTotal) {
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement("UPDATE apartados SET total_abono='"+updateAbonoTotal+"' WHERE id_apartado='"+id_apartatado+"'");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarDeudaTablaApartados(int id_apartatado,double updateDeuda) {
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement("UPDATE apartados SET deuda='"+updateDeuda+"' WHERE id_apartado='"+id_apartatado+"'");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actualizarEstadoTablaApartados(int id_apartatado,String pagado) {
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement("UPDATE apartados SET estado='"+pagado+"' WHERE id_apartado='"+id_apartatado+"'");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertTablaRegistroApartados(int fk_id_apartado,double dineroRecibido,double abono,double cambioEntregado, String fechaAbono) {
		try {
    		PreparedStatement pst = cn.prepareStatement("INSERT INTO registro_apartados"
    				+ "(fk_id_apartado,dinero_recibido,abono,cambio_entregado,fecha_abono) VALUES (?,?,?,?,?)");
    		        pst.setInt(1, fk_id_apartado);
    		        pst.setDouble(2, dineroRecibido);
    		        pst.setDouble(3, abono);
    		        pst.setDouble(4, cambioEntregado);
    		        pst.setString(5, fechaAbono);
    		        pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}finally {
			
		}
	}
	
 
}
