package map;

import units.*;

public class AttackPhase {
	private Unit unit;
	
	public AttackPhase(Unit u) {
		unit = u;
	}
	
	public void run(Unit u) {
		if(unit instanceof Healer) {
			if(u.isPlayerControlled()) {
				((Healer)unit).heal(u);
			}
		} else {
			if(!u.isPlayerControlled() && !(u instanceof units.Object)) {
				u.takeDamage(unit.getPower());
			}
		}
	}
	
}
