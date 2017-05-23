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
	
	/**
	 * Creates a tile with no unit
	 * @param terr type of terrain
	 * */
	public Tile(String terr) {
		terrain = terr;
		unit = null;
	}
	
	/**
	 * Creates a tile with a unit on it
	 * @param terr type of terrain
	 * @param object on the terrain
	 * */
	public Tile(String terr, Unit object) {
		terrain = terr;
		unit = object;
	}
	
	/**
	 * Returns the terrain type
	 * @return the terrain type
	 * */
	public String getTerrain() {
		return terrain;
	}
	
	/**
	 * Returns the unit on the tile
	 * @return the unit on the tile
	 * */
	public Unit getUnit() {
		return unit;
	}
	
	/**
	 * Adds a unit to the tile
	 * @param the unit to be added to the tile
	 * */
	public void addUnit(Unit object) {
		unit = object;
	}
	
	/**
	 * Determines whether the tile has a unit on it
	 * @return true or false depending on whether there is a unit on the tile
	 * */
	public boolean hasUnit() {
		if(unit == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Removes the unit from the tile
	 * @post the unit on the tile is null
	 * */
	public void removeUnit() {
		unit = null;
	}
	
	/**
	 * Draws the tile
	 * @param g the graphics object
	 * @param xCor the x coordinate of where the tile begins
	 * @param xDist width of the tile
	 * @param yCor the y coordinate of where the tile begins
	 * @param yDist height of the tile
	 * */
	public void draw(Graphics g, int xCor, int xDist, int yCor, int yDist) {
		if (terrain == "land")
			g.setColor(Color.GREEN);
		else if (terrain == "sea")
			g.setColor(Color.CYAN);
		g.fillRect(xCor, yCor, xDist, yDist);
		g.setColor(Color.BLACK);
	}

}
