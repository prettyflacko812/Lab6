import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerResponse {

    /*public ServerResponse() throws IOException {
    }

    public void sendResponse1(ReceiveCommand rc) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(rc.answer);
        byte[] bytearray = byteArrayOutputStream.toByteArray();
        ByteBuffer buffer = ByteBuffer.wrap(bytearray);
        DatagramChannel dc = createChannel();
        dc.send(buffer, new InetSocketAddress("localhost", 1111));
        dc.close();
        objectOutputStream.close();
    }

    private DatagramChannel createChannel() throws IOException {
        DatagramChannel dataChannel = DatagramChannel.open();
        dataChannel.configureBlocking(true);
        try {
            SocketAddress socketAddress = new InetSocketAddress("localhost", 1111);
            dataChannel.connect(socketAddress);
        } catch (ConnectException e) {
            createChannel();
        } catch (Exception e) {
            System.out.println("Проблемы с сервером");
        }
        return dataChannel;
    }


    public void sendResponse(ReceiveCommand rc) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(rc.answer);
        objectOutputStream.flush();
        byte[] by = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        DatagramPacket datagramPacket = new DatagramPacket(by, by.length, InetAddress.getByName("localhost"), 1111);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
        objectOutputStream.close();
    }*/
}
