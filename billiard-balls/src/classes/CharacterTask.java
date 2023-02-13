package classes;

public class CharacterTask {

    public static void task1() {
        CharacterThread dashThread = new CharacterThread('-');
        CharacterThread pipeThread = new CharacterThread('|');

        dashThread.start();
        pipeThread.start();
    }

}
