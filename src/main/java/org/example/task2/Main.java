package org.example.task2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        List<FrogCommand> commands = new ArrayList<>();
        int currentCommand = -1;

        System.out.println("Вводите команды из списка ниже и следите за лягушкой:\n" +
                "+N - прыгнет на N шагов вправо\n" +
                "-N - прыгнет на N шагов влево\n" +
                "<< - Undo (отменит последнюю команду)\n" +
                ">> - Redo (повторит отменённую команду)\n" +
                "!! - повторит последнюю команду\n" +
                "0 - если надоело");

        Frog frog = new Frog();
        frog.showFrog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            if (checkInput(input, "+")) {
                String integer = input.replaceAll("\\D", "");
                int steps = Integer.parseInt(integer);
                FrogCommand jump = FrogCommands.jumpRightCommand(frog, steps);
                jump.complete();
                if (currentCommand != commands.size() - 1) {
                    resetCommands(currentCommand, commands);
                }
                commands.add(jump);
                currentCommand++;

            } else if (checkInput(input, "-")) {
                String integer = input.replaceAll("\\D", "");
                int steps = Integer.parseInt(integer);
                FrogCommand jump = FrogCommands.jumpLeftCommand(frog, steps);
                jump.complete();
                if (currentCommand != commands.size() - 1) {
                    resetCommands(currentCommand, commands);
                }
                commands.add(jump);
                currentCommand++;

            } else if (input.startsWith("<<")) {
                if (currentCommand < 0) {
                    System.out.println("Лягушка еще ничего не делала!");
                } else {
                    commands.get(currentCommand).rewind();
                    currentCommand--;
                }

            } else if (input.startsWith(">>")) {
                if (currentCommand == commands.size() - 1) {
                    System.out.println("Отмененных команд нет!");
                } else {
                    commands.get(++currentCommand).complete();
                }

            } else if (input.startsWith("!!")) {
                if (currentCommand < 0) {
                    System.out.println("Лягушке нечего повторять!");
                } else {
                    commands.get(currentCommand).complete();
                }

            } else if (input.startsWith("0")) {
                System.out.println("Выход...");
                break;

            } else {
                System.out.println("Лягушка такое не поймет...");
                continue;
            }
            frog.showFrog();
        }
    }

    public static boolean checkInput(String input, String prefix) {
        if (input.startsWith(prefix)) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(input);
            return matcher.find();
        }
        return false;
    }

    public static void resetCommands(int currentCommand, List<FrogCommand> commands) {
        Iterator<FrogCommand> it = commands.listIterator(currentCommand + 1);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
}

