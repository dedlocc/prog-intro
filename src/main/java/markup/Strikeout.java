package markup;

public final class Strikeout extends AbstractSpan {
    public Strikeout(final Iterable<Markup> children) {
        super(children);
    }

    @Override
    protected String markdownTag() {
        return "~";
    }

    @Override
    protected String htmlTag() {
        return "s";
    }

    @Override
    protected String bbTag() {
        return "s";
    }

    @Override
    protected String texTag() {
        return "textst";
    }
}
