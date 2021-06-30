package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

import java.util.List;

public class EnrichedOffer {
    private String id;
    private String customerId;
    private String market;
    private Price price;
    private Availability availability;
    private SalePrice salePrice;
    private String brand;
    private Gtin gtin;
    private String title;
    private String description;
    private String condition;
    private String productLink;
    private String imageLink;
    private Mpn mpn;
    private String itemGroupId;
    private String dayOfAcquisition;
    private List<Category> categories;
    private List<Competitor> competitors;

    public EnrichedOffer(Price price, Availability availability, SalePrice salePrice, String brand, String title, String description, List<Category> categories, List<Competitor> competitors) {
        this.price = price;
        this.availability = availability;
        this.salePrice = salePrice;
        this.brand = brand;
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.competitors = competitors;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMarket() {
        return market;
    }

    public Price getPrice() {
        return price;
    }

    public Availability getAvailability() {
        return availability;
    }

    public SalePrice getSalePrice() {
        return salePrice;
    }

    public String getBrand() {
        return brand;
    }

    public Gtin getGtin() {
        return gtin;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCondition() {
        return condition;
    }

    public String getProductLink() {
        return productLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Mpn getMpn() {
        return mpn;
    }

    public String getItemGroupId() {
        return itemGroupId;
    }

    public String getDayOfAcquisition() {
        return dayOfAcquisition;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    @Override
    public String toString() {
        return "EnrichedOffer{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", market='" + market + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", salePrice=" + salePrice +
                ", brand='" + brand + '\'' +
                ", gtin=" + gtin +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", condition='" + condition + '\'' +
                ", productLink='" + productLink + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", mpn=" + mpn +
                ", itemGroupId='" + itemGroupId + '\'' +
                ", dayOfAcquisition='" + dayOfAcquisition + '\'' +
                ", categories=" + categories +
                ", competitors=" + competitors +
                '}';
    }
}
