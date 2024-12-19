package com.anthony.poll;

import com.anthony.Helper;
import com.anthony.Main;
import com.google.gson.reflect.TypeToken;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import com.vdurmont.emoji.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.utils.messages.MessagePollBuilder;
import net.dv8tion.jda.api.utils.messages.MessagePollData;

import java.io.File;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PollManager {

    public static ArrayList<PollData> pollDatas;
    private final String fileName = "pollChannels.json";

    public void loadPoll() {
        File file = new File(fileName);

        if (file.exists()) {
            Helper helper = new Helper();

            Type type = new TypeToken<ArrayList<PollData>>() {
            }.getType();

            helper.readListFromJson(fileName, type);
            sendPoll();
        } else {
            pollDatas = new ArrayList<>();
        }
    }

    public void sendPoll() {

        Helper helper = new Helper();

        for (PollData data : pollDatas) {
            if (data == null) {
                return;
            }
        }

        long delay = helper.calculateTimeUntil(LocalTime.of(22, 32));
        helper.delay(delay);

        MessagePollData data = createPoll();

        for (PollData pollData : pollDatas) {

            MessageChannel channel = Main.jda.getGuildById(pollData.getGuildId())
                            .getChannelById(TextChannel.class, pollData.getChannelId());

            channel.sendMessagePoll(data).queue();
            channel.sendMessage("@everyone");
        }
    }

    public void setChannel(MessageChannel channel, Guild guild) {

        Helper helper = new Helper();

        pollDatas.add(new PollData(guild.getIdLong(), channel.getIdLong()));

        helper.saveListToJson(fileName, pollDatas);
        sendPoll();
    }

    public MessagePollData createPoll() {
        return new MessagePollBuilder("Call?")
                .addAnswer("JAAAAA")
                .addAnswer("Naur..")
                .setMultiAnswer(false)
                .setDuration(Duration.of(4, ChronoUnit.HOURS))
                .build();
    }
}
