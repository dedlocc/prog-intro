package markup;

public final class Strong extends AbstractNode {
    public Strong(final Iterable<Node> children) {
        super(children);
    }

    @Override
    protected String markdownTag() {
        return "__";
    }

    @Override
    protected String texTag() {
        return "textbf";
    }
}
