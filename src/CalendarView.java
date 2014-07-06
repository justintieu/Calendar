/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CalendarView
{

    private CalendarModel cm;
    private CalendarControls cc;
    private ScheduleModel sm;
    private JPanel panel;
    private JPanel calendarHeader;
    private JPanel calendarPanel;
    private JButton month;
    private JButton[] days;
    private final int NUM_ROWS = 6;
    private final int NUM_COLS = 7;

    /**
     * Constructs the display of the calendar.
     * 
     * @param sm ScheduleModel reference
     * @param cm CalendarModel reference
     * @param av AgendaView reference
     */
    public CalendarView(ScheduleModel sm, CalendarModel cm, AgendaView av)
    {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        this.sm = sm;
        this.cm = cm;
        cc = new CalendarControls(cm, this, av, sm);

        days = new JButton[NUM_COLS * NUM_ROWS];
        for (int i = 0; i < days.length; i++)
        {
            days[i] = button("");
        }
    }

    /**
     * Gets the calendar.
     * 
     * @return calendar
     */
    public CalendarModel getCalendarModel()
    {
        return cm;
    }

    /**
     * Gets the header panel that consists of the main functions controlling
     * the calendar.
     * 
     * @return header panel
     */
    public JPanel getHeader()
    {
        calendarHeader = new JPanel();
        calendarHeader.setLayout(new BorderLayout());
        month = new JButton(cm.getMonthName() + " " + cm.getYear());
        month.setEnabled(false);
        JButton prevBtn = new JButton("<<");
        JButton nextBtn = new JButton(">>");
        JButton create = new JButton("Create");

        prevBtn.addActionListener(cc.changeMonth(-1));
        nextBtn.addActionListener(cc.changeMonth(1));
        create.addActionListener(cc.create());

        final JPanel dates = new JPanel();
        dates.setLayout(new GridLayout(1, 7));
        JButton[] dayNames = new JButton[7];
        String[] days = new String[]
        {
            "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"
        };
        for (int i = 0; i < dayNames.length; i++)
        {
            dayNames[i] = new JButton(days[i]);
            dayNames[i].setEnabled(false);
            dates.add(dayNames[i]);
        }

        calendarHeader.add(month, BorderLayout.NORTH);
        calendarHeader.add(prevBtn, BorderLayout.LINE_START);
        calendarHeader.add(create, BorderLayout.CENTER);
        calendarHeader.add(nextBtn, BorderLayout.LINE_END);
        calendarHeader.add(dates, BorderLayout.SOUTH);
        return calendarHeader;
    }

    /**
     * Gets the panel that displays the days of the calendar.
     * 
     * @return day panel
     */
    public JPanel getDays()
    {
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        int dayNumber = 1;
        for (int i = cm.getStartDay() - 1; i < cm.getStartDay() + cm.getNumDays() - 1; i++)
        {
            days[i] = this.button(dayNumber + "");
            days[i].addActionListener(cc.getDay(days[i].getText()));
            if (dayNumber <= cm.getNumDays())
            {
                dayNumber++;
            }
        }

        for (JButton d : days)
        {
            calendarPanel.add(d);
        }

        return calendarPanel;
    }

    /**
     * Gets the panel that displays the whole calendar.
     * 
     * @return calendar panel
     */
    public JPanel getCalendarPanel()
    {
        panel.add(getHeader(), BorderLayout.NORTH);
        panel.add(getDays(), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Updates the calendar to display different months in different year.s
     */
    public void update()
    {
        month.setText(cm.getMonthName() + " " + cm.getYear());
        int dayNumber = 1;
        for (int i = 0; i < cm.getStartDay(); i++)
        {
            days[i].setText("");
            for (ActionListener al : days[i].getActionListeners())
            {
                days[i].removeActionListener(al);
            }
            days[i].addActionListener(cc.getDay(days[i].getText()));
        }
        for (int i = cm.getStartDay() - 1; i < cm.getStartDay() + cm.getNumDays() - 1; i++)
        {
            days[i].setText(dayNumber + "");
            for (ActionListener al : days[i].getActionListeners())
            {
                days[i].removeActionListener(al);
            }
            days[i].addActionListener(cc.getDay(days[i].getText()));
            dayNumber++;
        }
        for (int i = cm.getStartDay() + cm.getNumDays() - 1; i < days.length; i++)
        {
            days[i].setText("");
            for (ActionListener al : days[i].getActionListeners())
            {
                days[i].removeActionListener(al);
            }
            days[i].addActionListener(cc.getDay(days[i].getText()));
        }
    }

    /**
     * Creates a customized button that displays the day in the month.
     * 
     * @param num - day
     * @return day button
     */
    public JButton button(String num)
    {
        final JButton bigBooty = new JButton(num);

        bigBooty.setFocusPainted(false);
        bigBooty.setBackground(Color.white);
        bigBooty.setBorder(null);
        bigBooty.setFocusable(false);
        bigBooty.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                bigBooty.setBackground(Color.pink);
                bigBooty.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        //go to day view       
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                bigBooty.setBackground(Color.lightGray);
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                bigBooty.setBackground(Color.white);
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                bigBooty.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                bigBooty.setBackground(Color.white);
            }
        });

        return bigBooty;
    }
}
