package windowAndMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import map.*;
import units.*;


public class GameMenu extends JPanel implements KeyListener, MouseListener {
	private Map map;
	private Main m;
	private ArrayList<Unit> players;
	private ArrayList<Unit> enemies;
	private int playerTurn;
	private boolean isAttackPhase;

	public GameMenu (Main m) {
		super();
		this.m = m;
		setBackground(Color.WHITE);
		
		Healer healer = new Healer(true, 5, 5, 5, 1);
		Attacker attacker = new Attacker(true, 5, 5, 5, 1);
		Healer eHealer = new Healer(false, 5, 5, 5, 1);
		Attacker eAttacker = new Attacker(false, 5, 5, 5, 1);
		
		map = new Map();
		map.getTile(0, 0).addUnit(healer);
		map.getTile(4, 4).addUnit(attacker);
		map.getTile(19, 19).addUnit(eHealer);
		map.getTile(15, 15).addUnit(eAttacker);
		
		playerTurn = 0;
		isAttackPhase = false;
		players = new ArrayList<Unit>();
		players.add(healer);
		players.add(attacker);
		
		enemies = new ArrayList<Unit>();
		enemies.add(eHealer);
		enemies.add(eAttacker);
		
		addMouseListener(this);

	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

		Graphics2D g2 = (Graphics2D)g;

		int width = getWidth();
		int height = getHeight();

		double ratioX = (double)width/800.0;
		double ratioY = (double)height/600.0;

		AffineTransform at = g2.getTransform();
		g2.scale(ratioX, ratioY);

		g2.setTransform(at);

		// TODO Add any custom drawings here
		map.paintComponent(g2, height, width);
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			m.changePanel("1");
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.print("hi");
		if(playerTurn != 0) {
			
			Unit u = players.get(playerTurn-1);
			int xPos = e.getX();
			int yPos = e.getY();
			
			if(!isAttackPhase) {
				
				
				
				MovementPhase mPhase = new MovementPhase(u);
				
				if(map.getTile(xPos, yPos, getHeight(), getWidth()).getUnit().equals(null)) { 
					
					mPhase.run(map.getTileRow(map.getTile(xPos, yPos, getHeight(), getWidth())), 
							map.getTileCol(map.getTile(xPos, yPos, getHeight(), getWidth())), getHeight(), 
							getWidth(), map);
					
					isAttackPhase = true;
				}
				
			} else {
				
				Tile other = map.getTile(xPos, yPos, getHeight(), getWidth());
				
				if(other.getUnit().equals(null)) {
					
					MovementPhase mPhase = new MovementPhase(u);
					
					mPhase.run(map.getTileRow(other), map.getTileCol(other), getHeight(), getWidth(), map);
					
					isAttackPhase = false;
					
				} else if(other.getUnit() instanceof Attacker) {
					
					if(!other.getUnit().isPlayerControlled()) {
						other.getUnit().takeDamage(u.getPower());
						isAttackPhase = false;
					}
					
				} else if(u instanceof Healer) {
					
					if(other.getUnit().isPlayerControlled()) {
						((Healer) u).heal(other.getUnit());
						isAttackPhase = false;
					}
					
				} 
			}
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
