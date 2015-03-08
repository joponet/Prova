package jpo.prova;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

class Prova extends JPanel implements ActionListener {

	private static final long serialVersionUID = -7040258232734882776L;

	static String usuari;
	JTextField texte1;
	JTextField texte2;
	JTextField texte3;
	JTextArea texte;
	JLabel imatge;


	public Prova() {
		// textes
		JLabel etiqueta1 = new JLabel("Entrada a");
		add(etiqueta1);
		texte1 = new JTextField("",15);
		add(texte1);
		
		JLabel etiqueta2 = new JLabel("Entrada b");
		add(etiqueta2);
		texte2 = new JTextField("",15);
		add(texte2);
		
		JButton btResultat = new JButton("Resultat");
		btResultat.addActionListener(this);
		add(btResultat);
		texte3 = new JTextField("",15);
		add(texte3);
		
		texte = new JTextArea(5,20);
		JScrollPane scroll = new JScrollPane(texte);
		add(scroll);
		
		imatge = new JLabel("Imatge");
		
		//layout
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		layout.putConstraint(SpringLayout.EAST, etiqueta1, 90, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta1, 10, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, texte1, 7, SpringLayout.EAST, etiqueta1);
		layout.putConstraint(SpringLayout.SOUTH, texte1, 0, SpringLayout.SOUTH, etiqueta1);
		
		layout.putConstraint(SpringLayout.EAST, etiqueta2, 0, SpringLayout.EAST, etiqueta1);
		layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);

		layout.putConstraint(SpringLayout.WEST, texte2, 7, SpringLayout.EAST, etiqueta2);
		layout.putConstraint(SpringLayout.EAST, texte2, -5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, texte2, 0, SpringLayout.SOUTH, etiqueta2);

		layout.putConstraint(SpringLayout.EAST, btResultat, 0, SpringLayout.EAST, etiqueta2);
		layout.putConstraint(SpringLayout.NORTH, btResultat, 8, SpringLayout.SOUTH, etiqueta2);

		layout.putConstraint(SpringLayout.WEST, texte3, 7, SpringLayout.EAST, btResultat);
		layout.putConstraint(SpringLayout.EAST, texte3, -5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, texte3, 0, SpringLayout.VERTICAL_CENTER, btResultat);
		
		layout.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, scroll, -5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH, btResultat);

		layout.putConstraint(SpringLayout.EAST, this, 5, SpringLayout.EAST, texte1);
		layout.putConstraint(SpringLayout.SOUTH, this, 5, SpringLayout.SOUTH, scroll);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String missatge;
		missatge ="Hola ";
		missatge = missatge.concat(usuari);
		System.out.println(missatge);
		int a = Integer.parseInt(texte1.getText());
		int b=Integer.parseInt(texte2.getText());
		int c;
		c=a*b;
		System.out.printf("%1$dx%2$d=%3$d\n",a,b,c);
		texte3.setText(String.valueOf(c));
		texte.append(String.format("%1$dx%2$d=%3$d\n",a,b,c));
	}

	public static void main(String[] args)	{		

		usuari = System.getenv("USERNAME");

		// finestra
		JFrame finestra = new JFrame();
		finestra.setTitle(usuari);
		finestra.setContentPane(new Prova());
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// mostra finestra
		finestra.pack();
		finestra.setVisible(true);
	}

}
