import java.util.ArrayList;
import java.util.Map;

public abstract class Join {
    public void generateColumns(Map<String, ArrayList> table1Info, Map<String, ArrayList> table2Info, Map<String, ArrayList> outputTableInfo) {

        for(String key : table1Info.keySet())
            outputTableInfo.put(key, new ArrayList());

        for(String key : table2Info.keySet())
            outputTableInfo.put(key, new ArrayList());
    }

    public abstract Map generateRecords(Map<String, ArrayList> table1, Map<String, ArrayList> table2, Map<String, ArrayList> outputTable);
}
