package com.rubylexer;

public class Token {

    private String content;
    private TokenType tokenType;

    public void setContent(String content) {
        this.content = content;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }



    public Token(){

    }

    public Token(TokenType tokenType, String content) {
        this.content = content;

        if (tokenType.equals(TokenType.ID)) {
            if (Patterns.isLiteral(content)) {
                this.tokenType = TokenType.LITERAL;
            } else if (Patterns.isKeyword(content)) {
                this.tokenType = TokenType.KEYWORD;
            } else
                this.tokenType = tokenType;
        } else
            this.tokenType = tokenType;

        if (tokenType.equals(TokenType.PUNCTUATION)) {
            if ("\n".equals(content)) {
                this.content = "\\" + "n";
            } else if ("\t".equals(content)) {
                this.content = "\\" + "t";
            }
        }

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

    @Override
    public String toString() {
        return "< " + fillTo(tokenType.toString(), 12) + "  :  " + content + " >";
    }
}

