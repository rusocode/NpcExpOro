## Change Log

#### Version: 1.0.1 - 16-08-2020

- **Implementacion de Maven.**
- *1399 KB menos!*
  - Dependencia MigLayout -> https://search.maven.org/artifact/com.miglayout/miglayout/3.7.4/jar
  - Dependencia swingx-autocomplete -> https://search.maven.org/artifact/org.swinglabs.swingx/swingx-autocomplete/1.6.5-1/jar
- Se eliminaron los acentos en los nombres de los NPCs para facilitar la busqueda del JComboBox.
- Nueva clase Launcher que ejecuta la aplicacion.
  
#### Version: 1.0 - 31-07-2020 

- JComboBox de NPCs autocompletable (libreria swingx-all-1.6.5.jar).
- Nuevas funciones de busqueda sobre el JComboBox del NPC:
  - `+exp` muestra los NPCs de mayor a menor exp.
  - `-exp` muestra los NPCs de menor a mayor exp.
  - `vida/exp` muestra los NPCs que mejor rinden en cuanto a la relacion que tienen entre vida y exp.
  - `abc` ordena los nombres de los NPCs alfabeticamente.
- Boton `Actualizar` agregado (falta hacerlo funcionar).
- Boton `Acerca de...` agregado:
  - Nueva componente JDialog que muestra informacion del software.
  - Link al repositorio en GitHub.
- Se agregaron los scrolls de +50%, +100% y +200% que otorgan un bonus adicional sobre la exp recibida.
- Se agregaron comentarios al codigo.
- Bugfixed.
