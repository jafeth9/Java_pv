package Punto_de_venta;

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
	
	public boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
