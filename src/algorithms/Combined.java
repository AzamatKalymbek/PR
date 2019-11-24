package algorithms;

import util.models.Data;
import java.util.ArrayList;
import java.util.List;

public class Combined {
    private static List<Data> zeroList = new ArrayList<>();

    public static void main(String[] args) {

        zeroList = Maxmin.startMaxminAlgorithm(true);

        KMean.startKMeanAlgorithm(zeroList, false);
    }
}
