package classes;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball){
        b = ball;
        setPriority(5);
    }

    public BallThread(Ball ball, int priority){
        b = ball;

        int fixedPriority = priority >= 1 && priority <= 10 ? priority
            : priority < 1 ? 1 : 10;

        setPriority(fixedPriority);
        ball.setPriority(fixedPriority);
    }

    @Override
    public void run(){
        try{
            for(int i=1; i<10000; i++){
                b.move();

                if (b.isInPocket()) {
                    b.removeFromCanvas();
                    BounceFrame.incrementBallsInPockets();
                    Thread.currentThread().interrupt();
                }

                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch(InterruptedException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
