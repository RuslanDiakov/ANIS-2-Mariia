import users.DiscountCard;
import users.Client;
import users.Employee;
import users.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Start extends ReadWriteUsersData {

    private User logInUser;
    private ArrayList<User> users;
    private int idUser;

    public Start() {
        HomePage();
    }

    private void HomePage() {
        System.out.println("=-- Welcome --=");
        System.out.println("1: LogIn");
        System.out.println("2: Create account");
        System.out.println("3: View information about the store and the range of products");
        System.out.println("4: Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                LogIn();
                break;
            case 2:
                CreateAccount();
                break;
            case 3:
                ShowShopInfo();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                HomePage();
                break;
        }
    }

    private void LogIn() {
        users = ReadUsersData();
        //users.get(0).ShowUserInfo();
        Scanner in = new Scanner(System.in);
        boolean log = false;
        boolean pass = false;

        System.out.println("=-- LogIn --=");
        System.out.println("Enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        int i =0;
        for (User user : users) {
            log = Objects.equals(login, user.getLogin());
            pass = Objects.equals(password, user.getPassword());
            if (log && pass) {
                this.logInUser = user;
                idUser=i;
                break;
            }
            i++;
        }
        if (log && pass) {
            System.out.println("Access good");
            Account();
        } else {
            System.out.println("Incorrect login or password");
            LogIn();
        }

    }

    private void CreateAccount() {
        users = ReadUsersData();
        Scanner in = new Scanner(System.in);

        System.out.println("=-- Create Account --=");
        System.out.println("Enter your name: ");
        String name = in.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNumber = in.nextLine();
        System.out.println("Enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        System.out.println("Are you employee: true or false");
        String isEmployee = in.nextLine();
        if (Objects.equals(isEmployee, "false")) {
            Client newUser = new Client(name, phoneNumber, login, password);
            users.add(newUser);
            idUser = users.size()-1;
            logInUser = users.get(idUser);
        } else if (Objects.equals(isEmployee, "true")) {
            Employee newUser = new Employee(name, phoneNumber, login, password);
            users.add(newUser);
            idUser = users.size()-1;
            logInUser = users.get(idUser);
        } else {
            System.out.println("You type the wrong value: true or false");
            CreateAccount();
        }
        WriteUsersData(users);
        Account();
    }

    private void Account() {
        System.out.println("=-- Welcome " + logInUser.getName() + " --=");
        System.out.println("1: View info about my account");
        System.out.println("2: View the list of purchased products");
        System.out.println("3: Choose a store");
        System.out.println("4: Exit" + users.size());
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                UserInfo();
                break;
            case 2:
                CreateAccount();
                break;
            case 3:
                ShowShopInfo();
                break;
            case 4:
                logInUser = null;
                HomePage();
                break;
            default:
                Account();
                break;
        }
    }
    private void UserInfo(){
        logInUser.ShowUserInfo();
        System.out.println("1: Top up your account");
        System.out.println("2: Choose a discount card");
        System.out.println("3: Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                System.out.println("=-- Top up account --=");
                System.out.println("Deposit amount:");
                int amount = in.nextInt();
                logInUser.MakeDeposit(amount);
                users.set(idUser, logInUser);
                WriteUsersData(users);
                UserInfo();
                break;
            case 2:
                System.out.println("=-- Choose a discount card --=");
                
                System.out.print("Discount card: ");
                for (DiscountCard card : DiscountCard.values()) {
                    System.out.print(card + ", ");
                }
                System.out.println();
                Scanner in2 = new Scanner(System.in);
                String card = in2.nextLine();

                logInUser.SetDiscountCard(card);

                users.set(idUser, logInUser);
                WriteUsersData(users);
                UserInfo();
                break;
            case 3:
                Account();
                break;
            default:
                UserInfo();
                break;
        }
    }

    private void ShowShopInfo() {
        if (logInUser == null) {
            System.out.println("Not LogIn");
        }

        if (logInUser.isEmployee()) {
            System.out.println("You are users.Employee");
        } else {
            System.out.println("You are users.Client");
        }

        System.out.println("Good");
    }

    public void CreateBasicListUsers(){
        ArrayList<users.User> users = new ArrayList<>();
        users.Client Ruslan = new users.Client("Ruslan", "421951305305", "LuxLux", "123456");
        users.Client Maria = new users.Client("Maria", "421951306306", "MariMari", "001122");
        users.Client Tom = new users.Client("Tom", "421951123123", "KobiKo", "159753");
        users.Employee Daniela = new users.Employee("Daniela", "421951741258", "DELL", "020202");

        users.add(Ruslan);
        users.add(Maria);
        users.add(Tom);
        users.add(Daniela);
        WriteUsersData(users);
//        ArrayList<users.User> users2 = ReadUsersData();
//        users2.get(0).ShowUserInfo();
//        System.out.println();
//        users2.get(3).ShowUserInfo();
    }
}