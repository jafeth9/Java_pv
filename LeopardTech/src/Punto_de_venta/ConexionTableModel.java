package Punto_de_venta;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*------jafeth8-------------*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
/*----------jafeth8---------*/
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;



public class ConexionTableModel {
	
	
	
	/*----------------------------------jafeth8----------------------------------------------*/
	public ConexionTableModel(){
		
	}
	
	
	Conectar cc= new Conectar();
    Connection cn= cc.conexion();
	  public void mostrardatos(String valor) throws SQLException{
		    DefaultTableModel modelo= new DefaultTableModel();
		    modelo.addColumn("ID");
		    modelo.addColumn("CODIGO_BARRA");
		    modelo.addColumn("CANTIDAD");
		    modelo.addColumn("DESCRIPCION");
		    modelo.addColumn("PRECIO UNITARIO");
		    PuntoDeVenta.JTResultado1.setModel(modelo);
		    String sql="";
		    if(valor.equals(""))
		    {
		        //sql="SELECT CODIGO_BARRA,CANTIDAD,DESCRIPCION,PRECIO_UNITARIO FROM productos";
		    	sql="SELECT * FROM productos";
		    }
		    else{
		    	sql="SELECT * FROM productos WHERE descripcion like '%"+valor+"%'";
		        //sql="SELECT CODIGO_BARRA,CANTIDAD,DESCRIPCION,PRECIO_UNITARIO FROM productos WHERE descripcion like '%"+valor+"%'";
		    }
		 
		    Object []datos = new Object [5];
		        try {
		            Statement st = cn.createStatement();
		            ResultSet rs = st.executeQuery(sql);
		            while(rs.next()){
		            	
		            	datos[0]=rs.getInt(1);
		                datos[1]=rs.getDouble(2);
		                datos[2]=rs.getInt(3);
		                datos[3]=rs.getString(4);
		                datos[4]=rs.getDouble(5);
		                
		                modelo.addRow(datos);
		            }
		            PuntoDeVenta.JTResultado1.setModel(modelo);
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }finally {
		        	
					cn.close();
					
				}
		    
		    }
	
	
	/*--------------------------------------------------------------------------------*/
	
	DefaultTableModel tablemodel;
	
    public ConexionTableModel(String query) throws SQLException {
		// TODO Auto-generated constructor stub
		tablemodel=new DefaultTableModel();
		tablemodel= EjecutaQuery(query);
	}

	public DefaultTableModel getTablemodel() {
		return tablemodel;
	}

	public void setTablemodel(DefaultTableModel tablemodel) {
		this.tablemodel = tablemodel;
	}

	public DefaultTableModel EjecutaQuery(String query) throws
	SQLException
	{
		DefaultTableModel dftable=new DefaultTableModel();
		String Usuario="Alejandro";
		String Contrasenia="12345";
		String URL="jdbc:mysql://localhost/tienda2015";
		
		
		Connection conn = null;
		Statement stmnt= null;
		ResultSet rs= null;
	
		try{
     	conn=(Connection) DriverManager.getConnection(URL,Usuario,Contrasenia);
		stmnt=conn.createStatement();
		rs=stmnt.executeQuery(query);
		if(rs!=null)
		{
			int columnas=rs.getMetaData().getColumnCount();
			for (int i = 0; i < columnas; i++) {
			dftable.addColumn(rs.getMetaData().getColumnLabel(i+1));
			}
			
			Object [] fila =new Object[columnas];
			while(rs.next())
			{
				for (int i = 0; i < columnas; i++) {
					fila[i]=rs.getObject(i+1);
				}
				dftable.addRow(fila);
			}
			return dftable;
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(rs!=null)
				rs.close();
			if(stmnt!=null)
				stmnt.close();
			if(conn!=null)
			
			conn.close();
		}
	return null;
	
 }
	
//select CODIGO_BARRA,CANTIDAD,DESCRIPCION,PRECIO_UNITARIO from productos	
}
	
	

