
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */
public class ScheduleModel {

    private ArrayList<EventModel> events = new ArrayList<EventModel>();
    private SortedSet<EventModel> sched;
    private EventViewLayout layout;
    private static ArrayList<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
    Calendar c = Calendar.getInstance();
    ArrayList<ChangeListener> listeners;

    /**
     * Constructs the Scheduler Model
     *
     * @param a initial Layout
     */
    public ScheduleModel(EventViewLayout a) {
        sched = new TreeSet<EventModel>(EventModel.createComparatorByName());
        listeners = new ArrayList<ChangeListener>();
        layout = a;
    }

    /**
     * Add new event into the scheduler
     * 
     * @param a new event
     * return true new event is added into the scheduler with no time conflict
     */
    public boolean add(EventModel a) {
        Iterator iter = sched.iterator();
        if (!a.endTime.isEmpty() && a.hour == Integer.parseInt(a.endTime)) {
            return false;
        }
        while (iter.hasNext()) {
            EventModel b = (EventModel) iter.next();
            if (!b.endTime.isEmpty() && ((a.year == b.year && a.month == b.month && a.day == b.day) && ((a.hour > b.hour && a.hour < Integer.parseInt(b.endTime))))) {
                return false;
            } else if (!a.endTime.isEmpty() && ((a.year == b.year && a.month == b.month && a.day == b.day) && ((b.hour > a.hour && b.hour < Integer.parseInt(a.endTime))))) {
                return false;
            } else if (a.year == b.year && a.month == b.month && a.day == b.day && b.hour == a.hour) {
                return false;
            }
        }
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
        return sched.add(a);
    }

    /**
     * Creates a string with the set layout of all the events in an specific
     * day
     *
     * @param year
     * @param month
     * @param day
     *
     * @return string with all events on the specified day formatted with the
     * current layout
     */
    public String getDayEvents(int year, int month, int day) {
        month++;
        Iterator iter = sched.iterator();
        events.clear();
        while (iter.hasNext()) {
            EventModel temp = (EventModel) iter.next();
            if (temp.day == day && temp.month == month && temp.year == year) {
                events.add(temp);
            }
        }
        return layout.eventsToString(events);
    }

    /**
     * Creates a string with the set layout of all the events in an specific
     * week
     *
     * @param year
     * @param month
     * @param day
     *
     * @param string with all events in the specified week, formatted with the
     * current layout
     */
    public String getWeek(int year, int month, int day) {
        month++;
        c.set(year, month, day);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        return getAgendaEvents(year, month, day - dayOfWeek, year, month, day + (7 - dayOfWeek));
    }

    /**
     * Creates a string with the set layout of all the events in an specific
     * month
     *
     * @param year
     * @param month
     *
     * @param string with all events in the specified month, formatted with the
     * current layout
     */
    public String getMonthEvents(int year, int month) {
        month++;
        Iterator iter = sched.iterator();
        events.clear();
        while (iter.hasNext()) {
            EventModel temp = (EventModel) iter.next();
            if (temp.month == month && temp.year == year) {
                events.add(temp);
            }
        }
        return layout.eventsToString(events);
    }

    /**
     * Creates a string with the set layout of all the events in an specific
     * date range
     *
     * @param year starting year
     * @param month starting month
     * @param day starting day
     * @param eday ending day
     * @param emonth ending month
     * @param eyear ending year
     *
     * @param string with all events in the specified date range, formatted with the
     * current layout
     */
    public String getAgendaEvents(int year, int month, int day, int eyear, int emonth, int eday) {
        Iterator iter = sched.iterator();
        events.clear();
        while (iter.hasNext()) {
            EventModel temp = (EventModel) iter.next();
            if (temp.year >= year && temp.year <= eyear) {
                if ((!(temp.month < month && temp.year == year)) && (!(temp.month > emonth && temp.year == eyear))) {
                    if ((!(temp.day < day && temp.month == month)) && (!(temp.day > eday && temp.month == emonth))) {
                        events.add(temp);
                    }
                }
            }
        }
        return layout.eventsToString(events);
    }

    /**
     * Attach a ChangeListner
     *
     */
    public void attach(ChangeListener c) {
        listeners.add(c);
    }

    /**
     * Sets the current Layout
     *
     * @param temp new layout to be set
     *
     * @return string with all events requested last formatted with the new
     * layout
     */
    public String setLayout(EventViewLayout temp) {
        layout = temp;
        return layout.eventsToString(events);
    }

    /**
     * Sets recurring events
     *
     * @param s data of the recurring event formatted in "Description;YYYY;MM;DD;MTWHFAS;SH;EH;"
     *
     */
    public void set(String s) {
        String desc = s.substring(0, s.indexOf(";"));
        s = s.substring(s.indexOf(";") + 1);
        int y = Integer.parseInt(s.substring(0, s.indexOf(";")));
        s = s.substring(s.indexOf(";") + 1);
        int sm = Integer.parseInt(s.substring(0, s.indexOf(";")));
        s = s.substring(s.indexOf(";") + 1);
        int em = Integer.parseInt(s.substring(0, s.indexOf(";")));
        s = s.substring(s.indexOf(";") + 1);
        String days = s.substring(0, s.indexOf(";"));
        s = s.substring(s.indexOf(";") + 1);
        int sh = Integer.parseInt(s.substring(0, s.indexOf(";")));
        s = s.substring(s.indexOf(";") + 1);
        int eh = Integer.parseInt(s.substring(0, s.indexOf(";")));

        for (int i = sm; i <= em; i++) {
            c.set(y, i - 1, 1);
            int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int j = 1; j <= daysInMonth; j++) {
                if (days.contains(getWeekday(y, i, j))) {
                    add(new EventModel(desc, j, i, y, sh, eh + ""));
                }
            }
        }
    }

    private String getWeekday(int year, int month, int day) {
        c.set(year, month - 1, day);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "S";
            case 2:
                return "M";
            case 3:
                return "T";
            case 4:
                return "W";
            case 5:
                return "H";
            case 6:
                return "F";
            case 7:
                return "A";
            default:
                return null;
        }
    }
}
