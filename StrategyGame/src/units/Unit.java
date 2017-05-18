package units;

import java.awt.Graphics;

/**This class represents a Unit object on the grid
 * 
 * @author ewan633
 *
 */
public class Unit//TO DO: Make Unit class use Map class to find location and more 
{
	
	private int hp;
	private int power;
	private boolean isPlayerControlled;
	private int movementDistance;
	private int level;
	
	
	/**
	 * Creates a default unit with both hp and power at 10
	 */
	public Unit()
	{
		hp = 10;
		power = 10;
		movementDistance = 10;
		level = 1;
	}
	
	/**Creates a unit
	 * 
	 * @param isPlayerControlled whether or not the player controls the unit
	 * @param hp the amount of health points the unit will have
	 * @param power the amount of damage/healing the unit will do
	 * @param movementDistance how far the unit can move in one time
	 */
	public Unit(boolean isPlayerControlled, int hp, int power, int movementDistance)
	{
		this.isPlayerControlled = isPlayerControlled;
		this.hp = hp;
		this.power = power;
		this.movementDistance = movementDistance;
		level = 1;
	}
	
	/**Heals the unit
	 * 
	 * @param healer the healer that is healing the unit
	 */
	public void getHealed(Healer healer)
	{
		hp += healer.getPower();
		
	}
	
	/**Makes the unit suffer damage
	 * 
	 * @param weapon the weapon used to attack this unit
	 */
	public void takeDamage(int amount)
	{
		hp -= amount;
		
		if(hp <= 0)
			die();
	}
	
	/**Makes unit die and removes him from grid
	 * 
	 */
	public void die()
	{
		if(hp <= 0)
		{
			
		}
	}
	
	/**Moves the unit
	 * 
	 * @param degree the direction to move
	 * 
	 */
	public void move(int degree)//will probably make move commands button direction based; WASD
	{
		
		
	}
	
	/**Returns the power level of the unit
	 * 
	 * @return the current power of the unit
	 */
	public int getPower()
	{
		if(power > 9000)
			System.out.println("Its over 9000");
		
		return power;
	}
	
	/**Returns the distance the unit can move in one turn
	 * 
	 * @return
	 */
	public int getMovementDistance() {
		return movementDistance;
	}
	
	/**Levels the unit up; increases hp and power by a random number
	 * 
	 */
	public void levelUp()
	{
		level++;
		hp += (int)(Math.random()*10);
		power += (int)(Math.random()*10);
	}
	
	public boolean isPlayerControlled()
	{
		return isPlayerControlled;
	}

	public void draw(Graphics g, int xCor, int xDist, int yCor, int yDist)
	{
		g.drawOval(xCor, yCor, xDist, yDist);
		
	}
}
