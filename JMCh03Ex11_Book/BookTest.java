public class BookTest {
    public static void main(String[] args) {
        Book testBook = new Book(3);
        Book testBook2 = new Book(20);


        System.out.println(testBook.getNumPages());
        System.out.println(testBook.getCurrentPage());
        testBook.nextPage();
        System.out.println(testBook.getCurrentPage());
        testBook.nextPage();
        System.out.println(testBook.getCurrentPage());
        testBook.nextPage();
        System.out.println(testBook.getCurrentPage());

    }
}
