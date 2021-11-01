# lme-martian-robots

Engineering Coding Challenge - Martian Robots

### Prerequisites

* Java 11
* Maven 3.8 (tested on Maven 3.8.3)

* Run the program using:
 ```
mvn spring-boot:run 
 ```

### Application commands

* Print a status of the robot positions:
```
STATUS
````
* Quit the application:
```
QUIT
````
1. Enter initial grid coordinates, e.g.
```5 3```
2. Enter robot starting position and orientation, in the form "x y [orientation]" where orientation is one of 'N' (north), 'S' (south), 'E' (east), 'W' (west), e.g.
```1 1 E```
3. Enter robot instructions in the form of a continuous string of characters from L (left), R (right), or F (forward), e.g.
```RFRFRFRF```
- Repeat steps 2-3 for each robot you wish to add/move a robot for.