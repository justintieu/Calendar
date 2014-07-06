/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalendarControls
{

    private CalendarModel cm;
    private CalendarView cv;
    private ScheduleModel sm;
    private AgendaView av;

    /**
     * Constructs all the controls for the calendar view.
     * 
     * @param cm CalendarModel reference
     * @param cv CalendarView reference
     * @param av AgendaView reference
     * @param sm ScheduleModel reference
     */
    public CalendarControls(CalendarModel cm, CalendarView cv, AgendaView av, ScheduleModel sm)
    {
        this.cm = cm;
        this.cv = cv;
        this.sm = sm;
        this.av = av;
    }

    /**
     * Creates an action to view a different month.
     * 
     * @param changeMonth
     * @return action for changing the current month
     */
    public ActionListener changeMonth(final int changeMonth)
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                cm.getGC().set(2, cm.getGC().get(2) + changeMonth);
                cv.update();
            }
        };
    }

    /**
     * Creates an action to set the current day and display events for the
     * current day
     * 
     * @param day - the current day to set
     * @return action to change the current day
     */
    public ActionListener getDay(final String day)
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                if (!day.equals(""))
                {
                    int dayNum = Integer.parseInt(day);
                    cm.setDay(dayNum);
                    av.setText(sm.getDayEvents(cm.getYear(), cm.getMonth(), cm.getDay()));
                }
            }
        };
    }

    /**
     * Creates an action for the "Create" button that allows a user to schedule
     * a new event
     * 
     * @return action for "Create" button
     */
    public ActionListener create()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                final JFrame frame = new JFrame();
                final JTextField descript;
                final JTextField eventDate;
                final JTextField startTime;
                final JTextField endTime;

                frame.setTitle("Create an Event");
                final JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(6, 2));

                panel.add(new JLabel("Event Description: "));
                descript = new JTextField(30);
                panel.add(descript);

                panel.add(new JLabel("Event Date (MM/DD/YYYY):  "));
                eventDate = new JTextField(10);
                panel.add(eventDate);

                panel.add(new JLabel("Start Time (0-23 hours): "));
                startTime = new JTextField(5);
                panel.add(startTime);

                panel.add(new JLabel("End Time: "));
                endTime = new JTextField(5);
                panel.add(endTime);

                panel.add(new JLabel());
                JButton doneBtn = new JButton("Create Event");
                doneBtn.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        // TODO Auto-generated method stub
                        String[] date = eventDate.getText().split("/");
                        EventModel newEvent = new EventModel(descript.getText(), Integer.parseInt(date[1]), Integer.parseInt(date[0]), Integer.parseInt(date[2]), Integer.parseInt(startTime.getText()), endTime.getText());
                        boolean success = sm.add(newEvent);
                        //check for time conflicts
                        if (success)
                        {
                            //rewrite old frame with success message
                            frame.setVisible(false);
                            //System.out.println("new event added\n\tupdate line94 in CalendarControls.java later");
                        }
                        else
                        {
                            //If there is any time conflict, ask the user to enter the event again with a correct time.
                            //System.out.println("these is a time conflict\n\tupdate line97 in CalendarControls.java later");
                            final JFrame conflict = new JFrame();
                            final JPanel conflictPanel = new JPanel(new BorderLayout());
                            JLabel error = new JLabel("There is a time conflict. Please choose another time for your event.");
                            JButton okay = new JButton("Okay");
                            okay.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									conflict.setVisible(false);
								}
							});
                            conflictPanel.add(error,BorderLayout.NORTH);
                            conflictPanel.add(okay,BorderLayout.SOUTH);
                            conflict.add(conflictPanel);
                            conflict.pack();
                            conflict.setVisible(true);
                            conflict.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        }
                    }
                });
                panel.add(doneBtn);

                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        };
    }
}
