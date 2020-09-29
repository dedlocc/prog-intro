package classworks.cw4.bank;

public final class Bank {
    public static void main(String[] args) {
        final var c1 = new Client("Client 1", "xxx");
        final var c2 = new Client("Client 2", "yyy");
        final var acc1 = new Account(c1);
        acc1.deposit(100);
//        acc1.deposit(-10);
        acc1.deposit(10);

        final var acc2 = new Account(c2);
        acc2.transfer(acc2, 20);

        System.out.println(acc1.getAmount());
        acc2.transfer(acc1, 200);
        acc2.transfer(acc1, 20);
        System.out.println(acc2.getAmount());
    }
}
