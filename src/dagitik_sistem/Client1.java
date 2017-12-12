/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dagitik_sistem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;

/**
*
* @author haye
*/
public class Client1 {
static Socket s;
public static void main(String[] args) throws IOException, Exception {

s = new Socket("localhost",15201);

PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
String komut;
ExecuteShellComand ext = new ExecuteShellComand();

while(true) {
komut = bf.readLine();
System.out.println(komut);





if(komut.equals("bitir")) {
break;
}


//islemi yap
String sonuc = ext.executeCommand(komut);

//sonucu donder sonlan
pw.println(sonuc + "PC1");
System.out.println(sonuc);
}




}


}