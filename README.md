# Instrucciones Generales

## Procesamiento de código fuente utilizando el GAST
1. Clone el repositorio con el branch llamado "Proyecto-Tesis"
2. Siga las instrucciones establecidas en el archivo Readme de dicho branch

## Visualización en la base de datos. 
1. Descargue e instale Neo4j del siguiente link: https://neo4j.com/download/
2. Cree una base de datos en el editor de Neo4j. 
3. Clone el repositorio utilizando el branch main
4. Ejecute en una terminal el archivo llamado FactsAnalyzer utilizando el siguiente comando: java -jar FactsAnalyzer <parameter list>, donde parameter list corresponde a: 
  
        4.1 o: Dueño del repositorio.

        4.2 r: Repositorio.

        4.3 g: URL del repositorio

        4.4 n: Nombre del Poyecto

        4.5 u: Autenticación de usuario para el usuario

        4.6 p: Password para autenticar

        4.7 d: Directorio local

        4.8 c: Charset de decodificación

        4.9 l: Lenguaje del proyecto

        4.10 b: String de conexión con la base de datos

        4.11 f: Inicio de los commits del repositorio a analizar

        4.12 t: Final de commi del rpositorio a analizar

        4.13 s: Indica si crear un proyecto en la base de datos

        4.14 h: Hilos simultáneamente
 
5. Un ejemplo de ejecución de lo anterior sería el siguiente comando: 
  ```bash
  java -jar FactsAnalyzer -o JasonLeiton -r Ajedrez -g https://github.com/JasonLeiton/Ajedrez -n Ajedrez -u JasonLeiton -p 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 -d C:\\Users\\Computadores\\Downloads\\ResultadosTesis\\output -c UTF-8 -l JAVA -b neo4j://localhost:7687,neo4j,123 -f1 -t1 -s -h10 
  ```

6. Utilice la vizualización de Neo4j para analizar los grafos generados por la base de datos.
