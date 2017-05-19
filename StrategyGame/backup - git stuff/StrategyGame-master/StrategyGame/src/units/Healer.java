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
	private int hp;
	private int power;
	private int level;
	private int movementDistance;
	private boolean isPlayerControlled;
	//
	
	/**Creates a default healer object
	 * 
	 */
	public Healer()
	{
		hp = 5;
		power = 10;
		movementDistance = 15;
		level = 1;
		isPlayerControlled = true;
	}
	
	/**Creates a healer
	 * 
	 * @param hp the healer's health points
	 * @param power the amount of healer the healer does
	 */
	public Healer(boolean isPlayerControlled, int hp, int power)
	{
		this.isPlayerControlled = isPlayerControlled;
		this.hp = hp;
		this.power = power;
	}

	/**Heals a unit
	 * 
	 * @param other the Unit being healed
	 */
	public void heal(Unit other)
	{
		other.getHealed(this);
		//
	}
	
	public void draw(Graphics g, int xCor, int xDist, int yCor, int yDist)
	{
		g.setColor(Color.RED);
		if (isPlayerControlled)
			g.setColor(Color.BLUE);
		g.fillRect(xCor, yCor, xDist, yDist);
		g.setColor(Color.BLACK);
	}
	
}
