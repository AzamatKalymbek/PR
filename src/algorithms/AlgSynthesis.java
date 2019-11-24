package algorithms;

import util.functions.FillFunctions;
import util.models.Data;

import java.util.HashMap;
import java.util.List;

public class AlgSynthesis {

    private static final List<Data> dataList = FillFunctions.fillDataListFromTxtFile();

    public static void main(String[] args) {

//        algorithm1();
//        System.out.println("==========================================================");
//        algorithm2();
//        System.out.println("==========================================================");
//        algorithm3();
//        System.out.println("==========================================================");
//        algorithm4();
//        System.out.println("==========================================================");
//        algorithm5();
//        System.out.println("==========================================================");
//        algorithm6();
//        System.out.println("==========================================================");
//        algorithm7();
//        System.out.println("==========================================================");
//        algorithm8();
//        algorithm9();
//        algorithm10();

        assert dataList != null;
        Integer[][] centerMatrix = new Integer[10][dataList.size()];
        System.out.println(centerMatrix.length);
        System.out.println(centerMatrix[0].length);
        for(int alg = 0; alg < 10; alg++){
            for(int obj = 0; obj < dataList.size(); obj++){

            }
        }
    }

    private List<HashMap<String, Object>> selectAlgorithm(int alg){
        switch (alg){
            case 1: return algorithm1();
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
            case 6: break;
            case 7: break;
            case 8: break;
            case 9: break;
            case 10: break;
        }
    }

    private static List<HashMap<String, Object>> algorithm1() {
        System.out.println("A-1 => K-meas 5 классов (центры рандомно)");
        List<HashMap<String, Object>> clusters = KMean.kMeanAlgorithm(5);
        // calculate quality functional
//        double QF = ReduceCentroid.getQualityFunctional(clusters);
//        System.out.println("QUALITY FUNCTIONAL 1: " + QF);
        KMean.clear();
        return clusters;
    }

    private static List<HashMap<String, Object>> algorithm2() {
        System.out.println("A-2 => K-meas 6 классов (центры рандомно)");
        List<HashMap<String, Object>> clusters =  KMean.kMeanAlgorithm(6);
        // calculate quality functional
        double QF = ReduceCentroid.getQualityFunctional(clusters);
        System.out.println("QUALITY FUNCTIONAL 2: " + QF);
        KMean.clear();
    }

    private static List<HashMap<String, Object>> algorithm3() {
        System.out.println("A-3 => max-min + ближайший сосед");
        List<Data> zeroList = Maxmin.startMaxminAlgorithm(false);
        List<HashMap<String, Object>> clusters = KMean.startKMeanAlgorithm(zeroList, true);
        double QF = ReduceCentroid.getQualityFunctional(clusters);
        System.out.println("QUALITY FUNCTIONAL 3: " + QF);
        KMean.clear();
    }

    private static List<HashMap<String, Object>> algorithm4() {
        System.out.println("A-4 => max-min (через матрицы расстояние) + ближайший сосед");
        List<Data> zeroList = Maxmin.startMaxminAlgorithm(true);
        List<HashMap<String, Object>> clusters = KMean.startKMeanAlgorithm(zeroList, true);
        double QF = ReduceCentroid.getQualityFunctional(clusters);
        System.out.println("QUALITY FUNCTIONAL 4: " + QF);
        KMean.clear();
    }

    private static List<HashMap<String, Object>> algorithm5() {
        System.out.println("A-5 => алгоритм сокращение до 5 классов (начальное кол-во классов 7)");
        List<HashMap<String, Object>> clusters = ReduceCentroid.start(false, 7, 5);
        double QF = ReduceCentroid.getQualityFunctional(clusters);
        System.out.println("QUALITY FUNCTIONAL 5: " + QF);
        KMean.clear();
    }

    private static List<HashMap<String, Object>> algorithm6() {
        System.out.println("A-6 => алгоритм сокращение до 6 классов (начальное кол-во классов 7)");
        List<HashMap<String, Object>> clusters = ReduceCentroid.start(false, 7, 6);
        double QF = ReduceCentroid.getQualityFunctional(clusters);
        System.out.println("QUALITY FUNCTIONAL 6: " + QF);
        KMean.clear();
    }

    private static List<HashMap<String, Object>> algorithm7() {
        System.out.println("A-7 => max-min + K-means");
        List<Data> zeroList = Maxmin.startMaxminAlgorithm(true);
        List<HashMap<String, Object>> clusters = KMean.startKMeanAlgorithm(zeroList, false);
//        double QF = ReduceCentroid.getQualityFunctional(clusters);
//        System.out.println("QUALITY FUNCTIONAL 7: " + QF);
        KMean.clear();
        return ;
    }

    private static List<HashMap<String, Object>> algorithm8() {
        System.out.println("A-8 => max-min + ABO");
        List<Data> zeroList = Maxmin.startMaxminAlgorithm(false);
        List<HashMap<String, Object>> clusters = AVO.start(zeroList, 0);
//        double QF = ReduceCentroid.getQualityFunctional(clusters);
//        System.out.println("QUALITY FUNCTIONAL 8: " + QF);
        return clusters;
    }

    private static List<HashMap<String, Object>> algorithm9() {
        System.out.println("A-9 => max-min(matrix distance) + ABO");
        List<Data> zeroList = Maxmin.startMaxminAlgorithm(true);
        List<HashMap<String, Object>> clusters = AVO.start(zeroList, 0);
//        double QF = ReduceCentroid.getQualityFunctional(clusters);
//        System.out.println("QUALITY FUNCTIONAL 9: " + QF);
        return clusters;
    }

    private static List<HashMap<String, Object>> algorithm10() {
        System.out.println("A-10 => руч центр + ABO");
        List<HashMap<String, Object>> clusters = AVO.start(null, 3);
//        double QF = ReduceCentroid.getQualityFunctional(clusters);
//        System.out.println("QUALITY FUNCTIONAL 10: " + QF);
        return clusters;
    }
}
