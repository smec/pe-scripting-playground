package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

public class Price {
    private Double amount;
    private Currency currency;

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Price(Double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
