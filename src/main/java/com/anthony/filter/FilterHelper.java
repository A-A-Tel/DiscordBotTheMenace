package com.anthony.filter;

import com.vdurmont.emoji.EmojiParser;
import com.vdurmont.emoji.EmojiManager;

import java.util.Map;

public class FilterHelper {

    public boolean isBadMessage(String message) {

        message = removeEmojis(message);
        message = removeLeetSpeak(message.toLowerCase());

        for (String word : FilterManager.bannedWords) {
            if (message.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public String removeEmojis(String text) {
        return EmojiParser.removeEmojis(EmojiParser.parseToUnicode(text), EmojiManager.getAll());
    }

    public String removeLeetSpeak(String text) {
        StringBuilder builder = new StringBuilder();

        Map<Character, Character> leetMap = Map.of(
                '0', 'o',
                '1', 'i',
                '2', 'z',
                '3', 'e',
                '4', 'a',
                '5', 's',
                '6', 'g',
                '7', 'l',
                '8', 'b',
                '9', 'g'
        );
        for (Character ch : text.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                builder.append(ch);
            } else if (Character.isDigit(ch)) {
                builder.append(leetMap.get(ch));
            }
        }
        return builder.toString();
    }
}
