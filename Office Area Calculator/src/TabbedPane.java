import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class TabbedPane extends JFrame
{
	public static final int WINDOW_WIDTH = 500;
	public static final int WINDOW_HEIGHT = 400;
	
	public TabbedPane() 
	{
		super();
		createPanels();
		setFrame();
	}
	private void setFrame()
	{
		Dimension windowSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		super.setTitle("Creating GUI's");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		super.setSize(windowSize);
		super.setMinimumSize(windowSize);
		super.setMaximumSize(windowSize);
		
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		
	}
	private void createPanels()
	{
		super.setLayout(new GridBagLayout());
		
		GridBagConstraints bag = new GridBagConstraints();
		
		bag.fill = GridBagConstraints.HORIZONTAL;
		bag.gridx = 0;
		bag.gridy = 0;
		bag.ipady = 250;
		bag.weightx = 1;
		bag.weighty = .75;
		JTabbedPane jtp = new JTabbedPane();
		
		jtp.addTab("Office Area Calculator", new OfficeAreaCalculator());
		jtp.addTab("Calculation Activity", new CalculationList());
		jtp.addTab("Day GUI", new DayGUI());
		
		this.add(jtp, bag);
		
		bag.fill = GridBagConstraints.CENTER;
		bag.gridx = 0;
		bag.gridy = 1;
		bag.ipady = 40;
		bag.ipadx = 400;
		bag.weightx = 1;
		bag.weighty = .25;
		bag.insets = new Insets(10,0,10, 0);
		
		this.add(new ExitButton("Area Calculator"), bag);
	}
}
