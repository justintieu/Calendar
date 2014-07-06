
/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class Calendar
{

    public static void main(String[] args)
    {
        final JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(new Dimension(1000, 700));
        EventViewLayout evl = new Layout1();
        CalendarModel cm = new CalendarModel();
        ScheduleModel sm = new ScheduleModel(evl);
        final AgendaView av = new AgendaView(sm, cm);
        final CalendarView cv = new CalendarView(sm, cm, av);

        frame.add(cv.getCalendarPanel());
        frame.add(av.getAgendaPanel());

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
