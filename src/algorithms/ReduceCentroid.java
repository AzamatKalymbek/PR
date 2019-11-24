package algorithms;

import util.functions.CommonFunctions;
import util.functions.DisplayFunctions;
import util.functions.FillFunctions;
import util.models.Data;
import java.util.*;

import static util.constance.CoreConstance.clusterCenterKey;
import static util.constance.CoreConstance.clusterObjectsKey;

// simple initial version for checking reducing centroid algorithm
// Алгоритм сокращения эталонов
// author: Kalymbek Azamat
// group: IS-193m
public class ReduceCentroid {
    // object list
    private static final List<Data> dataList = FillFunctions.fillDataListFromTxtFile();
    private static List<HashMap<String, Object>> clusters;
    // Кол-во классов
    private static Integer L_CURRENT = 6;
    private static Integer L_REQUIRED = 4;
    private static Map<Integer, Double> qualityFunctional = new HashMap<Integer, Double>();
    private static Double initialQF = 0.0;
    // Driver code
    public static void main(String []args){
        start(true, 0, 0);
        DisplayFunctions.printResult14D(clusters);
    }


    public static List<HashMap<String, Object>> start(Boolean customInput, Integer initialValue, Integer reduceValue){
        Scanner input = new Scanner(System.in);
        // fill params by default value
        if(customInput){
            clusters = KMean.startKMeanAlgorithm(null, false);
            System.out.println("Required cluster size: ");
            L_REQUIRED = input.nextInt();
            while(L_REQUIRED >= clusters.size()){
                System.out.println("Invalid cluster size. Must be greater than existing cluster size. Type again: ");
                L_REQUIRED = input.nextInt();
            }
        }else{
            clusters = KMean.kMeanAlgorithm(initialValue);
            L_REQUIRED = reduceValue;
        }

        while(L_REQUIRED < clusters.size()){
            initialQF = getQualityFunctional(clusters);
            for(int i = 0; i < clusters.size(); i++){
                List<HashMap<String, Object>> tempClusters = copy();
                tempClusters.remove(i);
                tempClusters = nearestNeighbor(tempClusters);
                qualityFunctional.put(i, Math.abs(initialQF - getQualityFunctional(tempClusters)));
                tempClusters.clear();
            }

//            System.out.println("qualityFunctional");
//            DisplayFunctions.printData(qualityFunctional);
            clusters.remove(CommonFunctions.getMinValueIndex(qualityFunctional));
            clusters = nearestNeighbor(clusters);
            qualityFunctional = new HashMap<>();
        }

        return clusters;
    }

    private static List<HashMap<String, Object>> copy() {
        List<HashMap<String, Object>> tempClusters = new ArrayList<>();
        for(HashMap<String, Object> tCluster: clusters){
            List<Data> newData = new ArrayList<>();
            newData.addAll((List<Data>) tCluster.get(clusterObjectsKey));

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(clusterCenterKey,  tCluster.get(clusterCenterKey));
            hashMap.put(clusterObjectsKey, newData);
            tempClusters.add(hashMap);
        }
        return tempClusters;
    }

    private static List<HashMap<String, Object>>
                    nearestNeighbor(List<HashMap<String, Object>> tempClusters){
        for(Data data: dataList){
            Double[] distance = new Double[tempClusters.size()];

            for(HashMap<String, Object> tCluster : tempClusters) {
                Data classCenter = (Data) tCluster.get(clusterCenterKey);
                List<Data> clusterObjects = (List<Data>) tCluster.get(clusterObjectsKey);

                if( !CommonFunctions.checkForEquality(data, classCenter) &&
                    !CommonFunctions.checkIfContain(clusterObjects, data)){
                    distance[tempClusters.indexOf(tCluster)] = CommonFunctions.getDistance(classCenter, data);
                }else{
                    distance = null;
                    break;
                }
            }

            if(distance == null) continue;

            ((List<Data>) tempClusters.get(CommonFunctions.getMinValueIndex(distance)).get(clusterObjectsKey)).add(data);
        }

        return tempClusters;
    }

    public static double getQualityFunctional(List<HashMap<String, Object>> tempClusters){
        double qualityFunctional = 0.0;
        for(HashMap<String, Object> cluster : tempClusters){
            List<Data> clusterObjects = (List<Data>) cluster.get(clusterObjectsKey);

            for(int i = 0; i < clusterObjects.size(); i++){
                for (Data clusterObject : clusterObjects) {
                    qualityFunctional += CommonFunctions.getDistance(clusterObjects.get(i), clusterObject);
                }
            }
        }
        return qualityFunctional;
    }
}
