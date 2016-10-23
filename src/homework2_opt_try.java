
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Niranjan Patil
 */
public class homework2_opt_try {

    static int total = 0;
    static int N;
    static String Mode;
    static int player;
    static int opp;
    static int depth;
    static int[][] scoreArr;
    static int iniPos;
    static int[][] iniBoard;
    static Node Root;
    static int cnt;
    static String fo1 = "C:\\Users\\Niranjan Patil\\Documents\\NetBeansProjects\\AI_USC_HW2\\testcases-5000\\OUTPUT\\";
    static String fo2 = "C:\\Users\\Niranjan Patil\\Documents\\NetBeansProjects\\AI_USC_HW2\\MyOP\\";
    static int cx=0;
    static String nx;
    
    
    public static void check() throws Exception     //All 165 Inputs 
    {
        String stro = null;
        String stro1 = null;
        
        
    
        BufferedReader bro = new BufferedReader(new FileReader(fo1+""+cnt+".out"));
       BufferedReader bro1 = new BufferedReader(new FileReader(fo2+"output"+cnt+".txt"));
       //stro = bro.readLine();
       //stro1 = bro1.readLine();
        System.out.println(cnt+ " ");
        while((stro = bro.readLine())!=null)
        {
            
            stro1 = bro1.readLine();
            if(stro.equals(stro1))
            {
                //System.out.println();
            }
            else if(!stro.equals(stro1))
            {
                //cx++;
                System.out.println(" test case failed!!!");
                break;
            }
        }
    
    }

    
    
    

    public static void main(String[] args) throws Exception {

        
        //for(cnt=1;cnt<5000;cnt++){
        
            
        Root = null;
        iniPos = 0;
        
        readIn();
        
        
        
        //System.out.println(iniPos);
        if((N*N-iniPos) == 0){
            //System.out.println(iniPos);
            nx = "A1 null";
            printl(Root);
            
        }
        else{
        Integer alpha = Integer.MIN_VALUE;
        Integer beta = Integer.MAX_VALUE;
      
        String m = null;
        Node ans = null;
        if (Mode.equals("MINIMAX")) {
            ans = MiniMax(depth, Root);
        } else if (Mode.equals("ALPHABETA")) {
            ans = AlphaBeta(depth, Root,alpha,beta);
        }
        printl(ans);
        }
        //check();
            
        //}
        
        
    }
    
    public static void printl(Node ans) throws Exception
    {
        //File f = new File(fo2+"output"+cnt+".txt");
        File f = new File("output.txt");
        if (f.exists()) {
            f.delete();
        }
        FileWriter wr = new FileWriter(f);
        
        int j = 0;
        if((N*N-iniPos) == 0){
            //System.out.println(iniPos);
            wr.write(nx);
        }
        else{
            wr.write(ans.move + "\n");
        for (int i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                if (ans.Board[i][j] == 1) {
                    //System.out.print('X');
                    wr.write('X');
                } else if (ans.Board[i][j] == 0) {
                    //System.out.print('O');
                    wr.write('O');
                } else if (ans.Board[i][j] == 2) {
                    //System.out.print('.');
                    wr.write('.');
                }
            }
            if (i != N - 1 && j != N - 1) {
                wr.write("\n");
            }
        }
        }

        wr.flush();
        wr.close();

    
    
    }
    
    
    public static Node AlphaBeta(int dep, Node curNode,int alpha,int beta) {

        List<Node> List = null;

        if (dep == 0) {
            curNode = Evaluate(curNode);
            return curNode;
        }

        if ((depth - dep) % 2 == 0) {
            List = genChildrenNodes(dep, curNode, player, opp);
        } else if ((depth - dep) % 2 == 1) {
            List = genChildrenNodes(dep, curNode, opp, player);
        }

        if ((depth - dep) % 2 == 0) {
            Node bestScN = new Node(N, (depth + iniPos));
            bestScN.score = alpha;
            bestScN.move="";
            int c1 = 0;
            while (c1 < List.size()) {
                Node N1 = new Node(N, (depth + iniPos));
                N1 = AlphaBeta(dep - 1, List.get(c1),alpha,beta);
                List.get(c1).score = N1.score;
                //List.get(c1).move = N1.move;
                //total++;
                //System.out.println("total:"+total);
                if (List.get(c1).score > bestScN.score) {
                    alpha = List.get(c1).score;
                    bestScN.score = List.get(c1).score;
                    bestScN.move = List.get(c1).move;
                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < N; y++) {
                            bestScN.Board[x][y] = List.get(c1).Board[x][y];
                        }
                    }

                    for (int x = 0; x < (depth + iniPos); x++) {
                        for (int y = 0; y < 2; y++) {
                            bestScN.posArr[x][y] = List.get(c1).posArr[x][y];
                        }
                    }
                } else if (List.get(c1).score == bestScN.score) {
                    if (List.get(c1).move.contains("Stake") && bestScN.move.contains("Raid")) {
                        alpha = List.get(c1).score;
                        bestScN.score = List.get(c1).score;
                        bestScN.move = List.get(c1).move;
                        for (int x = 0; x < N; x++) {
                            for (int y = 0; y < N; y++) {
                                bestScN.Board[x][y] = List.get(c1).Board[x][y];
                            }
                        }

                        for (int x = 0; x < (depth + iniPos); x++) {
                            for (int y = 0; y < 2; y++) {
                                bestScN.posArr[x][y] = List.get(c1).posArr[x][y];
                            }
                        }
                    }
                }
                if(alpha >= beta){
                    /*
                        System.out.println("alpha: "+alpha);
                            System.out.println("beta: "+beta);*/
                    //total++;
                    //System.out.println(total+"Pruning\n");
                    break;
                }
                c1++;
                
            }

            return bestScN;
        } else if ((depth - dep) % 2 == 1) {
            Node bestScP = new Node(N, (depth + iniPos));
            bestScP.score = beta;
            bestScP.move = "";
            int c2 = 0;
            int t = 0;
            while (c2 < List.size()) {
                Node N2 = new Node(N, (depth + iniPos));
                N2 = AlphaBeta(dep - 1, List.get(c2),alpha,beta);
                List.get(c2).score = N2.score;
                //List.get(c2).move = N2.move;
                //total++;
                //System.out.println("total:"+total);
                if (List.get(c2).score < bestScP.score) {
                    beta = List.get(c2).score;
                    bestScP.score = List.get(c2).score;
                    bestScP.move = List.get(c2).move;
                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < N; y++) {
                            bestScP.Board[x][y] = List.get(c2).Board[x][y];
                        }
                    }

                    for (int x = 0; x < (depth + iniPos); x++) {
                        for (int y = 0; y < 2; y++) {
                            bestScP.posArr[x][y] = List.get(c2).posArr[x][y];
                        }
                    }

                } else if (List.get(c2).score == bestScP.score) {
                    if (List.get(c2).move.contains("Stake") && bestScP.move.contains("Raid")) {
                        beta = List.get(c2).score;
                        bestScP.score = List.get(c2).score;
                        bestScP.move = List.get(c2).move;
                        for (int x = 0; x < N; x++) {
                            for (int y = 0; y < N; y++) {
                                bestScP.Board[x][y] = List.get(c2).Board[x][y];
                            }
                        }

                        for (int x = 0; x < (depth + iniPos); x++) {
                            for (int y = 0; y < 2; y++) {
                                bestScP.posArr[x][y] = List.get(c2).posArr[x][y];
                            }
                        }
                    }
                }
                if(alpha>=beta){
                    /*System.out.println("alpha: "+alpha);
                    System.out.println("beta: "+beta);*/
                    //total++;
                    //System.out.println(total+"Pruning\n");
                    break;
                }
                c2++;
                
            }
            
            return bestScP;
        }

        return null;
    }
    
    
    
    
    

    public static Node MiniMax(int dep, Node curNode) {

        List<Node> List = null;
        //int dp = N*N-iniPos;
        if (dep == 0) {   
            curNode = Evaluate(curNode);
            return curNode;
        }

        if ((depth - dep) % 2 == 0) {
            List = genChildrenNodes(dep, curNode, player, opp);
        } else if ((depth - dep) % 2 == 1) {
            List = genChildrenNodes(dep, curNode, opp, player);
        }

        if ((depth - dep) % 2 == 0) {
            Node bestScN = new Node(N, (depth + iniPos));
            bestScN.score = Integer.MIN_VALUE;
            int c1 = 0;
            while (c1 < List.size()) {
                Node N1 = new Node(N, (depth + iniPos));
                N1 = MiniMax(dep - 1, List.get(c1));
                List.get(c1).score = N1.score;

                if (List.get(c1).score > bestScN.score) {
                    bestScN.score = List.get(c1).score;
                    bestScN.move = List.get(c1).move;
                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < N; y++) {
                            bestScN.Board[x][y] = List.get(c1).Board[x][y];
                        }
                    }

                    for (int x = 0; x < (depth + iniPos); x++) {
                        for (int y = 0; y < 2; y++) {
                            bestScN.posArr[x][y] = List.get(c1).posArr[x][y];
                        }
                    }
                } else if (List.get(c1).score == bestScN.score) {
                    //System.out.println(cnt);
                    if (List.get(c1).move.contains("Stake") && bestScN.move.contains("Raid")) {
                        bestScN.score = List.get(c1).score;
                        bestScN.move = List.get(c1).move;
                        for (int x = 0; x < N; x++) {
                            for (int y = 0; y < N; y++) {
                                bestScN.Board[x][y] = List.get(c1).Board[x][y];
                            }
                        }

                        for (int x = 0; x < (depth + iniPos); x++) {
                            for (int y = 0; y < 2; y++) {
                                bestScN.posArr[x][y] = List.get(c1).posArr[x][y];
                            }
                        }
                    }
                }
                c1++;
            }

            return bestScN;
        } else if ((depth - dep) % 2 == 1) {
            Node bestScP = new Node(N, (depth + iniPos));
            bestScP.score = Integer.MAX_VALUE;
            int c2 = 0;
            int t = 0;
            while (c2 < List.size()) {
                Node N2 = new Node(N, (depth + iniPos));
                N2 = MiniMax(dep - 1, List.get(c2));
                List.get(c2).score = N2.score;

                if (List.get(c2).score < bestScP.score) {
                    bestScP.score = List.get(c2).score;
                    //
                    bestScP.move = List.get(c2).move;
                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < N; y++) {
                            bestScP.Board[x][y] = List.get(c2).Board[x][y];
                        }
                    }

                    for (int x = 0; x < (depth + iniPos); x++) {
                        for (int y = 0; y < 2; y++) {
                            bestScP.posArr[x][y] = List.get(c2).posArr[x][y];
                        }
                    }

                } else if (List.get(c2).score == bestScP.score) {
                    if (List.get(c2).move.contains("Stake") && bestScP.move.contains("Raid")) {
                        bestScP.score = List.get(c2).score;
                        bestScP.move = List.get(c2).move;
                        for (int x = 0; x < N; x++) {
                            for (int y = 0; y < N; y++) {
                                bestScP.Board[x][y] = List.get(c2).Board[x][y];
                            }
                        }

                        for (int x = 0; x < (depth + iniPos); x++) {
                            for (int y = 0; y < 2; y++) {
                                bestScP.posArr[x][y] = List.get(c2).posArr[x][y];
                            }
                        }
                    }
                }
                c2++;
            }
            return bestScP;
        }
        return null;
    }

    public static Node Evaluate(Node curNode) {
        int score = 0;
        for (int i = 0; i < (depth + iniPos); i++) {
            if (curNode.Board[curNode.posArr[i][0]][curNode.posArr[i][1]] == player) {
                score += scoreArr[curNode.posArr[i][0]][curNode.posArr[i][1]];
            } else if (curNode.Board[curNode.posArr[i][0]][curNode.posArr[i][1]] == opp) {
                score -= scoreArr[curNode.posArr[i][0]][curNode.posArr[i][1]];
            }
        }
        curNode.score = score;

        return curNode;
    }

    public static List<Node> genChildrenNodes(int dep, Node curNode, int play, int op) {
        List<Node> Children = new ArrayList<Node>();
        String move = null;
        int[][] tempBoard;
        int[][] tempposArr;

      for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {

                tempBoard = new int[N][N];
                tempposArr = new int[(depth + iniPos)][2];  
        
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        tempBoard[x][y] = curNode.Board[x][y];
                    }
                }

                for (int x = 0; x < (depth + iniPos); x++) {
                    for (int y = 0; y < 2; y++) {
                        tempposArr[x][y] = curNode.posArr[x][y];
                    }
                }
                
                if (tempBoard[row][col] == 2) {
                    //System.out.println("In For For: "+x);
                    for (int x = 0; x < (depth + iniPos); x++) {
                        if (tempposArr[x][0] == -1) {
                            tempposArr[x][0] = row;
                            tempposArr[x][1] = col;
                            break;
                        }
                    }
                    tempBoard[row][col] = play;
                    int flag = 0;
                    move = (char) (col + 65) + "" + (row + 1) + " Stake";
                    /*
                     for(int xy=0;xy<(depth+iniPos);xy++)
                     System.out.println(tempposArr[xy][0]+" "+ tempposArr[xy][1]);
                     */
                    Node tem = new Node(N, (depth + iniPos));
                    tem.Board = tempBoard;
                    tem.move = move;
                    tem.posArr = tempposArr;
                    //       total++;
                    //System.out.println(total+" : "+ tem.move);
                    
                    
                    Children.add(tem);

                }
        
    }}
        
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {

                tempBoard = new int[N][N];
                tempposArr = new int[(depth + iniPos)][2];
                
                //Raid
                
        
                
                
                
                
                
                
                // Raid
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        tempBoard[x][y] = curNode.Board[x][y];
                    }
                }

                for (int x = 0; x < (depth + iniPos); x++) {
                    for (int y = 0; y < 2; y++) {
                        tempposArr[x][y] = curNode.posArr[x][y];
                    }
                }
                
                if (tempBoard[row][col] == 2) {
                    //System.out.println("In For For: "+x);
                    for (int x = 0; x < (depth + iniPos); x++) {
                        if (tempposArr[x][0] == -1) {
                            tempposArr[x][0] = row;
                            tempposArr[x][1] = col;
                            break;
                        }
                    }
                    tempBoard[row][col] = play;
                    int flag = 0;
                    if (row != 0 && row != N - 1 && col != 0 && col != N - 1) {
                        //System.out.println();

                        if (tempBoard[row - 1][col] == play || tempBoard[row + 1][col] == play || tempBoard[row][col - 1] == play || tempBoard[row][col + 1] == play) {
                            if (tempBoard[row - 1][col] == op) {
                                tempBoard[row - 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row + 1][col] == op) {
                                tempBoard[row + 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col - 1] == op) {
                                tempBoard[row][col - 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col + 1] == op) {
                                tempBoard[row][col + 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }  
                        } 
                    } else if (row == 0 && col != 0 && col != N - 1) {
                        if (tempBoard[row + 1][col] == play || tempBoard[row][col - 1] == play || tempBoard[row][col + 1] == play) {
                            if (tempBoard[row + 1][col] == op) {
                                tempBoard[row + 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col - 1] == op) {
                                tempBoard[row][col - 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col + 1] == op) {
                                tempBoard[row][col + 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                        } 
                    } else if (row == N - 1 && col != 0 && col != N - 1) {
                        if (tempBoard[row - 1][col] == play || tempBoard[row][col - 1] == play || tempBoard[row][col + 1] == play) {
                            if (tempBoard[row - 1][col] == op) {
                                tempBoard[row - 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col - 1] == op) {
                                tempBoard[row][col - 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col + 1] == op) {
                                tempBoard[row][col + 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                        } 
                    } else if (col == 0 && row != 0 && row != N - 1) {
                        if (tempBoard[row + 1][col] == play || tempBoard[row - 1][col] == play || tempBoard[row][col + 1] == play) {
                            if (tempBoard[row - 1][col] == op) {
                                tempBoard[row - 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row + 1][col] == op) {
                                tempBoard[row + 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col + 1] == op) {
                                tempBoard[row][col + 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                        } 
                    } else if (col == N - 1 && row != 0 && row != N - 1) {
                        if (tempBoard[row + 1][col] == play || tempBoard[row - 1][col] == play || tempBoard[row][col - 1] == play) {
                            if (tempBoard[row - 1][col] == op) {
                                tempBoard[row - 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row + 1][col] == op) {
                                tempBoard[row + 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col - 1] == op) {
                                tempBoard[row][col - 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                        } 
                    } else if (row == 0 && col == 0) {
                        if (tempBoard[row + 1][col] == play || tempBoard[row][col + 1] == play) {
                            if (tempBoard[row + 1][col] == op) {
                                tempBoard[row + 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col + 1] == op) {
                                tempBoard[row][col + 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                        } 
                    } else if (row == 0 && col == N - 1) {
                        if (tempBoard[row + 1][col] == play || tempBoard[row][col - 1] == play) {
                            if (tempBoard[row + 1][col] == op) {
                                tempBoard[row + 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col - 1] == op) {
                                tempBoard[row][col - 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                        } 
                    } else if (row == N - 1 && col == 0) {
                        if (tempBoard[row - 1][col] == play || tempBoard[row][col + 1] == play) {
                            if (tempBoard[row - 1][col] == op) {
                                tempBoard[row - 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col + 1] == op) {
                                tempBoard[row][col + 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                        } 
                    } else if (row == N - 1 && col == N - 1) {
                        if (tempBoard[row - 1][col] == play || tempBoard[row][col - 1] == play) {
                            if (tempBoard[row - 1][col] == op) {
                                tempBoard[row - 1][col] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                            if (tempBoard[row][col - 1] == op) {
                                tempBoard[row][col - 1] = play;
                                move = (char) (col + 65) + "" + (row + 1) + " Raid";
                                flag = 1;
                            }
                        } 
                    }
                    /*
                     for(int xy=0;xy<(depth+iniPos);xy++)
                     System.out.println(tempposArr[xy][0]+" "+ tempposArr[xy][1]);
                     */
                    if(flag == 1){
                    Node tem1 = new Node(N, (depth + iniPos));
                    tem1.Board = tempBoard;
                    tem1.move = move;
                    
                    
                    tem1.posArr = tempposArr;
                    total++;
                    System.out.println("Row : "+ row);
                    System.out.println("Col : "+ col);
                    System.out.println(total+" : "+move);
                    
                    Children.add(tem1);
                    }
                }
            }

        }

        return Children;
    }

    public static void readIn() throws Exception {
        String str;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Niranjan Patil\\Documents\\NetBeansProjects\\AI_USC_HW2\\testcases-5000\\INPUT\\"+cnt+".in"));

        for (int i = 0; (str = br.readLine()) != null; i++) {
            if (i == 0) {
                N = Integer.parseInt(str.trim());
                scoreArr = new int[N][N];
                iniBoard = new int[N][N];
            } else if (i == 1) {
                Mode = str.trim();
            } else if (i == 2) {
                if ((str.trim().contains("O"))) {
                    player = 0;
                    opp = 1;
                } else {
                    player = 1;
                    opp = 0;
                }
            } else if (i == 3) {
                depth = Integer.parseInt(str.trim());
            } else if (i < (N + 4)) {
                for (int j = 0; j < N; j++) {
                    scoreArr[i - 4][j] = Integer.parseInt(str.trim().split(" ", N)[j]);
                }
            } else {
                for (int j = 0; j < N; j++) {
                    if (str.trim().charAt(j) == 'X') {
                        iniPos++;
                        iniBoard[i - N - 4][j] = 1;
                    } else if (str.trim().charAt(j) == 'O') {
                        iniPos++;
                        iniBoard[i - N - 4][j] = 0;
                    } else {
                        iniBoard[i - N - 4][j] = 2;
                    }
                }
            }
        }

        //System.out.println("d: "+depth+" ini: "+iniPos);
        int dpx=N*N-iniPos;
        if(depth>dpx)
            depth = dpx;
        
        Root = new Node(N, (depth + iniPos));
        Root.Board = iniBoard;
        int c = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (iniBoard[i][j] == 1 || iniBoard[i][j] == 0) {
                    Root.posArr[c][0] = i;
                    Root.posArr[c][1] = j;
                    c++;

                }
            }
        }

    }

}
