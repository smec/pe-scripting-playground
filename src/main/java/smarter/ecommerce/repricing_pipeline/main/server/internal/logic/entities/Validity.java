package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

public class Validity {
    private Long from;
    private Long to;

    public Long getFrom() {
        return from;
    }
    public Long getTo() {
        return to;
    }

    public Validity(Long from, Long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Validity{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
