package jpo.marge;

import javax.swing.JTextField;

public class Calculadora {
	
	static final char ENTER = 0x0A;
	float valor1=0;
	float valor2=0;
	float resultat=0;
	String strValor1="";
	String strValor2="";
	char operacio=ENTER;
	boolean calculat=false;
			
	// Entra un valor (exemple en canvi de casella)
//	public void entraValor (String strValor) {
//		if (!strValor.isEmpty()) resultat=Float.valueOf(strValor.replace(",","."));
//		else resultat=0;
//		operacio=ENTER;
//		calculat=true;
//	}
	
	
	// Entra valors a calculadora. Retorna String si cal actualitzar texte, "" si no cal.
	public void entra(char digit) {
//		String actualitza="";
		if (digit==',') digit='.';
		switch (digit) {
			case '0': case'1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case'.':
				// entra un valor nou
				if (calculat) {
					strValor1="";
					strValor2="";
//					actualitza="c"; // indica per esborrar la casella
					calculat=false;
				}
				if (operacio == ENTER) {
					strValor1 = strValor1.concat(String.valueOf(digit));
				}
				else {
					strValor2 = strValor2.concat(String.valueOf(digit));					
				}
				break;
			case '*': case '/': case '+': case '-':
				// operacio sobre resultat anterior
				if (calculat) {
					strValor1=String.valueOf(resultat);
					strValor2="";
					calculat=false;
				}
				operacio=digit;
				break;
			case ENTER: case'=':
				valor1=Float.valueOf(strValor1);
				if (operacio==ENTER) resultat=valor1;
				else valor2=Float.valueOf(strValor2);
				switch (operacio) {
					case '*': resultat=valor1*valor2; break;
					case '/': resultat=valor1/valor2; break;
					case '+': resultat=valor1+valor2; break;
					case '-': resultat=valor1-valor2; break;
				}
				operacio=ENTER;
				calculat=true;
				break;
		}
//		return actualitza;
	}
	
//	public float resultat () {
//		return resultat;
//	}
	
	public void calcula (JTextField tfActiu) {
		String cadena = tfActiu.getText();
		for (int i=0; i<cadena.length(); i++) {
			entra(cadena.charAt(i));
		}
		entra(ENTER);
		tfActiu.setText(String.format("%.2f", resultat));
	}
	
//	String valorActual () {
//		String valor="";
//		if (calculat) valor=String.format("%.2f", resultat);
//		else if (strValor2.isEmpty()) valor=strValor1;
//		else valor=strValor2;
//		return valor;
//	}
}
