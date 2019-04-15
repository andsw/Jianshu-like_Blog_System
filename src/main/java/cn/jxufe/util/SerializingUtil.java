package cn.jxufe.util;

import java.io.*;

/**
 * @ClassName: SerializingUtil
 * @author: hsw
 * @date: 2019/4/12 16:12
 * @Description: 我放弃抵抗了，原生自带的序列化工具类不是莫名其妙地有不存在的属性无法反序列化，要么就是不支持非静态内部类。
 */
public class SerializingUtil {

    private SerializingUtil() {
    }

    /**
     * 实现单例模式的静态内部类
     */
    private static final class InnerClass {
        private static SerializingUtil util = new SerializingUtil();
    }

    public SerializingUtil getInstance() {
        return InnerClass.util;
    }

    /**
     * 序列化
     * @param t 序列化对象
     * @return 返回序列化后的字节数组
     */
    public Byte[] serialized(Serializable t) {
        try (ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteOutputStream)) {
            objectOutputStream.writeObject(t);
            byte[] bytes = byteOutputStream.toByteArray();
            Byte[] boxBytes = new Byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                boxBytes[i] = bytes[i];
            }
            System.out.println("=================================序列化后的字节数组长度为 ： " + bytes.length + "==================================");
            return boxBytes;
        } catch (IOException e) {
            System.out.println("SerializingUtil 中序列化失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     * @param boxBytes 字节数组
     * @return 返回一个Serializable实现类对象
     */
    public Serializable unSerialized(Byte[] boxBytes) {

        byte[] bytes = new byte[boxBytes.length];
        for (int i = 0; i < boxBytes.length; i++) {
            bytes[i] = boxBytes[i];
        }

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return (Serializable) objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("SerializingUtil IO失败");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("SerializingUtil 中范雪梨花失败");
            e.printStackTrace();
        }
        return null;
    }

}
