package net.tcp;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
    public static void closeAll(Closeable... io) {
        for (Closeable temp : io) {
            try {
                temp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
