package markup;

public final class Strikeout extends AbstractNode {
    public Strikeout(final Iterable<Markup> children) {
        super(children);
    }

    @Override
    protected String markdownTag() {
        return "~";
    }

    @Override
    protected String texTag() {
        return "textst";
    }
}
