package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

import Datos.Counter;

import com.toedter.calendar.JCalendar;

import extra.Fecha;

public class VReporteContable extends JFrame implements MouseListener {
	JLabel lDescuentoTotal,lDescuentoDia,lImpuestoTotal,lImpuestoDia,lMontoTotal,lMontoDia,lFecha,lDolarCompra,lDolarVenta;
	JTextField tDescuentoTotal,tDescuentoDia,tImpuestoTotal,tImpuestoDia,tMontoTotal,tMontoDia;
	JButton bBuscar=new JButton("Buscar");
	JButton bVolver=new JButton("Volver");
	JCalendar calendario=new JCalendar();
	JLabel fondo;
	
	public VReporteContable(){
		this.setBounds(50,50,500,400);
		this.setTitle("Reporte Contable");
		this.setLayout(null);
		this.setVisible(false);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.gray);
		
		lFecha=new JLabel("Fecha:");
	    lFecha.setBounds(10,20,100,30);
	    lFecha.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
	    calendario.setBounds(90,20,200,100);
	    this.add(lFecha);
	    this.add(calendario);
	    
	    bBuscar.setBounds(340,45,100,30);
		bBuscar.addMouseListener(this);
		bBuscar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bBuscar.setBackground(Color.white);
		this.add(bBuscar);
	     
	    lDescuentoDia=new JLabel("Descuento por día:");
	    lDescuentoDia.setBounds(10,140,150,30);
	    lDescuentoDia.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tDescuentoDia=new JTextField();
		tDescuentoDia.setBounds(150,140,100,30);
		tDescuentoDia.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lDescuentoDia);
		this.add(tDescuentoDia);
			
		lImpuestoDia=new JLabel("Impuesto por día:");
		lImpuestoDia.setBounds(10,190,150,30);
		lImpuestoDia.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tImpuestoDia=new JTextField();
		tImpuestoDia.setBounds(150,190,100,30);
		tImpuestoDia.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lImpuestoDia);
		this.add(tImpuestoDia);
		
		lMontoDia=new JLabel("Monto por día:");
	    lMontoDia.setBounds(10,230,150,30);
	    lMontoDia.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tMontoDia=new JTextField();
		tMontoDia.setBounds(150,230,100,30);
		tMontoDia.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lMontoDia);
		this.add(tMontoDia);
			
		lDescuentoTotal=new JLabel("Descuento total:");
	    lDescuentoTotal.setBounds(270,140,150,30);
	    lDescuentoTotal.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tDescuentoTotal=new JTextField();
		tDescuentoTotal.setBounds(390,140,100,30);
		tDescuentoTotal.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lDescuentoTotal);
		this.add(tDescuentoTotal);
		
		lImpuestoTotal=new JLabel("Impuesto total:");
		lImpuestoTotal.setBounds(270,190,150,30);
		lImpuestoTotal.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tImpuestoTotal=new JTextField();
		tImpuestoTotal.setBounds(390,190,100,30);
		tImpuestoTotal.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lImpuestoTotal);
		this.add(tImpuestoTotal);
		
		lMontoTotal=new JLabel("Monto total:");
	    lMontoTotal.setBounds(270,230,150,30);
	    lMontoTotal.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tMontoTotal=new JTextField();
		tMontoTotal.setBounds(390,230,100,30);
		tMontoTotal.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lMontoTotal);
		this.add(tMontoTotal);
	   
		bVolver.setBounds(390,300,100,30);
		bVolver.addMouseListener(this);
		bVolver.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bVolver.setBackground(Color.white);
		this.add(bVolver);
		
		lDolarVenta=new JLabel("Dolar venta: "+Counter.getDolarVenta());
	    lDolarCompra=new JLabel("Dolar Compra: "+Counter.getDolarCompra());
	    lDolarVenta.setBounds(10,300,150,30);
	    lDolarCompra.setBounds(200,300,150,30);
	    
	    add(lDolarCompra);
	    add(lDolarVenta);
		
		tDescuentoTotal.setEditable(false);
		tDescuentoDia.setEditable(false);
		tImpuestoTotal.setEditable(false);
		tImpuestoDia.setEditable(false);
		tMontoTotal.setEditable(false);
		tMontoDia.setEditable(false);
		
		fondo = new JLabel("");
		fondo.setSize(new Dimension(500,400));
		this.add(fondo);
		ImageIcon portada = new ImageIcon(VPrincipal.class.getResource("/Imagenes/fondoReporteContable.png"));
		fondo.setIcon(new ImageIcon(portada.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		repaint();
		
		reiniciar();
	}
	
	public void reiniciar(){
		//Se pone el calendario en la fecha actual
		Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
		calendario.getDayChooser().setDay(dia);
		calendario.getMonthChooser().setMonth(mes);
		calendario.getYearChooser().setYear(anio);
		buscar();
	}
	public void buscar(){
		//Se obtiene del counter la fecha seleccionada en el calendario y se despliegan los valores en pantalla
		lDolarVenta.setText("Dolar venta:   "+Counter.getDolarVenta());
	    lDolarCompra.setText("Dolar Compra:   "+Counter.getDolarCompra());
		tMontoTotal.setText(String.valueOf(Counter.getMontoTotal()));
		tImpuestoTotal.setText(String.valueOf(Counter.getImpuestoTotal()));
		tDescuentoTotal.setText(String.valueOf(Counter.getDescuentoTotal()));
		tDescuentoDia.setText(String.valueOf(Counter.obtenerContableDia(calendario.getDayChooser().getDay(),calendario.getMonthChooser().getMonth(),calendario.getYearChooser().getYear()).getDescuentosTotales()));
		tImpuestoDia.setText(String.valueOf(Counter.obtenerContableDia(calendario.getDayChooser().getDay(),calendario.getMonthChooser().getMonth(),calendario.getYearChooser().getYear()).getImpuestosTotales()));
		tMontoDia.setText(String.valueOf(Counter.obtenerContableDia(calendario.getDayChooser().getDay(),calendario.getMonthChooser().getMonth(),calendario.getYearChooser().getYear()).getMontoTotal()));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==bVolver){
			this.setVisible(false);
		}
		else if(e.getSource()==bBuscar){
			buscar();
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