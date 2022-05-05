## Change Log (hacemos todo lo posible para adherirnos a [semantic versioning](https://semver.org/))

#### Version: 1.1.0 - 2022/05/04
- Se cambio el nombre del proyecto a _**NpcExpOro**_ por gusto personal.
- Se volvio a implementar Maven.
- Se mejoro el rendimiento de la aplicacion usando JDK 15. 

#### Version: 1.0.0 - 2020/12/01
- Migracio a Gradle.
- Ahora los datos (nombres, niveles, etc.) se pueden editar en los archivos **.dat** desde dentro del .jar.

#### Version: 0.3.1 - 2020/08/18
- **Fix**: Hay nombres de NPCs que el combo no puede encontrar.

#### Version: 0.3.0 - 2020/08/16
- Integracion de Maven para la construccion del proyecto.
- Se agrego la libreria [miglayout](https://search.maven.org/artifact/com.miglayout/miglayout/3.7.4/jar) para que el diseño de la GUI sea mas facil.
- Se reemplazo la libreria swingx-all por [swingx-autocomplete](https://search.maven.org/artifact/org.swinglabs.swingx/swingx-autocomplete/1.6.5-1/jar) ahorrando un par de KBs!
- Ahora hay una clase principal `Main` encargada de ejecutar la aplicacion.

#### Version: 0.2.0 - 2020/07/31
- Se agrego la libreria [swingx-all](https://search.maven.org/artifact/org.swinglabs.swingx/swingx-all/1.6.5-1/jar) para tipear el nombre del NPC dentro del combo.
- Ahora se puede calcular el bonus adicional sobre la experiencia recibida usando +50%, +100% y +200%.
- Nuevas funciones de busqueda sobre el combo de NPCs:
  - `abc` muestra los NPCs alfabeticamente.
  - `vida/exp` muestra los NPCs que mejor rinden en cuanto a la relacion que tienen entre vida y experiencia.
  - `+exp` muestra los NPCs de mayor a menor experiencia.
  - `-exp` muestra los NPCs de menor a mayor experiencia.
- `Acerca de...` muestra los detalles del software.

#### Version: 0.1.0 - 2020/07/01
Version inicial.
