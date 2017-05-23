package map;

import units.Unit;
import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
	
	private Tile[][] map;
	
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
		for (int i = 15; i < map[0].length; i++) {
			for (int j = 15; j < map.length; j++) {
				map[i][j] = new Tile("land");
			}
		}
	}
	
	
	public int getRows() {
		return map.length;
	}
	
	public int getCols() {
		return map[0].length;
	}
	
	
	public void changeUnitPlace(Unit u, int newR, int newC) {
		if(newR < 15 && newR >= 0 && newC < 15 &&  newC >= 0 && map[newR][newC].equals(null)) {
			Unit x = map[getUnitRow(u)][getUnitCol(u)].getUnit();
			map[getUnitRow(u)][getUnitCol(u)].removeUnit();
			map[newR][newC].addUnit(x);
		}
	}
	
	public Tile[][] getAllTiles() {
		return map;
	}
	
	public Tile getTile(Unit u) {
		int c = getUnitCol(u);
		int r = getUnitRow(u);
		if(r >= 0 && r < 20 && c >= 0 && c < 20) {
			return map[r][c];
		}
		return new Tile("nonexistent");
	}
	
	public Tile getTile(int r, int c) {
		if(r < 20 && r >= 0) {
			if(c < 20 && c >= 0) {
				return map[r][c];
			}
		}
		return new Tile("nonexistent");
	}
	
	public Tile getTile(int xPos, int yPos, int height, int width) {
		int boxWidth = width/20;
		int boxHeight = height/20;
		
		int xCurr = 0;
		int yCurr = 0;
		
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 20; y++) {
				if(xPos > xCurr && xPos < xCurr+boxWidth && yPos > yCurr && yPos < yCurr+boxHeight) {
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
	
	public int getTileXPos(Tile tile, int width) {
		int ratio = width/20;
		int answer = 0;
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 20; y++) {
				if(map[y][x].equals(tile)) {
					return answer+1; 
				}
			}
			answer += ratio;
		}
		
		return -1;
	}
	
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
	
	public Unit getObjectAtSpot(int r, int c) {
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
				if(map[r][c].hasUnit() && map[r][c].getUnit().equals(u)) {
					return r;
				}
			}
		}
		
		return -1;
	}
	
	
	
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
