package markup;

public final class Emphasis extends AbstractNode {
    public Emphasis(final Iterable<Markup> children) {
        super(children);
    }

    @Override
    protected String markdownTag() {
        return "*";
    }

    @Override
    protected String htmlTag() {
        return "em";
    }

    @Override
    protected String bbTag() {
        return "i";
    }

    @Override
    protected String texTag() {
        return "emph";
    }
}