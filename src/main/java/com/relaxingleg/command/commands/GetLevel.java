package com.relaxingleg.command.commands;

import com.relaxingleg.command.ICommand;
import com.relaxingleg.level.LevelManager;
import com.relaxingleg.level.LeveledUser;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class GetLevel implements ICommand {
    @Override
    public String getName() {
        return "level";
    }

    @Override
    public String getDescription() {
        return "Returns your/the user's level";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.USER, "user", "Specify a user to return the level of", false));
    }

    @Override
    public DefaultMemberPermissions permission() {
        return DefaultMemberPermissions.ENABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        String reply = "This user has not sent a message yet!";
        OptionMapping option = event.getOption("user");

        try {
            if (option == null) {
                for (LeveledUser user : LevelManager.users) {

                    if (user.getId() == event.getUser().getIdLong()) {

                        reply = "Your level is: " + user.getLevel();
                        break;
                    }
                }
            } else {
                User optionUser = option.getAsUser();
                for (LeveledUser user : LevelManager.users) {

                    if (user.getId() == optionUser.getIdLong()) {

                        reply = optionUser.getName() + "'s level is: " + user.getLevel();
                        break;
                    }
                }
            }
        } catch (NullPointerException _) {}
        event.reply(reply).queue();
    }
}
