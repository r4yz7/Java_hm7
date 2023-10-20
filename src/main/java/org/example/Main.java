package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
        static int[]arr;
        static int sum=0;
        static double avg = 0;

        static String path;
        static int primeCount = 0;


    static class FillArray implements Runnable{
        @Override
        public void run() {
            Random random = new Random();
            for (int i=0; i<arr.length;i++){
                arr[i] = random.nextInt(20);
            }
        }
    }

    static class SumArray implements Runnable{
        @Override
        public void run() {
            sum = Arrays.stream(arr).sum();
        }
    }

    static class AvgArray implements Runnable{

        @Override
        public void run() {
            avg = Arrays.stream(arr).average().orElse(0);
        }
    }

    static class FillFile implements Runnable{

        @Override
        public void run() {
            try(FileWriter writer = new FileWriter(path)) {
                Random random = new Random();
                for (int i = 0; i<10; i++){
                    int rnd = random.nextInt(10);
                    writer.write(rnd + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    static class Prime implements Runnable{

        @Override
        public void run() {
            try(BufferedReader reader = new BufferedReader(new FileReader(path)); FileWriter writer = new FileWriter("D:\\step\\java\\Java_hm7\\prime.txt")) {
                String line;
                while ((line = reader.readLine())!=null){
                    int n = Integer.parseInt(line);
                    if(isPrime(n)){
                        writer.write(n + "\n");
                        primeCount++;
                    }
                }
            }
            catch (IOException e) {
              System.out.println(e.getMessage());
            }
        }
    }

    static class Factorial implements Runnable{

        @Override
        public void run() {
            try(BufferedReader reader = new BufferedReader(new FileReader(path)); FileWriter writer = new FileWriter("D:\\step\\java\\Java_hm7\\factorial.txt")) {
                String line;
                while ((line = reader.readLine())!=null){
                    int n = Integer.parseInt(line);
                    long f = CalcFact(n);
                    writer.write(f + "\n");
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        }
    static long CalcFact(int n){
            if (n == 0) {
                return 1;
            } else {
                return n * CalcFact(n - 1);
            }
    }
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
//        arr = new int[10];
//        Thread fill = new Thread(new FillArray());
//        Thread findSum = new Thread(new SumArray());
//        Thread findAVG = new Thread(new AvgArray());
//        fill.start();
//        try {
//            fill.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        findSum.start();
//        findAVG.start();
//        try {
//            findAVG.join();
//            findSum.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Array: " + Arrays.toString(arr));
//        System.out.println("Sum: " + sum);
//        System.out.println("AVG: " + avg);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path: ");
        path = scanner.nextLine();
        Thread fill = new Thread(new FillFile());
        Thread prime = new Thread(new Prime());
        Thread factorial = new Thread(new Factorial());
        fill.start();
        prime.start();
        factorial.start();
        try {
            fill.join();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        try {
            prime.join();
            factorial.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}