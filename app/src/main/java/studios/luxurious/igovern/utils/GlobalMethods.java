package studios.luxurious.igovern.utils;

public class GlobalMethods {

    public static String removeSpaces(String text){
        return text.replace(" ","").trim();
    }

    public static String profileLetter(String name){


        String firstLetter;
        if (name.length() > 0) {
            name = name.toUpperCase();
            firstLetter = String.valueOf(name.charAt(0));
        }
        else {
            firstLetter = "#";
        }

        if (firstLetter.equals("0") || firstLetter.equals("+"))
            firstLetter = "#";

        return firstLetter;
    }

    public static String removeAllSpecialCharacters(String unformattedString){
        return unformattedString.replaceAll("[^\\w\\s]","");
    }
    //Todo work on this method
    public static boolean isDigitsOnly(String text){
       return true;
    }

    public static String formatAmountAddZeros(int amount){
        if (amount == 0){
            return "00.00";
        }else {
            return amount+".00";
        }
    }
}
