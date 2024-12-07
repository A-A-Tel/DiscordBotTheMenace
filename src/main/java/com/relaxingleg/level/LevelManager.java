package com.relaxingleg.level;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.relaxingleg.Helper;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LevelManager {

    public static ArrayList<LeveledUser> users;

    private final String fileName = "levels.json";

    // Loads all previous users into memory
    public void loadUsers() {

        File file = new File(fileName);

        if (file.exists()) {

            Helper helper = new Helper();
            Type type = new TypeToken<ArrayList<LeveledUser>>() {}.getType();

            users = helper.readListFromJson(fileName, type);
        } else {

            users = new ArrayList<>();
        }
    }


    // Calculates the user's level and remaining messages
    public void setLevelStatus(User user, MessageChannel channel) {

        Helper helper = new Helper();

        for (LeveledUser leveledUser : users) {
            if (leveledUser.getId() == user.getIdLong()) {

                leveledUser.setRemaining(leveledUser.getRemaining() - 1);

                if (leveledUser.getRemaining() <= 0) {

                    leveledUser.setLevel(leveledUser.getLevel() + 1);
                    leveledUser.setRemaining((leveledUser.getLevel() + 1) * 100);

                    channel.sendMessage(user.getAsMention() + " just advanced to level " + leveledUser.getLevel() + "!").queue();
                }
                helper.saveListToJson(fileName, users);
                return;
            }
        }
        users.add(new LeveledUser(user.getIdLong()));
        helper.saveListToJson(fileName, users);
    }
}
