import java.util.Scanner;
import java.util.Arrays;

public class Main{ //у нас есть класс
    public static void main(String[] args) throws Throwable
    {
        //1 мы получаем строку bp
        Scanner in = new Scanner(System.in);
        //System.out.print("Hello");
        String str = in.nextLine(); //ввод строки из консоли
        in.close();
        System.out.print(calc(str)); // вызовфункции кальк
    }
    public static String calc(String input) throws Exception{

        String str = input;
        //2 очищаем строку от пробелов
        str = str.replaceAll("\\s+","");
        //3 то uppercase
        str = str.toUpperCase();
        //минимальное число символов в очищеной строке 3
        if(str.length()<3){
            throw new Exception("Введена короткая строка");
        }

        //3 все что до знака в левую часть           остальное в правую.
        String oper = "+-*/";
        //перевел строку oper в массив символов
        char[] operArr = oper.toCharArray();
        //перевел строку str в массив символов
        char[] strArr = str.toCharArray();

        int q = 0;
        int sig = 0;
        int nonSig = 0;
        int res = 0;
        String left ="";
        String right ="";

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //сравнить элементы строки str c элементами массива sigArr. Пока нет совпада все влевл иначе вправо
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        for(int i = 0; i < str.length(); i++) {
            for (int j = 0; j < oper.length(); j++) {
                //System.out.print(operArr[i]);
                if(strArr[i] == operArr[j]){
                    //есть оператор
                    sig = i; // ЗАФИКСИРОВАЛ математ ОПЕРАТОР
                    q++;   //  МАРКЕР ЕСЛИ 0 ТО ВЛЕВО БОЛЬШЕ ТО ВПРАВО
                    if(q < 2){
                        i++;
                        j = oper.length(); //завершаем внутренний цикл  -->
                    }else{
                        j = oper.length();
                    }
                }else{
                    nonSig++;
                }
            }
            // ПОЛУЧИЛ ЛЕВУЮ ПРАВУЮ ЧАСТИ И ОПЕРАТОР
            if( q == 0 ){  //
                left += strArr[i];
            }else if(q == 1 ){
                right += strArr[i];
            }else{
                i = str.length();
                throw new Exception("в строке найдено более 1 математического оператора");
            }

        }
        if( nonSig >= (str.length() * oper.length()) ){ //ни + ни - ни * ни /
            throw new Exception("в строке нет ниодного мат оператора");
        }else {
            //=========у нас есть левая и правая части================================
            // Проверяем если есть римские цифры в левой и правой частях переводим в арабские и решаем


            int l = 0; //для левой части
            int r = 0; //для правой части
            int leftArabianPartBool = 0; //принимает 1 если есть совпадение с Арабской частью
            int rightArabianPartBool = 0;
            int leftRomanPartBool = 0;
            int rightRomanPartBool = 0;

            String[] arabArr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};//, "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

            //Принимаю римскую цифру и ПЕРЕВОЖУ В арабскую ЧИСЛО
            for (int i = 0; i < arabArr.length; i++) {
                if (arabArr[i].equals(left)) {
                    l = i;
                    leftArabianPartBool = 1; //если есть совпад с левой частью
                }
                if (arabArr[i].equals(right)) {
                    r = i;
                    rightArabianPartBool = 1; //если есть совпад с правой частью
                }
            }
            if( l > 10 ){
                throw new Exception("данное число не может превышать 10");
            }
            if( r > 10 ){
                throw new Exception("данное число не может превышать 10");
            }

            //ЕСЛИ ОБА ЧИСЛО===========================================================================
            if(leftArabianPartBool > 0 && rightArabianPartBool == 0) {
                throw new Exception("правая часть не число");
            } else if ((leftArabianPartBool == 0 && rightArabianPartBool > 0)) {
                throw new Exception("левая часть не число");
            } else if (leftArabianPartBool == 1 && rightArabianPartBool == 1) {
                //производим мат действия с числами
                switch (strArr[sig]) {
                    case '+':
                        res = l + r;
                        break;
                    case '-':
                        res = l - r;
                        break;
                    case '*':
                        res = l * r;
                        break;
                    case '/':
                        res = l / r;
                        break;
                    default:
                        throw new Exception("проблема в свитч араб ");
                }

                //======================================================================
                //ВЫВОД АРАБСКОГО РЕЗУЛЬТАТА++++++++++++++++++++++++++++++++++++++++++++
                String arabStr =""+res; //arabArr[res];
                return(arabStr);
                //==========================+++++++++++++++++++++++++++++++++++++
                //==================================================================
            }else{
                //ОБА не ЧИСЛО
                // РиМСКИЙ МОДУЛЬ
                //
                String[] romanArr = {" ", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI"};//, "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
                char[] leftArr = left.toCharArray(); //перевел левую часть в массив
                char[] rightArr = right.toCharArray(); //перевел правую часть в массив
                // переводим римские цифры в арабские
                for (int i = 0; i < romanArr.length; i++) {
                    if (romanArr[i].equals(left)) {
                        l = i;
                        leftRomanPartBool = 1; //если есть совпад с левой частью

                    }
                    if (romanArr[i].equals(right)) {
                        r = i;
                        rightRomanPartBool = 1; //если есть совпад с левой частью
                    }
                }

                //проверка на арабские.
                if(leftRomanPartBool > 0 &&  rightRomanPartBool > 0){ //если обе части имют совпад
                    if( l > 10 ){
                        throw new Exception("вводимое римское число не может превышать X");
                    }
                    if( r > 10 ){
                        throw new Exception("вводимое римское число не может превышать X");
                    }
                    //производим мат действия с числами
                    switch (strArr[sig]) {
                        case '+':
                            res = l + r;
                            break;
                        case '-':
                            res = l - r;
                            break;
                        case '*':
                            res = l * r;
                            break;
                        case '/':
                            res = l / r;
                            break;
                        default:
                            throw new Exception("проблема в свитч РИМСКИЙ МОДУЛЬ");
                    }
                    //получили арабский результат
                    //переводим римское число в арабское МАКСИМ число 100
                    String[] rimArr = {"X","XL","L","XC","C"};
                    String romanStr = "";
                    // у нас 3 разряда. если разряд 1 (единицы) тогда res входит в romanArr. если 2 разр (до 99) 3 разр = 100
                    if(res<0){
                        throw new Exception("римское число не может быть отрицательным");
                    }else if(res <= 10){
                        return(romanArr[res]);
                    }else{

                        //==================================================================================
                        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        //7*3 = 21
                        int razr = res / 10;//2
                        int ed = res % 10;//1

                        if(res > 10 && res < 40){
                            res = 10;
                            for (int i = 0; i < razr; i++) {
                                romanStr += romanArr[res];
                            }
                            romanStr += romanArr[ed];
                            return (romanStr);
                        }else if(res == 40){
                            romanStr = rimArr[1];
                            return(romanStr);
                        }else if(res > 40 && res < 50){
                            romanStr += rimArr[1];
                            romanStr += romanArr[ed];
                            return(romanStr);
                        }else if(res == 50){
                            romanStr = rimArr[2];
                            return(romanStr);
                        }else if(res > 50 && res < 90) {
                            romanStr += rimArr[2];
                            res = 10;
                            razr = razr - 5;
                            for (int i = 0; i < razr; i++) {
                                romanStr += romanArr[res];
                            }
                            romanStr += romanArr[ed];
                            return (romanStr);
                        }else if(res == 90){
                            romanStr = rimArr[3];
                            return(romanStr);
                        }else if(res > 90 && res < 100){
                            romanStr += rimArr[3];
                            romanStr += romanArr[ed];
                            return(romanStr);
                        }else if(res == 100){
                            romanStr = rimArr[4];
                            return(romanStr);
                        }else{
                            throw new Exception("wtf");
                        }


                    }

                }else{
                    throw new Exception("введены недопустимые числа");
                }
            }

        }
    }
}

