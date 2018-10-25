package com.mic.investments.funds;

public class Fund {

    private int id;
    private String name;
    private FundType type;

    public Fund(int id, String name, FundType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FundType getType() {
        return type;
    }

}

