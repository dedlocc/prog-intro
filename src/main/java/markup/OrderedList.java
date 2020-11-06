package markup;

public final class OrderedList extends AbstractList {
    public OrderedList(final Iterable<ListItem> items) {
        super(items);
    }

    @Override
    protected String getEnvironment() {
        return "enumerate";
    }
}
