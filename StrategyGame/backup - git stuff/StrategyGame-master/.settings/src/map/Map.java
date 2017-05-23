package map;

import units.Unit;
import java.awt.Graphics;

public class Map {
	
	private Tile[][] map;
	
	public Map() {
		map = new Tile[15][15];
	}
	
	public Tile getTile(int r, int c) {
		if(r >= 15 || r < 0) {
			if(c >= 15 || c < 0) {
				return map[r][c];
			}
		}
		return null;
	}
	
	public Unit getObjectAtSpot(int r, int c, int degrees) {
		if(r >= 0 && r < 15 && c >= 0 && c < 15) {
			return map[r][c].getUnit();
		}
		return null;
	}
	
	public Unit getAdjacentObject(int r, int c, int degrees) {
		if(degrees == 0) {
			if(r+1 < 15) {
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
			if(c+1 < 15) {
				return map[r][c+1].getUnit();
			} else {
				return null;
			}
		}
	}
	
	
	
	public void paintComponent(Graphics g, int height, int width) {
		int xDist = height/15;
		int yDist = width/15;
		
		int xCor = xDist;
		int yCor = yDist;
		
		for(int x = 0; x < 15; x++) {
			g.drawLine(xCor, 0, xCor, height);
			g.drawLine(0, yCor, width, yCor);
			xCor += xDist;
			yCor += yDist;
		}
	}

}
