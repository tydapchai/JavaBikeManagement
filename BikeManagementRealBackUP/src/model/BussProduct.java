package model;

import FileHandle.FileHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import tool.Tools;

public class BussProduct extends HashMap<String, Product> {
    private FileHandler fileHandler;
    
    public BussProduct() {
        super();
    }

    public BussProduct(String fileName) {
        super();
        this.fileHandler = new FileHandler(fileName);
    }

    //input infomation of product
    public Product inputProductInfo(Product p) {
        if (p == null) {
            p = new Product();
        }
        do {
            p.setName(Tools.inputString("Enter name: "));
            if (p.getName().isEmpty()) {
                System.out.println("Name must not be blank, please re-enter name.");
            }
        } while (p.getName().isEmpty());
        //add brand
        do {
            BrandList b = new BrandList();
            b.loadBrandFile(); 

            String check = Tools.inputString(
                    "--------Brand list-----------\n" +
                    "B001, Giant, Taiwan\n" +
                    "B002, Trek, USA\n" +
                    "-----------------------------\n" +
                     "Enter Brand ID:");
            Brand brand = b.getBrandById(check); 
            if (brand != null) {
                p.setBrandName(brand.getName());
            } else {
                System.out.println("No such Brand ID. Please enter Brand ID in the list.");
            }
        } while (p.getBrandName() == null);
        
//add category 
        do {
            CategoryList c = new CategoryList();
            c.loadCategoryFile();
            
            String check = Tools.inputString(
                    "-------Category list--------\n" +
                    "C001, Mountain Bike\n" +
                    "C002, Hybrid Bike\n" +
                    "----------------------------\n" +
                    "Enter category ID:");
            Category category = c.getCategoryById(check);
            if(category != null){
                p.setCategoryName(category.getName());
            } else{
                System.out.println("No such Category ID. Please enter Category ID in the list.");
        }
        } while (p.getCategoryName() == null);
        
        do {
            int d = Tools.inputInt("Enter model year: ");
            p.setModelYear(d);
        } while (!Tools.checkValidYear(p.getModelYear()));

        do {
            p.setPrice(Tools.inputFloat("Enter Price: "));
            if (p.getPrice() <= 0) {
                System.out.println("Price must be greater than 0, please re-enter price.");
            }
        } while (p.getPrice() <= 0);

        return p;
    }

    //add product to hashmap
    public void addProduct() {
        do {
            String newId = generateId();
            Product p = new Product();
            p.setId(newId);

            p = inputProductInfo(p);
            this.put(p.getId(), p);
            System.out.println("Product added successfully ");
        } while (Tools.continueFunction(Tools.inputString("Add another product? y/n:")));

    }

    //update product 
    public void updateProduct() {
        do {
            String id = Tools.inputString("Enter Product ID to update: ");
            Product p = this.get(id);
            if (p == null) {
                System.out.println("NOT EXIST");
            } else {
                p = inputProductInfo(p);
                this.put(id, p);
                System.out.println("updated successfully.");
            }
        } while (Tools.continueFunction(Tools.inputString("Update another product? y/n:")));

    }

    //search product by name
    public void searchProduct() {
        do {
            String name = Tools.inputString("Enter product name:");
            BussProduct result = new BussProduct();
            name = name.trim();

            boolean found = false;
            for (Product p : this.toList()) {
                if (p.getName().contains(name)) {
                    result.put(p.getId(), p);
                    found = true;
                }
            }
            if (found) {
                result.printProductTable();
            } else {
                System.out.println("No such product name");
            }
        } while (Tools.continueFunction(Tools.inputString("search another product? y/n:")));

    }

    //convert map to array list with ascending ID
    public List<Product> toList() {
        List<Product> productList = new ArrayList<>(this.values());
        Collections.sort(productList, new Comparator<Product>() {

            @Override
            public int compare(Product t1, Product t2) {
                float compare = t1.getPrice() - t2.getPrice();
                if(compare > 0) return -1;
                if(compare < 0) return 1;
                else return t1.getName().compareTo(t2.getName());
            }
        });
        return productList;
    }

    //print out table of product
    public void printProductTable() {
        StringBuilder table = new StringBuilder();
        String header = String.format("%10s %20s %15s %15s %12s %10s",
                "ID", "Name", "Brand Name", "Category Name", "Model Year", "Price");
        table.append(header).append("\n");

        for (Product product : this.toList()) {
            table.append(String.format("%10s|%20s|%15s|%15s|%12d|%10.2f|",
                    product.getId(), product.getName(), product.getBrandName(),
                    product.getCategoryName(), product.getModelYear(), product.getPrice()))
                    .append("\n");
        }
        System.out.println(table.toString());
    }

    //auto generate a unique id 
    private String generateId() {
        int maxID = 0;
        for (String id : this.keySet()) {
            String numberPart = id.substring(1);
            int idNumber = Integer.parseInt(numberPart);
            if (idNumber > maxID) {
                maxID = idNumber;
            }
        }
        maxID++;
        return String.format("P%03d", maxID);
    }

    //load product to a file
    public void loadFromFile() {
        List<Product> tempList = new ArrayList<>();
        fileHandler.loadFromFile(tempList);

        for (Product p : tempList) {
            this.put(p.getId(), p);
        }
    }

    //save product to a file
    public void saveToFile() {
        do {
            if (fileHandler.saveToFile(this.toList())) {
                System.out.println("save succesfully");
            } else {
                System.out.println("Can not save file");
            }
        } while (Tools.continueFunction(Tools.inputString("Save another file? y/n:")));
    }

}
