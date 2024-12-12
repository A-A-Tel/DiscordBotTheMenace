package com.anthony.command.commands;

import com.anthony.command.ICommand;
import com.anthony.flag.FlagManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class ResetFlags implements ICommand {
    @Override
    public String getName() {
        return "flags-reset";
    }

    @Override
    public String getDescription() {
        return "Reset all flags to the default";
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
        FlagManager flag = new FlagManager();
        flag.resetFlags();
        event.reply("Success").queue();
    }
}
