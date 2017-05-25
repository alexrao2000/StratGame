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
	private ArrayList<Unit> player1;
	private ArrayList<Unit> player2;
	private int playerTurn;
	private boolean isAttackPhase;
	private Tile backOneTile;
	private Tile thisTile;
	private boolean thePlayer;
	
	/**
	 * Constructs a game menu
	 * @param m the main class
	 * */
	public GameMenu (Main m) {
		super();
		this.m = m;
		setBackground(Color.WHITE);
		
		Healer healer = new Healer(true, 5, 10, 5, 1);
		Attacker attacker = new Attacker(true, 7, 5, 5, 1);
		Attacker rangeAttacker = new Attacker(true, 5, 5, 5, 2);
		
		Healer eHealer = new Healer(false, 5, 10, 5, 1);
		Attacker eAttacker = new Attacker(false, 7, 5, 5, 1);
		Attacker eRangeAttacker = new Attacker(false, 5, 5, 5, 2);
		
		map = new Map();
		map.getTile(1, 1).addUnit(healer);
		map.getTile(2, 2).addUnit(attacker);
		map.getTile(3, 2).addUnit(rangeAttacker);
		map.getTile(8, 8).addUnit(eHealer);
		map.getTile(7, 7).addUnit(eAttacker);
		map.getTile(6, 7).addUnit(eRangeAttacker);
		
		thePlayer = true;
		playerTurn = 1;
		isAttackPhase = false;
		
		player1 = new ArrayList<Unit>();
		player1.add(attacker);
		player1.add(healer);
		player1.add(rangeAttacker);
		
		player2 = new ArrayList<Unit>();
		player2.add(eAttacker);
		player2.add(eHealer);
		player2.add(eRangeAttacker);
		
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
		
		if (player2.size() == 0) {
			g2.drawString("Player 1 wins!", 0, 20);
		} else if (player1.size() == 0)	{
			g2.drawString("Player 2 wins!", 0, 20);;
		}
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			m.changePanel("1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(thePlayer && playerTurn != 0 && playerTurn <= player1.size()) {
			Unit u = player1.get(playerTurn-1);

			int xPos = e.getX();
			int yPos = e.getY();
			//System.out.println("(x:"+xPos+", y:"+yPos+")");
			
			Tile newTile = map.getTile(xPos, yPos, getHeight(), getWidth());
			Tile oldTile = map.getTile(u);
			
			if(!isAttackPhase) {

				//System.out.println(!map.getTile(xPos, yPos, getHeight(), getWidth()).hasUnit());
				double distance = Math.abs(map.getTileRow(newTile) - map.getTileRow(oldTile)) + Math.abs(map.getTileCol(newTile) - map.getTileCol(oldTile));
				if(!newTile.hasUnit() && newTile.getTerrain() != "sea" && distance <= u.getMovementDistance()) { 
					newTile.addUnit(u);
					oldTile.removeUnit();
					backOneTile = oldTile;
					thisTile = newTile;
					u.setCanMove(false);
					repaint();
					isAttackPhase = true;
				}
				
			} else {
				
				double range = Math.abs(map.getTileRow(newTile) - map.getTileRow(oldTile)) + Math.abs(map.getTileCol(newTile) - map.getTileCol(oldTile));
				
				if (newTile.getUnit() == u) {
					
					u.setHasActed(true);
					
				} else if(u instanceof Attacker && newTile.hasUnit() && range <= u.getAttackDistance() && !newTile.getUnit().isPlayerControlled()) {

					((Attacker) u).attack(newTile.getUnit());
					if (newTile.getUnit().getHP() <= 0) {
						newTile.removeUnit();
						int i;
						for (i = 0; i < player2.size(); i++) {
							if (player2.get(i) == u) {
								break;
							}
						}
						player2.remove(i-1);
					}
					u.setHasActed(true);
					
				} else if(u instanceof Healer && newTile.hasUnit() && range <= u.getAttackDistance() && newTile.getUnit().isPlayerControlled()) {
					
					((Healer) u).heal(newTile.getUnit());
					u.setHasActed(true);
					
				} else {
					
					backOneTile.addUnit(u);
					thisTile.removeUnit();
					backOneTile = null;
					thisTile = null;
					u.setCanMove(true);
					repaint();
					playerTurn--;
					
				}
				
				isAttackPhase = false;
				repaint();
				playerTurn++;
			}
		} else if (!thePlayer && playerTurn != 0 && playerTurn <= player2.size()) {
			Unit u = player2.get(playerTurn-1);
			int xPos = e.getX();
			int yPos = e.getY();
			//System.out.println("(x:"+xPos+", y:"+yPos+")");
			
			Tile newTile = map.getTile(xPos, yPos, getHeight(), getWidth());
			Tile oldTile = map.getTile(u);
			
			if(!isAttackPhase) {

				//System.out.println(!map.getTile(xPos, yPos, getHeight(), getWidth()).hasUnit());
				double distance = Math.abs(map.getTileRow(newTile) - map.getTileRow(oldTile)) + Math.abs(map.getTileCol(newTile) - map.getTileCol(oldTile));
				if(!newTile.hasUnit() && newTile.getTerrain() != "sea" && distance <= u.getMovementDistance()) { 
					newTile.addUnit(u);
					oldTile.removeUnit();
					backOneTile = oldTile;
					thisTile = newTile;
					u.setCanMove(false);
					repaint();
					isAttackPhase = true;
				}
				
			} else {
				
				double range = Math.abs(map.getTileRow(newTile) - map.getTileRow(oldTile)) + Math.abs(map.getTileCol(newTile) - map.getTileCol(oldTile));
				
				if (newTile.getUnit() == u) {
					
					u.setHasActed(true);
					
				} else if(u instanceof Attacker && newTile.hasUnit() && range <= u.getAttackDistance() && !newTile.getUnit().isPlayerControlled()) {
					
					((Attacker) u).attack(newTile.getUnit());
					if (newTile.getUnit().getHP() <= 0) {
						newTile.removeUnit();
						int i;
						for (i = 0; i < player1.size(); i++) {
							if (player1.get(i) == u) {
								break;
							}
						}
						player1.remove(i-1);
					}
					u.setHasActed(true);
					
				} else if(u instanceof Healer && newTile.hasUnit() && range <= u.getAttackDistance() && newTile.getUnit().isPlayerControlled()) {
					
					((Healer) u).heal(newTile.getUnit());
					u.setHasActed(true);
					
				} else {
					
					backOneTile.addUnit(u);
					thisTile.removeUnit();
					backOneTile = null;
					thisTile = null;
					u.setCanMove(true);
					repaint();
					playerTurn--;
					
				}
				
				isAttackPhase = false;
				repaint();
				playerTurn++;
			}
		} else {
			if(thePlayer) {
				for (Unit u : player2) {
					u.setIsPlayerControlled(true);
				}
				
				for (Unit u : player1) {
					u.setHasActed(false);
					u.setIsPlayerControlled(false);
				}
			} else {
				for (Unit u : player1) {
					u.setIsPlayerControlled(true);
				}
				
				for (Unit u : player2) {
					u.setHasActed(false);
					u.setIsPlayerControlled(false);
				}
			}
			
			backOneTile = null;
			thisTile = null;
			thePlayer = !thePlayer;
			playerTurn = 1;
			repaint();
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

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
