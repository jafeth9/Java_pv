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
    
    public void insertApartados(int idCliente, int idProducto,int cantidad,double total,String fecha_apartado,String estado) {
    	try {
    		PreparedStatement pst = cn.prepareStatement("INSERT INTO apartados"
    				+ "(fk_id_cliente,fk_id_producto,cantidad,total,fecha_de_apartado,estado) VALUES (?,?,?,?,?,?)");
    		        pst.setInt(1, idCliente);
    		        pst.setInt(2, idProducto);
    		        pst.setInt(3, cantidad);
    		        pst.setDouble(4, total);
    		        pst.setString(5, fecha_apartado);
    		        pst.setString(6, estado);
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
	
 
}
