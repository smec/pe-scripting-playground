package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

public class PriceIncludingTax {
    private Double amount;
    private Currency currency;

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public PriceIncludingTax(Double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "PriceIncludingTax{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
