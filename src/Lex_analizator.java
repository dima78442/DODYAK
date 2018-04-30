

import java.util.ArrayList;

public class Lex_analizator {

    private ArrayList<String> strings;
    private ArrayList<Lexem> lexems;
    private ArrayList<Lexem> lexems_type;

    Lex_analizator() {
        File_analyze file_analyze = new File_analyze("/Users/dima/Desktop/Test.asm");
        strings = file_analyze.getStrings();
        Lexem_creator();
        Lexem_type_adder();
    }

    private void Lexem_creator() {
        DATA data = new DATA();
        Lexem lexem = new Lexem();
        lexems = new ArrayList<Lexem>();
        Checker checker = new Checker();
        lexem.x = 0;
        lexem.y = 0;
        int z = 0;
        int i = 0;
        int j;
        int f = 0;
        for (String s : strings) {
            //lexem.text.delete(0, Integer.MAX_VALUE);
            lexem = new Lexem();
            z++;
            j = 0;
            f = 0;
            for (j = 0; j < s.length(); j++) {

                if ((checker.check_char("all_symb", s.charAt(j))) == false) {
                    //  System.out.println("err" + checker.check_char("all_symb", s.charAt(j)));
                    lexem.text.append(s.charAt(j));
                } else {
                    //  System.out.println("FF" + "HH");

                    if (checker.check_char("devider", s.charAt(j))) {
                        if (!(lexem.text.equals(""))) {
                            lexem.x = z;
                            lexem.y = f;
                            f++;
                            lexems.add(i, lexem);
                            i++;
                            // System.out.println(lexem.text + "SS");
                            //System.out.println(lexems.get(0).text + "DD");
                        }
                        //lexem.text.delete(0, Integer.MAX_VALUE);
                        lexem = new Lexem();
                    } else if (checker.check_char("all_oper", s.charAt(j))) {
                        if (!(lexem.text.equals(""))) {
                            lexem.x = z;
                            lexem.y = f;
                            f++;
                            lexems.add(i, lexem);
                            i++;
                            //  System.out.println(lexem.text + "DD");
                            // lexem.text.delete(0, Integer.MAX_VALUE);
                            lexem = new Lexem();
                        }
                        lexem.text.append(s.charAt(j));
                        lexem.x = z;
                        lexem.y = f;
                        f++;
                        lexems.add(lexem);
                        i++;
                        //  System.out.println(lexem.text + "DD");
                        // System.out.println(lexem.text + "DD");
                        //lexem.text.delete(0, Integer.MAX_VALUE);
                        lexem = new Lexem();
                    } else {
                        lexem.text.append(s.charAt(j));
                        // System.out.println(lexem.text + "VV");
                    }
                }
            }
            if (!(lexem.text.equals(""))) {
                lexem.x = z;
                lexem.y = f;
                f++;
                lexems.add(i, lexem);
                i++;
                // lexem.text.delete(0, Integer.MAX_VALUE);
            }
        }
        // System.out.print(lexems.get(1).text + "F");
    }

    void Lexem_type_adder() {
        lexems_type = new ArrayList<Lexem>();
        lexems_type = lexems;
        Checker checker = new Checker();
        Lexem typer = new Lexem();
        int i = 0;
        for (Lexem lexem : lexems_type) {
            if (checker.checker_data("register16", lexem.text.toString())) {
                lexems_type.get(i).type = "REGISTER16";
                i++;
            } else if (checker.checker_data("registers8", lexem.text.toString())) {
                lexems_type.get(i).type = "REGISTER8";
                i++;
            } else if (checker.checker_data("commands", lexem.text.toString())) {
                lexems_type.get(i).type = "COMMAND";
                i++;
            } else if (checker.checker_data("directives", lexem.text.toString())) {
                lexems_type.get(i).type = "DIRECTIVE";
                i++;
            } else if ((lexem.text.toString().length() > 1) && (checker.check_char("all_digit", lexem.text.charAt(0))) && (lexem.text.charAt(lexem.text.length() - 1) == 'h')) {
                lexems_type.get(i).type = "HEX";
                i++;
            } else if ((lexem.text.toString().length() > 1) && (lexem.text.charAt(0) == '"') && (lexem.text.charAt(lexem.text.length() - 1) == '"')) {
                lexems_type.get(i).type = "TEXT";
                i++;
            } else if (lexem.text.length() == 1) {
                if (checker.check_char("all_oper", lexem.text.charAt(0))) {
                    lexems_type.get(i).type = "SINGLE";
                    i++;
                } else {
                    lexems_type.get(i).type = "IDENTIFICATOR";
                    i++;
                    if (lexems_type.get(i).text.toString().length() > 6) {
                        ERROR.error.add(lexems_type.get(i).x);
                    }
                }
            } else {
                lexems_type.get(i).type = "IDENTIFICATOR";
                if (lexems_type.get(i).text.toString().length() > 6) {
                    ERROR.error.add(lexems_type.get(i).x);
                }
                i++;
            }
        }
        int k = 0;

        while (k < i) {
            if (lexems_type.get(k).text.toString().equals("")) {
                lexems_type.remove(k);
                i--;
            }
            k++;
        }
    }

    /*@Override
    public void show(){
    int d = 0;
    int z = 0;
    boolean b = false;
    ArrayList<Lexem> buf = getLexems_type();
    String s;
        System.out.printf("|%3s| |%8s| |%11s| |%2s|\n","num","lexem","type","size");
        for (int i = 0; i < buf.size(); i++) {
            d++;
            try {
                if(buf.get(i).x == buf.get(i + 1).x)
                {
                    if(b == false){
                        System.out.println("----" + strings.get(z));
                        z++;
                        b = true;
                    }
                   // System.out.println(d + " " + buf.get(i).text.toString() + " " + buf.get(i).type + " " +  buf.get(i).text.length() );
                    System.out.printf("%5d %10s %13s %4d %4d\n",d,buf.get(i).text.toString(),buf.get(i).type,buf.get(i).text.length(),buf.get(i).x);
                }
                else {
                    if(buf.get(i).text.toString().equals("ret")){
                        System.out.println("----" + strings.get(z));
                        z++;
                    }
                    //System.out.println(d + " " + buf.get(i).text.toString() + " " + buf.get(i).type + " " + buf.get(i).text.length());
                    System.out.printf("%5d %10s %13s %4d %4d\n",d,buf.get(i).text.toString(),buf.get(i).type,buf.get(i).text.length(),buf.get(i).x);
                    b = false;
                }
            } catch (Exception e) {
                if(buf.get(i).text.toString().equals("end")){
                    System.out.println("----" + strings.get(z));
                    z++;
                }
                //System.out.println(d + " " + buf.get(i).text.toString() + " " + buf.get(i).type + " " + buf.get(i).text.length());
                System.out.printf("%5d %10s %13s %4d %4d\n",d,buf.get(i).text.toString(),buf.get(i).type,buf.get(i).text.length(),buf.get(i).x);
            }
        }
    }*/

    public ArrayList<Lexem> getLexems() {
        return lexems;
    }

    public ArrayList<Lexem> getLexems_type() {
        return lexems_type;
    }

    public ArrayList<String> getStrings() {
        return strings;
    }
}

interface View {
    void show();
}