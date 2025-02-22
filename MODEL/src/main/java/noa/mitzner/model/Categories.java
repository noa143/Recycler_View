package noa.mitzner.model;

public class Categories extends BaseList<Category , Categories>{
    public void getAll()
    {
        add(new Category("Lego"));
        add(new Category("Barbie"));
        add(new Category("Car"));
        add(new Category("Robots"));
        add(new Category("Dolls"));
    }
}
