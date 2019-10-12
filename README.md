# ConjunctionJunction
Train Simulator for Team Conjunction Junction

## To run test javafx app
* Make sure you have [OpenJDK-13](https://jdk.java.net/13)
* run `make runtest` to see if the sample application will work on your computer
* run `make clean` to cleanup the working directory

## Notes:
* running `make` or `make all` needs all of our modules to be present
* The naming convention the makefile uses is now

|     Module     | Java Filename |
|:--------------:|:-------------:|
|Track Controller|      TkC.java |
|Track Model     |      TkM.java |
|Train Model     |      TrM.java |
|Train Controller|      TrC.java |
|CTC             |      CTC.java |

This isn't final but just a quickly made up convention to test the makefile

* changes should be through a new branch you create for your module, that way we can work independently and not step on eachothers' toes

