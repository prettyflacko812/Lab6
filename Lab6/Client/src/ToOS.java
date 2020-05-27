import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ToOS extends OutputStream {
    ByteBuffer bufF;

    public ToOS(ByteBuffer buf) {
        this.bufF = buf;
    }

    public void write(int b) throws IOException {
        bufF.put((byte) b);
    }

    public void write(byte[] bytes, int off, int len)
            throws IOException {
        bufF.put(bytes, off, len);
    }

}