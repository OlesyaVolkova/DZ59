/*Этот код создает клиентский сокет, отправляет запрос на сервер в формате <валюта1:валюта2:значение_в_валюте1> и получает ответ с результатом конвертации.
*/


import java.io.*;
import java.net.*;

public class CurrencyConverterClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Введите запрос в формате <валюта1:валюта2:значение_в_валюте1>: ");
            String request = reader.readLine();
            writer.println(request);

            String response = serverReader.readLine();
            System.out.println("Результат конвертации: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}