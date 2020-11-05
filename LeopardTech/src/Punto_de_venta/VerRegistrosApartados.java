package Punto_de_venta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTable;

public class VerRegistrosApartados extends JDialog {
	Conectar cc= new Conectar();
    Connection cn= cc.conexion();
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	public static int idApartado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerRegistrosApartados dialog = new VerRegistrosApartados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarRegistroApartado(int idApartado) {
		Statement st=null;
		ResultSet rs=null;
    	DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("Id apartado");
        modelo.addColumn("Dinero recibido");
        modelo.addColumn("Abono");
        modelo.addColumn("Cambio Entregado");
        modelo.addColumn("Fecha del Abono");
       
        String sql;
        sql="SELECT fk_id_apartado,dinero_recibido,abono,cambio_entregado,fecha_abono FROM registro_apartados WHERE fk_id_apartado="+idApartado+"";
        String []datos = new String [5];
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
            	
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
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
				cn.close();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
    }
	
	/**
	 * Create the dialog.
	 */
	
	public VerRegistrosApartados() {
		setResizable(false);
		setBounds(100, 100, 632, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 596, 262);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel labelTituloRegistroApartados = new JLabel("Registro del apartado");
		labelTituloRegistroApartados.setForeground(Color.RED);
		labelTituloRegistroApartados.setBounds(10, 13, 112, 14);
		contentPanel.add(labelTituloRegistroApartados);
		mostrarRegistroApartado(VerRegistrosApartados.idApartado);
		
	}
}
