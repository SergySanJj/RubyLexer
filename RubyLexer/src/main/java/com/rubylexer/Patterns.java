package com.rubylexer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Patterns {

    private static Set<String> keywords = new HashSet<>(Arrays.asList(
            "BEGIN", "END", "__ENCODING__", "__END__", "__FILE__", "__LINE__",

            "alias", "and",
            "begin", "break",
            "case", "class",
            "def", "defined?", "do",
            "else", "elsif", "end", "ensure",
            "false", "for",
            "if", "in",
            "module", "next", "nil", "not",
            "or",
            "redo", "rescue", "retry", "return",
            "self", "super",
            "then", "true",
            "undef", "unless", "until",
            "when", "while",
            "yield"
    ));

    private static Set<String> operations = new HashSet<>(Arrays.asList(
            "==", "!=", ">", "<", ">=", "<=", "<=>", "===", ".eql?", "equal?",
            "+=", "-=", "*=", "/=", "%=", "**=",
            "<<", ">>", "&&", "||", "!",
            "&", "|", "^", "~",
            "+", "-", "*", "/", "%", "**",
            "=",
            "..", "...",
            "defined?"
    ));


    private static Set<String> literals = new HashSet<>(Arrays.asList(
            "true", "false", "null", "TRUE", "FALSE", "NIL"
    ));

    private static Set<Character> punctuation = new HashSet<>(Arrays.asList(
            ',', '(', ')', '{', '}', '[', ']', ':', ';', '.', '\n'
    ));


    public static boolean isKeyword(String word) {
        return keywords.contains(word);
    }

    public static boolean isLiteral(String word) {
        return literals.contains(word);
    }

    public static boolean isPartOfOperations(String s){
        for (String op : operations){
            if (op.startsWith(s))
                return true;
        }
        return false;
    }

}