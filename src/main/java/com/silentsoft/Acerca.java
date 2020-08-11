package com.silentsoft;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

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
		String str = "Calculadora multiplataforma basada en ImperiumAO que calcula el porcentaje"
				+ "\nde experiencia que otorga el NPC, la cantidad de NPCs a matar para pasar de" + "\nnivel y el oro total. " + "\n\nVersión: 1.0"
				+ "\nCopyright © 2020 Juan Debenedetti" + "\nAll rights reserved.";
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
