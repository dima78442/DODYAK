import java.util.ArrayList;
import java.util.Map;

public class MOD_BYTE {
    static String createMOD_mov(ArrayList<Lexem> op1, ArrayList<Lexem> op2, int mov_type) {
        DATA data = new DATA();
        if (mov_type == 1) {
            String mod = "10";
            String _op1 = data.reg.get(op1.get(0).text.toString());
            String _op2 = data.reg16.get(op2.get(2).text.toString());
            // System.out.println(op1.get(0).text.toString());
            // System.out.println(op2.get(2).text.toString());
            //  System.out.println(mod + _op1 + _op2);
            return binaryToHex(mod + _op1 + _op2);
        } else if (mov_type == 2) {
            String mod = "10";
            String _op1 = data.reg16.get(op1.get(2).text.toString());
            return binaryToHex(mod + "000" + _op1);
        }
        return "NOT WORKING";
    }

    public static String createMOD_and(ArrayList<Lexem> op1, ArrayList<Lexem> op2, int and_type) {
        String mod = "11";
        DATA data = new DATA();
        if (and_type == 1) {
            String _op1 = data.reg.get(op1.get(0).text.toString());
            String _op2 = data.reg.get(op2.get(0).text.toString());
            return binaryToHex(mod + _op1 + _op2);
        } else if (and_type == 2) {
            String _op1 = data.reg.get(op1.get(0).text.toString());
            String _op2 = data.reg.get(op2.get(0).text.toString());
            return binaryToHex(mod + _op1 + _op2);
        }
        return "NOT WORKING";
    }

    public static String createMOD_not(ArrayList<Lexem> op1, int not_type) {
        String mod = "11";
        DATA data = new DATA();
        if (not_type == 1) {
            String _op1 = data.reg.get(op1.get(0).text.toString());
            return binaryToHex(mod + "010" + _op1);
        } else if (not_type == 2) {
            String _op1 = data.reg.get(op1.get(0).text.toString());
            return binaryToHex(mod + "010" + _op1);
        }
        return "NOT WORKING";
    }

    private static String binaryToHex(String bin) {
        try {
            return Long.toHexString(Long.parseLong(bin, 2));
        } catch (NumberFormatException e) {
            return "";
        }

    }

    public static String adress(ArrayList<Lexem> op) {
        String s = "";
        for (int i = 0; i < op.size(); i++) {
            if (op.get(i).type.equals("IDENTIFICATOR")) {
                for (Map.Entry<String, Integer> entry : DATA.offset.entrySet()) {
                    if (op.get(i).text.toString().equals(entry.getKey().toString())) {
                        s = entry.getValue().toString();
                        //System.out.println(entry.getValue().toString() + entry.getKey().toString());
                    }
                }
            }
        }
        return File_analyze.formHEX(s) + " R ";
    }

    public static String Jna_dis(int offset, String label, Lexem op) {
        String disp = "";
        String s = "";
        int i = 0;
        for (Map.Entry tmp : DATA.offset.entrySet()) {
            if (tmp.getKey().equals(label)) {
                i = Integer.parseInt(tmp.getValue().toString()) - offset - 2;
                s = Integer.toHexString(i).toUpperCase();
                op.label = true;
                if (i <= 0 && i >= -128) {
                    return s.substring(s.length() - 2, s.length());
                }
            }
        }
        return disp;
    }

    public static void Jna_dis2(ArrayList<Sentence> _sentences) {
        int i = 0;
        String string = "";
        for (Sentence s : _sentences) {
            if (s.JNA_OP != null) {
                if (s.JNA_OP.label == false) {
                    for (Map.Entry tmp : DATA.offset.entrySet()) {
                        if (tmp.getKey().equals(s.JNA_OP.text.toString())) {
                            i = Integer.valueOf((Integer) tmp.getValue()) - s.getNON_STATIC_OFFSET() - 2;
                            string = Integer.toHexString(i).toUpperCase();
                            if (string.length() == 1) {
                                string = "0" + string;
                            }
                            if (i >= 0 && i <= 127) {
                                s.struct += string;
                            } else {
                                s.struct += "00";
                                ERROR.error.add(s.JNA_OP.x);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void call_adress(ArrayList<Sentence> _sentences) {
        for (Sentence sentence : _sentences) {
            if (sentence.getCOMMAND() != null) {
                if (sentence.getCOMMAND().text.toString().equals("call")) {
                    for (Map.Entry<String, Offset_Seg> tmp : List_table.label_table.entrySet()) {
                        if (tmp.getKey().equals(sentence.CALL_OP.text.toString())) {
                            if (sentence.getCOMMAND().ptr == true) {
                                sentence.struct += " " + File_analyze.formHEX(Integer.toHexString((Integer) tmp.getValue().offset).toUpperCase()) + " ---- R";
                            } else {
                                sentence.struct += " " + File_analyze.formHEX(Integer.toHexString((Integer) tmp.getValue().offset).toUpperCase()) + " R";
                            }
                        }
                    }
                }
            }
        }
    }

    public static String offset_adress(String _imm) {
        String s = "00";
        for (Map.Entry tmp : DATA.offset.entrySet()) {
            if (_imm.equals(tmp.getKey().toString())) {
                //System.out.println("@@@@@@@");
                s = Integer.toHexString((Integer) tmp.getValue());
                if (s.length() > 2) {
                    // ERROR.error.add();
                }
                if (s.length() == 1) {
                    s = "0" + s;
                    return s.toUpperCase();
                }
                return s.toUpperCase();
            }
        }
        return s;
    }

    public static String adress_data(ArrayList<Sentence> _sentences) {
        String buf = "";
        for (Sentence sentence : _sentences) {

            if (sentence.getDIRECTIVE() != null) {
                if (sentence.getDIRECTIVE().text.toString().equals("dd") || sentence.getDIRECTIVE().text.toString().equals("dw")) {
                    for (Map.Entry<String, Offset_Seg> tmp : List_table.label_table.entrySet()) {
                        if (sentence.getSentence().get(2).type.equals("IDENTIFICATOR")) {
                            // System.out.println("WORKINg");
                            //System.out.println(sentence.getSentence().get(2).text.toString());
                            // System.out.println("SSS" + tmp.getKey());
                            if (tmp.getKey().equals(sentence.getSentence().get(2).text.toString())) {
                                //System.out.println("WORKINg");
                                if (tmp.getValue().seg_name.equals(sentence.getSegment_non_stat())) {
                                    if (sentence.getSentence().get(1).text.toString().equals("dd")) {
                                        buf = " ---- R";
                                    } else if (sentence.getSentence().get(1).text.toString().equals("dw")) {
                                        buf = " R";
                                    } else {
                                        //err
                                    }
                                    sentence.setStruct(File_analyze.formHEX(Integer.toHexString(tmp.getValue().offset)) + buf);
                                    // System.out.println(File_analyze.formHEX(Integer.toHexString(tmp.getValue().offset)) + buf);
                                } else {
                                    //err
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }
}
