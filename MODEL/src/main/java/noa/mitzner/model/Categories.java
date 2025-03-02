package noa.mitzner.model;

import java.util.Comparator;

public class Categories extends BaseList<Category , Categories>{
    public void getAll()
    {
        add(new Category("Lego"));
        add(new Category("Barbie"));
        add(new Category("Car"));
        add(new Category("Robots"));
        add(new Category("Dolls"));
    }

    public void sort() {
        this.sort(Comparator.comparing(Category::getName));
    }
    public boolean exist(Category category) {
        return this.contains(category);
    }
}
