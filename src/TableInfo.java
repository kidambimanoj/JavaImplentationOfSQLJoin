import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableInfo {

    void checkForErrors(HashMap<String, ArrayList> table1Info, HashMap<String, ArrayList> table2Info) {

        for(ArrayList value : table1Info.values())
            if(value.size() != table1Info.keySet().size())    nullValuesFound();

        for(ArrayList value : table2Info.values())
            if(value.size() != table2Info.keySet().size())    nullValuesFound();
    }

    private void nullValuesFound() {
        System.out.println(" Null values in the records..");
    }

    private String simplify(String str) {
        str = str.replaceAll("\t", " ");
        while(str.contains("  "))
            str = str.replaceAll("  ", " ");
        return str;
    }

    private void createTableInfo(Map<String, ArrayList> table, ArrayList<String[]> records, String[] attributes) {
        ArrayList columns;

        for(int i  = 0; i < attributes.length; i++) {
            columns = new ArrayList();

            //System.out.println(records.size());

            for(int j = 0; j < records.size(); j++){
                columns.add(records.get(j)[i]);
            }
            table.put(attributes[i], columns);
        }
    }

    private ArrayList<String[]> setRecords(BufferedReader bufferedReader) throws IOException {
        ArrayList<String[]> records = new ArrayList<String[]>();
        String str;

        while((str  = bufferedReader.readLine()) != null){
            str = simplify(str);
            records.add(str.split(" "));
        }

        return records ;
    }

    private String[] setAttributes(BufferedReader bufferedReader) throws IOException {
        return simplify(bufferedReader.readLine()).split(" ");
    }

    public void setInput(Map<String, ArrayList> table, String filename) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        String[] attributes = setAttributes(bufferedReader);

        ArrayList<String[]> records = setRecords(bufferedReader);

        createTableInfo(table, records, attributes);
    }
}
