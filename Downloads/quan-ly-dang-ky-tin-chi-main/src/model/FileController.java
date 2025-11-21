package model;

import java.io.*;

/**
 * Lưu / tải object vào file (serialization).
 * Dùng để persist các manager (tuỳ chọn).
 */
public class FileController {
    public static void save(Object obj, String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(obj);
        }
    }

    public static Object load(String path) throws IOException, ClassNotFoundException {
        File f = new File(path);
        if (!f.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return ois.readObject();
        }
    }
}
