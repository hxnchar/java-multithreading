package classes;

public class CharacterThread extends Thread {
    private char character;
    private int counter = 100;

    public CharacterThread(char character) {
        this.character = character;
    }

    @Override
    public void run(){
        try{
            for(int i = 0; i < counter; i++){
                System.out.print(character);
                Thread.sleep(5);
            }
        } catch(InterruptedException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
