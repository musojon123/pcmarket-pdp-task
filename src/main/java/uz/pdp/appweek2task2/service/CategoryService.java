package uz.pdp.appweek2task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return null;
        return optionalCategory.get();
    }

    public ApiResponse addCategory(Category category){
        if (categoryRepository.existsByName(category.getName()))
            return new ApiResponse(false, "Already exist") ;
        Category savedCategory = categoryRepository.save(category);
        return new ApiResponse(true, "Successfully added", savedCategory.getId());
    }

    public ApiResponse editCategory(Integer id, Category category){
        if (categoryRepository.existsByNameAndIdNot(category.getName(), id))
            return new ApiResponse(false, "Already exist");
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new ApiResponse(false, "No such category with this given id", id);
        Category editingCategory = optionalCategory.get();
        editingCategory.setName(category.getName());
        editingCategory.setActive(category.getActive());
        categoryRepository.save(editingCategory);
        return new ApiResponse(true, "Successfully edited", id);
    }

    public ApiResponse deleteCategory(Integer id){
        if (!categoryRepository.existsById(id))
            return new ApiResponse(false, "No such category with this given id", id);
        categoryRepository.deleteById(id);
        return new ApiResponse(true, "Successfully deleted", id);
    }

}
