/*Complete this class to implement a fully functional sparse table.  Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/

public class Schedule
{
    private Event [] days;
    private Event [] times;
    
	public Schedule()
	{
            days = new Event[7];
            for (int i=0; i<7; i++) {
                days[i]=null;
            }
            
            times = new Event[33];
            for(int i=0; i<33; i++) {
                times[i] = null;
            }         
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  
		This is the constructor which will be used for marking*/ 
	}
	
	/*Insertion*/
	public void addEvent(String addtime, String addDay, String description)
	{
           // System.out.println("Insert: "+ description);
            
            int tm = getTime(addtime);
            int d = getDayof(addDay);
            Event timeprev = null, timehold = times[tm];
            Event dayhold, dayprev=null;
            int icount=0;
            
//==============================================================================            
            
            Event newEvent = new Event(description, tm , d);

                boolean bset = false;
                while(bset==false) {
                    if(times[tm]==null) {
                    //    System.out.println("Empty row of times appending");
                    newEvent.setTime(tm);
                    times[tm] = newEvent;
                    bset=true;
                    continue; 
                    }
                        while(timehold.right!=null && newEvent.getDay()>timehold.getDay()) { //move to interval of where to insert or move down a time if time is taken
                            /*if(timehold.getDay()==newEvent.getDay()) { //time taken
                                if(tm<32) {
                       //             System.out.println("Time taken t="+tm);
                                    timehold = times[++tm];
                                    timeprev = null;
                                    continue;
                                }
//==============================================================================                                
                                else {
                        //            System.out.println("moving day forward from d="+d);
                                    tm=0;
                                    if(d==6) {
                                        d=0;
                                        newEvent.setDay(d);
                                        timehold = times[tm];
                                        timeprev = null;
                                    }
                                    else {
                                        newEvent.setDay(++d);
                                        timeprev=null;
                                        timehold = times[tm];
                                    }  
                                }
                                //timehold = times[tm];
                                //timeprev = null;
                            } 
                            else {*/
                                timeprev = timehold;
                                timehold = timehold.right;
                            }
                //}
                        
//==============================================================================    

                        if(timehold.getDay()==newEvent.getDay()) { //time taken
                                if(tm<32) {
                       //             System.out.println("Time taken t="+tm);
                                    timehold = times[++tm];
                                    timeprev = null;
                                    continue;
                                }
//==============================================================================                                
                                else {
                        //            System.out.println("moving day forward from d="+d);
                                    tm=0;
                                    if(d==6) {
                                        d=0;
                                        newEvent.setDay(d);
                                        timehold = times[tm];
                                        timeprev = null;
                                    }
                                    else {
                                        newEvent.setDay(++d);
                                        timeprev=null;
                                        timehold = times[tm];
                                    }
                                    continue;
                                }
                                //timehold = times[tm];
                                //timeprev = null;
                            }

                       // System.out.println(timehold.getDay());
                        if(timehold.getDay()>newEvent.getDay()) { //
                            if (timeprev==null) {
                      //          System.out.println("Appending to root of times");
                                newEvent.right =  times[tm];
                                times[tm] = newEvent;
                                newEvent.setTime(tm);
                                bset = true;
                            }
                            else {
                       //         System.out.println("appending general of times");
                                newEvent.right = timehold;
                                timeprev.right = newEvent;
                                newEvent.setTime(tm);
                                bset = true;
                            }
                        }
                        else {
                            newEvent.right = timehold.right;
                            timehold.right = newEvent;
                            newEvent.setTime(tm);
                            bset=true;
                        }
                        /*else if(tm<32) {
                                    timehold = times[tm++];
                                    timeprev = null;
                                }
                                else {
                       //             System.out.println("moving day forward from d="+d);
                                    tm=0;
                                    if(d==6) {
                                        d=0;
                                        newEvent.setDay(d);
                                    }
                                    else {
                                        newEvent.setDay(d++);
                                    }
                                    timeprev=null;
                                    timehold = times[tm];
                                }*/
                }
//===============================================================================
                dayhold = days[d];
                bset = false;
                if(dayhold==null) { //empty
               //     System.out.println("empty day appending");
                    days[d]=newEvent;
                    newEvent.down = newEvent;
                    newEvent.up= newEvent;
                    bset=true;
                }
                
                while(bset==false) { 
                    if(dayhold.getTime()>newEvent.getTime()) {  //new root
                        if(dayprev==null) {
                 //           System.out.println("appending root of day");
                            days[d].up.down = newEvent;
                            newEvent.down = days[d];
                            newEvent.up = days[d].up;
                            days[d].up = newEvent;
                            days[d] = newEvent;
                            return;
                        }
                        else {
                  //          System.out.println("appending general of days");
                            newEvent.down  =dayprev.down;  //general
                            dayprev.down = newEvent;
                            newEvent.up = dayprev;
                            newEvent.down.up = newEvent;
                            return;
                        }
                    }
                    if(dayhold.getTime()<newEvent.getTime()) { 
                        if(dayhold.down==days[d]) { //lowest node
                   //         System.out.println("appending to end of days");
                            newEvent.down  =dayhold.down;
                            dayhold.down = newEvent;
                            newEvent.up = dayhold;
                            newEvent.down.up = newEvent;
                            return;
                        }
                        dayprev = dayhold;      //iterate
                        dayhold = dayhold.down;
                    }
                }
                
		/*Insert an event at the given time and day combination. 
		Assume the event is 30 minutes long and insert a single Event node.
		Description should be used to initialise the node.
		
		Duplicate events (events with the same description) are allowed.*/
	}
//=====================================================================================	
	/*Insertion*/
	public void addEvent(String time, String day, String description, int duration)
	{
            Event curr = null;
            int many = duration/30, d = getDayof(day), t = getTime(time);
            boolean bset = false;
            
          //  System.out.println("appending "+ description+" as multiEvent");
            curr = days[d];
            while(true) {  
            if(many+t>33) {
                if(d==6) { 
                        d=0;
                        t=0;
                    }
                    else {
                        d++;
                        t=0;
                    }
                    curr = days[d];
                    continue;
            }

            if(days[d]==null) {
          //      System.out.println("Empty days appending");
                Event hold=days[d];
                for(int i=t; i<=t+many-1; i++) {//for(int i=t+many-1; i>=t; i--) {
                    Event newNode = new Event(description, i, d);
                    if(days[d]==null) {
                        days[d]=newNode;
                        newNode.down = newNode;
                        newNode.up = newNode;
                        aH(newNode, i, d);
                        hold = days[d];
                    }
                    else {
                        newNode.down = hold.down;
                        hold.down.up = newNode;
                        newNode.up = hold;
                        hold.down = newNode;
                        aH(newNode, i, d); 
                        hold = hold.down;
                        /*
                        newNode.down = days[d];
                        newNode.up = days[d].up;
                        newNode.up.down = newNode;
                        days[d].up = newNode;
                        days[d] = newNode;
                        aH(newNode, i, d);    */
                    }
                }
                return;
            }
            
            if(days[d].getTime()>t && days[d].getTime()-t>=many) { //
            //    System.out.println("appending to root of days with good size");
                for(int i=many-1; i>=t; i--) {
                    Event newNode = new Event(description, i, d);
                    days[d].up.down = newNode; //tail
                    newNode.down= days[d];
                    newNode.up = days[d].up;
                    days[d].up = newNode;
                    days[d] = newNode;
                    aH(newNode, i, d);
                }
                return;
            }
            
//==============================================================================
          
            while(curr.getTime()<t && curr!=days[d].up) { //run to last node or equals/less than the time
           //     System.out.println("CurrTime: "+curr.getTime()+ " index:"+t);
                curr = curr.down;
            }
            
          //  System.out.println("CurrTime: "+curr.getTime()+ " index:"+t); //;+ (curr.getTime()+many));
            if(curr.getTime()==t) {
           //     System.out.println("Time taken t="+t);
                t++;
                continue;
            }            
            
            if(t>curr.getTime() && curr==days[d].up) { //last node but is still later than that
            //    System.out.println("after last node "+curr.getTime()+" how many "+ (many));
                if((curr.getTime()+many)>32) {
            //        System.out.println("Move day forward d= "+d);
                    if(d==6) { 
                        d=0;
                        t=0;
                    }
                    else {
                        d++;
                        t=0;
                    }
                    curr = days[d];
                    continue;
                }
                else { //it fits
              //      System.out.println("last node but there is space remaining t="+t);
                    for(int i=t; i<=t+many-1; i++) {
                        Event node = new Event(description, i, d);
                        node.up = curr;
                        curr.down = node;
                        aH(node, i, d);
                        node.down = days[d];
                        days[d].up = node;
                        curr = curr.down;
                    }
                    return;
                }
            }
            
            if(curr.getTime()>t) {
                int sum = curr.getTime()-t;
                if(sum<many) {  //gap is too small
                //    System.out.println("gap too small advancing t="+t);
                    t++;
               //     System.out.println("New T: "+t);
                    continue;
                }
                else { //snuggly
                  //  System.out.println("general best fits for days");
                  for(int i=t+many-1; i>=t; i--) {
                        Event node = new Event(description, i, d);
                        node.up = curr.up;
                        curr.up.down = node;
                        aH(node, i, d);
                        node.down = curr;
                        curr.up = node;
                        curr = node;
                    }
                    return;
            }
            }

            
            
		/*Insert an event at the given time and day combination. 
		Description should be used to initialise the necessary nodes.
		Duration should be used to determine how many nodes with the same description have to be inserted.
		Assume only increments of 30 will be used, eg. 30, 60, 90, 120, etc.
		
		Duplicate events (events with the same description) are allowed.*/
        }
	}
//==============================================================================	
	/*Deletion methods*/
	public String deleteEvent(String time, String day)
	{
            int first, tm = getTime(time), d= getDayof(day);
            Event hold, prev=null, down,up;
            String comp=null;
            
            hold = days[d];
            if(hold==null) {
                return null;
            }
            

            if(hold.getTime()!=tm) {
                hold = hold.down;
                while(hold.getTime()!=tm && hold!=days[d]) {
                  hold = hold.down;
                }
                if(hold.getTime()!=tm) {
                    return null;
                }
            }
            
            while(hold.getDescription()==hold.up.getDescription() && hold!=days[d]) {
                hold= hold.up;
            }
//==============================================================================           
            comp = hold.getDescription();
            
            if(hold==days[d]) {
                while(days[d].getDescription()==comp && days[d].down!=days[d]) {
                    dH(days[d].getTime(), d);
                    days[d].up.down = days[d].down;
                    days[d].down.up = days[d].up;
                    days[d] = days[d].down;
                }
                if(days[d].getDescription()==comp && days[d].down==days[d]) {
                    dH(days[d].getTime(),d);
                    days[d]=null;
                }
            }
            else {
                while(hold.getDescription()==comp && hold!=days[d]) {
                    dH(hold.getTime(),d);
                    hold.up.down = hold.down;
                    hold.down.up = hold.up;
                    hold = hold.down;
                }
            }
            
            return comp;


		/*Delete the event at the given time and day combination and return the description of the 
		deleted event. Note: all adjacent (up and down) events with the same description must also be deleted.
		
		If no such event exists, return null.*/
				
	}
//==============================================================================	
	public void deleteEvent(String description)
	{
            Event hold=null, prev=null;
            for(int i=0; i<7; i++) {

                if(days[i]==null)
                    continue;
                
                hold = days[i];
                
                if(days[i].getDescription()==description && days[i].up == days[i]) {
                    days[i]=null;
                    continue;
                }
                
                while(days[i].getDescription()==description && days[i].down!=days[i]){ //check first element
                     days[i].up.down = days[i].down;
                     days[i].down.up = days[i].up;
                     days[i] = days[i].down;
                    }
                    if(days[i].getDescription()==description && days[i].down == days[i]) {
                        days[i]=null;
                        continue;
                    }
                    hold = days[i];
                prev=hold;
                
                Event end= days[i];
                hold = days[i].down;
                while(hold!=end) { //general
                    if(hold.getDescription()==description) {
                        prev.down = hold.down;
                        hold.down.up = prev;
                        hold = prev;
                    }
                    prev = hold;
                    hold = hold.down;
                }
                if(days[i].up.getDescription()==description){
                    days[i].up.up.down = days[i];
                    days[i].up = days[i].up.up;
                }
            }
            
//==============================================================================            
            hold = times[0];
            prev=null;
            for(int i=0; i<33; i++) {
                prev=null;
                if(times[i]==null) {
                    continue;
                }
                
                hold = times[i];
                while(hold!=null) {
                    if(hold.getDescription()==description) {
                        if(prev==null) {
                            times[i] = times[i].right;
                            hold = times[i];
                            continue;
                        }
                        else {
                            prev.right = hold.right;
                        }
                    }
                    prev = hold;
                    hold = hold.right;
                }
                
            }
            
		/*Delete all events that match the given description.*/
//==============================================================================				
	}
	
	/*Clearing Methods*/
	public void clearByDay(String day)
	{
            int d = getDayof(day);
            
            if(days[d]==null) {
                return;
            }
            
            Event hold = days[d].down;
            while(hold!=days[d]) {
                dH(hold.getTime(), d);
                hold = hold.down;
            }
            dH(days[d].getTime(), d);
            days[d] =null;
		/*All events for the given day should be deleted.
		If the day has no events, simply do nothing.*/
	}
	
	public void clearByTime(String time)
	{
            int tm = getTime(time);
            Event hold = times[tm];
            
            if(hold==null) {
                return;
            }
            
            while(times[tm]!=null) {
                if(times[tm].up == times[tm]) {
                    days[times[tm].getDay()]=null;
                }
                else {
                    times[tm].up.down = times[tm].down;
                    times[tm].down.up= times[tm].up;
                }
                times[tm]=times[tm].right;
            }
            
            
		/*All events for the given time should be deleted.
		If the time has no events, simply do nothing.*/
	}
	
	public void clearAll() //due to java garbage collecting pointers/references for the programmer, simple unlinking will be fine.
	{
            for(int i=0; i<7;i++) {
                days[i]=null;
            }
            
            for(int j=0; j<33; j++) {
                times[j]=null;
            }            
            
		/*Delete all events from the schedule.*/
	}
	
	
	/*Query methods*/
	public Event getEvent(String time, String day)
	{
            int d = getDayof(day);
            int tm = getTime(time);
            
            Event hold = days[d];
            if(hold.getTime()==tm) {
                return hold;
            }
            else hold = hold.down;
            while(hold!=days[d]) {
                if(hold.getTime()==tm) {
                    return hold;
                }
                else {
                    hold= hold.down;
                }
            }
		/*Return the first event for the given time and day combination.  
		If no such event exists, return null*/
		
		return null;
	}

	public Event findEvent(String description)
	{
            int d=0, t=0;
            Event hold = days[0];
            while(d<6) {
                if(hold==null) {
                    d++;
                    hold=days[d];
                    continue;
                }
                if(hold.getDescription()==description)
                    return hold;
                else hold=hold.down;
                while(hold!=days[d]) {
                 //   System.out.println(hold.getDescription());
                    if(hold.getDescription()==description) {
                        return hold;
                    }
                    else
                        hold = hold.down;
                }
                d++;
                hold = days[d];
            }    
		/*Return the first event that matches the given description.  
		If no such event exists, return null*/
		
		return null;
	}
	
	public Event getTimeEvent(String findtime)
	{
            int gotime = getTime(findtime);
            return times[gotime];
		/*Return the head event for the time passed as a parameter.
		If no such event exists, return null*/
	}
	
	public Event getDayEvent(String day)
	{
            return days[getDayof(day)];
		/*Return the head event for the day passed as a parameter.
		If no such event exists, return null*/
	}
        
        
        //additional methods
        private int getDayof(String day){ //returns an integer to the day given's index
            switch(day.toLowerCase()) {
                case "mon":
                    return 0;
                case "tue":
                    return 1;
                case "wed":
                    return 2;
                case "thu":
                    return 3;
                case "fri":
                    return 4;
                case "sat":
                    return 5;
                case "sun":
                    return 6; 
            }
            return -1; //error  
        }
        
        private int getTime(String time){ //return index to time slot
            String hh = time.substring(0, 2);
            int comp = Integer.parseInt(hh);
            if(comp<6 || comp>22) 
                return 0;
            
            if(comp==22) {
                return ((22-6)*2);
            }
            
            comp = comp-6;
            comp*=2;
            String mm = time.substring(3,5);
            if(null == mm)
                return 0;
            else switch (mm) {
                case "30":
                    return comp+1;
                case "00":
                    return comp;
                default:
                    return 0;
            }
        }
        
        private void dH(int time, int day) {
            Event curr = times[time], prev=null;
            while(true) {
                if(curr.getDay()==day) {
                    if(prev==null) {
                        times[time]=times[time].right;
                    }
                    else {
                        prev.right = curr.right;
                    }
                    return;
                }
                prev = curr;
                curr = curr.right;
            }
        }
        
        private void aH(Event hold ,int time, int day) { //passing newnode by reference 
            Event curr=times[time], prev=null;
            
            if(times[time]==null) { //empty
                times[time] = hold;
                return;
            }
            while(curr.getDay()<day && curr.right!=null) { //move to poisition
                prev = curr;
                curr = curr.right;
            }
            if(curr.getDay()<day) {
                curr.right=hold;
                return;
            }
            else {
                if(prev==null) {
                    times[time] = hold;
                    hold.right = curr;
                    return;
                }
                
                prev.right = hold;
                hold.right = curr;
                return;
            }    
        }
        
        public void LiamPrintDays() {
            System.out.println("Printing days from earliest: ");
            Event hold;
            int t=0;
            for(int i=0; i<7; i++) {
                hold= days[i];
                t=0;
                if(hold==null) {
                    System.out.println("Day: "+i+ " is empty");
                }
                else {
                    System.out.println("Day: "+i);
                    while(hold!=days[i].up && t<33) {
                        t++;
                        System.out.println(hold.getDescription()+" @ "+hold.getTime());
                        hold=hold.down;
                    }
                    System.out.println(hold.getDescription()+" @ "+hold.getTime());
                }
                
            }
                        System.out.println("");
        }
        
        public void LiamPrintTimes() {
            System.out.println("Printing times:");
            Event hold;
            for(int i=0; i<33; i++) {
                hold = times[i];
                if(hold==null) {
                    System.out.println("Empty time: "+i);
                }
                else {
                    System.out.println("Time: "+i);
                    while(hold!=null) {
                        System.out.println(hold.getDescription()+" on day: "+ (hold.getDay()+1));
                        hold = hold.right;
                    }
                }
            }
            System.out.println("");
        }
        
        public void PrintLatestToEarliest() {
            System.out.println("Printing days from latest: ");
            Event hold;
            int t=0;
            for(int i=0; i<7; i++) {
                hold= days[i];
                t=0;
                if(hold==null) {
                    System.out.println("Day: "+i+ " is empty");
                }
                else {
                    hold= days[i].up;
                    System.out.println("Day: "+i);
                    while(hold!=days[i] && t<33) {
                        t++;
                        System.out.println(hold.getDescription()+" @ "+hold.getTime());
                        hold=hold.up;
                    }
                    System.out.println(hold.getDescription()+" @ "+hold.getTime());
                }
                
            }
                        System.out.println("");
        }
}
