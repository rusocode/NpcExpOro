package com.silentsoft.calculadoraao.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * IniFile: Clase para leer archivos INI.
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
	 * @param file: Archivo localizado.
	 * @throws FileNotFoundException: Archivo no encontrado.
	 * @throws IOException:           Error de I/O (entra y salida).
	 */
	public void load(InputStream file) {
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new InputStreamReader(file));
			loadFromFile(buffer);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se encontro el archivo " + file, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			System.err.println("Error de I/O: " + e.getMessage());
		} finally {
			try {
				if (buffer != null) buffer.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar el flujo de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	// FIXME static?
	// Carga el INI a una coleccion de tipo LinkedHashMap
	private void loadFromFile(BufferedReader buffer) throws FileNotFoundException, IOException {

		int corcheteCierre, separador;
		String section = null, key = null, value = null;
		String srt = "";

		while ((srt = buffer.readLine()) != null) {
			srt = srt.trim(); // Si hay espacios en blanco al principio o final de la cadena, los elimina
			if (srt.length() > 0) { // Si no es un espacio en blanco, entonces...
				switch (srt.charAt(0)) {
				case '[':
					// Si la cadena empieza con un [, entonces...
					if ((corcheteCierre = srt.indexOf(']')) != -1) {
						// Almacena la seccion que va del indice 1 (N) al corcheteCierre (7 en este caso, pero no se incluye)
						section = srt.substring(1, corcheteCierre).toUpperCase();
						// Si la coleccion no contiene la seccion, entonces agrega la seccion a la coleccion
						if (!data.containsKey(section)) data.put(section, new LinkedHashMap<String, String>());
					}
					break;
				default:
					// Si esta dentro de una seccion y hay un signo = en la linea, entonces...
					if ((section != null) && (separador = srt.indexOf('=')) != -1) {

						key = srt.substring(0, separador).trim();
						value = srt.substring(separador + 1, srt.length()).trim();

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
