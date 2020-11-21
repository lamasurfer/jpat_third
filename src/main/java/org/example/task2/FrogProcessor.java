package org.example.task2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrogProcessor {

    private final List<FrogCommand> commands = new ArrayList<>();
    private int currentCommand = -1;
    private Frog frog;
    private boolean finished = false;

    //попробовал еще и через CoR эту задачу решить

    private final List<Processor> processors = Arrays.asList(
            (input) -> {
                if (checkInput(input, "+")) {
                    String integer = input.replaceAll("\\D", "");
                    int steps = Integer.parseInt(integer);
                    FrogCommand jump = FrogCommands.jumpRightCommand(frog, steps);
                    jump.complete();
                    if (currentCommand != commands.size() - 1) {
                        resetCommands(currentCommand);
                    }
                    commands.add(jump);
                    currentCommand++;
                    return true;
                }
                return false;
            },
            (input) -> {
                if (checkInput(input, "-")) {
                    String integer = input.replaceAll("\\D", "");
                    int steps = Integer.parseInt(integer);
                    FrogCommand jump = FrogCommands.jumpLeftCommand(frog, steps);
                    jump.complete();
                    if (currentCommand != commands.size() - 1) {
                        resetCommands(currentCommand);
                    }
                    commands.add(jump);
                    currentCommand++;
                    return true;
                }
                return false;
            },
            (input) -> {
                if (input.startsWith("<<")) {
                    if (currentCommand < 0) {
                        System.out.println("Лягушка еще ничего не делала!");
                    } else {
                        commands.get(currentCommand).rewind();
                        currentCommand--;
                    }
                    return true;
                }
                return false;
            },
            (input) -> {
                if (input.startsWith(">>")) {
                    if (currentCommand == commands.size() - 1) {
                        System.out.println("Отмененных команд нет!");
                    } else {
                        commands.get(++currentCommand).complete();
                    }
                    return true;
                }
                return false;
            },
            (input) -> {
                if (input.startsWith("!!")) {
                    if (currentCommand < 0) {
                        System.out.println("Лягушке нечего повторять!");
                    } else {
                        commands.get(currentCommand).complete();
                    }
                    return true;
                }
                return false;
            },
            (input) -> {
                if (input.startsWith("0")) {
                    System.out.println("Выход...");
                    finished = true;
                    return true;
                }
                return false;
            },
            (input) -> {
                System.out.println("Лягушка такое не поймет...");
                return true;
            }
    );

    public FrogProcessor(Frog frog) {
        this.frog = frog;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean checkInput(String input, String prefix) {
        if (input.startsWith(prefix)) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(input);
            return matcher.find();
        }
        return false;
    }

    public void resetCommands(int currentCommand) {
        Iterator<FrogCommand> it = commands.listIterator(currentCommand + 1);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public void startProcessing(String input) {
        for (Processor processor : processors) {
            if (processor.process(input)) {
                break;
            }
        }
    }

    public void showRules() {
        final String rules = "Вводите команды из списка ниже и следите за лягушкой:\n" +
                "+N - прыгнет на N шагов вправо\n" +
                "-N - прыгнет на N шагов влево\n" +
                "<< - Undo (отменит последнюю команду)\n" +
                ">> - Redo (повторит отменённую команду)\n" +
                "!! - повторит последнюю команду\n" +
                "0 - если надоело";
        System.out.println(rules);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Frog frog = new Frog();
        FrogProcessor frogProcessor = new FrogProcessor(frog);
        frogProcessor.showRules();
        frog.showFrog();

        while (true) {
            String input = scanner.nextLine().trim();
            frogProcessor.startProcessing(input);
            if (frogProcessor.isFinished()) {
                break;
            }
            frog.showFrog();
        }
    }
}

