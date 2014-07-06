/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Layout1 implements EventViewLayout
{

    private static ArrayList<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

    /**
     * Creates the default layout of the agenda view.
     */
    public Layout1()
    {
    }

    /**
     * Formats the agenda view using layout 1 as a template.
     * 
     * @param e - list of events
     * @return a formatted string of events
     */
    public String eventsToString(ArrayList<EventModel> e)
    {
        String retrn = "";
        boolean yearChange = false;
        boolean monthChange = false;
        int tempYear = 0;
        int tempmonth = 0;
        boolean noEvent = true;
        int tempday = 0;
        for (EventModel temp : e)
        {
            noEvent = false;
            if (temp.year != tempYear)
            {
                tempYear = temp.year;
                retrn += temp.year + "\n";
                yearChange = true;
            }
            if (temp.month != tempmonth || yearChange)
            {
                tempmonth = temp.month;
                retrn += "   " + months.get(temp.month - 1) + "\n";
                monthChange = true;
            }
            if (temp.day != tempday || monthChange)
            {
                tempday = temp.day;
                retrn += "      " + temp.day + "\n";
            }
            yearChange = false;
            monthChange = false;
            retrn += "         " + temp.hour + ((temp.endTime.isEmpty()) ? "" : " - " + temp.endTime) + " " + temp.descript + "\n";
        }

        return (noEvent) ? "No Events Scheduled" : retrn;
    }
}
