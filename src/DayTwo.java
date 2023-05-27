import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class DayTwo {
    private static class Round {
        private final String first;
        private final String second;

        public Round(String first, String second) {
            this.first = parseShape(first);
            this.second = parseShape(second);
        }

        private int getValueOfShape(String shape) {
            int out = 0;
            switch (shape) {
                case "Rock" -> out = 1;
                case "Paper" -> out = 2;
                case "Scissors" -> out = 3;
            }
            return out;
        }

        private int playRound() {
            int out = getValueOfShape(second);
            if (Objects.equals(first, second)) {
                out += 3;
                return out;
            }
            switch (first + "_" + second) {
                case "Scissors_Rock", "Rock_Paper", "Paper_Scissors" -> {
                    out += 6;
                    return out;
                }

            }
            return out;
        }

        private String parseShape(String encoding) {
            String out = "";
            switch (encoding) {
                case "A", "X" -> out = "Rock";
                case "B", "Y" -> out = "Paper";
                case "C", "Z" -> out = "Scissors";
            }
            return out;
        }
    }

    public static String solve() {
        try {
            var score = 0;
            var lines = Files.readAllLines(Path.of("day2.txt"));
            for (String line : lines) {
                var split = line.split(" ");
                var round = new Round(split[0], split[1]);
                score += round.playRound();
            }
            return "DAY2:\nMy total score is: " + score + ".\n";

        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
