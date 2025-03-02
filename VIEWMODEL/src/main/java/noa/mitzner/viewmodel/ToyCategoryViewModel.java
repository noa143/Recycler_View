package noa.mitzner.viewmodel;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Comparator;
import java.util.List;

import noa.mitzner.model.Categories;
import noa.mitzner.model.Category;

public class ToyCategoryViewModel extends ViewModel {
    private Categories categories;
    private MutableLiveData<Categories> toyCategoriesLiveData;

    public ToyCategoryViewModel(){
        categories = new Categories();
        categories.getAll();
        categories.sort();

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
        if (exist(category)) {
            Log.d("ToyCategoryViewModel", "this category is already exist");
            return;
        }
        Categories currentCategories = toyCategoriesLiveData.getValue();
        if (currentCategories != null) {
            currentCategories.add(category);
            toyCategoriesLiveData.setValue(currentCategories);
        }
    }
    public void delete(Object object) {
        Categories currentCategories = toyCategoriesLiveData.getValue();
        if (currentCategories != null) {
            currentCategories.remove(object);
            toyCategoriesLiveData.setValue(currentCategories);
        }
    }
    public void Update(Object oldObject, Object newObject) {
        Categories currentCategories = toyCategoriesLiveData.getValue();
        if (currentCategories != null) {
            int index = currentCategories.indexOf(oldObject);
            if (index != -1) {
                currentCategories.set(index, (Category) newObject);
                toyCategoriesLiveData.setValue(currentCategories);
            }
        }
    }
    public void sortCategories() {
        Categories currentCategories = toyCategoriesLiveData.getValue();
        if (currentCategories != null) {
            currentCategories.sort();
            toyCategoriesLiveData.setValue(currentCategories);
        }
    }
    public boolean exist(Category category) {
        Categories currentCategories = toyCategoriesLiveData.getValue();
        return currentCategories != null && currentCategories.exist(category);
    }
}
