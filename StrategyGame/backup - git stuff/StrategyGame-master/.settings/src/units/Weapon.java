package units;

/**This class represents a weapon used by attackers
 * 
 * @author ewan633
 *
 */
public class Weapon 
{//
	private String name;
	private int strength;
	
	public Weapon()
	{
		name = "Default";
		strength = 10;
	}
	
	/**Creates a weapon
	 * 
	 * @param name name of weapon
	 * @param strength 
	 */
	public Weapon(String name, int strength)
	{
		this.name = name;
		this.strength = strength;
	}
	
	public void alterStrength(int amount)
	{
		strength += amount;
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getStrength()
	{
		return strength;
	}
}
