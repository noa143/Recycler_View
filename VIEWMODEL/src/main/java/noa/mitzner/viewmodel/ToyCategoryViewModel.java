package noa.mitzner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import noa.mitzner.model.Categories;
import noa.mitzner.model.Category;

public class ToyCategoryViewModel extends ViewModel {
    private Categories categories;
    private MutableLiveData<Categories> toyCategoriesLiveData;

    public ToyCategoryViewModel(){
        categories = new Categories();
        categories.getAll();

        toyCategoriesLiveData = new MutableLiveData<>();
        toyCategoriesLiveData.setValue(categories);
    }

    public Categories getCategories() {
        return categories;
    }

    public MutableLiveData<Categories> getToyCategoriesLiveData() {
        return toyCategoriesLiveData;
    }

    public void addCategory(Category category) {
        List<Category> currentCategories = toyCategoriesLiveData.getValue();
        if (currentCategories != null) {
            currentCategories.add(category);
            toyCategoriesLiveData.setValue((Categories) currentCategories);
        }
    }
}
