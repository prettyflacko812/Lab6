import RouteInformation.*;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Receiver {
    public static String com;
    public static Route route;
    public static long id;
    public static String name;
    public static Integer dist;
    public static String hist;
    public static String[] arr;
    public DatagramChannel channel;
    private SocketAddress address;
    private byte[] buffer = new byte[16666];
    private ByteBuffer byteBuffer;

    public void receiveDataSendAnswer() throws IOException {
        Commands command = new Commands();
        address = new InetSocketAddress(1111);
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(address);
        try {
            while (true) {
                byteBuffer = ByteBuffer.wrap(buffer);
                do {
                    address = channel.receive(byteBuffer);
                } while (address == null);
                ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer);
                ObjectInputStream obs = new ObjectInputStream(byteStream);
                com = "";
                Object obj = obs.readObject();
                com = ((HelpObject) obj).getCom();
                route = ((HelpObject) obj).getRoute();
                id = ((HelpObject) obj).getId();
                name = ((HelpObject) obj).getName();
                dist = ((HelpObject) obj).getDist();
                hist = ((HelpObject) obj).getHist();

                arr = com.trim().split(" ", 2);
                switch (arr[0]) {
                    case "help":
                        ReceiveCommand help = new ReceiveCommand("help", Commands.info());
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                        objectOutputStream.writeObject(help.answer);
                        byte[] answer = outputStream.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer);
                        channel.send(byteBuffer, address);
                        break;
                    case "info":
                        ReceiveCommand info = new ReceiveCommand("info", Commands.info());
                        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(outputStream1);
                        objectOutputStream1.writeObject(info.answer);
                        byte[] answer1 = outputStream1.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer1);
                        channel.send(byteBuffer, address);
                        break;
                    case "show":
                        ReceiveCommand show = new ReceiveCommand("show", Commands.show());
                        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(outputStream2);
                        objectOutputStream2.writeObject(show.answer);
                        byte[] answer2 = outputStream2.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer2);
                        channel.send(byteBuffer, address);
                        break;
                    case "add":
                        ReceiveCommand add = new ReceiveCommand("add", Commands.add(route));
                        ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(outputStream3);
                        objectOutputStream3.writeObject(add.answer);
                        byte[] answer3 = outputStream3.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer3);
                        channel.send(byteBuffer, address);
                        break;
                    case "updateId":
                        ReceiveCommand updateId = new ReceiveCommand("updateId", Commands.updateId(id, route));
                        ByteArrayOutputStream outputStream4 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream4 = new ObjectOutputStream(outputStream4);
                        objectOutputStream4.writeObject(updateId.answer);
                        byte[] answer4 = outputStream4.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer4);
                        channel.send(byteBuffer, address);
                        break;
                    case "removeById":
                        ReceiveCommand removeById = new ReceiveCommand("removeById", Commands.removeById(id));
                        ByteArrayOutputStream outputStream5 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream5 = new ObjectOutputStream(outputStream5);
                        objectOutputStream5.writeObject(removeById.answer);
                        byte[] answer5 = outputStream5.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer5);
                        channel.send(byteBuffer, address);
                        break;
                    case "clear":
                        ReceiveCommand clear = new ReceiveCommand("clear", Commands.clear());
                        ByteArrayOutputStream outputStream6 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream6 = new ObjectOutputStream(outputStream6);
                        objectOutputStream6.writeObject(clear.answer);
                        byte[] answer6 = outputStream6.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer6);
                        channel.send(byteBuffer, address);
                        break;
                    case "executeScript":
                        ReceiveCommand executeScript = new ReceiveCommand("executeScript", command.executeScript().toString());
                        ByteArrayOutputStream outputStream7 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream7 = new ObjectOutputStream(outputStream7);
                        objectOutputStream7.writeObject(executeScript.answer);
                        byte[] answer7 = outputStream7.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer7);
                        channel.send(byteBuffer, address);
                        break;
                    case "exit":
                        command.save();
                        System.out.println("Клиент завершил работу программы, коллекция сохранена");
                        break;
                    case "addIfMax":
                        ReceiveCommand addIfMax = new ReceiveCommand("addIfMax", command.addIfMax(route));
                        ByteArrayOutputStream outputStream8 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream8 = new ObjectOutputStream(outputStream8);
                        objectOutputStream8.writeObject(addIfMax.answer);
                        byte[] answer8 = outputStream8.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer8);
                        channel.send(byteBuffer, address);
                        break;
                    case "removeLower":
                        ReceiveCommand removeLower = new ReceiveCommand("removeLower", Commands.removeLower(id));
                        ByteArrayOutputStream outputStream9 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream9 = new ObjectOutputStream(outputStream9);
                        objectOutputStream9.writeObject(removeLower.answer);
                        byte[] answer9 = outputStream9.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer9);
                        channel.send(byteBuffer, address);
                        break;
                    case "history":
                        ReceiveCommand history = new ReceiveCommand("history", hist);
                        ByteArrayOutputStream outputStream10 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream10 = new ObjectOutputStream(outputStream10);
                        objectOutputStream10.writeObject(history.answer);
                        byte[] answer10 = outputStream10.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer10);
                        channel.send(byteBuffer, address);
                        break;
                    case "maxByTo":
                        ReceiveCommand maxByTo = new ReceiveCommand("maxByTo", Commands.maxByTo());
                        ByteArrayOutputStream outputStream11 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream11 = new ObjectOutputStream(outputStream11);
                        objectOutputStream11.writeObject(maxByTo.answer);
                        byte[] answer11 = outputStream11.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer11);
                        channel.send(byteBuffer, address);
                        break;
                    case "filterStartsWithName":
                        ReceiveCommand filterStartsWithName = new ReceiveCommand("filterStartsWithName", Commands.filterStartsWithName(name));
                        ByteArrayOutputStream outputStream12 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream12 = new ObjectOutputStream(outputStream12);
                        objectOutputStream12.writeObject(filterStartsWithName.answer);
                        byte[] answer12 = outputStream12.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer12);
                        channel.send(byteBuffer, address);
                        break;
                    case "filterLessThanDistance":
                        ReceiveCommand filterLessThanDistance = new ReceiveCommand("filterLessThanDistance", Commands.filterLessThanDistance(dist));
                        ByteArrayOutputStream outputStream13 = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream13 = new ObjectOutputStream(outputStream13);
                        objectOutputStream13.writeObject(filterLessThanDistance.answer);
                        byte[] answer13 = outputStream13.toByteArray();
                        byteBuffer = ByteBuffer.wrap(answer13);
                        channel.send(byteBuffer, address);
                        break;
                }
            }
        } catch (ClassNotFoundException | ClassCastException e) {
            System.out.println("Сервер ожидал команду, а получил что-то не то");
        } catch (ClosedChannelException ignored) {
        } catch (IOException e) {
            System.out.println("Проблемы с подключением...");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








    /*public void sendResponse2(ReceiveCommand rc) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(rc.answer);
        byte[] answer = outputStream.toByteArray();
        byteBuffer = ByteBuffer.wrap(answer);
        channel.send(byteBuffer, address);
    }*/

    /*public void receiveObject() throws IOException, ClassNotFoundException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(1111));
        while (true) {
            try {
                sc = ssc.accept();
                ObjectInputStream ois = new ObjectInputStream(sc.socket().getInputStream());
                try {
                    while (sc.isConnected()) {
                        com = "";
                        Object obj = ois.readObject();
                        com = ((HelpObject) obj).getCom();
                        route = ((HelpObject) obj).getRoute();
                        id = ((HelpObject) obj).getId();
                        name = ((HelpObject) obj).getName();
                        dist = ((HelpObject) obj).getDist();
                        hist = ((HelpObject) obj).getHist();
                        CommandProcessing cp = new CommandProcessing();
                        cp.comProcessing();
                    }
                } catch (MyException e) {
                    e.printStackTrace();
                }
            } catch (IOException ignored) {
            }
        }
    }*/

    /*public HelpObject recive() throws IOException, InterruptedException {
        channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(1111));
        channel.configureBlocking(false);
        ArrayList<byte[]> messageList = new ArrayList<>();
        int packetCounter = 0;
        byte[] b;
        do {
            b = new byte[64836];
            ByteBuffer buffer = ByteBuffer.wrap(b);
            SocketAddress from = null;
            Thread.sleep(5);
            for (int i = 0; i < 1000; i++) {
                if (i % 200 == 0) System.out.println("Попытка считать ответ № " + (i / 200 + 1));
                from = channel.receive(buffer);
                if (from != null) break;
                Thread.sleep(10);
            }
            if (from != null) {
                ++packetCounter;
                messageList.add(b);
            } else {
                if (messageList.size() != 0) System.out.println("Пакеты сообщения потерялить.");
                else {
                    System.out.println("Ответ не был получен!");
                }
                return null;
            }
        } while (!DatagramTrimer.isFinal(b));
        System.out.println("Получено пакетов " + packetCounter);
        byte[] fullMessage = new byte[0];
        for (byte[] message : messageList) {
            fullMessage = DatagramTrimer.connectByte(fullMessage, message);
        }
        channel.close();
        return fromSerial(fullMessage);
    }

    private HelpObject fromSerial(byte[] b) {
        try (
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        new ByteArrayInputStream(b));
        ) {
            //ReceiveCommand serverMessage = (ReceiveCommand) objectInputStream.readObject();
            HelpObject obj = (HelpObject) objectInputStream.readObject();
            com = "";
            com = ((HelpObject) obj).getCom();
            this.route = ((HelpObject) obj).getRoute();
            this.id = ((HelpObject) obj).getId();
            this.name = ((HelpObject) obj).getName();
            this.dist = ((HelpObject) obj).getDist();
            this.hist = ((HelpObject) obj).getHist();
            CommandProcessing cp = new CommandProcessing();
            cp.comProcessing();
            objectInputStream.close();
            return obj;
        } catch (IOException | ClassNotFoundException | MyException e) {
            System.out.println("Ошибка десериализации.");
            System.out.println(e);
            return null;
        }
    }*/
}
