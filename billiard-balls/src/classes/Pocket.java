package classes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Pocket {
    static Color color = Color.darkGray;
    private final int x;
    private final int y;
    private static final int width = 30;
    private static final int height = 30;

    public Pocket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean contains(int x, int y) {
        return x >= this.x && x < this.x + this.width && y >= this.y && y < this.y + this.height;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Pocket.color);
        g2.fill(new Ellipse2D.Double(this.x, this.y, this.width, this.height));
    }

    public static void drawList(Graphics2D g2, ArrayList<Pocket> pockets) {
        for (Pocket pocket : pockets) {
            pocket.draw(g2);
        }
    }

    public static ArrayList<Pocket> generateList(int width, int height) {
        ArrayList<Pocket> pockets = new ArrayList<>();

        int halfPocketWidth = Pocket.width / 2,
            halfPocketHeight = Pocket.height / 2;

        pockets.add(new Pocket(0 - halfPocketWidth, 0 - halfPocketHeight));
        pockets.add(new Pocket(width/2 - halfPocketWidth, 0 - halfPocketHeight));
        pockets.add(new Pocket(width - halfPocketWidth, 0 - halfPocketHeight));

        pockets.add(new Pocket(0 - halfPocketWidth, height - BounceFrame.MENU_HEIGHT - halfPocketHeight - Pocket.height));
        pockets.add(new Pocket(width/2 - halfPocketWidth, height - BounceFrame.MENU_HEIGHT - halfPocketHeight - Pocket.height));
        pockets.add(new Pocket(width - halfPocketWidth, height - BounceFrame.MENU_HEIGHT - halfPocketHeight - Pocket.height));

        return pockets;
    }


}