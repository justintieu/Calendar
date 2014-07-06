
/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AgendaControls
{

    private AgendaView av;
    private CalendarModel cm;
    private ScheduleModel sm;

    /**
     * Constructs all the controls for the agenda.
     *
     * @param av agendaview reference
     * @param cm calendarmodel reference
     * @param sm schedulemodel reference
     */
    public AgendaControls(AgendaView av, CalendarModel cm, ScheduleModel sm)
    {
        this.av = av;
        this.cm = cm;
        this.sm = sm;
    }

    /**
     * Creates an action for the "Day" button that leads to the agenda for the
     * selected day.
     *
     * @return action for "Day" view
     */
    public ActionListener day()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                av.setText(sm.getDayEvents(cm.getYear(), cm.getMonth(), cm.getDay()));
            }
        };
    }

    /**
     * Creates an action for the "Week" button that leads to the agenda for the
     * selected week.
     *
     * @return action for "Week" view
     */
    public ActionListener week()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                av.setText(sm.getWeek(cm.getYear(), cm.getMonth(), cm.getDay()));
            }
        };
    }

    /**
     * Creates an action for the "Month" button that leads to the agenda for the
     * selected month.
     *
     * @return Action for the month button
     */
    public ActionListener month()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                av.setText(sm.getMonthEvents(cm.getYear(), cm.getMonth()));
            }
        };
    }

    /**
     * Creates an action for the "Agenda" button that leads to the agenda for a
     * selected time frame.
     *
     * @return action for "Agenda" button
     */
    public ActionListener agenda()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                final JFrame frame = new JFrame();
                final JTextField startDate;
                final JTextField endDate;
                frame.setTitle("Search Agenda");
                final JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 5));
                panel.add(new JLabel("Date Range (MM/DD/YYYY):  "));
                startDate = new JTextField(10);
                panel.add(startDate);
                panel.add(new JLabel(" - "));
                endDate = new JTextField(10);
                panel.add(endDate);
                panel.add(new JLabel());
                JButton doneBtn = new JButton("Search");
                doneBtn.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        // TODO Auto-generated method stub
                        String[] startdate = startDate.getText().split("/");
                        String[] enddate = endDate.getText().split("/");
                        av.setText(sm.getAgendaEvents(Integer.parseInt(startdate[2]), Integer.parseInt(startdate[0]), Integer.parseInt(startdate[1]),
                                Integer.parseInt(enddate[2]), Integer.parseInt(enddate[0]), Integer.parseInt(enddate[1])));
                        //check for time conflicts
                        frame.setVisible(false);
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

    /**
     * Creates an action for the "From File" button that asks the user to open a
     * .txt file from their computer. Once the user selects a .txt file, the
     * events in the file will be imported into the agenda.
     *
     * @return action for "From File" button
     */
    public ActionListener file()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                JFileChooser inputFile = new JFileChooser();
                inputFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                if (inputFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    File file = inputFile.getSelectedFile();

                    try
                    {
                        Scanner in = new Scanner(file);
                        while (in.hasNextLine())
                        {
                            sm.set(in.nextLine());
                        }
                    }
                    catch (FileNotFoundException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    av.setText("Uploading events from file into calendar...");
                }
            }
        };
    }

    /**
     * Creates an action for the all three of the layout buttons. Clicking each
     * button will change the layout of how the events are displayed in the
     * agenda view.
     *
     *
     * @param layoutType - 1 for layout 1, 2 for layout 2, 3 for layout 3
     * @return action for the layout buttons
     */
    public ActionListener layoutButton(final int layoutType)
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                switch (layoutType)
                {
                    case 1:
                        av.setText(sm.setLayout(new Layout1()));
                        break;
                    case 2:
                        av.setText(sm.setLayout(new Layout2()));
                        break;
                    case 3:
                        av.setText(sm.setLayout(new Layout3()));
                        break;
                    default:
                        av.setText(sm.setLayout(new Layout1()));
                        break;
                }
            }
        };
    }
}
