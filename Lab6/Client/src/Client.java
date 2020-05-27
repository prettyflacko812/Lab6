import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) throws SocketException {
        ReadCommands rc = new ReadCommands();
        try {
            rc.readCom();
        } catch (ConnectException e) {
            System.out.println("Нет связи с сервером...");
        } catch (IOException e) {
            System.out.println("Удаленный хост принудительно разорвал существующее подключение. Клиенту необходимо повторить подключение");
        }
    }
}
