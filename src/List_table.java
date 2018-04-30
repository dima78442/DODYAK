import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class List_table {
    static Map<String,Integer> seg_table = new LinkedHashMap<String, Integer>();
    static Map<String,Offset_Seg> label_table = new LinkedHashMap<String, Offset_Seg>();
    static public ArrayList<String> create_table(){
        ArrayList<String> strings = new ArrayList<String>();
        String buf = "NAME    VALUE   SEGMENT";
        strings.add(buf);
        for (Map.Entry tmp: label_table.entrySet()) {
            //buf = tmp.getKey().toString() + " " + File_analyze.formHEX(Integer.toHexString(((Offset_Seg)tmp.getValue()).offset)) +" "+ ((Offset_Seg)tmp.getValue()).seg_name;
            buf = String.format("%-8s %-6s %-7s", tmp.getKey().toString(),File_analyze.formHEX(Integer.toHexString(((Offset_Seg)tmp.getValue()).offset)),((Offset_Seg)tmp.getValue()).seg_name);
            strings.add(buf);
        }
        buf = "NAME   LENGTH";
        strings.add(buf);
        for (Map.Entry tmp : List_table.seg_table.entrySet()) {
            buf = tmp.getKey().toString();
            buf+= " " + File_analyze.formHEX(Integer.toHexString((Integer)tmp.getValue()));
            buf = String.format("%-7s %-6s",tmp.getKey().toString(),File_analyze.formHEX(Integer.toHexString((Integer)tmp.getValue())));
            strings.add(buf);
        }
        return strings;
    }
}
class Offset_Seg{
    String seg_name = "";
    Integer offset;
    Offset_Seg(String seg_name, Integer offset){
        this.seg_name = seg_name;
        this.offset = offset;
    }
}