package com.anthony.poll;

public class PollData {
    private long guildId;
    private long channelId;

    public PollData(long guildId, long channelId) {
        this.guildId = guildId;
        this.channelId = channelId;
    }

    public long getChannelId() {
        return channelId;
    }

    public long getGuildId() {
        return guildId;
    }
}
