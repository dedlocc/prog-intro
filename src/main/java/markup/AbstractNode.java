package markup;

public abstract class AbstractNode extends AbstractWrapper implements Node {
    public AbstractNode(final Iterable<Markup> children) {
        super(children);
    }

    protected abstract String markdownTag();

    protected abstract String htmlTag();

    protected abstract String bbTag();

    protected abstract String texTag();

    @Override
    public final void toMarkdown(final StringBuilder sb) {
        final String tag = markdownTag();
        sb.append(tag);
        super.toMarkdown(sb);
        sb.append(tag);
    }

    @Override
    public void toHtml(final StringBuilder sb) {
        final String tag = htmlTag();
        sb.append('<').append(tag).append('>');
        super.toHtml(sb);
        sb.append("</").append(tag).append('>');
    }

    @Override
    public void toBBCode(final StringBuilder sb) {
        final String tag = bbTag();
        sb.append('[').append(tag).append(']');
        super.toBBCode(sb);
        sb.append("[/").append(tag).append(']');
    }

    @Override
    public void toTex(final StringBuilder sb) {
        sb.append('/').append(texTag()).append('{');
        super.toTex(sb);
        sb.append('}');
    }
}
