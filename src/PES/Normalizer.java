package PES;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Normalizer {
    Normalizer()
    {

    }

    Map<String,Float> normalize(Map<String,List<Integer>> rawScores)
    {
        Map<String,Float> scores = new HashMap<String, Float>();

        for(String key: rawScores.keySet())
        {
            List<Integer> a = rawScores.get(key);
            scores.put(key, (float) 1.0);
        }
        return scores;
    }
}
