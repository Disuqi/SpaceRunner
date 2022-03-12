package disuqi.view;

import java.time.Duration;
import java.time.Instant;

public class FPS {
    private FPS(){}

    private static Duration fpsDeltatTime = Duration.ZERO;
    private static Duration lastTime = Duration.ZERO;
    private static Instant beginTime = Instant.now();
    private static double deltaTime = fpsDeltatTime.toMillis() - lastTime.toMillis();

    public static void calcBeginTime(){
        beginTime = Instant.now();
        fpsDeltatTime = Duration.ZERO;
    }

    public static void calcDeltaTime(){
        fpsDeltatTime = Duration.between(beginTime, Instant.now());
        deltaTime = (double)fpsDeltatTime.toMillis() - lastTime.toMillis();
        lastTime = fpsDeltatTime;
    }

    public static double getDeltaTime(){
        return deltaTime/1000;
    }
}
