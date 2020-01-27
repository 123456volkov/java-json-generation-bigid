import utils.JSONutil;

public class Main {
    public static void main(String[] args) {

        JSONutil jsoNutil = new JSONutil(5);
        for (int i = 0; i <= 1000000; i++) {
            System.out.println(i);
            System.out.println(jsoNutil.createNewJSON());
        }
    }
}
