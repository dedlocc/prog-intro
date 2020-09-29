package classworks.cw4.bank;

public final class Client {
    private final String name;
    private final String id;

    public Client(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
