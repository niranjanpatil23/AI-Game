/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Niranjan Patil
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
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
public class homework{

    static int total = 0;
    static int N;
    static String Mode;
    static int player;
    static int opp;
    static int depth;
    static int[][] scoreArr;
    static int iniPos;
    static int[][] iniBoard;
    static int[][] Root;
    static LinkedList<int[]> iniPosArr;

    static int cnt;
    static String fo1 = "C:\\Users\\Niranjan Patil\\Documents\\NetBeansProjects\\AI_USC_HW2\\testcases-5000\\OUTPUT\\";
    static String fo2 = "C:\\Users\\Niranjan Patil\\Documents\\NetBeansProjects\\AI_USC_HW2\\MyOP\\";
    static int cx = 0;
    static String nx;

    public static void check() throws Exception //All 165 Inputs 
    {
        String stro = null;
        String stro1 = null;

        BufferedReader bro = new BufferedReader(new FileReader(fo1 + "" + cnt + ".out"));
        BufferedReader bro1 = new BufferedReader(new FileReader(fo2 + "output" + cnt + ".txt"));
       //stro = bro.readLine();
        //stro1 = bro1.readLine();
        
        while ((stro = bro.readLine()) != null) {

            stro1 = bro1.readLine();
            if (stro.equals(stro1)) {
                //System.out.println();
            } else if (!stro.equals(stro1)) {
                //cx++;
                System.out.println(cnt + " ");
                System.out.println(" test case failed!!!");
                break;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        
        for (cnt = 1; cnt < 5000; cnt++) {
            Node1 Temp=null;
            iniPos = 0;
            iniPosArr = new LinkedList<int[]>();
            readIn();
            
            
            //System.out.println(iniPos);
            if ((N * N - iniPos) == 0) {
                //System.out.println(iniPos);
                nx = "A1 null";
                printl(Temp);

            } 
            else {
                Integer alpha = Integer.MIN_VALUE;
                Integer beta = Integer.MAX_VALUE;

                String m = null;
                Node1 ans = new Node1();
                if (Mode.equals("MINIMAX")) {
                    ans = MiniMax(depth);

                } else if (Mode.equals("ALPHABETA")) {
                    ans = AlphaBeta(depth, alpha, beta);
                }

        //System.out.println(ans.row);
                //System.out.println(ans.col);
                //System.out.println(ans.RaidRow);
                //System.out.println(ans.RaidCol);
        /*for(int i =0;i<N;i++){
                 for(int j=0;j<N;j++){
                 System.out.print(Root[i][j]+" ");
            
                 }
                
                 System.out.println();
        
                 }
                 */
                Root[ans.row][ans.col] = player;
        //System.out.println(ans.ans.row+ " "+ ans.col);
                //System.out.println(ans.move);
                if (ans.move.contains("Raid")) {
                    if (ans.row != 0 && ans.row != N - 1 && ans.col != 0 && ans.col != N - 1) {
                        if (Root[ans.row - 1][ans.col] == player || Root[ans.row + 1][ans.col] == player || Root[ans.row][ans.col - 1] == player || Root[ans.row][ans.col + 1] == player) {
                            if (Root[ans.row - 1][ans.col] == opp) {
                                Root[ans.row - 1][ans.col] = player;
                            }
                            if (Root[ans.row + 1][ans.col] == opp) {
                                Root[ans.row + 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col - 1] == opp) {
                                Root[ans.row][ans.col - 1] = player;

                            }
                            if (Root[ans.row][ans.col + 1] == opp) {
                                Root[ans.row][ans.col + 1] = player;
                            }

                        }
                    } else if (ans.row == 0 && ans.col != 0 && ans.col != N - 1) {
                        if (Root[ans.row + 1][ans.col] == player || Root[ans.row][ans.col - 1] == player || Root[ans.row][ans.col + 1] == player) {
                            if (Root[ans.row + 1][ans.col] == opp) {
                                Root[ans.row + 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col - 1] == opp) {
                                Root[ans.row][ans.col - 1] = player;

                            }
                            if (Root[ans.row][ans.col + 1] == opp) {
                                Root[ans.row][ans.col + 1] = player;

                            }

                        }
                    } else if (ans.row == N - 1 && ans.col != 0 && ans.col != N - 1) {
                        if (Root[ans.row - 1][ans.col] == player || Root[ans.row][ans.col - 1] == player || Root[ans.row][ans.col + 1] == player) {
                            if (Root[ans.row - 1][ans.col] == opp) {
                                Root[ans.row - 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col - 1] == opp) {
                                Root[ans.row][ans.col - 1] = player;

                            }
                            if (Root[ans.row][ans.col + 1] == opp) {
                                Root[ans.row][ans.col + 1] = player;

                            }

                        }
                    } else if (ans.col == 0 && ans.row != 0 && ans.row != N - 1) {
                        if (Root[ans.row + 1][ans.col] == player || Root[ans.row - 1][ans.col] == player || Root[ans.row][ans.col + 1] == player) {
                            if (Root[ans.row - 1][ans.col] == opp) {
                                Root[ans.row - 1][ans.col] = player;

                            }
                            if (Root[ans.row + 1][ans.col] == opp) {
                                Root[ans.row + 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col + 1] == opp) {
                                Root[ans.row][ans.col + 1] = player;

                            }

                        }
                    } else if (ans.col == N - 1 && ans.row != 0 && ans.row != N - 1) {
                        if (Root[ans.row + 1][ans.col] == player || Root[ans.row - 1][ans.col] == player || Root[ans.row][ans.col - 1] == player) {
                            if (Root[ans.row - 1][ans.col] == opp) {
                                Root[ans.row - 1][ans.col] = player;

                            }
                            if (Root[ans.row + 1][ans.col] == opp) {
                                Root[ans.row + 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col - 1] == opp) {
                                Root[ans.row][ans.col - 1] = player;

                            }

                        }
                    } else if (ans.row == 0 && ans.col == 0) {
                        if (Root[ans.row + 1][ans.col] == player || Root[ans.row][ans.col + 1] == player) {
                            if (Root[ans.row + 1][ans.col] == opp) {
                                Root[ans.row + 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col + 1] == opp) {
                                Root[ans.row][ans.col + 1] = player;

                            }

                        }
                    } else if (ans.row == 0 && ans.col == N - 1) {
                        if (Root[ans.row + 1][ans.col] == player || Root[ans.row][ans.col - 1] == player) {
                            if (Root[ans.row + 1][ans.col] == opp) {
                                Root[ans.row + 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col - 1] == opp) {
                                Root[ans.row][ans.col - 1] = player;

                            }

                        }
                    } else if (ans.row == N - 1 && ans.col == 0) {
                        if (Root[ans.row - 1][ans.col] == player || Root[ans.row][ans.col + 1] == player) {
                            if (Root[ans.row - 1][ans.col] == opp) {
                                Root[ans.row - 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col + 1] == opp) {
                                Root[ans.row][ans.col + 1] = player;

                            }

                        }
                    } else if (ans.row == N - 1 && ans.col == N - 1) {
                        if (Root[ans.row - 1][ans.col] == player || Root[ans.row][ans.col - 1] == player) {
                            if (Root[ans.row - 1][ans.col] == opp) {
                                Root[ans.row - 1][ans.col] = player;

                            }
                            if (Root[ans.row][ans.col - 1] == opp) {
                                Root[ans.row][ans.col - 1] = player;

                            }

                        }
                    }

                    //System.out.println(ans.Raid.get(i)[0]+" "+ans.Raid.get(i)[1]);
                }

                printl(ans);
            }
            check();

        }

    }

    public static void printl(Node1 ans) throws Exception {
        File f = new File(fo2 + "output" + cnt + ".txt");
        //File f = new File("output.txt");
        if (f.exists()) {
            f.delete();
        }
        FileWriter wr = new FileWriter(f);

        int j = 0;
        if ((N * N - iniPos) == 0) {
            //System.out.println(iniPos);
            wr.write(nx);
        } else {
            wr.write((char) (65 + ans.col) + "" + (ans.row + 1) + " " + ans.move + "\n");
            for (int i = 0; i < N; i++) {
                for (j = 0; j < N; j++) {
                    if (Root[i][j] == 1) {
                        //System.out.print('X');
                        wr.write('X');
                    } else if (Root[i][j] == 0) {
                        //System.out.print('O');
                        wr.write('O');
                    } else if (Root[i][j] == 2) {
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

    public static Node1 AlphaBeta(int dep, int alpha, int beta) {

        LinkedList<Node1> List = null;
        //int dp = N*N-iniPos;
        if (dep == 0) {
            Node1 N3 = new Node1();
            N3.score = Evaluate();
            //  System.out.println("N3 Score: "+N3.score);
            return N3;
        }

        if ((depth - dep) % 2 == 0) {
            List = genChildrenNodes(dep, player, opp);
        } else if ((depth - dep) % 2 == 1) {
            List = genChildrenNodes(dep, opp, player);
        }

        if ((depth - dep) % 2 == 0) {
            Node1 bestScN = new Node1();
            bestScN.score = alpha;
            bestScN.move = "";
            int c1 = 0;
            while (c1 < List.size()) {
                Node1 N1 = new Node1();
                /*  System.out.println("In N1");
                 System.out.println(List.get(c1).row);
                 System.out.println(List.get(c1).col);
                 System.out.println(List.get(c1).move);
                 System.out.println(dep);
                 System.out.println();
                 */

                iniBoard[List.get(c1).row][List.get(c1).col] = player;
                iniPosArr.addFirst(new int[]{List.get(c1).row, List.get(c1).col});
                if (List.get(c1).move.contains("Raid")) {
                    for (int i = 0; i < List.get(c1).Raid.size(); i++) {
                        iniBoard[List.get(c1).Raid.get(i)[0]][List.get(c1).Raid.get(i)[1]] = player;
                    }
                }
                N1 = AlphaBeta(dep - 1, alpha, beta);
                List.get(c1).score = N1.score;
                //System.out.println("In N1");
            /*    System.out.println("In N1");
                 System.out.println(List.get(c1).score);
                 System.out.println(List.get(c1).row);
                 System.out.println(List.get(c1).col);
                 System.out.println(bestScN.row);
                 System.out.println(bestScN.col);
                 System.out.println(bestScN.score);
                 System.out.println(List.get(c1).move);
                 System.out.println(bestScN.move);
                 System.out.println(dep);
              
                 if(List.get(c1).row == 2 && List.get(c1).col ==2)
                 {
                 for(int i =0;i<N;i++){
                 for(int j=0;j<N;j++)
                 {
                 System.out.print(iniBoard[i][j]+" ");
                 }
                 System.out.println();
                 }
                    
                                
                 }
                    
                    
                 System.out.println();
                
                 */

                if (List.get(c1).score > bestScN.score) {
                    alpha = List.get(c1).score;
                    bestScN.score = List.get(c1).score;
                    bestScN.move = List.get(c1).move;
                    bestScN.row = List.get(c1).row;
                    bestScN.col = List.get(c1).col;

                    if (List.get(c1).move.contains("Raid")) {
                        for (int i = 0; i < List.get(c1).Raid.size(); i++) {
                            bestScN.Raid.addFirst(new int[]{List.get(c1).Raid.get(i)[0], List.get(c1).Raid.get(i)[1]});
                        }
                    }

                } else if (List.get(c1).score == bestScN.score) {
                    /*
                     System.out.println("In N1");
                     System.out.println(bestScN.score);
                     System.out.println(List.get(c1).score);
                     System.out.println(bestScN.move);
                     System.out.println(List.get(c1).move);
                     System.out.println(dep);
                     System.out.println();
                     */
                    if (List.get(c1).move.contains("Stake") && bestScN.move.contains("Raid")) {

                        alpha = List.get(c1).score;
                        bestScN.score = List.get(c1).score;
                        bestScN.move = List.get(c1).move;
                        bestScN.row = List.get(c1).row;
                        bestScN.col = List.get(c1).col;

                    }
                }
                /*
                 System.out.println("In N1");
                 System.out.println(bestScN.score);
                 System.out.println(bestScN.row);
                 System.out.println(bestScN.col);
                 System.out.println(bestScN.move);
                 System.out.println(bestScN.RaidRow);
                 System.out.println(bestScN.RaidCol);
                 System.out.println(dep);
                 System.out.println();
                 */

                iniBoard[List.get(c1).row][List.get(c1).col] = 2;
                iniPosArr.removeFirst();

                if (List.get(c1).move.contains("Raid")) {
                    for (int i = 0; i < List.get(c1).Raid.size(); i++) {
                        iniBoard[List.get(c1).Raid.get(i)[0]][List.get(c1).Raid.get(i)[1]] = opp;

                    }
                }
                if (alpha >= beta) {
                    //total++;
                    //System.out.println(total+"Pruning\n");
                    break;
                }
                c1++;
            }

            return bestScN;
        } else if ((depth - dep) % 2 == 1) {

            Node1 bestScP = new Node1();
            bestScP.score = beta;
            bestScP.move = "";
            int c2 = 0;
            while (c2 < List.size()) {
                Node1 N2 = new Node1();
                /*      System.out.println("In N2");
                 System.out.println(List.get(c2).row);
                 System.out.println(List.get(c2).col);
                 System.out.println(List.get(c2).move);
                 System.out.println(dep);
                 System.out.println();
                 */
                iniBoard[List.get(c2).row][List.get(c2).col] = opp;
                iniPosArr.addFirst(new int[]{List.get(c2).row, List.get(c2).col});
                if (List.get(c2).move.contains("Raid")) {
                    for (int i = 0; i < List.get(c2).Raid.size(); i++) {
                        iniBoard[List.get(c2).Raid.get(i)[0]][List.get(c2).Raid.get(i)[1]] = opp;
                    }
                }
                N2 = AlphaBeta(dep - 1, alpha, beta);
                List.get(c2).score = N2.score;
                //System.out.println("In N2");
                /*
                 System.out.println("In N2");
                 System.out.println(List.get(c2).score);
                 System.out.println(bestScP.score);
                 System.out.println(List.get(c2).row);
                 System.out.println(List.get(c2).col);
                 System.out.println(bestScP.row);
                 System.out.println(bestScP.col);
                 System.out.println(List.get(c2).move);
                 System.out.println(bestScP.move);
                 System.out.println(dep);
                 System.out.println();
                 */
                if (List.get(c2).score < bestScP.score) {
                    beta = List.get(c2).score;
                    bestScP.score = List.get(c2).score;
                    bestScP.move = List.get(c2).move;
                    bestScP.row = List.get(c2).row;
                    bestScP.col = List.get(c2).col;
                    if (List.get(c2).move.contains("Raid")) {
                        for (int i = 0; i < List.get(c2).Raid.size(); i++) {
                            //System.out.println(i+" "+List.get(c2).Raid.size());
                            //System.out.println(List.get(c2).Raid.get(i)[0]);
                            //System.out.println(List.get(c2).Raid.get(i)[1]);
                            bestScP.Raid.addFirst(new int[]{List.get(c2).Raid.get(i)[0], List.get(c2).Raid.get(i)[1]});

                        }
                    }
                } else if (List.get(c2).score == bestScP.score) {
                    //System.out.println("In N2");
                        /*System.out.println(bestScP.score);
                     System.out.println(List.get(c2).score);
                     System.out.println(bestScP.move);
                     System.out.println(List.get(c2).move);
                     System.out.println(dep);
                     System.out.println();*/
                    if (List.get(c2).move.contains("Stake") && bestScP.move.contains("Raid")) {

                        beta = List.get(c2).score;
                        bestScP.score = List.get(c2).score;
                        bestScP.move = List.get(c2).move;
                        bestScP.row = List.get(c2).row;
                        bestScP.col = List.get(c2).col;
                    }
                }
                /*
                 System.out.println("In N2");
                 System.out.println(bestScP.score);
                 System.out.println(bestScP.row);
                 System.out.println(bestScP.col);
                 System.out.println(bestScP.move);
                 System.out.println(bestScP.RaidRow);
                 System.out.println(bestScP.RaidCol);
                 System.out.println(dep);
                 System.out.println();
                
                 */

                iniBoard[List.get(c2).row][List.get(c2).col] = 2;
                iniPosArr.removeFirst();

                if (List.get(c2).move.contains("Raid")) {
                    for (int i = 0; i < List.get(c2).Raid.size(); i++) {
                        iniBoard[List.get(c2).Raid.get(i)[0]][List.get(c2).Raid.get(i)[1]] = player;
                    }
                }
                if (alpha >= beta) {
                   // total++;
                    //  System.out.println(total+"Pruning\n");
                    break;
                }
                c2++;
            }

            return bestScP;
        }
        return null;
    }

    public static Node1 MiniMax(int dep) {

        LinkedList<Node1> List = null;
        //int dp = N*N-iniPos;
        if (dep == 0) {
            Node1 N3 = new Node1();
            N3.score = Evaluate();
            //  System.out.println("N3 Score: "+N3.score);
            return N3;
        }

        if ((depth - dep) % 2 == 0) {
            List = genChildrenNodes(dep, player, opp);
        } else if ((depth - dep) % 2 == 1) {
            List = genChildrenNodes(dep, opp, player);
        }

        if ((depth - dep) % 2 == 0) {
            Node1 bestScN = new Node1();
            bestScN.score = Integer.MIN_VALUE;
            int c1 = 0;
            while (c1 < List.size()) {
                Node1 N1 = new Node1();
                /*  System.out.println("In N1");
                 System.out.println(List.get(c1).row);
                 System.out.println(List.get(c1).col);
                 System.out.println(List.get(c1).move);
                 System.out.println(dep);
                 System.out.println();
                 */

                iniBoard[List.get(c1).row][List.get(c1).col] = player;
                iniPosArr.addFirst(new int[]{List.get(c1).row, List.get(c1).col});
                if (List.get(c1).move.contains("Raid")) {
                    for (int i = 0; i < List.get(c1).Raid.size(); i++) {
                        iniBoard[List.get(c1).Raid.get(i)[0]][List.get(c1).Raid.get(i)[1]] = player;
                    }
                }
                N1 = MiniMax(dep - 1);
                List.get(c1).score = N1.score;
                //System.out.println("In N1");
            /*    System.out.println("In N1");
                 System.out.println(List.get(c1).score);
                 System.out.println(List.get(c1).row);
                 System.out.println(List.get(c1).col);
                 System.out.println(bestScN.row);
                 System.out.println(bestScN.col);
                 System.out.println(bestScN.score);
                 System.out.println(List.get(c1).move);
                 System.out.println(bestScN.move);
                 System.out.println(dep);
              
                 if(List.get(c1).row == 2 && List.get(c1).col ==2)
                 {
                 for(int i =0;i<N;i++){
                 for(int j=0;j<N;j++)
                 {
                 System.out.print(iniBoard[i][j]+" ");
                 }
                 System.out.println();
                 }
                    
                                
                 }
                    
                    
                 System.out.println();
                
                 */

                if (List.get(c1).score > bestScN.score) {
                    bestScN.score = List.get(c1).score;
                    bestScN.move = List.get(c1).move;
                    bestScN.row = List.get(c1).row;
                    bestScN.col = List.get(c1).col;

                    if (List.get(c1).move.contains("Raid")) {
                        for (int i = 0; i < List.get(c1).Raid.size(); i++) {
                            bestScN.Raid.addFirst(new int[]{List.get(c1).Raid.get(i)[0], List.get(c1).Raid.get(i)[1]});
                        }
                    }

                } else if (List.get(c1).score == bestScN.score) {
                    /*
                     System.out.println("In N1");
                     System.out.println(bestScN.score);
                     System.out.println(List.get(c1).score);
                     System.out.println(bestScN.move);
                     System.out.println(List.get(c1).move);
                     System.out.println(dep);
                     System.out.println();
                     */
                    if (List.get(c1).move.contains("Stake") && bestScN.move.contains("Raid")) {

                        bestScN.score = List.get(c1).score;
                        bestScN.move = List.get(c1).move;
                        bestScN.row = List.get(c1).row;
                        bestScN.col = List.get(c1).col;

                    }
                }
                /*
                 System.out.println("In N1");
                 System.out.println(bestScN.score);
                 System.out.println(bestScN.row);
                 System.out.println(bestScN.col);
                 System.out.println(bestScN.move);
                 System.out.println(bestScN.RaidRow);
                 System.out.println(bestScN.RaidCol);
                 System.out.println(dep);
                 System.out.println();
                 */

                iniBoard[List.get(c1).row][List.get(c1).col] = 2;
                iniPosArr.removeFirst();
                if (List.get(c1).move.contains("Raid")) {
                    for (int i = 0; i < List.get(c1).Raid.size(); i++) {
                        iniBoard[List.get(c1).Raid.get(i)[0]][List.get(c1).Raid.get(i)[1]] = opp;
                    }
                }
                c1++;
            }

            return bestScN;
        } else if ((depth - dep) % 2 == 1) {

            Node1 bestScP = new Node1();
            bestScP.score = Integer.MAX_VALUE;
            int c2 = 0;
            while (c2 < List.size()) {
                Node1 N2 = new Node1();
                /*      System.out.println("In N2");
                 System.out.println(List.get(c2).row);
                 System.out.println(List.get(c2).col);
                 System.out.println(List.get(c2).move);
                 System.out.println(dep);
                 System.out.println();
                 */
                iniBoard[List.get(c2).row][List.get(c2).col] = opp;
                iniPosArr.addFirst(new int[]{List.get(c2).row, List.get(c2).col});
                if (List.get(c2).move.contains("Raid")) {
                    for (int i = 0; i < List.get(c2).Raid.size(); i++) {
                        iniBoard[List.get(c2).Raid.get(i)[0]][List.get(c2).Raid.get(i)[1]] = opp;
                    }
                }
                N2 = MiniMax(dep - 1);
                List.get(c2).score = N2.score;
                //System.out.println("In N2");
                /*
                 System.out.println("In N2");
                 System.out.println(List.get(c2).score);
                 System.out.println(bestScP.score);
                 System.out.println(List.get(c2).row);
                 System.out.println(List.get(c2).col);
                 System.out.println(bestScP.row);
                 System.out.println(bestScP.col);
                 System.out.println(List.get(c2).move);
                 System.out.println(bestScP.move);
                 System.out.println(dep);
                 System.out.println();
                 */
                if (List.get(c2).score < bestScP.score) {
                    bestScP.score = List.get(c2).score;
                    bestScP.move = List.get(c2).move;
                    bestScP.row = List.get(c2).row;
                    bestScP.col = List.get(c2).col;
                    if (List.get(c2).move.contains("Raid")) {
                        for (int i = 0; i < List.get(c2).Raid.size(); i++) {
                            //System.out.println(i+" "+List.get(c2).Raid.size());
                            //System.out.println(List.get(c2).Raid.get(i)[0]);
                            //System.out.println(List.get(c2).Raid.get(i)[1]);
                            bestScP.Raid.addFirst(new int[]{List.get(c2).Raid.get(i)[0], List.get(c2).Raid.get(i)[1]});

                        }
                    }
                } else if (List.get(c2).score == bestScP.score) {
                    //System.out.println("In N2");
                        /*System.out.println(bestScP.score);
                     System.out.println(List.get(c2).score);
                     System.out.println(bestScP.move);
                     System.out.println(List.get(c2).move);
                     System.out.println(dep);
                     System.out.println();*/
                    if (List.get(c2).move.contains("Stake") && bestScP.move.contains("Raid")) {

                        bestScP.score = List.get(c2).score;
                        bestScP.move = List.get(c2).move;
                        bestScP.row = List.get(c2).row;
                        bestScP.col = List.get(c2).col;
                    }
                }
                /*
                 System.out.println("In N2");
                 System.out.println(bestScP.score);
                 System.out.println(bestScP.row);
                 System.out.println(bestScP.col);
                 System.out.println(bestScP.move);
                 System.out.println(bestScP.RaidRow);
                 System.out.println(bestScP.RaidCol);
                 System.out.println(dep);
                 System.out.println();
                
                 */

                iniBoard[List.get(c2).row][List.get(c2).col] = 2;
                iniPosArr.removeFirst();
                if (List.get(c2).move.contains("Raid")) {
                    for (int i = 0; i < List.get(c2).Raid.size(); i++) {
                        iniBoard[List.get(c2).Raid.get(i)[0]][List.get(c2).Raid.get(i)[1]] = player;
                    }
                }
                c2++;
            }

            return bestScP;
        }
        return null;
    }

    public static int Evaluate() {
        int score = 0;
        int i = 0;
        //System.out.println("In Evaluate: ");
        while (i < iniPosArr.size()) {
            if (iniBoard[iniPosArr.get(i)[0]][iniPosArr.get(i)[1]] == player) {
                score += scoreArr[iniPosArr.get(i)[0]][iniPosArr.get(i)[1]];
            } else if (iniBoard[iniPosArr.get(i)[0]][iniPosArr.get(i)[1]] == opp) {
                score -= scoreArr[iniPosArr.get(i)[0]][iniPosArr.get(i)[1]];
            }
            i++;
        }

        return score;
    }

    public static LinkedList<Node1> genChildrenNodes(int dep, int play, int op) {
        LinkedList<Node1> Children = new LinkedList<Node1>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                Node1 tem = new Node1();
                if (iniBoard[row][col] == 2) {

                    tem.move = "Stake";
                    tem.row = row;
                    tem.col = col;
                    Children.add(tem);

                }

            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                Node1 tem1 = new Node1();

                if (iniBoard[row][col] == 2) {

                    tem1.row = row;
                    tem1.col = col;
                    tem1.move = "Stake";
                    int flag = 0;
                    if (row != 0 && row != N - 1 && col != 0 && col != N - 1) {
                        if (iniBoard[row - 1][col] == play || iniBoard[row + 1][col] == play || iniBoard[row][col - 1] == play || iniBoard[row][col + 1] == play) {
                            if (iniBoard[row - 1][col] == op) {
                                tem1.move = "Raid";
                                tem1.Raid.add(new int[]{row - 1, col});
                                flag = 1;
                            }
                            if (iniBoard[row + 1][col] == op) {
                                tem1.Raid.add(new int[]{row + 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col - 1] == op) {
                                tem1.Raid.add(new int[]{row, col - 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col + 1] == op) {
                                tem1.Raid.add(new int[]{row, col + 1});
                                flag = 1;
                                tem1.move = "Raid";
                            }

                        }
                    } else if (row == 0 && col != 0 && col != N - 1) {
                        if (iniBoard[row + 1][col] == play || iniBoard[row][col - 1] == play || iniBoard[row][col + 1] == play) {
                            if (iniBoard[row + 1][col] == op) {
                                tem1.Raid.add(new int[]{row + 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col - 1] == op) {
                                tem1.Raid.add(new int[]{row, col - 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col + 1] == op) {
                                tem1.Raid.add(new int[]{row, col + 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }

                        }
                    } else if (row == N - 1 && col != 0 && col != N - 1) {
                        if (iniBoard[row - 1][col] == play || iniBoard[row][col - 1] == play || iniBoard[row][col + 1] == play) {
                            if (iniBoard[row - 1][col] == op) {
                                tem1.Raid.add(new int[]{row - 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col - 1] == op) {
                                tem1.Raid.add(new int[]{row, col - 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col + 1] == op) {
                                tem1.Raid.add(new int[]{row, col + 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }

                        }
                    } else if (col == 0 && row != 0 && row != N - 1) {
                        if (iniBoard[row + 1][col] == play || iniBoard[row - 1][col] == play || iniBoard[row][col + 1] == play) {
                            if (iniBoard[row - 1][col] == op) {
                                tem1.Raid.add(new int[]{row - 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row + 1][col] == op) {
                                tem1.Raid.add(new int[]{row + 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col + 1] == op) {
                                tem1.Raid.add(new int[]{row, col + 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }

                        }
                    } else if (col == N - 1 && row != 0 && row != N - 1) {
                        if (iniBoard[row + 1][col] == play || iniBoard[row - 1][col] == play || iniBoard[row][col - 1] == play) {
                            if (iniBoard[row - 1][col] == op) {
                                tem1.Raid.add(new int[]{row - 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row + 1][col] == op) {
                                tem1.Raid.add(new int[]{row + 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col - 1] == op) {
                                tem1.Raid.add(new int[]{row, col - 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }

                        }
                    } else if (row == 0 && col == 0) {
                        if (iniBoard[row + 1][col] == play || iniBoard[row][col + 1] == play) {
                            if (iniBoard[row + 1][col] == op) {
                                tem1.Raid.add(new int[]{row + 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col + 1] == op) {
                                tem1.Raid.add(new int[]{row, col + 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }

                        }
                    } else if (row == 0 && col == N - 1) {
                        if (iniBoard[row + 1][col] == play || iniBoard[row][col - 1] == play) {
                            if (iniBoard[row + 1][col] == op) {
                                tem1.Raid.add(new int[]{row + 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col - 1] == op) {
                                tem1.Raid.add(new int[]{row, col - 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }

                        }
                    } else if (row == N - 1 && col == 0) {
                        if (iniBoard[row - 1][col] == play || iniBoard[row][col + 1] == play) {
                            if (iniBoard[row - 1][col] == op) {
                                tem1.Raid.add(new int[]{row - 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col + 1] == op) {
                                tem1.Raid.add(new int[]{row, col + 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }

                        }
                    } else if (row == N - 1 && col == N - 1) {
                        if (iniBoard[row - 1][col] == play || iniBoard[row][col - 1] == play) {
                            if (iniBoard[row - 1][col] == op) {
                                tem1.Raid.add(new int[]{row - 1, col});
                                flag = 1;
                                tem1.move = "Raid";

                            }
                            if (iniBoard[row][col - 1] == op) {
                                tem1.Raid.add(new int[]{row, col - 1});
                                flag = 1;
                                tem1.move = "Raid";

                            }

                        }
                    }
                    if (flag == 1) {
                    //total++;
                        //System.out.println("Row : "+ tem1.row);
                        //System.out.println("Col : "+ tem1.col);
                        //System.out.println(total+" : "+ tem1.move);
                        //for(int i=0;i<tem1.Raid.size();i++)
                        //  System.out.println(tem1.Raid.get(i)[0]+" "+tem1.Raid.get(i)[1]);
                        Children.add(tem1);
                    }

                }
            }

        }

        return Children;
    }

    public static void readIn() throws Exception {
        String str;
        //BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Niranjan Patil\\Documents\\NetBeansProjects\\AI_USC_HW2\\testcases-5000\\INPUT\\" + cnt + ".in"));

        for (int i = 0; (str = br.readLine()) != null; i++) {
            if (i == 0) {
                N = Integer.parseInt(str.trim());
                scoreArr = new int[N][N];
                iniBoard = new int[N][N];
                Root = new int[N][N];
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
                        Root[i - N - 4][j] = 1;
                    } else if (str.trim().charAt(j) == 'O') {
                        iniPos++;
                        iniBoard[i - N - 4][j] = 0;
                        Root[i - N - 4][j] = 0;
                    } else{
                        iniBoard[i - N - 4][j] = 2;
                        Root[i - N - 4][j] = 2;
                    }
                }
                
            }
        }

        //System.out.println("d: "+depth+" ini: "+iniPos);
        int dpx = N * N - iniPos;
        if (depth > dpx) {
            depth = dpx;
        }

        int c = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (iniBoard[i][j] == 1 || iniBoard[i][j] == 0) {
                    iniPosArr.addFirst(new int[]{i, j});
                    c++;

                }
            }
        }

    }
}
