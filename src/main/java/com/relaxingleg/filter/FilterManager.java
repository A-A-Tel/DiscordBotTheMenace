package com.relaxingleg.filter;

import com.google.gson.reflect.TypeToken;
import com.relaxingleg.Helper;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FilterManager {

    public static ArrayList<String> bannedWords;
    public static ArrayList<User> whitelistedUsers;

    private final String bannedFileName = "bannedWords.json";
    private final String whitelistFileName = "whitelistedUsers.json";

    public void loadFilter() {
        File file = new File(bannedFileName);

        if (file.exists()) {
            Helper helper = new Helper();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();

            bannedWords = helper.readListFromJson(bannedFileName, type);
        } else {
            bannedWords = new ArrayList<>();
        }

        file = new File(whitelistFileName);

        if (file.exists()) {
            Helper helper = new Helper();
            Type type = new TypeToken<ArrayList<User>>() {}.getType();

            whitelistedUsers = helper.readListFromJson(whitelistFileName, type);
        } else {
            whitelistedUsers = new ArrayList<>();
        }
    }

    public void addBannedWord(String word) {
        Helper helper = new Helper();

        bannedWords.add(word);
        helper.saveListToJson(bannedFileName, bannedWords);
    }

    public boolean removeBannedWord(String word) {
        Helper helper = new Helper();

        boolean success = bannedWords.remove(word);

        helper.saveListToJson(bannedFileName, bannedWords);
        return success;
    }

    public void addWhitelistedUser(User user) {
        Helper helper = new Helper();

        whitelistedUsers.add(user);
        helper.saveListToJson(whitelistFileName, whitelistedUsers);
    }

    public boolean removeWhitelistedUser(User user) {
        Helper helper = new Helper();

        boolean success = whitelistedUsers.remove(user);

        helper.saveListToJson(whitelistFileName, whitelistedUsers);
        return success;
    }

    public boolean filterMessage(Message message) {

        if (message.getAuthor().isBot()) {
            return false;
        }

        for (User user : whitelistedUsers) {

            if (user.equals(message.getAuthor())) {
                return false;
            }
        }

        FilterHelper fHelper = new FilterHelper();

        if (fHelper.isBadMessage(message.getContentRaw())) {
            message.delete().queue();
            return true;
        }
        return false;
    }
}
