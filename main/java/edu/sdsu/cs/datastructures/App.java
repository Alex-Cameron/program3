package edu.sdsu.cs.datastructures;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        IGraph<String> newGraph = new DirectedGraph<>();

        if (args.length > 1) {
            System.out.println("Error: only one input argument allowed");
            System.exit(1);
        } else if (args.length == 0) {
            String csvFile = "/Users/cameronbailey/IdeaProjects/program3/src/main/java/edu/sdsu/cs/datastructures/layout.csv";
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
            System.out.println("Error: Unable to open file. Verify the file exists, is accessible, and meets the syntax requirements.");
            System.exit(1);
        }
        try {
            List<String> fileLines = Files.readAllLines(path, Charset.defaultCharset());
            for (String line : fileLines) {
                String[] toks = line.split(",");
                for (String tok : toks) {
                    if (!graph.contains(tok)) {
                        graph.add(tok);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



