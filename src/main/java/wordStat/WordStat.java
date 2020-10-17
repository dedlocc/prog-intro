package wordStat;

import common.IntList;

public final class WordStat implements Comparable<WordStat> {
    private final IntList lines = new IntList();
    private final IntList indexes = new IntList();

    public void add(int line, int index) {
        lines.add(line);
        indexes.add(index);
    }

    public int size() {
        return lines.size();
    }

    public StringBuilder str() {
        final var sb = new StringBuilder();
        sb.append(' ').append(lines.size());
        for (var i = 0; i < lines.size(); ++i) {
            sb.append(' ').append(lines.get(i)).append(':').append(indexes.get(i));
        }
        return sb;
    }

    @Override
    public String toString() {
        return "WordStat {" + str().substring(1) + "}";
    }

    @Override
    public int compareTo(final WordStat o) {
        return Integer.compare(size(), o.size());
    }
}
