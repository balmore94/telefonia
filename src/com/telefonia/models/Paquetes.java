package com.telefonia.models;

import java.util.ArrayList;
import java.util.List;

public class Paquetes {

	private String pa[];
	List<String[]> listaPq = new ArrayList<>();
	double pre = 0.0;
	int mb = 0, mi = 0, me = 0;

	public List<String[]> llenar(int n) {
		if (n == 1) {
			pre = 3.33;
			mb = 2000;
			mi = 90;
			me = 25;
		} else if (n == 2) {
			pre = 6.66;
			mb = 5000;
			mi = 180;
			me = 50;
		} else if (n == 3) {
			pre = 9.99;
			mb = 10000;
			mi = 300;
			me = 100;
		} else if (n == 4) {
			pre = 13.33;
			mb = 30000;
			mi = 400;
			me = 150;
		}
		llenar1();
		return listaPq;
	}

	public void llenar1() {
		// posicion 0=tipo de servicio
		/*
		 * posicion 1=precio posicion 2=vigencia posicion 3=estado posicion
		 * 4=megas,5=mensajes,6=minutos
		 */
		for (int i = 0; i < 3; i++) {
			pa = new String[7];
			if (i == 0) {
				pa[0] = "1";
			} else if (i == 2) {
				pa[0] = "4";
			} else {
				pa[0] = "2";
			}
			pa[1] = "" + pre;
			pa[2] = "1";
			pa[3] = "1";
			pa[4] = "" + mb;
			pa[5] = "" + me;
			pa[6] = "" + mi;
			System.out.println("datos de iiiiiiiiii---- "+i);
			listaPq.add(pa);
		}

	}
}
