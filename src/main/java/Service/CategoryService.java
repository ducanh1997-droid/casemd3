package Service;

import DAO.CategoryDAO;
import Model.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    public Category findById(int id) {
        return categoryDAO.findById(id);
    }


}