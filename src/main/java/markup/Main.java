package markup;

import java.util.List;

public final class Main {
    public static void main(final String[] args) {
        final Paragraph paragraph = new Paragraph(List.of(
            new Strong(List.of(
                new Text("1"),
                new Strikeout(List.of(
                    new Text("2"),
                    new Emphasis(List.of(
                        new Text("3"),
                        new Text("4")
                    )),
                    new Text("5")
                )),
                new Text("6")
            ))
        ));

        final var sb = new StringBuilder();
        paragraph.toMarkdown(sb);
        System.out.println(sb.toString());
        sb.setLength(0);
        paragraph.toHtml(sb);
        System.out.println(sb.toString());
        sb.setLength(0);
        paragraph.toBBCode(sb);
        System.out.println(sb.toString());
        sb.setLength(0);
        paragraph.toTex(sb);
        System.out.println(sb.toString());
    }
}
