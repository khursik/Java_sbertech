package sbertech.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sbertech.model.impl.Holding;
import sbertech.model.impl.Individual;
import sbertech.model.impl.LegalEntity;

public enum ClientType {
    INDIVIDUAL((JSONObject clientJson) -> {
        return new Individual((String) clientJson.get("name"), (String) clientJson.get("lastname"), ((Long) clientJson.get("age")).intValue());
    }),
    LEGAL_ENTITY((JSONObject clientJson) -> {
        return new LegalEntity((String) clientJson.get("name"), (long) clientJson.get("inn"), (boolean) clientJson.get("isSanctioned"));
    }),
    HOLDING((JSONObject clientJson) -> {
        JSONArray subCompaniesJson = (JSONArray) clientJson.get("subCompanies");
        String[] subCompanies = new String[subCompaniesJson.size()];
        for (int i = 0; i < subCompanies.length; i++) {
            subCompanies[i] = (String) subCompaniesJson.get(i);
        }
        return new Holding((String) clientJson.get("name"), ((Long) clientJson.get("stockCount")).intValue(), subCompanies);
    });
    private final ClientCreator creator;
    ClientType(ClientCreator creator) {
        this.creator = creator;
    }
    public Client createClient(JSONObject object) {
        return creator.createClient(object);
    }
}

interface ClientCreator {
    Client createClient(JSONObject object);
}
