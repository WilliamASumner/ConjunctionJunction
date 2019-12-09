# ConjunctionJunction
Train Simulator for Team Conjunction Junction

## Prerequisites
* Requires [OpenJDK-13](https://jdk.java.net/13)
* For development on Windows, requires [Cygwin](https://cygwin.com/) and the Cygwin [`make` package](https://cygwin.com/install.html)

## Installation Guide
* See the [Installation Guide](https://docs.google.com/document/d/1zlLPpr5dfsBHH6GLrXF_E6YXHDVTAXLtOWcBkD1vDFg/edit?usp=sharing)

## To Run
* run `make clean` to cleanup the working directory
* run `make run` to run the application
* run `make runv VTARGET=<class-name>` to run a specific class within the application (requires a class with a `public static void main()`)

## To Create Package
* run `make package` - the result will be `cjunction.zip` or `cjunction-unix.zip` depending on your platform
