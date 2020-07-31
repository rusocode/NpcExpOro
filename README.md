# CalculadoraAO
*Calculadora multiplataforma basada en ImperiumAO que calcula el porcentaje de experiencia que otorga el NPC, la cantidad de NPCs a matar para pasar de nivel y el oro total. 
La idea original no es mía, yo solo hice algunas modificaciones, optimizando bastante el código original y agregando algunas funciones.*

![](screenshot-1.0.png)

[![Download CalculadoraAO](https://a.fsdn.com/con/app/sf-download-button)](https://sourceforge.net/projects/calculadoraao/files/)

## Características
- Nivel máximo: 50.
- Tabla de niveles (ver [aquí](https://wiki.imperiumao.com.ar/niveles)).
- Tabla de NPCs (ver [aquí](https://wiki.imperiumao.com.ar/criaturas_hostiles)).
- Calcula la cantidad total de NPCs necesarios para pasar de nivel.
- Calcula el porcentaje de experiencia que otorga cada NPC.
- Calcula la cantidad total de oro a conseguir.
- Sistema de ajustes:
	- Servidor [PVP] (exp x5 y oro x3).
	- Servidor [RPG] (exp x1 y oro x1).
	- Evento de experiencia x2.
	- Evento de oro x2.
	- Bonus adicional de +50%.
	- Bonus adicional de +100%.
	- Bonus adicional de +200%.
	- Grupos **Los renegados pierden un 10% de la experiencia total al formar grupos*.
- Búsqueda personalizable de NPCs:
	- `+exp` muestra los NPCs de mayor a menor exp.
	- `-exp` muestra los NPCs de menor a mayor exp.
	- `vida/exp` muestra los NPCs que mejor rinden en cuanto a la relación que tienen entre vida y exp.
	- `abc` ordena los nombres de los NPCs alfabéticamente.
- Interfaz gráfica de usuario administrada por la librería [MigLayout](http://www.miglayout.com/).
- Es gratis y puede usarse con fines comerciales.

## Requerimientos
#### Para compilar:
- **[JDK 8u251](https://workupload.com/file/sf3RVkNuTXJ)**
   
#### Para ejecutar:
- **[JRE 8u251](https://www.java.com/es/download/)**

## Licencia
"CalculadoraAO" es SOFTWARE LIBRE que está bajo licencia GPL versión 3 o posterior.
Esto significa que puedes usarlo, modificarlo, y hasta venderlo, pero siempre debes
DISTRIBUIR EL CÓDIGO FUENTE COMPLETO si haces cualquier modificación al mismo.
De esta forma, la comunidad podrá aprovechar tu aporte, del mismo modo que tú lo haces. 
Evitamos reinventar la rueda, y el trabajo queda protegido. Aquí FREE significa LIBRE.
Cualquier duda, revisa los términos de la GNU General Public License, en 
http://www.fsf.org y http://www.gnu.org. A continuación incluyo un resumen legal:

```
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <https://www.gnu.org/licenses/>.
```

## Créditos
CalculadoraAO Copyright (C) 2020 Juan Debenedetti

### Contacto
juandebenedetti94@gmail.com
