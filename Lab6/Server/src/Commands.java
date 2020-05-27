import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

import RouteInformation.*;

public class Commands implements Comparator<Route> {

    public Commands() throws IOException {
    }

    public static String help() {
        return "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "updateId {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "removeById id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "executeScript file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "addIfMax {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "removeLower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние команды (без их аргументов)\n" +
                "maxByTo : вывести название маршрута, значение координаты X поля to которого является максимальным\n" +
                "filterStartsWithName name : вывести элементы, значение поля name которых начинается с заданной подстроки\n" +
                "filterLessThanDistance distance : вывести элементы, значение поля distance которых меньше заданного\n";
    }

    public static String info() {
        String info;
        if (RouteCollection.col.size() != 0) {
            info = "Тип коллекции: LinkedHashSet" + "\n" +
                    "Размерность коллекции равна: " + RouteCollection.col.size() + "\n" +
                    "Дата инициализации: " + RouteCollection.dateTime;
        } else info = "Коллекция пуста";
        return info;
    }

    public static String show() {
        String show;
        if (RouteCollection.col.size() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            RouteCollection.col.forEach((r) -> sb.append(r.toString()).append(","));
            sb.deleteCharAt(sb.lastIndexOf(","));
            return String.valueOf(sb.append("]"));
        } else show = "Коллекция пуста";
        return show;
    }

    public static String add(Route route) {
        RouteCollection.col.add(route);
        return "Элемент добавлен в коллецию";
    }

    public static String updateId(long id, Route route) {
        int k = 0;
        Iterator<Route> it = RouteCollection.col.iterator();
        while (it.hasNext()) {
            Route route1 = it.next();
            try {
                if (route1.id == id) {
                    k++;
                    route1.setName(route.getName());
                    route1.setCoordinates(route.getCoordinates());
                    route1.setLocationFrom(route.getLocationFrom());
                    route1.setLocationTo(route.getLocationTo());
                    route1.setDistance(route.getDistance());
                }
            } catch (ConcurrentModificationException ignored) {
            }
        }
        if (k == 0) {
            return "Элемента с таким ID в коллекции нет";
        } else {
            return "Коллекция обновлена";
        }
    }

    public static String removeById(long id) {
        int k = 0;
        Iterator<Route> it = RouteCollection.col.iterator();
        while (it.hasNext()) {
            Route route = it.next();
            if (route.id == id) {
                k++;
            }
        }
        Iterator<Route> itr = RouteCollection.col.iterator();
        if (k == 0) return "В коллекции нет элемента с таким ID ";
        else {
            while (itr.hasNext()) {
                Route route = itr.next();
                if (route.id == id) {
                    RouteCollection.col.remove(route);
                    return "Элемент коллекции удален";

                }
            }
        }
        return null;
    }

    public static String clear() {
        RouteCollection.col.clear();
        return "Все элементы коллекции удалены";
    }

    public void save() {
        String pathToFile = "test";
        File file = new File(pathToFile + ".csv");
        try {
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter writer = new BufferedWriter(fw);
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                String line = route.makeString();
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Коллекция сохранена в файл test.csv");
    }

    public String addIfMax(Route route) {
        RouteCollection.col.add(route);
        int k = 0;
        int minCom = 0;
        ArrayList<Integer> com = new ArrayList<>();
        Iterator<Route> it = RouteCollection.col.iterator();
        while (it.hasNext()) {
            route = it.next();
            k++;
            com.add(compare(getLast(RouteCollection.col), route));
        }
        if (k > 0) {
            minCom = Collections.min(com);
            if (minCom == -1) {
                RouteCollection.col.remove(getLast(RouteCollection.col));
                return "Элемент не добавлен в коллекцию, так как он не привышает максимальный";
            } else return "Новый элемент добавлен в коллекцию";
        } else if (k == 0) {
            return "Новый элемент добавлен в коллекцию";
        }
        return null;
    }

    public static <E> E getLast(Collection<E> c) {
        E last = null;
        for (E e : c) last = e;
        return last;
    }

    @Override
    public int compare(Route o1, Route o2) {
        if (o1.getId() > o2.getId()) {
            return 1;
        } else if (o1.getId().equals(o2.getId())) {
            return 0;
        } else return -1;
    }


    public static String removeLower(long id) {
        int k = 0;
        int check = RouteCollection.col.size();
        int compCheck = RouteCollection.col.size();
        long idd = 0;
        ArrayList<Long> removed = new ArrayList<>();
        Iterator<Route> it = RouteCollection.col.iterator();
        while (it.hasNext()) {
            Route route = it.next();
            if (route.id == id) {
                k++;
                idd = route.id;
            }
        }
        if (k > 0) {
            Iterator<Route> itt = RouteCollection.col.iterator();
            while (itt.hasNext()) {
                Route routee = itt.next();
                if (routee.id < id) {
                    itt.remove();
                    check++;
                    removed.add(routee.id);
                }
            }
            if (check != compCheck)
                return "Из коллекции удалены элементы с ID: " + removed;
            else
                return "В коллекции нет элемента, у которого ID меньше " + idd;
        } else return "В коллекции нет элемента с таким ID";
    }

    public static String maxByTo() {
        Double maxToX = 0D;
        int k = 0;
        ArrayList<Double> count1 = new ArrayList<>();
        try {
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                k++;
                if (route.getLocationTo() != null) {
                    count1.add(route.getLocationTo().getX());
                }
            }
            if (k > 0) {
                maxToX = Collections.max(count1);
                Iterator<Route> itt = RouteCollection.col.iterator();
                while (itt.hasNext()) {
                    Route routt = itt.next();
                    if (routt.getLocationTo() != null) {
                        if (maxToX == routt.getLocationTo().getX())
                            return "Название маршрута с максимальной координатой X поля to: " + routt.getName();
                    }
                }
            } else return "Коллекция пуста";
        } catch (InputMismatchException | ConcurrentModificationException ignored) {
        } catch (NoSuchElementException e) {
            System.out.println("Такого элемента нет");
        }
        return null;
    }

    public static String filterStartsWithName(String name) {
        int k = 0;
        Iterator<Route> it = RouteCollection.col.iterator();
        while (it.hasNext()) {
            Route route = it.next();
            String name1 = route.getName();
            if (name1.contains(name)) {
                k++;
            }
        }
        if (k == 0) return "В коллекции нет такого маршрута " + name;
        else {
            Iterator<Route> it1 = RouteCollection.col.iterator();
            while (it1.hasNext()) {
                Route route = it1.next();
                String name1 = route.getName();
                if (name1.contains(name)) {
                    return "Маршрут " + route;
                }
            }
        }
        return null;
    }

    public static String filterLessThanDistance(Integer dist) {
        int k = 0;
        Iterator<Route> it = RouteCollection.col.iterator();
        while (it.hasNext()) {
            Route route = it.next();
            Integer dis = route.getDistance();
            if (dis.equals(dist)) {
                k++;
            }
        }
        if (k == 0) return "В коллекции нет элемента с такой дистанцией";
        else {
            Iterator<Route> itt = RouteCollection.col.iterator();
            while (itt.hasNext()) {
                Route route = itt.next();
                Integer dis = route.getDistance();
                if (dis < dist) {
                    return "Маршрут " + route;
                }
            }
        }
        return null;
    }


   /* public static String filterLessThanDistance(Integer dist) {
        int k = 0;
        Iterator<Route> it = RouteCollection.col.iterator();
        while (it.hasNext()) {
            Route route = it.next();
            if (route.getDistance() == dist) {
                k++;
            }
        }
        if (k > 0) {
            Iterator<Route> itt = RouteCollection.col.iterator();
            while (itt.hasNext()) {
                Route routee = itt.next();
                if (routee.getDistance() < dist) {
                    return "Маршрут " + routee;
                }
            }
        } else return "В коллекции нет элемента с такой дистанцией";
        return null;
    }*/

    private int scriptStuck = 0;
    private Route routT = new Route();

    public ArrayList<String> executeScript() {
        try {
            ArrayList<String> extraData = new ArrayList<>();
            Server ser = new Server();
            if (!(ser.getScriptName().toString().equals("script.txt")))
                System.out.println("Файл не найден");
            else {
                String command = "";
                String[] lastCommand;
                try {
                    FileReader fr = new FileReader(ser.getScriptName());
                    Scanner scrScan = new Scanner(fr);
                    while ((scrScan.hasNextLine()) && (scriptStuck < 5)) {
                        command = scrScan.nextLine();
                        lastCommand = command.trim().split(" ", 2);
                        switch (lastCommand[0]) {
                            case "help":
                                help();
                                extraData.add(help());
                                break;
                            case "info":
                                info();
                                extraData.add(info());
                                break;
                            case "show":
                                show();
                                extraData.add(show());
                                break;
                            case "add":
                                try {
                                    int check = RouteCollection.col.size();
                                    Route route1 = new Route();
                                    route1.setId(routT.randomId());
                                    route1.setName(scrScan.nextLine());
                                    float coorX = Float.parseFloat(scrScan.nextLine());
                                    int coorY = Integer.parseInt(scrScan.nextLine());
                                    route1.setCoordinates(new Coordinates(coorX, coorY));
                                    route1.setCreationDate(ZonedDateTime.now());
                                    route1.setLocationFrom(null);
                                    try {
                                        long fromX = Long.parseLong(scrScan.nextLine());
                                        Float fromY = Float.parseFloat(scrScan.nextLine());
                                        int fromZ = Integer.parseInt(scrScan.nextLine());
                                        route1.setLocationFrom(new LocationFrom(fromX, fromY, fromZ));
                                    } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException ignored) {
                                    }
                                    route1.setLocationTo(null);
                                    try {
                                        double toX = Double.parseDouble(scrScan.nextLine());
                                        Float toY = Float.parseFloat(scrScan.nextLine());
                                        double toZ = Double.parseDouble(scrScan.nextLine());
                                        route1.setLocationTo(new LocationTo(toX, toY, toZ));
                                    } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException ignored) {
                                    }
                                    Integer dist = Integer.parseInt(scrScan.nextLine());
                                    route1.setDistance(dist);
                                    RouteCollection.col.add(route1);
                                    if ((RouteCollection.col.size() != check) && (!getLast(RouteCollection.col).getName().equals("")))
                                        System.out.println("Новый маршрут добавлен в коллекцию");
                                    else {
                                        System.out.println("Ошибка ввода");
                                        RouteCollection.col.remove(getLast(RouteCollection.col));
                                    }
                                } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException e) {
                                    System.out.println("Неверный формат введенных данных");
                                }
                                break;
                            case "updateId":
                                try {
                                    long saveId = -1;
                                    java.time.ZonedDateTime dd = null;
                                    long idScanner = -1;
                                    int k = 0;
                                    idScanner = Long.parseLong(scrScan.nextLine());
                                    Iterator<Route> it = RouteCollection.col.iterator();
                                    while (it.hasNext()) {
                                        Route rr = it.next();
                                        if (rr.getId() == idScanner) {
                                            dd = rr.getCreationDate();
                                            saveId = rr.getId();
                                            it.remove();
                                            k++;
                                        }
                                    }
                                    if (k == 0) {
                                        System.out.println("Элемента c тким ID нет в коллекции");
                                    } else {
                                        int check = RouteCollection.col.size();
                                        Route route2 = new Route();
                                        route2.setId(saveId);
                                        route2.setName(scrScan.nextLine());
                                        float coorX = Float.parseFloat(scrScan.nextLine());
                                        int coorY = Integer.parseInt(scrScan.nextLine());
                                        route2.setCoordinates(new Coordinates(coorX, coorY));
                                        route2.setCreationDate(dd);
                                        route2.setLocationFrom(null);
                                        try {
                                            long fromX = Long.parseLong(scrScan.nextLine());
                                            Float fromY = Float.parseFloat(scrScan.nextLine());
                                            int fromZ = Integer.parseInt(scrScan.nextLine());
                                            route2.setLocationFrom(new LocationFrom(fromX, fromY, fromZ));
                                        } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException ignored) {
                                        }
                                        route2.setLocationTo(null);
                                        try {
                                            double toX = Double.parseDouble(scrScan.nextLine());
                                            Float toY = Float.parseFloat(scrScan.nextLine());
                                            double toZ = Double.parseDouble(scrScan.nextLine());
                                            route2.setLocationTo(new LocationTo(toX, toY, toZ));
                                        } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException ignored) {
                                        }
                                        Integer dist = Integer.parseInt(scrScan.nextLine());
                                        route2.setDistance(dist);
                                        RouteCollection.col.add(route2);
                                        if ((RouteCollection.col.size() != check) && (!getLast(RouteCollection.col).getName().equals("")))
                                            System.out.println("Маршрут обновлен");
                                        else {
                                            System.out.println("Ошибка ввода");
                                            RouteCollection.col.remove(getLast(RouteCollection.col));
                                        }
                                    }
                                } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException e) {
                                    System.out.println("Неверный формат введенных данных");
                                }
                                break;
                            case "clear":
                                RouteCollection.col.clear();
                                System.out.println("Все элементы коллекции удалены");
                                break;
                            case "maxByTo":
                                maxByTo();
                                break;
                            case "removeById":
                                removeByIdScr(scrScan.nextLine());
                                break;
                            case "removeLower":
                                removeLowerScr(scrScan.nextLine());
                                break;
                            case "filterStartsWithName":
                                filterStartsWithNameScr(scrScan.nextLine());
                                break;
                            case "filterLessThanDistance":
                                filterLessThanDistanceScr(scrScan.nextLine());
                                break;
                            case "addIfMax":
                                int minCom = 0;
                                ArrayList<Integer> com = new ArrayList<>();

                                try {
                                    int check = RouteCollection.col.size();
                                    Route route2 = new Route();
                                    route2.setId(routT.randomId());
                                    route2.setName(scrScan.nextLine());
                                    float coorX = Float.parseFloat(scrScan.nextLine());
                                    int coorY = Integer.parseInt(scrScan.nextLine());
                                    route2.setCoordinates(new Coordinates(coorX, coorY));
                                    route2.setCreationDate(ZonedDateTime.now());
                                    route2.setLocationFrom(null);
                                    try {
                                        long fromX = Long.parseLong(scrScan.nextLine());
                                        Float fromY = Float.parseFloat(scrScan.nextLine());
                                        int fromZ = Integer.parseInt(scrScan.nextLine());
                                        route2.setLocationFrom(new LocationFrom(fromX, fromY, fromZ));
                                    } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException ignored) {
                                    }
                                    route2.setLocationTo(null);
                                    try {
                                        double toX = Double.parseDouble(scrScan.nextLine());
                                        Float toY = Float.parseFloat(scrScan.nextLine());
                                        double toZ = Double.parseDouble(scrScan.nextLine());
                                        route2.setLocationTo(new LocationTo(toX, toY, toZ));
                                    } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException ignored) {
                                    }
                                    Integer dist = Integer.parseInt(scrScan.nextLine());
                                    route2.setDistance(dist);
                                    RouteCollection.col.add(route2);

                                    Iterator<Route> it = RouteCollection.col.iterator();
                                    while (it.hasNext()) {
                                        Route route = it.next();
                                        com.add(compare(getLast(RouteCollection.col), route));
                                    }

                                    if ((RouteCollection.col.size() != check) && (!getLast(RouteCollection.col).getName().equals(""))) {
                                        minCom = Collections.min(com);
                                        if (minCom == -1) {
                                            System.out.println("Элемент не добавлен в коллекцию, так как он не привышает максимальный");
                                            RouteCollection.col.remove(getLast(RouteCollection.col));
                                        } else System.out.println("Новый элемент добавлен в коллекцию");
                                    } else {
                                        System.out.println("Ошибка ввода");
                                        RouteCollection.col.remove(getLast(RouteCollection.col));
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Ошибка ввода");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ConcurrentModificationException e) {
                                    System.out.println();
                                } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException | StringIndexOutOfBoundsException e) {
                                    System.out.println("Неверный формат введенных данных");
                                }
                                break;
                            case "executeScript":
                                scriptStuck++;
                                executeScript();
                                break;
                            case "exit":
                                System.exit(0);
                            default:
                                System.out.println("Такой команды не существует: " + command);
                        }
                    }
                    fr.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден");
                }
            }
            return extraData;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Файл не найден");
        }
        return null;
    }

    public void removeByIdScr(String str) {
        try {
            long idScan = -1;
            int k = 0;
            Long id = Long.parseLong(str);
            idScan = id;
            Iterator<Route> it = RouteCollection.col.iterator();
            try {
                while (it.hasNext()) {
                    Route route = it.next();
                    if (route.getId() == idScan) {
                        RouteCollection.col.remove(route);
                        System.out.println("Элемент коллекции удален");
                        k++;
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.out.println();
            }
            if (k == 0) {
                System.out.println("Элемента с таким ID в коллекции нет");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка, вы должны были указать ID элемента, который вы хотлеи удалить");
        }
    }

    public void removeLowerScr(String str) {
        try {
            long idScan = -1;
            int k = 0;
            int check = RouteCollection.col.size();
            int compCheck = RouteCollection.col.size();
            long idd = 0;
            Long id = Long.parseLong(str);
            idScan = id;
            ArrayList<Long> removed = new ArrayList<>();
            Iterator<Route> it = RouteCollection.col.iterator();
            try {
                while (it.hasNext()) {
                    Route route = it.next();
                    if (route.getId() == idScan) {
                        k++;
                        idd = route.getId();
                    }
                }
                if (k > 0) {
                    Iterator<Route> itt = RouteCollection.col.iterator();
                    while (itt.hasNext()) {
                        Route routee = itt.next();
                        if (routee.getId() < idScan) {
                            itt.remove();
                            check++;
                            removed.add(routee.getId());
                        }
                    }
                    if (check != compCheck)
                        System.out.println("Из коллекции удалены элементы с ID: " + removed);
                    else
                        System.out.println("В коллекции нет элемента, у которого ID меньше " + idd);
                } else System.out.println("В коллекции нет элемента с таким ID");
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода");
            } catch (ConcurrentModificationException e) {
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка, вы должны были указать ID элемента");
        }
    }

    public void filterStartsWithNameScr(String str) {
        try {
            String nameScan = "";
            int k = 0;
            nameScan = str;
            Iterator<Route> it = RouteCollection.col.iterator();
            try {
                while (it.hasNext()) {
                    Route route = it.next();
                    String s = route.getName();
                    String n = s.substring(0, 3);
                    if (n.equals(nameScan)) {
                        System.out.println(route);
                        k++;
                    }
                }
                if (k == 0)
                    System.out.println("В коллекции нет маршрута, название которого бы начиналось с данной строки, либо ваша строка не содержит ровно 3 символа");
            } catch (InputMismatchException | StringIndexOutOfBoundsException e) {
                System.out.println("Ошибка ввода");
            } catch (ConcurrentModificationException e) {
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка, вы должны были указать первые 3 символа названия элемента");
        }
    }

    public void filterLessThanDistanceScr(String str) {
        try {
            long idScan = -1;
            int k = 0;
            Integer dist = Integer.parseInt(str);
            idScan = dist;
            Iterator<Route> it = RouteCollection.col.iterator();
            try {
                while (it.hasNext()) {
                    Route route = it.next();
                    if (route.getDistance() == idScan) {
                        k++;
                    }
                }
                if (k > 0) {
                    Iterator<Route> itt = RouteCollection.col.iterator();
                    while (itt.hasNext()) {
                        Route routee = itt.next();
                        if (routee.getDistance() < idScan) {
                            System.out.println(routee);
                        }
                    }
                } else System.out.println("В коллекции нет элемента с такой дистанцией");
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода");
            } catch (ConcurrentModificationException e) {
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка, вы должны были указать дистанцию");
        }
    }

}
