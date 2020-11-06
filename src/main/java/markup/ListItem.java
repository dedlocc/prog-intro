package markup;

public class ListItem implements ListComponent {
    protected final Iterable<ListComponent> items;

    public ListItem(final Iterable<ListComponent> items) {
        this.items = items;
    }

    @Override
    public void toTex(final StringBuilder sb) {
        sb.append("\\item ");
        items.forEach(i -> i.toTex(sb));
    }
}
