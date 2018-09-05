import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import helpers.*;
import java.text.NumberFormat;

@SuppressWarnings("serial")
public class CalculationList extends JPanel {

	JLabel lblDirections;
	JButton btnDisplay;
	
	public CalculationList() {
		createPanel();
	}
	
	private void createPanel() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
	
		bag.fill = GridBagConstraints.BOTH;
		bag.anchor = GridBagConstraints.FIRST_LINE_START;
		bag.insets = new Insets(5,5,5,5);
		
		bag.gridx = 0;
		bag.gridy = 0;
		bag.gridwidth = 2;
		lblDirections = new JLabel("Click Display to show history of calculations");
		lblDirections.setFont(new Font("Arial", Font.BOLD, 12));
		lblDirections.setForeground(Color.BLUE);
		this.add(lblDirections, bag);
	
		bag.gridx = 1;
		bag.gridy = 5;
		bag.weightx = .5;
		bag.gridwidth = 2;
		btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new DisplayHandler());
		this.add(btnDisplay, bag);
		
	}
	
	private class DisplayHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			displayCalculationList();
			
		}
		
		private void displayCalculationList() {
			StringBuilder str = new StringBuilder();
			str.append("Calculations Activity Report");
			str.append("\nWidth\tLength\tArea");
			str.append("\n");
			
			String deliminator = "[,]";
			StringTokenizer row;
			ArrayList<String> listString = FileHelpers.readList("officeData.txt");
			
			for(String calcStr : listString) {
				row = new StringTokenizer(calcStr, deliminator);
				str.append(extractValue(row.nextToken()) + "\t");
				str.append(extractValue(row.nextToken()) + "\t");
				str.append(extractValue(row.nextToken()) + "\n");
			}
			OutputHelpers.showConsole(str.toString());
		}
		private String extractValue (String strValue ) {
			double tmpValue = 0;
			NumberFormat nf = NumberHelpers.getDecimalFormatter(2);
			try {
				tmpValue = Double.parseDouble(strValue);
			}
			catch (NumberFormatException ex) {
				ex = null;
				tmpValue = 0;
			}
			return nf.format(tmpValue);
		}
	}
}
