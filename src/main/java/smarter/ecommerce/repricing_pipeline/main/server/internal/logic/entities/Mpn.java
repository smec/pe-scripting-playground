package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

public class Mpn {
    private String raw;

    public String getRaw() {
        return raw;
    }

    public Mpn(String raw) {
        this.raw = raw;
    }

    @Override
    public String toString() {
        return "Mpn{" +
                "raw='" + raw + '\'' +
                '}';
    }
}
