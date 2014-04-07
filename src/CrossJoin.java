import java.util.ArrayList;
import java.util.Map;

public class CrossJoin extends Join{

    @Override
    public Map generateRecords(Map<String, ArrayList> table1Info, Map<String, ArrayList> table2Info, Map<String, ArrayList> outputTableInfo) {

        for(int i = 0; i < table1Info.get(table1Info.keySet().toArray()[0]).size(); i++) {
            for(int j = 0; j < table2Info.get(table2Info.keySet().toArray()[0]).size(); j++) {
                    for(Object key : table1Info.keySet())
                        outputTableInfo.get(key).add(table1Info.get(key).get(i));

                    for(Object key : table2Info.keySet())
                        outputTableInfo.get(key).add(table2Info.get(key).get(j));
                }
            }
        return outputTableInfo;
    }
}

