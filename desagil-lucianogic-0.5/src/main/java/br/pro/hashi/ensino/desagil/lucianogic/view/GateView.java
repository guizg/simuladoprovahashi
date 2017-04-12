package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements ItemListener, MouseListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;

	private int sizao;
	
	private boolean entrada1;
	private boolean entrada2;
	private boolean entrada3;
	
	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;

		image = loadImage(gate.toString());

		int size = gate.getSize();
		
		sizao = size;

		inBoxes = new JCheckBox[size];

		switches = new Switch[size];
		
		entrada1 = false;
		entrada2 = false;
		entrada3 = false;

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		outBox.setEnabled(false);
		
		
		



		if(size == 1) {
			//add(inBoxes[0], 0, 60, 20, 20);			
		}
		else {
			for(int i = 0; i < size; i++) {
				//add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
			}			
		}

		add(outBox, 184, 60, 20, 20);

		outBox.setSelected(gate.read());
		addMouseListener(this);
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches[i].setOn(inBoxes[i].isSelected());

		outBox.setSelected(gate.read());
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);

		// Evita bugs visuais em alguns sistemas operacionais.
		
		if(sizao==1){
			g.fillArc(0, 62, 20, 20, 0, 180);
			if(entrada1==false){
				g.drawLine(10, 62, 3, 52);
				g.fillOval(0, 50, 5, 5);	
			}else{
				g.drawLine(10, 62, 17, 52);
				g.fillOval(14, 50, 5, 5);
			}
			
		}
		if(sizao==2){
			g.fillArc(0, 42, 20, 20, 0, 180);
			g.fillArc(0, 82, 20, 20, 0, 180);
			if(entrada1==false){
				g.drawLine(10, 42, 3, 32);
				g.fillOval(0, 30, 5, 5);	
			}else{
				g.drawLine(10, 42, 17, 32);
				g.fillOval(14, 30, 5, 5);
			}
			if(entrada2==false){
				g.drawLine(10, 82, 3, 72);
				g.fillOval(0, 70, 5, 5);	
			}else{
				g.drawLine(10, 82, 17, 72);
				g.fillOval(14, 70, 5, 5);
			}
		}
		if(sizao==3){
			g.fillArc(0, 42, 20, 20, 0, 180);
			g.fillArc(0, 82, 20, 20, 0, 180);
			g.fillArc(0, 122, 20, 20, 0, 180);
			if(entrada1==false){
				g.drawLine(10, 42, 3, 32);
				g.fillOval(0, 30, 5, 5);	
			}else{
				g.drawLine(10, 42, 17, 32);
				g.fillOval(14, 30, 5, 5);
			}
			if(entrada2==false){
				g.drawLine(10, 82, 3, 72);
				g.fillOval(0, 70, 5, 5);	
			}else{
				g.drawLine(10, 82, 17, 72);
				g.fillOval(14, 70, 5, 5);
			}
			if(entrada3==false){
				g.drawLine(10, 122, 3, 112);
				g.fillOval(0, 110, 5, 5);	
			}else{
				g.drawLine(10, 122, 17, 112);
				g.fillOval(14, 110, 5, 5);
			}
		}
		
		getToolkit().sync();
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(sizao==1){
			if(e.getX()>0 && e.getX()<30 && e.getY()>50 && e.getY()<70){
				if(entrada1==false){
					entrada1=true;
				}else{
					entrada1=false;
				}
				repaint();
				
			}
			Switch switch1 = new Switch();
			if(entrada1==true){
				switch1.setOn(true);
			}else{
				switch1.setOn(false);
			}
			gate.connect(switch1, 0);
			outBox.setSelected(gate.read());
		}
		if(sizao==2){
			if(e.getX()>0 && e.getX()<30 && e.getY()>30 && e.getY()<50){
				if(entrada1==false){
					entrada1=true;
				}else{
					entrada1=false;
				}
				repaint();
			}
			if(e.getX()>0 && e.getX()<30 && e.getY()>70 && e.getY()<90){
				if(entrada2==false){
					entrada2=true;
				}else{
					entrada2=false;
				}
				repaint();
			}
			Switch switch1 = new Switch();
			Switch switch2 = new Switch();
			if(entrada1==true){
				switch1.setOn(true);
			}else{
				switch1.setOn(false);
			}
			if(entrada2==true){
				switch2.setOn(true);
			}else{
				switch2.setOn(false);
			}
			gate.connect(switch1, 0);
			gate.connect(switch2, 1);
			outBox.setSelected(gate.read());
		}
		if(sizao==3){
			if(e.getX()>0 && e.getX()<30 && e.getY()>30 && e.getY()<50){
				if(entrada1==false){
					entrada1=true;
				}else{
					entrada1=false;
				}
				repaint();
			}
			if(e.getX()>0 && e.getX()<30 && e.getY()>70 && e.getY()<90){
				if(entrada2==false){
					entrada2=true;
				}else{
					entrada2=false;
				}
				repaint();
			}
			if(e.getX()>0 && e.getX()<30 && e.getY()>110 && e.getY()<130){
				if(entrada3==false){
					entrada3=true;
				}else{
					entrada3=false;
				}
				repaint();
			}
			Switch switch1 = new Switch();
			Switch switch2 = new Switch();
			Switch switch3 = new Switch();
			if(entrada1==true){
				switch1.setOn(true);
			}else{
				switch1.setOn(false);
			}
			if(entrada2==true){
				switch2.setOn(true);
			}else{
				switch2.setOn(false);
			}
			if(entrada3==true){
				switch3.setOn(true);
			}else{
				switch3.setOn(false);
			}
			gate.connect(switch1, 0);
			gate.connect(switch2, 1);
			gate.connect(switch3, 2);
			outBox.setSelected(gate.read());
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
