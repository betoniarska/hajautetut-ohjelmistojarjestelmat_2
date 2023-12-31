package fi.utu.tech.assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Remote extends Thread {

    private Hub hub;

    private enum Action {
        TURNOFF, TURNON, TOGGLE, TURNOFFALL, TURNONALL, REMOVE, ADD
    };

    private Random rnd = new Random();

    public Remote(Hub h) {
        super();
        this.hub = h;
    }

    @Override
    public void run() {
        while (true) {
            int next = rnd.nextInt(Action.values().length);
            Action nextAction = Action.values()[next];
            List<Integer> lightIds = new ArrayList<>(hub.getLightIds());
            int id = 0;
            if (lightIds.size()>0) {
                id = lightIds.get(rnd.nextInt(lightIds.size()));
            } else {
                nextAction = Action.ADD;
            }
            switch (nextAction) {
                case TURNOFF:
                    hub.turnOffLight(id);
                    break;
                case TOGGLE:
                    hub.toggleLight(id);
                    break;
                case TURNOFFALL:
                    hub.turnOffAllLights();
                    break;
                case TURNON:
                    hub.turnOnLight(id);
                    break;
                case TURNONALL:
                    hub.turnOnAllLights();
                    break;
                case REMOVE:
                    hub.removeLight(id);
                    break;
                case ADD:
                    hub.addLight();
                    break;
                default:
                    break;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
