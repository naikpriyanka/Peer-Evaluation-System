package PES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* The method normalize() takes in the raw score and normalizes them.
* The normalized scores are returned as a Map with key as name and
* value as normalized score.
* */

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
