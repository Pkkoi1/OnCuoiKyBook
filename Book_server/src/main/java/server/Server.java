package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import dao.Impl.bookImpl;
import dao.Impl.reviewImpl;
import dao.bookDao;
import dao.reviewDao;
import entity.Book;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(2951)) {
            System.out.println("Server is running...");
            while (true) {
                Socket socket = ss.accept();
                System.out.println("New client connected");
                System.out.println("Client IP: " + socket.getInetAddress().getHostAddress());
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;


    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            bookDao bookDao = new bookImpl();
            reviewDao reviewDao = new reviewImpl();
            int choice = 0;
            while (true) {
                choice = dis.readInt();
                switch (choice) {
                    case 1:
                        String author = dis.readUTF();
                        int rating = dis.readInt();
                        List<Book> books = bookDao.listRatedBooks(author, rating);
                        books.forEach(System.out::println);
                        oos.writeObject(books);
                        oos.flush();
                        break;
                    case 2:
                        Map<String, Long> result = bookDao.countBooksByAuthor();
                        oos.writeObject(result);
                        oos.flush();
                        break;
                    case 3:
                        String ibsn = dis.readUTF();
                        String readerID = dis.readUTF();
                        int rating2 = dis.readInt();
                        String review = dis.readUTF();
                        boolean result2 = reviewDao.updateReviews(ibsn, readerID, rating2, review);
                        oos.writeBoolean(result2);
                        oos.flush();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
