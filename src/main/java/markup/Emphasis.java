package markup;

public final class Emphasis extends AbstractNode {
    public Emphasis(final Iterable<Node> children) {
        super(children);
    }

    @Override
    protected String markdownTag() {
        return "*";
    }

    @Override
    protected String texTag() {
        return "emph";
    }
}