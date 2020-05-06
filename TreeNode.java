
public class TreeNode implements Comparable<TreeNode>{
    
    int freq;
    char data;
    TreeNode left;
    TreeNode right;
    TreeNode(int freq,char data){
        this.freq=freq;
        this.data=data;
    }
    @Override
    public int compareTo(TreeNode o) {
        if(this.freq<o.freq)
        {
            return -1;
        }else if(this.freq>o.freq)
        {
            return 1;
        }else
        return 0;
    }


}