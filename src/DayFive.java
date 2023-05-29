import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

public class DayFive {

    public static String solve() {
        try {
            var lines = Files.readString(Path.of("day5.txt")).split("\n\n");
            //Input construction
            var input = lines[0].split("\n");
            var lastLine = input[input.length - 1].split("\\s+");
            var stackAmount = Integer.parseInt(lastLine[lastLine.length - 1]);

            var stacks = new ArrayList<Stack<String>>(stackAmount);
            for (int i = 0; i < stackAmount; i++) {
                stacks.add(new Stack<>());
            }

            for (String line : input) {
                var temp = line.split("\\s+");
                for (int i = 0; i < temp.length; i++) {
                    if (temp.length > stackAmount) continue;
                    if (!temp[i].isBlank()) {
                        stacks.get(i).add(temp[i]);
                    }
                }
            }

            //reverse stacks for proper structure
            var actStacks = new ArrayList<Stack<String>>();
            for (Stack<String> stack : stacks) {
                var temp = new Stack<String>();
                while (!stack.isEmpty()) {
                    temp.push(stack.pop());
                }
                actStacks.add(temp);
            }

            //Command parser
            var commands = lines[1].split("\n");
            for (String command : commands) {
                var cmd = command.split(" ");
                var amount = Integer.parseInt(cmd[1]);
                var from = Integer.parseInt(cmd[3]) - 1;
                var to = Integer.parseInt(cmd[cmd.length - 1]) - 1;
                var fromStack = actStacks.get(from);
                var toStack = actStacks.get(to);

                for (int i = 0; i < amount; i++) {
                    toStack.push(fromStack.pop());
                }
            }

            var b = new StringBuilder();
            b.append("Day 5 - Crates on the top of each stack: ");
            for (Stack<String> actStack : actStacks) {
                b.append(actStack.pop());
            }
            b.append("\n");
            return b.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
