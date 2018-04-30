import java.util.ArrayList;

public class Checker {

    static public boolean check_char(String atribute, char c) {
        boolean b = true;
        int value;
        switch (atribute) {
            case "all_symb":
                value = STRING_DATA.all_symb.indexOf(c);
                //System.out.println(value + "  ");
                if (value == -1) {
                    b = false;
                } else {
                    b = true;
                }
                // System.out.print("DD"+b);
                break;
            case "all_digit":
                value = STRING_DATA.all_digit.indexOf(c);
                if (value == -1) {
                    b = false;
                } else {
                    b = true;
                }
                break;
            case "all_eng":
                value = STRING_DATA.all_eng.indexOf(c);
                if (value == -1) {
                    b = false;
                } else {
                    b = true;
                }
                break;
            case "devider":
                value = STRING_DATA.divider.indexOf(c);
                if (value == -1) {
                    b = false;
                } else {
                    b = true;
                }
                break;
            case "all_oper":
                value = STRING_DATA.all_oper.indexOf(c);
                if (value == -1) {
                    b = false;
                } else {
                    b = true;
                }
                break;
        }
        return b;
    }

    static boolean checker_data(String atribute, String c) {
        DATA data = new DATA();
        ArrayList<String> str;
        str = new ArrayList<String>();
        switch (atribute) {
            case "directives":
                str = data.source.get("directives");
                if (str.contains(c)) {
                    return true;
                }
                return false;

            case "commands":
                str = data.source.get("commands");
                if (str.contains(c)) {
                    return true;
                }
                return false;

            case "registers8":
                str = data.source.get("registers8");
                if (str.contains(c)) {
                    return true;
                }
                return false;

            case "register16":
                str = data.source.get("register16");
                if (str.contains(c)) {
                    return true;
                }
                return false;

        }
        return false;
    }

}