package Client;

import entity.Book;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 2951);
            Scanner scanner = new Scanner(System.in);
        ){
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            System.out.println("Connected to server");
            int choice =0;
            while (true)
            {
                System.out.println("1. Danh sách các cuốn sách được viết bởi tác giả nào đó khi biết tên tác giả và\n" +
                        "có điểm đánh giá từ mấy sao trở lên\n");
                System.out.println("2. Số cuốn sách được dịch sang ngôn ngữ khác của từng tác giả, kết quả sắp\n" +
                        "xếp theo tên tác giả\n");
                System.out.println("3. Cập nhật thêm một lượt đánh giá cho một cuốn sách\n" +
                        "4. Exit");
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                dos.writeInt(choice);
                dos.flush();
                switch (choice)
                {
                    case 1:
                        scanner.nextLine();
                        System.out.println("Nhập tên tác giả: ");
                        String author = scanner.nextLine();
                        dos.writeUTF(author);

                        System.out.println("Nhập điểm đánh giá: ");
                        int rating = scanner.nextInt();
                        dos.writeInt(rating);
                        scanner.nextLine();
                        dos.flush();

                        List<Book> books = (List<Book>) ois.readObject();
                        books.forEach(System.out::println);
                        break;
                    case 2:
                        Map<String, Long> result = (Map<String, Long>) ois.readObject();
                        result.forEach((k, v) -> System.out.println(k + ": " + v));
                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.println("Nhập mã sách: ");
                        String bookID = scanner.nextLine();
                        dos.writeUTF(bookID);

                        System.out.println("Nhập mã người đánh giá: ");
                        String reader = scanner.nextLine();
                        dos.writeUTF(reader);

                        System.out.println("Nhập điểm đánh giá: ");
                        int point = scanner.nextInt();
                        dos.writeInt(point);
                        scanner.nextLine();

                        System.out.println("Nhập đánh giá:");
                        String review = scanner.nextLine();
                        dos.writeUTF(review);

                        dos.flush();

                        boolean result2 = ois.readBoolean();
                        if (result2)
                        {
                            System.out.println("Cập nhật thành công");
                        }
                        else
                        {
                            System.out.println("Cập nhật thất bại");
                        }
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
            System.out.println("Client exception " + e.getMessage());
        }

    }
}
