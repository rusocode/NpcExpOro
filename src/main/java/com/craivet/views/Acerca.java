/**
 * NpcExpOro Copyright (C) Craivet
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

package com.craivet.views;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.*;

import com.craivet.utils.JHyperlink;

import net.miginfocom.swing.MigLayout;

/**
 * @author Ruso
 */

public class Acerca extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton btnOk;

	private static Acerca instance;

	public static Acerca getInstance() {
		if (instance == null) instance = new Acerca();
		return instance;
	}

	private Acerca() {
		setTitle("Acerca de NpcExpOro");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initialize();
	}

	private void initialize() {
		setLayout(new MigLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("insets 0", "[][grow, right]"));

		JTextArea txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setBackground(SystemColor.menu);
		txtArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		String str = "Calculadora multiplataforma basada en Argentum Online que calcula el porcentaje\n" +
				"de experiencia que otorga el NPC, la cantidad de NPCs a matar y el oro total.\n\n" +
				"v1.1.7\n" +
				"Copyright © Craivet";
		txtArea.append(str);
		add(txtArea, "wrap");

		btnOk = new JButton("OK");
		btnOk.setFocusable(false);
		btnOk.addActionListener(evt -> {
			if (evt.getSource() == btnOk)
				dispose(); // Cierra la ventana actual y libera los recursos que esa ventana haya estado ocupando
		});
		panel.add(btnOk);

		JHyperlink link = new JHyperlink("github.com/NpcExpOro");
		link.setURL("https://github.com/rusocode/NpcExpOro");
		panel.add(link);

		add(panel, "spanx, growx");

		pack();
		setLocationRelativeTo(null);

	}

}
