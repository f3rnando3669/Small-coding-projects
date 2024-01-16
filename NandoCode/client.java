import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
public class client {
    
    public static void main (String[] args) {
        Socket socket = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            socket =  new Socket("localhost", 1234);
            isr = new InputStreamReader(socket.getInputStream());
            osw = new OutputStreamWriter(socket.getOutputStream());
            br = new BufferedReader(isr);
            bw = new BufferedWriter(osw);

            Scanner scanner = new Scanner(System.in);
                while(true) {
                    String msgToSend = scanner.nextLine();
                    bw.write(msgToSend);
                    bw.newLine();
                    bw.flush();
                    System.out.println("server: " + br.readLine());

                    if (msgToSend.equalsIgnoreCase("BYE"))
                    break;
                }
            
        } catch(IOException e){
                e.printStackTrace();
        }
            finally {
                try{
                    if(socket != null)
                        socket.close();
                    if(isr != null)
                        isr.close();
                    if(osw != null)
                        osw.close();
                    if(br != null)
                        br.close();
                    if(bw != null)
                        bw.close();

                } catch(IOException e){
                    e.printStackTrace();
                }
            }

        }
    }
