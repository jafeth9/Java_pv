package Punto_de_venta;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerClientes extends JFrame {
    public static boolean verClientesClosed=false;
	private JPanel contentPane;
	private JTable table;
	Conectar cc= new Conectar();
    Connection cn= cc.conexion();
    private JButton buttonNuevoCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerClientes frame = new VerClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}

	public void mostrarClientes() {
		Statement st=null;
		ResultSet rs=null;
    	DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("Id cliente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Segundo nombre");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
       
        String sql;
        sql="SELECT * FROM clientes";
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
	 * Create the frame.
	 */
	
	public VerClientes() {
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 554, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton buttonAsignar = new JButton("Asignar compra");
		buttonAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila=table.getSelectedRow();
				if (fila>=0) {
					PuntoDeVenta.setClienteId=Integer.parseInt(table.getValueAt(fila, 0).toString());
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "no selecciono cliente");
					
				}
			}
		});
		buttonAsignar.setBounds(10, 261, 125, 35);
		contentPane.add(buttonAsignar);
		
		buttonNuevoCliente = new JButton("NuevoCliente");
		buttonNuevoCliente.setBounds(420, 261, 144, 35);
		contentPane.add(buttonNuevoCliente);
		
		
	
		mostrarClientes();
		addWindowListener(new OyenteVentana());
		
	}


}



/*--------------------##########################################------------------------------------*/
class OyenteVentana extends WindowAdapter{

	

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	    VerClientes.verClientesClosed=true;
	    System.out.println("ventana cerrada");
	    
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("ventana Minimizada");
	}
	
	public void windowOpened(WindowEvent e) {
		System.out.println("Ventana Abierta");
		VerClientes.verClientesClosed=false;
	}



	

	
}
