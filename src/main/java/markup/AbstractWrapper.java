package markup;

public abstract class AbstractWrapper implements Markup {
    private final Iterable<Node> children;

    public AbstractWrapper(final Iterable<Node> children) {
        this.children = children;
    }

    @Override
    public void toMarkdown(final StringBuilder sb) {
        children.forEach(e -> e.toMarkdown(sb));
    }

    @Override
    public void toTex(final StringBuilder sb) {
        children.forEach(e -> e.toTex(sb));
    }
}
