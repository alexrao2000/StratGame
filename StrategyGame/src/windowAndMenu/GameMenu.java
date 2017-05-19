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
		
		map = new Map();
		map.getTile(0, 0).addUnit(new Healer());
		map.getTile(8, 1).addUnit(new Attacker());
		
		playerTurn = 0;
		isAttackPhase = false;
		players = new ArrayList<Unit>();
		enemies = new ArrayList<Unit>();
		
		for (Unit u:map.getAllUnits()) {
			if(u.isPlayerControlled()) {
				players.add(u);
				playerTurn++;
			} else if (!(u instanceof units.Object)) {
				enemies.add(u);
			}
		}
		
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
		if(playerTurn != 0) {
			Unit u = players.get(playerTurn-1);
			if(!isAttackPhase) {
				int xPos = e.getX();
				int yPos = e.getY();
				MovementPhase mPhase = new MovementPhase(u);
				mPhase.run(map.getUnitRow(u), map.getUnitCol(u), getHeight(), getWidth(), map);
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
