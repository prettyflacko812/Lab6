import RouteInformation.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RouteCreation {

    public RouteCreation() {
    }

    public static Route createRoute() throws IOException {
        System.out.println("Введите данные для создания маршрута. Если вы допустите ошибку при вводе, то вам будет предложено создать маршрут заново");
        System.out.println("Вещественные числа нужно вводить, используя знак ','!");
        try {
            Scanner scanner = new Scanner(System.in);
            String nameScan = "";
            while (nameScan.equals("")) {
                System.out.println("Введите название маршрута: ");
                nameScan = scanner.nextLine();
            }

            System.out.println("Ввод ваших координат");
            float coorX = -319;
            int coorY;
            while (coorX < -318) {
                System.out.println("Введите координату X (float), значение должно быть больше -318. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorX = scanner.nextFloat();
            }
            System.out.println("Введите координату Y (int). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
            coorY = scanner.nextInt();

            System.out.println("Ввод координат начала маршрута, чтобы сделать поле пустым, при вводе координаты X, введите любой символ (кроме цифр)");
            System.out.println("Если вы сделаете ошибку при вводе координаты X, поле так же будет пустым");
            Long coorFromX;
            Float coorFromY = null;
            int coorFromZ = 0;
            Scanner scanner2 = new Scanner(System.in);
            try {
                System.out.println("Введите координату X (long). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorFromX = scanner2.nextLong();
            } catch (InputMismatchException e) {
                coorFromX = null;
            }
            if (coorFromX != null) {
                while (coorFromY == null) {
                    System.out.println("Введите координату Y (Float), значение не должно быть null. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                    coorFromY = scanner.nextFloat();
                }
                System.out.println("Введите координату Z (int). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorFromZ = scanner.nextInt();
            }

            System.out.println("Ввод координат конца маршрута, чтобы сделать поле пустым, при вводе координаты X, введите любой символ (кроме цифр)");
            System.out.println("Если вы сделаете ошибку при вводе координаты X, поле так же будет пустым");
            Double coorToX;
            Float coorToY = null;
            double coorToZ = 0;
            Scanner scanner3 = new Scanner(System.in);
            try {
                System.out.println("Введите координату X (double). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorToX = scanner3.nextDouble();
            } catch (InputMismatchException e) {
                coorToX = null;
            }

            if (coorToX != null) {
                while (coorToY == null) {
                    System.out.println("Введите координату Y (Float), значение не должно быть null. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                    coorToY = scanner.nextFloat();
                }
                System.out.println("Введите координату Z (double). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorToZ = scanner.nextDouble();
            }

            Integer dist = 0;
            while (dist < 1) {
                System.out.println("Введите дистанцию маршрута (Integer), значение поля должно быть больше 1. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                dist = scanner.nextInt();
            }

            if (coorFromX == null && coorToX == null) {
                Route route = new Route(nameScan, new Coordinates(coorX, coorY), null, null, dist);
                RouteCollection.col.add(route);
                return route;
            } else if (coorFromX == null) {
                Route route = new Route(nameScan, new Coordinates(coorX, coorY), null, new LocationTo(coorToX, coorToY, coorToZ), dist);
                RouteCollection.col.add(route);
                return route;
            } else if (coorToX == null) {
                Route route = new Route(nameScan, new Coordinates(coorX, coorY), new LocationFrom(coorFromX, coorFromY, coorFromZ), null, dist);
                RouteCollection.col.add(route);
                return route;
            } else {
                Route route = new Route(nameScan, new Coordinates(coorX, coorY), new LocationFrom(coorFromX, coorFromY, coorFromZ), new LocationTo(coorToX, coorToY, coorToZ), dist);
                RouteCollection.col.add(route);
                return route;
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Заполните данные заново");
            createRoute();
        } catch (MyException e) {
            System.out.println("Неверный формат введенных данных. Заполните данные заново");
            createRoute();
        }
        return null;
    }

    public static Route createForUpdateId() throws IOException {
        System.out.println("Введите данные для создания маршрута. Если вы допустите ошибку при вводе, то вам будет предложено создать маршрут заново");
        System.out.println("Вещественные числа нужно вводить, используя знак ','!");
        try {
            Scanner scanner = new Scanner(System.in);
            String nameScan = "";
            while (nameScan.equals("")) {
                System.out.println("Введите название маршрута: ");
                nameScan = scanner.nextLine();
            }

            System.out.println("Ввод ваших координат");
            float coorX = -319;
            int coorY;
            while (coorX < -318) {
                System.out.println("Введите координату X (float), значение должно быть больше -318. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorX = scanner.nextFloat();
            }
            System.out.println("Введите координату Y (int). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
            coorY = scanner.nextInt();

            System.out.println("Ввод координат начала маршрута, чтобы сделать поле пустым, при вводе координаты X, введите любой символ (кроме цифр)");
            System.out.println("Если вы сделаете ошибку при вводе координаты X, поле так же будет пустым");
            Long coorFromX;
            Float coorFromY = null;
            int coorFromZ = 0;
            Scanner scanner2 = new Scanner(System.in);
            try {
                System.out.println("Введите координату X (long). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorFromX = scanner2.nextLong();
            } catch (InputMismatchException e) {
                coorFromX = null;
            }
            if (coorFromX != null) {
                while (coorFromY == null) {
                    System.out.println("Введите координату Y (Float), значение не должно быть null. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                    coorFromY = scanner.nextFloat();
                }
                System.out.println("Введите координату Z (int). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorFromZ = scanner.nextInt();
            }

            System.out.println("Ввод координат конца маршрута, чтобы сделать поле пустым, при вводе координаты X, введите любой символ (кроме цифр)");
            System.out.println("Если вы сделаете ошибку при вводе координаты X, поле так же будет пустым");
            Double coorToX;
            Float coorToY = null;
            double coorToZ = 0;
            Scanner scanner3 = new Scanner(System.in);
            try {
                System.out.println("Введите координату X (double). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorToX = scanner3.nextDouble();
            } catch (InputMismatchException e) {
                coorToX = null;
            }

            if (coorToX != null) {
                while (coorToY == null) {
                    System.out.println("Введите координату Y (Float), значение не должно быть null. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                    coorToY = scanner.nextFloat();
                }
                System.out.println("Введите координату Z (double). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorToZ = scanner.nextDouble();
            }

            Integer dist = 0;
            while (dist < 1) {
                System.out.println("Введите дистанцию маршрута (Integer), значение поля должно быть больше 1. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                dist = scanner.nextInt();
            }

            if (coorFromX == null && coorToX == null) {
                return new Route(nameScan, new Coordinates(coorX, coorY), null, null, dist);
            } else if (coorFromX == null) {
                return new Route(nameScan, new Coordinates(coorX, coorY), null, new LocationTo(coorToX, coorToY, coorToZ), dist);
            } else if (coorToX == null) {
                return new Route(nameScan, new Coordinates(coorX, coorY), new LocationFrom(coorFromX, coorFromY, coorFromZ), null, dist);
            } else {
                return new Route(nameScan, new Coordinates(coorX, coorY), new LocationFrom(coorFromX, coorFromY, coorFromZ), new LocationTo(coorToX, coorToY, coorToZ), dist);
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Заполните данные заново");
            createRoute();
        } catch (MyException e) {
            System.out.println("Неверный формат введенных данных. Заполните данные заново");
            createRoute();
        }
        return null;
    }
}