package markup;

public final class Strong extends AbstractNode {
    public Strong(final Iterable<Markup> children) {
        super(children);
    }

    @Override
    protected String markdownTag() {
        return "__";
    }

    @Override
    protected String htmlTag() {
        return "strong";
    }

    @Override
    protected String bbTag() {
        return "b";
    }

    @Override
    protected String texTag() {
        return "textbf";
    }
}
