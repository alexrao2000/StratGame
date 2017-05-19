package units;

import java.util.ArrayList;

public class Attacker extends Unit{
	
	private int hp;
	private int power;
	//private ArrayList<Weapon> weapons;
	private boolean isPlayerControlled;
	private Weapon weapon;
	//
	/**Creates an attacker object that can move and attack
	 * 
	 * @param hp the attacker's health points
	 * @param power the amount of damage the attacker inflicts
	 */
	public Attacker(boolean isPlayerControlled, int hp, int power)
	{
		this.isPlayerControlled = isPlayerControlled;
		this.hp = hp;
		this.power = power;
		weapon = new Weapon();
		//weapons = new ArrayList<Weapon>();
	}
	
	/**makes the attacker attack an enemy
	 * 
	 */
	public void attack(Unit other)
	{
		other.takeDamage(weapon.getStrength() + getPower());
		
	}
	
	/**Makes the attacker get a weapon
	 * 
	 * @param weapon the weapon being collected
	 */
	public void changeWeapon(Weapon weapon)
	{
		//weapons.add(weapon);
		this.weapon = weapon;
	}
	
	public void draw()
	{
		
		
	}
	
}
