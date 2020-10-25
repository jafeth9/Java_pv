package Punto_de_venta;
import java.sql.*;
public class Insertar {
	
	static Statement stmnt=null;
    static ResultSet rs=null;
	
	public static void main(String[] args) throws Exception {
		String Usuario="Alejandro";
		String Contraseña="12345";
		String URL="jdbc:mysql://localhost/tienda2015";
		
	    java.sql.Connection conn=null;
	    
		
		try{
		conn=DriverManager.getConnection(URL,Usuario,Contraseña);
		stmnt=conn.createStatement();
		//para insertar 
		//stmnt.executeUpdate("insert into productos(nombre,precio)values(\'Azucar\',23);");
		//para borrar
		//stmnt.executeUpdate("delete from productos where id_producto=12;");
		}catch (SQLException e){
			e.printStackTrace();
		}catch (ClassCastException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			if(rs!=null)
				rs.close();
			if(conn!=null)
				conn.close();
			if(conn !=null)
				conn.close();
		
		}
		
	}
	
}