package classworks.cw4.bank;

public final class Account {
    private final Client client;
    private int amount;

    public Account(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public int getAmount() {
        return amount;
    }

    public void deposit(int money) {
        assert money >= 0 : "Money are negative";
        amount += money;
    }

    public boolean withdraw(int money) {
        if (money > amount) {
            return false;
        }
        amount -= money;
        return true;
    }

    public boolean transfer(Account from, int money) {
        boolean success = from.withdraw(money);
        if (success) {
            deposit(money);
        }
        return success;
    }
}
