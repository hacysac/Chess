import java.util.*;

public class Main {

  public static Scanner input = new Scanner(System.in);
  public static Piece[][] board = new Piece[8][8];
  public static String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h" };

  public static void main(String[] args) {

    startUp(board);

    boolean isWhiteMove = false;

    while (true) {

      clearScreen();
      isWhiteMove = !isWhiteMove;

      if (isWhiteMove) {
        System.out.println("White to move:\n");
      } else {
        System.out.println("Black to move:\n");
      }

      drawBoard(board);
      Pair<int[],int[]> startEnd = verifyPiece(isWhiteMove);
      int[] startPos = startEnd.getFirst();
      int[] endPos = startEnd.getSecond();
      board[endPos[0]][endPos[1]] = board[startPos[0]][startPos[1]];
      board[startPos[0]][startPos[1]] = null;
    }
  }

  public static boolean realPosition(String position) {
    if (position.length() == 2) {
      String letter = Character.toString(position.charAt(0));
      String number = Character.toString(position.charAt(1));
      if (isNumeric(number)) {
        int num = Integer.valueOf(number);
        if (num > 0 && num < 9 && contains(letters, letter)) {
          return true;
        }
      }
    }
    return false;
  }
  

  public static Pair<int[], int[]> verifyPiece(boolean isWhiteMove) {
    while (true) {
      System.out.print("Move: ");
      String position = input.nextLine();
      if (position.length() == 4) {
        if (realPosition(position.substring(0, 2))) {
          String letter1 = Character.toString(position.charAt(0));
          String number1 = Character.toString(position.charAt(1));
          int num1 = Integer.valueOf(number1);
          int x1 = convertNumPos(num1);
          int y1 = convertLetterPos(letter1);
          if (board[y1][x1] != null && board[y1][x1].isWhite == isWhiteMove) {
            String letter2 = Character.toString(position.charAt(2));
            String number2 = Character.toString(position.charAt(3));
            int num2 = Integer.valueOf(number2);
            int x2 = Main.convertNumPos(num2);
            int y2 = Main.convertLetterPos(letter2);
            if (board[y1][x1].verifyMove(board, new int[] { y1, x1 }, position.substring(2))) {
              return new Pair(new int[] { y1, x1 }, new int[] { y2, x2 });
            }
          }
        }
      }
      System.out.println("Invalid position, try again.\n");
    }
  }

  public static int convertNumPos(int num) {
    return num - 1;
  }

  public static int convertLetterPos(String letter) {
    return Arrays.binarySearch(letters, letter);
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void startUp(Piece[][] board) {
    System.out.println("Welcome to Chess");
    System.out.println("Press enter to start: ");

    input.nextLine();

    setUpBoard(board);
  }

  public static void setUpBoard(Piece[][] board) {
    board[0][0] = new Rook(true);
    board[0][1] = new Knight(true);
    board[0][2] = new Bishop(true);
    board[0][3] = new Queen(true);
    board[0][4] = new King(true);
    board[0][5] = new Bishop(true);
    board[0][6] = new Knight(true);
    board[0][7] = new Rook(true);

    board[7][0] = new Rook(false);
    board[7][1] = new Knight(false);
    board[7][2] = new Bishop(false);
    board[7][3] = new Queen(false);
    board[7][4] = new King(false);
    board[7][5] = new Bishop(false);
    board[7][6] = new Knight(false);
    board[7][7] = new Rook(false);

    for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(true);
    }
    for (int i = 0; i < 8; i++) {
      board[6][i] = new Pawn(false);
    }
  }

  public static void drawBoard(Piece[][] board) {

    for (int i = board.length - 1; i >= 0; i--) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == null) {
          System.out.print(Colors.ANSI_CYAN + "- " + Colors.ANSI_RESET);
        } else {
          System.out.print(board[i][j] + " ");
        }
      }
      System.out.println("  " + letters[i]);
    }
    System.out.println("\n1 2 3 4 5 6 7 8\n");

  }

  public static boolean contains(String[] arr, String str) {
    for (String s : arr) {
      if (s.equals(str)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isNumeric(String str) {
    return str != null && str.matches("[0-9.]+");
  }
}