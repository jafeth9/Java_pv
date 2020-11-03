package Punto_de_venta;

import java.util.concurrent.CompletableFuture;

import javax.swing.JOptionPane;

public class pruebas {

	public static void main(String[] args) {
		//MostrarClientes i=new MostrarClientes();
		//i.setVisible(true);
		SqlOperaciones instancia=new SqlOperaciones();
		int resultado=instancia.obtenerCantidadTablaProducto(1);
		System.out.println(resultado);
		instancia.actualizarCantidadProductos(1, 3, resultado);
	}
}
