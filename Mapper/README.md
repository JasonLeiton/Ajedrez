# How to use Mapper Project
This project use [Maven](https://maven.apache.org/index.html) as the Software Project Management, all the dependencies
are automatically downloaded by Maven, but one of those dependencies, the GAST project not, it must be installed
manually. You can see the Maven dependencies in the [Pom.xml file](pom.xml) of this project.

To do this, first clone the [GAST project](https://github.com/Stevenpach10/GAST), then, run Maven>Install.
The GAST Project is going to be added to the local Maven repository. In this way, the GAST project can be found by the
Mapper Project (this project).

## Changes in the dependencies
Any change made to the GAST project, doesn't be reflected immediately in this Project, remember to run again
Maven>Install in the GAST project, in order to refresh de new changes in this project (maybe its also necessary to run
Maven>Verify in this  project).

## Recommendations
Maven is quite simple, some IDLE's like [IntelliJ IDEA](https://www.jetbrains.com/idea/), implement a graphic way to
use Maven, this a fast way to use the Project if you aren't familiarized with Maven.

## Use the Mapper Project in another project.
To use the functionalities provided by this project in another one, you must include the Maven dependency of this
project in your Maven pom file. The maven dependency of this project is this one:
```
<dependency>
    <groupId>avib.coderetriever</groupId>
    <artifactId>avib.coderetriever.mapper</artifactId>
    <version>1.0</version>
</dependency>
```
