import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    static final BigDecimal metersInKilometer = new BigDecimal(1000);
    static final BigDecimal secondsInHour = new BigDecimal(3600);
    static BigDecimal[] greenLightsMaxSpeed;
    static int speedInKilometers;
    static BigDecimal speedInMeters;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        speedInKilometers = in.nextInt();
        calcSpeedInMeters();
        
        int lightCount = in.nextInt();
        greenLightsMaxSpeed = new BigDecimal[lightCount];
        for (int i = 0; i < lightCount; i++) {
            int distance = in.nextInt();
            int duration = in.nextInt();
            greenLightsMaxSpeed[i] = new BigDecimal(distance).divide(new BigDecimal(duration), 20, RoundingMode.DOWN);
        }

        int i = 0;
        while(i < lightCount){
            if(isRed(i)) {
                adjustSpeed(i);
                i = 0;
            } else {
                i++;
            }
        }

        System.out.println(speedInKilometers);
    }
    
    static boolean isRed(int lightNumber) {
        return greenLightsMaxSpeed[lightNumber].divide(speedInMeters, 20, RoundingMode.DOWN).remainder(new BigDecimal(2)).compareTo(BigDecimal.ONE) >= 0;
    }
    
    static void adjustSpeed(int lightNumber) {
        while(isRed(lightNumber)) {
            speedInKilometers--;
            calcSpeedInMeters();
        }
    }
    
    static void calcSpeedInMeters() {
        speedInMeters = new BigDecimal(speedInKilometers).multiply(metersInKilometer).divide(secondsInHour, 20, RoundingMode.DOWN);
    }
}
