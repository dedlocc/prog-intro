package markup;

public abstract class AbstractNode extends AbstractWrapper implements Node {
    public AbstractNode(final Iterable<Node> children) {
        super(children);
    }

    protected abstract String markdownTag();

    protected abstract String texTag();

    @Override
    public final void toMarkdown(final StringBuilder sb) {
        final String tag = markdownTag();
        sb.append(tag);
        super.toMarkdown(sb);
        sb.append(tag);
    }

    @Override
    public void toTex(final StringBuilder sb) {
        sb.append('\\').append(texTag()).append('{');
        super.toTex(sb);
        sb.append('}');
    }
}
