package PES.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 Class for calculating normalized scores for entered Peer evaluation scores.
 */
public class NormalizerUtility {
    /*
    Method Name : normalize()
    Description : Method for normalizing and return final scores.
    Parameters : Map<String, List<Integer>> rawScores (Map of team members and List pf peer evaluation scores).
    Return Value : Map<String, Float> (Map of final scores for every team member).
     */
    public static Map<String, Float> normalize(Map<String, List<Integer>> rawScores) {
        Map<String, Float> result = new LinkedHashMap<String, Float>();
        float totalScore = 0;

        // For each entry(name), the list of scores(three) given by a student are added
        // and stored as intermediate score in a map(result)
        for (Map.Entry<String, List<Integer>> entry : rawScores.entrySet()) {
            float totalValue = 0;
            for (Integer score : entry.getValue()) {
                totalValue += score;
            }
            totalScore += totalValue;
            result.put(entry.getKey(), totalValue);
        }

        // Finally, the intermediate scores are normalized by dividing with the total score
        if (totalScore != 0) {
            for (Map.Entry<String, Float> entry : result.entrySet()) {
                result.put(entry.getKey(), entry.getValue() / totalScore);
            }
        }
        return result;
    }
}
