package com.rubylexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer("./input/test1.txt");

        Token t = lexer.getNextToken();
        while (t != null) {
            System.out.println(t);
            t = lexer.getNextToken();
        }
    }
}
