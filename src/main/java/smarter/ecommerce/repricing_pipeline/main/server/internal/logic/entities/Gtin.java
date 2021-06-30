package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

public class Gtin {
    private String raw;

    public String getRaw() {
        return raw;
    }

    public Gtin(String raw) {
        this.raw = raw;
    }

    @Override
    public String toString() {
        return "Gtin{" +
                "raw='" + raw + '\'' +
                '}';
    }
}
