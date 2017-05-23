package windowAndMenu;

import java.awt.Color;
import java.awt.Font;
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
	
	/**
	 * Constructs a game menu
	 * @param m the main class
	 * */
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
		
		playerTurn = 1;
		isAttackPhase = false;
		players = new ArrayList<Unit>();
		players.add(healer);
		players.add(attacker);
		
		enemies = new ArrayList<Unit>();
		enemies.add(eHealer);
		enemies.add(eAttacker);
		
		addMouseListener(this);

	}

	/**
	 * Draws the game menu
	 * @param g the graphics object
	 * */
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
		
		g2.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		if(playerTurn != 0) {
			Unit u = players.get(playerTurn-1);
			
			g2.drawString(u.getName(), 0, 20);
			
			Tile tile = map.getTile(map.getTileXPos(map.getTile(u), getWidth()), map.getTileXPos(map.getTile(u), getHeight()), getHeight(), getWidth());
			int x = map.getTileXPos(tile, getWidth());
			int y = map.getTileYPos(tile, getHeight()); 
			g2.setColor(Color.RED);
			int moveDistX = u.getMovementDistance()*getWidth()/20;
			int moveDistY = u.getMovementDistance()*getHeight()/20;
			g2.drawRect(x-moveDistX, y-moveDistY, moveDistX*2, moveDistY*2);
			
			if(isAttackPhase) {
				
			}
		}
		repaint();
		
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
		if(playerTurn != 0) {
			
			Unit u = players.get(playerTurn-1);
			int xPos = e.getX();
			int yPos = e.getY();
			//System.out.println("(x:"+xPos+", y:"+yPos+")");
			int moveDistX = u.getMovementDistance()*getWidth()/20;
			int moveDistY = u.getMovementDistance()*getHeight()/20;
			
			if(!isAttackPhase) {

				//System.out.println(!map.getTile(xPos, yPos, getHeight(), getWidth()).hasUnit());
				Tile newTile = map.getTile(xPos, yPos, getWidth(), getHeight());
				if(!newTile.hasUnit()) { 
					
					newTile.addUnit(u);
					map.getTile(u).removeUnit();
					isAttackPhase = true;
				}
				
			} else {
				
				Tile other = map.getTile(xPos, yPos, getHeight(), getWidth());
				
				if(!other.hasUnit()) {
					 
					
					
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
