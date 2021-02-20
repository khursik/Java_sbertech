package sbertech.model.impl;

import sbertech.model.Client;

public class Individual extends Client {
    public final String lastname;
    public final int age;

    public Individual(String name, String lastname, int age) {
        super(name);
        this.age = age;
        this.lastname = lastname;
    }
}
