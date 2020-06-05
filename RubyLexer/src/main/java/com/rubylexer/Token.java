package com.rubylexer;

import java.util.HashMap;
import java.util.Map;

public class Token {
    private Map<TokenType, String> map = new HashMap<>();

    {
        map.put(TokenType.NUMBER, "color: #83c385;");
        map.put(TokenType.OPERATOR, "color: #e0e0e0; font-weight: bold;");
        map.put(TokenType.LITERAL, "color: #e47c4b;");
        map.put(TokenType.PUNCTUATION, "color: #ffffff;");
        map.put(TokenType.ID, "color: #e4e4e4;");
        map.put(TokenType.KEYWORD, "color: #0095ff; font-weight: bold;");
        map.put(TokenType.COMMENT, "color: #4aa724;");
        map.put(TokenType.ERROR, "color: white; text-decoration: underline; text-decoration-color:red");
        map.put(TokenType.SYMBOL, "color: #e47c4b; font-weight: bold;");
    }

    private String content;
    private TokenType tokenType;

    public void setContent(String content) {
        this.content = content;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }


    public Token() {
    }

    public Token(TokenType tokenType, String content) {
        this.content = content;
        this.tokenType = tokenType;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getContent() {
        return content;
    }

    private static String fillTo(String s, int n) {
        StringBuilder res = new StringBuilder(s);
        while (res.length() < n) {
            res.append(" ");
        }
        return res.toString();
    }

    public String toHtml() {
        if (getContent().equals("\\n"))
            return "<br>";
        return "<span style='" + getStyle(getTokenType()) + "'>" + getContent() + "</span>";
    }

    private String getStyle(TokenType tokenType) {

        return map.get(tokenType);
    }


    @Override
    public String toString() {
        return "< " + fillTo(tokenType.toString(), 12) + "  :  " + content + " >";
    }
}

