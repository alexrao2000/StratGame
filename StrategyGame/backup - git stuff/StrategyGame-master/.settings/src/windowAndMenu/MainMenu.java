package windowAndMenu;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainMenu extends JPanel implements ActionListener {
	
	private Main m;
	private Image img;
	//Image img;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	
	public MainMenu(Main m) {
		this.m = m;
		JPanel p = new JPanel();
		p.setBackground(new Color(0,0,0,0));  // Panel is transparent
		
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));	
		p.add(Box.createVerticalStrut(300));   // Move down by 300 pixels  

		img = Toolkit.getDefaultToolkit().createImage("battle.jpg");
		img = img.getScaledInstance(800, 600, 0);
		
		button1 = new JButton("Start");
		button2 = new JButton("Load");
		button3 = new JButton("Options");
		button4 = new JButton("Quit");
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		
		p.add(button1);
		p.add(button2);
		p.add(button3);
		p.add(button4);
		add(p);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		double ratioX = getWidth() / 800.0;
		double ratioY = getHeight() / 600.0;
		
		Graphics2D g2 = (Graphics2D)g;
		
		AffineTransform af = g2.getTransform();
		
		g2.scale(ratioX,ratioY);
		g.drawImage(img, 0, 0, this);
		
		g.setColor(Color.RED);
		g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 45));
		g.drawString("Batallion", 305, 200);
		
		g2.setTransform(af);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1)
			m.changePanel("2");
		if (e.getSource() == button2)
			m.changePanel("1");
		if (e.getSource() == button3)
			m.changePanel("1");
		if (e.getSource() == button4)
			m.dispose();
	}

	
}