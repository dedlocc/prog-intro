package md2html;

import expression.parser.CharMatcher;

import java.util.*;

public final class Parser {
    private static final Map<Character, String> SPECIAL_CHARS = Map.of(
        '&', "amp",
        '<', "lt",
        '>', "gt"
    );

    private static final Map<String, String> TAGS = new LinkedHashMap<>() {{
        put("**", "strong");
        put("__", "strong");
        put("--", "s");
        put("++", "u");
        put("*", "em");
        put("_", "em");
        put("`", "code");
        put("~", "mark");
    }};

    private static final Set<Character> ESCAPABLE = Set.of('*', '_', '-', '`', '+', '~', '[', ']', '(', ')');

    private ParagraphSource src;
    private StringBuilder html;

    public String parse(final ParagraphSource src) {
        this.src = src;
        this.html = new StringBuilder();

        while (src.nextParagraph()) {
            parseParagraph();
        }

        return html.toString();
    }

    private void parseParagraph() {
        final var pos = src.position();

        var heading = 0;
        while (src.test('#')) {
            ++heading;
        }

        if (6 < heading || 0 != heading && !src.test(' ')) {
            heading = 0;
            src.reset(pos);
        }

        parseLines(0 == heading ? "p" : "h" + heading);
    }

    private void parseLines(final String outer) {
        html.append(String.format("<%s>", outer));
        parseText(html, new HashSet<>());
        html.append(String.format("</%s>%n", outer));
    }

    private void parseText(final StringBuilder html, final Collection<String> formats, final String stop, final boolean ignoreFormatting) {
        outer:
        while (!src.isEnd()) {
            if (null != stop && (!formats.contains(stop) || src.test(stop))) {
                formats.remove(stop);
                return;
            }

            if (src.test('!', '[')) {
                parseImage(html);
                continue;
            }

            if (!ignoreFormatting) {
                for (final var tag : TAGS.keySet()) {
                    if (src.test(tag)) {
                        if (formats.remove(tag)) {
                            return;
                        }
                        parseTag(tag, html, formats, stop);
                        continue outer;
                    }
                }
            }

            final var ch = src.test(CharMatcher.equals('\\'), ESCAPABLE::contains)
                ? src.current() : src.next();

            if (SPECIAL_CHARS.containsKey(ch)) {
                html.append('&').append(SPECIAL_CHARS.get(ch)).append(';');
            } else {
                html.append(ch);
            }
        }
    }

    private void parseText(final StringBuilder html, final Collection<String> formats) {
        parseText(html, formats, null, false);
    }

    private void parseTag(final String tag, final StringBuilder html, final Collection<String> formats, final String stop) {
        formats.add(tag);
        final var inner = new StringBuilder();
        parseText(inner, formats, stop, false);
        if (formats.remove(tag) || null != stop && !formats.contains(stop)) {
            html.append(tag).append(inner);
        } else {
            html.append(String.format("<%1$s>%2$s</%1$s>", TAGS.get(tag), inner));
        }
    }

    private void parseImage(final StringBuilder html) {
        final var label = new StringBuilder();
        final var formats = new HashSet<>(Set.of("]"));
        parseText(label, formats, "]", true);
        if (formats.contains("]")) {
            html.append('[').append(label);
            return;
        }
        if (!src.test('(')) {
            html.append('[').append(label).append(']');
            return;
        }
        final var destination = new StringBuilder();
        formats.clear();
        formats.add(")");
        parseText(destination, formats, ")", true);
        if (formats.contains(")")) {
            html.append('[').append(label).append("](").append(destination);
            return;
        }

        html.append(String.format("<img alt='%1$s' src='%2$s'>", label, destination));
    }
}
