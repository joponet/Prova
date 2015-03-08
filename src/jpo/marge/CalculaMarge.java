package jpo.marge;

import javax.swing.JTextField;

public class CalculaMarge {
	
	JTextField tfCost;
	JTextField tfVenta;
	JTextField tfMarge;
	
	CalculaMarge (JTextField argCost, JTextField argVenta, JTextField argMarge) {
		tfCost=argCost;
		tfVenta=argVenta;
		tfMarge=argMarge;
	}
	
	void calcula (char tecla) {
		Float cost=0f;
		Float venta=0f;
		Float marge=0f;
		if (tecla!='c') cost=Float.valueOf(tfCost.getText().replace(",","."));
		if (tecla!='v') venta=Float.valueOf(tfVenta.getText().replace(",","."));
		if (tecla!='m') marge=Float.valueOf(tfMarge.getText().replace(",","."))/100;
		System.out.printf("c:%s v:%s m:%s\n", tfCost.getText(), tfVenta.getText(), tfMarge.getText());
		switch (tecla) {
		case 'c':
			cost=venta*(1-marge);
			tfCost.setText(String.format("%.2f",cost));
			break;
		case 'v':
			venta=cost/(1-marge);
			tfVenta.setText(String.format("%.2f",venta));
			break;
		case 'm':
			marge=(1-(cost/venta))*100;
			tfMarge.setText(String.format("%.2f", marge));
			break;
		}
	}
}
