package com.anthony;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    private static final Logger log = LoggerFactory.getLogger(Helper.class);

    public void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
        }
    }

    public void saveListToJson(String name, List list) {
        try (FileWriter writer = new FileWriter(name)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            log.error("e: ", e);
        }
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
