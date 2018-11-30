/*
program3
Alex Bailey
cssc0846

Cody Edgington
cssc0889
 */

package edu.sdsu.cs.datastructures;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        IGraph<String> newGraph = new DirectedGraph<>();

        if (args.length > 1) {
            System.out.println("Error: only one input argument allowed");
            System.exit(1);
        } else if (args.length == 0) {
            String csvFile = "./layout.csv";
            File defaultFile = new File(csvFile);
            Path filePath = Paths.get(defaultFile.getPath());
            readFile(filePath, defaultFile, newGraph);
        } else if (args.length == 1) {
            String argumentPath = args[0];
            File inputFile = new File(argumentPath);
            Path filePath = Paths.get(inputFile.getPath());
            readFile(filePath, inputFile, newGraph);
        }
    }

    private static void readFile(Path path, File file, IGraph<String> graph){
        if (!file.exists() || !file.canRead()) {
            System.out.println("Error: Unable to open file. Verify the file exists, " +
                    "is accessible, and meets the syntax requirements.");
            System.exit(1);
        }
        try {
            List<String> fileLines = Files.readAllLines(path, Charset.defaultCharset());
            populateGraph(fileLines, graph);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void populateGraph(List<String> fileLines, IGraph<String> graph){
        for (String line : fileLines) {
            String[] toks = line.split(",");
            for (String tok : toks) {
                if (!graph.contains(tok))
                    graph.add(tok);
            }
            if (toks.length > 1){
                graph.connect(toks[0], toks[1]);
            }
        }
        displayData(graph);
    }

    private static void displayData(IGraph<String> graph){
        System.out.println(graph.toString());
        Scanner input = new Scanner(System.in);
        System.out.println("Using the data displayed above choose a starting point and destination " +
                "to compute the shortest path and the places visited along the way!" + "\n");
        System.out.println("First enter a starting point between 1 and " + graph.size() + "." + "\n");
        int source = input.nextInt();
        if (source > graph.size() || source < 1){
            System.out.println("\n" + "You have chosen an invalid option! Please try again." + "\n");
             source = input.nextInt();
        }
        System.out.println("You have chosen " + graph.getByIndex(source) + " as a starting point!");
        System.out.println("\n" + "Now choose a destination between 1 and " + graph.size() +
                " to determine the shortest path and the places visited along the way." + "\n");
        int destination = input.nextInt();
        if (destination > graph.size() || destination < 1){
            System.out.println("\n" + "You have chosen an invalid option! Please try again." + "\n");
            destination = input.nextInt();
        }
        System.out.println("You have chosen " + graph.getByIndex(destination) + " as a destination!");
        String src = graph.getByIndex(source);
        String dest = graph.getByIndex(destination);
        List<String> s = graph.shortestPath(src, dest);
        int distance = s.size()-1;
        System.out.println("\n" + "Distance traveled: " + distance);
        System.out.println("Places visited: " + s);
        System.out.println("\n" + "Congratulations you now have an optimal route!");
    }
}



