package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

public class Competitor {
    public final String name;
    public final Price price;
    public final Availability availability;

    public Competitor(String name, Price price, Availability availability) {
        this.name = name;
        this.price = price;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", availability='" + availability + '\'' +
                '}';
    }
}
