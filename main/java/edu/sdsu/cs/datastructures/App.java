package edu.sdsu.cs.datastructures;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Error: only one input argument allowed");
            System.exit(1);

        } else if (args.length == 0) {
            String csvFile = "layout.csv";
            File defaultFile = new File(csvFile);
            Path defaultFilePath = Paths.get(defaultFile.getPath());
            DirectedGraph<IGraph> graphForDefault = new DirectedGraph<>();

        } else if (args.length == 1) {
            String fileName = args[0];
            File inputFile = new File(fileName);
            Path inputFilePath = Paths.get(inputFile.getPath());

            if (!inputFile.exists() || !inputFile.canRead()) {
                System.out.println("Error: Unable to open " + args[0] + ". Verify the file exists, is accessible, and meets the syntax requirements.");
                System.exit(1);
            }
            DirectedGraph<IGraph> graphForInput = new DirectedGraph<>();

        }
    }
}
