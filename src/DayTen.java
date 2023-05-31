import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DayTen {

    public static String solve() {
        try {
            var lines = Files.readAllLines(Path.of("day10.txt"));
            var signals = new ArrayList<Integer>();
            var commands = new LinkedList<Command>();
            int x = 1;
            var ticks = 0;
            int checkSignal = 20;
            for (String line : lines) {
                var elems = line.split(" ");
                switch (elems[0]) {
                    case "addx" -> {
                        ticks += 2;
                        commands.offer(new Command(elems[0], Integer.parseInt(elems[1]), ticks));
                    }
                    case "noop" -> {
                        ticks += 1;
                        commands.offer(new Command(elems[0], ticks));
                    }
                }
            }

            var running = 0;
            while (!commands.isEmpty()) {
                running++;
                var current = commands.poll();

                if (running == checkSignal) {
                    signals.add(running * x);
                    checkSignal += 40;
                }

                if (Objects.equals(current.name, "noop")) {
                    continue;
                }

                if (current.tick == running && current.value.isPresent()) {
                    x += current.value.get();
                } else {
                    commands.addFirst(current);
                }
            }

            var sum = signals.stream().reduce(0, Integer::sum);
            return "Day 10 - Signal strength is: " + sum + ".\n";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class Command {
        private String name;
        private Optional<Integer> value;
        private Integer tick;

        public Command(String name, Integer tick) {
            this.name = name;
            this.tick = tick;
            this.value = Optional.empty();
        }

        public Command(String name, Integer value, Integer tick) {
            this.name = name;
            this.tick = tick;
            this.value = Optional.of(value);
        }
    }

}
