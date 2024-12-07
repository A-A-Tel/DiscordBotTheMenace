package com.relaxingleg.command.commands;

import com.relaxingleg.command.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class KillBot implements ICommand {
    @Override
    public String getName() {
        return "kill";
    }

    @Override
    public String getDescription() {
        return "Kills the bot";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of();
    }

    @Override
    public DefaultMemberPermissions permission() {
        return DefaultMemberPermissions.DISABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Goodbye!").queue();
        System.exit(0);
    }
}
