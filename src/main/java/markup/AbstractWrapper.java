package markup;

import java.util.function.Consumer;

public abstract class AbstractWrapper implements Markup {
    private final Iterable<Markup> children;

    public AbstractWrapper(final Iterable<Markup> children) {
        this.children = children;
    }

    private void build(Consumer<Markup> format) {
        children.forEach(format);
    }

    @Override
    public void toMarkdown(final StringBuilder sb) {
        build(e -> e.toMarkdown(sb));
    }

    @Override
    public void toHtml(final StringBuilder sb) {
        build(e -> e.toHtml(sb));
    }

    @Override
    public void toBBCode(final StringBuilder sb) {
        build(e -> e.toBBCode(sb));
    }

    @Override
    public void toTex(final StringBuilder sb) {
        build(e -> e.toTex(sb));
    }
}
