package map;

import units.Unit;
import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
	
	private Tile[][] map;
	
	/**
	 * Creates a map with 20 by 20 dimensions and randomly generated tile terrain
	 */
	public Map() {
		map = new Tile[20][20];
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				int x = (int) (Math.random() * 4);
				if (x > 0)
					map[i][j] = new Tile("land");
				else if (x == 0)
					map[i][j] = new Tile("sea");
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j] = new Tile("land");
			}
		}
		for (int i = 6; i < 9; i++) {
			for (int j = 6; j < 9; j++) {
				map[i][j] = new Tile("land");
			}
		}
	}
	
	/**
	 * Finds the number of rows in the map grid
	 * @return rows in the map grid
	 */
	public int getRows() {
		return map.length;
	}
	
	/**
	 * Finds the number of columns in the map grid
	 * @return the columns in the map grid
	 */
	public int getCols() {
		return map[0].length;
	}
	
	/**
	 * Switches the location of a unit on the grid
	 * @param u the unit being moved
	 * @param newR the new row the unit is on
	 * @param newC the new column the unit is on
	 */
	public void changeUnitPlace(Unit u, int newR, int newC) {
		if(newR < 15 && newR >= 0 && newC < 15 &&  newC >= 0 && map[newR][newC].equals(null)) {
			Unit x = map[getUnitRow(u)][getUnitCol(u)].getUnit();
			map[getUnitRow(u)][getUnitCol(u)].removeUnit();
			map[newR][newC].addUnit(x);
		}
	}
	
	/**
	 * Returns all tiles in the map
	 * @return the 2D array holding all tiles in the map
	 */
	public Tile[][] getAllTiles() {
		return map;
	}
	
	/**
	 * Returns a tile on the map containing the unit passed in
	 * @param u the unit the tile contains
	 * @return the tile containing the unit or a "nonexistent" tile
	 */
	public Tile getTile(Unit u) {
		int c = getUnitCol(u);
		int r = getUnitRow(u);
		if(r >= 0 && r < 20 && c >= 0 && c < 20) {
			return map[r][c];
		}
		return new Tile("nonexistent");
	}
	
	/**
	 * Returns a tile on the map based on the coordinates passed in
	 * @param r the row the tile is on
	 * @param c the column the tile is on
	 * @return the tile matching the coordinates or a "nonexistent" tile
	 */
	public Tile getTile(int r, int c) {
		if(r < 20 && r >= 0) {
			if(c < 20 && c >= 0) {
				return map[r][c];
			}
		}
		return new Tile("nonexistent");
	}
	
	/**
	 * Returns a tile on the map based on the the position of a pixel and dimensions of the map
	 * @param xPos the x-coordinate of the pixel
	 * @param yPos the y-coordinate of the pixel
	 * @param width the width of the panel
	 * @param height the height of the panel
	 * @return the tile based on the position and dimensions
	 */
	public Tile getTile(int xPos, int yPos, int width, int height) {
		int boxWidth = width/20;
		int boxHeight = height/20;
		
		int xCurr = 0;
		int yCurr = 0;
		
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 20; y++) {
				if(yPos > xCurr && yPos < xCurr+boxWidth && xPos > yCurr && xPos < yCurr+boxHeight) {
					//System.out.println("(xPos:"+xCurr+", yPos:"+yCurr+")");
					return map[x][y];
					
				}
				xCurr += boxWidth;
				
			}
			yCurr += boxHeight;
			xCurr = 0;
		}
		
		return new Tile("nonexistent");
	}
	
	/**
	 * Gets the x-coordinate of a tile on the grid
	 * @param tile the tile with the coordinate
	 * @param width the width of the panel
	 * @return the x-coordinate of the tile
	 */
	public int getTileXPos(Tile tile, int width) {
		int ratio = width/20;
		int answer = 0;
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 20; y++) {
				if(map[x][y].equals(tile)) {
					return answer+1; 
				}
			}
			answer += ratio;
		}
		
		return -1;
	}
	
	/**
	 * Gets the y-coordinate of a tile on the grid
	 * @param tile the tile with the coordinate
	 * @param height the height of the panel
	 * @return the y-coordinate of the tile
	 */
	public int getTileYPos(Tile tile, int height) {
		int ratio = height/20;
		int answer = 0;
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 20; y++) {
				if(map[x][y].equals(tile)) {
					return answer+1; 
				}
			}
			answer += ratio;
		}
		
		return -1;
	}
	
	/**
	 * Gets the row of a tile on the grid
	 * @param tile the tile with the coordinate
	 * @return the row the tile is on
	 */
	public int getTileRow(Tile tile) {
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 20; y++) {
				if(map[x][y].equals(tile)) {
					return x; 
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Gets the column of a tile on the grid
	 * @param tile the tile with the coordinate
	 * @return the column the tile is on
	 */
	public int getTileCol(Tile tile) {
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 20; y++) {
				if(map[x][y].equals(tile)) {
					return y; 
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Gets the unit at a particular space
	 * @param r the row of the unit
	 * @param c the column of the unit
	 * @return the unit at the coordinates passed in
	 */
	public Unit getObjectAtSpot(int r, int c) {
		if(r >= 0 && r < 20 && c >= 0 && c < 20) {
			return map[r][c].getUnit();
		}
		return null;
	}
	
	/**
	 * Gets the unit adjacent to a particular space
	 * @param r the row of the unit
	 * @param c the column of the unit
	 * @param degrees direction the adjacent tile is in
	 * @return the unit at the coordinates adjacent in the correct direction
	 */
	public Unit getAdjacentObject(int r, int c, int degrees) {
		if(degrees == 0) {
			if(r+1 < 20) {
				return map[r+1][c].getUnit();
			} else {
				return null;
			}
		} else if(degrees == 90) {
			if(c-1 >+ 0) {
				return map[r][c-1].getUnit();
			} else {
				return null;
			}
		} else if(degrees == 180) {
			if(r-1 >= 0) {
				return map[r-1][c].getUnit();
			} else {
				return null;
			}
		} else {
			if(c+1 < 20) {
				return map[r][c+1].getUnit();
			} else {
				return null;
			}
		}
	}
	
	/**
	 * Gets the row of a unit on the grid
	 * @param u the unit with the coordinate
	 * @return the row the unit is on
	 */
	public int getUnitRow(Unit u) {
		for(int r = 0; r < 15; r++) {
			for(int c = 0; c < 15; c++) {
				if(map[r][c].hasUnit() && map[r][c].getUnit().equals(u)) {
					return r;
				}
			}
		}
		
		return -1;
	}
	
	
	/**
	 * Gets the column of a unit on the grid
	 * @param u the unit with the coordinate
	 * @return the column the unit is on
	 */
	public int getUnitCol(Unit u) {
		for(int r = 0; r < 15; r++) {
			for(int c = 0; c < 15; c++) {
				if(map[r][c].hasUnit() && map[r][c].getUnit().equals(u)) {
					return c;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Draws the map and all it contains
	 * @param g the graphics object
	 * @param width width of the panel
	 * @param height height of the panel
	 */
	public void paintComponent(Graphics g, int height, int width) {
		int xDist = width/20;
		int yDist = height/20;
		
		int xCor = xDist;
		int yCor = yDist;
		
		g.drawLine(0, 0, width, 0);
		g.drawLine(0, 0, 0, height);
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 20; y++) {
				map[x][y].draw(g, x*xDist + 1, xDist - 1, y*yDist + 1, yDist - 1);
				if (map[x][y].getUnit() != null)
					map[x][y].getUnit().draw(g, x*xDist + xDist/4, xDist/2, y*yDist + yDist/4, yDist/2);
			}
			g.drawLine(xCor, 0, xCor, height);
			g.drawLine(0, yCor, width, yCor);
			xCor += xDist;
			yCor += yDist;
		}
	}
		
}
