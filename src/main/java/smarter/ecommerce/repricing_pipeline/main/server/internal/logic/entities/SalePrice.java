package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

public class SalePrice {
    private Double amount;
    private Currency currency;
    private Validity validity;

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Validity getValidity() {
        return validity;
    }

    public SalePrice(Double amount, Currency currency, Validity validity) {
        this.amount = amount;
        this.currency = currency;
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "SalePrice{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", validity=" + validity +
                '}';
    }
}
