
/**
 * Calendar Project
 * Fall 2013 CS 151 - 01
 * 12/02/13
 *
 * Team IDK: Eric Tam, Justin Tieu, Jannette Pham-Le
 */

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class AgendaView
{

    private JPanel panel;
    private JTextArea textarea;
    private AgendaControls ac;
    private ScheduleModel sm;

    /**
     * Constructs the display of the agenda.
     * 
     * @param sm - ScheduleModel reference
     * @param cm - CalendarModel reference
     */
    public AgendaView(ScheduleModel sm, CalendarModel cm)
    {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ac = new AgendaControls(this, cm, sm);
        this.sm = sm;
    }

    /**
     * Gets the schedule model.
     * 
     * @return schedule model
     */
    public ScheduleModel getScheduleModel()
    {
        return sm;
    }

    /**
     * Gets the panel that displays the buttons that control the agenda view.
     * 
     * @return button panel
     */
    public JPanel getButtons()
    {
        JPanel buttons = new JPanel();
        JButton day = new JButton("Day");
        JButton week = new JButton("Week");
        JButton month = new JButton("Month");
        JButton agenda = new JButton("Agenda");
        JButton file = new JButton("From File");

        day.addActionListener(ac.day());
        week.addActionListener(ac.week());
        month.addActionListener(ac.month());
        agenda.addActionListener(ac.agenda());
        file.addActionListener(ac.file());

        buttons.add(day);
        buttons.add(week);
        buttons.add(month);
        buttons.add(agenda);
        buttons.add(file);
        return buttons;
    }

    /**
     * Gets the panel that displays the agenda.
     * 
     * @return panel that displays the agenda
     */
    public JPanel getAgenda()
    {
        JPanel textPanel = new JPanel();
        final int NUM_ROWS = 35;
        final int NUM_COLS = 40;
        textarea = new JTextArea(NUM_ROWS, NUM_COLS);
        textarea.setBorder(new LineBorder(null));
        textarea.setEditable(false);

        JScrollPane scroll = new JScrollPane(textarea);
        textPanel.add(scroll);
        return textPanel;
    }

    /**
     * Gets the panel that displays the buttons to change the layout of the
     * agenda.
     * 
     * @return layout buttons panel
     */
    public JPanel getLayoutButtons()
    {
        JPanel panel = new JPanel();
        JButton eventview = new JButton("Event View Type:");
        JButton layout1 = new JButton("Layout 1");
        JButton layout2 = new JButton("Layout 2");
        JButton layout3 = new JButton("Layout 3");

        eventview.setEnabled(false);

        layout1.addActionListener(ac.layoutButton(1));
        layout2.addActionListener(ac.layoutButton(2));
        layout3.addActionListener(ac.layoutButton(3));

        panel.add(eventview);
        panel.add(layout1);
        panel.add(layout2);
        panel.add(layout3);

        return panel;
    }

    /**
     * Creates a panel with the agenda buttons on the top, the agenda text
     * area in the center and the layout buttons in the bottom
     *
     * @return  panel with agenda buttons, agenda textarea and layout buttons
     */
    public JPanel getAgendaPanel()
    {
        panel.add(getButtons(), BorderLayout.NORTH);
        panel.add(getAgenda(), BorderLayout.CENTER);
        panel.add(getLayoutButtons(), BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Changes the content in the agenda.
     * 
     * @param newText - content that want to show in the agenda
     */
    public void setText(String newText)
    {
        textarea.setText(newText);
    }

    /**
     * Gives a reference of the JTextArea that is in the agenda
     * 
     * @return JTextArea in the agenda
     */
    public JTextArea getText()
    {
        return textarea;
    }
}
