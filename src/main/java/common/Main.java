package common;

import java.io.FileReader;
import java.io.IOException;

public final class Main {
    public static void main(String[] args) throws IOException {
        var scanner = new FastScanner(new FileReader("file.txt"));
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
