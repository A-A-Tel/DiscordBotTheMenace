package com.anthony.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public void loadCommandsSingle(Guild guild) {
        for (ICommand command : commands) {
            guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).setDefaultPermissions(command.permission()).queue();
        }
    }

    public void loadCommandsAll(ReadyEvent event) {
        for (Guild guild : event.getJDA().getGuilds()) {
            for (ICommand command : commands) {
                guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).setDefaultPermissions(command.permission()).queue();
            }
        }
    }

    public void commandInteraction(SlashCommandInteractionEvent event) {
        for (ICommand command : commands) {
            if (command.getName().equals(event.getName())) {
                command.execute(event);
                return;
            }
        }
    }

    public void add(ICommand command) {
        commands.add(command);
    }
}
