package org.example.task2;

public class FrogCommands {

    public static FrogCommand jumpRightCommand(Frog frog, int steps) {
        return new FrogCommand() {
            @Override
            public boolean complete() {
                return frog.jump(steps);
            }

            @Override
            public boolean rewind() {
                return frog.jump(-steps);
            }
        };
    }

    public static FrogCommand jumpLeftCommand(Frog frog, int steps) {
        return new FrogCommand() {
            @Override
            public boolean complete() {
                return frog.jump(-steps);
            }

            @Override
            public boolean rewind() {
                return frog.jump(steps);
            }
        };
    }
}
