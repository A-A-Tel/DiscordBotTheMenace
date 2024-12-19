package com.anthony.command.commands;

import com.anthony.command.ICommand;
import com.anthony.poll.PollManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class SetPollChannel implements ICommand {
    @Override
    public String getName() {
        return "set-poll-channel";
    }

    @Override
    public String getDescription() {
        return "Set the automated poll channel";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.CHANNEL, "channel", "specify which channel", true)
        );
    }

    @Override
    public DefaultMemberPermissions getPermission() {
        return DefaultMemberPermissions.DISABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        MessageChannel channel = event.getOption("channel").getAsChannel().asGuildMessageChannel();

        PollManager poll = new PollManager();
        poll.setChannel(channel, event.getGuild());
        event.reply("Success!");
    }
}
