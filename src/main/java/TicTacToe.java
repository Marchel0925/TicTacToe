import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static Scanner sc = new Scanner(System.in);
    public static Random r = new Random();

    public static void main(String[] args) {
        char board[][] = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        char playerAndAI [] = characterChoice();
        char player = playerAndAI[0];
        char ai = playerAndAI[1];
        LinkedList<Integer> posList = new LinkedList<>();
        for(int i = 1; i < 10; i++){
            posList.add(i);
        }
        System.out.println();
        int [] moves = orderOfMoves(startPoint());
        int step = 0;
        while(step < moves.length){
            if (moves[step]==1){
                int choice = sc.nextInt();
                int playerChoice = checkingMechanism(posList, choice);
                gameOn(board, player, playerChoice);
                printBoard(board);
                if(checkWinner(board, player)){
                    System.out.println("The Player wins!!");
                    break;
                }
                step++;
            } else {
                int choice = r.nextInt(9)+1;
                int randomChoice = checkingMechanism(posList, choice);
                gameOn(board, ai, randomChoice);
                printBoard(board);
                if(checkWinner(board, ai)){
                    System.out.println("The AI wins!!");
                    break;
                }
                System.out.print("Available positions : ");
                if(posList.isEmpty()){
                    System.out.print("-");
                } else {
                    for(int i : posList){
                        System.out.print(i + " ");
                    }
                }
                step++;
            }
        }
    }

    public static boolean checkWinner(char board [][], char character){
        boolean winner = false;
        if(board[0][0] == character && board[0][2] == character && board[0][4] == character){
            winner = true;
        } else if(board[2][0] == character && board[2][2] == character && board[2][4] == character){
            winner = true;
        } else if(board[4][0] == character && board[4][2] == character && board[4][4] == character){
            winner = true;
        } else if(board[0][0] == character && board[2][0] == character && board[4][0] == character){
            winner = true;
        } else if(board[0][2] == character && board[2][2] == character && board[4][2] == character){
            winner = true;
        } else if(board[0][4] == character && board[2][4] == character && board[4][4] == character){
            winner = true;
        } else if(board[0][0] == character && board[2][2] == character && board[4][4] == character){
            winner = true;
        } else if(board[0][4] == character && board[2][2] == character && board[4][0] == character){
            winner = true;
        }
        return winner;
    }

    public static int checkingMechanism(LinkedList<Integer> posArr, int choice){
        int validChoice = 0;
        if(posArr.contains(choice)){
            validChoice = choice;
            posArr.remove(posArr.indexOf(choice));
        } else {
            while(!posArr.contains(choice)) {
                choice = r.nextInt(9) + 1;
                if (posArr.contains(choice)) {
                    validChoice = choice;
                    posArr.remove(posArr.indexOf(choice));
                    break;
                }
            }
        }
        return validChoice;
    }

    public static void gameOn(char board[][], char player, int choice){
            switch (choice){
                case 1:
                    board[0][0] = player;
                    break;
                case 2:
                    board[0][2] = player;
                    break;
                case 3:
                    board[0][4] = player;
                    break;
                case 4:
                    board[2][0] = player;
                    break;
                case 5:
                    board[2][2] = player;
                    break;
                case 6:
                    board[2][4] = player;
                    break;
                case 7:
                    board[4][0] = player;
                    break;
                case 8:
                    board[4][2] = player;
                    break;
                case 9:
                    board[4][4] = player;
                    break;
                default:
                    printBoard(board);
                    break;
            }
    }

    public static char[] characterChoice(){
        char [] symArr = new char[2];
        System.out.println("Choose symbol: 1 or 2 (X or O)");
        int choice;
        while (true) {
            choice = sc.nextInt();
            if (choice == 1) {
                symArr[0] = 'X';
                symArr[1] = 'O';
                break;
            } else if (choice == 2) {
                symArr[0] = 'O';
                symArr[1] = 'X';
                break;
            } else {
                System.out.println("Input valid symbol: ");
            }
        }
        System.out.println("Player: " + symArr[0] + " || AI: " + symArr[1]);
        return symArr;
    }

    public static void printBoard(char [][] board){
        System.out.println();
        for(int i=0; i<board.length;i++){
            for(int j=0; j< board.length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean startPoint(){
        System.out.println("Heads or Tails? (1 or 2)");
        boolean playerChoice;
        while(true){
            int coinF = sc.nextInt();
            if (coinF == 1) {
                playerChoice = true;
                break;
            } else if (coinF == 2) {
                playerChoice = false;
                break;
            } else {
                System.out.println("Input valid choice: ");
            }
        }
        double chance = Math.random();
        boolean flag;
        boolean start;
        if(chance<=0.5){
            System.out.println("It's Heads");
            flag = true;
        } else{
            System.out.println("It's Tails");
            flag = false;
        }
        if (playerChoice == flag){
            System.out.println("You've won! You may start!");
            start = true;
        }else{
            System.out.println("You've lost! The AI will start!");
            start = false;
        }
        return start;
    }

    public static int [] orderOfMoves(boolean start){
        int [] moves = new int [9];
        if(start){
            for(int i = 0; i < moves.length; i++){
                if(i % 2 == 0){
                    moves[i] = 1;
                } else {
                    moves[i] = 0;
                }
            }
        } else {
            for(int i = 0; i < moves.length; i++){
                if(i % 2 == 0){
                    moves[i] = 0;
                } else {
                    moves[i] = 1;
                }
            }
        }
        return moves;
    }
}