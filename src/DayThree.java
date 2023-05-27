import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DayThree {

    public static String solve() {
        try {
            var lines = Files.readAllLines(Path.of("day3.txt"));
            var commons = new HashSet<Character>();

            lines.forEach((line) -> {
                var chars = line.toCharArray();
                var middleIndex = chars.length / 2;
                var first = Arrays.copyOfRange(chars, 0, middleIndex);
                var second = Arrays.copyOfRange(chars, middleIndex, chars.length);
                for (char aChar : chars) {
                    if (contains(first, aChar) && contains(second, aChar)) {
                        commons.add(aChar);
                    }
                }
            });

            var totalPrio = commons.stream().map((it) -> {
                var value = (int) it;
                if (value < 90) {
                    value -= 38;
                } else {
                    value -= 96;
                }
                return value;
            }).reduce(0, Integer::sum);
            return "Day 3:\nPriority sum is: " + totalPrio + ".\n";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean contains(char[] arr, char needle) {
        for (char c : arr) {
            if (Objects.equals(c, needle)) return true;
        }
        return false;
    }
}
