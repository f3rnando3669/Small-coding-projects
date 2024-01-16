import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class server {
    public static void main (String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        ServerSocket ss = null;
        ss = new ServerSocket(1234);

        while (true) {
            try {
            socket = ss.accept();
            isr = new InputStreamReader(socket.getInputStream());
            osw = new OutputStreamWriter(socket.getOutputStream());
            br = new BufferedReader(isr);
            bw = new BufferedWriter(osw);

            while(true) {
                String msgFromClient = br.readLine();
                
                System.out.println("Client: "+ msgFromClient);

                bw.write("MSG Recived.");
                bw.newLine();
                bw.flush();

                if(msgFromClient.equalsIgnoreCase("BYE"))
                break;
            }
        
                socket.close();
                isr.close();
                osw.close();
                br.close();
                bw.close();
        }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
