package jpo.provaTab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class provaTab extends JFrame implements ActionListener {

	/**
	 * 
	 */

	/**
	 * @param args
	 */
	
	private static final long serialVersionUID = -781030521247767047L;
	
	private provaTab() {
		JTabbedPane panellTabulat = new JTabbedPane();

		// panell 1
		JPanel panell1 = new JPanel();
		panell1.setBackground(Color.lightGray);
		// etiqueta
		panell1.add(new JLabel("Prova 1"));
		// camp text
		JTextField text1 = new JTextField(10);
		panell1.add(text1);
		// buto
		JButton buto = new JButton("Prova");
		buto.addActionListener(this);
		panell1.add(buto);
		
		panellTabulat.addTab("Panell 1", panell1);
		
		// panell 2
		JLayeredPane panell2 = new JLayeredPane();
		panell2.setOpaque(true);
		panell2.setBackground(Color.lightGray);
		
		// hipotenusa
		JLabel etiquetaH = new JLabel("h");
		etiquetaH.setBounds(150, 150, 100, 25);
		panell2.add(etiquetaH,-1);
		// camp text
		JTextField textH = new JTextField();
		textH.setBounds(165, 150, 60, 25);
		panell2.add(textH,-1);

		// costat a
		JLabel etiquetaA = new JLabel("a");
		etiquetaA.setBounds(20, 130, 100, 25);
		panell2.add(etiquetaA,-1);
		// camp text
		JTextField textA = new JTextField();
		textA.setBounds(35, 130, 60, 25);
		panell2.add(textA,-1);
		
		// costat b
		JLabel etiquetaB = new JLabel("b");
		etiquetaB.setBounds(140, 250, 100, 25);
		panell2.add(etiquetaB,-1);
		// camp text
		JTextField textB = new JTextField();
		textB.setBounds(155, 250, 60, 25);
		panell2.add(textB,-1);
		
		// imatge
		JLabel imatge = new JLabel();
		// ruta origen es ../prova
		ImageIcon icona = new ImageIcon("images/triangle.png");
		imatge.setIcon(icona);
		imatge.setBounds(30, 0, icona.getIconWidth(), icona.getIconHeight());
		panell2.add(imatge,-1);
		
		panellTabulat.addTab("Triangle", panell2);
		
		setContentPane(panellTabulat);
		pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		JFrame principal = new provaTab();
		principal.setTitle("Prova tabulacions");
		// tamany de la finestre
		int amplada = 350;
		int alçada = 350;
		
		Dimension minDimensio = new Dimension(amplada,alçada);
		Dimension tamany;
		
		principal.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// posició inicial de la finestra centrada en pantall
		tamany = Toolkit.getDefaultToolkit().getScreenSize();
		principal.setLocation((tamany.width-amplada)/2, (tamany.height-alçada)/2);
		
		principal.setMinimumSize(minDimensio);
		principal.setVisible(true);
	}

}
