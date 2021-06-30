package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

public class Category {
    private String categoryType;
    private String name;

    public String getCategoryType() {
        return categoryType;
    }

    public String getName() {
        return name;
    }

    public Category(String categoryType, String name) {
        this.categoryType = categoryType;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryType='" + categoryType + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
