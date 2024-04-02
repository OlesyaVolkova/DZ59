/* Реализовать сервис (на сервере), который будет конвертировать валюты одна в другую:
3 валют достаточно. На клиенте вводится запрос в формате <валюта1:валюта2:значение_в_валюте1>
(без угловых скобок) и получает ответ в виде значения в валюте 2.
Этот код запускает сервер, который принимает запросы от клиентов, конвертирует валюты и отправляет результат обратно клиенту.
*/

import java.io.*;
import java.net.*;

public class CurrencyConverterServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Сервер запущен. Ожидаем подключение...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился клиент: " + clientSocket);

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String request = reader.readLine();
                System.out.println("Получен запрос: " + request);

                String[] parts = request.split(":");
                String currency1 = parts[0];
                String currency2 = parts[1];
                double value = Double.parseDouble(parts[2]);

                double result = convertCurrency(currency1, currency2, value);
                writer.println(result);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double convertCurrency(String currency1, String currency2, double value) {
        return value * 2;
    }
}
