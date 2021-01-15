public class Main
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/
		
	public static void main(String[] args)
	{
		//Write code to test your implementation here.
            
            Schedule planner = new Schedule() ;
            
            planner.clearAll();
            
            planner.addEvent("07:00", "Tue", "Walking");
            planner.addEvent("06:00", "Tue", "Swimming", 60); 
            
            planner.clearAll();
            
            planner.addEvent("21:00", "Mon", "Back"); //empty day/time
            planner.addEvent("10:30", "Mon", "Legs"); //test same time and moving to next day
            planner.addEvent("11:30", "Wed", "Glutes");
            planner.addEvent("11:30", "Tue", "Walking");
            planner.addEvent("11:30", "Tue", "Walking");
            
            planner.clearAll();
            
            planner.addEvent("06:00", "Wed", "Walking");
            planner.addEvent("21:00", "tue", "Quads", 120);
            planner.addEvent("08:00", "wed", "Quads");
            
            planner.addEvent("06:00", "Sun", "Marathon", 240);
            planner.addEvent("12:00", "Wed", "Pilates");
            
            planner.LiamPrintDays();
            planner.PrintLatestToEarliest();
            planner.LiamPrintTimes();
            
            /*
            System.out.println(planner.deleteEvent("07:00", "Wed")); //delete multiple middle
            System.out.println(planner.deleteEvent("12:00", "Wed")); //delete last
            System.out.println(planner.deleteEvent("22:00", "Wed")); //not found
            System.out.println(planner.deleteEvent("11:30", "Wed")); //delete single middle
            System.out.println(planner.deleteEvent("06:00", "Wed")); //delete root single
            System.out.println(planner.deleteEvent("11:30", "Tue")); //mutliple end
            System.out.println(planner.deleteEvent("06:00", "Tue")); //delete multiple root
            
            planner.LiamPrintDays();
            planner.PrintLatestToEarliest();
            planner.LiamPrintTimes(); */
            
/*
            //planner.LiamPrintTimes();
            //planner.LiamPrintDays();
            //System.out.println("");
            //planner.PrintLatestToEarliest();
           
           // planner.deleteEvent("Walking");
           // planner.deleteEvent("Quads");
            //planner.LiamPrintTimes();
            //planner.LiamPrintDays();
            //planner.PrintLatestToEarliest();
            //System.out.println("");
            
            //planner.clearByDay("Mon");
            planner.addEvent("12:00", "Mon", "Laterals", 30);
            //planner.LiamPrintDays();
           // planner.clearByDay("Mon");
            planner.addEvent("11:00", "Mon", "Laterals");
           // planner.clearByDay("Tue");
           // planner.clearByDay("Wed");            
           // planner.LiamPrintDays();
           // planner.clearAll();
           // planner.LiamPrintDays();
           // planner.PrintLatestToEarliest();
           // planner.LiamPrintTimes();
           // System.out.println("Before Lats");
            */
            planner.addEvent("11:00", "Mon", "Laterals");
            planner.addEvent("11:00", "Tue", "Laterals");
            planner.addEvent("11:00", "Wed", "Laterals");
            planner.addEvent("10:30", "Thu", "Rowing");
            planner.addEvent("11:00", "Thu", "Laterals",300);
            planner.addEvent("16:00", "Thu", "Treadmill");
            planner.addEvent("11:00", "Fri", "Laterals");
            planner.addEvent("11:00", "Sat", "Laterals");
            planner.addEvent("11:00", "Sun", "Laterals");
            
            planner.LiamPrintDays();
            planner.PrintLatestToEarliest();
            planner.LiamPrintTimes();
            /*
            //planner.deleteEvent("Laterals");
          //  planner.deleteEvent("21:00", "Mon");
          //  planner.deleteEvent("10:30", "Thu"); 
            
            planner.clearByDay("Wed");  //clear glutes, walking, pilates
            planner.clearByDay("Thu");
           // planner.clearByTime("09:00"); //clear marathon
           // planner.clearByTime("14:30");
           // planner.clearByTime("13:30");
           // planner.clearByTime("12:30");
          //  planner.clearByTime("10:00"); //none
          //  planner.clearByTime("12:00"); 
          //  planner.clearByTime("13:00");
            
            planner.LiamPrintDays();
            planner.PrintLatestToEarliest();
            planner.LiamPrintTimes();
            
           // planner.clearAll();
           // planner.addEvent("06:00", "Thu", "Neck",990);
           
          
            
            planner.LiamPrintDays();
            planner.PrintLatestToEarliest();
            planner.LiamPrintTimes();
            
            
            //System.out.println("After lats");
            /*planner.LiamPrintDays();
            planner.PrintLatestToEarliest();
            planner.LiamPrintTimes();
            //System.out.println("Deletion");
            //planner.deleteEvent("Laterals");
            //planner.deleteEvent("11:00", "Thu");
            planner.LiamPrintDays();
            planner.PrintLatestToEarliest();
            planner.LiamPrintTimes(); */
            
            /*planner.clearAll();
            planner.LiamPrintDays();
            planner.PrintLatestToEarliest();
            planner.LiamPrintTimes(); */
            
           
            
            
            //planner.clearByDay("Tue");
            
            /*planner.LiamPrintDays();
            System.out.println("");
            planner.PrintLatestToEarliest();
            
            /*
            planner.LiamPrintDays();
            System.out.println("");
            planner.PrintLatestToEarliest();
            
            //planner.LiamPrintTimes(); 
            
            planner.addEvent("07:00", "Mon", "Running"); //insert before another even
            planner.addEvent("09:00", "Mon", "Rest"); //general in between
            planner.addEvent("21:30", "Mon", "Running");
            planner.addEvent("22:00", "Mon", "Running"); //general in between two
            planner.addEvent("10:00", "Mon", "Shoulders"); //shift time down
            
            planner.addEvent("21:30", "Mon", "Running");
            planner.addEvent("18:30", "Thu", "Running");
            planner.addEvent("14:30", "Sun", "Running");
            
            
            //planner.LiamPrintTimes();
          //  planner.LiamPrintDays();
          //  System.out.println("");
          // planner.PrintLatestToEarliest();
            
            planner.addEvent("07:00", "Mon", "Triceps", 90); //test multiple periods 
            planner.addEvent("10:30", "Mon", "Calves", 90); 
            planner.addEvent("08:30", "Wed", "Cycling", 180);
            planner.addEvent("16:00", "Wed", "Chest", 240); //already taken
            planner.addEvent("20:00", "Wed", "Biceps", 90);
             
            planner.addEvent("06:00", "Fri", "Sleep", 990);
            //planner.LiamPrintTimes();
           // planner.LiamPrintDays();
           
          //  planner.LiamPrintDays();
          //  System.out.println("");
          //  planner.PrintLatestToEarliest();           
           
            planner.clearByDay("fri");
            planner.addEvent("09:30", "Fri", "Think", 480);
            System.out.println("Cleared sleep");
            
         //   planner.LiamPrintDays();
         //  System.out.println("");
         //   planner.PrintLatestToEarliest();
            
            /*
            planner.LiamPrintTimes();
            planner.LiamPrintDays();
            
            planner.deleteEvent("Running");
            
            planner.LiamPrintDays();
            planner.deleteEvent("14:00", "Wed");            
            planner.deleteEvent("14:00", "Fri");
	
            planner.LiamPrintDays();
            
            Event hold = planner.findEvent("Triceps");
            System.out.println("retrieve event: "+ hold.getDescription());
            hold = planner.getTimeEvent("20:00");
            System.out.println("retrieve event: "+ hold.getDescription());
            
            planner.clearByDay("Fri");
            planner.clearByTime("07:00"); 
            
            System.out.println("Deleting back");
            
            planner.deleteEvent("Back");
            
            planner.LiamPrintDays();
            
            planner.clearByDay("Mon");
            
            planner.LiamPrintDays();
            
            planner.clearAll();
            
            planner.LiamPrintDays(); */
        }
	
}
