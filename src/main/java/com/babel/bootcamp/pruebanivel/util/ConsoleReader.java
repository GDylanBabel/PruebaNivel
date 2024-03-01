package com.babel.bootcamp.pruebanivel.util;

import java.util.Scanner;

public class ConsoleReader {

    private static ConsoleReader consoleReader;

    public static String readString(){
        Scanner terminalInput = new Scanner(System.in);
        return terminalInput.nextLine();
    }

}
