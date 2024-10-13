package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private List<Category> categories;

    public CategoryList() {
        this.categories = new ArrayList<>();
    }

    public void loadCategoryFile() {
        try {
            FileReader fr = new FileReader("src/Data/Category.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                Category c = new Category();
                if (parts.length == 2) {
                    c.setId(parts[0].trim());
                    c.setName(parts[1].trim());
                    categories.add(c);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error loading category file: ");
        }
    }

    public Category getCategoryById(String categoryId) {
        for (Category c : categories) {
            if (c.getId().equals(categoryId.trim())) {
                return c;
            }
        }
        return null;
    }
}
