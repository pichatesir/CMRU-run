package cmru.siriratanapaisalkul.pichate.cmrurun;

/**
 * Created by PICHATE on 28/6/2559.
 */
public class MyData {

    //Explicit
    private int[] avataInts = new int[]{R.drawable.bird48,
            R.drawable.doremon48, R.drawable.kon48,
            R.drawable.nobita48, R.drawable.rat48};

    private double[] latStationDoubles = new double[]{18.807587,18.807973, 18.805850,18.806703 };
    private double[] lngStationDoubles = new double[]{98.985204, 98.987146 ,98.987564, 98.986223};

        private int[] iconStationInts = new int[]{R.drawable.build1,R.drawable.build2,
        R.drawable.build3, R.drawable.build4};

    public int[] getIconStationInts() {
        return iconStationInts;
    }

    public int[] getAvataInts() {
        return avataInts;
    }

    public double[] getLatStationDoubles() {
        return latStationDoubles;
    }

    public double[] getLngStationDoubles() {
        return lngStationDoubles;
    }
}   // Main Class
