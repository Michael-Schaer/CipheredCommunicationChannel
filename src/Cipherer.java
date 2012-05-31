import java.util.Random;

/**
 * A Cipherer can generate random Strings, Integers and Cipher them with an XOR comparison
 * 
 */
public class Cipherer 
{
	private Random rander;
	
	public Cipherer()
	{
		rander = new Random();
	}
	
	/**
	 * generates a random String
	 */
	public String generateString()
	{
		return Integer.toString(Math.abs(rander.nextInt()), Integer.SIZE);
	}
	
	/**
	 * generates a random Integer
	 */
	public int generateInt() 
	{
		return rander.nextInt();
	}
	
	/**
	 * @param item A String to cipher
	 * @param number The Mask used to cipher
	 * @return A String that is an XOR combination of item and number
	 */
	public String cipher(String item, int number)
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < item.length(); i++)
		{
			sb.append((char)(item.charAt(i) ^ number));
		}
		return sb.toString();
	}
}