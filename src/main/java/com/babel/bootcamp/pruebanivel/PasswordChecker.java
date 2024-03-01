package com.babel.bootcamp.pruebanivel;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

    private final int umbral;
    private String password;
    private int level;
    private boolean isMaxCombination = true;
    private final String[] strengthLevels = {"Muy débil", "Débil", "Moderada", "Fuerte", "Muy fuerte"};

    /**
     * Comprueba que la contraseña introducida tiene un nivel mínimo de seguridad
     * @param umbral - el umbral que se debe dar como mínimo (no incluido)
     */
    public PasswordChecker(int umbral) {
        this.umbral = umbral;
        this.level = 0;
        readPassword();
    }

    /**
     * Le la contraseña introducida a través de la consola
     */
    private void readPassword(){
        // Tomamos el input de la  contraseña
        System.out.println("Por favor, introduzca su contraseña: ");
        Scanner terminalInput = new Scanner(System.in);
        this.password = terminalInput.nextLine();
    }

    /**
     * Comprueba la fortaleza de la contraseña e informa al usuario de su nivel
     */
    public void comprobarFortleza() {
        // Calculamos la puntuación de la contraseña

        // Por longitud
        checkLength();

        // Por contenido
        checkContains();

        // Combinación máxima
        if (this.isMaxCombination) {
            this.level += 1;
        }

        // Comprobamos el nivel de la contraseña
        checkLevel();

        // Contraseña débil
        System.out.println(this.level  + "\n");
        if (this.level <= umbral) {
            System.out.println("Esta contraseña no es lo suficientemete fuerte.");
            System.out.println("¿Está seguro de que desea utilizarla? y(Y)/n(N): ");
            Scanner terminalInput = new Scanner(System.in);
            String answer = terminalInput.nextLine();
            // Por si el usuaro introduce algo incorrecto
            while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                System.out.println("Por favor, introduzca una respuesta válida y(Y)/n(N): ");
                answer = terminalInput.nextLine();
            }

            // Comprobamos si el usuario acepta la contraseña y si no, no la aceptamos
            if (!answer.equalsIgnoreCase("y")) {
                System.out.println("La contraseña no se ha aceptado.");
                return;
            }
        }
        System.out.println("Se ha aceptado la contraseña correctamente.");
    }

    /**
     * Comprueba el nivel de la contraseña e informa al usuario de su estado
     */
    private void checkLevel(){
        // Mostramos el nivel de la contraseña
        String nivel = "";
        if (this.level <= 2) {
            nivel = this.strengthLevels[0];
        } else if (this.level <= 5) {
            nivel = this.strengthLevels[1];
        } else if (this.level <= 7) {
            nivel = this.strengthLevels[2];
        } else if (this.level <= 9) {
            nivel = this.strengthLevels[3];
        } else {
            nivel = this.strengthLevels[4];
        }
        System.out.printf("Nivel de la contraseña: %s%n", nivel);
    }

    /**
     * Comprueba si la contraseña contiene los caracteres especificados para sumarle puntos de nivel
     */
    private void checkContains() {
        // Patrones con expresiones regulares que comprueban si la contraseña contiene o no los caracteres indicados
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern specialCharacter = Pattern.compile("[/'.'·,;:!@#$%&*()_+=|<>?{}\\[\\]~-]");

        // Matchers para comprobar que la contraseña cumple las regexp dadas
        Matcher hasUpperCaseLetter = upperCase.matcher(this.password);
        Matcher hasLowerCaseLetter = lowerCase.matcher(this.password);
        Matcher hasDigit = digit.matcher(this.password);
        Matcher hasSpecial = specialCharacter.matcher(this.password);

        // Comprobamos las letras por separado y a continuación las juntamos
        boolean smallCasePresent = hasLowerCaseLetter.find();
        boolean upperCasePresent = hasUpperCaseLetter.find();
        boolean isLetterPresent = smallCasePresent || upperCasePresent;
        // Comprobamos ambas, ya que buscamos que tenga tanto mayúsculas como minúsculas
        boolean areMayusAndMinusPresent = upperCasePresent && smallCasePresent;
        boolean isDigitPresent = hasDigit.find();
        boolean isSpecialPresent = hasSpecial.find();

        // Comprobamos el uso de letras
        // Si tiene letras
        if (isLetterPresent) {
            this.level += 1;
        }
        // Si tiene mayúsculas y minúsculas
        if (areMayusAndMinusPresent) {
            this.level += 2;
        }
        // Si tiene números
        if (isDigitPresent) {
            this.level += 1;
        }
        // Si tiene símbolos
        if (isSpecialPresent) {
            this.level += 2;
        }

        // No se cumple la condición para la combinación máxima
        if (!isDigitPresent || !isLetterPresent || !isSpecialPresent || !areMayusAndMinusPresent) {
            this.isMaxCombination = false;
        }
    }

    /**
     * Comprueba la longitud de la contraseña y le asigna puntos en funcion de esta
     */
    private void checkLength() {
        // Longitud
        int length = this.password.length();
        if (length > 12) {
            this.level += 3;
        } else if (length >= 9) {
            this.isMaxCombination = false; // Ya no se cumple una de las condiciones para la combinación máxima
            this.level += 2;
        } else if (length >= 7) {
            this.isMaxCombination = false;
            this.level += 1;
        } else {
            // Si tiene 0, no le asignamos puntos
            this.isMaxCombination = false;
        }
    }

}
