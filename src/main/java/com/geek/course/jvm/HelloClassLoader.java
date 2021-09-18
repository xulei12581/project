package com.geek.course.jvm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，
 * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            //ClassLoader的findClass方法只抛了个异常 需要继承的子类自己去重写这个方法
            //todo 需要对ClassLoader这个类进行仔细的研究
            Class<?> helloClass = new HelloClassLoader().findClass("Hello");
            //这边用反射类Method获取hello方法
            //todo 需要对Method这个类进行仔细的研究
            helloClass.getMethod("hello").invoke(helloClass.newInstance());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写findClass方法 根据类的名字去根据加密后的字节获取之前的字节码
     * 最后通过defineClass把字节转化为Class类的实例
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //文件路径定义
        Path path = Paths.get("C:\\Users\\Pactera\\Desktop\\study\\Hello.xlass");
        byte[] helloBase64 = new byte[0];
        try {
            helloBase64 = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件 这边做解密
        for (int i = 0; i < helloBase64.length; i++) {
            helloBase64[i] = (byte) (255 - helloBase64[i]);
        }

        return defineClass(name, helloBase64, 0, helloBase64.length);
    }
}
