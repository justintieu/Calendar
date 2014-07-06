/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Layout3 implements EventViewLayout
{

    private static ArrayList<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

    /**
     * Creates a different layout for the agenda.
     */
    public Layout3()
    {
    }

    /**
     * Formats the agenda view using layout 3 as a template.
     * 
     * @param e - list of events
     * @return a formatted string of events
     */
    public String eventsToString(ArrayList<EventModel> e)
    {
        String retrn = "";
        boolean noEvent = true;
        for (EventModel temp : e)
        {
            noEvent = false;
            retrn += temp.month + "/" + temp.day + "/" + temp.year + " - " + temp.hour + "-" + temp.endTime + " " + temp.descript + "\n";
        }

        return (noEvent) ? "No Events Scheduled" : retrn;
    }
}