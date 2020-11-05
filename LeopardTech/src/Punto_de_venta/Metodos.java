package Punto_de_venta;

import java.sql.Connection;

import javax.swing.JOptionPane;

public class Metodos {

	
	/*public void validarCantidadProducto(int fila ) {
		String resultadoString;
		if(fila>=0) {
			
			try {
				do {
					resultadoString=JOptionPane.showInputDialog("CANTIDAD DE PRODUCTOS A APARTAR");
					if(isNumeric(resultadoString)==false && !resultadoString.equals("")) {
					JOptionPane.showMessageDialog(null, "Igrese un numero !","error",JOptionPane.ERROR_MESSAGE);
					
				    }
				} while (resultadoString.equals("") || isNumeric(resultadoString)==false );
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			JOptionPane.showMessageDialog(null, "no selecciono fila");
		}
	}*/
	
	public boolean validarEntradaCantidad(String entrada) {
		boolean resultado=true;
		if (entrada.equals("")) {
			JOptionPane.showMessageDialog(null, "El campo esta vacio","error",JOptionPane.ERROR_MESSAGE);
			resultado= false;
		}else if(isNumeric(entrada)) {
		    
			int numero=Integer.parseInt(entrada);
		    if(numero<=0) {
		       JOptionPane.showMessageDialog(null, "Numero no valido: debe ser mayor a cero","error",JOptionPane.ERROR_MESSAGE);	
			   resultado=false;
		    }
		}else {
		   JOptionPane.showMessageDialog(null, "Ingresa un numero y entero, no decimal!","error",JOptionPane.ERROR_MESSAGE);
		   return resultado=false;
		}
		
		return resultado;
	}
	public boolean validarCantidadProducto(String entrada, int cantidadTabla) {
		boolean resultado=true;
		int cantidad=Integer.parseInt(entrada);
		if(cantidad>cantidadTabla) {
			JOptionPane.showMessageDialog(null,"Producto insuficiente","Aviso!",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return resultado;
	}
	
	public boolean validarEntradaApartados(String entrada) {
		boolean resultado=true;
		if (entrada.equals("")) {
			JOptionPane.showMessageDialog(null, "El campo esta vacio","error",JOptionPane.ERROR_MESSAGE);
			resultado= false;
		}
		else if(isDouble(entrada)==false) {
			resultado=false;
			JOptionPane.showMessageDialog(null, "Ups,al parecer no ingresaste un numero o escribiste una , entre los numeros","error",JOptionPane.ERROR_MESSAGE);
		}else if(isDouble(entrada)) {
			if(Double.parseDouble(entrada)<=0) {
				   JOptionPane.showMessageDialog(null, "El numero es negativo o igual a cero","error",JOptionPane.ERROR_MESSAGE);
				   resultado=false;
			   }
		}
		return resultado;
	}
	
   public boolean validarCantidadAbono(String entrada,int id_apartado) {
	   Conectar cc= new Conectar();
	   Connection cn= cc.conexion();
	   SqlOperaciones instancia =new SqlOperaciones();
	   boolean resultado=true;
	   
	   double precioTotal=instancia.obtenerPrecioTotalTablaApartados(id_apartado);
	   double abono=Double.parseDouble(entrada);
	   double abonoTotal=instancia.obtenerTotalAbonoTablaApartados(id_apartado);
	   double abonoAcumulado;
	   double deuda=instancia.obtenerDeudaTablaApartados(id_apartado);
	   
	   abonoAcumulado=abonoTotal+abono;
	   if(abonoAcumulado>precioTotal) {
		   JOptionPane.showMessageDialog(null, "hey! ya tan solo la deuda es de: "+deuda+" ingresa la cantidad que corresponde al abono","error",JOptionPane.ERROR_MESSAGE);
		   resultado=false;
	   }
	  
	   return resultado;
   }
   

   
     public boolean entradasValidas(String entrada1,String entrada2) {
    	 boolean resultado=true;
    	 double entradaDineroRecibido=Double.parseDouble(entrada1);
    	 double entradaCantidadAbono=Double.parseDouble(entrada2);
    	 if(entradaDineroRecibido<entradaCantidadAbono) {
    		 JOptionPane.showMessageDialog(null,"El dinero recibido del cliente es menor a la cantidad que va a abonar","Atencion",JOptionPane.WARNING_MESSAGE);
    		 resultado=false;
    	 }
    	 return resultado;
     }
	
	public boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public boolean isDouble(String cadena){
		try {
			Double.parseDouble((cadena));
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
