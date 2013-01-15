package org.linesofcode.timeline;

import org.linesofcode.timeline.objects.TimeLineItem;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dominik Eckelmann
 */
public class Main extends PApplet {

    public static final int H = 640;
    public static final int W = 1136;

    public static final int TIMELINE_BOTTOM_SPACING = 10;
    public static final int TIMELINE_TO_BOX_DISTANCE = 10;
    public static final int BOX_TO_BOX_DISTANCE = 10;
    public static final int TIMELINE_FIRST_ITEM_DISTANCE_X = 10;

    private List<TimeLineItem> items;

    @Override
    public void setup() {

        // IPhone 5 resolution
        size(W, H);

        loadItems();

        smooth();
    }

    private void loadItems() {
        items = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            items.add(new TimeLineItem("Nur eine Überschrift", null, true, new Date(315532800l + (31536000*i)), 1));
        }
    }

    @Override
    public void draw() {
        update(1 / frameRate);

        background(Color.WHITE.getRGB());

        float lineX = TIMELINE_FIRST_ITEM_DISTANCE_X;
        float lineY = H - TIMELINE_BOTTOM_SPACING;

        for (final TimeLineItem item : items) {
            final PVector connectionPoint = new PVector(lineX, lineY);

            lineX += item.draw(this, connectionPoint);
            lineX += BOX_TO_BOX_DISTANCE;
        }

        stroke(Color.BLACK.getRGB());
        line(0, lineY, W, lineY);

    }

    public void update(final float delta) {
        for (final TimeLineItem item : items) {
            item.update(delta);
        }
    }
}
