import org.w3c.dom.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DaySeven {

    public static String solve() {
        try {
            //parse directory sizes
            var lines = Files.readAllLines(Path.of("day7.txt"));
            var currentPath = new Stack<String>();
            currentPath.push("root");
            var dirs = new HashMap<String, Integer>();
            for (String line : lines) {
                var elems = line.split(" ");
                switch (elems[0]) {
                    case "$" -> {
                        if (!Objects.equals(elems[1], "cd")) continue;
                        if (elems[2].contains("/")) continue;
                        if (Objects.equals(elems[2], "..")) {
                            currentPath.pop();
                        } else {
                            currentPath.push("/" + elems[2]);
                        }
                    }
                    case "dir" -> {
                    }
                    default -> {
                        var path = joinToString(currentPath);
                        var size = Integer.parseInt(elems[0]);
                        var temp = dirs.getOrDefault(path,0);
                        dirs.put(path, temp + size);
                    }
                }
            }

            //add sizes of subdirectories to parent dirs
            var paths = new ArrayList<>(dirs.keySet());
            for (String s : dirs.keySet()) {
                for (String path : paths) {
                    if(Objects.equals(s, path)) continue;
                    if(path.contains(s)) {
                        var sValue = dirs.get(s);
                        var pathValue = dirs.get(path);
                        dirs.put(s, sValue + pathValue);
                    }
                }
            }

            int counter = 0;
            for (Integer value : dirs.values()) {
                if(value < 100000) counter += value;
            }
            return "Day 7 - Sum of sizes of dirs smaller than 100000: " + counter + ".\n";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String joinToString(Stack<String> stack) {
        var b = new StringBuilder();
        stack.forEach(b::append);
        return b.toString();
    }

    private static class NodeFile {
        private String fullPath;
        private Integer size;

        public NodeFile(String path, int size) {
            this.fullPath = path;
            this.size = size;
        }
    }
}
