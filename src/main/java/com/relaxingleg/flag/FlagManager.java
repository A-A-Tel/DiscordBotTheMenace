package com.relaxingleg.flag;

import com.google.gson.reflect.TypeToken;
import com.relaxingleg.Helper;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlagManager {

    private final String fileName = "flags.json";

    /*
    index = use-case (default value)

    0 = Enable chat filtering (false)
    1 = Enable leveling (true)
     */
    public static List<Boolean> flags;

    public void loadFlags() {

        File file = new File(fileName);

        if (file.exists()) {

            Helper helper = new Helper();
            Type type = new TypeToken<ArrayList<Boolean>>() {
            }.getType();

            flags = helper.readListFromJson(fileName, type);
        } else {
            flags = Arrays.asList(false, true);
        }
    }

    public boolean getFlag(int index) throws IllegalArgumentException {
        if (index >= 0 && index < flags.size()) {
            return flags.get(index);
        }
        throw new IllegalArgumentException("The index should not fall below or exceed the size of flags.");
    }

    public void setFlag(int index, boolean value) throws IllegalArgumentException {
        if (index >= 0 && index < flags.size()) {
            Helper helper = new Helper();
            flags.set(index, value);
            helper.saveListToJson(fileName, flags);
            return;
        }
        throw new IllegalArgumentException("The index should not fall below or exceed the size of flags.");
    }

    public void resetFlags() {

        Helper helper = new Helper();

        flags = Arrays.asList(false, true);
        helper.saveListToJson(fileName, flags);
    }
}
