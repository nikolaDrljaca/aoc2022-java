import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class DaySix {

    public static String solve() {
        var input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
        var counter = 0;
        var checker = new SignalChecker(4);
        for (Character ch : input.toCharArray()) {
            counter++;
            checker.add(ch);
            if (checker.checkUnique()) {
                break;
            }
        }

        return "Day 6 - Number of processed characters: " + counter + ".\n";
    }

    private static class SignalChecker {
        private int capacity;
        private LinkedList<Character> elems;

        public SignalChecker(int capacity) {
            this.capacity = capacity;
            this.elems = new LinkedList<>();
        }

        public void add(Character ch) {
            if (elems.size() == capacity) {
                elems.removeFirst();
                elems.add(ch);
            } else {
                elems.add(ch);
            }
        }

        public boolean checkUnique() {
            var unique = new HashSet<>(elems);
            return unique.size() == capacity;
        }
    }
}
