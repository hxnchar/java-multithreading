package classes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

class Ball {
    public Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;


    public Ball(Component c){
        this.canvas = c;

        if(Math.random()<0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public void draw (Graphics2D g2){
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }

    public void removeFromCanvas() {
        BallCanvas.balls.remove(this);
    }

    public boolean isInPocket() {
        ArrayList<Pocket> pockets = BallCanvas.pockets;
        int ballCenterX = x + XSIZE / 2;
        int ballCenterY = y + YSIZE / 2;

        for (Pocket pocket : pockets) {
            if (pocket.contains(ballCenterX, ballCenterY)) {
                return true;
            }
        }

        return false;
    }

    public void move(){
        x += dx;
        y += dy;

        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }

        this.canvas.repaint();
    }
}