package Project;
import java.util.Scanner;

public class CityBankATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccount[] allAccounts = Database.getAllAccounts();
        int currentIndex = -1;
        while (true) {
            System.out.print("ENTER YOUR CARD NUMBER: ");
            String inputCardNumber = input.next();

            for (int i = 0; i < allAccounts.length; i++) {
                if (allAccounts[i] == null) {
                    continue;
                }
                if (allAccounts[i].getAccountNumber().equals(inputCardNumber)) {
                    System.out.println("Account successfully has been found\n");
                    currentIndex = i;

                    int tries = 3;
                    while (true) {
                        System.out.print("Enter the password: ");
                        String password = input.next();
                        if (tries >= 1) {
                            if (allAccounts[currentIndex].getPinCode().equals(password)) {
                                break;
                            } else {
                                System.out.println("Wrong password");
                                System.out.printf("You've got %x available attempts.%n", tries);
                                tries -= 1;
                            }
                        } else {
                            System.out.println("You've reached the maximum logon attempts.");
                            currentIndex = -1;
                            break;
                        }
                    }
                }
            }


            if (currentIndex != -1) {
                if (allAccounts[currentIndex] != null) {
                    System.out.printf("\nHello, %s%n", allAccounts[currentIndex].accountData());
                }

            }

            if (currentIndex != -1) {
                if (!(allAccounts[currentIndex] instanceof CityBankAccount)) {
                    System.out.println("""
                            PRESS [1] TO CASH WITHDRAWAL
                            PRESS [2] TO VIEW BALANCE
                            PRESS [3] TO EXIT""");
                    int option = input.nextInt();
                    if (option == 1) {
                        System.out.println("Enter the amount you want to withdrawal: ");
                        int inputWithdrawal = input.nextInt();
                        inputWithdrawal += 200;
                        if (inputWithdrawal <= (allAccounts[currentIndex]).totalBalance()) {
                            allAccounts[currentIndex].creditBalance(inputWithdrawal);
                        } else {
                            System.out.println("You've reached your maximum account balance limit");
                            continue;
                        }
                    }
                    if (option == 2) {
                        System.out.println("Your Current Balance:");
                        System.out.println(allAccounts[currentIndex].totalBalance());
                    }
                    if (option == 3) {
                        continue;
                    }
                }

                if (allAccounts[currentIndex] instanceof CityBankAccount) {
                    System.out.println("""
                            PRESS [1] TO CASH WITHDRAWAL
                            PRESS [2] TO VIEW BALANCE
                            PRESS [3] TO CHANGE PIN CODE
                            PRESS [4] TO CASH IN ACCOUNT
                            PRESS [5] TO VIEW ACCOUNT DATA
                            PRESS [6] TO EXIT
                            """);
                    int option = input.nextInt();
                    if (option == 1) {
                        System.out.println("Enter the amount you want to withdrawal: ");
                        int inputWithdrawal = input.nextInt();

                        if (inputWithdrawal <= allAccounts[currentIndex].totalBalance()) {
                            allAccounts[currentIndex].creditBalance(inputWithdrawal);
                        } else {
                            System.out.println("You've reached your maximum account balance limit");
                            break;
                        }
                    }
                    if (option == 2) {
                        System.out.println("Your Current Balance:");
                        System.out.println((allAccounts[currentIndex]).totalBalance());
                    }
                    if (option == 3) {
                        System.out.println("Input the new PIN code: ");
                        String inputPinCode = input.next();
                        allAccounts[currentIndex].setPinCode(inputPinCode);
                    }
                    if (option == 4) {
                        System.out.println("Enter the amount you want to cash in: ");
                        int inputCashIn = input.nextInt();
                        allAccounts[currentIndex].debitBalance(inputCashIn - 200);
                    }
                    if (option == 5) {
                        System.out.println(allAccounts[currentIndex].accountData());
                    }
                    if (option == 6) {
                        continue;
                    }
                }
                Database.setAllAccounts(allAccounts);
                currentIndex = -1;
            }
            else {
                System.out.println("Wrong Account ID or Password\n\t");
            }
        }
    }
}
