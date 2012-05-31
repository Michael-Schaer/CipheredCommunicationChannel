/**
 * A LogEvent provides the Strings classToken, event and mask to store Log information
 * int milliSeconds is used to save, when the log was created
 */
public class LogEvent 
{
	private String classToken,event,mask;
	private int milliSeconds;

	public LogEvent(String c, String e, String m, int ms)
	{
		classToken = c;
		event = e;
		mask = m;
		milliSeconds = ms;
	}

	public String getClassToken() {
		return classToken;
	}

	public String getEvent() {
		return event;
	}

	public String getMask() {
		return mask;
	}	
	
	public long getMilliSeconds() {
		return milliSeconds;
	}	
}