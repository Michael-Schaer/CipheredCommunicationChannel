public class Launcher 
{
	private static final int PROD_DELAY = 200; 	// Default: 200
	private static final int PROD_QUANTITY = 7; // Default: 7
	private static final int CONS_DELAY = 110; 	// Default: 110
	private static final int MASK_DELAY = 280; 	// Default: 280

	private static boolean consFlag=true, prodFlag=true;

	/**
	 * Starts a Buffer and the threads MaskGenerator, Producer, Consumer
	 */
	public static void main(String[] args) 
	{		
		DiagramData data = new DiagramData();

		MaskGenerator maskGenerator = new MaskGenerator(data, MASK_DELAY);
		maskGenerator.start();

		// Instantiate the buffer
		ListBuffer<String> buf = new ListBuffer<String>();

		Thread producer = new Thread(new Producer(buf,maskGenerator,data,PROD_DELAY, PROD_QUANTITY));
		producer.start();

		Thread consumer = new Thread(new Consumer(buf,maskGenerator,data,CONS_DELAY));
		consumer.start(); 
	}
	
	/*
	 * Getters and Setters for the consumer and producer flags
	 */
	public static void setConsFlag(boolean b)
	{
		consFlag = b;
	}
	
	public static boolean getConsFlag()
	{
		return consFlag;
	}
	
	public static void setProdFlag(boolean b)
	{
		prodFlag = b;
	}
	
	public static boolean getProdFlag()
	{
		return prodFlag;
	}
}