package Punto_de_venta;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conectar {
	
    Connection conectar=null;
    public Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/tienda2015","Alejandro","12345");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }
	
	/*
	public static void main(String[] args) throws SQLException {
		String Usuario="Alejandro";
		String Contraseña="12345";
		String URL="jdbc:mysql://localhost/tienda2015";
		
		Connection conn = null;
		Statement stmnt= null;
		ResultSet rs= null;
		
		try{
     	conn=(Connection) DriverManager.getConnection(URL,Usuario,Contraseña);
		stmnt=conn.createStatement();
		rs=stmnt.executeQuery("select * from productos");
		while(rs.next())
		{
			System.out.println(rs.getInt("id_producto") +" "+
		rs.getString("nombre")+" "
		+ rs.getDouble("precio")+"\n");
		}
		
		
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally
		{
			if(rs!=null)
				rs.close();
			if(stmnt!=null)
				stmnt.close();
			if(conn!=null)
			
			conn.close();
		}
		}

	public java.sql.Connection conexion() {
		// TODO Auto-generated method stub
		return null;
	}*/
}