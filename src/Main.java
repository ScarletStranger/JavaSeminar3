import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String personInfo = getNsp(scanner) + " " + getBirthDate(scanner) + " " + getPhoneNumber(scanner) + " " + getGender(scanner);
            File phone = new File(personInfo.split(",")[0] + ".txt");
            FileWriter fw = new FileWriter(phone, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(personInfo);
            bw.flush();
            if (phone.createNewFile()) {
                System.out.println("File created: " + phone.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static String getNsp(Scanner scanner)throws NumberFormatException {
        String name;
        String surName;
        String patronymic;

        System.out.println("Введите фамилию");
        name = scanner.nextLine();
        System.out.println("Введите имя");
        surName = scanner.nextLine();
        System.out.println("Введите отчество");
        patronymic = scanner.nextLine();
        if (!isNumeric(name) && !isNumeric(surName) && !isNumeric(patronymic))
            return name + "," + surName + "," + patronymic;
        else throw new RuntimeException("Введены не буквенные данные");
    }

    private static String getBirthDate(Scanner scanner) throws NumberFormatException{
        int day;
        int month;
        int year;
        System.out.println("Введите день рождения");
        day = scanner.nextInt();
        System.out.println("Введите месяц рождения");
        month = scanner.nextInt();
        System.out.println("Введите год рождения");
        year = scanner.nextInt();
        if (checkDay(day) && checkMonth(month) && checkYear(year))
            return day + "." + month + "." + year;
        else throw new RuntimeException("Введены неверные данные");
    }


    private static int getPhoneNumber(Scanner scanner) throws NumberFormatException{
        int number;
        System.out.println("Введите 11-значный номер телефона начиная с восьмёрки");
        number = scanner.nextInt();
        if (checkNumber(number))
            return number;
        else throw new RuntimeException("Введены неверные данные");
    }

    private static String getGender(Scanner scanner) throws NumberFormatException{
        String gender;
        System.out.println("Введите пол");
        gender = scanner.nextLine();
        if (!isNumeric(gender) && gender.equals("f") || gender.equals("m"))
            return gender;
        return "Ошибка пола";
    }


    private static boolean isNumeric(String fio) throws NumberFormatException {
        try {
            int tmp = Integer.parseInt(fio);
            System.out.println(tmp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkDay(int day) {
        try {
            int minDay = 0;
            int maxDay = 32;
            if (day > minDay && day < maxDay)
                return true;
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    private static boolean checkMonth(int month) {
        try {
            int minMonth = 0;
            int maxMonth = 13;
            if (month > minMonth && month < maxMonth)
                return true;
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    private static boolean checkYear(int year) {
        try {
            int maxYear = 2023;
            if (year < maxYear)
                return true;
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    private static boolean checkNumber(int number) {
        try {
            String tmp = String.valueOf(Integer.parseInt(String.valueOf(number)));
            return tmp.length() == 11;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


