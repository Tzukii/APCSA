import java.util.*;

/**
 * Represents various States and their respective cities.
 *
 * @author Shivam Maji
 * @version 3/25/2021
 * @author Period - 6
 *
 * @author Assignment - TestSem2CollectionsStates
 *
 * @author Sources - Me Myself and I
 */
public class States {
    private Map<String, Set<String>> theMap;

    public States() {
        theMap = new TreeMap<String, Set<String>>();
    }

    public void addCityToMap(CityInfo theCity) {
        theMap.put(theCity.state(), new TreeSet<>());
        theMap.get(theCity.state()).add(theCity.city());
    }

    public void printOneState(String theState) {
        System.out.print(theState);
        for (String string : theMap.get(theState)) {
            System.out.print(" " + string);
        }
    }

    public void printAllStates() {
        for (String state : theMap.keySet()) {
            printOneState(state);
            System.out.println();
        }

    }

    // *************************************************************
    // For test purposes only
    // not to be used in solution implementation
    public Map<String, Set<String>> getTheMap() {
        return theMap;
    }
}