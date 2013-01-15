package org.linesofcode.timeline.objects;

import com.sun.istack.internal.Nullable;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.Date;

/**
 * @author Dominik Eckelmann
 */
public class TimeLineItem {

    public static final int Y_DISTANCE_TO_TIMELINE = 10;
    public static final int BOX_PADDING = 10;
    public static final int TEXT_SIZE = 15;

    private final String headline;
    private final String text;
    private final boolean connected;
    private final Date date;
    private final int weighs;

    public TimeLineItem(@Nullable final String headline, @Nullable final String text, final boolean connected, final Date date, final int weights) {
        this.headline = headline;
        this.text = text;
        this.connected = connected;
        this.date = date;
        this.weighs = weights;
    }

    public float draw(PApplet context, final PVector connectionPoint) {

        context.textSize(TEXT_SIZE);

        PVector boxBottomLeft = new PVector(connectionPoint.x, connectionPoint.y - Y_DISTANCE_TO_TIMELINE);
        PVector boxTopRight = new PVector(boxBottomLeft.x + 2 * BOX_PADDING + context.textWidth(headline), boxBottomLeft.y -(2*BOX_PADDING + TEXT_SIZE));

        context.line(connectionPoint.x, connectionPoint.y, boxBottomLeft.x, boxBottomLeft.y);
        context.rect(boxBottomLeft.x, boxBottomLeft.y,boxTopRight.x - boxBottomLeft.x, boxTopRight.y - boxBottomLeft.y);
        context.fill(Color.BLACK.getRGB());
        context.text(headline, boxBottomLeft.x+BOX_PADDING, boxBottomLeft.y-BOX_PADDING);
        context.fill(0,0);

        return boxTopRight.x - boxBottomLeft.x;
    }

    public void update(final float delta) {

    }
}