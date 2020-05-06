
public class HaufManUse {
    
    public static void main(String[] args) {
   
    Hauffman hauffman =new Hauffman("kartik is great man");
    String encoded =hauffman.encode();
    System.out.println(encoded);
    hauffman.printCodes();
    System.out.println(hauffman.decode(encoded));


     }

      
}