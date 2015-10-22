package Interfaz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;

import com.toedter.calendar.JCalendar;

import Datos.Casillero;
import Datos.Cliente;
import extra.Fecha;
import extra.Validaciones;

public class VLogin extends JFrame implements MouseListener{
	JButton bIniciarSesion=new JButton("Iniciar Sesión");
	JLabel lNombre,lDireccion,lCasillero,lCedula;
	JTextField tfNombre,tfCasillero,tfCedula;
	JTextArea taDireccion;
	VPrincipal vPrincipal;
	JLabel fondo;
	
	public VLogin(){
		this.setTitle("Iniciar Sesión");
		this.setBounds(400,100,300,350);
		this.setLayout(null);
		this.setResizable(false);
		
		lNombre=new JLabel("Nombre");
		lNombre.setBounds(10,10,100,30);
		tfNombre=new JTextField();
		tfNombre.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		lNombre.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfNombre.setBounds(120,10,100,30);
		this.add(lNombre);
		this.add(tfNombre);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		lDireccion=new JLabel("Dirección");
		lDireccion.setBounds(10,60,100,30);
		lDireccion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		taDireccion=new JTextArea();
		taDireccion.setBounds(120,60,100,50);
		taDireccion.setBorder(border);
		taDireccion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lDireccion);
		this.add(taDireccion);
		
		lCasillero=new JLabel("Casillero");
		lCasillero.setBounds(10,130,100,30);
		lCasillero.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfCasillero=new JTextField();
		tfCasillero.setBounds(120,130,100,30);
		tfCasillero.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lCasillero);
		this.add(tfCasillero);
		
		lCedula=new JLabel("Cédula");
		lCedula.setBounds(10,180,100,30);
		lCedula.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfCedula=new JTextField();
		tfCedula.setBounds(120,180,100,30);
		tfCedula.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lCedula);
		this.add(tfCedula);
		
        bIniciarSesion.setBounds(10,250,135,30);
        bIniciarSesion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		bIniciarSesion.addMouseListener(this);
		bIniciarSesion.setBackground(Color.white);
		this.add(bIniciarSesion);
		
		fondo = new JLabel("");
		fondo.setSize(new Dimension(300,350));
		this.add(fondo);
		ImageIcon portada = new ImageIcon(VPrincipal.class.getResource("/Imagenes/fondoLogin.png"));
		fondo.setIcon(new ImageIcon(portada.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		repaint();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void reiniciar(){
		tfNombre.setText("");
		taDireccion.setText("");
		tfCasillero.setText("");
		tfCedula.setText("");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==bIniciarSesion){
			//Se realizan las validaciones y posteriormente se inicializa la ventana principal
			String nombre=tfNombre.getText(),casillero=tfCasillero.getText(),cedula=tfCedula.getText(),direccion=taDireccion.getText();
			if(nombre.isEmpty() || casillero.isEmpty() || cedula.isEmpty() || direccion.isEmpty()){
				JOptionPane.showMessageDialog(null,"Por favor rellene todos los datos");
			}else if(Validaciones.confirmarTelefono(casillero)==false){
				JOptionPane.showMessageDialog(null,"Ingrese un número de casillero correcto");
			}else if(Validaciones.confirmarTelefono(cedula)==false){
				JOptionPane.showMessageDialog(null,"Ingrese un número de cédula correcto");
			}else{
				vPrincipal=new VPrincipal(Integer.parseInt(casillero),Integer.parseInt(cedula),nombre,direccion);
				setVisible(false);
				vPrincipal.setVisible(true);
			}
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
	public static void main(String[] args) {
		VLogin aplicacion=new VLogin();
		aplicacion.setVisible(true);
	}
}
