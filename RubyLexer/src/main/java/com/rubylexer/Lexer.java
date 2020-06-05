package com.rubylexer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Lexer {

    private State curState = State.START;

    private Buff bf;

    private String value = "";

    private ArrayList<String> symtable = new ArrayList<>();

    public Lexer(String filePath) {
        try {
            bf = new Buff(Files.newBufferedReader(Paths.get(filePath)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Token getNextToken() throws IOException {
        Token t = getTokenImplementation();
        lastToken = t;
        return t;
    }

    private Token getTokenImplementation() throws IOException {
        boolean eof = false;
        Token res = new Token();

        while (!eof) {
            Character c = bf.next();
            if (curState.equals(State.START)) {
                if (idHandler(c, res) != null) {
                    value = "";
                    setStart();
                    return res;
                }
                if (symbolHandler(c, res) != null) {
                    value = "";
                    setStart();
                    return res;
                }
                if (stringLiteralHandler(c,res) != null){
                    value="";
                    setStart();
                    return res;
                }

                if (plusOrMinus(c, res) != null) {
                    value = "";
                    setStart();
                    return res;
                }

                if (operatorHandler(c, res) != null) {
                    value = "";
                    setStart();
                    return res;
                }

                if (numberHandler(c, res) != null) {
                    value = "";
                    setStart();
                    return res;
                }

                if (punctuationHandler(c, res) != null) {
                    value = "";
                    setStart();
                    return res;
                }

            }


            if (c == (char) 0)
                eof = true;
        }
        return null;
    }

    private Token punctuationHandler(Character c, Token t) {
        switch (c) {
            case ',':
                curState = State.COMA;
                break;
            case '(':
                curState = State.LBRACE;
                break;
            case ')':
                curState = State.RBRACE;
                break;
            case '{':
                curState = State.L_FIG_BRACE;
                break;
            case '}':
                curState = State.R_FIG_BRACE;
                break;
            case '[':
                curState = State.L_SQ_BRACE;
                break;
            case ']':
                curState = State.R_SQ_BRACE;
                break;
            case ':':
                curState = State.DOUBLE_DOT;
                break;
            case ';':
                curState = State.DOT_COMA;
                break;
            case '.':
                curState = State.DOT;
                break;
            case '\n':
                curState = State.NEW_LINE;
                t.setContent("\\n");
                t.setTokenType(TokenType.PUNCTUATION);
                setStart();
                return t;

            default:
                value = "";
                setStart();
                return null;
        }
        t.setContent(Character.toString(c));
        t.setTokenType(TokenType.PUNCTUATION);

        setStart();
        return t;
    }

    private Token symbolHandler(Character c, Token t) throws IOException {
        if (c == ':') {
            curState = State.SYMBOL_START;
            value = Character.toString(c);
        } else {
            setStart();
            return null;
        }

        Character k = bf.next();
        while (Character.toString(k).matches("[_0-9a-zA-Z]")) {
            value += Character.toString(k);
            curState = State.SYMBOL;
            k = bf.next();
        }

        t.setTokenType(TokenType.SYMBOL);
        t.setContent(value);
        value = "";
        setStart();
        bf.back(Character.toString(k));
        return t;
    }

    private Token idHandler(Character c, Token t) throws IOException {
        Character k = c;
        if (Character.toString(k).matches("[$@_a-zA-Z]")) {
            curState = State.ID_START;
        } else {
            setStart();
            value = "";
            return null;
        }
        value = Character.toString(k);

        k = bf.next();
        if (curState.equals(State.ID_START)) {
            if (Character.toString(k).matches("[@_a-zA-Z0-9]")) {
                value += Character.toString(k);
                curState = State.ID;
            } else {
                if (Patterns.isKeyword(value))
                    finalizeWithBufferBack(t, k, TokenType.KEYWORD);
                else if (Patterns.isLiteral(value))
                    finalizeWithBufferBack(t, k, TokenType.LITERAL);
                else
                    finalizeWithBufferBack(t, k, TokenType.ID);
                return t;
            }
        }

        while (curState.equals(State.ID) || curState.equals(State.ID_START)) {
            k = bf.next();
            if (!Character.toString(k).matches("[_a-zA-Z0-9]")) {
                curState = State.ID_FOUND_ESCAPE;

                if (Patterns.isKeyword(value))
                    finalizeWithBufferBack(t, k, TokenType.KEYWORD);
                else if (Patterns.isLiteral(value))
                    finalizeWithBufferBack(t, k, TokenType.LITERAL);
                else
                    finalizeWithBufferBack(t, k, TokenType.ID);
                return t;
            } else {
                value += Character.toString(k);
            }
        }

        setStart();
        value = "";
        bf.back(Character.toString(k));
        return null;
    }


    private Token plusOrMinus(Character c, Token t) throws IOException {
        if (c == '+' || c == '-') {
            if (lastToken != null) {
                if (lastToken.getTokenType() == TokenType.PUNCTUATION ||
                        lastToken.getTokenType() == TokenType.OPERATOR) {
                    value = Character.toString(c);

                    Character k = bf.next();

                    if (!Character.toString(k).matches("[0-9]")) {
                        finalizeWithTokenType(t, TokenType.OPERATOR, Character.toString(c));
                        bf.back(Character.toString(k));
                        return t;
                    }


                    Token tryNum = numberHandler(k, t);
                    if (tryNum == null) {
                        t.setTokenType(TokenType.OPERATOR);
                        t.setContent(Character.toString(c));
                        value = "";
                        setStart();
                        bf.back(Character.toString(k));
                        return t;
                    } else return tryNum;

                } else {
                    Character k = bf.next();
                    if (k == '=') {
                        t.setTokenType(TokenType.OPERATOR);
                        t.setContent(Character.toString(c) + k);
                        value = "";
                        setStart();
                        return t;
                    } else {
                        t.setTokenType(TokenType.OPERATOR);
                        t.setContent(Character.toString(c));
                        value = "";
                        setStart();
                        bf.back(Character.toString(k));
                        return t;
                    }
                }
            }
        }

        return null;
    }

    private Token getErrorToken(Token t, Character k) throws IOException {
        while (Character.toString(k).matches("[_A-Za-z0-9]")) {
            value += Character.toString(k);
            k = bf.next();
        }
        t.setContent(value);
        t.setTokenType(TokenType.ERROR);
        setStart();
        value = "";
        bf.back(Character.toString(k));
        return t;
    }

    private Token numberHandler(Character c, Token t) throws IOException {
        if (!Character.toString(c).matches("[0-9]")) {
            return null;
        } else {
            value += Character.toString(c);
        }

        Character k = bf.next();
        if (Character.toString(k).matches("[0-9]")) {
            curState = State.NUMBER_START;
            value += Character.toString(k);
        } else if (k == '.') {
            value += Character.toString(k);
            curState = State.NUMBER_DOUBLE;

        } else if (k == 'e' || k == 'E') {
            value += Character.toString(k);
            curState = State.NUMBER_EXP_START;
        } else {
            finalizeWithBufferBack(t, k, TokenType.NUMBER);
            return t;
        }

        while (curState.equals(State.NUMBER_START)) {
            k = bf.next();

            if (Character.toString(k).matches("[0-9]")) {
                value += Character.toString(k);
            } else if (k == '.') {
                value += Character.toString(k);
                curState = State.NUMBER_DOUBLE;
                break;
            } else if (k == 'e' || k == 'E') {
                value += Character.toString(k);
                curState = State.NUMBER_EXP_START;
                break;
            } else if (Character.toString(k).matches("[_A-Za-z]")) {
                // ERR
                return getErrorToken(t, k);
            } else {
                finalizeWithTokenType(t, TokenType.NUMBER, value);
                bf.back(Character.toString(k));
                return t;
            }
        }

        while (curState.equals(State.NUMBER_DOUBLE)) {
            k = bf.next();
            if (Character.toString(k).matches("[0-9]")) {
                value += Character.toString(k);
            } else if (k == 'e' || k == 'E') {
                value += Character.toString(k);
                curState = State.NUMBER_EXP_START;
                break;
            } else {
                curState = State.NUMBER_ESCAPE;
            }
        }

        if (curState.equals(State.NUMBER_EXP_START)) {
            k = bf.next();
            if (k == '-' || k == '+') {
                Character expectedDigit = bf.next();
                if (Character.toString(expectedDigit).matches("[0-9]")) {
                    value += Character.toString(k);
                    value += Character.toString(expectedDigit);
                    curState = State.NUMBER_EXP;
                } else {
                    finalizeWithTokenType(t, TokenType.NUMBER, value);
                    return t;
                }
            } else {
                if (Character.toString(k).matches("[0-9]")) {
                    value += Character.toString(k);
                    curState = State.NUMBER_EXP;
                } else {
                    finalizeWithTokenType(t, TokenType.NUMBER, value);
                    return t;
                }
            }
        }


        while (curState.equals(State.NUMBER_EXP)) {
            k = bf.next();
            if (Character.toString(k).matches("[0-9]")) {
                value += Character.toString(k);
            } else if (Character.toString(k).matches("[_A-Za-z]")) {
                // ERR
                return getErrorToken(t, k);
            } else {
                finalizeWithTokenType(t, TokenType.NUMBER, value);
                return t;
            }
        }
        finalizeWithBufferBack(t, k, TokenType.NUMBER);
        return t;
    }

    private void finalizeWithBufferBack(Token t, Character k, TokenType number) {
        t.setTokenType(number);
        t.setContent(value);
        bf.back(Character.toString(k));
        setStart();
        value = "";
    }

    private void finalizeWithTokenType(Token t, TokenType number, String value) {
        t.setTokenType(number);
        t.setContent(value);
        setStart();
    }

    private Token operatorHandler(Character c, Token t) throws IOException {
        value = "";
        if (Patterns.isPartOfOperations(Character.toString(c))) {
            curState = State.OPERATION;
            value += Character.toString(c);
        } else {
            return null;
        }
        Character k = bf.next();
        while (curState.equals(State.OPERATION)) {
            if (Patterns.isPartOfOperations(value + Character.toString(k))) {
                value += Character.toString(k);
            } else {
                break;
            }

            k = bf.next();
        }
        finalizeWithTokenType(t, TokenType.OPERATOR, value);
        bf.back(Character.toString(k));
        return t;
    }

    private Token stringLiteralHandler(Character c, Token t) throws IOException {
        value = "";
        if (c == '\'') {
            strLiteralSearch(c, t, '\'');
            return t;
        } else if (c == '\"') {
            strLiteralSearch(c, t, '\"');
            return t;
        }
        return null;
    }

    private void strLiteralSearch(Character c, Token t, char c2) throws IOException {
        curState = State.STR_LIT_SQ;
        value += Character.toString(c);
        Character k = bf.next();
        while (k != c2 && k != (char) 0) {
            String app = Character.toString(k);
            value += app;
            k = bf.next();
        }
        if (k != (char) 0)
            value += Character.toString(k);
        finalizeWithTokenType(t, TokenType.LITERAL, value);
    }

    private void setStart() {
        curState = State.START;
    }

    private Token lastToken = null;
}