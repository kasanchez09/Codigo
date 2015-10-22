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

import javax.swing.*;
import javax.swing.border.Border;

import Datos.*;
import extra.Fecha;
import extra.Mail;
import extra.Validaciones;

public class VRegistrarEntregable extends JFrame implements MouseListener{
	JButton bRegistrar=new JButton("Registrar");
	JButton bInfo=new JButton("Consultar clientes");
	JLabel lNumReferencia,lDescripcion,lRemitente,lFecha,lArticulo,lTema,lPeso;
	JLabel lTipo,lContenido,lElectronico,lFragil,lCatalogo,fondo;
	JTextField tNumReferencia,tRemitente,tPeso;
	JTextArea taDescripcion,taInfo,taFecha;
	JRadioButton rbPaquete,rbRevista,rbSobre;
	JRadioButton rbAereo,rbManila,rbDocumentos,rbOtro,rbESi,rbENo,rbFSi,rbFNo,rbCSi,rbCNo;
	JComboBox cbTemaCat = new JComboBox();
	JButton bVolver=new JButton("Volver");
	Date fecha = new Date();
	Calendar fecha1 = new GregorianCalendar();
	
	public VRegistrarEntregable(){
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		this.setTitle("Registrar Entregable");
		this.setBounds(200,60,480,600);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.orange);
		
		lNumReferencia=new JLabel("Identificador:");
		lNumReferencia.setBounds(10,10,170,30);
		lNumReferencia.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tNumReferencia=new JTextField();
		tNumReferencia.setBounds(170,10,100,30);
		tNumReferencia.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lNumReferencia);
		this.add(tNumReferencia);
		
		lRemitente=new JLabel("Remitente:");
		lRemitente.setBounds(10,60,110,30);
		lRemitente.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tRemitente=new JTextField();
		tRemitente.setBounds(170,60,100,30);
		tRemitente.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lRemitente);
		this.add(tRemitente);
		
		lFecha=new JLabel("Fecha:");
		lFecha.setBounds(10,110,80,30);
		lFecha.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		taFecha=new JTextArea();
		int mes = fecha1.get(Calendar.MONTH);
		taFecha.setText(fecha1.get(Calendar.DATE)+"/"+(mes+1)+"/"+fecha1.get(Calendar.YEAR));
		taFecha.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		taFecha.setBounds(170,120,100,30);
		taFecha.setEditable(false);
		taFecha.setOpaque(false);
		taFecha.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lFecha);
		this.add(taFecha);
		
		lDescripcion=new JLabel("Descripción:");
		lDescripcion.setBounds(10,160,130,30);
		lDescripcion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		taDescripcion=new JTextArea();
		taDescripcion.setBounds(170,160,140,100);
		taDescripcion.setBorder(border);
		taDescripcion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lDescripcion);
		this.add(taDescripcion);
		
		ButtonGroup articulo = new ButtonGroup();
		lArticulo=new JLabel("Seleccionar tipo artículo:");
		lArticulo.setBounds(10,280,200,30);
		lArticulo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbPaquete=new JRadioButton("Paquete");
		rbPaquete.setBounds(200,280,100,28);
		rbPaquete.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbPaquete.setOpaque(false);
		rbRevista=new JRadioButton("Revista");
		rbRevista.setBounds(200,300,100,28);
		rbRevista.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbRevista.setOpaque(false);
		rbSobre=new JRadioButton("Sobre");
		rbSobre.setBounds(200,320,100,28);
		rbSobre.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbSobre.setOpaque(false);
		rbPaquete.addMouseListener(this);
		rbRevista.addMouseListener(this);
		rbSobre.addMouseListener(this);
		articulo.add(rbPaquete);
		articulo.add(rbRevista);
		articulo.add(rbSobre);
        rbPaquete.setSelected(true);
        this.add(lArticulo);
        this.add(rbPaquete);
        this.add(rbRevista);
        this.add(rbSobre);
        
        ButtonGroup paqueteE = new ButtonGroup();
		lElectronico=new JLabel("Electrónico");
		lElectronico.setBounds(10,340,100,30);
		lElectronico.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbESi=new JRadioButton("Si");
		rbESi.setBounds(95,340,50,28);
		rbESi.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbESi.setOpaque(false);
		rbENo=new JRadioButton("No");
		rbENo.setBounds(150,340,50,28);
		rbENo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbENo.setOpaque(false);
		paqueteE.add(rbESi);
        paqueteE.add(rbENo);
        rbENo.setSelected(true);
        this.add(lElectronico);
        this.add(rbESi);
        this.add(rbENo);
        
        ButtonGroup paqueteF = new ButtonGroup();
		lFragil=new JLabel("Frágil");
		lFragil.setBounds(10,360,100,30);
		lFragil.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbFSi=new JRadioButton("Si");
		rbFSi.setBounds(95,360,50,30);
		rbFSi.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbFSi.setOpaque(false);
		rbFNo=new JRadioButton("No");
		rbFNo.setBounds(150,360,50,30);
		rbFNo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbFNo.setOpaque(false);
		paqueteF.add(rbFSi);
        paqueteF.add(rbFNo);
        rbENo.setSelected(true);
        this.add(lFragil);
        this.add(rbFSi);
        this.add(rbFNo);
		
        ButtonGroup revista = new ButtonGroup();
		lCatalogo=new JLabel("Catálogo:");
		lCatalogo.setBounds(10,340,120,30);
		lCatalogo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbCSi=new JRadioButton("Si");
		rbCSi.setBounds(80,340,50,30);
		rbCSi.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbCSi.setOpaque(false);
		rbCNo=new JRadioButton("No");
		rbCNo.setBounds(150,340,50,30);
		rbCNo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbCNo.setOpaque(false);
		rbCSi.addMouseListener(this);
		revista.add(rbCSi);
        revista.add(rbCNo);
        rbCSi.setSelected(true);
        this.add(lCatalogo);
        this.add(rbCSi);
        this.add(rbCNo);
        
        lTema=new JLabel("Tema:");
		lTema.setBounds(10,360,80,30);
		lTema.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
        cbTemaCat.setModel(new DefaultComboBoxModel(new String[] {"Negocios", "Moda/Belleza", 
        "Tecnología","Salud/Medicina", "Cocina", "Otro"}));
        cbTemaCat.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
        lTema.setVisible(false);
        cbTemaCat.setVisible(false);
        cbTemaCat.setMaximumRowCount(6);
        cbTemaCat.setBounds(100,370,155,20); 
        this.add(cbTemaCat);
        this.add(lTema);
        this.setVisible(true);
        
        lPeso=new JLabel("Peso:");
		lPeso.setBounds(10,390,80,30);
		lPeso.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tPeso=new JTextField();
		tPeso.setBounds(80,390,100,30);
		tPeso.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		this.add(lPeso);
		this.add(tPeso);
		
		Border border2 = BorderFactory.createLineBorder(Color.RED);
		
		taInfo=new JTextArea("Si no sabe el número de identificador \n"
						+"de la persona a la cual desea enviar el \n"
						+"paquete, presione el siguiente botón.");
		taInfo.setBounds(150,443,290,60);
		taInfo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		taInfo.setEditable(false);
		taInfo.setOpaque(false);
		taInfo.setBorder(border2);
		this.add(taInfo);
		
		bInfo.setBounds(150,515,200,30);
		bInfo.addMouseListener(this);
		bInfo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bInfo.setBackground(Color.white);
		this.add(bInfo);
        
        ButtonGroup sobreT = new ButtonGroup();
		lTipo=new JLabel("Tipo:");
		lTipo.setBounds(10,340,100,30);
		lTipo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbAereo=new JRadioButton("Aéreo");
		rbAereo.setBounds(90,340,80,28);
		rbAereo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbAereo.setOpaque(false);
		rbManila=new JRadioButton("Manila");
		rbManila.setBounds(210,340,100,28);
		rbManila.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbManila.setOpaque(false);
		sobreT.add(rbAereo);
        sobreT.add(rbManila);
        rbAereo.setSelected(true);
        this.add(lTipo);
        this.add(rbAereo);
        this.add(rbManila);
        
        ButtonGroup sobreC = new ButtonGroup();
		lContenido=new JLabel("Contenido:");
		lContenido.setBounds(10,360,120,30);
		lContenido.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbDocumentos=new JRadioButton("Documentos");
		rbDocumentos.setBounds(90,360,120,30);
		rbDocumentos.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbDocumentos.setOpaque(false);
		rbOtro=new JRadioButton("Otro");
		rbOtro.setBounds(210,360,100,30);
		rbOtro.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		rbOtro.setOpaque(false);
		sobreC.add(rbDocumentos);
        sobreC.add(rbOtro);
        rbDocumentos.setSelected(true);
        this.add(lContenido);
        this.add(rbDocumentos);
        this.add(rbOtro);
		
        bRegistrar.setBounds(10,460,120,30);
		bRegistrar.addMouseListener(this);
		bRegistrar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bRegistrar.setBackground(Color.white);
		this.add(bRegistrar);
		
		bVolver.setBounds(10,510,100,30);
		bVolver.addMouseListener(this);
		bVolver.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bVolver.setBackground(Color.white);
		this.add(bVolver);
		
		cbTemaCat.setVisible(false);
		lTema.setVisible(false);
		lTipo.setVisible(false);
		lContenido.setVisible(false);
		lCatalogo.setVisible(false);
		rbAereo.setVisible(false);
		rbManila.setVisible(false);
		rbDocumentos.setVisible(false);
		rbOtro.setVisible(false);
		rbCSi.setVisible(false);
		rbCNo.setVisible(false);
		rbFNo.setSelected(true);
		
		bInfo.setBorder(border2);
		
		fondo = new JLabel("");
		fondo.setSize(new Dimension(500,600));
		this.add(fondo);
		ImageIcon portada = new ImageIcon(VPrincipal.class.getResource("/Imagenes/fondoRegistrarEntregable.png"));
		fondo.setIcon(new ImageIcon(portada.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		
		this.setVisible(false);
		repaint();
	}
	//Reinicia los elementos de la ventana como los JTextField y los JTextArea
	public void reiniciar(){
		tNumReferencia.setText("");
		tRemitente.setText("");
		taDescripcion.setText("");
		tPeso.setText("");
	}
	//Funcion que realiza las diferentes validaciones como que no hayan campos vacios y que los datos sean validos
	public boolean validacionesCorrectas(){
		String numReferencia=tNumReferencia.getText(),remitente=tRemitente.getText(),descripcion=taDescripcion.getText();
		if(descripcion.isEmpty()){
			JOptionPane.showMessageDialog(null,"Por favor rellene todos los datos");
			return false;
		}
		if(rbRevista.isSelected()==false){
			String peso=tPeso.getText();
			if(peso.isEmpty() || tRemitente.getText().isEmpty()){
				JOptionPane.showMessageDialog(null,"Por favor rellene todos los datos");
				return false;
			}
		}
		if(Validaciones.confirmarTelefono(numReferencia) == false){
			JOptionPane.showMessageDialog(null,"Ingrese un número de identificador correcto");
			return false;
		}
		return true;
	}
	//Funcion que registra el paquete con cada una de sus caracteristicas
	private void registrarPaquete(){
		int numReferencia=Integer.parseInt(tNumReferencia.getText());
		Paquete paquete = new Paquete();
		paquete.setDescripcion(taDescripcion.getText());
		paquete.setElectronico(rbESi.isSelected());
		paquete.setFragil(rbFSi.isSelected());
		paquete.setEntregado(false);
		paquete.setFecha(new Fecha(fecha1.get(Calendar.DATE),fecha1.get(Calendar.MONTH),fecha1.get(Calendar.YEAR)));
		paquete.setNumReferencia(numReferencia);
		paquete.setPeso(Integer.parseInt(tPeso.getText()));
		paquete.setRemitente(tRemitente.getText());
		paquete.setIdentificadorArticulo(Counter.identificadorArticulo);
		boolean encontro=false;
		float descuento=0;
		float ele=0,fra=0;
		if(rbESi.isSelected()){
			ele=2;
		}
		if(rbFSi.isSelected()){
			fra=2;
		}
		float impuestos=(float) (((Float.parseFloat(tPeso.getText()))*0.02)+ele+fra);
		for(int i=0;i<Counter.getClientes().size();i++){
			if(Counter.getClientes().get(i).getIdentificador()==numReferencia){
				if(Counter.getClientes().get(i).getTipo()=="plata"){
					descuento=(float)((5*impuestos)/100);
				}else if(Counter.getClientes().get(i).getTipo()=="oro"){
					descuento=(float)((10*impuestos)/100);
				}
				float total=impuestos-descuento;
				paquete.setTotal(total);
				paquete.setDescuento(descuento);
				paquete.setImpuestos(impuestos);
				Counter.getClientes().get(i).getCasillero().getArticulos().add(paquete);
				enviarCorreo(Counter.getClientes().get(i));
				Counter.getClientes().get(i).actualizaTipo();
				encontro=true;
				break;
			}
		}
		if(encontro==false){
			JOptionPane.showMessageDialog(null,"El identificador que indicó no pertenece a ninguna persona.");
		}else{
			setVisible(false);
			JOptionPane.showMessageDialog(null,"Articulo registrado correctamente");
		}
	}
	//Funcion que registra la revista con cada una de sus caracteristicas
	private void registrarRevista(){
		int numReferencia=Integer.parseInt(tNumReferencia.getText());
		Revista revista = new Revista();
		revista.setCatalogo(true);
		revista.setTema((String) cbTemaCat.getSelectedItem());
		revista.setDescripcion(taDescripcion.getText());
		revista.setEntregado(false);
		revista.setFecha(new Fecha(fecha1.get(Calendar.DATE),fecha1.get(Calendar.MONTH),fecha1.get(Calendar.YEAR)));
		revista.setNumReferencia(numReferencia);
		revista.setRemitente(tRemitente.getText());
		revista.setIdentificadorArticulo(Counter.identificadorArticulo);
		boolean encontro=false;
		float impuestos=1;
		float descuento=0;
		for(int i=0;i<Counter.getClientes().size();i++){
			if(Counter.getClientes().get(i).getIdentificador()==numReferencia){
				if(Counter.getClientes().get(i).getTipo()=="plata"){
					descuento=(float)((5*impuestos)/100);
				}else if(Counter.getClientes().get(i).getTipo()=="oro"){
					descuento=(float)((10*impuestos)/100);
				}
				float total=impuestos-descuento;
				revista.setImpuestos(impuestos);
				revista.setTotal(total);
				revista.setDescuento(descuento);
				Counter.getClientes().get(i).getCasillero().getArticulos().add(revista);
				enviarCorreo(Counter.getClientes().get(i));
				Counter.getClientes().get(i).actualizaTipo();
				encontro=true;
				break;
			}
		}
		if(encontro==false){
			JOptionPane.showMessageDialog(null,"El identificador que indicó no pertenece a ninguna persona.");
		}else{
			setVisible(false);
			JOptionPane.showMessageDialog(null,"Articulo registrado correctamente");
		}
	}
	//Funcion que registra el sobre con cada una de sus caracteristicas
	private void registrarSobre(){
		int numReferencia=Integer.parseInt(tNumReferencia.getText());
		String contenido,tipo;
		if(rbDocumentos.isSelected()){
			contenido="Documentos";
		}else{
			contenido="Otro";
		}
		if(rbAereo.isSelected()){
			tipo="Aereo";
		}else{
			tipo="Manila";
		}
		Sobre sobre = new Sobre();
		sobre.setContenido(contenido);
		sobre.setTipo(tipo);
		sobre.setDescripcion(taDescripcion.getText());
		sobre.setEntregado(false);
		sobre.setFecha(new Fecha(fecha1.get(Calendar.DATE),fecha1.get(Calendar.MONTH),fecha1.get(Calendar.YEAR)));
		sobre.setNumReferencia(numReferencia);
		sobre.setRemitente(tRemitente.getText());
		sobre.setPeso(Integer.parseInt(tPeso.getText()));
		sobre.setIdentificadorArticulo(Counter.identificadorArticulo);
		boolean encontro=false;
		float descuento=0;
		float impuestos=0;
		if(rbAereo.isSelected()){
			if(rbDocumentos.isSelected()==false){
				impuestos++;
			}
		}else{
			if(rbDocumentos.isSelected()){
				impuestos++;
			}else{
				impuestos+=2;
			}
		}
		for(int i=0;i<Counter.getClientes().size();i++){
			if(Counter.getClientes().get(i).getIdentificador()==numReferencia){
				if(Counter.getClientes().get(i).getTipo()=="plata"){
					descuento=(float)((5*impuestos)/100);
				}else if(Counter.getClientes().get(i).getTipo()=="oro"){
					descuento=(float)((10*impuestos)/100);
				}
				float total=impuestos-descuento;
				sobre.setTotal(total);
				sobre.setImpuestos(impuestos);
				sobre.setDescuento(descuento);
				Counter.getClientes().get(i).getCasillero().getArticulos().add(sobre);
				enviarCorreo(Counter.getClientes().get(i));
				Counter.getClientes().get(i).actualizaTipo();
				encontro=true;
				break;
			}
		}
		if(encontro==false){
			JOptionPane.showMessageDialog(null,"El identificador que indicó no pertenece a ninguna persona.");
		}else{
			setVisible(false);
			JOptionPane.showMessageDialog(null,"Artículo registrado correctamente");
		}
	}
	//Funcion que envia correos al cliente cuando se registra un articulo
	public void enviarCorreo(Cliente cliente){
		Counter.identificadorArticulo++;
		Mail mail = new Mail();
		String informacionArticulos="Buenas, este mensaje es de parte del counter de casilleros.\n\nArtículos pendientes: \n";
		for(int i=0;i<cliente.getCasillero().getArticulos().size();i++){
			if(cliente.getCasillero().getArticulos().get(i).isEntregado()==false){
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
		System.out.println(informacionArticulos);
		mail.enviar(cliente.getCorreo(), informacionArticulos);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==bRegistrar){
			if(validacionesCorrectas()){
				if(rbPaquete.isSelected()){
					registrarPaquete();
				}else if(rbRevista.isSelected()){
					registrarRevista();
				}else{
					registrarSobre();
				}
			}
		}
		if(e.getSource()==bVolver){
			this.setVisible(false);
		}
		if(e.getSource()==rbPaquete){
			cbTemaCat.setVisible(false);
			lTema.setVisible(false);
			lTipo.setVisible(false);
			lContenido.setVisible(false);
			lCatalogo.setVisible(false);
			rbAereo.setVisible(false);
			rbManila.setVisible(false);
			rbDocumentos.setVisible(false);
			rbOtro.setVisible(false);
			rbCSi.setVisible(false);
			rbCNo.setVisible(false);
			lElectronico.setVisible(true);
			lFragil.setVisible(true);
			rbESi.setVisible(true);
			rbENo.setVisible(true);
			rbFSi.setVisible(true);
			rbFNo.setVisible(true);
			lPeso.setVisible(true);
			tPeso.setVisible(true);
			tPeso.setText("");
		}
		
		if(e.getSource()==rbRevista){
			lTipo.setVisible(false);
			rbCSi.setVisible(true);
			rbCNo.setSelected(true);
			rbCNo.setVisible(true);
			lContenido.setVisible(false);
			lCatalogo.setVisible(true);
			lElectronico.setVisible(false);
			lFragil.setVisible(false);
			rbAereo.setVisible(false);
			rbManila.setVisible(false);
			rbDocumentos.setVisible(false);
			rbOtro.setVisible(false);
			rbESi.setVisible(false);
			rbENo.setVisible(false);
			rbFSi.setVisible(false);
			rbFNo.setVisible(false);
			lPeso.setVisible(false);
			tPeso.setVisible(false);
		}
		
		if(e.getSource()==rbCSi){
			cbTemaCat.setVisible(true);
			lTema.setVisible(true);
		}
		
		if(e.getSource()==rbSobre){
			lTipo.setVisible(true);
			rbCSi.setVisible(false);
			rbCNo.setVisible(false);
			lContenido.setVisible(true);
			lCatalogo.setVisible(false);
			lElectronico.setVisible(false);
			lFragil.setVisible(false);
			rbAereo.setVisible(true);
			rbManila.setSelected(true);
			rbManila.setVisible(true);
			rbDocumentos.setVisible(true);
			rbOtro.setSelected(true);
			rbOtro.setVisible(true);
			rbESi.setVisible(false);
			rbENo.setVisible(false);
			rbFSi.setVisible(false);
			rbFNo.setVisible(false);
			cbTemaCat.setVisible(false);
			lTema.setVisible(false);
			lPeso.setVisible(true);
			tPeso.setVisible(true);
			tPeso.setText("");
		}
		if(bInfo==e.getSource()){
			Counter.administrarClientes();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
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
