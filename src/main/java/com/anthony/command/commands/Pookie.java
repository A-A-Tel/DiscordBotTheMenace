package com.anthony.command.commands;

import com.anthony.command.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Pookie implements ICommand {
    @Override
    public String getName() {
        return "pookie";
    }

    @Override
    public String getDescription() {
        return "Tag the user as a pookie";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(new OptionData(OptionType.USER, "user", "target user", false));
    }

    @Override
    public DefaultMemberPermissions permission() {
        return DefaultMemberPermissions.ENABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping optionUser = event.getOption("user");
        String reply = "    Hey pookie :3   :heart:";

        if (optionUser == null) {
            reply = event.getUser().getAsMention() + reply;
        } else {
            reply = optionUser.getAsUser().getAsMention() + reply;
        }
        event.reply(reply).queue();
    }
}
