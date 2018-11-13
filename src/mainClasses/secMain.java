package mainClasses;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class secMain {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Database Main_Database=null;
        ObjectInputStream infile = null;
        try {
            infile = new ObjectInputStream(new FileInputStream("Database.txt"));
            Main_Database=(Database) infile.readObject();
            //System.out.println(Main_Database.categoriesHashMap);
            infile.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Main_Database = new Database();
            System.out.println("NO database found");
            //System.out.println(Main_Database.categoriesHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


        Warehouse_Admin Current= new Warehouse_Admin("mainStore");
        int pqwe=0;
        while(pqwe==0) {
            System.out.println("Choose Your Role\n" +
                    "1. Warehouse_Admin\n" +
                    "2. Customer\n" +
                    "3. EXIT");
            try {
                switch (in.nextInt()) {
                    case 1:
                        Admin_Main(Current,Main_Database);
                        break;
                    case 2:
                        System.out.println("Enter Your Name");
                        String name=in.next();
                        Client cust;
                        System.out.println(Main_Database.getClientHashMap().toString());
                        if(Main_Database.getClientHashMap().get(name)==null){
                            cust= new Client(name,"pass","uid");
                        }
                        else{
                            cust=Main_Database.getClientHashMap().get(name);
                        }
                        Client_Main(cust,Main_Database);
                        break;
                    case 3:
                        pqwe = 1;
                        break;
                }}
            catch (InputMismatchException e){
                System.out.println("Wrong Input");
                break;
            }
            ObjectOutputStream out = null;
            try{
                out= new ObjectOutputStream(new FileOutputStream("Database.txt"));
                out.writeObject(Main_Database);
            }
            finally {
                out.close();
            };
        }
    }
    static void Admin_Main(Warehouse_Admin Current,Database main_database) throws IOException {
        Scanner in = new Scanner(System.in);
        int k=0;
        while(k==0){
            System.out.println("Menu\n" +
                    "1. Insert Product\n" +
                    "2. Delete Product\n" +
                    "3. Search Product\n" +
                    "4. Modify Product\n" +
                    "5. Exit as Warehouse_Admin");
            try {
                switch (in.nextInt()){
                    case 1:
                        System.out.println("Enter the option\n" +
                                "1. Add Product\n" +
                                "2. Add Category/Subcategory");
                        //System.out.println(main_database.getCategoriesHashMap().toString());
                        switch (in.nextInt()){
                            case 1:
                                in.nextLine();
                                System.out.println("Enter the path");
                                try {
                                    String path = in.nextLine();
                                    System.out.println("Enter the product name");
                                    Product product= new Product(in.next());
                                    main_database.addProduct(Current,product,in.nextInt());
                                }
                                catch (InputMismatchException e){
                                    System.out.println("Wrong Input");
                                }// catch (Product_exists product_exists) {
                                  //  product_exists.printStackTrace();
                                //}
                                break;
                            case 2:
                                in.nextLine();
                                System.out.println("Enter the path");
                                main_database.addCategory(Current,in.nextLine());
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Enter the Category or the product to be deleted");
                        try {
                            in.nextLine();
                            Categories delc= new Categories(in.next());
                            main_database.delCategory(Current,delc);
                        }
                        catch (InputMismatchException e){
                            System.out.println("Wrong Input");
                        }
                        break;
                    case 3:
                        in.nextLine();
                        System.out.println("Enter the product name to be searched");
                        main_database.search(in.nextLine());
                        break;
                    case 4:
                        in.nextLine();
                        System.out.println("Enter the product name to be modified");
                        main_database.updateCategory();
                        break;
                    case 5:
                        k=1;
                        break;
                }}
            catch (InputMismatchException e){
                System.out.println("Wrong Input");
                break;
            } //catch (Product_not_found product_not_found) {
               // product_not_found.printStackTrace();
            //}
            //serialization
            System.out.println("Serializing");
            serialize(main_database);
        }
    }
    static void Client_Main(Client cust,Database main_database) throws IOException {
        Scanner in = new Scanner(System.in);
        int k=0;
        while(k==0){
            System.out.println("Menu\n" +
                    "1. Add funds\n" +
                    "2. Add product\n" +
                    "3. Check-out cart\n" +
                    "4. Exit as Customer");
            try {
                switch (in.nextInt()){
                    case 1:
                        System.out.println("Funds to be added");
                        try {
                            cust.add_funds(in.nextInt());
                            System.out.println("Funds Added");
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Wrong Input");
                        }
                        break;
                    case 2:
                        System.out.println("Name of Product");
                        cust.add_product(in.next(),in.nextInt());
                        System.out.println("Added to the Cart");
                        break;
                    case 3:
                        cust.check_out();
                        break;
                    case 4:
                        k=1;
                        break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Wrong Input");
                break;
            } //catch (Product_not_found product_not_found) {
              //  product_not_found.printStackTrace();
            //} //catch (Out_of_stock out_of_stock) {
                //out_of_stock.printStackTrace();
            //} //catch (Insufficient_funds insufficient_funds) {
                //insufficient_funds.printStackTrace();
            //}
            ObjectOutputStream out = null;
            try{
                out= new ObjectOutputStream(new FileOutputStream("Database.txt"));
                out.writeObject(main_database);
            }
            finally {
                out.close();
            }
        }
    }
    public static void serialize(Database Main_Database) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Database.txt"));
            out.writeObject(Main_Database);
            out.close();
        }
        catch(Exception e)
        {

        }
    }
    public static void deserialize(){
        Database Main_Database=null;
        ObjectInputStream  infile = null;
        try {
            infile = new ObjectInputStream(new FileInputStream("Database.txt"));
            Main_Database=(Database) infile.readObject();
            //System.out.println(Main_Database.categoriesHashMap);
            infile.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Main_Database = new Database();
            System.out.println("NO database found");
            //System.out.println(Main_Database.categoriesHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
