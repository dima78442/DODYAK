public class Lexem implements Cloneable{
    Lexem(){
    }
    public StringBuilder text = new StringBuilder("");
    int x;
    int y;
    String type;
    boolean ptr = false;
    int mov = 0;
    int not = 0;
    int and = 0;
    boolean label = false;
    @Override
    protected Lexem clone() throws CloneNotSupportedException {
        return (Lexem)super.clone();
    }
}
