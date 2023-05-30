import java.util.ArrayList;
import java.util.HashSet;

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
        private ArrayList<Character> elems;

        public SignalChecker(int capacity) {
            this.capacity = capacity;
            this.elems = new ArrayList<>(capacity);
        }

        public void add(Character ch) {
            if (elems.size() == capacity) {
                elems.remove(0);
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
