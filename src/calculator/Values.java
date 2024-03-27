package calculator;

public class Values {

    public static final double rSun = 432450, rEarth = 3963.1906, rMoon = 1079.6,
            dEarthToSun = 92955807.2481707 + rEarth + rSun, dEarthToMoon = 238855 + rMoon + rEarth,
            dMoonToSun = dEarthToSun - dEarthToMoon,
            maxEarthToSun = 94510000 + rEarth + rSun, minEarthToSun = 91400000,
            maxEarthToMoon = 252898.075 + rEarth + rMoon, minEarthToMoon = 221829.516,
            maxMoonToSun = maxEarthToSun - minEarthToMoon, minMoonToSun = minEarthToSun - maxEarthToMoon;

    public static void init() {

    }

    public static double calculateEclipseSize(double rSun, double dMoonToSun, double rMoon, double dEarthToMoon) {
        return (rMoon - (dEarthToMoon*Math.tan(Math.atan2(rSun-rMoon, dMoonToSun))))*2d;
    };

}
