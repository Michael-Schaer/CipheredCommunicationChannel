import java.util.ArrayList;

/**
 * Provides an ArrayList to Store Object of class T and the methods put and get to handle the data.
 */
public class ListBuffer<T> implements Buffer<T> 
{
	private ArrayList<T> buffer;

	public ListBuffer() 
	{
		// Create an array of the given size
		buffer = new ArrayList<T>();
	}

	/**
	 * Adds an item to the ArrayList, notifies the Consumer
	 */
	public synchronized void put(T item) throws InterruptedException 
	{
		buffer.add(item);

		// If the Consumer might be waiting, wake it up
		Launcher.setConsFlag(true);
		notify(); // notifyAll() if more than one Prod./Cons.
	}

	/**
	 * Checks if an Item is Stored in the ArrayList and returns it, if available
	 */
	public synchronized T get() throws InterruptedException 
	{
		T item;
		if (buffer.size() == 0) // Buffer empty
		{
			item = null;
			System.out.println("No Items to get"); // Can be checked with hasItem(), so should not happen
		}
		else
		{
			item = (T)buffer.get(0); // "Consume" item
			buffer.remove(0);
		}
		return item;
	}

	/**
	 * true if at least one Item is stored in the ArrayList, else false
	 * Use this to Check for Items, before you get them
	 */
	public boolean hasItem()
	{
		return !(buffer.size() == 0);
	}
}