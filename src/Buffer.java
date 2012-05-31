/**
 * The Buffer interface demands two methods put and get. Those methods are used to store information and get it back out to another time.
 */
public interface Buffer<T> 
{
	public void put(T item) throws InterruptedException;
	public T get() throws InterruptedException;
}
