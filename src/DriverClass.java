import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverClass {

    private static String FILENAME1 = "Table1.csv";
    private static String FILENAME2 = "Table2.csv";


    private void doTheJoin(Map<String, ArrayList> table1Info, Map<String, ArrayList> table2Info) {

        Map<String, ArrayList> naturalJoinOutput = naturalJoin(table1Info, table2Info, new NaturalJoin());
        Map<String, ArrayList> crossJoinOutput = crossJoin(table1Info, table2Info, new CrossJoin());
        print(naturalJoinOutput);
        print(crossJoinOutput);
    }

    private Map crossJoin(Map<String, ArrayList> table1Info, Map<String, ArrayList> table2Info, CrossJoin join) {

        Map<String, ArrayList> outputTableInfo = new LinkedHashMap<String, ArrayList>();
        join.generateColumns(table1Info, table2Info, outputTableInfo);
        return join.generateRecords(table1Info, table2Info, outputTableInfo);
    }

    private Map naturalJoin(Map<String, ArrayList> table1Info, Map<String, ArrayList> table2Info, Join join) {

        Map<String, ArrayList> outputTableInfo = new LinkedHashMap<String, ArrayList>();
        join.generateColumns(table1Info, table2Info, outputTableInfo);
        return join.generateRecords(table1Info, table2Info, outputTableInfo);
    }

    private static void print(Map<String, ArrayList> outputTableInfo) {

        for(Object key : outputTableInfo.keySet())
            System.out.format("%20s", key);
        System.out.println();

        int loopCount = outputTableInfo.values().iterator().next().size();

        for(int i = 0; i < loopCount; i++) {
            for(ArrayList value : outputTableInfo.values()) {
                System.out.format("%20s", (i < value.size()) ? value.get(i) : "NULL");
            }
            System.out.println();
        }
        System.out.println("***************************************");
    }

    private static void getTheTableData(Map<String, ArrayList> table1Info, Map<String, ArrayList> table2Info) throws IOException {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setInput(table1Info, FILENAME1);
        tableInfo.setInput(table2Info, FILENAME2);
        //tableInfo.checkForErrors(table1Info, table2Info);
    }

    public static void main(String[] args) throws Exception {
        Map<String, ArrayList> table1Info = new LinkedHashMap<String, ArrayList>();
        Map<String, ArrayList> table2Info = new LinkedHashMap<String, ArrayList>();

        DriverClass driver = new DriverClass();
        getTheTableData(table1Info, table2Info);
        driver.doTheJoin(table1Info, table2Info);
     }
}