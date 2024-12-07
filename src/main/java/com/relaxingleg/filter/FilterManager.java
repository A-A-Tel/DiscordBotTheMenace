package com.relaxingleg.filter;

import com.google.gson.reflect.TypeToken;
import com.relaxingleg.Helper;
import net.dv8tion.jda.api.entities.Message;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FilterManager {

    public static ArrayList<String> bannedWords;

    private final String fileName = "bannedWords.json";

    public void loadBannedWords() {
        File file = new File(fileName);

        if (file.exists()) {
            Helper helper = new Helper();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();

            bannedWords = helper.readListFromJson(fileName, type);
        } else {
            bannedWords = new ArrayList<>();
        }
    }

    public void addBannedWord(String word) {
        Helper helper = new Helper();

        bannedWords.add(word);
        helper.saveListToJson(fileName, bannedWords);
    }

    public boolean removeBannedWord(String word) {
        Helper helper = new Helper();

        boolean success = bannedWords.remove(word);

        helper.saveListToJson(fileName, bannedWords);
        return success;
    }

    public boolean filterMessage(Message message) {

        if (message.getAuthor().isBot()) {
            return false;
        }

        FilterHelper fHelper = new FilterHelper();

        if (fHelper.isBadMessage(message.getContentRaw())) {
            message.delete().queue();
            return true;
        }
        return false;
    }
}
