import java.lang.annotation.ElementType;

public class List {
    private class Element {
        private int data;
        private Element next;
        private Element prev;
        public static int count = 0;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Element getPrev() {
            return prev;
        }

        public void setPrev(Element prev) {
            this.prev = prev;
        }

        public Element(int data) {
            this.data = data;
            count++;
            System.out.println("ListConstructor:\t" + Integer.toHexString(this.hashCode()));
        }

        public Element(int data, Element next) {
            this.data = data;
            this.next = next;
            count++;
            System.out.println("ListConstructor2:\t" + Integer.toHexString(this.hashCode()));
        }

        public Element(int data, Element next, Element prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
            count++;
            System.out.println("ListConstructor3:\t" + Integer.toHexString(this.hashCode()));
        }
        @Override
        protected void finalize() {
           // super.finalize();
            count--;
            System.out.println("EDestructor:\t" + Integer.toHexString(this.hashCode()));
        }
    }

    private Element head, tail;
    private int size;

    public int getSize() {
        return size;
    }

    public List() {
        head = null;
        tail = null;
        size = 0;
    }
                                    // Методы

    public void push_front(int data) {
        if (head == null && tail == null) {
            head = tail = new Element(data);
        } else {
           /* Element New = new Element(data);
            New.setNext(head);
            head.setPrev(New);
            head = New;*/
            head =head.prev = new Element(data,head);
            size++;
        }
    }
    public void push_back (int data) {
        if (head == null && tail == null) {
            head = tail = new Element(data);
        } else {
            /*Element New = new Element(data);
            New.setPrev(tail);
            tail.setNext(New);
            tail = New;*/
            tail = tail.next = new Element(data,null,tail);
        }
        size++;
    }
    public void pop_back() {
        if (head == null && tail == null)
            return;
        else if (head == tail) {
            tail.finalize();
            head = tail = null;
        } else  {
            tail = tail.getPrev();
            tail.next.finalize();
            tail.setNext(null);
        }
        size--;
    }
    public void pop_front() {
        if (head == null && tail == null)
            return;
         else if (head == tail){
        head.finalize();
        head = null;
        tail = null;
        }
        else  {
            head = head.getNext();
            head.prev.finalize();
            head.setPrev(null);
        }
        size--;
    }
    public List (List other){
        for (Element temp = other.head; temp != null; temp = temp.getNext())
            this.push_back(temp.getData());

    }
    public void clear(){
        while (head != null)
            pop_front();
        System.out.println("ListClear:\t" + Integer.toHexString(this.hashCode()));

    }
    // Вставка
    public void insert (int data, int index){
        if (index == 0) {
            push_front(data);
            return;
        } if (index > size) {
            System.out.println("Превышен размер списка");
            return;
        } else {
            Element temp = head;
            for (int i = 0; i < index - 1; i++)
                temp = temp.getNext();
                Element elementNew = new Element(data,temp.getNext(),temp.getPrev());
                temp.next = elementNew;
        }
        size++;
    }
    // Удаление
    public void erase (int index) {
        if (index == 0) {
            head = head.getNext();
            return;
        }
        if (index > size) {
            System.out.println("Превышен размер списка");
            return;
        } else {
            Element temp = head;
            for (int i = 0; i < index - 1; i++)
            temp = temp.getNext();
            temp.setNext(temp.getNext().getNext());
            temp.setPrev(temp.getPrev().getPrev());
            temp.finalize();

        }
        size--;
    }
    // обратная печать
    public void reverse_print () {
        Element temp = tail;
        while (temp != null) {
            System.out.print(temp.data + "\t");
            temp = temp.prev;
      /*  for (Element temp = tail; temp != null; temp.getPrev())
            System.out.print(temp.data + "\t");
        System.out.println("\nКоличество элементов списка: " + size);*/
        }
        System.out.println("\nКоличество элементов списка: " + size);
        System.out.println("\nОбщее количество элементов списка: " + Element.count);
    }
    // печать
    public void print() {
        Element temp = head;
        while (temp != null) {
            System.out.print(temp.data + "\t");
            temp = temp.next;
           /* for (Element temp = head; temp != null; temp.getNext())
                System.out.print(temp.data + "\t");
            System.out.println("\nКоличество элементов списка: " + size);
            }*/
        }
        System.out.println("\nКоличество элементов списка: " + size);
        System.out.println("\nОбщее количество элементов списка: " + Element.count);
    }
}


