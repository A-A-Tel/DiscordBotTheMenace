package com.relaxingleg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public void delay(long milliseconds) {
        long awaitingTime = System.currentTimeMillis();

        while(System.currentTimeMillis() < awaitingTime);
    }

    public void saveListToJson(String name, List list) {
        try (FileWriter writer = new FileWriter(name)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(list, writer);
        } catch (IOException _) {}
    }

    public ArrayList readListFromJson(String name, Type type) {
        ArrayList list;

        try (FileReader reader = new FileReader(name)) {
            Gson gson = new Gson();
            list = gson.fromJson(reader, type);
            return list;
        } catch (IOException _) {}

        return null;
    }
}
