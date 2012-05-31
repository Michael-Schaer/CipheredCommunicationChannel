/**
 * This Producer is used to generate random Strings every DELAY milliseconds and store them ciphered to the ListBuffer.
 */
public class Producer extends Thread 
{
	private final int DELAY,QUANTITY;
	private Cipherer cipherer;
	private ListBuffer<String> buf;
	private MaskGenerator maskGenerator;
	private DiagramData data;

	public Producer(ListBuffer<String> inBuf, MaskGenerator mGen, DiagramData d, int delay, int quantity)
	{
		DELAY = delay;
		QUANTITY = quantity;
		buf = inBuf;
		maskGenerator = mGen;
		data = d;
		
		cipherer = new Cipherer();
	}

	public void run() 
	{
		int prodCounter = 0;
		try 
		{
			while (prodCounter < QUANTITY) 
			{
				
				String item = cipherer.generateString();
				data.addEvent(ClassToken.Produce.toString(), "Generate:  "+item, String.valueOf(maskGenerator.getCurrentMask()));
				
				item = cipherer.cipher(item.toString(),maskGenerator.getCurrentMask());
				data.addEvent(ClassToken.Produce.toString(), "Cipher:    "+item, String.valueOf(maskGenerator.getCurrentMask()));

				buf.put(item); // store item into buffer
				System.out.println("Produced item "+(prodCounter+1)+" of "+QUANTITY);
				prodCounter++;
				
				sleep(DELAY);
			}
			Launcher.setProdFlag(false);
		} 
		catch (InterruptedException ex) 
		{ 
			System.out.println(ex.getMessage());
		}
	}
}