package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BounceFrame extends JFrame {

    public static BallCanvas canvas;
    public static int NUMBER_OF_RED_BALLS = 1, NUMBER_OF_BLUE_BALLS = 20;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    public static final int MENU_HEIGHT = 100;
    private static int ballsInPockets = 0;
    private static JLabel ballsInPocketLabel;
    static int totalBalls() {
        return BounceFrame.NUMBER_OF_RED_BALLS + BounceFrame.NUMBER_OF_BLUE_BALLS;
    }
    public static void incrementBallsInPockets() {
        ballsInPocketLabel.setText("Balls in pockets: " + ++ballsInPockets);
    }

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");

        this.canvas = new BallCanvas(WIDTH, HEIGHT);
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel infoPanel = new JPanel();

        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(new Dimension(WIDTH, MENU_HEIGHT));
        infoPanel.setBackground(Color.lightGray);
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonExperiment = new JButton("Add " + BounceFrame.totalBalls() + " balls");
        JButton buttonStart = new JButton("Add 1 ball");
        JButton buttonStop = new JButton("Stop");
        ballsInPocketLabel = new JLabel("Balls in pockets: " + BounceFrame.ballsInPockets);

        buttonPanel.add(buttonExperiment);
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);

        infoPanel.add(ballsInPocketLabel);

        panel.add(infoPanel);
        panel.add(buttonPanel);

        content.add(panel, BorderLayout.PAGE_END);
        buttonExperiment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<BallThread> threads = new ArrayList<BallThread>();

                int startX, startY;

                if(Math.random()<0.5){
                    startX = new Random().nextInt(canvas.getWidth());
                    startY = 0;
                }else{
                    startX = 0;
                    startY = new Random().nextInt(canvas.getHeight());
                }

                for(int i = 0; i < NUMBER_OF_BLUE_BALLS; i++) {
                    Ball ball = new Ball(canvas, startX, startY);
                    threads.add(new BallThread(ball, 1));
                    canvas.add(ball);
                }

                for(int i = 0; i < NUMBER_OF_RED_BALLS; i++) {
                    Ball ball = new Ball(canvas, startX, startY);
                    threads.add(new BallThread(ball, 10));
                    canvas.add(ball);
                }

                for (Thread thread: threads) {
                    System.out.println(thread.toString());
                    thread.start();
                    System.out.println("Thread name = " + thread.getName());
                }

            }
        });

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }

        });
    }
}
