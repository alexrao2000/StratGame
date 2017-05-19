package units;

import java.awt.Graphics;

/**This class represents a Unit object on the grid
 * 
 * @author ewan633
 *
 */
public class Unit//TO DO: Make Unit class use Map class to find location and more. 
{
	
	private int hp;
	private int power;
	private boolean isPlayerControlled;
	private int movementDistance;
	private int attackDistance;
	private int level;
	
	
	/**
	 * Creates a default unit with both hp and power at 10
	 */
	public Unit()
	{
		hp = 10;
		power = 10;
		movementDistance = 10;
		attackDistance = 10;
		level = 1;
	}
	
	/**Creates a unit
	 * 
	 * @param isPlayerControlled whether or not the player controls the unit
	 * @param hp the amount of health points the unit will have
	 * @param power the amount of damage/healing the unit will do
	 * @param movementDistance how far the unit can move in one time
	 * @param attackDistance how far the unit can attack
	 */
	public Unit(boolean isPlayerControlled, int hp, int power, int movementDistance, int attackDistance)
	{
		this.isPlayerControlled = isPlayerControlled;
		this.hp = hp;
		this.power = power;
		this.movementDistance = movementDistance;
		this.attackDistance = attackDistance;
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
	 * @param amount the amount of damage the unit takes
	 */
	public void takeDamage(int amount)
	{
		hp -= amount;
	}
	
	/**Checks whether or not the unit is alive
	 * 
	 * @return if the unit is alive or dead
	 */
	public boolean isAlive()
	{
		if(hp <= 0)
		{
			return false;
		}
		return true;
	}
	
	/**Moves the unit
	 * 
	 * @param degree the direction to move
	 */
	public void move(int degree)//will probably make move commands button direction based; WASD
	{
		
		
	}
	
	/**Returns the hp the unit has
	 * 
	 * @return the current hp of the unit
	 */
	public int getHP() {
		return hp;
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
	 * @return the distance the unit can move
	 */
	public int getMovementDistance() {
		return movementDistance;
	}
	
	/**Returns the distance the unit can attack from
	 * 
	 * @return the distance the unit can attack from
	 */
	public int getAttackDistance() {
		return attackDistance;
	}
	
	
	/**Returns the level of the unit
	 * 
	 * @return the current level of the unit
	 */
	public int getLevel() {
		return level;
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
	
	
	/**Returns whether or not the unit is player controlled
	 * 
	 * @return whether or not the unit is controlled by the player
	 */
	public boolean isPlayerControlled()
	{
		return isPlayerControlled;
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
		g.drawOval(xCor, yCor, xDist, yDist);
		
	}
}
