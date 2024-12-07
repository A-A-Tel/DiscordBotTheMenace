package com.relaxingleg.command.commands;

import com.relaxingleg.command.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class SetFlag implements ICommand {
    @Override
    public String getName() {
        return "set-flag";
    }

    @Override
    public String getDescription() {
        return "Set a flag of the server.";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of();
    }

    @Override
    public DefaultMemberPermissions permission() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

    }
}
