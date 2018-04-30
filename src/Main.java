public class Main {

    public static void main(String[] args) throws Exception{

       // System.out.println("Hello World!");
      //  File_analyze fw = new File_analyze("/Users/dima/Desktop/Test.asm");
        Lex_analizator lex_analizator = new Lex_analizator();
        Lexem lexem;

       // lexem.text.append('c');
//lex_analizator.lexems.add(lexem);
       // int i = 0;
        //String s1 = "a";
        //while (i != lex_analizator.getLexems_type().size()) {
        //    lexem = lex_analizator.getLexems_type().get(i);
        //    if(lex_analizator.getLexems_type().get(i).text.toString().equals("]")){
        //        s1 = lex_analizator.getLexems_type().get(i + 1).text.toString();
        //    }
        //    System.out.println(lexem.text + " type: " + lexem.type + " row: " + lexem.x);
       //     i++;
      //  }
      //  System.out.println("SHOWWWW");
        //int i = 0;
//        while (!lex_analizator.getLexems().isEmpty()){
//            System.out.println(lex_analizator.getLexems().get(i).text.toString());
   //         i++;
     //   }


       // lex_analizator.show();



       // "/Users/dima/Desktop/Test.asm"
      //  File_analyze file_analyze = new File_analyze("Test.asm");
      //  ArrayList<String> fw = file_analyze.getStrings();

      //  for (String s: fw) {
      //      System.out.println(s + "N");
       // }
       // String s = "dasf";
     //  int value = STRING_DATA.all_symb.indexOf(s.charAt(2));
      //  System.out.println(value + "N");
       // System.out.println(s1.equals("") + "a");
     //   StringBuilder stringBuilder = new StringBuilder();
        Syntax_Analyzer syntax_analyzer = new Syntax_Analyzer(new Lex_analizator());
        syntax_analyzer.show();
        ERROR.showError();
       // System.out.println(DATA.offset.get("P1").toString());
       /* for(Map.Entry<String,Integer> entry: DATA.offset.entrySet()){
            System.out.println("next");
            System.out.println(entry.getKey().toString() +" " +entry.getValue().toString() + "\n");*/
        }
       // System.out.println("NAME");
      /*  for (Map.Entry<String,Offset_Seg> tmp :List_table.label_table.entrySet()) {
            System.out.println(" " + tmp.getKey() + " " + " " +Integer.toHexString(tmp.getValue().offset));
        }

        System.out.println("\n" + List_table.label_table.get("P2").offset +" "+ DATA.offset.get("P22"));*/
}
