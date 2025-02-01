package noa.mitzner.model;

public class Toy extends BaseEntity{
    private String name;
    private double price;
    private String picture;

    public Toy(String name , int price)
    {
        this.name = name;
        this.price = price;
    }
    public Toy(){}

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
