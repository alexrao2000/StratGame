package units;

import java.awt.Color;
import java.awt.Graphics;

/**This class represents a unit that can heal, a healer
 * 
 * @author ewan633
 *
 */
public class Healer extends Unit
{
	
	
	/**Creates a default healer object
	 * 
	 */
	public Healer()
	{
		super(true, 5, 10, 10, 0);
	}
	
	/**Creates a healer
	 * 
	 * @param isPlayerControlled whether or not the player controls the unit
	 * @param hp the amount of health points the unit will have
	 * @param power the amount of damage/healing the unit will do
	 * @param movementDistance how far the unit can move in one time
	 * @param healDistance how far the unit can heal
	 */
	public Healer(boolean isPlayerControlled, int hp, int power, int movementDistance, int healDistance)
	{
		super(isPlayerControlled, hp, power, movementDistance, healDistance);
	}

	/**Heals a unit
	 * 
	 * @param other the Unit being healed
	 */
	public void heal(Unit other)
	{
		other.getHealed(this);
		
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
		g.fillRect(xCor, yCor, xDist, yDist);
		g.setColor(Color.BLACK);
	}
	
}
