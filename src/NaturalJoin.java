import java.util.*;

public class NaturalJoin extends Join {

    public void noCommonKeyFound() {
        System.out.println(" No common key for naturalJoin...");
    }

    public Object getCommonKey(Map<String, ArrayList> table1Info, Map<String, ArrayList> table2Info) {

        for(Object key : table2Info.keySet())
            if(table1Info.keySet().contains(key))
                return key;

        return null;
    }

    @Override
    public Map generateRecords(Map<String, ArrayList> table1Info, Map<String, ArrayList> table2Info, Map<String, ArrayList> outputTableInfo) {

        Object commonKey;

        if((commonKey = getCommonKey(table1Info, table2Info)) == null)   noCommonKeyFound();

        for(int i = 0; i < table1Info.get(commonKey).size(); i++) {

            for(int j = 0; j < table2Info.get(commonKey).size(); j++) {

                  if(table1Info.get(commonKey).get(i).equals(table2Info.get(commonKey).get(j)))
                  {
                    for(Object key : table1Info.keySet())
                        outputTableInfo.get(key).add(table1Info.get(key).get(i));

                    for(Object key : table2Info.keySet())
                        outputTableInfo.get(key).add(table2Info.get(key).get(j));

                    break;
                  }
            }
        }
        return outputTableInfo;
    }
}
