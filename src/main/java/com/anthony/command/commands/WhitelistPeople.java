package com.anthony.command.commands;

import com.anthony.command.ICommand;
import com.anthony.filter.FilterManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class WhitelistPeople implements ICommand {
    @Override
    public String getName() {
        return "whitelist-members";
    }

    @Override
    public String getDescription() {
        return "Give users a bypass to the chat filter/view users";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.USER, "user", "Specify which user", false),
                new OptionData(OptionType.BOOLEAN, "remove", "set to true to remove, default is add", false)
        );
    }

    @Override
    public DefaultMemberPermissions permission() {
        return DefaultMemberPermissions.DISABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        FilterManager filter = new FilterManager();

        OptionMapping optionUser = event.getOption("user");
        OptionMapping optionRemove = event.getOption("remove");

        String reply;

        if (optionUser == null) {
            StringBuilder builder = new StringBuilder("[");

            for (long id : FilterManager.whitelistedUsers) {
                builder.append("<@").append(id).append(">, ");
            }

            builder.delete(builder.length() - 2, builder.length());
            builder.append("]");
            reply = builder.toString();

        } else if (optionRemove == null || optionRemove.getAsBoolean()) {

            filter.addWhitelistedUser(optionUser.getAsUser().getIdLong());
            reply = "Added user: " + optionUser.getAsMentionable();

        } else if (filter.removeWhitelistedUser(optionUser.getAsUser().getIdLong())) {

            reply = "Removed user: " + optionUser.getAsMentionable();
        } else {

            reply = "List did not contain: " + optionUser.getAsMentionable();
        }
        event.reply(reply).queue();
    }
}
