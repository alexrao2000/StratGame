package units;

/**This class represents a unit that can heal, a healer
 * 
 * @author ewan633
 *
 */
public class Healer extends Unit
{
	private int hp;
	private int power;
	private boolean isPlayerControlled;
	//
	
	/**Creates a default healer object
	 * 
	 */
	public Healer()
	{
		super();
		
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
		
	}
	
	public void draw()
	{
		
		
	}
	
}
