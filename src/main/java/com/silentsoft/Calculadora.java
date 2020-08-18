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

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * @author Ru$o
 * 
 */

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblNivel, lblExpPJ, lblNPC, lblExpNPC, lblVidaNPC, lblOroNPC, lblGrupo, lblPorcentajeExp, lblTotNpcMatar, lblTotOro;
	private JTextField txtExpPJ, txtExpNPC, txtVidaNPC, txtOroNPC, txtPorcentajeExp, txtTotalNPC, txtTotalOro;
	private JComboBox<String> cbNivel, cbNPC, cbGrupo;
	private JToggleButton tbtnPVP, tbtnRPG, tbtnGrupo, tbtnRenegado, tbtnExpX2, tbtnOroX2, tbtn50, tbtn100, tbtn200;
	private JButton btnCalcular, btnActualizar, btnAcerca;
	private ButtonGroup grupo;
	private JRadioButton mayor, menor, relacion, abc;

	private Integer[][] datosPJ = { { 1, 150 }, { 2, 200 }, { 3, 250 }, { 4, 300 }, { 5, 350 }, { 6, 450 }, { 7, 550 }, { 8, 650 }, { 9, 750 },
			{ 10, 1000 }, { 11, 1250 }, { 12, 1500 }, { 13, 1750 }, { 14, 2000 }, { 15, 2300 }, { 16, 2600 }, { 17, 2900 }, { 18, 3200 },
			{ 19, 3500 }, { 20, 4000 }, { 21, 4500 }, { 22, 5000 }, { 23, 5900 }, { 24, 7350 }, { 25, 9150 }, { 26, 11400 }, { 27, 14250 },
			{ 28, 17750 }, { 29, 22000 }, { 30, 27500 }, { 31, 35000 }, { 32, 42750 }, { 33, 52750 }, { 34, 62750 }, { 35, 72750 }, { 36, 85000 },
			{ 37, 97250 }, { 38, 109500 }, { 39, 134500 }, { 40, 200000 }, { 41, 275000 }, { 42, 400000 }, { 43, 525000 }, { 44, 650000 },
			{ 45, 765000 }, { 46, 955000 }, { 47, 1200000 }, { 48, 1500000 }, { 49, 2000000 } };

	// 108 filas y 4 columnas
	/* Cada fila indica el NPC por asi decirlo. La primera columna indica el nombre del NPC, la segunda la vida, la tercera
	 * la exp y la cuarta el oro.
	 * 
	 * Lo que fue llenar esto a mano una madrugada re loco.. */
	private Object[][] datosNPC = { { "Gallina salvaje", 20, 26, 10 }, { "Gallo salvaje", 20, 26, 10 }, { "Conejo", 25, 30, 11 },
			{ "Serpiente", 25, 30, 11 }, { "Rana Venenosa", 30, 33, 14 }, { "Cuervo", 30, 33, 14 }, { "Murcielago", 30, 33, 14 },
			{ "Escorpion", 35, 39, 16 }, { "Escorpion Oscuro", 35, 39, 16 }, { "Sertlac", 100, 70, 25 }, { "Rata Gigante", 115, 81, 29 },
			{ "Mano poseida", 130, 91, 33 }, { "Señor de las Sombras", 150, 105, 38 }, { "Tortuga Gigante", 180, 126, 45 },
			{ "Esqueleto", 200, 140, 50 }, { "Esqueleto Guardian", 300, 180, 60 }, { "Asesino", 350, 210, 70 }, { "Bandido", 360, 216, 72 },
			{ "Mercenario", 375, 225, 75 }, { "Jabali Salvaje", 450, 225, 90 }, { "Zorro Rojo", 500, 250, 100 }, { "Zorro Gris", 500, 250, 100 },
			{ "Lobo", 600, 300, 120 }, { "Tigre", 600, 300, 120 }, { "Oso Pardo", 750, 450, 113 }, { "Araña Gigante", 1000, 600, 150 },
			{ "Orco", 1000, 600, 150 }, { "Orco Brujo", 1250, 750, 188 }, { "Jefe Orco", 2000, 1400, 500 }, { "Orco del Bosque", 1250, 750, 188 },
			{ "Ent", 3000, 2100, 450 }, { "Ciclope", 3250, 2275, 488 }, { "Fango", 3500, 2450, 525 }, { "Basilisco", 3000, 2100, 450 },
			{ "Aparicion", 4000, 3600, 1200 }, { "Ogro", 4500, 4050, 1350 }, { "Ogro Guerrero", 4750, 4275, 1425 },
			{ "Ogro Lider", 7500, 7500, 2250 }, { "Arquero Pirata", 4000, 3600, 1200 }, { "Guerrero Pirata", 4500, 4050, 1350 },
			{ "Jefe Pirata", 5000, 5000, 1500 }, { "Saqueador de Minas", 4250, 3825, 1275 }, { "Sirena", 5000, 5000, 1500 },
			{ "Zombie Aullador", 560, 616, 196 }, { "Zombie Desquiciado", 555, 611, 194 }, { "Zombie Putrido", 550, 605, 193 },
			{ "Zombie Mordedor", 575, 633, 201 }, { "Zombie Cenagoso", 595, 655, 208 }, { "Linche", 6500, 5850, 975 },
			{ "Lord Orco", 6750, 6075, 1013 }, { "Yuan Ti", 7000, 6300, 1050 }, { "Hombre Lagarto", 7000, 6300, 1050 },
			{ "Medusa", 8500, 8500, 1360 }, { "Medusa Matriarca", 10000, 10000, 1700 }, { "Golem de piedra", 20000, 16000, 2600 },
			{ "Cria de Dragon Verde", 25000, 10000, 3500 }, { "Cria de Dragon Rojo", 27500, 11000, 3850 }, { "Conjuradora", 12500, 12500, 2125 },
			{ "Golem de Bronce", 25000, 21250, 3500 }, { "Demonio", 15000, 7500, 3000 }, { "Demonio Abisal", 22500, 11250, 3150 },
			{ "Golem de Plata", 40000, 36000, 5600 }, { "Neishtar", 12500, 12500, 2125 }, { "Tolkvar", 25000, 27500, 4500 },
			{ "Oso Polar", 1050, 1155, 315 }, { "Huargo de Nieve", 1125, 1238, 338 }, { "Lobo Invernal", 975, 1073, 293 },
			{ "Chaman Artico", 12500, 13750, 2500 }, { "Yeti", 50000, 65000, 10000 }, { "Golem de Hielo", 75000, 67500, 15000 },
			{ "Cria de Rey Dragan", 35000, 17500, 7000 }, { "Corruptor", 995, 1095, 299 }, { "Turbador Sombrio", 1045, 1150, 314 },
			{ "Lacayo del Mal", 1025, 1128, 308 }, { "Sanguijuela Gigante", 975, 1073, 293 }, { "Adosador de la Muerte", 12500, 13750, 2125 },
			{ "Vampiro", 12500, 13750, 2125 }, { "Vampiresa", 12500, 13750, 2125 }, { "Vampiro Transformado", 95000, 109250, 19000 },
			{ "Hechicero de Agua", 25000, 32500, 5000 }, { "Hechicero de Aire", 25000, 32500, 5000 }, { "Hechicero de Tierra", 25000, 32500, 5000 },
			{ "Hechicero de Fuego", 25000, 32500, 5000 }, { "Profeta Arcano", 30000, 42000, 6000 }, { "Guardian", 30000, 42000, 6000 },
			{ "Gran Hechicero Garveloth", 100000, 145000, 20000 }, { "Kraken", 12500, 15000, 4375 }, { "Golem de Oro", 60000, 60000, 9000 },
			{ "Castigador", 17500, 17500, 2800 }, { "Conjuradora de Farzhe", 12500, 11250, 1875 }, { "Cria de Dragon Legendario", 20000, 9205, 7429 },
			{ "Bruja", 1450, 547, 700 }, { "Mago Protector", 1400, 532, 678 }, { "Cria Aracnida", 645, 677, 226 }, { "Archne", 11500, 10305, 1840 },
			{ "Desertor de las Tinieblas", 7500, 7500, 1275 }, { "Golem Infernal", 72000, 72000, 12240 }, { "F. Elemental Marron", 750, 825, 225 },
			{ "F. Elemental V. Oscuro", 730, 803, 219 }, { "F. Elemental Fucsia", 710, 781, 213 }, { "F. Elemental Verde", 690, 759, 207 },
			{ "F. Elemental Azul", 670, 737, 201 }, { "F. Elemental Gris", 650, 715, 195 }, { "F. Elemental Amarillo", 610, 671, 183 },
			{ "Gran Dragon Verde", 175000, 113750, 52500 }, { "Gran Dragon Rojo", 190000, 123500, 57000 },
			{ "Gran Dragon Azul", 225000, 146250, 67500 }, { "Gran Dragon Negro", 450000, 337500, 135000 },
			{ "Dragon Legendario", 650000, 520000, 195000 }, { "Gran Dragon de Hielo", 750000, 600000, 225000 },
			{ "Guarda del Pantano", 150000, 120000, 45000 }, { "Archimago Abisal", 175000, 210000, 52500 }, { "Gorgona", 125000, 150000, 37500 },
			{ "Kern Ghard", 200000, 240000, 60000 }, { "Rey Dragon", 1000000, 1000000, 300000 }, { "Agnus el Oscuro", 3225, 3870, 645 },
			{ "Momia enfurecida", 3750, 3350, 1200 }, { "Fantasma", 5300, 5565, 1060 }, { "Hombre Lobo", 6500, 7020, 1300 },
			{ "Zombie Lobrego", 5000, 6000, 1000 }, { "Alma Cautiva", 16500, 18150, 3300 }, { "Ermitaño de Neiran", 12500, 16000, 2750 },
			{ "Sacerdote de Neiran", 13050, 16704, 2871 }, { "Sabio de Neiran", 14275, 18272, 3141 }, { "Comandante de Neiran", 15750, 20475, 3465 },
			{ "Sicario de Neiran", 25000, 33750, 5500 }, { "Cazador de Neiran", 27000, 36450, 5940 }, { "Archimago de Neiran", 30000, 43500, 6600 },
			{ "Caballero de Neiran", 35000, 50750, 7700 }, { "Abigor", 25000, 38750, 6250 }, { "Belfegor", 34500, 54855, 8625 },
			{ "Bali", 42750, 68288, 10688 }, { "Cerbero", 27500, 43175, 6875 }, { "Fuego Fatuo", 5500, 6325, 1100 },
			{ "Elemental de Fuego", 8000, 10000, 1600 }, { "Elemental de Agua", 7750, 9688, 1550 }, { "Elemental de Tierra", 7500, 9000, 1500 },
			{ "Hechizero de la Oscuridad", 12500, 16250, 3125 }

	};

	private DecimalFormat formatoPorcentaje;

	private static final int BONUS_50 = 50;
	private static final int BONUS_100 = 100;
	private static final int BONUS_200 = 200;

	public Calculadora() {

		super("CalculadoraAO v1.1.0");

		setResizable(false);

		setIconImage(getImagen("logo.png"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		formatoPorcentaje = new DecimalFormat("##.##");

		initialize();

	}

	/* Comprueba si la ruta de la imagen existe. En caso de que no la encuentre, la aplicacion sigue con su ejecucion y no
	 * se detiene.
	 * 
	 * La razon de este metodo, se debe que al generar el .jar ejecutable DESDE Eclipse (en este caso), podria llegar a
	 * generar un error en la ruta de clases de la imagen, ya que estamos implementando maven para la estructura de carpetas
	 * y por convenio es recomendable crear el .jar desde la consola con un "mvn clean compile assembly:single". Este
	 * comando, crea el ejecutable incluyendo las dependencias especificadas en el archivo pom.xml y lo alamacena dentro de
	 * la carpeta target.
	 * 
	 * En conclusion, con esta funcion se evita que no se ejecute la aplicacion. */
	private Image getImagen(String path) {

		// Crea un cargador de clases para esta clase
		ClassLoader classLoader = this.getClass().getClassLoader();
		Image image = null;
		ImageIcon imageIcon;

		// El cargador de clases carga la ruta de la imagen y la almacena en una variable de tipo URL
		URL url = classLoader.getResource(path);

		// Evita un NullPointerException en caso de que la ruta de la imagen no se ecuentre
		if (url != null) {
			imageIcon = new ImageIcon(url);
			image = imageIcon.getImage();
		} else System.err.println("No se pudo encontrar la imagen [" + path + "] en \"" + System.getProperty("user.dir") + "\"");

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
		panel.setLayout(new MigLayout("fill")); // Reclama todo el espacio libre en el panel

		JPanel panelServidor = new JPanel();
		panelServidor.setLayout(new MigLayout("fill, insets 0"));

		tbtnPVP = new JToggleButton("PVP");
		tbtnPVP.addActionListener(new Oyente());
		tbtnPVP.setFocusable(false);
		tbtnRPG = new JToggleButton("RPG");
		tbtnRPG.addActionListener(new Oyente());
		tbtnRPG.setFocusable(false);

		panelServidor.add(tbtnPVP, "growx");
		panelServidor.add(tbtnRPG, "growx");

		tbtnGrupo = new JToggleButton("¿Estás en grupo?");
		tbtnGrupo.addActionListener(new Oyente());
		tbtnGrupo.setFocusable(false);

		tbtnRenegado = new JToggleButton("¿Sos renegado?");
		tbtnRenegado.setToolTipText("*Los renegados pierden un 10% de la experiencia total al formar grupos.");
		tbtnRenegado.setFocusable(false);
		tbtnRenegado.setEnabled(false);

		lblGrupo = new JLabel("¿Cuántos son?");

		cbGrupo = new JComboBox<String>(new String[] { "2", "3", "4", "5" });
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

		tbtn50 = new JToggleButton("+50%");
		tbtn50.addActionListener(new Oyente());
		tbtn50.setFocusable(false);

		tbtn100 = new JToggleButton("+100%");
		tbtn100.addActionListener(new Oyente());
		tbtn100.setFocusable(false);

		tbtn200 = new JToggleButton("+200%");
		tbtn200.addActionListener(new Oyente());
		tbtn200.setFocusable(false);

		panelBotonesExp.add(tbtnExpX2, "sg 2");
		panelBotonesExp.add(tbtnOroX2, "sg 2");
		panelBotonesExp.add(tbtn50, "sg 2");
		panelBotonesExp.add(tbtn100, "sg 2");
		panelBotonesExp.add(tbtn200, "sg 2, growx");

		panel.add(panelServidor, "spanx, growx");
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

		lblNivel = new JLabel("Nivel:");

		cbNivel = new JComboBox<String>(getNiveles());
		cbNivel.addActionListener(new Oyente());
		cbNivel.setFocusable(false);
		cbNivel.setSelectedItem(null);

		lblExpPJ = new JLabel("Experiencia:");

		// Obviamente si le indico el tama�o al campo de texto, entonces la ventana no tiene que ser redimensionable
		txtExpPJ = new JTextField(6);
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
		grupo = new ButtonGroup();
		mayor = new JRadioButton("+exp");
		mayor.addActionListener(new Oyente());
		mayor.setFont(new Font("Consolas", Font.PLAIN, 11));
		mayor.setFocusable(false);
		menor = new JRadioButton("-exp");
		menor.addActionListener(new Oyente());
		menor.setFont(new Font("Consolas", Font.PLAIN, 11));
		menor.setFocusable(false);
		relacion = new JRadioButton("vida/exp");
		relacion.addActionListener(new Oyente());
		relacion.setFont(new Font("Consolas", Font.PLAIN, 11));
		relacion.setFocusable(false);
		abc = new JRadioButton("abc");
		abc.addActionListener(new Oyente());
		abc.setFont(new Font("Consolas", Font.PLAIN, 11));
		abc.setFocusable(false);
		grupo.add(mayor);
		grupo.add(menor);
		grupo.add(relacion);
		grupo.add(abc);
		panelRadio.add(mayor);
		panelRadio.add(menor);
		panelRadio.add(relacion);
		panelRadio.add(abc);
		panel.add(panelRadio, "spanx, wrap");

		lblNPC = new JLabel("Nombre:");
		cbNPC = new JComboBox<String>(getNombres());
		AutoCompleteDecorator.decorate(cbNPC);
		cbNPC.addActionListener(new Oyente());
		cbNPC.setSelectedItem(null);

		lblVidaNPC = new JLabel("Vida:");
		txtVidaNPC = new JTextField();
		txtVidaNPC.setEditable(false);
		lblExpNPC = new JLabel("Exp:");
		txtExpNPC = new JTextField();
		txtExpNPC.setEditable(false);
		lblOroNPC = new JLabel("Oro:");
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

		lblPorcentajeExp = new JLabel("Porcentaje de experiencia que otorga el NPC:");
		txtPorcentajeExp = new JTextField();
		txtPorcentajeExp.setEditable(false);
		lblTotNpcMatar = new JLabel("Total de NPCs a matar para subir de nivel:");
		txtTotalNPC = new JTextField();
		txtTotalNPC.setEditable(false);
		lblTotOro = new JLabel("Total de oro a conseguir:");
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

		panel.add(new JLabel("by Ru$o"));

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFocusable(false);
		btnActualizar.setEnabled(false);
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == btnActualizar) {
					// actualizar por medios de comandos en git bash
				}
			}
		});

		btnAcerca = new JButton("Acerca de...");
		btnAcerca.setFocusable(false);
		btnAcerca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == btnAcerca) Acerca.getInstance().setVisible(true);
			}
		});

		panel.add(btnActualizar, "split 2");
		panel.add(btnAcerca);

		return panel;
	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	private String[] getNiveles() {
		String[] nivel = new String[datosPJ.length];
		for (int i = 0; i < datosPJ.length; i++)
			nivel[i] = "" + datosPJ[i][0];
		return nivel;
	}

	private String[] getNombres() {
		String[] nombre = new String[datosNPC.length];
		for (int i = 0; i < datosNPC.length; i++)
			nombre[i] = "" + datosNPC[i][0];
		return nombre;
	}

	// FIXME Crear una clase aparte para la clase interna
	private class Oyente implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) {

			if (evt.getSource() == tbtnPVP || evt.getSource() == tbtnRPG || evt.getSource() == cbNivel || evt.getSource() == cbNPC) accion0();
			if (evt.getSource() == tbtnPVP) accion1();
			if (evt.getSource() == tbtnRPG) accion2();
			if (evt.getSource() == tbtnGrupo) accion3();
			if (evt.getSource() == tbtn50) accion4();
			if (evt.getSource() == tbtn100) accion5();
			if (evt.getSource() == tbtn200) accion6();
			if (evt.getSource() == cbNivel) getExpPJ();
			if (evt.getSource() == cbNPC) getDatosNPC();
			if (evt.getSource() == mayor) sortDesc();
			if (evt.getSource() == menor) sortAsc();
			if (evt.getSource() == relacion) sortRelacion();
			if (evt.getSource() == abc) sortABC();
			if (evt.getSource() == btnCalcular) calcularTotal();

		}

		/* Habilita el boton btnCalcular si se selecciono un servidor, y si los dos combos PJ y NPC tienen seleccionado un
		 * item. */
		private void accion0() {
			if ((tbtnPVP.isSelected() == true || tbtnRPG.isSelected() == true)
					&& (cbNivel.getSelectedItem() != null && cbNPC.getSelectedItem() != null))
				btnCalcular.setEnabled(true);
			else btnCalcular.setEnabled(false);
		}

		private void accion1() {
			if (tbtnRPG.isSelected()) tbtnRPG.setSelected(false);
		}

		private void accion2() {
			if (tbtnPVP.isSelected()) tbtnPVP.setSelected(false);
		}

		private void accion3() {
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

		private void accion4() {
			if (tbtn100.isSelected() || tbtn200.isSelected()) {
				tbtn100.setSelected(false);
				tbtn200.setSelected(false);
			}
		}

		private void accion5() {
			if (tbtn50.isSelected() || tbtn200.isSelected()) {
				tbtn50.setSelected(false);
				tbtn200.setSelected(false);
			}
		}

		private void accion6() {
			if (tbtn50.isSelected() || tbtn100.isSelected()) {
				tbtn50.setSelected(false);
				tbtn100.setSelected(false);
			}
		}

		private void getExpPJ() {
			if (cbNivel.getSelectedIndex() != -1) {
				for (int i = 0; i < datosPJ.length; i++) {
					if (cbNivel.getSelectedItem().equals("" + datosPJ[i][0])) {
						txtExpPJ.setText("" + datosPJ[i][1]);
						break;
					}
				}
			}
		}

		private void getDatosNPC() {
			if (cbNPC.getSelectedIndex() != -1) {
				for (int i = 0; i < datosNPC.length; i++) {
					if (cbNPC.getSelectedItem().equals(datosNPC[i][0])) {
						txtVidaNPC.setText("" + datosNPC[i][1]);
						txtExpNPC.setText("" + datosNPC[i][2]);
						txtOroNPC.setText("" + datosNPC[i][3]);
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

			for (int i = 0; i < datosNPC.length - 1; i++) {
				for (int j = 0; j < datosNPC.length - 1 - i; j++) {

					int expX = (int) datosNPC[j][2];
					int expY = (int) datosNPC[j + 1][2];

					if (expX < expY) {
						auxNombre = "" + datosNPC[j + 1][0];
						datosNPC[j + 1][0] = datosNPC[j][0];
						datosNPC[j][0] = auxNombre;

						auxVida = (int) datosNPC[j + 1][1];
						datosNPC[j + 1][1] = datosNPC[j][1];
						datosNPC[j][1] = auxVida;

						auxExp = (int) datosNPC[j + 1][2];
						datosNPC[j + 1][2] = datosNPC[j][2];
						datosNPC[j][2] = auxExp;

						auxOro = (int) datosNPC[j + 1][3];
						datosNPC[j + 1][3] = datosNPC[j][3];
						datosNPC[j][3] = auxOro;
					}

				}
			}

			for (int i = 0; i < datosNPC.length; i++)
				cbNPC.addItem("" + datosNPC[i][0]);

		}

		// Ordena los NPCs de menor a mayor exp
		private void sortAsc() {
			String auxNombre;
			int auxVida, auxExp, auxOro;

			cbNPC.removeAllItems();

			// El primer for controlada cada item y el segundo el intercambio
			for (int i = 0; i < datosNPC.length - 1; i++) {
				/* Se le resta - 1 al tama�o del array ya que el limite se llega en la suma de j + 1, evitando asi tambien un
				 * ArrayIndexOutOfBoundsException. */
				for (int j = 0; j < datosNPC.length - 1 - i; j++) {

					int expX = (int) datosNPC[j][2];
					int expY = (int) datosNPC[j + 1][2];

					// Si la exp de X es mayor a la exp de Y, entonces se intercambia el nombre, la vida, la exp y el oro
					if (expX > expY) {
						auxNombre = "" + datosNPC[j + 1][0];
						datosNPC[j + 1][0] = datosNPC[j][0];
						datosNPC[j][0] = auxNombre;

						auxVida = (int) datosNPC[j + 1][1];
						datosNPC[j + 1][1] = datosNPC[j][1];
						datosNPC[j][1] = auxVida;

						auxExp = (int) datosNPC[j + 1][2];
						datosNPC[j + 1][2] = datosNPC[j][2];
						datosNPC[j][2] = auxExp;

						auxOro = (int) datosNPC[j + 1][3];
						datosNPC[j + 1][3] = datosNPC[j][3];
						datosNPC[j][3] = auxOro;
					}

				}
			}

			for (int i = 0; i < datosNPC.length; i++)
				cbNPC.addItem("" + datosNPC[i][0]);

		}

		// Ordena los NPCs que mejor rinden en cuanto a la relacion que tienen entre vida y exp
		private void sortRelacion() {
			String auxNombre;
			int auxVida, auxExp, auxOro;
			double relacionX, relacionY;

			cbNPC.removeAllItems();

			for (int i = 0; i < datosNPC.length - 1; i++) {
				for (int j = 0; j < datosNPC.length - 1 - i; j++) {

					int vidaX = (int) datosNPC[j][1];
					int expX = (int) datosNPC[j][2];
					int vidaY = (int) datosNPC[j + 1][1];
					int expY = (int) datosNPC[j + 1][2];

					// Calcula la relacion del npc X y del npc Y, con respecto a la vida y exp de cada uno
					relacionX = (double) vidaX / expX;
					relacionY = (double) vidaY / expY;

					/* Compara ambas relaciones, y si X tiene una peor (mucha diferencia entre vida y exp) relacion con respecto a Y,
					 * entonces se intercambian los items del array. */
					if (relacionX > relacionY) {
						auxNombre = "" + datosNPC[j + 1][0];
						datosNPC[j + 1][0] = datosNPC[j][0];
						datosNPC[j][0] = auxNombre;

						auxVida = (int) datosNPC[j + 1][1];
						datosNPC[j + 1][1] = datosNPC[j][1];
						datosNPC[j][1] = auxVida;

						auxExp = (int) datosNPC[j + 1][2];
						datosNPC[j + 1][2] = datosNPC[j][2];
						datosNPC[j][2] = auxExp;

						auxOro = (int) datosNPC[j + 1][3];
						datosNPC[j + 1][3] = datosNPC[j][3];
						datosNPC[j][3] = auxOro;
					}

				}
			}

			for (int i = 0; i < datosNPC.length; i++)
				cbNPC.addItem("" + datosNPC[i][0]);

		}

		// Ordena los NPCs alfabeticamente
		private void sortABC() {
			ArrayList<String> nombres = new ArrayList<String>();

			// Agrega los nombres del array de objetos al array de strings
			for (int i = 0; i < datosNPC.length; i++)
				nombres.add("" + datosNPC[i][0]);

			// Ordena los nombres alfabeticamente
			Collections.sort(nombres);

			// Limpia el JComboBox viejo
			cbNPC.removeAllItems();

			// Agrega los nombres ordenados al ArrayList
			for (int i = 0; i < nombres.size(); i++)
				cbNPC.addItem(nombres.get(i));

		}

		private void calcularTotal() {

			int exp = Integer.parseInt(txtExpNPC.getText()), oro = Integer.parseInt(txtOroNPC.getText());
			double npc, npc_r, tot_exp, tot_exp_r;

			// Si es un server PVP
			if (tbtnPVP.isSelected()) {
				exp *= 5;
				oro *= 3;
			}

			// Si hay evento de exp x2
			if (tbtnExpX2.isSelected()) exp *= 2;
			// Si hay evento de oro x2
			if (tbtnOroX2.isSelected()) oro *= 2;

			// Calcula el bonus adicional del +50%, +100% y +200% de la experiencia ganada
			if (tbtn50.isSelected()) exp += calcular50(exp);
			if (tbtn100.isSelected()) exp += calcular100(exp);
			if (tbtn200.isSelected()) exp += calcular200(exp);

			// Cantidad de NPCs a matar
			npc = Double.parseDouble(txtExpPJ.getText()) / exp;
			// Porcentaje de experiencia que otorga el NPC
			tot_exp = 100 / npc;

			// Cantidad de NPCs a matar para los grupos de renegados
			/* 0.9
			 * 
			 * Multiplicar la cantidad de exp que otorga el NPCs por 0.9 es un atajo para sacar la perdida del 10% del total de esa
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
			npc_r = Double.parseDouble(txtExpPJ.getText()) / (exp * 0.9);
			// Porcentaje de experiencia que otorga el NPC para los grupos de renegados
			tot_exp_r = 100 / npc_r; // tot_exp_r = tot_exp * 0.9;

			// Si el PJ esta en grupo, entonces...
			if (tbtnGrupo.isSelected()) {
				// Si el PJ es renegado, entonces...
				if (tbtnRenegado.isSelected()) {
					if (cbGrupo.getSelectedIndex() != -1) {
						if (cbGrupo.getSelectedIndex() == 0) setValores(npc_r * 2, tot_exp_r / 2);
						if (cbGrupo.getSelectedIndex() == 1) setValores(npc_r * 3, tot_exp_r / 3);
						if (cbGrupo.getSelectedIndex() == 2) setValores(npc_r * 4, tot_exp_r / 4);
						if (cbGrupo.getSelectedIndex() == 3) setValores(npc_r * 5, tot_exp_r / 5);
					}
				} else {
					if (cbGrupo.getSelectedIndex() != -1) {
						if (cbGrupo.getSelectedIndex() == 0) setValores(npc * 2, tot_exp / 2);
						if (cbGrupo.getSelectedIndex() == 1) setValores(npc * 3, tot_exp / 3);
						if (cbGrupo.getSelectedIndex() == 2) setValores(npc * 4, tot_exp / 4);
						if (cbGrupo.getSelectedIndex() == 3) setValores(npc * 5, tot_exp / 5);
					}
				}

			} else setValores(npc, tot_exp);

			// Oro y oro para los renes
			int npc_redo = (int) Math.ceil(npc);
			int npc_redo_r = (int) Math.ceil(npc_r);

			int oroTotal = npc_redo * oro;
			int oroTotalR = npc_redo_r * oro;

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

		private int calcular50(int exp) {
			return exp * BONUS_50 / 100;
		}

		private int calcular100(int exp) {
			return exp * BONUS_100 / 100;
		}

		private int calcular200(int exp) {
			return exp * BONUS_200 / 100;
		}

		private void setValores(double cantidad, double exp) {
			txtTotalNPC.setText("" + (int) Math.ceil(cantidad));
			txtPorcentajeExp.setText(formatoPorcentaje.format(exp) + "%");
		}

	}

}
