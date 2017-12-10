package PES.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 Class for calculating normalized scores for entered Peer evaluation scores.
 */
public final class NormalizerUtility {

    /*
   Method Name : normalize()
   Description : Method for normalizing raw scores and returning them.
   Parameters : Map<String, List<Integer>> rawScores (Map  with key as name of team members and value as List of scores entered).
   Return Value : Map<String, Float> (Map with key as name of team members and value as final normalized scores for every team member).
    */
    public static Map<String, Float> normalize(Map<String, List<Integer>> rawScores) {
        Map<String, Float> result = new LinkedHashMap<String, Float>();
        float totalScore = 0;

        for (Map.Entry<String, List<Integer>> entry : rawScores.entrySet()) {
            float totalValue = 0;
            for (Integer score : entry.getValue()) {
                totalValue += score;
            }
            totalScore += totalValue;
            result.put(entry.getKey(), totalValue);
        }

        if (totalScore != 0) {
            for (Map.Entry<String, Float> entry : result.entrySet()) {
                result.put(entry.getKey(), entry.getValue() / totalScore);
            }
        }
        return result;
    }
}
