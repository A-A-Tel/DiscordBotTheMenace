package com.relaxingleg.command.commands;

import com.relaxingleg.Helper;
import com.relaxingleg.command.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.io.FileWriter;
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

        Helper helper = new Helper();

        event.reply("Goodbyeeeeee!").queue();
        helper.delay(2000);
        event.getChannel().sendMessage("e").queue();
        helper.delay(5000);
        System.exit(0);
    }
}
