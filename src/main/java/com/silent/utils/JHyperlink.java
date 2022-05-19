/**
 * NpcExpOro Copyright (C) Silent
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <<a href="https://www.gnu.org/licenses/">https://www.gnu.org/licenses/</a>>.
 */

package com.silent.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author Ruso
 */

public class JHyperlink extends JLabel {

	private static final long serialVersionUID = 1L;

	private String url;

	public JHyperlink(String txt) {
		// Le pasa el texto (nombre) al constructor de la clase JLabel
		super(txt);

		setForeground(Color.BLUE.darker());
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Le agrega un escuchador al JLabel de tipo MouseListener para hacerlo clickable
		addMouseListener(new Oyente());

	}

	public void setURL(String url) {
		this.url = url;
	}

	private class Oyente extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
			/* Comprueba si esta clase es compatible con la plataforma actual. Si es compatible, usa getDesktop() para recuperar una
			 * instancia. */
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			// Si la accion especificada es compatible con la plataforma actual, entonces...
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new URI(url));
				} catch (URISyntaxException
						/* Si la cadena dada viola RFC 2396, como se incrementa por las desviaciones anteriores. */ |
						 NullPointerException
						/* Si la cadena es nula. */ | IOException e) {
					JOptionPane.showMessageDialog(JHyperlink.this, "No se pudo abrir el hipervínculo. Error: " + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		// Cuando el mouse pasa sobre el hyperlink
		public void mouseEntered(MouseEvent evt) {
			setForeground(Color.BLUE.brighter());
		}

		// Cuando el mouse sale del hyperlink
		public void mouseExited(MouseEvent evt) {
			setForeground(Color.BLUE.darker());
		}

	}

}
