package com.example.demo.util.generator;


import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*@Log4j2
public class Main {


    public static void main(String[] args) throws IOException {*/
/*
        File file = new File("C:/Users/User/Desktop/tttt");
*/
//        System.out.println("absolute path " + file.getAbsolutePath());
//        System.out.println("name " + file.getName());
//        System.out.println("absolute file " + file.getAbsoluteFile());
//        System.out.println("path " + file.getPath());
//        System.out.println(Paths.get(file.getAbsolutePath()).normalize());
//        System.out.println(Files.createDirectories(Paths.get(file.getAbsolutePath()).normalize()));
//
//        String s = "C:/Users/User/Desktop/uploads";
//        System.out.println(s.substring(22));
//        Path filePath = Paths.get(file.getAbsolutePath()).resolve(file.getName()).normalize();
//        Resource resource = new UrlResource(filePath.toUri());
//        System.out.println(resource);


//        Path path = Paths.get("SFSDF".toUp).normalize().resolve(file.getName());
//        System.out.println(path);


    /*    PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\User\\Desktop\\ba.txt")));
        System.setOut(System.out);
        System.out.println("in");
        System.setOut(out);
        System.out.println("file");
        out.close();
    }*/
     /*class Test {


        static class A {

            public void a(Number n) {
                System.out.println("number");
            }

            public void a(Double n) {
                System.out.println("double");
            }
        }

        public static void main(String[] args) {

            A i = new A();
            Double d = new Double(12d);
            i.a(d);
        }
    }*/
//    class Copier<T extends A> {
//        public T copy(T param) throws CloneNotSupportedException {
//            return  (T) param.clone();       /**/                     //1

/*  }
    }

class A  implements Cloneable{
    public int i=10;
    @Override
    public A clone() throws CloneNotSupportedException {
        return (A) super.clone();                             //2
    }
}

class B extends A{
    public int i=20;
}

 class MyClass {
    public static void main(String[] args) throws CloneNotSupportedException {
        B b = new B();
        Copier<B> copier = new Copier<>();                //3
        A a = copier.copy(b);                                //4
        System.out.println(a.i);
    }
}*/
/*
class  A{
    public static void main(String[] args) {
        File file = new File("C:\\Users\\User\\Desktop\\uploads\\");
        long freeSpace = file.getFreeSpace();
        long total=file.getTotalSpace();
       long us= file.getUsableSpace();
        System.out.println(freeSpace);
        System.out.println(total);
        System.out.println(us);
    }
}*/
