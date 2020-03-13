import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static final String REFERENCE_PREFIX = "$";
    private Map<Integer, Node> nodes = new HashMap<>();
    
    void parse() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String operation = in.next();
            String arg1 = in.next();
            String arg2 = in.next();
            Node node = new Node(operation, arg1, arg2);
            nodes.put(i, node);
        }
    }
    
    void solve() {
        for(Integer key : nodes.keySet()){
            Node node = nodes.get(key);
            System.out.println(getNodeResult(node));
        }
    }
    
    int getNodeResult(Node node) {
        if(!node.calculated) {
            calculateNode(node);
        }
        return node.result;
    }
    
    void calculateNode(Node node) {
        int result = getArgValue(node.arg1);
        switch (node.operation) {
            case "ADD" :
                result += getArgValue(node.arg2);
                break;
            case "SUB" :
                result -= getArgValue(node.arg2);
                break;
            case "MULT" :
                result *= getArgValue(node.arg2);
                break;
        }
        updateNode(node, result);
    }
    
    void updateNode(Node node, int result){
        node.result = result;
        node.calculated = true;
    }
    
    int getArgValue(String arg) {
        if(arg.startsWith(REFERENCE_PREFIX)) {
            int ref = Integer.parseInt(arg.substring(1));
            Node node = nodes.get(ref);
            return getNodeResult(node);
        } else {
            return Integer.parseInt(arg);
        }
    }
    
    public static void main(String args[]) {
        Solution solution = new Solution();
        solution.parse();
        solution.solve();
    }
    
    class Node {
        String operation;
        String arg1;
        String arg2;
        int result;
        boolean calculated;
        
        Node(String operation, String arg1, String arg2) {
            this.operation = operation;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }
    }

}
