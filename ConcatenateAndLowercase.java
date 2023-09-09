public class ConcatenateAndLowercase {
    public static void main(String[] args) {
        //Declaramos el array de Strings.
    	String[] arrayStrings = {"FirstWord", "SecondWord", "THIRDWORD"};
        //Creamos un objeto StringBuilder nuevo para poder devolver un String con el contenido del array ya unificado.
        StringBuilder resultado = new StringBuilder();
        //Iteramos cada objeto String del array dado.
        for (String word : arrayStrings) {
            resultado.append(word).append(" ");
        }
        //Pasamos cada objeto del StringBuilder a String y a minúscula.
        String finalString = resultado.toString().toLowerCase();
        //Devolvemos el resultado final.
        System.out.println(finalString);
    }
}