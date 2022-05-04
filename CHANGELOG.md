## Change Log ([semantic versioning](https://semver.org/))

#### Version: 1.1 - 04/05/2022
- Se volvio a implementar Maven
- Cambio de Java 8 a 15
- Se mejoro el rendimiento de la aplicacion

#### Version: 1 - 01/12/2020
- Migracio a Gradle.
- Manipulacion de datos a travez de archivos .dat, **los datos se modifican dentro del .jar!**
- Optimizacion de codigo.

#### Version: 0.3 - 16/08/2020

- Integracion de Maven.
- **1399 KB menos!**
  - Dependencia [miglayout](https://search.maven.org/artifact/com.miglayout/miglayout/3.7.4/jar).
  - Dependencia [swingx-autocomplete](https://search.maven.org/artifact/org.swinglabs.swingx/swingx-autocomplete/1.6.5-1/jar).
- Clase principal `Launcher` agregada.
- **FIX**: Eliminacion de los acentos sobre los nombres de NCPs que evitaban su busqueda en el JComboBox.

#### Version: 0.2 - 31/07/2020 

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

#### Version: 0.1 - 01/07/2020

Version inicial
