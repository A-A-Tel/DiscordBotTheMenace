package com.anthony.level;

public class LeveledUser {
    private long id;
    private int level;
    private int remaining;

    public LeveledUser(long id)  {
        this.id = id;
        this.level = 0;
        this.remaining = 100;
    }

    public long getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
