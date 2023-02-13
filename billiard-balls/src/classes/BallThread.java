package classes;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
        try{
            for(int i=1; i<10000; i++){
                b.move();

                if (b.isInPocket()) {
                    Thread.currentThread().interrupt();
                    b.removeFromCanvas();
                    BounceFrame.canvas.repaint();
                    BounceFrame.incrementBallsInPockets();
                }

                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);
                if(Thread.interrupted()) {
                    return;
                }
            }
        } catch(InterruptedException ex){

        }
    }
}
