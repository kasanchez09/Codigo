package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Datos.Counter;
import extra.*;

public class VAdministrarClientes extends JFrame implements MouseListener{
	private String[] columnas = {"Num-Casillero","Identificador","Nombre","Correo","Teléfono","Dirección",
			"Sexo","Tipo","Fecha Nac..."};
	private Object[][] info = {};
	private DefaultTableModel tabla= new DefaultTableModel(info,columnas);
	private JTable table;
	private JButton bEliminar,bCambiarInformacion,bConsultarArticulos,bVolver;
	private JTextField tfNombre,tfNumCasillero,tfIdentificador,tfSexo,tfTipo;
	private JLabel lNombre,lNumCasillero,lIdentificador,lSexo,lTipo,lFiltros;
	private JButton bFiltrar=new JButton("Filtrar");
	
	public VAdministrarClientes(){
		this.setBounds(50,50,800,600);
		this.setTitle("Administración de clientes");
		this.setLayout(null);
		this.setVisible(false);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.GREEN);
		
		lFiltros=new JLabel("Filtros");
		lFiltros.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		lNombre=new JLabel("Nombre:");
		lNombre.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		lNumCasillero=new JLabel("Num Casillero:");
		lNumCasillero.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		lIdentificador=new JLabel("Identificador:");
		lIdentificador.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		lSexo=new JLabel("Sexo:");
		lSexo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		lTipo=new JLabel("Tipo:");
		lTipo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		
		tfNombre=new JTextField();
		tfNombre.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfNumCasillero=new JTextField();
		tfNumCasillero.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfIdentificador=new JTextField();
		tfIdentificador.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfSexo=new JTextField();
		tfSexo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		tfTipo=new JTextField();
		tfTipo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		
		lFiltros.setBounds(10,10,100,30);
		
		lNombre.setBounds(120,0,100,30);
		tfNombre.setBounds(120,25,100,30);
		
		lNumCasillero.setBounds(230,0,110,30);
		tfNumCasillero.setBounds(230,25,100,30);
		
		lIdentificador.setBounds(340,0,110,30);
		tfIdentificador.setBounds(340,25,100,30);
		
		lSexo.setBounds(450,0,100,30);
		tfSexo.setBounds(450,25,100,30);
		
		lTipo.setBounds(560,0,100,30);
		tfTipo.setBounds(560,25,100,30);
		
		bFiltrar=new JButton("Filtrar");
		bFiltrar.setBounds(670,25,100,30);
		bFiltrar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bFiltrar.setBackground(Color.white);
		bFiltrar.addMouseListener(this);

		add(lFiltros);
		add(lNombre);
		add(lNumCasillero);
		add(lIdentificador);
		add(lSexo);
		add(lTipo);
		add(tfNombre);
		add(tfNumCasillero);
		add(tfIdentificador);
		add(tfSexo);
		add(tfTipo);
		add(bFiltrar);
		
		table = new JTable(tabla);
		table.setPreferredScrollableViewportSize(new Dimension(50,50));
		table.setFillsViewportHeight(true);
		table.addMouseListener(this);
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(10,60,757,450);
		this.add(scrollPane);
		
		bEliminar=new JButton("Eliminar Cliente");
		bEliminar.setBounds(160,520,200,30);
		bEliminar.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bEliminar.addMouseListener(this);
		bEliminar.setBackground(Color.white);
		this.add(bEliminar);
		
		bCambiarInformacion=new JButton("Cambiar Información");
		bCambiarInformacion.setBounds(360,520,200,30);
		bCambiarInformacion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bCambiarInformacion.addMouseListener(this);
		bCambiarInformacion.setBackground(Color.white);
		this.add(bCambiarInformacion);
		
		bConsultarArticulos=new JButton("Consultar Artículos");
		bConsultarArticulos.setBounds(560,520,200,30);
		bConsultarArticulos.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bConsultarArticulos.addMouseListener(this);
		bConsultarArticulos.setBackground(Color.white);
		this.add(bConsultarArticulos);
		
		bVolver=new JButton("Volver");
		bVolver.setBounds(10,520,100,30);
		bVolver.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
		bVolver.addMouseListener(this);
		bVolver.setBackground(Color.white);
		this.add(bVolver);
		//tabla.getDataVector(todas las lineas)
	}
	public void reiniciarTabla(){
		while(tabla.getRowCount()!=0){
			tabla.removeRow(0);
		}
		anadirClientes();
	}
	public void anadirClientes(){
		//Se revisan los filtros y se añaden los clientes a la lista
		boolean fNombre=true,fNum=true,fIden=true,fSexo=true,fTipo=true;
		if(tfNombre.getText().isEmpty()){
			fNombre=false;
		}
		if(tfNumCasillero.getText().isEmpty()){
			fNum=false;
		}
		if(tfIdentificador.getText().isEmpty()){
			fIden=false;
		}
		if(tfSexo.getText().isEmpty()){
			fSexo=false;
		}
		if(tfTipo.getText().isEmpty()){
			fTipo=false;
		}
		for(int i=0;i<Counter.getClientes().size();i++){
			boolean cumpleFiltros=true;
			Object[] cliente = new Object[9];
			cliente[0]=Counter.getClientes().get(i).getNumCasillero();
			cliente[1]=Counter.getClientes().get(i).getIdentificador();
			cliente[2]=Counter.getClientes().get(i).getNombre();
			cliente[3]=Counter.getClientes().get(i).getCorreo();
			cliente[4]=Counter.getClientes().get(i).getTelefono();
			cliente[5]=Counter.getClientes().get(i).getDireccion();
			cliente[6]=Counter.getClientes().get(i).getSexo();
			cliente[7]=Counter.getClientes().get(i).getTipo();
			cliente[8]=Counter.getClientes().get(i).getFecha().getDay()+"/"+Counter.getClientes().get(i).getFecha().getMonth()+"/"+Counter.getClientes().get(i).getFecha().getYear();
			if(fNombre && (cliente[2].equals(tfNombre.getText())==false)){
				cumpleFiltros=false;
			}
			if(fNum && (cliente[0].equals(tfNumCasillero.getText())==false)){
				cumpleFiltros=false;
			}
			if(fIden && (cliente[1].toString().equals(tfIdentificador.getText())==false)){
				cumpleFiltros=false;
			}
			if(fSexo && (cliente[6].equals(tfSexo.getText())==false)){
				cumpleFiltros=false;
			}
			if(fTipo && (cliente[7].equals(tfTipo.getText())==false)){
				cumpleFiltros=false;
			}
			if(cumpleFiltros){
				tabla.addRow(cliente);
			}
		}
	}
	public void reiniciar(){
		tfNombre.setText("");
		tfNumCasillero.setText("");
		tfIdentificador.setText("");
		tfSexo.setText("");
		tfTipo.setText("");
		reiniciarTabla();
	}
	public void cambiarInformacion(){
		//Se obtiene linea por linea de la tabla la nueva informacion y se cambia en los clientes del Counter
		String nombre,correo,telefono,direccion,sexo;
		int identificador;
		boolean telefonoBueno;
		for(int k=0;k<tabla.getDataVector().size();k++){
			telefonoBueno=true;
			identificador=(int) table.getValueAt(k, 1);
			System.out.println(identificador);
			nombre=(String) table.getValueAt(k, 2);
			correo=(String) table.getValueAt(k, 3);
			telefono=(String) table.getValueAt(k, 4);
			direccion=(String) table.getValueAt(k, 5);
			sexo=(String) table.getValueAt(k, 6);
			try{
				//Obtiene el telefono con un espacio al inicio
				Integer.parseInt(telefono);
			}catch(Exception exception){
				telefonoBueno=false;
			}
			for(int j=0;j<Counter.getClientes().size();j++){
				if(Counter.getClientes().get(j).getIdentificador()==identificador){
					if(nombre.isEmpty()==false){
						Counter.getClientes().get(j).setNombre(nombre);
					}
					if(Validaciones.validarCorreo(correo)){
						Counter.getClientes().get(j).setCorreo(correo);
					}
					if(direccion.isEmpty()==false){
						Counter.getClientes().get(j).setDireccion(direccion);
					}
					if(sexo.equals("hombre") || sexo.equals("mujer")){
						Counter.getClientes().get(j).setSexo(sexo);
					}
					if(telefonoBueno){
						Counter.getClientes().get(j).setTelefono(telefono);
					break;
					}
				}
			}
		}
		//Luego se vuelven a añadir los clientes a la lista
		reiniciarTabla();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==bVolver){
			this.setVisible(false);
		}else if(e.getSource()==bEliminar){
			if(table.getSelectedRow()!=-1){
				//Se obtiene de la tabla el cliente seleccionado y se elimina del counter
				int identificadorSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 1);
				System.out.println(identificadorSeleccionado);
				for(int i=0;i<Counter.getClientes().size();i++){
					if(Counter.getClientes().get(i).getIdentificador()==identificadorSeleccionado){
						Counter.getClientes().remove(i);
						System.out.println("REMOVIO");
						break;
					}
				}
				reiniciarTabla();
			}
		}else if(e.getSource()==bCambiarInformacion){
			cambiarInformacion();
		}else if(e.getSource()==bConsultarArticulos){
			if(table.getSelectedRow()!=-1){
				//Se opbtiene de la lista el cliente seleccionado y se despliegan los articulos asignados a dicho cliente
				int identificador=(int) table.getValueAt(table.getSelectedRow(), 1);
				Counter.vDesplegarEntregables.reiniciar();
				Counter.vDesplegarEntregables.setValores(identificador);
				Counter.vDesplegarEntregables.setVisible(true);
			}
		}else if(e.getSource()==bFiltrar){
			reiniciarTabla();
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
