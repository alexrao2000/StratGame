package map;

import java.awt.Color;
import java.awt.Graphics;

import units.Unit;

/**
 * This class represents a spot on the grid.
 * 
 * @author aghatge738
 *
 */
public class Tile {
	private String terrain;
	private Unit unit;
	
	public Tile(String terr) {
		terrain = terr;
		unit = null;
	}
	
	public Tile(String terr, Unit object) {
		terrain = terr;
		unit = object;
	}
	
	public String getTerrain() {
		return terrain;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void addUnit(Unit object) {
		unit = object;
	}
	
	public boolean hasUnit() {
		if(unit.equals(null)) {
			return false;
		}
		return true;
	}
	
	public void removeUnit() {
		unit = null;
	}
	
	public void draw(Graphics g, int xCor, int xDist, int yCor, int yDist) {
		if (terrain == "land")
			g.setColor(Color.GREEN);
		else if (terrain == "sea")
			g.setColor(Color.CYAN);
		g.fillRect(xCor, yCor, xDist, yDist);
		g.setColor(Color.BLACK);
	}

}
