/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarModel
{

    private GregorianCalendar gc;
    private String[] month;
    private int day;

    /**
     * Creates a new Calendar.
     */
    public CalendarModel()
    {
        gc = new GregorianCalendar();
        month = new String[]
        {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        DateFormat dateFormat = new SimpleDateFormat("dd");
        day = Integer.parseInt(dateFormat.format(gc.getTime()));
    }

    /**
     * Gets the Gregorian Calendar.
     *
     * @return Gregorian calendar
     */
    public GregorianCalendar getGC()
    {
        return gc;
    }

    /**
     * Gets the current month of the Gregorian calendar.
     *
     * @return current month
     */
    public int getMonth()
    {
        return gc.get(2);
    }

    /**
     * Returns String version of month
     *
     * @return current month
     */
    public String getMonthName()
    {
        return month[gc.get(2)];
    }

    /**
     * Update current month.
     *
     * @param changeMonth - value to increase or decrease month
     */
    public void updateMonth(int changeMonth)
    {
        gc.set(2, gc.get(2) + changeMonth);
    }

    /**
     * Get current day.
     * 
     * @return day number
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Set the day of the calendar.
     * 
     * @param day - day number
     */
    public void setDay(int day)
    {
        this.day = day;
    }

    /**
     * Get the year of the Gregorian calendar.
     * 
     * @return year
     */
    public int getYear()
    {
        return gc.get(1);
    }

    /**
     * Get day of the week that a month starts on.
     * 
     * @return day of the week
     */
    public int getStartDay()
    {
        java.util.Calendar currentMonth = new GregorianCalendar(gc.get(1), gc.get(2), 1);
        int startDay = currentMonth.get(java.util.Calendar.DAY_OF_WEEK);
        return startDay;
    }

    /**
     * Get amount of days a month has.
     * 
     * @return amount of days
     */
    public int getNumDays()
    {
        java.util.Calendar currentMonth = new GregorianCalendar(gc.get(1), gc.get(2), 1);
        int numDays = currentMonth.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        return numDays;
    }
}