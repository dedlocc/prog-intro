package markup;

public abstract class AbstractWrapper implements Markup {
    private final Iterable<Markup> children;

    public AbstractWrapper(final Iterable<Markup> children) {
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
