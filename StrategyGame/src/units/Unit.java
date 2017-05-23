package units;

import java.awt.Graphics;
import map.Map;
import map.Tile;

/**This class represents a Unit object on the grid
 * 
 * @author ewan633
 *
 */
public class Unit
{
	
	private int hp;
	private int power;
	private boolean isPlayerControlled;
	private int movementDistance;
	private int actionDistance;//the distance the unit can heal/attack
	private int level;
	private String name;
	
	
	/**
	 * Creates a default unit with both hp and power at 10
	 */
	public Unit()
	{
		hp = 10;
		power = 10;
		movementDistance = 10;
		actionDistance = 1;
		level = 1;
		name = "Unit";
	}
	
	/**Creates a unit
	 * 
	 * @param isPlayerControlled whether or not the player controls the unit
	 * @param hp the amount of health points the unit will have
	 * @param power the amount of damage/healing the unit will do
	 * @param movementDistance how far the unit can move in one time
	 * @param actionDistance how far the unit can attack
	 */
	public Unit(boolean isPlayerControlled, int hp, int power, int movementDistance, int actionDistance)
	{
		this.isPlayerControlled = isPlayerControlled;
		this.hp = hp;
		this.power = power;
		this.movementDistance = movementDistance;
		this.actionDistance = actionDistance;
		level = 1;
		name = "";
		if(!isPlayerControlled) {
			name += "Enemy ";
		}
		name += "Unit";
		//map == null;
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
	
	public void insertSelf(Tile tile, int row, int col)
	{
		if(tile.getUnit() == null)
			tile.addUnit(this);
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
		return actionDistance;
	}
	
	
	/**Returns the level of the unit
	 * 
	 * @return the current level of the unit
	 */
	public int getLevel() {
		return level;
	}
	
	/**Returns the name of the unit
	 * 
	 * @return the name of the unit
	 */
	public String getName() {
		return name;
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
