package classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    public static ArrayList<Ball> balls = new ArrayList<>();
    public static ArrayList<Pocket> pockets = new ArrayList<Pocket>();
    private int width, height;

    public BallCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void add(Ball b){
        BallCanvas.balls.add(b);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.decode("#006e25"));
        g2.fill(new Rectangle(0,0,BounceFrame.WIDTH,BounceFrame.HEIGHT));

        var pockets = Pocket.generateList(this.width, this.height);
        BallCanvas.pockets = pockets;
        Pocket.drawList(g2, pockets);

        for(int i=0; i<balls.size();i++){
            Ball b = balls.get(i);
            if(b != null) {
                b.draw(g2);
            }

        }
    }
}
