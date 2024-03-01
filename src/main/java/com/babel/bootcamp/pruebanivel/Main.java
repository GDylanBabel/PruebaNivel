package com.babel.bootcamp.pruebanivel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // El umbral se debe especificar como argumento a la hora de ejecutar el programa
        int umbral = Integer.parseInt(args[0]);
        PasswordChecker passwordChecker = new PasswordChecker(umbral);
        passwordChecker.comprobarFortleza();
    }
}