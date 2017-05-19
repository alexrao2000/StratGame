package units;

import java.awt.Color;
import java.awt.Graphics;

public class Attacker extends Unit{
	
	
	//private ArrayList<Weapon> weapons;
	private Weapon weapon;
	
	public Attacker()
	{
		super(true, 10, 10, 10, 5);
		weapon = new Weapon();
	}
	
	/**Creates an attacker object that can move and attack
	 * 
	 * @param isPlayerControlled whether or not the player controls the unit
	 * @param hp the amount of health points the unit will have
	 * @param power the amount of damage/healing the unit will do
	 * @param movementDistance how far the unit can move in one time
	 * @param attackDistance how far the unit can attack
	 */
	public Attacker(boolean isPlayerControlled, int hp, int power, int movementDistance, int attackDistance)
	{
		super(isPlayerControlled, hp, power, movementDistance, attackDistance);
		weapon = new Weapon();
		//weapons = new ArrayList<Weapon>();
	}
	
	/**makes the attacker attack an enemy
	 * 
	 * @param other the enemy
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
	
	/**Draws the unit
	 * 
	 * @param g the Graphics class used to draw the unit
	 * @param xCor the x coordinate of the unit
	 * @param xDist the width of the unit
	 * @param yCor the y coordinate of the unit
	 * @param yDist the height of the unit
	 */
	public void draw(Graphics g, int xCor, int xDist, int yCor, int yDist)
	{
		g.setColor(Color.RED);
		if (super.isPlayerControlled())
			g.setColor(Color.BLUE);
		g.fillOval(xCor, yCor, xDist, yDist);
		g.setColor(Color.BLACK);
		
	}
	
}
