package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import Datos.Counter;
import Datos.Paquete;
import Datos.Revista;
import Datos.Sobre;
import extra.Fecha;

public class VConsultarArticulos extends JFrame implements MouseListener{
	private String[] columnas = {"ID.Cliente","Id.Artículo","Entregado","Descripción","Remitente","Fecha","Extra"};
	private Object[][] info = {};
	private DefaultTableModel tabla= new DefaultTableModel(info,columnas);
	private JTable table;
	private JButton bFiltrar = new JButton("Filtrar");
	private JButton bVolver = new JButton("Volver");
	private JRadioButton rbTodos=new JRadioButton("Todos"),rbPaquetes=new JRadioButton("Paquetes"),rbSobres=new JRadioButton("Sobres"),rbRevistas=new JRadioButton("Revistas");
	private JRadioButton rbEntregadoSI=new JRadioButton("SI"),rbEntregadoNO=new JRadioButton("NO"),rbEntregadoTodos=new JRadioButton("Todos");
	private JLabel lIdentificador=new JLabel("ID.Cliente:"),lRemitente=new JLabel("Remitente:"),lTipo=new JLabel("Tipo:"),lEntregado=new JLabel("Entregado:");
	private JTextField tfIdentificador=new JTextField(),tfRemitente=new JTextField();
	
	JButton bBuscar=new JButton("Buscar");
	JCheckBox bBuscarEnFecha=new JCheckBox("Fecha");
	
	JCalendar calendario = new JCalendar();
	
	public VConsultarArticulos(){
		this.setBounds(50,50,800,600);
		this.setTitle("Consultar Artículos");
		this.setLayout(null);
		this.setVisible(false);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.cyan);
		
		ButtonGroup selector = new ButtonGroup();
		lTipo.setBounds(10,1,100,30);
		lTipo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbTodos.setBounds(110,1,100,28);
		rbTodos.setOpaque(false);
		rbTodos.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbPaquetes.setBounds(210,1,100,28);
		rbPaquetes.setOpaque(false);
		rbPaquetes.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbSobres.setBounds(310,1,100,28);
		rbSobres.setOpaque(false);
		rbSobres.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbRevistas.setBounds(410,1,100,28);
		rbRevistas.setOpaque(false);
		rbRevistas.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		selector.add(rbTodos);
		selector.add(rbPaquetes);
		selector.add(rbSobres);
		selector.add(rbRevistas);
		rbTodos.setSelected(true);
		add(lTipo);
		add(rbTodos);
		add(rbPaquetes);
		add(rbSobres);
		add(rbRevistas);
		
		ButtonGroup selector2 = new ButtonGroup();
		lEntregado.setBounds(10,30,100,30);
		lEntregado.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbEntregadoTodos.setBounds(110,30,100,28);
		rbEntregadoTodos.setOpaque(false);
		rbEntregadoTodos.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbEntregadoSI.setBounds(210,30,100,28);
		rbEntregadoSI.setOpaque(false);
		rbEntregadoSI.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbEntregadoNO.setBounds(310,30,100,28);
		rbEntregadoNO.setOpaque(false);
		rbEntregadoNO.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		selector2.add(rbEntregadoTodos);
		selector2.add(rbEntregadoSI);
		selector2.add(rbEntregadoNO);
		rbEntregadoTodos.setSelected(true);
		add(lEntregado);
		add(rbEntregadoTodos);
		add(rbEntregadoSI);
		add(rbEntregadoNO);
		
		lIdentificador.setBounds(520,1,90,30);
		lIdentificador.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfIdentificador.setBounds(600,1,70,30);
		tfIdentificador.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		
		lRemitente.setBounds(520,30,90,30);
		lRemitente.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfRemitente.setBounds(600,30,70,30);
		tfRemitente.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		
		add(lIdentificador);
		add(tfIdentificador);
		add(lRemitente);
		add(tfRemitente);
		
		bFiltrar.setBounds(680,10,90,30);
		bFiltrar.addMouseListener(this);
		bFiltrar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bFiltrar.setBackground(Color.white);
		add(bFiltrar);
		
		bVolver.setBounds(680,520,100,30);
		bVolver.addMouseListener(this);
		bVolver.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bVolver.setBackground(Color.white);
		this.add(bVolver);
		
		table = new JTable(tabla);
		table.setPreferredScrollableViewportSize(new Dimension(50,50));
		table.setFillsViewportHeight(true);
		table.addMouseListener(this);
		
		calendario.setBounds(10,440,200,130);
		add(calendario);
		
		bBuscarEnFecha.setBounds(220,500,100,30);
		bBuscarEnFecha.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bBuscarEnFecha.setBackground(Color.white);
		this.add(bBuscarEnFecha);
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(10,60,757,360);
		this.add(scrollPane);
	}
	//Función que se encarga de realizar diferentes tipos de filtros en los artículos registrados
	public void agregaValores(){
		boolean fCliente=true,fRemitente=true;
		if(tfIdentificador.getText().isEmpty()){
			fCliente=false;
		}
		if(tfRemitente.getText().isEmpty()){
			fRemitente=false;
		}
		for(int i=0;i<Counter.getClientes().size();i++){
			for(int j=0;j<Counter.getClientes().get(i).getCasillero().getArticulos().size();j++){
				boolean cumpleFiltros=true;
				Object[] entregable = new Object[7];
				entregable[0]=Counter.getClientes().get(i).getCasillero().getArticulos().get(j).getNumReferencia();
				entregable[1]=Counter.getClientes().get(i).getCasillero().getArticulos().get(j).getIdentificadorArticulo();
				if(Counter.getClientes().get(i).getCasillero().getArticulos().get(j).isEntregado()){
					entregable[2]="SI";
				}else{
					entregable[2]="NO";
				}
				entregable[3]=Counter.getClientes().get(i).getCasillero().getArticulos().get(j).getDescripcion();
				entregable[4]=Counter.getClientes().get(i).getCasillero().getArticulos().get(j).getRemitente();
				entregable[5]=Counter.getClientes().get(i).getCasillero().getArticulos().get(j).getFecha().toString();
				if(Counter.getClientes().get(i).getCasillero().getArticulos().get(j) instanceof Paquete){
					entregable[6]=((Paquete)Counter.getClientes().get(i).getCasillero().getArticulos().get(j)).getExtra();
				}else if(Counter.getClientes().get(i).getCasillero().getArticulos().get(j) instanceof Sobre){
					entregable[6]=((Sobre)Counter.getClientes().get(i).getCasillero().getArticulos().get(j)).getExtra();
				}else{
					entregable[6]=((Revista)Counter.getClientes().get(i).getCasillero().getArticulos().get(j)).getExtra();
				}
				if(fCliente && (entregable[0].toString().equals(tfIdentificador.getText())==false)){
					cumpleFiltros=false;
				}
				if(fRemitente && (entregable[4].equals(tfRemitente.getText())==false)){
					cumpleFiltros=false;
				}
				if(rbEntregadoSI.isSelected() && entregable[2]!="SI"){
					cumpleFiltros=false;
				}
				if(rbEntregadoNO.isSelected() && entregable[2]!="NO"){
					cumpleFiltros=false;
				}
				if(rbPaquetes.isSelected() && (Counter.getClientes().get(i).getCasillero().getArticulos().get(j) instanceof Paquete)==false){
					cumpleFiltros=false;
				}
				if(rbRevistas.isSelected() && (Counter.getClientes().get(i).getCasillero().getArticulos().get(j) instanceof Revista)==false){
					cumpleFiltros=false;
				}
				if(rbSobres.isSelected() && (Counter.getClientes().get(i).getCasillero().getArticulos().get(j) instanceof Sobre)==false){
					cumpleFiltros=false;
				}
				if(bBuscarEnFecha.isSelected()){
					if(calendario.getDayChooser().getDay()==Counter.getClientes().get(i).getCasillero().getArticulos().get(j).getFecha().getDay() &&
							calendario.getMonthChooser().getMonth()==Counter.getClientes().get(i).getCasillero().getArticulos().get(j).getFecha().getMonth() &&
							calendario.getYearChooser().getYear()==Counter.getClientes().get(i).getCasillero().getArticulos().get(j).getFecha().getYear()){
						//Correcto
					}else{
						cumpleFiltros=false;
					}
				}
				if(cumpleFiltros){
					tabla.addRow(entregable);
				}
			}
		}
	}
	//Reinicia los elementos de la ventana como los JTextField,los RadioButton y la tabla  
	public void reinicar(){
		rbTodos.setSelected(true);
		rbEntregadoTodos.setSelected(true);
		tfIdentificador.setText("");
		tfRemitente.setText("");
		Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
		calendario.getDayChooser().setDay(dia);
		calendario.getMonthChooser().setMonth(mes);
		calendario.getYearChooser().setYear(anio);
		reiniciaTabla();
	}
	public void reiniciaTabla(){
		while(tabla.getRowCount()!=0){
			tabla.removeRow(0);
		}
		agregaValores();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==bFiltrar){
			reiniciaTabla();
		}
		if(e.getSource()==bVolver){
			this.setVisible(false);
		}
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
