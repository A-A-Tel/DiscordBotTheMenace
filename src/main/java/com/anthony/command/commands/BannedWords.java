package com.anthony.command.commands;

import com.anthony.command.ICommand;
import com.anthony.filter.FilterManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class BannedWords implements ICommand {
    @Override
    public String getName() {
        return "banned-word";
    }

    @Override
    public String getDescription() {
        return "Add, Remove, or View all of the banned words";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.STRING, "add-remove", "Add or remove a word, the default is add", false),
                new OptionData(OptionType.BOOLEAN, "remove", "Remove the given value", false));
    }

    @Override
    public DefaultMemberPermissions permission() {
        return DefaultMemberPermissions.DISABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping optionWord = event.getOption("add-remove");
        OptionMapping optionRemove = event.getOption("remove");
        String reply = "null";

        if (optionWord == null) {
            reply = "Banned words: " + FilterManager.bannedWords.toString();

        } else if (optionRemove == null || !optionRemove.getAsBoolean()) {

            FilterManager filter = new FilterManager();

            filter.addBannedWord(optionWord.getAsString().toLowerCase());
            reply = "Added '" + optionWord.getAsString().toLowerCase() + "' to the list.";
        } else {

            FilterManager filter = new FilterManager();

            if (filter.removeBannedWord(optionWord.getAsString().toLowerCase())) {
                reply = "Success";
            } else {
                reply = "Failure";
            }
        }
        event.reply(reply).queue();
    }
}
