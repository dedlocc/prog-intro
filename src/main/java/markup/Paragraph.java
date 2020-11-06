package markup;

public class Paragraph extends AbstractWrapper implements ListComponent {
    public Paragraph(final Iterable<Node> children) {
        super(children);
    }
}
