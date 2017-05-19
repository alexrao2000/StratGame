package map;

import units.*;

public class MovementPhase {
	private Unit unit;
	
	
	public MovementPhase(Unit u) {
		unit = u;
	}
	
	public void run(int r, int c, Map map) {
		int range = unit.getMovementDistance();
		int currentRow = map.getUnitRow(unit);
		int currentCol = map.getUnitCol(unit);
		
		if(r < 15 && r >= 0 && c < 15 &&  c >= 0) {
			if(r < currentRow+range && r > currentRow+range && c < currentCol+range && c > currentCol+range) {
				if(map.getObjectAtSpot(currentRow, currentCol).equals(null)) {
					map.changeUnitPlace(unit, r, c);
					
				}
			}
		}
		
	}

}
