package jpo.marge;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Marge extends JFrame implements ActionListener, KeyListener, FocusListener  {

	private static final long serialVersionUID = -1499764436972007854L;
	
	Calculadora calculadora = new Calculadora();
	JTextField tfCost;
	JTextField tfVenta;
	JTextField tfMarge;
	CalculaMarge calculaMarge;
	boolean novaEntrada = true;

	public Marge() {
		Container contenidor = this.getContentPane();
		
		JLabel lbCost = new JLabel("Cost");
		contenidor.add(lbCost);
		
		tfCost = new JTextField(10);
		tfCost.addKeyListener(this);
		tfCost.addFocusListener(this);
		contenidor.add(tfCost);
		
		JLabel lbVenta = new JLabel("Venta");
		contenidor.add(lbVenta);
		
		tfVenta = new JTextField(10);
		tfVenta.addKeyListener(this);
		tfVenta.addFocusListener(this);
		contenidor.add(tfVenta);
		
		JLabel lbMarge = new JLabel("Marge");
		contenidor.add(lbMarge);

		tfMarge = new JTextField(10);
		tfMarge.addKeyListener(this);
		tfMarge.addFocusListener(this);
		contenidor.add(tfMarge);
		
		JButton btCost = new JButton("Cost");
		btCost.setActionCommand("c");
		btCost.addActionListener(this);
		btCost.setFocusable(false);
		contenidor.add(btCost);

		JButton btVenta = new JButton("Venta");
		btVenta.setActionCommand("v");
		btVenta.addActionListener(this);
		btVenta.setFocusable(false);
		contenidor.add(btVenta);

		JButton btMarge = new JButton("Marge");
		btMarge.setActionCommand("m");
		btMarge.addActionListener(this);
		btMarge.setFocusable(false);
		contenidor.add(btMarge);

		JButton bt7 = new JButton("7");
		bt7.setActionCommand("7");
		bt7.addActionListener(this);
		bt7.setFocusable(false);
		contenidor.add(bt7);

		JButton bt8 = new JButton("8");
		bt8.setActionCommand("8");
		bt8.addActionListener(this);
		bt8.setFocusable(false);
		contenidor.add(bt8);

		JButton bt9 = new JButton("9");
		bt9.setActionCommand("9");
		bt9.addActionListener(this);
		bt9.setFocusable(false);
		contenidor.add(bt9);

		JButton btDiv = new JButton("/");
		btDiv.setActionCommand("/");
		btDiv.addActionListener(this);
		btDiv.setFocusable(false);
		contenidor.add(btDiv);
		
		JButton bt4 = new JButton("4");
		bt4.setActionCommand("4");
		bt4.addActionListener(this);
		bt4.setFocusable(false);
		contenidor.add(bt4);

		JButton bt5 = new JButton("5");
		bt5.setActionCommand("5");
		bt5.addActionListener(this);
		bt5.setFocusable(false);
		contenidor.add(bt5);

		JButton bt6 = new JButton("6");
		bt6.setActionCommand("6");
		bt6.addActionListener(this);
		bt6.setFocusable(false);
		contenidor.add(bt6);

		JButton btMult = new JButton("x");
		btMult.setActionCommand("*");
		btMult.addActionListener(this);
		btMult.setFocusable(false);
		contenidor.add(btMult);
		
		JButton bt1 = new JButton("1");
		bt1.setActionCommand("1");
		bt1.addActionListener(this);
		bt1.setFocusable(false);
		contenidor.add(bt1);

		JButton bt2 = new JButton("2");
		bt2.setActionCommand("2");
		bt2.addActionListener(this);
		bt2.setFocusable(false);
		bt2.setFocusable(false);
		contenidor.add(bt2);

		JButton bt3 = new JButton("3");
		bt3.setActionCommand("3");
		bt3.addActionListener(this);
		bt3.setFocusable(false);
		contenidor.add(bt3);
		
		JButton btResta = new JButton("-");
		btResta.setActionCommand("-");
		btResta.addActionListener(this);
		btResta.setFocusable(false);
		contenidor.add(btResta);
		
		JButton bt0 = new JButton("0");
		bt0.setActionCommand("0");
		bt0.addActionListener(this);
		bt0.setFocusable(false);
		contenidor.add(bt0);

		JButton btComa = new JButton(",");
		btComa.setActionCommand(".");
		btComa.addActionListener(this);
		btComa.setFocusable(false);
		contenidor.add(btComa);

		JButton btIgual = new JButton("=");
		btIgual.setActionCommand("=");
		btIgual.addActionListener(this);
		btIgual.setFocusable(false);
		contenidor.add(btIgual);

		JButton btSuma = new JButton("+");
		btSuma.setActionCommand("+");
		btSuma.addActionListener(this);
		btSuma.setFocusable(false);
		contenidor.add(btSuma);
		
		// layout
		SpringLayout layout = new SpringLayout();
		contenidor.setLayout(layout);
		// lbCost
		layout.putConstraint(SpringLayout.EAST, lbCost, 50, SpringLayout.WEST, contenidor);
		layout.putConstraint(SpringLayout.NORTH, lbCost, 15, SpringLayout.NORTH, contenidor);
		//tfCost
		layout.putConstraint(SpringLayout.WEST, tfCost, 8, SpringLayout.EAST, lbCost);
		layout.putConstraint(SpringLayout.SOUTH, tfCost, 0, SpringLayout.SOUTH, lbCost);
		layout.putConstraint(SpringLayout.EAST, tfCost, -5, SpringLayout.EAST, contenidor);
		// lbVenta
		layout.putConstraint(SpringLayout.EAST, lbVenta, 0, SpringLayout.EAST, lbCost);
		layout.putConstraint(SpringLayout.NORTH, lbVenta, 5, SpringLayout.SOUTH, lbCost);
		// tfVenta
		layout.putConstraint(SpringLayout.WEST, tfVenta, 8, SpringLayout.EAST, lbVenta);
		layout.putConstraint(SpringLayout.SOUTH, tfVenta, 0, SpringLayout.SOUTH, lbVenta);
		layout.putConstraint(SpringLayout.EAST, tfVenta, -5, SpringLayout.EAST, contenidor);
		// lbMarge
		layout.putConstraint(SpringLayout.EAST, lbMarge, 0, SpringLayout.EAST, lbVenta);
		layout.putConstraint(SpringLayout.NORTH, lbMarge, 5, SpringLayout.SOUTH, lbVenta);
		// tfMarge
		layout.putConstraint(SpringLayout.WEST, tfMarge, 8, SpringLayout.EAST, lbMarge);
		layout.putConstraint(SpringLayout.SOUTH, tfMarge, 0, SpringLayout.SOUTH, lbMarge);
		layout.putConstraint(SpringLayout.EAST, tfMarge, -5, SpringLayout.EAST, contenidor);
		// btCost
		layout.putConstraint(SpringLayout.WEST, btCost, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, btCost, 10, SpringLayout.SOUTH, lbMarge);
		// btVenta
		layout.putConstraint(SpringLayout.WEST, btVenta, 5, SpringLayout.EAST, btCost);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btVenta, 0, SpringLayout.VERTICAL_CENTER, btCost);
		// btMarge
		layout.putConstraint(SpringLayout.WEST, btMarge, 5, SpringLayout.EAST, btVenta);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btMarge, 0, SpringLayout.VERTICAL_CENTER, btCost);
		// bt7
		layout.putConstraint(SpringLayout.WEST, bt7, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, bt7, 10, SpringLayout.SOUTH, btCost);
		// bt8
		layout.putConstraint(SpringLayout.WEST, bt8, 5, SpringLayout.EAST, bt7);
		layout.putConstraint(SpringLayout.SOUTH, bt8, 0, SpringLayout.SOUTH, bt7);
		// bt9
		layout.putConstraint(SpringLayout.WEST, bt9, 5, SpringLayout.EAST, bt8);
		layout.putConstraint(SpringLayout.SOUTH, bt9, 0, SpringLayout.SOUTH, bt8);
		// btDiv
		layout.putConstraint(SpringLayout.WEST, btDiv, 5, SpringLayout.EAST, bt9);
		layout.putConstraint(SpringLayout.SOUTH, btDiv, 0, SpringLayout.SOUTH, bt9);
		layout.putConstraint(SpringLayout.WIDTH, btDiv, 0, SpringLayout.WIDTH, bt9);
		// bt4	
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bt4, 0, SpringLayout.HORIZONTAL_CENTER, bt7);
		layout.putConstraint(SpringLayout.NORTH, bt4, 10, SpringLayout.SOUTH, bt7);
		// bt5
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bt5, 0, SpringLayout.HORIZONTAL_CENTER, bt8);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, bt5, 0, SpringLayout.VERTICAL_CENTER, bt4);
		// bt6
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bt6, 0, SpringLayout.HORIZONTAL_CENTER, bt9);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, bt6, 0, SpringLayout.VERTICAL_CENTER, bt4);		
		// btMult
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btMult, 0, SpringLayout.HORIZONTAL_CENTER, btDiv);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btMult, 0, SpringLayout.VERTICAL_CENTER, bt4);		
		// bt1
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bt1, 0, SpringLayout.HORIZONTAL_CENTER, bt7);
		layout.putConstraint(SpringLayout.NORTH, bt1, 10, SpringLayout.SOUTH, bt4);
		// bt2
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bt2, 0, SpringLayout.HORIZONTAL_CENTER, bt8);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, bt2, 0, SpringLayout.VERTICAL_CENTER, bt1);
		// bt3
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bt3, 0, SpringLayout.HORIZONTAL_CENTER, bt9);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, bt3, 0, SpringLayout.VERTICAL_CENTER, bt1);		
		// btResta
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btResta, 0, SpringLayout.HORIZONTAL_CENTER, btDiv);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btResta, 0, SpringLayout.VERTICAL_CENTER, bt1);		
		layout.putConstraint(SpringLayout.WIDTH, btResta, 0, SpringLayout.WIDTH, bt1);
		// bt0
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bt0, 0, SpringLayout.HORIZONTAL_CENTER, bt7);
		layout.putConstraint(SpringLayout.NORTH, bt0, 10, SpringLayout.SOUTH, bt1);
		// btComa
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btComa, 0, SpringLayout.HORIZONTAL_CENTER, bt8);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btComa, 0, SpringLayout.VERTICAL_CENTER, bt0);
		layout.putConstraint(SpringLayout.WIDTH, btComa, 0, SpringLayout.WIDTH, bt0);
		// btIgual
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btIgual, 0, SpringLayout.HORIZONTAL_CENTER, bt9);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btIgual, 0, SpringLayout.VERTICAL_CENTER, bt0);		
		// btSuma
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btSuma, 0, SpringLayout.HORIZONTAL_CENTER, btDiv);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btSuma, 0, SpringLayout.VERTICAL_CENTER, bt0);		
		// fi formulari
		layout.putConstraint(SpringLayout.EAST, contenidor, 5, SpringLayout.EAST, btMarge);
		layout.putConstraint(SpringLayout.SOUTH, contenidor, 5, SpringLayout.SOUTH, bt0);

		calculaMarge = new CalculaMarge(tfCost,tfVenta,tfMarge);
	}
	
	public static void main(String[] args) {	
		JFrame finestre = new Marge();
		finestre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestre.pack();
		finestre.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		char tecla=e.getActionCommand().charAt(0);
		switch (tecla) {
		case '0': case'1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case'.':
			enviaCalculadora(tecla);
			JTextField tfActiu = (JTextField) this.getFocusOwner();
			tfActiu.replaceSelection(String.valueOf(tecla));
			break;
		case 'c': case 'v': case 'm': case Calculadora.ENTER: case '=':
			enviaCalculadora(tecla);
			break;
		}
	}
	
	@Override
	public void keyPressed (KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) { 
		char tecla;
		tecla = e.getKeyChar();
		switch (tecla) {
		case 'c': case 'v': case 'm':
			e.consume();
		default:
			enviaCalculadora(tecla);		
		}
	}
	
	// revisa entrada, calcula casella, y esborra casella si es nova entrada
	void enviaCalculadora(char tecla) {
		JTextField tfActiu = (JTextField) this.getFocusOwner();
		switch (tecla) {
			case Calculadora.ENTER: case '=':
				calculadora.calcula(tfActiu);
				novaEntrada=true;
				break;
			case 'c': case 'v': case 'm':
				calculaMarge.calcula(tecla);
				novaEntrada=true;
				break;
			case '*': case '/': case '+': case '-':
				novaEntrada=false;
				break;
			case 8: case 127:
				novaEntrada=false;
				break;
			default:
				if (novaEntrada) {
					tfActiu.setText("");
					novaEntrada=false;
				}
				System.out.printf("c:%d\n", Integer.valueOf(tecla));
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// apunta el canvi de casella
		novaEntrada=true;
	}

	@Override
	public void focusLost(FocusEvent e) {
	}
	
}
