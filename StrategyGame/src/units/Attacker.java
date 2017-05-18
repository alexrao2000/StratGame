package units;

import java.awt.Graphics;

public class Attacker extends Unit{
	
	private int hp;
	private int power;
	private int level;
	private int movementDistance;
	//private ArrayList<Weapon> weapons;
	private boolean isPlayerControlled;
	private Weapon weapon;
	//
	public Attacker()
	{
		hp = 10;
		power = 10;
		movementDistance = 10;
		level = 1;
		weapon = new Weapon();
	}
	
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
		level = 1;
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
	
	public void draw(Graphics g, int xCor, int xDist, int yCor, int yDist)
	{
		g.drawOval(xCor, yCor, xDist, yDist);
		
	}
	
}
