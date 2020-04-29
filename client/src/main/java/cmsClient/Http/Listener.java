package cmsClient.Http;

import java.io.DataInputStream;
import java.util.concurrent.Semaphore;

public class Listener extends Thread{

    private DataInputStream  input;

    Listener (DataInputStream  input){
        this.input=input;
    }

    public void run(){
        System.out.println("client listening");
    }

}
