import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class ERROR {
    static Set<Integer> error = new LinkedHashSet<Integer>();

    /*static void Redefinment_error(ArrayList<Sentence> _sentences){
        int i;
        for (Sentence buf : _sentences) {
            i = 0;
            for (Map.Entry<String,Offset_Seg> tmp: List_table.label_table.entrySet()) {
                if (buf.getDIRECTIVE()!=null) {
                    if (buf.getDIRECTIVE().text.toString().equals("dd")||buf.getDIRECTIVE().text.toString().equals("dw")||buf.getDIRECTIVE().text.toString().equals("db")) {
                        if(buf.getSentence().get(0).text.toString().equals(tmp.getKey())&&buf.getNON_STATIC_OFFSET()!=tmp.getValue().offset&&buf.getNON_STATIC_OFFSET()>tmp.getValue().offset){
                            i++;
                          //  System.out.println(buf.getSentence().get(0).text.toString() + " " + tmp.getKey() + " "+ buf.getNON_STATIC_OFFSET() + " " +tmp.getValue().offset);
                            //System.out.println("F");
                        }
                    }
                }
            }
            //System.out.println(i);
            if(i > 0){
                ERROR.error.add(buf.getSentence().get(0).x);
            }
        }
    }

    static void Redefinment_error2(ArrayList<Lexem> buf,int offset){
        int i;

            i = 0;
            for (Map.Entry<String,Offset_Seg> tmp: List_table.label_table.entrySet()) {
                try {
                    if (buf.get(1)!=null) {
                        if (buf.get(1).text.toString().equals("dd")||buf.get(1).text.toString().equals("dw")||buf.get(1).text.toString().equals("db")) {
                            if(buf.get(0).text.toString().equals(tmp.getKey())){
                                i++;
                                  System.out.println(buf.get(0).text.toString() + " " + tmp.getKey() + " "+ offset + " " +tmp.getValue().offset);
                                //System.out.println("F");
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }
           // System.out.println(i);
            if(i == 2){
                ERROR.error.add(buf.get(0).x);
            }

    }*/
    public static void showError(){
        if(ERROR.error.isEmpty()){
            System.out.println("SUCCESSFUl RUN(NO ERRORS)");
            return;
        }
        System.out.println("ERRORS:");
        Iterator<Integer> iterator = ERROR.error.iterator();
        while (iterator.hasNext()){
            System.out.println("line " + iterator.next().toString());
        }
    }
}
