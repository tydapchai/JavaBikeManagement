package FileHandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class FileHandler {

    List<Product> temp = new ArrayList<>();
    private String pathFile;

    public FileHandler(String pathFile) {
        this.pathFile = pathFile;
    }

    //save list to main file
    public boolean saveToFile(List<Product> productList) {
        boolean status = false;
        try {
            FileWriter fw = new FileWriter(this.pathFile, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Product p : productList) {
                String line = String.format("%s, %s, %s, %s, %d, %.2f",
                        p.getId(), p.getName(), p.getBrandName(), p.getCategoryName(),
                        p.getModelYear(), p.getPrice());
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            fw.close();
            status = true;
        } catch (IOException e) {
            System.out.println("Error while accessing file: ");
        }
        return status;
    }

    //load the main file
    public void loadFromFile(List<Product> productList) {

        try {
            FileReader fr = new FileReader(this.pathFile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                Product p = new Product();
                if (parts.length == 6) {
                    p.setId(parts[0].trim());
                    p.setName(parts[1].trim());
                    p.setBrandName(parts[2].trim());
                    p.setCategoryName(parts[3].trim());
                    p.setModelYear(Integer.parseInt(parts[4].trim()));
                    p.setPrice(Float.parseFloat(parts[5].trim()));
                }
                productList.add(p);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: ");
        } catch (IOException e) {
            System.out.println("Error while accessing file: ");
        }
    } 
}
