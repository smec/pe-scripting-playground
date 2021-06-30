package smarter.ecommerce.repricing_pipeline.main.server.internal.logic.entities;

import java.util.List;

public class ShippingPrice {
    private Double amount;
    private List<String> markets;

    public Double getAmount() {
        return amount;
    }
    public List<String> getMarkets() {
        return markets;
    }

    public ShippingPrice(Double amount, List<String> markets) {
        this.amount = amount;
        this.markets = markets;
    }

    @Override
    public String toString() {
        return "ShippingPrice{" +
                "amount=" + amount +
                ", markets=" + markets +
                '}';
    }
}
