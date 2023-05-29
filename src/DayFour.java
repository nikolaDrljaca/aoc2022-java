import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DayFour {

    public static String solve() {
        try {
            var lines = Files.readAllLines(Path.of("day4.txt"));
            var counter = 0;

            for (String line : lines) {
                var pairs = line.split(",");
                var first = new Range(pairs[0]);
                var second = new Range(pairs[1]);
                if (first.contains(second)) counter++;
                if (second.contains(first)) counter++;
            }

            return "Total number of contained paris is: " + counter + ".\n";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class Range {
        private final int first;
        private final int second;

        public Range(String value) {
            var nums = value.split("-");
            this.first = Integer.parseInt(nums[0]);
            this.second = Integer.parseInt(nums[1]);
        }

        public boolean contains(Range other) {
            return this.first >= other.first && this.second <= other.second;
        }
    }
}
