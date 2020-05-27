import RouteInformation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    private static File fileeek;
    private static File fileek;

    public static void main(String[] args) throws Exception {
        try {
            String fname = args[0];
            String fnamee = args[1];
            fileek = new File(fname);
            fileeek = new File(fnamee);

            if (fileek.toString().equals("test.csv")) {
                if (fileek.exists() && (fileek.isFile())) {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(fileek));
                    BufferedReader reader = new BufferedReader(isr);
                    String line;
                    try {
                        line = reader.readLine();
                        while ((line != null) && (line.length() > 0)) {
                            Route r1 = new Route(0);
                            r1.ParseFromCSV(line);
                            RouteCollection.col.add(r1);
                            line = reader.readLine();
                            if (r1.getId() == 0) {
                                System.out.println("Ошибка, неверный формат введенных данных");
                                RouteCollection.col.remove(r1);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else System.out.println("Файл не найден");
            } else System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Неверный ввод параметров (нужно было ввести название двух файлов)");
        }

        Receiver receiverAndSender = new Receiver();
        try {
            receiverAndSender.receiveDataSendAnswer();
        } catch (IOException ignored) {
        } catch (NullPointerException e) {
            System.out.println("Возникла ошибка. Работа сервера прекращена. Подключитесь заново");
            System.exit(0);
        }
    }

    public Server() {
        }

        public File getScriptName () {
            return fileeek;
        }

        public File getTestName () {
            return fileek;
        }
    }
