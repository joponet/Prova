package jpo.formulari;

import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Formulari extends JFrame {

	private static final long serialVersionUID = 2025078504280569220L;

	public Formulari() {
		Container panel;
		
		JLabel label1,label2;
		JTextField text1,text2;
		
		panel = this.getContentPane();
		GroupLayout layout = new GroupLayout(panel);
		this.setLayout(layout);
		this.setTitle("Formulari");
		
		
		label1 = new JLabel("Label 1");
//		label.setLocation(5, 5);
//		label.setSize(60, 20);
//		panel.add(label);
		text1 = new JTextField(15);
//		text.setLocation(75, 5);
//		text.setSize(100, 20);
//		panel.add(text);
		
		
		label2 = new JLabel("Etiqueta 2");
//		label.setLocation(5, 30);
//		label.setSize(60, 20);
//		panel.add(label);
		text2 = new JTextField(15);
//		text.setLocation(75, 30);
//		text.setSize(100, 20);
//		panel.add(text);
		
		// layout
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(label1)
						.addComponent(text1)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(label2)
						.addComponent(text2)
						)
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(label1)
						.addComponent(text1,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(label2)
						.addComponent(text2,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						)
				);
		layout.linkSize(SwingConstants.HORIZONTAL, label1,label2);
		
		setContentPane(panel);
		pack();
	}
	public static void main(String[] args) {
		JFrame finestre = new Formulari();
		finestre.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		Dimension mida = new Dimension(200,95);
//		finestre.setSize(mida);
//		finestre.setMaximumSize(mida);
//		finestre.setMinimumSize(mida);
//		finestre.setLocation(500, 400);
		finestre.setVisible(true);
	}

}
