import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Random random = new Random(0);
        List list = new List();
        for (int i = 0; i < 5; i++)
            list.push_back(random.nextInt(100));
            list.print();
        System.out.println();
           /* list.reverse_print();
            list.pop_back();
            list.print();
            list.pop_front();
            list.print();
            List listCopy = new List(list);
            listCopy.print();*/
        list.insert(9999,3);
        list.print();
        list.erase(3);
        list.pop_front();
        list.print();
        list.clear();
        list.print();


    }
}