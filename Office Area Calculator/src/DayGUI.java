/**********************************************************************
 * Program Name: DayGUI
 * Programmers Name: Robert DeCrescentis
 * 
 * Program Description: This class creates a GUI having a JFrame and two JButtons.
 * Each button has Add individual event handlers to your program so that when 
 * a user clicks the Good button, the message "Today is a good day!" appears 
 * in a dialog box, and when the Bad button is clicked, 
 * the message "I'm having a bad day today!" is displayed. 
 *****************************************************************************/
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import helpers.*;


@SuppressWarnings("serial")
public class DayGUI extends JPanel
{
	private JButton cmdGood;
	private JButton cmdBad;

	public DayGUI()
	{
		super();
		createPanel();
	}

	private void createPanel() {
		//no layout manager, use the default, very little placement control
		cmdGood = new JButton("Good");
		cmdGood.setForeground(Color.red);
		cmdGood.setBackground(Color.white);
		cmdBad = new JButton("Bad");
		cmdBad.setForeground(Color.red);
		cmdBad.setBackground(Color.white);

		this.add(cmdGood);
		this.add(cmdBad);

		cmdGood.setMnemonic('G');
		cmdBad.setMnemonic('B');

		ButtonsHandler bhandler = new ButtonsHandler();
		cmdGood.addActionListener(bhandler);
		cmdBad.addActionListener(bhandler);
	}
	private class ButtonsHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String prompt;
			String title;
			
			if (e.getSource() == cmdGood)
			{
				prompt = "Today is a good day";
				title = "Good Day Event";
			}
			else if (e.getSource() == cmdBad)
			{
				prompt = "Today is a bad day";
				title = "Bad Day Event";
			}
			else
			{
				prompt = "Unknown Event";
				title = "Illegal Operation";
			}
			OutputHelpers.showStandardDialog(prompt, title);
		}
		
	}	
}
