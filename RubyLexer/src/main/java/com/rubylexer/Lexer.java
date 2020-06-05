package com.rubylexer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Lexer {

    private State curState = State.START;

    private Buff bf;

    private String value = "";

    private static final int buffSize = 10;

    private ArrayList<String> symtable = new ArrayList<>();

    public Lexer(String filePath) {
        try {
            bf = new Buff(Files.newBufferedReader(Paths.get(filePath)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Token getNextToken() throws IOException {
        boolean eof = false;
        Token res = new Token();

        while (!eof) {
            Character c = bf.next();
            if (curState.equals(State.START)) {
                if (idHandler(c, res) != null) {
                    return res;
                }
                if (punctuationHandler(c, res) != null) {
                    return res;
                }

            }


            if (c == '\u001a')
                eof = true;
            value += String.valueOf(c);
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
            case ':':
                curState = State.DOUBLE_DOT;
                break;
            case ';':
                curState = State.DOT_COMA;
                break;
            case '.':
                curState = State.DOT;
                break;
            default:
                setStart();
                return null;
        }
        t.setContent("");
        t.setTokenType(TokenType.PUNCTUATION);

        setStart();
        return t;
    }

    private Token idHandler(Character c, Token t) throws IOException {
        Character k = c;
        if (Character.toString(c).matches("[$@_a-zA-Z]")) {
            curState = State.ID;
        }
        value = Character.toString(k);
        while (curState.equals(State.ID)) {
            System.out.println(k);
            k = bf.next();
            if (!Character.toString(k).matches("[_a-zA-Z0-9]")) {
                curState = State.ID_FOUND_ESCAPE;
                t.setTokenType(TokenType.ID);
                t.setContent(value);

                //bf.back(k);
                setStart();
                return t;
            } else {

                value += Character.toString(k);
            }

        }

        //bf.back(k);
        setStart();
        return null;
    }

    private boolean analyzeChar(Character c) {
        return true;
    }


    private void setStart() {
        curState = State.START;
    }
}