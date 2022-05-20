import java.util.Scanner;

public class calc {

    private static int strToArab(String subString) {

        int num = -1;
        num = Integer.parseInt(subString);
        if(num > 10) throw new NumberFormatException();

        return num;
    }


    public static int romanToArab(String romanNumber) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber;
        for (int x = romanNumeral.length() - 1; x >= 0; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;

                default:
                    throw new NumberFormatException();
            }
        }
        if(decimal > 10) throw new NumberFormatException();
        return decimal;
    }

    public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            if(decimal == 1)
                return lastDecimal - decimal;
            else throw new NumberFormatException();
        } else {
            return lastDecimal + decimal;
        }
    }

    public static int calculation(int[] arrOfDecNum, char exp){
        int res = 0;

        switch (exp){
            case '*':
                res = arrOfDecNum[0] * arrOfDecNum[1];
                break;

            case '/':
                res = arrOfDecNum[0] / arrOfDecNum[1];
                break;

            case '+':
                res = arrOfDecNum[0] + arrOfDecNum[1];
                break;

            case '-':
                res = arrOfDecNum[0] - arrOfDecNum[1];
                break;
        }

        return res;
    }

    public static String getRoman(int number) {

        String roman[] = {"M","XM","CM","D","XD","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int arab[] = {1000, 990, 900, 500, 490, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (number > 0 || arab.length == (i - 1)) {
            while ((number - arab[i]) >= 0) {
                number -= arab[i];
                result.append(roman[i]);
            }
            i++;
        }
        return result.toString();
    }


        public static void main (String[]args){
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine().replaceAll(" ", "");
            int[] arrOfNumbers = new int[2];
            int indOfExp = -1;
            char realExp = ' ';
            int res;
            String resRoman;

            char[] expressions = {'*', '/', '-', '+'};


            for (char expression : expressions) {
                indOfExp = s.indexOf(expression);
                if (indOfExp != -1) {
                    realExp = expression;
                    break;
                }
            }
            if (indOfExp == -1) throw new NumberFormatException();
            else if (Character.isDigit(s.charAt(0))) {
                arrOfNumbers[0] = strToArab(s.substring(0, indOfExp));
                arrOfNumbers[1] = strToArab(s.substring(indOfExp + 1, s.length()));
                res = calculation(arrOfNumbers, realExp);
                System.out.println(res);
            }
            else if (s.charAt(0) == 'X' || s.charAt(0) == 'V' || s.charAt(0) == 'I'){
                arrOfNumbers[0] = romanToArab(s.substring(0, indOfExp));
                arrOfNumbers[1] = romanToArab(s.substring(indOfExp + 1, s.length()));
                res = calculation(arrOfNumbers, realExp);
                if (res < 1) throw new NumberFormatException();
                resRoman = getRoman(res);
                System.out.println(resRoman);
            }
            else throw new NumberFormatException();

        }
    }