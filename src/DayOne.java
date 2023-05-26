import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class DayOne {

    public static String solve() {
        try {
            var lines = Files.readAllLines(Path.of("day1.txt"));
            var max = 0;
            var curr = 0;

            for (String item : lines) {
                if (!item.isBlank()) {
                    var value = Integer.parseInt(item);
                    curr += value;
                } else {
                    if (max <= curr) {
                        max = curr;
                    }
                    curr = 0;
                }
            }
            return "The highest calorie value is: " +
                    max +
                    ".\n";
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static String solveTwo() {
        try {
            var lines = Files.readAllLines(Path.of("day1.txt"));
            var elves = new ArrayList<ArrayList<Integer>>();
            var curr = new ArrayList<Integer>();
            for (String line : lines) {
                if (!line.isBlank()) {
                    var value = Integer.parseInt(line);
                    curr.add(value);
                } else {
                    elves.add(curr);
                    curr = new ArrayList<>();
                }
            }
            var res = elves.stream()
                    .map((elf) -> elf.stream().reduce(0, Integer::sum))
                    .sorted()
                    .toList();
            var b = new StringBuilder();
            b.append("The elf ");
            b.append(res.size());
            b.append(" has the highest calorie count, which is: ");
            b.append(res.get(res.size() - 1));
            b.append(".\n");
            return b.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
