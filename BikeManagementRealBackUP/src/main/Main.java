package main;

import model.BussProduct;
import model.Product;
import tool.Tools;

public class Main {

    public static void main(String[] args) {
        int choice = 0;
        String pathFile = "src/Data/Product.txt";
        BussProduct ds = new BussProduct(pathFile);
        ds.loadFromFile();
        do {
            choice = Tools.inputInt(
                    "---------------------------MENU---------------------------------\n"
                    + "             1. Add a product. \n"
                    + "             2. Search product by product name.\n"
                    + "             3. Update product. \n"
                    + "             4. Delete product. \n"
                    + "             5. Save to file. \n"
                    + "             6. Print list products from the file. \n"
                    + "             0. Exit. \n"
                    + "----------------------------------------------------------------\n"
                    + "Please enter your choice:");

            switch (choice) {
                case 1://create a product
                    ds.addProduct();
                    break;
                    
                case 2://search product infomation by name
                    ds.searchProduct();
                    break;
                    
                case 3://update product information
                    ds.updateProduct();
                    break;
                    
                case 4://delete product
                    do {
                        String id = Tools.inputString("Input product ID to remove:");
                        Product check = ds.get(id);
                        if (check == null) {
                            System.out.println("Product does not exist");
                        } else {
                            ds.remove(id);
                            System.out.println("Remove successfully product");
                        }
                    } while (Tools.continueFunction(Tools.inputString("delete another product? y/n:")));
                    break;
                    
                case 5://save to file
                    ds.saveToFile();
                    break;
                    
                case 6://print list after save 
                    do {
                        BussProduct tempds = new BussProduct(pathFile);
                        //ds.printProductTable();
                        tempds.loadFromFile();
                        tempds.printProductTable();
                    } while (Tools.continueFunction(Tools.inputString("print another file? y/n:")));

                    break;
                    
                case 0:
                    System.out.println("Exiting program...");

                    break;
                    
                default:
                    System.out.println("Invalid choice, please enter a number between 0 and 6.");

                    break;
            }
        } while (choice != 0);
    }

}
