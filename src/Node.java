/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Niranjan Patil
 */
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
public class Node {
    public int[][] posArr;
    
    public int[][] Board;
    public String move=null;
    public Integer score=0;
    public Node(int N, int d)
    {
        this.Board = new int[N][N];

        this.posArr = new int[d][2];
        for(int x=0;x<d;x++)
            this.posArr[x][0] = -1;
    }
    
}
