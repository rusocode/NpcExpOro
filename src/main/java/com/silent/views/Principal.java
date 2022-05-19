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

package com.silent.views;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

import com.silent.utils.IniFile;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * @author Ruso
 */

public class Principal extends JFrame {

	private static final long serialVersionUID = -2225328319053890966L;

	private JTextField txtExpPJ, txtExpNPC, txtVidaNPC, txtOroNPC, txtPorcentajeExp, txtTotalNPC, txtTotalOro;
	private JComboBox<String> cbNivel, cbNPC, cbGrupo;
	private JToggleButton tbtnGrupo, tbtnRenegado, tbtnExpX2, tbtnBonificador, tbtnOroX2, tbtn10, tbtn25;
	private JButton btnCalcular, btnAcerca;
	private JRadioButton mayor, menor, relacion, abc;

	private final DecimalFormat formatoPorcentaje;

	private IniFile ini;

	// PJs
	private String[] nivel, expPJ;
	// NPCs
	private String[] npc, vida, expNPC, oro;

	private static final short BONUS_10 = 10;
	private static final short BONUS_25 = 25;
	private static final short BONIFICADOR_EXP = 10;
	private static final short BONIFICADOR_ORO = 5;

	public Principal() {
		super("NpcExpOro");
		setResizable(false);
		setIconImage(getImagen());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formatoPorcentaje = new DecimalFormat("##.##");
		loadPJs();
		loadNPCs();
		initialize();
	}

	private void loadPJs() {

		// Crea un objeto de la clase IniFile
		ini = new IniFile();

		// Localiza el archivo dentro de la ruta de clases y lo asigna al flujo de entrada
		InputStream file = this.getClass().getClassLoader().getResourceAsStream("PJs.dat");

		// Carga el dat
		ini.load(file);

		// Almacena los valores de las claves especificadas dentro de un array de String
		nivel = ini.getValues("Nivel");
		expPJ = ini.getValues("Exp");

	}

	private void loadNPCs() {

		ini = new IniFile();

		InputStream file = this.getClass().getClassLoader().getResourceAsStream("NPCs.dat");

		ini.load(file);

		npc = ini.getValues("Nombre");
		vida = ini.getValues("Vida");
		expNPC = ini.getValues("Exp");
		oro = ini.getValues("Oro");

	}

	/* Comprueba si la ruta de la imagen existe. En caso de que no la encuentre, la aplicacion sigue con su ejecucion y no
	 * se detiene.
	 *
	 * La razon de este metodo, se debe a que al generar el .jar ejecutable DESDE Eclipse (en mi caso), podria llegar a
	 * generar un error en la ruta de clases de la imagen, ya que estamos implementando maven para la estructura de carpetas
	 * y por convenio es recomendable crear el .jar desde la consola con un "mvn clean compile assembly:single". Este
	 * comando, crea el ejecutable incluyendo las dependencias especificadas en el archivo pom.xml y lo alamacena dentro de
	 * la carpeta target.
	 *
	 * En conclusion, con esta funcion se evita un NullPointerException. */
	private Image getImagen() {

		// Cargador de clases para este objeto
		ClassLoader classLoader = this.getClass().getClassLoader();
		Image image = null;
		ImageIcon imageIcon;

		// Carga la ruta de la imagen desde la raiz del .classpath y la almacena en una variable de tipo URL
		URL url = classLoader.getResource("logo.png");

		// Evita un NullPointerException en caso de que la ruta de la imagen no se ecuentre
		if (url != null) {
			imageIcon = new ImageIcon(url);
			image = imageIcon.getImage();
		} else
			JOptionPane.showMessageDialog(null, "No se pudo encontrar la imagen [" + "logo.png" + "] en \"" + System.getProperty("user.dir") + "\"",
					"Error", JOptionPane.ERROR_MESSAGE);

		return image;
	}

	private void initialize() {

		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);

		btnCalcular = new JButton("CALCULAR EXPERIENCIA Y ORO");
		btnCalcular.addActionListener(new Oyente());
		btnCalcular.setFocusable(false);
		btnCalcular.setEnabled(false);

		add(getAjustesPanel(), "spanx, growx");
		add(getPJPanel(), "growy");
		add(getNPCPanel());
		add(getCalculadoraPanel(), "spanx, growx");
		add(getBotonesPanel(), "spanx, growx");

		/* Comprime la ventana al tamaño del componente mas grande, garantizando que el marco tenga el tamaño minimo para
		 * mostrar los componentes que contiene. */
		pack();
		setLocationRelativeTo(null);

	}

	private JPanel getAjustesPanel() {
		JPanel panel = getPanel("Ajustes");
		panel.setLayout(new MigLayout("fill"));

		tbtnGrupo = new JToggleButton("¿Estás en grupo?");
		tbtnGrupo.addActionListener(new Oyente());
		tbtnGrupo.setFocusable(false);

		tbtnRenegado = new JToggleButton("¿Sos renegado?");
		tbtnRenegado.setToolTipText("Los renegados pierden un 10% de la experiencia total al formar grupos.");
		tbtnRenegado.setFocusable(false);
		tbtnRenegado.setEnabled(false);

		JLabel lblGrupo = new JLabel("¿Cuántos son?");

		cbGrupo = new JComboBox<>(new String[]{"2", "3", "4", "5"});
		cbGrupo.setSelectedIndex(-1);
		cbGrupo.setFocusable(false);
		cbGrupo.setEnabled(false);

		JPanel panelBotonesExp = new JPanel();
		panelBotonesExp.setLayout(new MigLayout("fill, insets 0"));

		tbtnExpX2 = new JToggleButton("exp x2");
		tbtnExpX2.addActionListener(new Oyente());
		tbtnExpX2.setFocusable(false);

		tbtnOroX2 = new JToggleButton("oro x2");
		tbtnOroX2.addActionListener(new Oyente());
		tbtnOroX2.setFocusable(false);

		tbtnBonificador = new JToggleButton("Bonificador");
		tbtnBonificador.setToolTipText("Bonus de +10% en experiencia y +5% en oro.");
		tbtnBonificador.addActionListener(new Oyente());
		tbtnBonificador.setFocusable(false);

		tbtn10 = new JToggleButton("+10%");
		tbtn10.addActionListener(new Oyente());
		tbtn10.setFocusable(false);

		tbtn25 = new JToggleButton("+25%");
		tbtn25.addActionListener(new Oyente());
		tbtn25.setFocusable(false);

		panelBotonesExp.add(tbtnExpX2, "sg 2");
		panelBotonesExp.add(tbtnOroX2, "sg 2");
		panelBotonesExp.add(tbtnBonificador);
		panelBotonesExp.add(tbtn10, "sg 2");
		panelBotonesExp.add(tbtn25, "sg 2, growx");

		panel.add(tbtnGrupo, "sg 1");
		panel.add(tbtnRenegado, "sg 1");
		panel.add(lblGrupo);
		panel.add(cbGrupo, "growx, wrap");
		panel.add(panelBotonesExp, "spanx, growx");

		return panel;
	}

	private JPanel getPJPanel() {
		JPanel panel = getPanel("PJ");
		panel.setLayout(new MigLayout("wrap 2"));

		JLabel lblNivel = new JLabel("Nivel:");

		cbNivel = new JComboBox<>(nivel);
		cbNivel.addActionListener(new Oyente());
		cbNivel.setFocusable(false);
		cbNivel.setSelectedItem(null);

		JLabel lblExpPJ = new JLabel("Experiencia:");

		// Obviamente si le indico el tamaño al campo de texto, entonces la ventana no tiene que ser redimensionable
		txtExpPJ = new JTextField(7);
		txtExpPJ.setEditable(false);

		panel.add(lblNivel, "right");
		panel.add(cbNivel, "growx");
		panel.add(lblExpPJ, "right");
		panel.add(txtExpPJ);

		return panel;
	}

	private JPanel getNPCPanel() {
		JPanel panel = getPanel("NPC");
		panel.setLayout(new MigLayout("wrap 2", "[right][fill]"));

		JPanel panelRadio = new JPanel();
		panelRadio.setLayout(new MigLayout("insets 0"));
		ButtonGroup grupo = new ButtonGroup();
		abc = new JRadioButton("abc");
		abc.addActionListener(new Oyente());
		abc.setFont(new Font("Consolas", Font.PLAIN, 11));
		abc.setFocusable(false);
		relacion = new JRadioButton("vida/exp");
		relacion.addActionListener(new Oyente());
		relacion.setFont(new Font("Consolas", Font.PLAIN, 11));
		relacion.setFocusable(false);
		mayor = new JRadioButton("+exp");
		mayor.addActionListener(new Oyente());
		mayor.setFont(new Font("Consolas", Font.PLAIN, 11));
		mayor.setFocusable(false);
		menor = new JRadioButton("-exp");
		menor.addActionListener(new Oyente());
		menor.setFont(new Font("Consolas", Font.PLAIN, 11));
		menor.setFocusable(false);

		grupo.add(abc);
		grupo.add(relacion);
		grupo.add(mayor);
		grupo.add(menor);

		panelRadio.add(abc);
		panelRadio.add(relacion);
		panelRadio.add(mayor);
		panelRadio.add(menor);

		panel.add(panelRadio, "spanx, wrap");

		JLabel lblNPC = new JLabel("Nombre:");
		cbNPC = new JComboBox<>(npc);
		AutoCompleteDecorator.decorate(cbNPC);
		cbNPC.addActionListener(new Oyente());
		cbNPC.setSelectedItem(null);

		JLabel lblVidaNPC = new JLabel("Vida:");
		txtVidaNPC = new JTextField();
		txtVidaNPC.setEditable(false);
		JLabel lblExpNPC = new JLabel("Exp:");
		txtExpNPC = new JTextField();
		txtExpNPC.setEditable(false);
		JLabel lblOroNPC = new JLabel("Oro:");
		txtOroNPC = new JTextField();
		txtOroNPC.setEditable(false);

		panel.add(lblNPC);
		panel.add(cbNPC);
		panel.add(lblVidaNPC);
		panel.add(txtVidaNPC);
		panel.add(lblExpNPC);
		panel.add(txtExpNPC);
		panel.add(lblOroNPC);
		panel.add(txtOroNPC);

		return panel;

	}

	private JPanel getCalculadoraPanel() {
		JPanel panel = getPanel("Calculadora");
		panel.setLayout(new MigLayout("wrap 2, fill", "[grow 0, right][fill]"));

		JLabel lblPorcentajeExp = new JLabel("Porcentaje de experiencia que otorga el NPC:");
		txtPorcentajeExp = new JTextField();
		txtPorcentajeExp.setEditable(false);
		JLabel lblTotNpcMatar = new JLabel("Total de NPCs a matar para subir de nivel:");
		txtTotalNPC = new JTextField();
		txtTotalNPC.setEditable(false);
		JLabel lblTotOro = new JLabel("Total de oro a conseguir:");
		txtTotalOro = new JTextField();
		txtTotalOro.setEditable(false);

		panel.add(lblPorcentajeExp);
		panel.add(txtPorcentajeExp);
		panel.add(lblTotNpcMatar);
		panel.add(txtTotalNPC);
		panel.add(lblTotOro);
		panel.add(txtTotalOro);
		panel.add(btnCalcular, "spanx, growx");

		return panel;
	}

	private JPanel getBotonesPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new MigLayout("insets 0", "[][grow, right]"));

		panel.add(new JLabel("by Ruso"));

		btnAcerca = new JButton("Acerca de...");
		btnAcerca.setFocusable(false);
		btnAcerca.addActionListener(evt -> {
			if (evt.getSource() == btnAcerca) Acerca.getInstance().setVisible(true);
		});

		panel.add(btnAcerca);

		return panel;
	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	// FIXME Crear una clase aparte para la clase interna
	private class Oyente implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) {

			if (evt.getSource() == cbNivel || evt.getSource() == cbNPC) accion0();
			if (evt.getSource() == tbtnGrupo) accion1();
			if (evt.getSource() == tbtn10) accion2();
			if (evt.getSource() == tbtn25) accion3();
			if (evt.getSource() == cbNivel) getExpPJ();
			if (evt.getSource() == cbNPC) getDatosNPC();
			if (evt.getSource() == mayor) sortDesc();
			if (evt.getSource() == menor) sortAsc();
			if (evt.getSource() == relacion) sortRelacion();
			if (evt.getSource() == abc) sortABC();
			if (evt.getSource() == btnCalcular) calcularTotal();

		}

		// Habilita el boton btnCalcular si los combos PJ y NPC tienen seleccionado un item
		private void accion0() {
			btnCalcular.setEnabled(cbNivel.getSelectedItem() != null && cbNPC.getSelectedItem() != null);
		}

		private void accion1() {
			if (tbtnGrupo.isSelected()) {
				tbtnRenegado.setEnabled(true);
				cbGrupo.setEnabled(true);
				cbGrupo.setSelectedIndex(0);
			} else {
				if (tbtnRenegado.isSelected()) tbtnRenegado.setSelected(false);
				tbtnRenegado.setEnabled(false);
				cbGrupo.setEnabled(false);
				cbGrupo.setSelectedIndex(-1);
			}
		}

		private void accion2() {
			if (tbtn25.isSelected()) tbtn25.setSelected(false);
		}

		private void accion3() {
			if (tbtn10.isSelected()) tbtn10.setSelected(false);
		}

		private void getExpPJ() {
			if (cbNivel.getSelectedIndex() != -1) {
				for (int i = 0; i < nivel.length; i++) {
					if (Objects.equals(cbNivel.getSelectedItem(), nivel[i])) {
						txtExpPJ.setText("" + expPJ[i]);
						break;
					}
				}
			}
		}

		private void getDatosNPC() {
			if (cbNPC.getSelectedIndex() != -1) {
				for (int i = 0; i < npc.length; i++) {
					if (Objects.equals(cbNPC.getSelectedItem(), npc[i])) {
						txtVidaNPC.setText("" + vida[i]);
						txtExpNPC.setText("" + expNPC[i]);
						txtOroNPC.setText("" + oro[i]);
						break;
					}
				}
			}
		}

		// Ordena los NPCs de mayor a menor exp
		private void sortDesc() {
			String auxNombre;
			int auxVida, auxExp, auxOro;

			cbNPC.removeAllItems();

			for (int i = 0; i < npc.length - 1; i++) {
				for (int j = 0; j < npc.length - 1 - i; j++) {

					int expX = Integer.parseInt(expNPC[j]);
					int expY = Integer.parseInt(expNPC[j + 1]);

					if (expX < expY) {
						auxNombre = npc[j + 1];
						npc[j + 1] = npc[j];
						npc[j] = auxNombre;

						auxVida = Integer.parseInt(vida[j + 1]);
						vida[j + 1] = vida[j];
						vida[j] = "" + auxVida;

						auxExp = Integer.parseInt(expNPC[j + 1]);
						expNPC[j + 1] = expNPC[j];
						expNPC[j] = "" + auxExp;

						auxOro = Integer.parseInt(oro[j + 1]);
						oro[j + 1] = oro[j];
						oro[j] = "" + auxOro;
					}

				}
			}

			for (String s : npc) cbNPC.addItem("" + s);

		}

		// Ordena los NPCs de menor a mayor exp
		private void sortAsc() {
			String auxNombre;
			int auxVida, auxExp, auxOro;

			cbNPC.removeAllItems();

			// El primer for controlada cada item y el segundo el intercambio
			for (int i = 0; i < npc.length - 1; i++) {
				/* Se le resta - 1 al tamaño del array ya que el limite se llega en la suma de j + 1, evitando asi tambien un
				 * ArrayIndexOutOfBoundsException. */
				for (int j = 0; j < npc.length - 1 - i; j++) {

					int expX = Integer.parseInt(expNPC[j]);
					int expY = Integer.parseInt(expNPC[j + 1]);

					// Si la exp de X es mayor a la exp de Y, entonces se intercambia el nombre, la vida, la exp y el oro
					if (expX > expY) {
						auxNombre = npc[j + 1];
						npc[j + 1] = npc[j];
						npc[j] = auxNombre;

						auxVida = Integer.parseInt(vida[j + 1]);
						vida[j + 1] = vida[j];
						vida[j] = "" + auxVida;

						auxExp = Integer.parseInt(expNPC[j + 1]);
						expNPC[j + 1] = expNPC[j];
						expNPC[j] = "" + auxExp;

						auxOro = Integer.parseInt(oro[j + 1]);
						oro[j + 1] = oro[j];
						oro[j] = "" + auxOro;
					}

				}
			}

			for (String s : npc) cbNPC.addItem("" + s);

		}

		// Ordena los NPCs que mejor rinden en cuanto a la relacion que tienen entre vida y exp
		private void sortRelacion() {
			String auxNombre;
			int auxVida, auxExp, auxOro;
			double relacionX, relacionY;

			cbNPC.removeAllItems();

			for (int i = 0; i < npc.length - 1; i++) {
				for (int j = 0; j < npc.length - 1 - i; j++) {

					int vidaX = Integer.parseInt(vida[j]);
					int expX = Integer.parseInt(expNPC[j]);
					int vidaY = Integer.parseInt(vida[j + 1]);
					int expY = Integer.parseInt(expNPC[j + 1]);

					// Calcula la relacion del npc X y del npc Y, con respecto a la vida y exp de cada uno
					relacionX = (double) vidaX / expX;
					relacionY = (double) vidaY / expY;

					/* Compara ambas relaciones, y si X tiene una peor (mucha diferencia entre vida y exp) relacion con respecto a Y,
					 * entonces se intercambian los items del array. */
					if (relacionX > relacionY) {
						auxNombre = npc[j + 1];
						npc[j + 1] = npc[j];
						npc[j] = auxNombre;

						auxVida = Integer.parseInt(vida[j + 1]);
						vida[j + 1] = vida[j];
						vida[j] = "" + auxVida;

						auxExp = Integer.parseInt(expNPC[j + 1]);
						expNPC[j + 1] = expNPC[j];
						expNPC[j] = "" + auxExp;

						auxOro = Integer.parseInt(oro[j + 1]);
						oro[j + 1] = oro[j];
						oro[j] = "" + auxOro;
					}

				}
			}

			for (String s : npc) cbNPC.addItem("" + s);

		}

		// Ordena los NPCs alfabeticamente
		private void sortABC() {
			ArrayList<String> nombres = new ArrayList<>();

			// Agrega los nombres del array de objetos al array de strings
			for (String s : npc) nombres.add("" + s);

			// Ordena los nombres alfabeticamente
			Collections.sort(nombres);

			// Limpia el JComboBox viejo
			cbNPC.removeAllItems();

			// Agrega los nombres ordenados al JComboBox
			for (String nombre : nombres) cbNPC.addItem(nombre);

		}

		private void calcularTotal() {

			double expPJ = (!txtExpPJ.getText().isEmpty()) ? Integer.parseInt(txtExpPJ.getText()) : 0;
			double expNPC = (!txtExpNPC.getText().isEmpty()) ? Integer.parseInt(txtExpNPC.getText()) : 0;
			int oroNPC = (!txtOroNPC.getText().isEmpty()) ? Integer.parseInt(txtOroNPC.getText()) : 0;
			double cantidad, cantidadR, expTotal, expTotalR;

			/* Ajuste */
			// Calcula el x2
			if (tbtnExpX2.isSelected()) expNPC *= 2;
			if (tbtnOroX2.isSelected()) oroNPC *= 2;
			// Calcula el bonificador
			if (tbtnBonificador.isSelected()) {
				expNPC += (expNPC * BONIFICADOR_EXP) / 100;
				oroNPC += (oroNPC * BONIFICADOR_ORO) / 100;
			}
			// Calcula el bonus adicional del +10% o +25%
			if (tbtn10.isSelected()) expNPC += (expNPC * BONUS_10) / 100;
			if (tbtn25.isSelected()) expNPC += (expNPC * BONUS_25) / 100;
			/* Ajustes */

			cantidad = expPJ / expNPC; // Cantidad total de NPCs a matar para pasar de nivel
			expTotal = 100 / cantidad; // Porcentaje de experiencia que otorga el NPC

			/* Multiplicar la cantidad de exp que otorga el NPCs por 0.9 es un atajo para sacar la perdida del 10% del total de esa
			 * exp, sin hacer muchos calculos (20 exp * 10% / 100 - el total). Es decir que reducimos la cantidad de exp para que la
			 * perdida tambien se vea reflejada (ademas del % que otorge) en el total de npcs a matar. Es obvio que vamos a tener
			 * que matar mas npcs estando en grupo, y mas si hablamos de renegados. Por eso, al reducir la cantidad de exp en el
			 * npc, mas npcs van a necesitarce para llegar al 100%, menos exp mas npcs a matar. Es logico, no podemos tener una
			 * reduccion de % sobre la exp y matar un npc con un % sin reduccion, ya que estariamos pasando el limite de 100
			 * (calculando la cantidad de npcs * el %).
			 *
			 * Una vez reducida la exp del npc, dividimos la exp del pj por la exp del npc, y obtenemos la cantidad de npcs a matar.
			 * En otras palabras, esa cantidad serian los "pedazitos (npcs)" de la exp del pj en la que se "dividen" por x partes, y
			 * cada parte va a contener un % del total de la exp del npc.
			 *
			 * Para calcular el % de cada parte es necesario dividir 100 por el total, ej: el % de 4,75 es de 20%, ya que sumando
			 * 20% a cada parte de 4.75 llegamos a 100. Para calcular el % de 4,75 se tiene que dividir 100 por 4,75 = 21,05%. */
			cantidadR = expPJ / (expNPC * 0.9);
			expTotalR = 100 / cantidadR; // tot_exp_r = tot_exp * 0.9;

			// Si el PJ esta en grupo, entonces...
			if (tbtnGrupo.isSelected()) {
				// Si el PJ es renegado, entonces...
				if (tbtnRenegado.isSelected()) {
					if (cbGrupo.getSelectedIndex() != -1) {
						if (cbGrupo.getSelectedIndex() == 0) setValores(cantidadR * 2, expTotalR / 2);
						if (cbGrupo.getSelectedIndex() == 1) setValores(cantidadR * 3, expTotalR / 3);
						if (cbGrupo.getSelectedIndex() == 2) setValores(cantidadR * 4, expTotalR / 4);
						if (cbGrupo.getSelectedIndex() == 3) setValores(cantidadR * 5, expTotalR / 5);
					}
				} else {
					if (cbGrupo.getSelectedIndex() != -1) {
						if (cbGrupo.getSelectedIndex() == 0) setValores(cantidad * 2, expTotal / 2);
						if (cbGrupo.getSelectedIndex() == 1) setValores(cantidad * 3, expTotal / 3);
						if (cbGrupo.getSelectedIndex() == 2) setValores(cantidad * 4, expTotal / 4);
						if (cbGrupo.getSelectedIndex() == 3) setValores(cantidad * 5, expTotal / 5);
					}
				}

			} else setValores(cantidad, expTotal);

			// Redondea la cantidad antes de multiplicarla por el oro
			int oroTotal = ((int) Math.ceil(cantidad)) * oroNPC;
			int oroTotalR = ((int) Math.ceil(cantidadR)) * oroNPC;

			if (cbGrupo.getSelectedIndex() == 0) {
				oroTotal /= 2;
				oroTotalR /= 2;
			} else if (cbGrupo.getSelectedIndex() == 1) {
				oroTotal /= 3;
				oroTotalR /= 3;
			} else if (cbGrupo.getSelectedIndex() == 2) {
				oroTotal /= 4;
				oroTotalR /= 4;
			} else if (cbGrupo.getSelectedIndex() == 3) {
				oroTotal /= 5;
				oroTotalR /= 5;
			}

			/* La ventaja de los renegados, es que van a conseguir menos exp pero mas oro, debido a la mayor cantidad de NPCs que
			 * tienen que matar. */
			if (tbtnRenegado.isSelected()) txtTotalOro.setText("" + oroTotalR);
			else txtTotalOro.setText("" + oroTotal);

		}

		private void setValores(double cantidad, double exp) {
			txtTotalNPC.setText("" + (int) Math.ceil(cantidad));
			txtPorcentajeExp.setText(formatoPorcentaje.format(exp) + "%");
		}

	}

}
