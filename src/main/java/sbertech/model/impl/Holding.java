package sbertech.model.impl;

import sbertech.model.Client;

public class Holding extends Client {
    public final int stockCount;
    public final String[] subCompanies;
    public Holding(String name, int stockCount, String[] subCompanies) {
        super(name);
        this.stockCount = stockCount;
        this.subCompanies = subCompanies;
    }
}
