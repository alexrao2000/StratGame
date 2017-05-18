package map;

import units.Unit;

public class Turn {
	private MovementPhase mPhase;
	private AttackPhase aPhase;
	
	public Turn(Unit u) {
		mPhase = new MovementPhase(u);
		aPhase = new AttackPhase(u);
	}
	
	public void runMovementPhase(Map map, int r, int c) {
		mPhase.run(r, c, map);
	}
	
	public void runAttackPhase(Unit u, boolean wantToAttack) {
		if(wantToAttack) {
			aPhase.run(u);
		}
		
	}
}
