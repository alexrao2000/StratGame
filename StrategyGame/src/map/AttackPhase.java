package map;

import units.*;

public class AttackPhase {
	private Unit unit;
	
	/**
	 * Creates an attack phase where a unit can perform an action
	 * @param u the unit that will attack/heal
	 * */
	public AttackPhase(Unit u) {
		unit = u;
	}
	
	/**
	 * Damages or heals another unit
	 * @param u the unit to be attacked/healed
	 * */
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
