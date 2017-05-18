package map;

import units.Unit;
import java.awt.Graphics;

public class Map {
	
	private Tile[][] map;
	
	public Map() {
		map = new Tile[20][20];
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = new Tile("land");
			}
		}
	}
	
	public Tile getTile(int r, int c) {
		if(r < 20 && r >= 0) {
			if(c < 20 && c >= 0) {
				return map[r][c];
			}
		}
		return null;
	}
	
	public Unit getObjectAtSpot(int r, int c, int degrees) {
		if(r >= 0 && r < 20 && c >= 0 && c < 20) {
			return map[r][c].getUnit();
		}
		return null;
	}
	
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
	
	public int getUnitRow(Unit u) {
		for(int r = 0; r < 15; r++) {
			for(int c = 0; c < 15; c++) {
				if(map[r][c].getUnit().equals(u)) {
					return r;
				}
			}
		}
		
		return -1;
	}
	
	public int getUnitCol(Unit u) {
		for(int r = 0; r < 15; r++) {
			for(int c = 0; c < 15; c++) {
				if(map[r][c].getUnit().equals(u)) {
					return c;
				}
			}
		}
		
		return -1;
	}
	
	public void paintComponent(Graphics g, int height, int width) {
		int xDist = width/20;
		int yDist = height/20;
		
		int xCor = xDist;
		int yCor = yDist;
		
		for(int x = 0; x < 20; x++) {
			g.drawLine(xCor, 0, xCor, height);
			g.drawLine(0, yCor, width, yCor);
			for(int y = 0; y < 20; y++) {
				if (map[x][y].getUnit() != null) {
					map[x][y].getUnit().draw(g, x*xDist + xDist/4, xDist/2, y*yDist + yDist/4, yDist/2);
				}
			}
			xCor += xDist;
			yCor += yDist;
		}
	}

}
