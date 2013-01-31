package org.linesofcode.timeline.objects;

import org.linesofcode.timeline.Main;

import java.awt.*;

/**
 * @author Dominik Eckelmann
 */
public class TimeLineItem {

    public static final int RADIUS = 30;

    private int x;
    private int y;
    private float red;
    private float green;
    private float blue;

    private Main context;

    public TimeLineItem(int x, Main context) {
        this.x = x;
        this.context = context;
        red = context.random(255);
        green = context.random(255);
        blue = context.random(255);
    }

    public void drawCircle() {

        context.stroke(Color.BLACK.getRGB());
        context.fill(red, green, blue);

        final int x1 = x;
        final int y1 = y;
        context.ellipse(x1, y1, RADIUS * 2, RADIUS * 2);
    }

    public void drawLine() {
        context.stroke(Color.GRAY.getRGB());
        context.strokeWeight(2);
        context.line(x, y, x, context.height - Main.BOTTOM_DISTANCE);
    }

    public void update(int index) {
        y = context.height /2;
        if (index == 0) {
            return;
        }

        if (!collides()) {
            return;
        }

        // try under
        for (int newY = context.height / 2; newY < context.height - Main.BOTTOM_DISTANCE - 30; newY++) {
            y = newY;
            if (!collides()) {
                return;
            }
        }

        // try above
        for (int newY = context.height / 2; newY > RADIUS; newY--) {
            y = newY;
            if (!collides()) {
                return;
            }
        }

    }

    private boolean collides() {
        for (TimeLineItem item : context.getItems()) {
            if (item.equals(this)) {
                continue;
            }
            if (TimeLineItem.checkCollision(this, item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkCollision(TimeLineItem left, TimeLineItem right) {
        return (Point.distance(left.x, left.y, right.x, right.y) < RADIUS*2 + Main.ITEM_MARGIN);
    }
}
