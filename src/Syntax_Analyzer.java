import java.util.ArrayList;
import java.util.Map;

public class Syntax_Analyzer {
    private ArrayList<Lexem> lexems;
    private ArrayList<String> strings;
    // private ArrayList<Sentence> Sentence_s;
    private ArrayList<Sentence> Sentences2 = new ArrayList<Sentence>();
    private ArrayList<ArrayList<Lexem>> Sentences;

    Syntax_Analyzer(Lex_analizator lex_analizator) throws Exception {
        //System.out.println("1");
        lexems = lex_analizator.getLexems_type();
        Sentences2 = SentenceBuilder(lexems);
        strings = lex_analizator.getStrings();
        File_analyze file_analyze = new File_analyze("/Users/dima/Desktop/Test.asm");
        file_analyze.createListing(Sentences2);
    }

    public void show() {
        /*int mnemocode_1 = -1;
        int mnemocode_2 = -1;
        int operand1_1 = -1;
        int operand1_2 = 0;
        int operand2_1 = -1;
        int operand2_2 = 0;
        int label = -1;*/
        int d = -1;
        int z = 0;
        boolean b = false;
        ArrayList<Lexem> buf = lexems;
        Sentence sentence2;
        String s;
        int f = 0;
        System.out.printf("|%3s| |%8s| |%11s| |%2s|\n", "num", "lexem", "type", "size");
        for (int i = 0; i < buf.size(); i++) {
            d++;
            try {
                if (buf.get(i).x == buf.get(i + 1).x) {
                    if (b == false) {
                        System.out.println("----" + strings.get(z));
                        z++;
                        b = true;
                    }
                    // System.out.println(d + " " + buf.get(i).text.toString() + " " + buf.get(i).type + " " +  buf.get(i).text.length() );
                    System.out.printf("%5d %10s %13s %4d %4d\n", d, buf.get(i).text.toString(), buf.get(i).type, buf.get(i).text.length(),buf.get(i).y);
                } else {
                    int mnemocode_1 = -1;
                    int mnemocode_2 = -1;
                    int operand1_1 = -1;
                    int operand1_2 = 0;
                    int operand2_1 = -1;
                    int operand2_2 = 0;
                    int label = -1;
                    if (buf.get(i).text.toString().equals("ret")) {
                        System.out.println("----" + strings.get(z));
                        z++;
                    }
                    //System.out.println(d + " " + buf.get(i).text.toString() + " " + buf.get(i).type + " " + buf.get(i).text.length());
                    System.out.printf("%5d %10s %13s %4d %4d\n", d, buf.get(i).text.toString(), buf.get(i).type, buf.get(i).text.length(),buf.get(i).y);
                    b = false;
                    sentence2 = Sentences2.get(f);
                    f++;
                    d = -1;
                    System.out.printf("|%3s| |%8s| |%11s| |%2s|\n", "label", "mnemocode", "1 operand", "2 operand");
                    if (sentence2.getLABEL() != null) {
                        label = 0;
                    } else {
                        label = -1;
                    }
                    if (sentence2.getCOMMAND() != null) {
                        mnemocode_1 = sentence2.getCOMMAND().y;
                        if (sentence2.getCOMMAND().ptr == true) {
                            mnemocode_2 = 3;
                            operand1_1 = 3;
                            operand1_2 = 1;
                        } else if (sentence2.getCOMMAND().ptr == false) {
                            mnemocode_2 = 1;
                            operand1_1 = 1;
                            operand1_2 = 1;
                        }
                        if (sentence2.getOPERAND1() != null) {
                            operand1_1 = sentence2.getOPERAND1().get(0).y;
                            operand1_2 = sentence2.getOPERAND1().size();
                        }

                        if (sentence2.getOPERAND2() != null) {
                            operand2_1 = sentence2.getOPERAND2().get(0).y;
                            operand2_2 = sentence2.getOPERAND2().size();
                            if(sentence2.getOPERAND2().get(0).text.toString().equals("offset")){
                                operand2_1 = sentence2.getOPERAND2().get(0).y - 1;
                            }
                        }
                        if (sentence2.getCOMMAND().text.toString().equals("ret")) {
                            operand1_1 = -1;
                            operand1_2 = 0;
                            operand2_1 = -1;
                            operand1_2 = 0;
                        }
                    } else if (sentence2.getDIRECTIVE() != null) {
                        mnemocode_1 = sentence2.getDIRECTIVE().y;
                        mnemocode_2 = 1;
                        if (sentence2.getDIRECTIVE().text.toString().equals("dd") || sentence2.getDIRECTIVE().text.toString().equals("dw") || sentence2.getDIRECTIVE().text.toString().equals("db")) {
                            operand1_1 = 2;
                            operand1_2 = 1;
                        }
                    } else {
                        mnemocode_1 = -1;
                    }
                    if (sentence2.getFINAL() != null) {
                        operand1_1 = 2;
                        operand1_2 = 1;
                        //operand2 = -1;
                    }
                    System.out.printf("|%3d| |%4d| |%4d| |%5d| |%4d| |%2d| |%2d|\n", label, mnemocode_1, mnemocode_2, operand1_1, operand1_2, operand2_1, operand2_2);
                }
            } catch (Exception e) {
                if (buf.get(i).text.toString().equals("end")) {
                    System.out.println("----" + strings.get(z));
                    z++;
                }
                //System.out.println(d + " " + buf.get(i).text.toString() + " " + buf.get(i).type + " " + buf.get(i).text.length());
                System.out.printf("%5d %10s %13s %4d %4d\n", d, buf.get(i).text.toString(), buf.get(i).type, buf.get(i).text.length(),buf.get(i).y);
                System.out.printf("|%3s| |%8s| |%11s| |%2s|\n", "label", "mnemocode", "1 operand", "2 operand");
                System.out.printf("|%3d| |%4d| |%4d| |%5d| |%4d| |%2d| |%2d|\n", -1, 0, 1, -1, 0, -1, 0);
            }
        }
    }

    ArrayList<Sentence> SentenceBuilder(ArrayList<Lexem> _lexems) throws Exception {
        for (int k = 0; k < _lexems.size(); k++) {
            //System.out.print(_lexems.get(k).text.toString() + "1 " );
        }
        ArrayList<ArrayList<Lexem>> lexem_sentences = new ArrayList<ArrayList<Lexem>>();
        ArrayList<Lexem> buf = new ArrayList<Lexem>();
        int z = 0;
        int i = 1;
        // int j = _lexems.size();

        buf.add(lexems.get(0));
        // System.out.println(" ");
        while (i != _lexems.size()) {

            if (_lexems.get(i).x == _lexems.get(i - 1).x) {
                buf.add(_lexems.get(i).clone());

            } else {
                for (int k = 0; k < buf.size(); k++) {
                    // System.out.print(buf.get(k).text.toString() + " " + buf.get(k).x );

                }
                lexem_sentences.add(buf);

                buf = new ArrayList<Lexem>();
                buf.add(_lexems.get(i).clone());
            }
            i++;
        }
        lexem_sentences.add(buf);
        //System.out.println("2");


        for (int k = 0; k < lexem_sentences.size(); k++) {
            //System.out.println(lexem_sentences.get(k).get(0).text.toString() + "\n");
            buf = lexem_sentences.get(k);
            Sentence sentence = new Sentence(buf);
            sentence.SentenceChecker();
            sentence.SentenceSizer();
            sentence.SentenceStruct();
            if (sentence.getLABEL() != null) {
                DATA.offset.put(sentence.getLABEL().text.toString(), sentence.getNON_STATIC_OFFSET());
            }
            Sentences2.add(sentence);
        }
        MOD_BYTE.Jna_dis2(Sentences2);
        MOD_BYTE.call_adress(Sentences2);
        MOD_BYTE.adress_data(Sentences2);
        //ERROR.Redefinment_error(Sentences2);
        return Sentences2;
    }

}

class Sentence implements Cloneable {
    public static String segment_stat = "";
    public String segment_non_stat = "";
    public int segment_size = 0;
    private Lexem LABEL = null;
    private Lexem DIRECTIVE = null;
    private Lexem FINAL = null;
    private Lexem COMMAND = null;
    //private Lexem OPERAND1 = null;
    private ArrayList<Lexem> OPERAND1 = null;
    private ArrayList<Lexem> OPERAND2 = null;
    Lexem JNA_OP = null;
    Lexem CALL_OP = null;
    private ArrayList<Lexem> sentence;
    private static int STATIC_OFFSET;
    private int NON_STATIC_OFFSET;
    private boolean oper_flag = false;
    private int local_offset = 0;
    String command_byte = "";
    String mod_r_m_byte = "";
    String struct = "";
    String adress = "";
    DATA data = new DATA();
    Sentence(ArrayList<Lexem> _sentence) {
        sentence = _sentence;
    }


    public void SentenceChecker() throws Exception {
        int b = 0;
        int z = 0;
        int size = sentence.size();
        for (int i = 0; i < size; i++) {
            switch (sentence.get(i).type) {
                case "COMMAND":
                    //System.out.println(sentence.get(i).type + " ");
                    COMMAND = sentence.get(i).clone();
                    if (sentence.size() != i + 1 && sentence.get(i + 1).text.toString().equals("far")) {
                        COMMAND.ptr = true;
                        //System.out.println("win");
                    }
                    switch (COMMAND.text.toString()) {
                        case "not":
                            OPERAND1 = new ArrayList<Lexem>();
                            break;
                        case "mov":
                            OPERAND1 = new ArrayList<Lexem>();
                            OPERAND2 = new ArrayList<Lexem>();
                            break;
                        case "and":
                            OPERAND1 = new ArrayList<Lexem>();
                            OPERAND2 = new ArrayList<Lexem>();
                            break;
                        case "jna":
                            JNA_OP = sentence.get(i + 1);
                            break;
                    }
                    break;
                case "IDENTIFICATOR":
                    //System.out.println(sentence.get(i).type + " ");
                    if (i == 0) {
                        LABEL = sentence.get(0).clone();
                        if (sentence.get(i + 1).text.toString().equals(":")) {
                            List_table.label_table.put(LABEL.text.toString(), new Offset_Seg(segment_stat, STATIC_OFFSET));
                        }
                    }
                    /*if (i == 0 && sentence.size() == 1||i == 0 && sentence.get(i + 1).text.toString().equals(":")&&sentence.size()>1) {
                        LABEL = sentence.get(i).clone();
                    }
                    if(COMMAND.text.toString().equals("dd")||COMMAND.text.toString().equals("dd")||COMMAND.text.toString().equals("dd")){
                        LABEL = sentence.get(i - 1).clone();
                    }*/
                    if (COMMAND == null) {
                        break;
                    }
                    try {
                        if (COMMAND.text.toString().equals("mov") == true && oper_flag == false && sentence.get(i - 1).type.equals("COMMAND") == true) {
                            // System.out.println("FFFFFF");
                            try {
                                if (sentence.get(i).type.equals("IDENTIFICATOR")) {
                                    sentence.get(i).mov = 2;
                                    OPERAND1.add(sentence.get(i));
                                } else {
                                    ERROR.error.add(sentence.get(i).x);
                                }
                                if (sentence.get(i + 1).text.toString().equals("[")) {
                                    sentence.get(i + 1).mov = 2;
                                    OPERAND1.add(sentence.get(i + 1));
                                } else {
                                    ERROR.error.add(sentence.get(i).x);
                                }
                                //if (sentence.get(i + 4).text.toString().equals("[")) {
                                if (Checker.checker_data("registers8",sentence.get(i + 2).text.toString())||Checker.checker_data("register16",sentence.get(i + 2).text.toString())) {
                                    sentence.get(i + 2).mov = 2;
                                    OPERAND1.add(sentence.get(i + 2));
                                }else {
                                    ERROR.error.add(sentence.get(0).x);
                                }
                                //}
                                if (sentence.get(i + 3).text.toString().equals("]")) {
                                    sentence.get(i + 3).mov = 2;
                                    OPERAND1.add(sentence.get(i + 3));
                                }else {
                                    ERROR.error.add(sentence.get(i).x);
                                }
                                if (sentence.get(i + 4).text.toString().equals(",")) {
                                    ;
                                } else {
                                    ERROR.error.add(sentence.get(i).x);
                                }
                                if (sentence.get(i + 5).text.toString().equals("offset")) {
                                    sentence.get(i + 5).mov = 2;
                                    OPERAND2.add(sentence.get(i + 5));
                                }else {
                                    ERROR.error.add(sentence.get(i).x);
                                }
                                if (sentence.get(i + 6).type.equals("IDENTIFICATOR")) {
                                    sentence.get(i + 6).mov = 2;
                                    OPERAND2.add(sentence.get(i + 6));
                                } else {
                                    ERROR.error.add(sentence.get(i).x);
                                }
                                oper_flag = true;

                            } catch (IndexOutOfBoundsException e) {
                                ERROR.error.add(sentence.get(i).x);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "HEX":
                    FINAL = sentence.get(i).clone();
                    break;
                case "TEXT":
                    FINAL = sentence.get(i).clone();
                    break;
                case "DIRECTIVE":
                    if (!sentence.get(i).equals("far") && !sentence.get(i).equals("offset") && !sentence.get(i).equals("ptr")) {
                        DIRECTIVE = sentence.get(i).clone();
                    }
                    if (sentence.get(i).text.toString().equals("segment")) {
                        segment_stat = sentence.get(i - 1).text.toString();
                    }
                    if (sentence.get(i).text.toString().equals("ends")) {
                        segment_size = STATIC_OFFSET;

                        List_table.seg_table.put(segment_stat, segment_size);

                    }
                    if (sentence.get(i).text.toString().equals("dd") || sentence.get(i).text.toString().equals("dw") || sentence.get(i).text.toString().equals("db")) {
                        for (Map.Entry<String, Offset_Seg> tmp : List_table.label_table.entrySet()) {
                            if (tmp.getKey().equals(LABEL.text.toString())) {
                                ERROR.error.add(sentence.get(i).x);
                            }
                        }
                        List_table.label_table.put(LABEL.text.toString(), new Offset_Seg(segment_stat, STATIC_OFFSET));

                    }
                    /*String buf = "";
                    if(sentence.get(i).text.toString().equals("dd")||sentence.get(i).text.toString().equals("dw")||sentence.get(i).text.toString().equals("db")){
                        List_table.label_table.put(LABEL.text.toString(),new Offset_Seg(segment_stat, STATIC_OFFSET));
                        if(sentence.get(2).type.equals("IDENTIFICATOR")){
                            for (Map.Entry<String,Offset_Seg> tmp: List_table.label_table.entrySet()) {
                                if(tmp.getKey().equals(sentence.get(2).text.toString())){
                                    if (tmp.getValue().seg_name.equals(segment_stat)) {
                                        if(sentence.get(i).text.toString().equals("dd")){
                                          buf = " ---- R";
                                        }else if(sentence.get(i).text.toString().equals("dw")){
                                            buf = " R";
                                        }else {
                                            //err
                                        }
                                        setCommand_byte(File_analyze.formHEX(Integer.toHexString(tmp.getValue().offset)) + buf);
                                    }else {
                                        //err
                                    }
                                }
                            }
                        }
                    }*/
                    if (sentence.get(i).text.toString().equals("proc")) {
                        List_table.label_table.put(LABEL.text.toString(), new Offset_Seg(segment_stat, STATIC_OFFSET));
                    }
                    break;
                case "REGISTER8":
                    // System.out.println(sentence.get(i).type + " ");
                    if (COMMAND == null) {
                        break;
                    }
                    if (COMMAND.text.toString().equals("not")) {
                        sentence.get(i).not = 1;
                        if (sentence.get(i).type.equals("REGISTER8")) {
                            OPERAND1.add(sentence.get(i).clone());
                        }else {
                            ERROR.error.add(sentence.get(i).x);
                        }
                    }

                    try {
                        if (COMMAND.text.toString().equals("mov") == true && oper_flag == false && sentence.get(i - 1).type.equals("COMMAND") == true) {
                            sentence.get(i).mov = 1;
                            OPERAND1.add(sentence.get(i));
                            try {
                                if (sentence.get(i + 1).text.toString().equals(",")) {
                                    if (sentence.get(i + 2).type.equals("IDENTIFICATOR")) {
                                        sentence.get(i + 2).mov = 1;
                                        OPERAND2.add(sentence.get(i + 2));
                                    } else {
                                        ERROR.error.add(sentence.get(i).x);
                                    }
                                    if (sentence.get(i + 3).text.toString().equals("[")) {
                                        sentence.get(i + 3).mov = 1;
                                        OPERAND2.add(sentence.get(i + 3));
                                    } else {
                                        ERROR.error.add(sentence.get(i).x);
                                    }
                                    //if (sentence.get(i + 4).text.toString().equals("[")) {
                                    if (sentence.get(1).type.equals("REGISTER8")) {
                                        sentence.get(i + 4).mov = 1;
                                        OPERAND2.add(sentence.get(i + 4));
                                    }else {
                                        ERROR.error.add(sentence.get(i).x);
                                    }
                                    //}
                                    if (sentence.get(i + 5).text.toString().equals("]")) {
                                        sentence.get(i + 5).mov = 1;
                                        OPERAND2.add(sentence.get(i + 5));
                                    } else {
                                        ERROR.error.add(sentence.get(i).x);
                                    }
                                    oper_flag = true;
                                }else {
                                    ERROR.error.add(sentence.get(i).x);
                                }
                            } catch (Exception e) {
                                ERROR.error.add(sentence.get(i).x);
                                e.printStackTrace();
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ERROR.error.add(sentence.get(i).x);
                    }
                    try {
                        if (COMMAND.text.toString().equals("and") == true && sentence.get(i - 1).type.equals("COMMAND") == true) {
                            sentence.get(i).and = 1;
                            if (sentence.get(i).type.equals("REGISTER8")) {
                                OPERAND1.add(sentence.get(i));
                            }else {
                                ERROR.error.add(sentence.get(i).x);
                            }
                            if (sentence.get(i + 1).text.toString().equals(",")) {
                                ;
                            } else {
                                ERROR.error.add(sentence.get(i).x);
                            }
                            if (sentence.get(i + 2).type.equals("REGISTER8")) {
                                OPERAND2.add(sentence.get(i + 2));
                            }else {
                                ERROR.error.add(sentence.get(i).x);
                            }
                        }
                    } catch (Exception e) {
                       // e.printStackTrace();
                        ERROR.error.add(sentence.get(i).x);
                    }
                    break;
                case "REGISTER16":
                    if (COMMAND == null) {
                        break;
                    }
                    if (COMMAND.text.toString().equals("not")) {
                        sentence.get(i).not = 2;
                        if (sentence.get(i).type.equals("REGISTER16")) {
                            OPERAND1.add(sentence.get(i).clone());
                        }else {
                            ERROR.error.add(sentence.get(i).x);
                        }
                    }
                    try {
                        if (COMMAND.text.toString().equals("mov") == true && oper_flag == false && sentence.get(i - 1).type.equals("COMMAND") == true) {

                            //System.out.println("GETETE");
                            sentence.get(i).mov = 1;
                            OPERAND1.add(sentence.get(i));
                            try {
                                if (sentence.get(i + 1).text.toString().equals(",")) {
                                    if (sentence.get(i + 2).type.equals("IDENTIFICATOR")) {
                                        sentence.get(i + 2).mov = 1;
                                        OPERAND2.add(sentence.get(i + 2));
                                    } else {
                                        ERROR.error.add(sentence.get(i).x);
                                    }
                                    if (sentence.get(i + 3).text.toString().equals("[")) {
                                        sentence.get(i + 3).mov = 1;
                                        OPERAND2.add(sentence.get(i + 3));
                                    } else {
                                        ERROR.error.add(sentence.get(i).x);
                                    }
                                    //if (sentence.get(i + 4).text.toString().equals("[")) {
                                    if (sentence.get(1).type.equals("REGISTER16")) {
                                        sentence.get(i + 4).mov = 1;
                                        OPERAND2.add(sentence.get(i + 4));
                                    }else {
                                        ERROR.error.add(sentence.get(i).x);
                                    }
                                    //}
                                    if (sentence.get(i + 5).text.toString().equals("]")) {
                                        sentence.get(i + 5).mov = 1;
                                        OPERAND2.add(sentence.get(i + 5));
                                    } else {
                                        ERROR.error.add(sentence.get(i).x);
                                    }
                                    oper_flag = true;
                                }else {
                                    ERROR.error.add(sentence.get(i).x);
                                }
                            } catch (Exception e) {
                                //e.printStackTrace();
                                ERROR.error.add(sentence.get(i).x);
                            }

                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                        ERROR.error.add(sentence.get(i).x);
                    }
                    try {
                        if (COMMAND.text.toString().equals("and") && sentence.get(i - 1).type.equals("COMMAND")) {
                            // System.out.println("***********");
                            sentence.get(i).and = 2;
                            OPERAND1.add(sentence.get(i));
                            if (sentence.get(i + 1).text.toString().equals(",")) {
                                ;
                            } else {
                                ERROR.error.add(sentence.get(i).x);
                            }
                            if (sentence.get(i + 2).type.equals("REGISTER16")) {
                                OPERAND2.add(sentence.get(i + 2));
                            } else {
                               // ERROR.error.add(sentence.get(i).x);
                            }
                        }
                    } catch (Exception e) {
                        ERROR.error.add(sentence.get(i).x);
                        //e.printStackTrace();
                    }

                    break;
            }
        }

            try {
                if (COMMAND!=null) {
                    if(COMMAND.text.toString().equals("not")&&OPERAND1.size() == 0){
                        ERROR.error.add(sentence.get(1).x);
                        System.out.println("SS");
                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();
                ERROR.error.add(sentence.get(0).x);
            }

        segment_non_stat = segment_stat;
    }

    public void SentenceSizer() {
        DATA data = new DATA();
        NON_STATIC_OFFSET = STATIC_OFFSET;
        String buf;
        if (DIRECTIVE != null) {
            buf = DIRECTIVE.text.toString();
            switch (buf) {
                case "segment":
                    local_offset = 0;
                    break;
                case "ends":
                    STATIC_OFFSET = 0;
                    break;
                case "end":
                    break;
                case "proc":
                    break;
                case "endp":
                    break;
                case "dd":
                    if (FINAL != null) {
                        if (FINAL.type.equals("HEX")) {
                            try {
                                command_byte += FINAL.text.charAt(FINAL.text.length() - 3);
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                            try {
                                command_byte += FINAL.text.charAt(FINAL.text.length() - 2);
                            } catch (Exception e) {
                                command_byte += "0";

                            }
                            try {
                                command_byte += FINAL.text.charAt(FINAL.text.length() - 5);
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                            try {
                                command_byte += FINAL.text.charAt(FINAL.text.length() - 4);
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                            try {
                                command_byte += FINAL.text.charAt(FINAL.text.length() - 7);
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                            try {
                                command_byte += FINAL.text.charAt(FINAL.text.length() - 6);
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                            try {
                                command_byte += FINAL.text.charAt(FINAL.text.length() - 8);
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                            try {
                                command_byte += FINAL.text.charAt(FINAL.text.length() - 9);
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                        }
                    }
                    local_offset += 4;
                    break;
                case "dw":
                    if (FINAL != null) {
                        if (FINAL.type.equals("HEX")) {
                            //System.out.println("KKKKKKKKKKKKKK");
                            try {
                                if (FINAL.text.toString().length() != 1) {
                                    command_byte += FINAL.text.charAt(0);
                                }else { }
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                            try {
                                if (FINAL.text.toString().length() != 2) {
                                    command_byte += FINAL.text.charAt(1);
                                }else { command_byte += "0";}
                            } catch (Exception e) {
                                if (FINAL.text.toString().length() != 2) {
                                    command_byte += "0";
                                }else { command_byte += "0";}
                            }
                            try {
                                if (FINAL.text.toString().length() != 3) {
                                    command_byte += FINAL.text.charAt(2);
                                }else { command_byte += "0";}
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                            try {
                                if (FINAL.text.toString().length() != 4) {
                                    command_byte += FINAL.text.charAt(3);
                                }else { command_byte += "0";}
                            } catch (Exception e) {
                                command_byte += "0";
                            }
                        }
                    }
                    local_offset += 2;
                    break;
                case "db":
                    if (FINAL != null) {
                        if (FINAL.type.equals("TEXT")) {
                            local_offset += FINAL.text.toString().length() - 2;
                            for (int i = 1; i < FINAL.text.toString().length() - 1; i++) {
                                command_byte += Integer.toHexString((int) FINAL.text.charAt(i)).toUpperCase() + " ";
                            }

                        }
                        if (FINAL != null) {
                            if (FINAL.type.equals("HEX")) {
                                //System.out.println("KKKKKKKKKKKKKK");
                                try {
                                    command_byte += FINAL.text.charAt(FINAL.text.length() - 3);
                                } catch (Exception e) {
                                    command_byte += "0";
                                }
                                try {
                                    command_byte += FINAL.text.charAt(FINAL.text.length() - 2);
                                } catch (Exception e) {
                                    command_byte += "0";
                                }
                            }
                        }
                        break;
                    }
                    local_offset += 1;
                    break;
            }
        }
        if (COMMAND != null) {
            buf = COMMAND.text.toString();
            //System.out.println(buf);
            switch (buf) {
                case "jna":
                    local_offset += 2;
                    command_byte = data.commands_size.get("jna");
                    mod_r_m_byte += " " + MOD_BYTE.Jna_dis(NON_STATIC_OFFSET, JNA_OP.text.toString(), JNA_OP);
                    break;
                case "not":
                    local_offset += 2;
                    try {
                        if (OPERAND1.get(0).not == 1) {
                            command_byte = data.commands_size.get("not_8");
                            mod_r_m_byte = " " + MOD_BYTE.createMOD_not(OPERAND1, 1).toUpperCase();
                        } else if (OPERAND1.get(0).not == 2) {
                            command_byte = data.commands_size.get("not_16");
                            mod_r_m_byte = " " + MOD_BYTE.createMOD_not(OPERAND1, 2).toUpperCase();
                        }
                    } catch (Exception e) {
                    //    e.printStackTrace();
                    }
                    // System.out.println(local_offset + " GOOOOOOOOOOOOOO");
                    break;
                case "mov":
                    // if(OPERAND1!=null&&OPERAND2!=null&&((OPERAND1.get(0).type == "REGISTER16"||OPERAND1.get(0).type == "REGISTER8")&&(OPERAND2.get(0).type == "REGISTER16"||OPERAND2.get(0).type == "REGISTER8"))){
                    //     local_offset+=2;
                    //  }
                    try {
                        if (OPERAND1.get(0).mov == 1) {
                            local_offset += 4;
                            if (OPERAND1.get(0).type.equals("REGISTER8")) {
                                command_byte = data.commands_size.get("mov_reg8_mem");
                            } else if (OPERAND1.get(0).type.equals("REGISTER16")) {
                                command_byte = data.commands_size.get("mov_reg16_mem");
                            }
                            mod_r_m_byte = " " + MOD_BYTE.createMOD_mov(OPERAND1, OPERAND2, 1).toUpperCase();
                            adress = " " + MOD_BYTE.adress(OPERAND2);

                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                    try {
                        if (OPERAND2.get(0).mov == 2) {
                            command_byte = data.commands_size.get("mov_mem_imm");
                            local_offset += 5;
                            mod_r_m_byte = " " + MOD_BYTE.createMOD_mov(OPERAND1, OPERAND2, 2).toUpperCase();
                            adress = " " + MOD_BYTE.adress(OPERAND1);
                            adress += " " + MOD_BYTE.offset_adress(OPERAND2.get(1).text.toString());

                        }
                    } catch (Exception e) {
                      //  e.printStackTrace();
                    }

                    break;
                case "ret":
                    command_byte = data.commands_size.get("ret");
                    local_offset += 1;
                    break;
                case "and":
                    if (OPERAND1.get(0).and == 1) {
                        command_byte = data.commands_size.get("and_8");
                        mod_r_m_byte = " " + MOD_BYTE.createMOD_and(OPERAND1, OPERAND2, 1).toUpperCase();
                    } else if (OPERAND1.get(0).and == 2) {
                        command_byte = data.commands_size.get("and_16");
                        mod_r_m_byte = " " + MOD_BYTE.createMOD_and(OPERAND1, OPERAND2, 2).toUpperCase();
                    }
                    local_offset += 2;
                    break;
                case "call":
                    if (COMMAND.ptr == true) {
                        command_byte = data.commands_size.get("call_far");
                        local_offset += 5;
                        CALL_OP = sentence.get(3);
                        break;
                    } else {
                        command_byte = data.commands_size.get("call");
                        local_offset += 3;
                        CALL_OP = sentence.get(1);
                    }
                    break;
            }
        }
        //ERROR.Redefinment_error2(getSentence(),getNON_STATIC_OFFSET());
        STATIC_OFFSET += local_offset;
        // System.out.println(Integer.toHexString(STATIC_OFFSET) + " ");
    }

    void SentenceStruct() {
        //  if(!command_byte.equals("")){
        struct += command_byte;
        // }
        // if(!mod_r_m_byte.equals("")){
        struct += mod_r_m_byte;
        struct += adress;
        // }

    }

    void mod() {

    }

    @Override
    protected Sentence clone() throws CloneNotSupportedException {
        return (Sentence) super.clone();
    }

    public Lexem getCOMMAND() {
        return COMMAND;
    }

    public Lexem getDIRECTIVE() {
        return DIRECTIVE;
    }

    public ArrayList<Lexem> getOPERAND1() {
        return OPERAND1;
    }

    public ArrayList<Lexem> getOPERAND2() {
        return OPERAND2;
    }

    public Lexem getLABEL() {
        return LABEL;
    }

    public Lexem getFINAL() {
        return FINAL;
    }

    public int getNON_STATIC_OFFSET() {
        return NON_STATIC_OFFSET;
    }

    public String setStruct(String s) {
        return struct += s;
    }

    public ArrayList<Lexem> getSentence() {
        return sentence;
    }

    public String getSegment_non_stat() {
        return segment_non_stat;
    }
}