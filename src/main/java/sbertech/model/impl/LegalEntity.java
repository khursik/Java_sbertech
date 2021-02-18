package sbertech.model.impl;

import sbertech.model.Client;

public class LegalEntity extends Client {
    public final long inn;
    public final boolean isSanctioned;
    public LegalEntity(String name, long inn, boolean isSanctioned) {
        super(name);
        this.inn = inn;
        this.isSanctioned = isSanctioned;
    }
}
