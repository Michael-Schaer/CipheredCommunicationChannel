import java.util.ArrayList;

/**
 * Provides an ArrayList with LogEvents that can be stored and read
 * int startTime is the time, when DiagramData was constructed
 */
public class DiagramData
{
	private ArrayList<LogEvent> events;
	private long startTime;
	
	public DiagramData()
	{
		events = new ArrayList<LogEvent>();
		System.out.println("Recording data, please wait...");
		startTime = System.currentTimeMillis();
	}
	
	public void addEvent(String a, String b, String c)
	{
		events.add(new LogEvent(a, b, c, (int)(System.currentTimeMillis()-startTime)));
	}
	
	public ArrayList<LogEvent> getEvents()
	{
		return events;
	}
	
}
