# Game of Life Lab
Java-based GUI simulation of John H. Conway's "Game of Life".

## Usage
Ensure you have Java installed. Then, run the main method of the `Life.java` file.

## Advanced Usage
We allow you to use a custom configuration file, which lets you determine how the grid will look at the start of program execution. By default, the program starts with a random population. To use such a file, manually modify the main method of `Life.java` to use a different constructor when initializing the `Life` object, as follows:

```java
Life conway = new Life("blinker.lif"); // Replace with path to your configuration file
```

We provide four sample configuration files in this repository: `blinker.lif`, `glgun13.lif`, `penta.lif`, and `tumbler.lif`.

## Video Demo
[YouTube Link](https://www.youtube.com/watch?v=l--tlU7lZK4)
