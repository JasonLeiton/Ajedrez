# Método para convertir código fuente escrito en diversos lenguajes de programación a un lenguaje universal

A continuación se indican los pasos para instalar y utilizar el marco de trabajo.

## Requisitos

Se utilizará IntelliJ IDEA 2020.2 (Community Edition)

## Instalación

1. Descargar el [proyecto](https://github.com/JasonLeiton/Ajedrez/tree/proyecto-tesis).
2. Se encontrarán los proyectos: GAST y Mapper.
3. Cargar los tres proyectos en IntelliJ.
4. En el menú de Maven instalar el proyecto en el siguiente orden: GAST y Mapper. En caso de no tener menú de Maven, se activa mediante **View | Tool Windows | Maven** desde el menú principal.

## Uso

Una vez que los archivos de Maven se instalaron correctamente, se deben realizar las siguientes acciones para ejecutar el marco de trabajo.

1. Dentro del proyecto de **Mapper** ubicar el archivo *configurationTestRun.json*. Este archivo se encuentra en la dirección:

```bash
\Mapper\src\main\java\testrun\config\
```
2. El archivo tendrá la siguiente estructura:

```bash
[
    {
        "inputDirectory": "C:\\Tests\\JAVA\\Input",
        "outputDirectory": "C:\\Tests\\JAVA\\Output",
        "sourceLanguage": "JAVA",
        "semantic": false,
        "validateMap": false
    }
]
```
- **inputDirectory:** directorio donde se encuentran los archivos que se quieren analizar.
- **outputDirectory:** directorio donde se guardará el mapeo de los archivos en la carpeta de entrada y el archivo de diferencias en caso de indicarlo.
- **sourceLanguage:** lenguaje de programación que se analizará. El marco de trabajo soporta JAVA, CSHARP y RPG.
- **semantic:** bandera para realizar un análisis semántico de los archivos en la carpeta de entrada.
- **validateMap:** bandera para generar el archivo con las diferencias entre el GAST y el AST.

3. Ejecutar el archivo principal ubicado en la dirección:

```bash
\Mapper\src\main\java\testrun
```