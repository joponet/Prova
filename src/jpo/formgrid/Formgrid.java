package jpo.formgrid;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Formgrid extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179692360722472275L;

	/**
	 * @param args
	 */
	public Formgrid () {
		GridBagLayout layout = new GridBagLayout();
		JPanel panel = new JPanel(layout);
		GridBagConstraints gb = new GridBagConstraints();
		Insets insets = new Insets(5,5,5,5);
		
		JLabel label;
		JTextField text;
				
		label = new JLabel("Label 1");
		gb.gridx=0;
		gb.gridy=0;
		gb.anchor=GridBagConstraints.EAST;
		gb.fill=GridBagConstraints.NONE;
		gb.weightx=0;
		gb.insets=insets;
		panel.add(label, gb);

		text = new JTextField(15);
		gb.gridx=1;
		gb.gridy=0;
		gb.anchor=GridBagConstraints.CENTER;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.weightx=1;
		panel.add(text,gb);
		
		
		label = new JLabel("Labelito 2");
		gb.gridx=0;
		gb.gridy=1;
		gb.anchor=GridBagConstraints.EAST;
		gb.fill=GridBagConstraints.NONE;
		gb.weightx=0;
		panel.add(label,gb);

		text = new JTextField(15);
		gb.gridx=1;
		gb.gridy=1;
		gb.anchor=GridBagConstraints.CENTER;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.weightx=1;
		panel.add(text,gb);
		
		setContentPane(panel);
		pack();

	}
	
	public static void main(String[] args) {
		JFrame finestre = new Formgrid();
		finestre.setDefaultCloseOperation(EXIT_ON_CLOSE);
		finestre.setLocation(500, 300);
		finestre.setVisible(true);
	}

}
