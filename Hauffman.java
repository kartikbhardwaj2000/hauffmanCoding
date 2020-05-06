import java.util.HashMap;
import java.util.PriorityQueue;

public class Hauffman {
    TreeNode Superparent;
    HashMap<Character,Integer> freqMap;
    HashMap<Character,String> codeMap;
    HashMap<String,Character>reverseCodeMap;
    String word;
     

    Hauffman(String word)
    {
        codeMap=new HashMap<>();
        reverseCodeMap=new HashMap<>();
        this.word=word;
    }
    
    private void getReverseCode()
    {
        for(Character key :codeMap.keySet()){
          reverseCodeMap.put(codeMap.get(key), key);
        }
    }
    public String encode()
    {
        freqMap=getFreqMap(word);
        getTreeParent(freqMap);
        getCodes(Superparent, "", codeMap);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<word.length();i++)
        {
            char ch =word.charAt(i);
            stringBuilder.append(codeMap.get(ch));

        }
        String encoded =String.valueOf(stringBuilder);
        return encoded;
    }

    public String decode(String word)
    {
        StringBuilder stringBuilder =new StringBuilder();
        String code ="";
        getReverseCode();
        for(int i=0;i<word.length();i++)
        { 
           code =code+word.charAt(i);
           if(reverseCodeMap.containsKey(code))
           {
               stringBuilder.append(reverseCodeMap.get(code));
               code="";
           }
            
        }
        return String.valueOf(stringBuilder);
    }


    private  void getCodes(TreeNode root,String path,HashMap<Character,String> map)
    {
        if(root.left==null&&root.right==null)
        {
            map.put(root.data, path);
            return;
        }

        getCodes(root.left, path+"0", map);
        getCodes(root.right, path+"1", map);
    }

    public  void printCodes()
    {
     for(Character key:codeMap.keySet())
     {
            System.out.println("key is "+key+" code is "+codeMap.get(key));


     }   
    }


    private void getTreeParent(HashMap<Character,Integer> map){
        
        PriorityQueue<TreeNode> pq =new PriorityQueue<>();
        
        for(Character key:map.keySet()){
            TreeNode node =new TreeNode(map.get(key), key);
            pq.add(node);
        }
        //System.out.println(pq.peek().data);
        while(!pq.isEmpty())
        {
            if(pq.size()==1)
            {   Superparent=pq.poll();
                break;
            }
            TreeNode node1 =pq.poll();
            TreeNode node2 =pq.poll();
            int sumfreq=node1.freq+node2.freq;

            TreeNode parent =new TreeNode(sumfreq, '\0');
            parent.left=node1;
            parent.right=node2;
            pq.add(parent);
        }
    }

    private   HashMap<Character,Integer> getFreqMap(String word){
        HashMap<Character,Integer> map =new HashMap<>();
        for(int i=0;i<word.length();i++)
        {
            char ch =word.charAt(i);
            if(map.containsKey(ch))
            {
                int freq =map.get(ch);
                freq++;
                map.put(ch, freq);
            }else {
                map.put(ch, 1);
            }
        }
        return map;
    }
}
