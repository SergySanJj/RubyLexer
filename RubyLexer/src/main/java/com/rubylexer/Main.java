package com.rubylexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer("./input/test1.txt");

        int c = 0;
        while ( c < 200) {
            c++;
            System.out.println(lexer.getNextToken());
        }
    }
}
