import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Receiving {

    /*Receiving() throws SocketException {
    }

    private DatagramSocket ds = new DatagramSocket(1111);

    void receive() throws IOException {
        byte[] buf = new byte[16666];
        DatagramPacket pack = new DatagramPacket(buf, buf.length);
        ds.receive(pack);
        ds.close();
        //String data = new String(buf, StandardCharsets.UTF_8);
        //System.out.println(data);
        String data1 = new String(buf, StandardCharsets.UTF_8);
        System.out.println(data1.substring(7));
    }*/

    /*private SocketAddress socketAddress;
    private DatagramSocket socket;
    private SerializationManager<HelpObject> commandSerializationManager = new SerializationManager<>();
    private SerializationManager<ReceiveCommand> responseSerializationManager = new SerializationManager<>();

    public void receive2() throws IOException, ClassNotFoundException {
        byte[] answerInBytes = new byte[16666];
        DatagramPacket packet = new DatagramPacket(answerInBytes, answerInBytes.length);
        ds.receive(packet);
        String result = responseSerializationManager.readObject(answerInBytes).toString();
        System.out.println("Получен ответ от сервера: ");
        System.out.println(result);
    }*/

    /*private DatagramChannel createChannel1() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(true);
        try {
            SocketAddress socketAddress = new InetSocketAddress("localhost", 1111);
            datagramChannel.connect(socketAddress);
        } catch (ConnectException e) {
            createChannel1();
        } catch (Exception e) {
            System.out.println("Проблемы с сервером");
        }
        return datagramChannel;
    }

    public void receive1() throws IOException {
        DatagramChannel channel = createChannel1();
        byte[] buf = new byte[16666];
        byte[] b = new byte[16666];
        ByteBuffer f = ByteBuffer.wrap(b);
        channel.receive(f);
        channel.close();
        //String data = new String(buf, StandardCharsets.UTF_8);
        //System.out.println(data);
        String data1 = new String(buf, StandardCharsets.UTF_8);
        System.out.println(data1.substring(7));
    }*/
}
