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

package com.silentsoft.calculadoraao.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * IniFile: Clase para leer archivos INI.
 * 
 * @author Ru$o
 * 
 */

public class IniFile {

	private LinkedHashMap<String, LinkedHashMap<String, String>> data = new LinkedHashMap<String, LinkedHashMap<String, String>>();

	public IniFile() {
	}

	public IniFile(InputStream file) {
		load(file);
	}

	/**
	 * Carga y parsea un archivo INI.
	 * 
	 * @param file: Nombre del archivo ini.
	 * @throws NullPointerException: Archivo no encontrado.
	 * @throws IOException:          Error de I/O (entra y salida).
	 */
	public void load(InputStream file) {
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new InputStreamReader(file)); // FIXME le agrego el charset (UTF-8)?
			loadFromFile(buffer);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No se encontro el archivo especificado", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error de I/O: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if (buffer != null) buffer.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar el flujo de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	// Carga el INI a una coleccion de tipo LinkedHashMap
	private void loadFromFile(BufferedReader buffer) throws FileNotFoundException, IOException {

		int corcheteCierre, separador, comentario;
		String section = null, key, value;
		String srt;

		while ((srt = buffer.readLine()) != null) {
			srt = srt.trim(); // Si hay espacios en blanco al principio o final de la cadena, los elimina
			if (srt.length() > 0) { // Si no es un espacio en blanco, entonces...
				switch (srt.charAt(0)) {
				case ';': // COMENTARIOS
					break;
				case '[': // SECCIONES
					if ((corcheteCierre = srt.indexOf(']')) != -1) {
						// Almacena la seccion que va del indice 1 al corcheteCierre sin incluirlo
						section = srt.substring(1, corcheteCierre).toUpperCase();
						// Si la coleccion no contiene la seccion, entonces agrega la seccion a la coleccion
						if (!data.containsKey(section)) data.put(section, new LinkedHashMap<String, String>());
					}
					break;
				default: // PARAMETROS
					// Si esta dentro de una seccion y hay un signo '=' en la linea, entonces...
					if ((section != null) && (separador = srt.indexOf('=')) != -1) {

						key = srt.substring(0, separador).trim(); // FIXME Q funcionalidad tiene el metodo trim() en esta linea?
						value = srt.substring(separador + 1, srt.length()).trim();

						// Si hay un comentario al final de la linea, lo quita
						if ((comentario = value.indexOf(';')) > -1) value = value.substring(0, comentario).trim();

						// Recupera la seccion y agrega el par (clave, valor)
						data.get(section).put(key, value);

					}

					break;
				}
			}

		}

	}

	// Devuelve los valores correspondientes a la clave recibida por parametro
	public String[] getValues(String key) {
		String[] valores = new String[data.size()];

		int i = 0;

		/* Itera la primera clave de la coleccion.
		 * 
		 * Convierte la coleccion en Set para poder trabajarla como un conjuto de datos. */
		for (Map.Entry<String, LinkedHashMap<String, String>> v : data.entrySet()) {

			// Almacena las sub claves y valores
			LinkedHashMap<String, String> value = v.getValue();

			// Itera las claves y valores de la clave n
			for (Map.Entry<String, String> v2 : value.entrySet()) {
				// Si la clave es igual a key, entonces...
				if (v2.getKey().equalsIgnoreCase(key)) {
					valores[i] = v2.getValue(); // Almacena el valor de la clave nivel de la seccion n
					i++;
				}

			}

		}

		return valores;

	}

}
