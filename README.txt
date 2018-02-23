Para ejecutar la solución se tiene que compilar con la ayuda de la herramienta de construcción Maven

•	Descomprimir mercader-galactico.zip
•	Ingresar mercader-galactico
•	Ejecutar mvn clean package
•	Copiar el archivo de entrada (Input.txt junto a el Jar generado)
•	Ejecutar el jar con dependencias generado
	o	Java –jar mercader-galactico-1.0-SNAPSHOT-jar-with-dependencies.jar Input.txt

Para la solución se utilizó los principios de la programación orientada a objetos.
Se dividió las transacciones de entrada en:

•	Asignar valores a números romanos (glob is I)
•	Valor por unidad del metal raro (glob glob Silver is 34 Credits)
•	Expresión de moneda galáctica (how much is pish tegj glob glob ?)
•	Evaluación de créditos transacción con metal raro (how many Credits is glob prok Silver ?)

Como suposiciones, se basaron algunos cálculos guiándonos en las entradas y salidas de ejemplo. Unas suposiciones serían:

•	Las transacciones de moneda galáctica es una transformación de números romanos a decimales.
•	La evaluación de créditos indica que se debe multiplicar el valor decimal del número romano por el valor de los metales raros.

Con un número de parámetros definido para cada transacción los algoritmos pudieron ser más precisos.
