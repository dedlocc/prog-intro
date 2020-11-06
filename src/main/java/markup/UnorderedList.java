package markup;

public final class UnorderedList extends AbstractList {
    public UnorderedList(final Iterable<ListItem> items) {
        super(items);
    }

    @Override
    protected String getEnvironment() {
        return "itemize";
    }
}
