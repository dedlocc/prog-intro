package markup;

public class Paragraph extends AbstractWrapper implements ListComponent {
    public Paragraph(final Iterable<Markup> children) {
        super(children);
    }
}
