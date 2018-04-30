import java.util.*;

public class DATA {
public Map<String,ArrayList<String >> source = new HashMap<String, ArrayList<String>>();
public Map<String,String> commands_size = new HashMap<>();
public Map<String,String> reg = new HashMap<>();
public Map<String,String> reg16 = new HashMap<>();
public ArrayList<String> data= new ArrayList<String>();
public String[][] mod = new String[4][7];
static public Map<String,Integer> offset = new LinkedHashMap<String, Integer>();
static Deque<Integer> error = new ArrayDeque<Integer>();
DATA(){
    ArrayList<String> arrayList = new ArrayList<String>();
    STRING_DATA sd = new STRING_DATA();
    for (String s : sd.directices) {
        arrayList.add(s);
    }
    source.put(sd.atributes[0],arrayList);
    arrayList = new ArrayList<String>();

    for (String s : sd.commands) {
        arrayList.add(s);
    }
    source.put(sd.atributes[1],arrayList);
    arrayList = new ArrayList<String>();

    for (String s : sd.reg8) {
        arrayList.add(s);
    }
    source.put(sd.atributes[2],arrayList);
    arrayList = new ArrayList<String>();

    for (String s : sd.reg16) {
        arrayList.add(s);
    }
    source.put(sd.atributes[3],arrayList);
    toMap();
    Reg();
}

public void MODtable(){


}
public void Reg(){
    reg.put("ax", "000");
    reg.put("cx", "001");
    reg.put("dx", "010");
    reg.put("bx", "011");
    reg.put("sp", "100");
    reg.put("bp", "101");
    reg.put("si", "110");
    reg.put("di", "111");

    reg.put("al", "000");
    reg.put("cl", "001");
    reg.put("dl", "010");
    reg.put("bl", "011");
    reg.put("ah", "100");
    reg.put("ch", "101");
    reg.put("dh", "110");
    reg.put("bh", "111");

    reg16.put("si", "100");
    reg16.put("di", "101");
    reg16.put("bx", "111");
    reg16.put("bp", "110");
}
public void toMap(){
    commands_size.put("mov_reg8_mem","8A");
    commands_size.put("mov_reg16_mem","8B");
    commands_size.put("mov_mem_imm","C6");
    commands_size.put("not_16","F7");
    commands_size.put("not_8","F6");
    commands_size.put("and_16","23");
    commands_size.put("and_8","22");
    commands_size.put("call_far","9A");
    commands_size.put("call","E8");
    commands_size.put("ret","C3");
    commands_size.put("jna","76");
}


}

