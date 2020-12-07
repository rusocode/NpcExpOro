/**
 * 
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
 * <https://www.gnu.org/licenses/>.
 * 
 */

package com.silentsoft.calculadoraao.views;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.silentsoft.calculadoraao.utils.JHyperlink;

import net.miginfocom.swing.MigLayout;

/**
 * @author Ru$o
 * 
 */

public class Acerca extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JTextArea txtArea;
	private JButton btnOk;
	private JHyperlink link;

	private static Acerca instance;

	public static Acerca getInstance() {
		if (instance == null) instance = new Acerca();
		return instance;
	}

	private Acerca() {

		setTitle("Acerca de CalculadoraAO");

		setResizable(false);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initialize();
	}

	private void initialize() {
		setLayout(new MigLayout());

		panel = new JPanel();
		panel.setLayout(new MigLayout("insets 0", "[][grow, right]"));

		txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setBackground(SystemColor.menu);
		txtArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		String str = "Calculadora multiplataforma basada en Argentum Online que calcula el porcentaje"
				+ "\nde experiencia que otorga el NPC, la cantidad de NPCs a matar y el oro total. " + "\n\nVersión: 1.5.0"
				+ "\nCopyright © 2020 SilentSoft" + "\nAll rights reserved";
		txtArea.append(str);
		add(txtArea, "wrap");

		btnOk = new JButton("OK");
		btnOk.setFocusable(false);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == btnOk) dispose(); // Cierra la ventana actual y libera los recursos que esa ventana haya estado ocupando
			}
		});
		panel.add(btnOk);

		link = new JHyperlink("github.com/CalculadoraAO");
		link.setURL("https://github.com/rusocode/CalculadoraAO");
		panel.add(link);

		add(panel, "spanx, growx");

		pack();
		setLocationRelativeTo(null);

	}

}
