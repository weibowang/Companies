package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class FindNearestLockerInTheCity {
    
    static public class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Another method in http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=194035&extra=page%3D5%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D5%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
    public static void main(String[] args) {
        int m = 7; 
        int n = 5;
        int[] xArrays = {3,7};
        int[] yArrays = {2,4};
        int[][] matrix = get2DLocation(m, n, xArrays, yArrays);
        System.out.println(Arrays.deepToString(get2DLocation(m, n, xArrays, yArrays)));
    }
    
    public static int[][] get2DLocation(int m, int n, int[] xArrays, int[] yArrays) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -1;
            }
        }
        
        Queue<Node> q = new LinkedList<Node>();
        
        for (int i = 0; i < xArrays.length; i++) {
            Node node = new Node(xArrays[i] - 1, yArrays[i] - 1);
            matrix[xArrays[i] - 1][yArrays[i] - 1] = 0;
            q.add(node);
        }
        
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                ArrayList<Node> list = getNeighbors(m, n, node);
                for (int j = 0; j < list.size(); j++) {
                    Node current = list.get(j);
                    if (matrix[current.x][current.y] == -1) {
                        matrix[current.x][current.y] = level;
                        q.add(current);
                    }
                }
            }
            level++;
        }
        return matrix;
    }
    
    public static ArrayList<Node> getNeighbors(int m, int n, Node node) {
        ArrayList<Node> array = new ArrayList<Node>();
        int x = node.x;
        int y = node.y;
        if (x - 1 >= 0) {
            Node current = new Node(x - 1, y);
            array.add(current);
        }
        if (x + 1 < m) {
            Node current = new Node(x + 1, y);
            array.add(current);
        }
        if (y - 1 >= 0) {
            Node current = new Node(x, y - 1);
            array.add(current);
        }
        if (y + 1 < n) {
            Node current = new Node(x, y + 1);
            array.add(current);
        }
        return array;
    }
}
