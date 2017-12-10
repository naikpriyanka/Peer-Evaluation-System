package PES.util;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


/*
 * Class for testing NormalizerUtility class.
 * */
public class NormalizerUtilityTest {

    /*
    Method Name : testNormalizeZeroScores()
    Description : Method for testing normalizerUtility for all zero scores.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void testNormalizeZeroScores() throws Exception {
        Map<String, List<Integer>> mymap = new HashMap<>();
        mymap.put("Person 1", new ArrayList<>(Arrays.asList(0, 0, 0)));
        mymap.put("Person 2", new ArrayList<>(Arrays.asList(0, 0, 0)));
        mymap.put("Person 3", new ArrayList<>(Arrays.asList(0, 0, 0)));
        mymap.put("Person 4", new ArrayList<>(Arrays.asList(0, 0, 0)));
        mymap.put("Person 5", new ArrayList<>(Arrays.asList(0, 0, 0)));
        mymap.put("Person 6", new ArrayList<>(Arrays.asList(0, 0, 0)));
        Map<String, Float> expectedMap = new HashMap<>();
        expectedMap.put("Person 1", (float) 0.0);
        expectedMap.put("Person 2", (float) 0.0);
        expectedMap.put("Person 3", (float) 0.0);
        expectedMap.put("Person 4", (float) 0.0);
        expectedMap.put("Person 5", (float) 0.0);
        expectedMap.put("Person 6", (float) 0.0);

        assertEquals(expectedMap, NormalizerUtility.normalize(mymap));
    }

    /*
    Method Name : testNormalizeOnePerson()
    Description : Method for testing normalizerUtility for 1 person.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void testNormalizeOnePerson() throws Exception {
        Map<String, List<Integer>> mymap = new HashMap<>();
        mymap.put("Person 1", new ArrayList<>(Arrays.asList(4, 3, 5)));
        Map<String, Float> expectedMap = new HashMap<>();
        expectedMap.put("Person 1", 1.0f);
        assertEquals(expectedMap, NormalizerUtility.normalize(mymap));
    }

    /*
    Method Name : testNormalizeTwoPeople()
    Description : Method for testing normalizerUtility for 2 people.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void testNormalizeTwoPeople() throws Exception {
        Map<String, List<Integer>> mymap = new HashMap<>();
        mymap.put("Person 1", new ArrayList<>(Arrays.asList(4, 3, 5)));
        mymap.put("Person 2", new ArrayList<>(Arrays.asList(2, 0, 5)));
        Map<String, Float> expectedMap = new HashMap<>();
        expectedMap.put("Person 1", (float) 12 / 19);
        expectedMap.put("Person 2", (float) 7 / 19);
        assertEquals(expectedMap, NormalizerUtility.normalize(mymap));
    }

    /*
    Method Name : testNormalizeSixPeople()
    Description : Method for testing normalizerUtility for 6 people.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void testNormalizeSixPeople() throws Exception {
        Map<String, List<Integer>> mymap = new HashMap<>();
        mymap.put("Person 1", new ArrayList<>(Arrays.asList(4, 3, 5)));
        mymap.put("Person 2", new ArrayList<>(Arrays.asList(2, 0, 5)));
        mymap.put("Person 3", new ArrayList<>(Arrays.asList(1, 4, 3)));
        mymap.put("Person 4", new ArrayList<>(Arrays.asList(5, 5, 5)));
        mymap.put("Person 5", new ArrayList<>(Arrays.asList(0, 0, 0)));
        mymap.put("Person 6", new ArrayList<>(Arrays.asList(2, 4, 5)));
        Map<String, Float> expectedMap = new HashMap<>();
        expectedMap.put("Person 1", (float) 12 / 53);
        expectedMap.put("Person 2", (float) 7 / 53);
        expectedMap.put("Person 3", (float) 8 / 53);
        expectedMap.put("Person 4", (float) 15 / 53);
        expectedMap.put("Person 5", (float) 0 / 53);
        expectedMap.put("Person 6", (float) 11 / 53);

        assertEquals(expectedMap, NormalizerUtility.normalize(mymap));
    }

    /*
    Method Name : testNormalizeSevenPeople()
    Description : Method for testing normalizerUtility for 7 people.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void testNormalizeSevenPeople() throws Exception {
        Map<String, List<Integer>> mymap = new HashMap<>();
        mymap.put("Person 1", new ArrayList<>(Arrays.asList(4, 3, 5)));
        mymap.put("Person 2", new ArrayList<>(Arrays.asList(2, 0, 5)));
        mymap.put("Person 3", new ArrayList<>(Arrays.asList(1, 4, 3)));
        mymap.put("Person 4", new ArrayList<>(Arrays.asList(5, 5, 5)));
        mymap.put("Person 5", new ArrayList<>(Arrays.asList(0, 0, 0)));
        mymap.put("Person 6", new ArrayList<>(Arrays.asList(2, 4, 5)));
        mymap.put("Person 7", new ArrayList<>(Arrays.asList(3, 2, 3)));
        Map<String, Float> expectedMap = new HashMap<>();
        expectedMap.put("Person 1", (float) 12 / 61);
        expectedMap.put("Person 2", (float) 7 / 61);
        expectedMap.put("Person 3", (float) 8 / 61);
        expectedMap.put("Person 4", (float) 15 / 61);
        expectedMap.put("Person 5", (float) 0 / 61);
        expectedMap.put("Person 6", (float) 11 / 61);
        expectedMap.put("Person 7", (float) 8 / 61);

        assertEquals(expectedMap, NormalizerUtility.normalize(mymap));
    }
}
