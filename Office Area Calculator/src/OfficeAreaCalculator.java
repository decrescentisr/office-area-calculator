import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import helpers.*;

@SuppressWarnings("serial")
public class OfficeAreaCalculator extends JPanel
{
	JLabel lblLength;
	JTextField txtLength;
	JLabel lblWidth;
	JTextField txtWidth;
	JLabel lblArea;
	JTextField txtArea;
	JButton btnCalculate; 
	
	CalculateHandler handler;
	
	public OfficeAreaCalculator()
	{
		super();
		createPanel();
	}	
	public CalculateHandler getHandler() {
		return handler;
	}
	public void clear() {
		txtArea.setText("");
		txtLength.setText("");
		txtWidth.setText("");
	}
	private void createPanel()
	{
		super.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
	
		bag.fill = GridBagConstraints.BOTH;
		bag.anchor = GridBagConstraints.FIRST_LINE_START;
		bag.insets = new Insets(5,5,5,5);
		
		//layout the panel in two four rows, two columns
		//column 0, row 0, start counting at 0 (zero)
		bag.gridx = 0;
		bag.gridy = 0;
		lblLength = new JLabel();
		lblLength.setText("Enter the length of your office:");
		super.add(lblLength, bag);
		
		//column 1, row 0
		bag.gridx = 1;
		bag.gridy = 0;
		txtLength = new JTextField(5);
		super.add(txtLength, bag);
		
		//column 0, row 1
		bag.gridx = 0;
		bag.gridy = 1;
		lblWidth = new JLabel();
		lblWidth.setText("Enter the width of your office:");
		super.add(lblWidth, bag);
		
		////column 1, row 1
		bag.gridx = 1;
		bag.gridy = 1;
		
		txtWidth = new JTextField(5);
		super.add(txtWidth, bag);
		
		//row 2, span both columns
		//take up half the column
		handler = new CalculateHandler();
		bag.gridx = 0;
		bag.gridy = 2;
		bag.gridwidth = 2;
		btnCalculate = new JButton("Calculate Area");
		btnCalculate.addActionListener(handler);
		super.add(btnCalculate, bag);
		
		//column 0, row 3
		bag.gridx = 0;
		bag.gridy = 3;
		lblArea = new JLabel();
		lblArea.setText("The area of your office is:");
		super.add(lblArea, bag);
		
		//column 1, row 3
		bag.gridx = 1;
		bag.gridy = 3;
		txtArea = new JTextField(5);
		txtArea.setEditable(false); //read only field
		super.add(txtArea, bag);
		
		
	}
	public class CalculateHandler implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{				
			double width, length, area;
			
			width = InputHelpers.parseDoubleField(txtLength, "Width", 5, 100);
			length = InputHelpers.parseDoubleField(txtWidth, "Length", 5, 100);
			
			if(length > 0 && width > 0) {
				area = length * width;
				txtArea.setText(OutputHelpers.formattedDouble(area, 2));
				writeToFile(length, width, area);
			}
			else {
				txtArea.setText("");
			}
		}
		private void writeToFile(double length, double width, double area) {
			StringBuilder str = new StringBuilder();
			str.append(length + ",");
			str.append(width + ",");
			str.append(area);
			FileHelpers.writeData(str.toString(), "officeData.txt");
		}
	}
	
}
