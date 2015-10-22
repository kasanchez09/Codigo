package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Datos.Counter;

import javax.swing.*;

import extra.ContableDia;
import extra.WebService;
import extra.imagenesAplicacion;

public class VPrincipal extends JFrame implements MouseListener, imagenesAplicacion {
	private JMenuBar barra;
    private JMenu registrar,administrar,consultar,salir;
    private JMenuItem cliente,articulo,administrar_cliente,retirar,reporte,clientes,articulos;
    private JLabel lDolarCompra,lDolarVenta;
	public Counter counter;
	private JLabel fondo;
	private WebService webService = new WebService();
	
	public VPrincipal(int casilleros,int cedula,String nombre, String direccion){
		counter=new Counter();
		counter.setCasilleros(1000+casilleros);
		counter.setCedula(cedula);
		counter.setNombre(nombre);
		counter.setDireccion(direccion);
		counter.setDolarCompra(webService.getCompra());
		counter.setDolarVenta(webService.getVenta());
		
		Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
		counter.getContableDias().add(new ContableDia(dia,mes,anio));
		
		this.setTitle("Counter:  "+nombre);
		this.setBounds(250,100,620,400);
		this.setLayout(null);
		this.setFont(new Font("Lucida Handwriting", Font.PLAIN, 36));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		barra= new JMenuBar();
		barra.setBackground(new Color(255,255,255));
	    setJMenuBar(barra);
	    
	    fondo = new JLabel("");
		fondo.setSize(new Dimension(600,300));
		this.add(fondo);
		ImageIcon portada = new ImageIcon(VPrincipal.class.getResource("/Imagenes/casillero3.png"));
		fondo.setIcon(new ImageIcon(portada.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
	    
	    registrar= new JMenu("");
	    registrar.setIcon(new ImageIcon(imagenRegistrar));
	    barra.add(registrar);

	    cliente= new JMenuItem("Registrar Cliente");
	    cliente.setIcon(new ImageIcon(imagenRegistrarCliente));
	    cliente.setBackground(new Color(255,255,255));
	    cliente.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
	    cliente.setForeground(Color.blue); 
	    registrar.add(cliente);
	    //Accion del boton, llama a la respectiva ventana
	    cliente.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent arg0){
            	counter.registrarCliente();
            }
        });
	   
	    articulo= new JMenuItem("Registrar Artículo");
	    articulo.setIcon(new ImageIcon(imagenRegistrarArticulo));
	    registrar.add(articulo);
	    articulo.setForeground(Color.blue); 
	    articulo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
	    articulo.setBackground(new Color(255,255,255));
	    articulo.addActionListener(new ActionListener() { 
	    //Accion del boton, llama a la respectiva ventana
            public void actionPerformed(ActionEvent arg0){
            	counter.registrarEntregable();
            }
        });
	    
	    consultar= new JMenu("");
	    consultar.setIcon(new ImageIcon(imagenConsultar));
	    barra.add(consultar);
	    
	    administrar= new JMenu("");
	    administrar.setIcon(new ImageIcon(imagenAdministrar));
	    barra.add(administrar);

	    administrar_cliente= new JMenuItem("Administrar Clientes");
	    administrar_cliente.setIcon(new ImageIcon(imagenAdministrarCliente));
	    administrar_cliente.setForeground(Color.blue); 
	    administrar_cliente.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
	    administrar_cliente.setBackground(new Color(255,255,255));
	    consultar.add(administrar_cliente);
	    administrar_cliente.addActionListener(new ActionListener() { 
	    //Accion del boton, llama a la respectiva ventana
	    	public void actionPerformed(ActionEvent arg0){
            	counter.administrarClientes(); 
            }
        });
	    
	    articulos= new JMenuItem("Consultar Artículos");
	    articulos.setIcon(new ImageIcon(imagenConsultarArticulo));
	    consultar.add(articulos);
	    articulos.setForeground(Color.blue); 
	    articulos.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
	    articulos.setBackground(new Color(255,255,255));
	    articulos.addActionListener(new ActionListener() { 
	    //Accion del boton, llama a la respectiva ventana
	    	public void actionPerformed(ActionEvent arg0){
            	Counter.consultarArticulos();
            }
        });
	    
	    retirar= new JMenuItem("Retirar Articulos");
	    retirar.setIcon(new ImageIcon(imagenRetirarArticulo));
	    retirar.setForeground(Color.blue); 
	    administrar.add(retirar);
	    retirar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
	    retirar.setBackground(new Color(255,255,255));
	    retirar.addActionListener(new ActionListener() { 
	    	//Funcion que pide al usuario el identificador del cliente y revisa que este sea valido
            public void actionPerformed(ActionEvent arg0){
            	String identificador = JOptionPane.showInputDialog(null, "Digite el identificador de quien desea retirar artículos");
            	if(identificador!=null){
            		boolean encontro=false;
            		for(int i=0;i<Counter.getClientes().size();i++){
            			if(Counter.getClientes().get(i).getIdentificador()==Integer.parseInt(identificador)){
            				encontro=true;
            				break;
            			}
            		}
            		if(encontro){
            			Counter.vDesplegarEntregables.reiniciar();
            			Counter.vDesplegarEntregables.setValores(Integer.parseInt(identificador));
            			Counter.vDesplegarEntregables.setVisible(false);
            			Counter.vDesplegarEntregables.setVisible(true);
            		}else{
            			JOptionPane.showMessageDialog(null,"No existe cliente asociado a este número de identificador.");
            		}
            	}
            }
        });
	    
	    reporte= new JMenuItem("Reporte Contable");
	    reporte.setIcon(new ImageIcon(imagenReporte));
	    reporte.setForeground(Color.blue); 
	    administrar.add(reporte);
	    reporte.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
	    reporte.setBackground(new Color(255,255,255));
	    reporte.addActionListener(new ActionListener() { 
	    //Accion del boton, llama a la respectiva ventana
	    	public void actionPerformed(ActionEvent arg0){
            	counter.reporteContable();
            }
        });
	    
	    salir= new JMenu("");
	    salir.setIcon(new ImageIcon(imagenSalir));
	    barra.add(salir);
	    salir.addMouseListener(new MouseAdapter() { 
	    //Accion del boton, sale de la aplicacion
	    	public void mouseClicked(MouseEvent arg0){
            	System.exit(0);
            }
	    });
	    
		this.setVisible(false);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
