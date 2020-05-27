import RouteInformation.MyException;

import java.io.IOException;

public class CommandProcessing extends Receiver {

    /*public CommandProcessing() throws IOException, MyException {
    }

    private Commands command = new Commands();

    public void comProcessing() throws IOException {
        ServerResponse sr = new ServerResponse();
        Receiver rec = new Receiver();
        arr = com.trim().split(" ", 2);
        switch (arr[0]) {
            case "help":
                ReceiveCommand help = new ReceiveCommand("help", Commands.help());
                sr.sendResponse1(help);
                break;
            case "info":
                ReceiveCommand inf = new ReceiveCommand("info", Commands.info());
                sr.sendResponse1(inf);
                break;
            case "show":
                ReceiveCommand sh = new ReceiveCommand("show", Commands.show());
                sr.sendResponse1(sh);
                break;
            case "add":
                ReceiveCommand add = new ReceiveCommand("add", Commands.add(route));
                sr.sendResponse1(add);
                break;
            case "updateId":
                ReceiveCommand upd = new ReceiveCommand("updateId", Commands.updateId(id, route));
                sr.sendResponse1(upd);
                break;
            case "removeById":
                ReceiveCommand remById = new ReceiveCommand("removeById", Commands.removeById(id));
                sr.sendResponse1(remById);
                break;
            case "clear":
                ReceiveCommand cl = new ReceiveCommand("clear", Commands.clear());
                sr.sendResponse1(cl);
                break;
            case "executeScript":
                ReceiveCommand ex = new ReceiveCommand("executeScript", command.executeScript().toString());
                sr.sendResponse1(ex);
                break;
            case "exit":
                command.save();
                System.out.println("Клиент завершил работу программы, коллекция сохранена");
                channel.close();
                break;
            case "addIfMax":
                ReceiveCommand addIfMax = new ReceiveCommand("addIfMax", command.addIfMax(route));
                sr.sendResponse1(addIfMax);
                break;
            case "removeLower":
                ReceiveCommand remLw = new ReceiveCommand("removeLower", Commands.removeLower(id));
                sr.sendResponse1(remLw);
                break;
            case "history":
                ReceiveCommand his = new ReceiveCommand("history", hist);
                sr.sendResponse1(his);
                break;
            case "maxByTo":
                ReceiveCommand maxByTo = new ReceiveCommand("maxByTo", Commands.maxByTo());
                sr.sendResponse1(maxByTo);
                break;
            case "filterStartsWithName":
                ReceiveCommand filtName = new ReceiveCommand("filterStartsWithName", Commands.filterStartsWithName(name));
                sr.sendResponse1(filtName);
                break;
            case "filterLessThanDistance":
                ReceiveCommand filtDist = new ReceiveCommand("filterLessThanDistance", Commands.filterLessThanDistance(dist));
                sr.sendResponse1(filtDist);
                break;
        }
    }*/
}
