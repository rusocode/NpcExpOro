package com.silentsoft;

import javax.swing.UIManager;

public class Launcher {

	private static void setLAF() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Error al establecer el LookAndFeel: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		setLAF();
		// La ventana debe hacerse visible en ultimo lugar, para evitar parpadeos, movimientos y cambios de tama�o
		new Calculadora().setVisible(true);
	}

}
