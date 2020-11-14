package game;

public enum Cell {
    X,
    O,
    EMPTY {
        @Override
        public String toString() {
            return "\u00b7";
        }
    },
}
