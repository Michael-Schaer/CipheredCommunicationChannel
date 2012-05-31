/**
 * A Consumer that checks the ListBuffer every DELAY milliseconds for new Items stored.
 * When items are found, they are consumed, (de)ciphered and stored in the DiagramData
 */
public class Consumer implements Runnable
{
	private final int DELAY;
	private Cipherer cipherer;
	private ListBuffer<String> buf;
	private MaskGenerator maskGenerator;
	private DiagramData data;

	public Consumer(ListBuffer<String> inBuf, MaskGenerator mGen, DiagramData d,int delay)
	{
		DELAY = delay;
		buf = inBuf;
		maskGenerator = mGen;
		data = d;
		
		cipherer = new Cipherer();
	}

	public void run() 
	{
		try 
		{
			while(Launcher.getConsFlag() || Launcher.getProdFlag())
			{
				if(buf.hasItem())
				{
					String item = buf.get(); // retrieve item
					item = cipherer.cipher(item.toString(),maskGenerator.getCurrentMask());
					data.addEvent(ClassToken.Consume.toString(), "Decipher: "+item, String.valueOf(maskGenerator.getCurrentMask()));
					Launcher.setConsFlag(true);
				}
				else
				{
					Launcher.setConsFlag(false);
					//Use this only for testing: data.addEvent(ClassToken.Consume.toString(), "No item found: ", String.valueOf(maskGenerator.getCurrentMask())); // Log even if no item is found //
				}

				Thread.sleep(DELAY);
			}
		} 
		catch (InterruptedException ex) 
		{ 
			System.out.println(ex.getMessage()); 
		}
	}
}