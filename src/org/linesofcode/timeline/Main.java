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

        final String[] words = "Lorem ipsum dolor sit amet consetetur sadipscing elitr sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat sed diam voluptua At vero eos et accusam et justo duo dolores et ea rebum Stet clita kasd gubergren no sea takimata sanctus est Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet consetetur sadipscing elitr sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat sed diam voluptua At vero eos et accusam et justo duo dolores et ea rebum Stet clita kasd gubergren no sea takimata sanctus est Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet consetetur sadipscing elitr sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat sed diam voluptua At vero eos et accusam et justo duo dolores et ea rebum Stet clita kasd gubergren no sea takimata sanctus est Lorem ipsum dolor sit amet".split(" ");
        final String[] texts = {
                "Eine wunderbare Heiterkeit hat meine ganze Seele eingenommen, gleich den süßen Frühlingsmorgen, die ich mit ganzem Herzen genieße.",
                "Ich bin allein und freue mich meines Lebens in dieser Gegend, die für solche Seelen geschaffen ist wie die meine.",
                "Ich bin so glücklich, mein Bester, so ganz in dem Gefühle von ruhigem Dasein versunken, daß meine Kunst darunter leidet.",
                "Ich könnte jetzt nicht zeichnen, nicht einen Strich, und bin nie ein größerer Maler gewesen als in diesen Augenblicken.",
                "Jemand musste Josef K.",
                "verleumdet haben, denn ohne dass er etwas Böses getan hätte, wurde er eines Morgens verhaftet.",
                "»Wie ein Hund!",
                "« sagte er, es war, als sollte die Scham ihn überleben.",
                "Als Gregor Samsa eines Morgens aus unruhigen Träumen erwachte, fand er sich in seinem Bett zu einem ungeheueren Ungeziefer verwandelt.",
                "Und es war ihnen wie eine Bestätigung ihrer neuen Träume und guten Absichten, als am Ziele ihrer Fahrt die Tochter als erste sich erhob und ihren jungen Körper dehnte.",
                "»Es ist ein eigentümlicher Apparat«, sagte der Offizier zu dem Forschungsreisenden und überblickte mit einem gewissermaßen bewundernden Blick den ihm doch wohlbekannten Apparat.",
                "Sie hätten noch ins Boot springen können, aber der Reisende hob ein schweres, geknotetes Tau vom Boden, drohte ihnen damit und hielt sie dadurch von dem Sprunge ab.",
                "In den letzten Jahrzehnten ist das Interesse an Hungerkünstlern sehr zurückgegangen.",
                "Aber sie überwanden sich, umdrängten den Käfig und wollten sich gar nicht fortrühren.",
                "Jemand musste Josef K.",
                "verleumdet haben, denn ohne dass er etwas Böses getan hätte, wurde er eines Morgens verhaftet.",
                "»Wie ein Hund!",
                "« sagte er, es war, als sollte die Scham ihn überleben.",
                "Als Gregor Samsa eines Morgens aus unruhigen Träumen erwachte, fand er sich in seinem Bett zu einem ungeheueren Ungeziefer verwandelt.",
                "Und es war ihnen wie eine Bestätigung ihrer neuen Träume und guten Absichten, als am Ziele ihrer Fahrt die Tochter als erste sich erhob und ihren jungen Körper dehnte.",
                "»Es ist ein eigentümlicher Apparat«, sagte der Offizier zu dem Forschungsreisenden und überblickte mit einem gewissermaßen bewundernden Blick den ihm doch wohlbekannten Apparat.",
                "Sie hätten noch ins Boot springen können, aber der Reisende hob ein schweres, geknotetes Tau vom Boden, drohte ihnen damit und",
        };

        items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            String headline = null;
            String text = null;
            boolean connected = true;

            if (random(0,1) > 0.5) {
                int headlineStart = round(random(0, words.length-3));
                headline = String.format("%s %s %s", words[headlineStart], words[headlineStart+1], words[headlineStart+2]);
            }
            if (random(0,1) > 0.5) {
                text = texts[round(random(0, texts.length-1))];
            }
            if (random(0,1) > 0.5) {
                connected = false;
            }
            items.add(new TimeLineItem(headline, text, connected, new Date(315532800l + (31536000*i)), 1));
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
