package Interfaz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.Border;
import javax.swing.*;

import com.toedter.calendar.JCalendar;

import Datos.Casillero;
import Datos.Cliente;
import Datos.Counter;
import extra.ContableDia;
import extra.Fecha;
import extra.Validaciones;

public class VRegistrarCliente extends JFrame implements MouseListener{
	JButton bRegistrar=new JButton("Registrar");
	JLabel lIdentificador,lNombre,lCorreo,lTelefono,lDireccion,lSexo,lFechaNacimiento,lFecha,fondo;
	JTextField tfIdentificador,tfNombre,tfCorreo,tfTelefono;
	JTextArea taDireccion;
	JRadioButton rbHombre,rbMujer;
	JButton bVolver=new JButton("Volver");
	JCalendar calendario=new JCalendar();
	
	public VRegistrarCliente(){
		this.setTitle("Registrar Cliente");
		this.setBounds(200,60,500,600);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		lIdentificador=new JLabel("Identificador:");
		lIdentificador.setBounds(10,10,120,30);
		lIdentificador.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfIdentificador=new JTextField();
		tfIdentificador.setBounds(120,10,100,30);
		tfIdentificador.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lIdentificador);
		this.add(tfIdentificador);
		
		lNombre=new JLabel("Nombre:");
		lNombre.setBounds(10,60,100,30);
		lNombre.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfNombre=new JTextField();
		tfNombre.setBounds(120,60,100,30);
		tfNombre.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lNombre);
		this.add(tfNombre);
		
		lCorreo=new JLabel("Correo:");
		lCorreo.setBounds(10,110,100,30);
		lCorreo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfCorreo=new JTextField();
		tfCorreo.setBounds(120,110,100,30);
		tfCorreo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lCorreo);
		this.add(tfCorreo);
		
		lTelefono=new JLabel("Teléfono:");
		lTelefono.setBounds(10,160,100,30);
		lTelefono.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfTelefono=new JTextField();
		tfTelefono.setBounds(120,160,100,30);
		tfTelefono.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lTelefono);
		this.add(tfTelefono);
		
		lDireccion=new JLabel("Dirección:");
		lDireccion.setBounds(10,210,100,30);
		lDireccion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		taDireccion=new JTextArea();
		taDireccion.setBorder(border);
		taDireccion.setBounds(120,210,100,100);
		taDireccion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lDireccion);
		this.add(taDireccion);
		
		ButtonGroup selector = new ButtonGroup();
		lSexo=new JLabel("Sexo:");
		lSexo.setBounds(10,320,100,30);
		lSexo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbHombre=new JRadioButton("Hombre");
		rbHombre.setBounds(120,320,100,30);
		rbHombre.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbHombre.setOpaque(false);
		rbMujer=new JRadioButton("Mujer");
		rbMujer.setBounds(230,320,100,30);
		rbMujer.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbMujer.setOpaque(false);
		selector.add(rbHombre);
        selector.add(rbMujer);
        rbHombre.setSelected(true);
        this.add(lSexo);
        this.add(rbHombre);
        this.add(rbMujer);
		
        lFecha=new JLabel("Fecha de nacimiento:");
        lFecha.setBounds(10,370,170,30);
        lFecha.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
        calendario.setBounds(250,400,200,100);
        this.add(lFecha);
        this.add(calendario);
        
        bRegistrar.setBounds(10,520,120,30);
		bRegistrar.addMouseListener(this);
		bRegistrar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bRegistrar.setBackground(Color.white);
		this.add(bRegistrar);
		
		bVolver.setBounds(140,520,100,30);
		bVolver.addMouseListener(this);
		bVolver.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bVolver.setBackground(Color.white);
		this.add(bVolver);
		
		fondo = new JLabel("");
		fondo.setSize(new Dimension(500,600));
		this.add(fondo);
		ImageIcon portada = new ImageIcon(VPrincipal.class.getResource("/Imagenes/fondoRegistrarClientes.png"));
		fondo.setIcon(new ImageIcon(portada.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		repaint();
		
		setVisible(false);
	}
	//Reinicia los elementos de la ventana como los JTextField,los RadioButton y el calendario
	public void reiniciar(){
		tfIdentificador.setText("");
		tfNombre.setText("");
		tfCorreo.setText("");
		tfTelefono.setText("");
		taDireccion.setText("");
		rbHombre.setSelected(true);
		Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
		calendario.getDayChooser().setDay(dia);
		calendario.getMonthChooser().setMonth(mes);
		calendario.getYearChooser().setYear(anio);
	}
	@Override
	//Accion de los botones, revisa que no hayan campos vacios y que los datos sean validos
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==bRegistrar){
			String identificador=tfIdentificador.getText(),nombre=tfNombre.getText(),correo=tfCorreo.getText(),telefono=tfTelefono.getText(),direccion=taDireccion.getText(),sexo;
			if(rbHombre.isSelected()){
				sexo="hombre";
			}else{
				sexo="mujer";
			}
			if(identificador.isEmpty() || nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || direccion.isEmpty()){
				JOptionPane.showMessageDialog(null,"Por favor rellene todos los datos");
			}else if(Validaciones.confirmarTelefono(telefono)==false || telefono.length()!=8){
				JOptionPane.showMessageDialog(null,"Ingrese un numero de teléfono correcto");
			}else if(Validaciones.validarCorreo(correo)==false){
				JOptionPane.showMessageDialog(null,"Ingrese un correo electrónico correcto");
			}else if(Validaciones.confirmarTelefono(identificador)==false){
				JOptionPane.showMessageDialog(null,"Ingrese un numero de identificador correcto");
			}else{
				System.out.println(calendario.getDayChooser().getDay()+"/"+calendario.getMonthChooser().getMonth()+"/"+calendario.getYearChooser().getYear());
				Fecha fecha=new Fecha(calendario.getDayChooser().getDay(),calendario.getMonthChooser().getMonth()+1,calendario.getYearChooser().getYear());
				Cliente cliente=new Cliente();
				cliente.setCorreo(correo);
				cliente.setDireccion(direccion);
				cliente.setIdentificador(Integer.parseInt(identificador));
				cliente.setNombre(nombre);
				cliente.setSexo(sexo);
				cliente.setTelefono(telefono);
				cliente.setTipo("normal");
				cliente.setFecha(fecha);
				boolean existe=false;
				for(int p=0;p<Counter.getClientes().size();p++){
					if(Counter.getClientes().get(p).getIdentificador()==Integer.parseInt(identificador)){
						existe=true;
						break;
					}
				}
				if(existe==false){
					this.setVisible(false);
					if(Counter.getCasillero()<Counter.getCasilleros()){
						cliente.setCasillero(new Casillero());
						cliente.setNumCasillero(Integer.toString(Counter.getCasillero()));
						Counter.setCasillero(Counter.getCasillero()+1);
						JOptionPane.showMessageDialog(null,"Cliente registrado correctamente, su número de casillero es: "+(Counter.getCasillero()-1));
					}else{
						cliente.setCasillero(null);
						cliente.setNumCasillero("NO TIENE");
						JOptionPane.showMessageDialog(null,"Ya no hay casilleros disponibles, por lo tanto a esta persona no se le asignara uno.");
					}
					Counter.getClientes().add(cliente);
				}else{
					JOptionPane.showMessageDialog(null,"Ya existe una persona con este identificador.");
				}
			}
		}else if(e.getSource()==bVolver){
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
