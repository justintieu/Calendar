/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */

import java.util.Comparator;

public class EventModel implements Comparable
{

    int day;
    int year;
    int month;
    int hour;
    String endTime;
    String descript;

    /**
     * Creates an event in the calendar's agenda.
     * 
     * @param descript description of the Event
     * @param day day of the event
     * @param month month of the event
     * @param year year of the event
     * @param hour starting hour of the event
     * @param endTime ending hour of the event
     */
    public EventModel(String descript, int day, int month, int year, int hour, String endTime)
    {
        this.day = day;
        this.month = month;
        this.descript = descript;
        this.year = year;
        this.hour = hour;
        this.endTime = endTime;
    }

    /**
     * Compares the event's date and time.
     * 
     * @param b - event to compare to
     * @return -1 Event b is scheduled later
     * @return 1 Event b is scheduler before
     * @return 0 Scheduled at the same time
     */
    public int compareTo(Object b)
    {
        if (getClass() == b.getClass())
        {
            EventModel a = (EventModel) b;
            if (year > a.year)
            {
                return 1;
            }
            if (year < a.year)
            {
                return -1;
            }
            if (month > a.month)
            {
                return 1;
            }
            if (month < a.month)
            {
                return -1;
            }
            if (day > a.day)
            {
                return 1;
            }
            if (day < a.day)
            {
                return -1;
            }
            if (hour > a.hour)
            {
                return 1;
            }
            if (hour < a.hour)
            {
                return -1;
            }
            return 0;
        }
        return 0;
    }

    /**
     * Creates comparator object.
     * 
     * @return comparator object
     */
    public static Comparator<EventModel> createComparatorByName()
    {
        return new Comparator<EventModel>()
        {
            public int compare(EventModel one, EventModel two)
            {
                return one.compareTo(two);
            }
        };
    }
;
}
