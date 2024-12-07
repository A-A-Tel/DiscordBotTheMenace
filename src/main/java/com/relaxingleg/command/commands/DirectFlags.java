package com.relaxingleg.command.commands;

import com.relaxingleg.command.ICommand;
import com.relaxingleg.flag.FlagManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class DirectFlags implements ICommand {
    @Override
    public String getName() {
        return "flag";
    }

    @Override
    public String getDescription() {
        return "Manage/get the value of flags";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(new OptionData(OptionType.INTEGER, "index", "The index of the flag", false),
                new OptionData(OptionType.BOOLEAN, "value", "Value for what you want to change.", false));
    }

    @Override
    public DefaultMemberPermissions permission() {
        return DefaultMemberPermissions.DISABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping optionIndex = event.getOption("index");
        OptionMapping optionValue = event.getOption("value");
        String reply = "null";

        try {
            FlagManager flag = new FlagManager();

            if (optionIndex == null) {

                reply = FlagManager.flags.toString();
            } else if (optionValue == null) {
                reply = "Value of this index is: " + flag.getFlag(optionIndex.getAsInt());
            } else {
                flag.setFlag(optionIndex.getAsInt(), optionValue.getAsBoolean());
                reply = "Success";
            }
        } catch (IllegalArgumentException e) {
            reply = e.toString();
        }
        event.reply(reply).queue();
    }
}
