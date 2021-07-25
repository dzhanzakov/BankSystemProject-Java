package Project;

public class Database {
    public static BankAccount[] allAccounts = new BankAccount[100];
    static {
        allAccounts[0] = new CityBankAccount("Ilyas", "Zhuanyshev", 20000, "KZ010322312", "0102");
        allAccounts[1] = new CityBankAccount("Erbol", "Assanbek", 10000, "KZ010322313", "0101");
        allAccounts[2] = new NationalBankAccount("Ilyas Zhuanyshev", 2000, "KZ0101112", "0102");
        allAccounts[3] = new NationalBankAccount("Yoel Romero", 3000, "KZ0101113", "1234");
    }
    private int index = 4;

    public void addAccount(BankAccount bankAccount){
        allAccounts[index] = bankAccount;
        index++;
    }

    public static BankAccount[] getAllAccounts() {
        return allAccounts;
    }

    public static void setAllAccounts(BankAccount[] allAccounts) {
        Database.allAccounts = allAccounts;
    }

}
