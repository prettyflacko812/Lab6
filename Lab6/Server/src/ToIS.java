import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ToIS extends InputStream {

    ByteBuffer buff;

    public ToIS(ByteBuffer buf) {
        this.buff = buf;
    }

    public int read() throws IOException {
        if (!buff.hasRemaining()) {
            return -1;
        }
        return buff.get() & 0xFF;
    }

    public int read(byte[] bytes, int off, int len)
            throws IOException {
        if (!buff.hasRemaining()) {
            return -1;
        }

        len = Math.min(len, buff.remaining());
        buff.get(bytes, off, len);
        return len;
    }
}