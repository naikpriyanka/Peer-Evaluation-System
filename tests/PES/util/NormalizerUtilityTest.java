package PES.util;

import java.util.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
/*
 * Class for testing NormalizerUtility class.
 * */
public class NormalizerUtilityTest {
    private NormalizerUtility nu;

    /*
    Method Name : setUp()
    Description : Method for initial setup of variables.
    Parameters : None.
    Return Value : None.
     */
    @Before
    public void setUp(){
        nu = new NormalizerUtility();
    }

    /*
    Method Name : OnePerson()
    Description : Method for testing normalizerUtility for 1 person.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void OnePerson() throws Exception {
    	Map<String, List<Integer>> mymap = new HashMap<>();
    	mymap.put("Person 1",new ArrayList<>(Arrays.asList(4,3,5)));
    	Map<String,Float> expectedMap = new HashMap<>();
    	expectedMap.put("Person 1",1.0f);
    	assertEquals(expectedMap,nu.normalize(mymap));

    }

    /*
    Method Name : TwoPeople()
    Description : Method for testing normalizerUtility for 2 people.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void TwoPeople() throws Exception {
        Map<String, List<Integer>> mymap = new HashMap<>();
        mymap.put("Person 1",new ArrayList<>(Arrays.asList(4,3,5)));
        mymap.put("Person 2",new ArrayList<>(Arrays.asList(2,0,5)));
        Map<String,Float> expectedMap = new HashMap<>();
        expectedMap.put("Person 1",(float)12/19);
        expectedMap.put("Person 2",(float)7/19);
        assertEquals(expectedMap,nu.normalize(mymap));

    }

    /*
    Method Name : SixPeople()
    Description : Method for testing normalizerUtility for 6 people.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void SixPeople() throws Exception {
        Map<String, List<Integer>> mymap = new HashMap<>();
        mymap.put("Person 1",new ArrayList<>(Arrays.asList(4,3,5)));
        mymap.put("Person 2",new ArrayList<>(Arrays.asList(2,0,5)));
        mymap.put("Person 3",new ArrayList<>(Arrays.asList(1,4,3)));
        mymap.put("Person 4",new ArrayList<>(Arrays.asList(5,5,5)));
        mymap.put("Person 5",new ArrayList<>(Arrays.asList(0,0,0)));
        mymap.put("Person 6",new ArrayList<>(Arrays.asList(2,4,5)));
        Map<String,Float> expectedMap = new HashMap<>();
        expectedMap.put("Person 1",(float)12/53);
        expectedMap.put("Person 2",(float)7/53);
        expectedMap.put("Person 3",(float)8/53);
        expectedMap.put("Person 4",(float)15/53);
        expectedMap.put("Person 5",(float)0/53);
        expectedMap.put("Person 6",(float)11/53);

        assertEquals(expectedMap,nu.normalize(mymap));

    }

    /*
    Method Name : SevenPeople()
    Description : Method for testing normalizerUtility for 7 people.
    Parameters : None.
    Return Value : None.
     */
    @Test
    public void SevenPeople() throws Exception {
        Map<String, List<Integer>> mymap = new HashMap<>();
        mymap.put("Person 1",new ArrayList<>(Arrays.asList(4,3,5)));
        mymap.put("Person 2",new ArrayList<>(Arrays.asList(2,0,5)));
        mymap.put("Person 3",new ArrayList<>(Arrays.asList(1,4,3)));
        mymap.put("Person 4",new ArrayList<>(Arrays.asList(5,5,5)));
        mymap.put("Person 5",new ArrayList<>(Arrays.asList(0,0,0)));
        mymap.put("Person 6",new ArrayList<>(Arrays.asList(2,4,5)));
        mymap.put("Person 7",new ArrayList<>(Arrays.asList(3,2,3)));
        Map<String,Float> expectedMap = new HashMap<>();
        expectedMap.put("Person 1",(float)12/61);
        expectedMap.put("Person 2",(float)7/61);
        expectedMap.put("Person 3",(float)8/61);
        expectedMap.put("Person 4",(float)15/61);
        expectedMap.put("Person 5",(float)0/61);
        expectedMap.put("Person 6",(float)11/61);
        expectedMap.put("Person 7",(float)8/61);

        assertEquals(expectedMap,nu.normalize(mymap));

    }
}