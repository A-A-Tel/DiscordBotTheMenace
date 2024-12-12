package com.anthony;

import com.anthony.command.CommandManager;
import com.anthony.command.commands.*;
import com.anthony.filter.FilterManager;
import com.anthony.flag.FlagManager;
import com.anthony.level.LevelManager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import org.jetbrains.annotations.NotNull;

public class Main extends ListenerAdapter {

    public static JDA jda = JDABuilder.createDefault(Token.BOT)
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(new Main()).build();

    public static void main(String[] ignoredArgs) {
        System.out.println("Loading bot...");
    }

    /// Beginning event listener ///

    private final LevelManager level = new LevelManager();
    private final CommandManager command = new CommandManager();
    private final FlagManager flag = new FlagManager();
    private final FilterManager filter = new FilterManager();

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        for (Guild guild : jda.getGuilds()) {
            guild.loadMembers();
        }

        command.add(new GetLevel());
        command.add(new KillBot());
        command.add(new DirectFlags());
        command.add(new ResetFlags());
        command.add(new BannedWords());
        command.add(new Pookie());
        command.add(new WhitelistPeople());
        command.loadCommandsAll(event);

        level.loadUsers();
        flag.loadFlags();
        filter.loadFilter();

        System.out.println("Finished Loading");
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {

        event.getGuild().loadMembers();

        command.loadCommandsSingle(event.getGuild());
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        command.commandInteraction(event);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (!event.isFromGuild()) return;

        if (FlagManager.flags.getFirst() && filter.filterMessage(event.getMessage())) {
            return;
        }
        if (FlagManager.flags.get(1)) {
            level.setLevelStatus(event.getJDA().getUserById(event.getAuthor().getIdLong()), event.getChannel());
        }
    }

    @Override
    public void onMessageUpdate(@NotNull MessageUpdateEvent event) {

        if (!event.isFromGuild()) return;

        if (FlagManager.flags.getFirst()) {
            filter.filterMessage(event.getMessage());
        }

    }
}