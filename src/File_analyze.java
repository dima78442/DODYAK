import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class File_analyze {
    private static String name;
    private ArrayList<String> strings;
    File_analyze(String _name){
        name = _name;
        analyze();
    }
private void analyze(){
    try(FileReader reader = new FileReader(name))
    {
        strings = new ArrayList<>();
        Scanner sc = new Scanner(reader);
        int i = 0;
        while(sc.hasNextLine()){

                strings.add(sc.nextLine());
                if(strings.get(strings.size() - 1).trim().length() == 0){
                    strings.remove(strings.size() - 1);
                }
            i++;
        }
        reader.close();

    }
    catch(IOException ex){

        System.out.println(ex.getMessage());
    }
}
    public void  createListing (ArrayList<Sentence> _sentences) throws Exception{
        Sentence buf;
        String s;
        File newfile = new File("/Users/dima/Desktop/Test1.lst");
        if (!newfile.exists()) {

            newfile.createNewFile();
            newfile.setWritable(true);
        }
        FileWriter fw = new FileWriter(newfile);
        BufferedWriter fileWriter = new BufferedWriter(fw);
        for (int i = 0; i < _sentences.size(); i++) {
            buf = _sentences.get(i);
            s = strings.get(i);
            if(s.equals("end")) {
                fileWriter.write("                             end");
            }
            else if (ERROR.error.contains(i + 1)) {
                fileWriter.write(String.format("%-5d %-5s %-16s %-20s %-50s",buf.getSentence().get(0).x, formHEX(Integer.toHexString(buf.getNON_STATIC_OFFSET())),buf.struct, s,"\n error"));
            }else {
                fileWriter.write(String.format("%-5d %-5s %-16s %-10s",buf.getSentence().get(0).x, formHEX(Integer.toHexString(buf.getNON_STATIC_OFFSET())),buf.struct, s));
            }
            fileWriter.newLine();
        }
       // fileWriter.write(String.format);
        ArrayList<String> table = List_table.create_table();
        for (String buf1 : table) {
             fileWriter.write(buf1 + "\n");
        }
        if (!ERROR.error.isEmpty()) {
            fileWriter.write("ERRORS:\n");
            Iterator<Integer> integerIterator = ERROR.error.iterator();
            while (integerIterator.hasNext()){
                fileWriter.write("line " + integerIterator.next().toString());
                fileWriter.newLine();
            }
        }else {
            fileWriter.write("SUCCESSESFUL RUN(NO ERRORS)");
        }
        fileWriter.close();
    }

    public ArrayList<String> getStrings() {
        return strings;
    }
    static public String formHEX(String s){
        String buf;
        if(s.length() == 1){
            buf = "000" + s;
            return buf.toUpperCase();
        }
        else if(s.length() == 2){
            buf = "00" + s;
            return buf.toUpperCase();
        }
        else if(s.length() == 3){
            buf = "0" + s;
            return buf.toUpperCase();
        }
        return s.toUpperCase();
    }

}
