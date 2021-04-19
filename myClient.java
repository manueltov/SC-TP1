import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class myClient {

    public static void main(String[] args) {
        System.out.println("cliente: main");
        myClient client = new myClient();
        client.startClient();
    }

    public void startClient() {
        Socket cSocket = null;

        try {
            cSocket = new Socket("localhost", 23456);

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            ObjectInputStream inStream = new ObjectInputStream(cSocket.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(cSocket.getOutputStream());

            System.out.println("Introduza o username e a password:");

            System.out.println("Username: ");
            Scanner scanner = new Scanner(System.in);
            String localUserID = scanner.nextLine();
            System.out.println("Introduza a sua password:");
            String password = scanner.nextLine();

            outStream.writeObject(localUserID);
            outStream.writeObject(password);

            System.out.println();

            //se ele for autenticado, é o servidor que avisa disso
            boolean ehAutenticado = inStream.readObject().toString().equals("User autenticado");

            if (ehAutenticado){
                FileReader fileReader = new FileReader("test.txt");
                String content = getContentFromFile(fileReader);

                int fileSize = content.length();

                outStream.writeObject(fileSize); //
                outStream.writeObject(content);
            }
        } catch (IOException | ClassNotFoundException e) {
        System.err.println(e.getMessage());
        System.exit(-1);
    }
    }

    private String getContentFromFile(FileReader fileReader) {
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            BufferedReader br= new BufferedReader(fileReader);
            while((s=br.readLine())!=null)
            {
                stringBuilder.append(s + "\n");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();

        }
        return stringBuilder.toString();
    }
}
