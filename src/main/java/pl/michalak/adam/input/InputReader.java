package pl.michalak.adam.input;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

class InputReader {

    private final Scanner keyboard;

    Supplier<String> dostawca = new Supplier<>() {
        @Override
        public String get() {
            return new Scanner(System.in).nextLine();
        }
    };

    Consumer<String> konsument = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println(s);
        }
    };

    InputReader(InputStream inputStream){
        this.keyboard = new Scanner(inputStream);
    }
    int getInt(){ return keyboard.nextInt(); }

    String getString(){ return keyboard.next(); }


}

@FunctionalInterface
interface Wejście extends Supplier<String> {
    @Override
    default String get() {
        return new Scanner(System.in).nextLine();
    }

    String wczytaj();

}