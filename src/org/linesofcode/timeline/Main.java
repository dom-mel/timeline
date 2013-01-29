package org.linesofcode.timeline;

import org.linesofcode.timeline.objects.TimeLineItem;
import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dominik Eckelmann
 */
public class Main extends PApplet {

    public  static final int H = 640 / 2;
    private static final int W = 1136 / 2;
    private static final int DEMO_ITEMS = 100;

    public static final int BOTTOM_DISTANCE = 10;
    public static final int GEN_ITEM_MAX_DISTANCE = 100;

    private List<TimeLineItem> items;

    @Override
    public void setup() {
        // IPhone 5 resolution
        size(W, H);
        smooth();

        loadItems();
    }

    private void loadItems() {
        items = new ArrayList<>(DEMO_ITEMS);

        int x = TimeLineItem.RADIUS+10;
        for (int i = 0; i < DEMO_ITEMS; i++) {
            items.add(new TimeLineItem(x, this));
            x += random(0, GEN_ITEM_MAX_DISTANCE);// + TimeLineItem.RADIUS/2;
        }
    }

    @Override
    public void draw() {
        update(1 / frameRate);
        background(Color.WHITE.getRGB());

        for (final TimeLineItem item : items) {
            item.drawLine();
        }

        for (final TimeLineItem item : items) {
            item.drawCircle();
        }

        // time line
        stroke(Color.BLACK.getRGB());
        line(0,height - BOTTOM_DISTANCE,width, height - BOTTOM_DISTANCE);
    }

    public void update(final float delta) {

        for (int i = 0; i < items.size(); i++) {
            items.get(i).update(i);
        }
    }

    public List<TimeLineItem> getItems() {
        return items;
    }
}
