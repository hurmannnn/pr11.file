import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Main1 {

    static Scanner sc = new Scanner(System.in);
    static String file = "text3.txt";//створення змінної файл у якій збергіається новий файл
    public static void main(String[] args) {

        while (true) {//цикл, який буде продоважуватись поки користувач не натисне вийти
            showMenu();//вивклик функції показу меню
            try {
                int choice = Integer.parseInt(sc.nextLine());//читає введений рядок і перетворює його на число

                if (choice == 1) {//якщо вибір 1 - записується у файл
                    writeFile();
                }
                else if (choice == 2) {
                    readFile();// якщо вибір 2 - читає файл
                }
                else if (choice == 3) {
                    System.out.println("Вихід");
                    break;// 3 - вихід + завершення циклу
                }
                else {
                    throw new IllegalArgumentException("Немає такого пункту");
                }//перевірка помилки, якщо буде введене інше число
            }
            catch (NumberFormatException e) {
                System.out.println("Потрібно ввести число");// перевірка помилки якщо буде введено букву
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void showMenu() {//саме меню
        System.out.println("Меню");
        System.out.println("1 - Записати у файл");
        System.out.println("2 - Прочитати файл");
        System.out.println("3 - Вийти");
        System.out.print("Ваш вибір: ");
    }

    static void writeFile() {
        try {// функція яка записує у файл (обробка помилок)
            System.out.println("Введіть рядок:");
            String text = sc.nextLine();
            if (text.length() == 0) {// якщо рядок порожній - помилка
                throw new IllegalArgumentException("Рядок порожній");
            }
            FileWriter writer = new FileWriter(file, true);//створення обʼєкта FileWriter, який дозаписує у файл а не переписує заново
            writer.write(text);//записує текст у файл
            writer.close();//закриття після запису

            System.out.println("Записано у файл");
        }
        catch (IOException e) {//перевірка помилки запису у файл
            System.out.println("Помилка запису у файл");
        }
    }

    static void readFile() {//читання тексту з файлу
        try {
            FileReader reader = new FileReader(file);//відкриття файлу для читання

            char[] arr = new char[1000];//масив який морже бути записано у файл - до 1000 символів
            int i = 0;

            int symbol;

            while ((symbol = reader.read()) != -1) {//читання одного символу
                arr[i] = (char) symbol;//символ записується у масив
                i++;//перехід до до іншого символу
            }
            if (i == 0) {
                System.out.println("Файл порожній");//якщо нічого не прочитано - файл порожній
            }
            for (int j = 0; j < i; j++) {
                System.out.print(arr[j]);//масив, що проходить через усі символи масиву і виводить символ на екран
            }
            System.out.println();

            reader.close();//закриття читання
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Помилка: файл більший ніж 1000 символів");//переаірка кількості символів для читання, більше 1000 - помилка
        }
        catch (IOException e) {
            System.out.println("Помилка читання файлу");//перевірка помилки читання файлу
        }
    }
}