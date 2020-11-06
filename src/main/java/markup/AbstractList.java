package markup;

public abstract class AbstractList implements ListComponent {
    protected final Iterable<ListItem> items;

    public AbstractList(final Iterable<ListItem> items) {
        this.items = items;
    }

    protected abstract String getEnvironment();

    @Override
    public void toTex(final StringBuilder sb) {
        final var env = getEnvironment();
        sb.append("\\begin{").append(env).append('}');
        items.forEach(i -> i.toTex(sb));
        sb.append("\\end{").append(env).append('}');
    }
}
