package features;

import shops.*;
import users.Client;
import users.Employee;
import users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Feature extends ReadWriteData{

    /**
     * COMPLETE
     * This method gets the name of the color and returns his code.
     *
     * @param color - name color. Example (RED, BLUE, RESET)
     * @return code color. Example "\u001B[0m"
     */
    public String set(String color) {
        return Color.valueOf(color).colorCode;
    }

    /**
     * COMPLETE
     * This method gets the text entered by the user and checks if it's a number or not.
     * If it is not a number, the user has to enter it again.
     *
     * @return number
     */
    public int myScanner() {
        int input = 0;
        boolean flag;
        do {
            try {
                Scanner in = new Scanner(System.in);
                input = in.nextInt();
                flag = false;
            } catch (Exception ex) {
                System.out.println(set("RED") + "Please enter a number!" + set("RESET"));
                flag = true;
            }
        } while (flag);
        return input;
    }

    /**
     * COMPLETE
     * This method creates ready-made users (clients and employees).
     * Then save them to a file "usersData.txt".
     */
    public void createBasicListUsers() {
        ArrayList<User> users = new ArrayList<>();
        Client Ruslan = new Client(0, "Ruslan", "421951305305", "LuxLux", "123456");
        Client Sam = new Client(1, "Sam", "421951305305", "1", "1");
        Client Maria = new Client(2, "Maria", "421951306306", "MariMari", "001122");
        Client Tom = new Client(3, "Tom", "421951123123", "KobiKo", "159753");
        Employee Daniela = new Employee(4, "Daniela", "421951741258", "2", "2");

        users.add(Ruslan);
        users.add(Sam);
        users.add(Maria);
        users.add(Tom);
        users.add(Daniela);
        writeUserData(users);
        System.out.println("Create Users!!!");
    }

    /**
     * COMPLETE
     * This method creates ready-made products.
     * Adds them to the ready-made store.
     * And creates copies of the store.
     * Then saves them to a file "shopsData.txt".
     */
    public void createBasicListShops() {
        ArrayList<Shop> shops = new ArrayList<>();
        Item Peony = new Flowers(0, "Peony", 3);
        Item Rose = new Flowers(1, "Rose", 5.5);
        Item Fir = new Flowers(2, "Fir", 15);
        Item Cactus = new Flowers(3, "Cactus", 7.98);
        Item Banana = new Fruits(4, "Banana", 1.65, 10,1000);

        Shop HappyChappy = new Shop(1, "HappyChappy", "Kosice Jedlikova 9");
        HappyChappy.addItem(Peony);
        HappyChappy.addItem(Rose);
        HappyChappy.addItem(Fir);

        //патерн "Composite" Компоновщик
        // Компоновщик — это структурный паттерн проектирования,
        // который позволяет сгруппировать множество объектов в
        // древовидную структуру, а затем работать с ней так,
        // как будто это единичный объект.
        HappyChappy.addItem(Cactus);
        HappyChappy.addItem(Banana);

// патерн "Prototype" клонирования магазина
        ShopFactory factory = new ShopFactory(HappyChappy);
        Shop FlowerCat = factory.CloneShop();
        Shop AsiaFlower = factory.CloneShop();
        FlowerCat.setNameAddress("FlowerCat", "Hlavna 11");
        AsiaFlower.setNameAddress("AsiaFlower", "Hlavna 3");

        shops.add(HappyChappy);
        shops.add(FlowerCat);
        shops.add(AsiaFlower);

        writeShopData(shops);
        System.out.println("Create Shops!!!");
    }
}
