package com.mic.investments.funds;

import java.math.BigDecimal;
import java.util.Objects;

public class FundResult extends Fund {

    public FundResult(int id, String name, FundType type, BigDecimal percentage, BigDecimal amount) {
        super(id, name, type);
        this.percentage = percentage;
        this.amount = amount;
    }

    private BigDecimal percentage;
    private BigDecimal amount;

    public BigDecimal getPercentage() {
        return percentage;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundResult fundResult = (FundResult) o;
        return Objects.equals(percentage, fundResult.percentage) &&
                Objects.equals(amount, fundResult.amount) &&
                Objects.equals(getId(), fundResult.getId()) &&
                Objects.equals(getType(), fundResult.getType()) &&
                Objects.equals(getName(), fundResult.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType(), percentage, amount);
    }

}
