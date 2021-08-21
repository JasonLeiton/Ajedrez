# How to use Code Retriever Project
This project use [Maven](https://maven.apache.org/index.html) as the Software Project Management, all the dependencies
are automatically downloaded by Maven, but two of those dependencies, the GAST project, and the Mapper project not, they
must be installed manually. You can see the Maven dependencies in the [Pom.xml file](pom.xml) of this project.

To do this, first clone the [GAST project](https://github.com/Stevenpach10/GAST) and the
[Mapper project](https://github.com/Stevenpach10/Mapper), then, run Maven>Install on both projects. These projects are
going to be added to the local Maven repository. In this way, the projects can be found by the Code Retriever Project
(this project).

## Changes in the dependencies
Any change made to the imported projects, don't be reflected immediately in this Project, remember to run again
Maven>Install in the GAST project, or the Mapper project, in order to refresh de new changes in this project (maybe its
also necessary to run Maven>Verify in this  project).

## Recommendations
Maven is quite simple, some IDLE's like [IntelliJ IDEA](https://www.jetbrains.com/idea/), implement a graphic way to
use Maven, this a fast way to use the Project if you aren't familiarized with Maven.