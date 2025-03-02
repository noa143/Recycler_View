package noa.mitzner.model;

import java.util.Objects;

public class Category extends BaseEntity{
    private String name;

    public Category(String name)
    {
        this.name = name;
    }
    public String getName() {

        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category category = (Category) obj;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
