package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BrandList {

    private List<Brand> brands;

    public BrandList() {
        this.brands = new ArrayList<>();
    }

    public void loadBrandFile() {
        try {
            FileReader fr = new FileReader("src/Data/Brand.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                Brand p = new Brand();
                if (parts.length == 3) {
                    p.setId(parts[0].trim());
                    p.setName(parts[1].trim());
                    p.setCountry(parts[2].trim());
                    brands.add(p);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error loading brand file: ");
        }
    }

    public Brand getBrandById(String brandId) {
        for (Brand br : brands) {
            if (br.getId().equals(brandId.trim())) {
                return br;
            }
        }
        return null;
    }
}
