/**
 * 
 * CalculadoraAO Copyright (C) 2020 Juan Debenedetti (alias Ru$o)
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <https://www.gnu.org/licenses/>.
 * 
 */

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
