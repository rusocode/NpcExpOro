/**
 * CalculadoraAO Copyright (C) 2020 SilentSoft
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <<a href="https://www.gnu.org/licenses/">https://www.gnu.org/licenses/</a>>.
 */

package com.silentsoft.calculadoraao;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.silentsoft.calculadoraao.views.Principal;

/**
 * @author Ru$o
 * 
 */

/* La legibilidad y la longitud del codigo son menos importantes que la experiencia facil y rapida del usuario final. */
public class Main {

	public static void main(String[] args) {
		setLAF();
		// La ventana debe hacerse visible en ultimo lugar, para evitar parpadeos, movimientos y cambios de tama�o
		new Principal().setVisible(true);
	}

	private static void setLAF() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al establecer el LookAndFeel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
