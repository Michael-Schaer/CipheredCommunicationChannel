import javax.swing.JFrame;

/**
 * A MaskGenerator is used to generate a random Integer every DELAY milliseconds.<br>
 * This cycle is stopped, when there is no more item to produce and consume. Afterwards the DiagramFrame is started to present the information that was collected
 *
 */
public class MaskGenerator extends Thread
{
	private int currentMask = 0;
	private final int DELAY;
	private Cipherer cipherer;
	private DiagramData data;
	private final int FRAME_WIDTH = 600, FRAME_HEIGHT = 600;
	
	public MaskGenerator(DiagramData d, int delay)
	{
		DELAY = delay;
		cipherer = new Cipherer();
		data = d;
	}

	public void run()
	{		
		try 
		{
			while(Launcher.getConsFlag() || Launcher.getProdFlag())
			{
				currentMask = cipherer.generateInt();
				data.addEvent(ClassToken.MaskGen.toString(), "Generate Mask:",String.valueOf(currentMask));

				sleep(DELAY);
			}
			
			DiagramFrame frame = new DiagramFrame(data);
			frame.setTitle("Diagram");
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} 
		catch (InterruptedException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public synchronized int getCurrentMask()
	{
		return currentMask;
	}


}
