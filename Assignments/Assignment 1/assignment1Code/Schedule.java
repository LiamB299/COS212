/*Complete this class to implement a fully functional sparse table.  Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/

public class Schedule
{
	public Schedule()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  
		This is the constructor which will be used for marking*/ 
	}
	
	/*Insertion*/
	public void addEvent(String time, String day, String description)
	{
		/*Insert an event at the given time and day combination. 
		Assume the event is 30 minutes long and insert a single Event node.
		Description should be used to initialise the node.
		
		Duplicate events (events with the same description) are allowed.*/
	}
	
	/*Insertion*/
	public void addEvent(String time, String day, String description, int duration)
	{
		/*Insert an event at the given time and day combination. 
		Description should be used to initialise the necessary nodes.
		Duration should be used to determine how many nodes with the same description have to be inserted.
		Assume only increments of 30 will be used, eg. 30, 60, 90, 120, etc.
		
		Duplicate events (events with the same description) are allowed.*/
	}
	
	/*Deletion methods*/
	public String deleteEvent(String time, String day)
	{
		/*Delete the event at the given time and day combination and return the description of the 
		deleted event. Note: all adjacent (up and down) events with the same description must also be deleted.
		
		If no such event exists, return null.*/
				
		return null;
	}
	
	public void deleteEvent(String description)
	{
		/*Delete all events that match the given description.*/
				
	}
	
	/*Clearing Methods*/
	public void clearByDay(String day)
	{
		/*All events for the given day should be deleted.
		If the day has no events, simply do nothing.*/
	}
	
	public void clearByTime(String time)
	{
		/*All evnts for the given time should be deleted.
		If the time has no events, simply do nothing.*/
	}
	
	public void clearAll()
	{
		/*Delete all events from the schedule.*/
	}
	
	
	/*Query methods*/
	public Event getEvent(String time, String day)
	{
		/*Return the first event for the given time and day combination.  
		If no such event exists, return null*/
		
		return null;
	}

	public Event findEvent(String description)
	{
		/*Return the first event that matches the given description.  
		If no such event exists, return null*/
		
		return null;
	}
	
	public Event getTimeEvent(String time)
	{
		/*Return the head event for the time passed as a parameter.
		If no such event exists, return null*/
		return null;
	}
	
	public Event getDayEvent(int day)
	{
		/*Return the head event for the day passed as a parameter.
		If no such event exists, return null*/
		return null;
	}
		
	
}
