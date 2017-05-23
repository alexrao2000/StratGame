package map;

import units.*;

public class MovementPhase {
	private Unit unit;
	
	/**
	 * Creates a movement phase where a unit can move
	 * @param u the unit that will move
	 * */
	public MovementPhase(Unit u) {
		unit = u;
	}
	
	/**
	 * Moves the unit to another spot
	 * @param xPos the x coordinate to move to
	 * @param yPos the y coordinate to move to
	 * @param width the width of the map
	 * @param height the height of the map
	 * @param map the map the unit will move on
	 * */
	public void run(int xPos, int yPos, int height, int width, Map map) {
		int range = unit.getMovementDistance();
		int currentRow = map.getUnitRow(unit);
		int currentCol = map.getUnitCol(unit);
		int r = map.getTileRow(map.getTile(xPos, yPos, height, width));	
		int c = map.getTileCol(map.getTile(xPos, yPos, height, width));
		
		if(r < 15 && r >= 0 && c < 15 &&  c >= 0) {
			if(r < currentRow+range && r > currentRow+range && c < currentCol+range && c > currentCol+range) {
				if(map.getObjectAtSpot(currentRow, currentCol).equals(null)) {
					map.changeUnitPlace(unit, r, c);
					
				}
			}
		}
		
	}

}
