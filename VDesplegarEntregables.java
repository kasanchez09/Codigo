package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import extra.ContableDia;
import extra.Mail;
import Datos.*;

public class VDesplegarEntregables extends JFrame implements MouseListener{
	private String[] columnas = {"Acción","Id.Artículo","Entregado","Descripción","Remitente","Fecha","Extra"};
	private Object[][] info = {};
	private DefaultTableModel tabla= new DefaultTableModel(info,columnas);
	private JTable table;
	private ArrayList<Integer>entregablesRetirar=new ArrayList<Integer>();
	int identificadorCliente=0;
	private String tipoDescuento="normal";
	//Valores que se añadiran al total de x dia y al total de todos los dias.
	private float descuento,impuestos,total;
	
	JTextArea taInformacion=new JTextArea();
	
	JButton bVolver=new JButton("Volver");
	JButton bRetirar=new JButton("Retirar Artículo");
	JButton bDevolver=new JButton("Devolver Artículo");
	JButton bRetirarArticulos=new JButton("Retirar artículos seleccionados");
	JButton bEnviarCorreo=new JButton("Enviar Correo");
	JButton bBuscar=new JButton("Buscar");
	JCheckBox bBuscarEnFecha=new JCheckBox("Fecha");
	
	JCalendar calendario = new JCalendar();
	
	public VDesplegarEntregables(){
		setBounds(10,10,810,500);
		setLayout(null);
		this.setTitle("Retirar Artículos");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.YELLOW);
		
		table = new JTable(tabla);
		table.setPreferredScrollableViewportSize(new Dimension(50,50));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(10,150,500,240);
		
		calendario.setBounds(10,10,200,135);
		add(calendario);
		
		bBuscarEnFecha.setBounds(220,10,100,30);
		bBuscarEnFecha.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bBuscarEnFecha.setBackground(Color.white);
		this.add(bBuscarEnFecha);
		
		bBuscar.setBounds(330,10,100,30);
		bBuscar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bBuscar.addMouseListener(this);
		bBuscar.setBackground(Color.white);
		this.add(bBuscar);
		
		taInformacion.setBounds(540,20,200,300);
		taInformacion.setEditable(false);
		taInformacion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		add(taInformacion);
		
		bDevolver.setBounds(540,325,170,30);
		bDevolver.addMouseListener(this);
		bDevolver.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bDevolver.setBackground(Color.white);
		add(bDevolver);
		
		bRetirar.setBounds(540,355,170,30);
		bRetirar.addMouseListener(this);
		bRetirar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bRetirar.setBackground(Color.white);
		add(bRetirar);
		
		bRetirarArticulos.setBounds(540,385,263,30);
		bRetirarArticulos.addMouseListener(this);
		bRetirarArticulos.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bRetirarArticulos.setBackground(Color.white);
		add(bRetirarArticulos);
		
		bVolver.setBounds(540,430,90,30);
		bVolver.addMouseListener(this);
		bVolver.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bVolver.setBackground(Color.white);
		this.add(bVolver);
		
		bEnviarCorreo.setBounds(620,430,180,30);
		bEnviarCorreo.addMouseListener(this);
		bEnviarCorreo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bEnviarCorreo.setBackground(Color.white);
		this.add(bEnviarCorreo);
		
		this.add(scrollPane);
		
		setVisible(false);
	}
	public void reiniciar(){
		Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
		calendario.getDayChooser().setDay(dia);
		calendario.getMonthChooser().setMonth(mes);
		calendario.getYearChooser().setYear(anio);
		descuento=0;
		impuestos=0;
		total=0;
	}
	public void setValores(int identificadorCliente){
		while(tabla.getRowCount()!=0){
			tabla.removeRow(0);
		}
		//Se busca entre todos los clientes y se obtiene el cliente seleccionado
		for(int k=0;k<Counter.getClientes().size();k++){
			if(Counter.getClientes().get(k).getIdentificador()==identificadorCliente){
				this.identificadorCliente=identificadorCliente;
				tipoDescuento=Counter.getClientes().get(k).getTipo();
				for(int i=0;i<Counter.getClientes().get(k).getCasillero().getArticulos().size();i++){
					Object[] entregable = new Object[7];
					entregable[0]=Counter.getClientes().get(k).getCasillero().getArticulos().get(i).getAccion();
					entregable[1]=Counter.getClientes().get(k).getCasillero().getArticulos().get(i).getIdentificadorArticulo();
					if(Counter.getClientes().get(k).getCasillero().getArticulos().get(i).isEntregado()){
						entregable[2]="Si";
					}else{
						entregable[2]="No";
					}
					entregable[3]=Counter.getClientes().get(k).getCasillero().getArticulos().get(i).getDescripcion();
					entregable[4]=Counter.getClientes().get(k).getCasillero().getArticulos().get(i).getRemitente();
					entregable[5]=Counter.getClientes().get(k).getCasillero().getArticulos().get(i).getFecha().toString();
					if(Counter.getClientes().get(k).getCasillero().getArticulos().get(i) instanceof Paquete){
						entregable[6]=((Paquete)Counter.getClientes().get(k).getCasillero().getArticulos().get(i)).getExtra();
					}else if(Counter.getClientes().get(k).getCasillero().getArticulos().get(i) instanceof Sobre){
						entregable[6]=((Sobre)Counter.getClientes().get(k).getCasillero().getArticulos().get(i)).getExtra();
					}else{
						entregable[6]=((Revista)Counter.getClientes().get(k).getCasillero().getArticulos().get(i)).getExtra();
					}
					boolean fechaCorrecta=true;
					if(bBuscarEnFecha.isSelected()){
						System.out.println("ESTA SELECCIONADOOOOOOOOOOOO");
						if(calendario.getDayChooser().getDay()==Counter.getClientes().get(k).getCasillero().getArticulos().get(i).getFecha().getDay() &&
								calendario.getMonthChooser().getMonth()==Counter.getClientes().get(k).getCasillero().getArticulos().get(i).getFecha().getMonth() &&
								calendario.getYearChooser().getYear()==Counter.getClientes().get(k).getCasillero().getArticulos().get(i).getFecha().getYear()){
							fechaCorrecta=true;
							System.out.println("SE CUMPLEEEE");
						}else{
							fechaCorrecta=false;
						}
					}
					if(fechaCorrecta){
						tabla.addRow(entregable);
					}
				}
				setInformacion();
				repaint();
			}
		}
	}
	public void setInformacion(){
		String informacion = "Tipo  cliente:  ";
		if(tipoDescuento.equals("normal")){
			informacion+="normal\nDescuento:  0%";
		}else if(tipoDescuento.equals("plata")){
			informacion+="Plata\nDescuento:  5%";
		}else{
			informacion+="Oro\nDescuento:  10%";
		}
		informacion+="\n\nEn  dolares:";
		informacion+="\nDescuento  total: $"+descuento;
		informacion+="\nImpuesto  total: $"+impuestos;
		informacion+="\nTotal:  $"+total;
		informacion+="\n\nEn  colones:";
		informacion+="\nDescuento  total:  ¢"+descuento*Float.parseFloat(Counter.getDolarCompra());
		informacion+="\nImpuesto  total:  ¢"+impuestos*Float.parseFloat(Counter.getDolarCompra());
		informacion+="\nTotal:  ¢"+total*Float.parseFloat(Counter.getDolarCompra());
		taInformacion.setText(informacion);
	}
	public void enviarCorreo(Cliente cliente){
		Counter.identificadorArticulo++;
		Mail mail = new Mail();
		String informacionArticulos="Buenas, este mensaje es de parte del counter de casilleros.\n\nArtículos pendientes: \n";
		for(int i=0;i<cliente.getCasillero().getArticulos().size();i++){
			if(cliente.getCasillero().getArticulos().get(i).isEntregado()==false){
				//Segun el tipo de entregable, el toString se realiza de diferente manera.
				if(cliente.getCasillero().getArticulos().get(i) instanceof Paquete ){
					informacionArticulos+=((Paquete)cliente.getCasillero().getArticulos().get(i)).toString();
				}else if(cliente.getCasillero().getArticulos().get(i) instanceof Sobre ){
					informacionArticulos+=((Sobre)cliente.getCasillero().getArticulos().get(i)).toString();
				}else if(cliente.getCasillero().getArticulos().get(i) instanceof Revista ){
					informacionArticulos+=((Revista)cliente.getCasillero().getArticulos().get(i)).toString();
				}
				informacionArticulos+="\n";
				
			}
		}
		informacionArticulos+="\nFavor retirarlos lo más pronto posible";
		//System.out.println(informacionArticulos);
		mail.enviar(cliente.getCorreo(), informacionArticulos);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Integer numero=table.getSelectedRow();
		if(e.getSource()==bRetirar && numero!=-1){
			for(int k=0;k<Counter.getClientes().size();k++){
				//Función para obtener eun cliente específico entre todos y se busca entre los artículos el que se desea retirar.
				if(Counter.getClientes().get(k).getIdentificador()==identificadorCliente){
					for(int j=0;j<Counter.getClientes().get(k).getCasillero().getArticulos().size();j++){
						if(Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getIdentificadorArticulo()==(int)table.getValueAt(numero, 1)){
							if(Counter.getClientes().get(k).getCasillero().getArticulos().get(j).isEntregado()==false){
								boolean esta=false;
								//Comprobación de si el árticulo ya está en la lista de artículos a entregar.
								for(int i=0;i<entregablesRetirar.size();i++){
									if(entregablesRetirar.get(i)==Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getIdentificadorArticulo()){
										esta=true;
										break;
									}
								}
								if(esta==false){
									Counter.getClientes().get(k).getCasillero().getArticulos().get(j).setAccion("Retirar");
									descuento+=Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getDescuento();
									impuestos+=Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getImpuestos();
									total+=Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getTotal();
									entregablesRetirar.add(Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getIdentificadorArticulo());
								}
							}else{
								JOptionPane.showMessageDialog(null,"Este artículo ya esta entregado");
							}
							break;
						}
					}
					break;
				}
			}
			setValores(identificadorCliente);
			setInformacion();
		}
		if(e.getSource()==bDevolver && numero!=-1){
			for(int k=0;k<Counter.getClientes().size();k++){
				//Se obtiene el artículo que se desea marcar como "no retirar" entre una persona específica.
				if(Counter.getClientes().get(k).getIdentificador()==identificadorCliente){
					for(int j=0;j<Counter.getClientes().get(k).getCasillero().getArticulos().size();j++){
						if(Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getIdentificadorArticulo()==(int)table.getValueAt(numero, 1)){
							if(Counter.getClientes().get(k).getCasillero().getArticulos().get(j).isEntregado()==false){
								for(int i=0;i<entregablesRetirar.size();i++){
									if(entregablesRetirar.get(i)==Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getIdentificadorArticulo()){
										entregablesRetirar.remove(i);
										Counter.getClientes().get(k).getCasillero().getArticulos().get(j).setAccion("Ninguna");
										descuento-=Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getDescuento();
										impuestos-=Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getImpuestos();
										total-=Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getTotal();
										break;
									}
								}
							}else{
								JOptionPane.showMessageDialog(null,"Este artículo ya esta entregado");
							}
							break;
						}
					}
					break;
				}
			}
			setValores(identificadorCliente);
			setInformacion();
		}
		if(e.getSource()==bRetirarArticulos){
			if(entregablesRetirar.size()>0){
				String numeroTarjeta="";
				String[] listaPago = {"Contado", "MASTER-CARD", "VISA", "AMERICAN-EXPRESS"};
				String tipoPago = (String) JOptionPane.showInputDialog(null, "¿Cuál va a ser el tipo de pago?","Pago: ",JOptionPane.QUESTION_MESSAGE, null, listaPago, listaPago[0]);
				if(tipoPago!=null){
					boolean contado=true;
					if(tipoPago.equals("Contado")==false){
						contado=false;
						numeroTarjeta = JOptionPane.showInputDialog(null, "Digite el número de tarjeta:");
					}
					if(contado || numeroTarjeta!=null){
						for(int k=0;k<Counter.getClientes().size();k++){
							//Se obtienen los artículos marcados como "retirar" y se retiran, entregan.
							if(Counter.getClientes().get(k).getIdentificador()==identificadorCliente){
								for(int i=0;i<entregablesRetirar.size();i++){
									for(int j=0;j<Counter.getClientes().get(k).getCasillero().getArticulos().size();j++){
										if(Counter.getClientes().get(k).getCasillero().getArticulos().get(j).getIdentificadorArticulo()==entregablesRetirar.get(i)){
											Counter.getClientes().get(k).getCasillero().getArticulos().get(j).setEntregado(true);
											Counter.getClientes().get(k).getCasillero().getArticulos().get(j).setAccion("Ninguna");
											break;
										}
									}
								}
								enviarCorreo(Counter.getClientes().get(k));
								entregablesRetirar.clear();
								
								//Se busca el dia actual entre los dias creados y se le suman los valores del retiro.
								Calendar fecha = new GregorianCalendar();
						        int anio = fecha.get(Calendar.YEAR);
						        int mes = fecha.get(Calendar.MONTH);
						        int dia = fecha.get(Calendar.DAY_OF_MONTH);
								Counter.obtenerContableDia(dia, mes, anio).sumaDescuentos(descuento);
								Counter.obtenerContableDia(dia, mes, anio).sumaImpuestos(impuestos);
								Counter.obtenerContableDia(dia, mes, anio).sumaTotal(total);
								Counter.setDescuentoTotal(Counter.getDescuentoTotal()+descuento);
								Counter.setImpuestoTotal(Counter.getImpuestoTotal()+impuestos);
								Counter.setMontoTotal(Counter.getMontoTotal()+total);
								break;
							}
						}
						setValores(identificadorCliente);
					}
				}
				reiniciar();
				setInformacion();
			}else{
				JOptionPane.showMessageDialog(null,"No hay artículos marcados para retirar");
			}
		}
		if(e.getSource()==bVolver){
			this.setVisible(false);
		}
		if(e.getSource()==bEnviarCorreo){
			for(int k=0;k<Counter.getClientes().size();k++){
				if(Counter.getClientes().get(k).getIdentificador()==identificadorCliente){
					enviarCorreo(Counter.getClientes().get(k));
					break;
				}
			}
		}
		if(e.getSource()==bBuscar){
			System.out.println("EN BUSCAAAAARRR");
			setValores(identificadorCliente);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
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
