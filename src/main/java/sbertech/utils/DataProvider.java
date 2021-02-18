package sbertech.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sbertech.model.Client;
import sbertech.model.ClientType;
import sbertech.model.impl.Holding;
import sbertech.model.impl.Individual;
import sbertech.model.impl.LegalEntity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataProvider {
    private static JSONParser parser;

    private static JSONObject getJsonObject(String filePath) throws IOException, ParseException {
        if (parser == null) {
            parser = new JSONParser();
        }
        return (JSONObject) parser.parse(new FileReader(filePath));
    }

    public static Client getClientFromFileSwitch(String filePath) throws IOException, ParseException {
        var clientJson = DataProvider.getJsonObject(filePath);
        var clientType = (String) clientJson.get("clientType");
        Client client;
        switch (clientType) {
            case "INDIVIDUAL":
                client = new Individual((String) clientJson.get("name"), (String) clientJson.get("lastname"), ((Long) clientJson.get("age")).intValue());
                break;
            case "HOLDING":
                JSONArray subCompaniesJson = (JSONArray) clientJson.get("subCompanies");
                String[] subCompanies = new String[subCompaniesJson.size()];
                for(int i = 0; i<subCompanies.length; i++){
                    subCompanies[i]= (String) subCompaniesJson.get(i);
                }
                client = new Holding((String) clientJson.get("name"), ((Long) clientJson.get("stockCount")).intValue(), subCompanies);
                break;
            case "LEGAL_ENTITY":
                client = new LegalEntity((String) clientJson.get("name"), (long) clientJson.get("inn"), (boolean) clientJson.get("isSanctioned"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + clientType);
        }
        return client;
    }
    public static Client getClientFromFileEnum(String filePath) throws IOException, ParseException {
        var clientJson = DataProvider.getJsonObject(filePath);
        var clientType = ClientType.valueOf((String) clientJson.get("clientType"));
        return clientType.createClient(clientJson);
    }
}
