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

package paquete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

/**
 * @author Ru$o
 * 
 */

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblNivel, lblExpPJ, lblNPC, lblExpNPC, lblVidaNPC, lblOroNPC, lblGrupo, lblPorcentajeExp, lblTotNpcMatar, lblTotOro;
	private JTextField txtExpPJ, txtExpNPC, txtVidaNPC, txtOroNPC, txtPorcentajeExp, txtTotalNPC, txtTotalOro;
	private JComboBox<String> cbNivel, cbNPC, cbGrupo;
	private JToggleButton tbtnGrupo, tbtnRenegado, tbtnPVP, tbtnRPG, tbtnExpX2, tbtnOroX2;
	private JButton btnCalcular;

	private Integer[][] datosPJ = { { 1, 150 }, { 2, 200 }, { 3, 250 }, { 4, 300 }, { 5, 350 }, { 6, 450 }, { 7, 550 }, { 8, 650 }, { 9, 750 },
			{ 10, 1000 }, { 11, 1250 }, { 12, 1500 }, { 13, 1750 }, { 14, 2000 }, { 15, 2300 }, { 16, 2600 }, { 17, 2900 }, { 18, 3200 },
			{ 19, 3500 }, { 20, 4000 }, { 21, 4500 }, { 22, 5000 }, { 23, 5900 }, { 24, 7350 }, { 25, 9150 }, { 26, 11400 }, { 27, 14250 },
			{ 28, 17750 }, { 29, 22000 }, { 30, 27500 }, { 31, 35000 }, { 32, 42750 }, { 33, 52750 }, { 34, 62750 }, { 35, 72750 }, { 36, 85000 },
			{ 37, 97250 }, { 38, 109500 }, { 39, 134500 }, { 40, 200000 }, { 41, 275000 }, { 42, 400000 }, { 43, 525000 }, { 44, 650000 },
			{ 45, 765000 }, { 46, 955000 }, { 47, 1200000 }, { 48, 1500000 }, { 49, 2000000 } };

	private Object[][] datosNPC = { { "Gallina salvaje", 20, 26, 10 }, { "Gallo salvaje", 20, 26, 10 }, { "Conejo", 25, 30, 11 },
			{ "Serpiente", 25, 30, 11 }, { "Rana Venenosa", 30, 33, 14 }, { "Cuervo", 30, 33, 14 }, { "Murciélago", 30, 33, 14 },
			{ "Escorpión", 35, 39, 16 }, { "Escorpión Oscuro", 35, 39, 16 }, { "Sertlac", 100, 70, 25 }, { "Rata Gigante", 115, 81, 29 },
			{ "Mano poseída", 130, 91, 33 }, { "Señor de las Sombras", 150, 105, 38 }, { "Tortuga Gigante", 180, 126, 45 },
			{ "Esqueleto", 200, 140, 50 }, { "Esqueleto Guardián", 300, 180, 60 }, { "Asesino", 350, 210, 70 }, { "Bandido", 360, 216, 72 },
			{ "Mercenario", 375, 225, 75 }, { "Jabalí Salvaje", 450, 225, 90 }, { "Zorro Rojo", 500, 250, 100 }, { "Zorro Gris", 500, 250, 100 },
			{ "Lobo", 600, 300, 120 }, { "Tigre", 600, 300, 120 }, { "Oso Pardo", 750, 450, 113 }, { "Araña Gigante", 1000, 600, 150 },
			{ "Orco", 1000, 600, 150 }, { "Orco Brujo", 1250, 750, 188 }, { "Jefe Orco", 2000, 1400, 500 }, { "Orco del Bosque", 1250, 750, 188 },
			{ "Ent", 3000, 2100, 450 }, { "Cíclope", 3250, 2275, 488 }, { "Fango", 3500, 2450, 525 }, { "Basilisco", 3000, 2100, 450 },
			{ "Aparición", 4000, 3600, 1200 }, { "Ogro", 4500, 4050, 1350 }, { "Ogro Guerrero", 4750, 4275, 1425 },
			{ "Ogro Líder", 7500, 7500, 2250 }, { "Arquero Pirata", 4000, 3600, 1200 }, { "Guerrero Pirata", 4500, 4050, 1350 },
			{ "Jefe Pirata", 5000, 5000, 1500 }, { "Saqueador de Minas", 4250, 3825, 1275 }, { "Sirena", 5000, 5000, 1500 },
			{ "Zombie Aullador", 560, 616, 196 }, { "Zombie Desquiciado", 555, 611, 194 }, { "Zombie Pútrido", 550, 605, 193 },
			{ "Zombie Mordedor", 575, 633, 201 }, { "Zombie Cenagoso", 595, 655, 208 }, { "Linche", 6500, 5850, 975 },
			{ "Lord Orco", 6750, 6075, 1013 }, { "Yuan Ti", 7000, 6300, 1050 }, { "Hombre Lagarto", 7000, 6300, 1050 },
			{ "Medusa", 8500, 8500, 1360 }, { "Medusa Matriarca", 10000, 10000, 1700 }, { "Gólem de Piedra", 20000, 16000, 2600 },
			{ "Cría de Dragón Verde", 25000, 10000, 3500 }, { "Cría de Dragón Rojo", 27500, 11000, 3850 }, { "Conjuradora", 12500, 12500, 2125 },
			{ "Gólem de Bronce", 25000, 21250, 3500 }, { "Demonio", 15000, 7500, 3000 }, { "Demonio Abisal", 22500, 11250, 3150 },
			{ "Gólem de Plata", 40000, 36000, 5600 }, { "Neishtar", 12500, 12500, 2125 }, { "Tolkvar", 25000, 27500, 4500 },
			{ "Oso Polar", 1050, 1155, 315 }, { "Huargo de Nieve", 1125, 1238, 338 }, { "Lobo Invernal", 975, 1073, 293 },
			{ "Chamán Ártico", 12500, 13750, 2500 }, { "Yeti", 50000, 65000, 10000 }, { "Gólem de Hielo", 75000, 67500, 15000 },
			{ "Cría de Rey Dragón", 35000, 17500, 7000 }, { "Corruptor", 995, 1095, 299 }, { "Turbador Sombrío", 1045, 1150, 314 },
			{ "Lacayo del Mal", 1025, 1128, 308 }, { "Sanguijuela Gigante", 975, 1073, 293 }, { "Adosador de la Muerte", 12500, 13750, 2125 },
			{ "Vampiro", 12500, 13750, 2125 }, { "Vampiresa", 12500, 13750, 2125 }, { "Vampiro Transformado", 95000, 109250, 19000 },
			{ "Hechicero de Agua", 25000, 32500, 5000 }, { "Hechicero de Aire", 25000, 32500, 5000 }, { "Hechicero de Tierra", 25000, 32500, 5000 },
			{ "Hechicero de Fuego", 25000, 32500, 5000 }, { "Profeta Arcano", 30000, 42000, 6000 }, { "Guardián", 30000, 42000, 6000 },
			{ "Gran Hechicero Garveloth", 100000, 145000, 20000 }, { "Kraken", 12500, 15000, 4375 }, { "Gólem de Oro", 60000, 60000, 9000 },
			{ "Castigador", 17500, 17500, 2800 }, { "Conjuradora de Farzhe", 12500, 11250, 1875 }, { "Cría de Dragón Legendario", 20000, 9205, 7429 },
			{ "Bruja", 1450, 547, 700 }, { "Mago Protector", 1400, 532, 678 }, { "Cría Arácnida", 645, 677, 226 }, { "Archne", 11500, 10305, 1840 },
			{ "Desertor de las Tinieblas", 7500, 7500, 1275 }, { "Gólem Infernal", 72000, 72000, 12240 }, { "F. Elemental Marrón", 750, 825, 225 },
			{ "F. Elemental V. Oscuro", 730, 803, 219 }, { "F. Elemental Fucsia", 710, 781, 213 }, { "F. Elemental Verde", 690, 759, 207 },
			{ "F. Elemental Azul", 670, 737, 201 }, { "F. Elemental Gris", 650, 715, 195 }, { "F. Elemental Amarillo", 610, 671, 183 },
			{ "Gran Dragón Verde", 175000, 113750, 52500 }, { "Gran Dragón Rojo", 190000, 123500, 57000 },
			{ "Gran Dragón Azul", 225000, 146250, 67500 }, { "Gran Dragón Negro", 450000, 337500, 135000 },
			{ "Dragón Legendario", 650000, 520000, 195000 }, { "Gran Dragón de Hielo", 750000, 600000, 225000 },
			{ "Guarda del Pantano", 150000, 120000, 45000 }, { "Archimago Abisal", 175000, 210000, 52500 }, { "Gorgona", 125000, 150000, 37500 },
			{ "Kern Ghard", 200000, 240000, 60000 }, { "Rey Dragón", 1000000, 1000000, 300000 }, { "Agnus el Oscuro", 3225, 3870, 645 },
			{ "Momia enfurecida", 3750, 3350, 1200 }, { "Fantasma", 5300, 5565, 1060 }, { "Hombre Lobo", 6500, 7020, 1300 },
			{ "Zombie Lóbrego", 5000, 6000, 1000 }, { "Alma Cautiva", 16500, 18150, 3300 }, { "Ermitaño de Neiran", 12500, 16000, 2750 },
			{ "Sacerdote de Neiran", 13050, 16704, 2871 }, { "Sabio de Neiran", 14275, 18272, 3141 }, { "Comandante de Neiran", 15750, 20475, 3465 },
			{ "Sicario de Neiran", 25000, 33750, 5500 }, { "Cazador de Neiran", 27000, 36450, 5940 }, { "Archimago de Neiran", 30000, 43500, 6600 },
			{ "Caballero de Neiran", 35000, 50750, 7700 }, { "Abigor", 25000, 38750, 6250 }, { "Belfegor", 34500, 54855, 8625 },
			{ "Bali", 42750, 68288, 10688 }, { "Cerbero", 27500, 43175, 6875 }, { "Fuego Fatuo", 5500, 6325, 1100 },
			{ "Elemental de Fuego", 8000, 10000, 1600 }, { "Elemental de Agua", 7750, 9688, 1550 }, { "Elemental de Tierra", 7500, 9000, 1500 },
			{ "Hechizero de la Oscuridad", 12500, 16250, 3125 }

	};

	private DecimalFormat formatoPorcentaje;

	private Calculadora() {

		super("CalculadoraAO v0.5");

		setResizable(false);
		setIconImage((new ImageIcon(getClass().getClassLoader().getResource("logo.png"))).getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		formatoPorcentaje = new DecimalFormat("##.##");

		initialize();

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
		add(new JLabel("by Ru$o"));

		pack();
		setLocationRelativeTo(null);

	}

	private JPanel getAjustesPanel() {
		JPanel panel = getPanel("Ajustes");
		panel.setLayout(new MigLayout("fill"));

		JPanel panelServidores = new JPanel();
		panelServidores.setLayout(new MigLayout("fill, insets 0"));

		tbtnPVP = new JToggleButton("PVP [exp x5 - oro x3]");
		tbtnPVP.addActionListener(new Oyente());
		tbtnPVP.setFocusable(false);

		tbtnRPG = new JToggleButton("RPG [exp x1 - oro x1]");
		tbtnRPG.addActionListener(new Oyente());
		tbtnRPG.setFocusable(false);

		panelServidores.add(tbtnPVP, "growx");
		panelServidores.add(tbtnRPG, "growx");

		tbtnGrupo = new JToggleButton("¿Estás en grupo?");
		tbtnGrupo.addActionListener(new Oyente());
		tbtnGrupo.setFocusable(false);

		tbtnRenegado = new JToggleButton("¿Sos renegado?");
		tbtnRenegado.setToolTipText("*Los renegados pierden un 10% de la experiencia total al formar grupos.");
		tbtnRenegado.setFocusable(false);
		tbtnRenegado.setEnabled(false);

		lblGrupo = new JLabel("¿Cuántos son?");

		cbGrupo = new JComboBox<String>();
		cbGrupo.setModel(new DefaultComboBoxModel<String>(new String[] { "2", "3", "4", "5" }));
		cbGrupo.setSelectedIndex(-1);
		cbGrupo.setFocusable(false);
		cbGrupo.setEnabled(false);

		tbtnExpX2 = new JToggleButton("exp x2");
		tbtnExpX2.addActionListener(new Oyente());
		tbtnExpX2.setFocusable(false);

		tbtnOroX2 = new JToggleButton("oro x2");
		tbtnOroX2.addActionListener(new Oyente());
		tbtnOroX2.setFocusable(false);

		panel.add(panelServidores, "spanx, growx");
		panel.add(tbtnGrupo, "sg 1");
		panel.add(tbtnRenegado, "sg 1");
		panel.add(lblGrupo);
		panel.add(cbGrupo, "growx, wrap");
		panel.add(tbtnExpX2, "sg 1");
		panel.add(tbtnOroX2, "sg 1");

		return panel;
	}

	private JPanel getPJPanel() {
		JPanel panel = getPanel("PJ");
		panel.setLayout(new MigLayout("wrap 2"));

		lblNivel = new JLabel("Nivel:");

		cbNivel = new JComboBox<String>();
		cbNivel.setModel(new DefaultComboBoxModel<String>(getNivel()));
		cbNivel.addActionListener(new Oyente());
		cbNivel.setSelectedItem(null);
		cbNivel.setFocusable(false);

		lblExpPJ = new JLabel("Experiencia:");

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

		lblNPC = new JLabel("Nombre:");
		cbNPC = new JComboBox<String>();
		cbNPC.setModel(new DefaultComboBoxModel<String>(getNombresOrdenados()));
		cbNPC.addActionListener(new Oyente());
		cbNPC.setSelectedItem(null);
		cbNPC.setFocusable(false);

		lblExpNPC = new JLabel("Experiencia:");
		txtExpNPC = new JTextField();
		txtExpNPC.setEditable(false);
		lblVidaNPC = new JLabel("Vida:");
		txtVidaNPC = new JTextField();
		txtVidaNPC.setEditable(false);
		lblOroNPC = new JLabel("Oro:");
		txtOroNPC = new JTextField();
		txtOroNPC.setEditable(false);

		panel.add(lblNPC);
		panel.add(cbNPC);
		panel.add(lblExpNPC);
		panel.add(txtExpNPC);
		panel.add(lblVidaNPC);
		panel.add(txtVidaNPC);
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
		panel.add(btnCalcular, "span, growx");

		return panel;
	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	private String[] getNivel() {
		String[] nivel = new String[datosPJ.length];
		for (int i = 0; i < datosPJ.length; i++)
			nivel[i] = "" + datosPJ[i][0];

		return nivel;

	}

	private String[] getNombresOrdenados() {

		ArrayList<String> nombres = new ArrayList<String>();

		for (int i = 0; i < datosNPC.length; i++)
			nombres.add((String) datosNPC[i][0]);

		Collections.sort(nombres);

		return nombres.toArray(new String[0]);

	}

	private class Oyente implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) {

			if (evt.getSource() == tbtnPVP) accion1();
			if (evt.getSource() == tbtnRPG) accion2();
			if (evt.getSource() == cbNivel) getExpPJ();
			if (evt.getSource() == cbNPC) getDatosNPC();
			if (evt.getSource() == tbtnGrupo) habilitarComponentes();
			if (evt.getSource() == btnCalcular) calcularTotal();

			/* Habilita el boton btnCalcular si se selecciono un servidor y si alguno los dos combos (PJ y NPC) tienen seleccionado
			 * un item. */
			if (evt.getSource() == tbtnPVP || evt.getSource() == tbtnRPG || evt.getSource() == cbNivel || evt.getSource() == cbNPC) {
				if ((cbNivel.getSelectedItem() != null && cbNPC.getSelectedItem() != null)
						&& (tbtnPVP.isSelected() == true || tbtnRPG.isSelected() == true))
					btnCalcular.setEnabled(true);
				else btnCalcular.setEnabled(false);
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

		private void accion1() {
			if (tbtnRPG.isSelected()) tbtnRPG.setSelected(false);
		}

		private void accion2() {
			if (tbtnPVP.isSelected()) tbtnPVP.setSelected(false);
		}

		private void habilitarComponentes() {
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

		private void calcularTotal() {

			int exp = Integer.parseInt(txtExpNPC.getText()), oro = Integer.parseInt(txtOroNPC.getText());
			double npc, npc_r, tot_exp, tot_exp_r;

			if (tbtnPVP.isSelected()) {
				exp *= 5;
				oro *= 3;
			}

			if (tbtnExpX2.isSelected()) exp *= 2;
			if (tbtnOroX2.isSelected()) oro *= 2;

			npc = Double.parseDouble(txtExpPJ.getText()) / exp;
			tot_exp = 100 / npc;

			npc_r = Double.parseDouble(txtExpPJ.getText()) / (exp * 0.9);
			tot_exp_r = 100 / npc_r;

			if (tbtnGrupo.isSelected()) {
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

			if (tbtnRenegado.isSelected()) txtTotalOro.setText("" + oroTotalR);
			else txtTotalOro.setText("" + oroTotal);

		}

		private void setValores(double cantidad, double exp) {
			txtTotalNPC.setText("" + (int) Math.ceil(cantidad));
			txtPorcentajeExp.setText(formatoPorcentaje.format(exp) + "%");
		}

	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Calculadora().setVisible(true);

	}

}
