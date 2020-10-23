package markup;

public final class Text implements Node {
    private final String text;

    public Text(final String text) {
        this.text = text;
    }

    private void append(final StringBuilder sb) {
        sb.append(text);
    }

    @Override
    public void toMarkdown(final StringBuilder sb) {
        append(sb);
    }

    @Override
    public void toHtml(final StringBuilder sb) {
        append(sb);
    }

    @Override
    public void toBBCode(final StringBuilder sb) {
        append(sb);
    }

    @Override
    public void toTex(final StringBuilder sb) {
        append(sb);
    }
}
