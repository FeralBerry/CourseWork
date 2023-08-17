import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Main {
    //Метод для добавления элемента в массив
    private static <T> T[] append(T[] arr, T element)
    {
        T[] array = Arrays.copyOf(arr, arr.length + 1);
        array[arr.length] = element;
        return array;
    }
    public static void main(String[] args) throws IOException {
        //Объект для считывания файла
        BufferedReader br = null;
        //отлавливание ошибок отсутствия файла
        try {
            //Создание объекта на основе файла
            File file = new File("lib.txt");
            //проверка существования файла
            if(file.exists()){
                //при отсутствии создание файла
                file.createNewFile();
            }
            //открытие файла для записи в него строк
            PrintWriter lib = new PrintWriter(file);
            //Запись строк в файл
            lib.println("Петька;20000");
            lib.println("Василий Иванович;30000");
            lib.println("Анка;15000");
            lib.println("Вася Пупкин;10000");
            //Закрытие файла
            lib.close();
            //Считывание файла
            br = new BufferedReader(new FileReader("lib.txt"));
            String line;
            String[] array = new String[0];
            //проход строк в файле до тех пор пока строка не будет пустой
            while((line = br.readLine()) != null){
                //разбиваем строку на массив по разделителю
                String[] split_line = line.split(";");
                //Вывод массива
                System.out.println("ФИО: " + split_line[0] + ", Зарплата: " + split_line[1]);
                //создаем массив данных из 2 столбца изначального массива
                array = append(array,split_line[1]);
            }
            //Объявляем переменный для рассчета минимальной, максимальной и средней зарплаты
            //в переменный минимальной и максимальной зарплаты добавляем 1 значение массива
            double min_price = Double.parseDouble(array[0]);
            double max_price = Double.parseDouble(array[0]);
            double middle = 0;
            //Обходим массив заработных плат
            for(int i = 0; i < array.length; i++){
                //Сравниваем каждое новое значение массива с предъидущим и записываем
                if (max_price < Integer.parseInt (array[i]))
                    max_price = Integer.parseInt (array[i]);
                if (min_price > Integer.parseInt (array[i]))
                    min_price = Integer.parseInt (array[i]);
                //находим среднее значение для каждого элемента массива относительно всего массива и суммируем
                middle += (double) Integer.parseInt(array[i]) /array.length;
            }
            //Вывод массива зарплат
            System.out.println(Arrays.toString(array));
            //Вывод минимальной, максимальной и средней зарплаты
            System.out.println("Минимальная зарплата:" + min_price);
            System.out.println("Максимальная зарплата:" + max_price);
            System.out.println("Средняя зарплата:" + middle);
        } catch (IOException e){
            //Вывод ошибки исключения
            System.out.println("Error: " + e);
        } finally {
            try {
                br.close();
            } catch (IOException e){
                System.out.println("Error: " + e);
            }
        }
    }
}