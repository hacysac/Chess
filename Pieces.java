import java.util.ArrayList;

class Piece {
  public boolean isWhite;
  public static ArrayList<String> takenPieces = new ArrayList<String>();

  public Piece(boolean isWhite) {

    this.isWhite = isWhite;

  }

  public String toString() {
    return isWhite ? Colors.ANSI_WHITE : Colors.ANSI_BLACK;
  }

  public boolean verifyMove(Piece[][] board, int[] oldPos, String position) {
    return false;
  }

  public boolean clearPath(Piece[][] board, int[] oldPos, int[] newPos) {
    return true;
  }

  public boolean validPlacement(Piece[][] board, String position, int[] oldPos) {
    if (Main.realPosition(position)) {
      String letter = Character.toString(position.charAt(0));
      String number = Character.toString(position.charAt(1));
      int num = Integer.valueOf(number);
      int x = Main.convertNumPos(num);
      int y = Main.convertLetterPos(letter);
      if (board[y][x] == null || board[y][x].isWhite != this.isWhite) {
        if (new int[] { y, x } != oldPos) {
          return true;
        }
      }
    }
    return false;
  }
}

class King extends Piece {

  public King(boolean isWhite) {
    super(isWhite);
  }

  public String toString() {
    return super.toString() + "K" + Colors.ANSI_RESET;
  }

  public boolean verifyMove(Piece[][] board, int[] oldPos, String position) {
    if (validPlacement(board, position, oldPos)) {
      String letter = Character.toString(position.charAt(0));
      String number = Character.toString(position.charAt(1));
      int num = Integer.valueOf(number);
      int x = Main.convertNumPos(num);
      int y = Main.convertLetterPos(letter);
      int changeInX = x - oldPos[1];
      int changeInY = y - oldPos[0];
      if (Math.abs(changeInX) <= 1 && Math.abs(changeInY) <= 1) {
        if (board[y][x] != null) {
          takenPieces.add(board[y][x].toString());
        }
        return true;
      }
    }
    return false;
  }
}

class Queen extends Piece {

  public Queen(boolean isWhite) {
    super(isWhite);
  }

  public String toString() {
    return super.toString() + "Q" + Colors.ANSI_RESET;
  }

  public boolean clearPath(Piece[][] board, int[] oldPos, int[] newPos) {
    int changeInX = newPos[1] - oldPos[1];
    int changeInY = newPos[0] - oldPos[0];
    if (changeInX == 0) {
      for (int i = oldPos[0]; i < newPos[0] - Math.signum(changeInY); i += Math.signum(changeInY)) {
        if (board[i][oldPos[1]] != null) {
          return false;
        }
      }
      return true;
    } else if (changeInY == 0) {
      for (int j = oldPos[1]; j < newPos[1] - Math.signum(changeInX); j += Math.signum(changeInX)) {
        if (board[oldPos[0]][j] != null) {
          return false;
        }
      }
      return true;
    } else if (changeInX == changeInY) {
      int j = oldPos[1];
      for (int i = oldPos[0]; i < newPos[0] - Math.signum(changeInY); i += Math.signum(changeInY)) {
        if (board[i][j] != null) {
          return false;
        }
        i += Math.signum(changeInY);
      }
      return true;
    }
    return false;
  }

  public boolean verifyMove(Piece[][] board, int[] oldPos, String position) {
    if (validPlacement(board, position, oldPos)) {
      String letter = Character.toString(position.charAt(0));
      String number = Character.toString(position.charAt(1));
      int num = Integer.valueOf(number);
      int x = Main.convertNumPos(num);
      int y = Main.convertLetterPos(letter);
      int changeInX = x - oldPos[1];
      int changeInY = y - oldPos[0];
      if (clearPath(board, oldPos, new int[] { y, x })) {
        if (board[y][x] != null) {
          takenPieces.add(board[y][x].toString());
        }
        return true;
      }
    }
    return false;
  }
}

class Rook extends Piece {

  public Rook(boolean isWhite) {
    super(isWhite);
  }

  public String toString() {
    return super.toString() + "R" + Colors.ANSI_RESET;
  }

  public boolean clearPath(Piece[][] board, int[] oldPos, int[] newPos) {
    int changeInX = newPos[1] - oldPos[1];
    int changeInY = newPos[0] - oldPos[0];
    if (changeInX == 0) {
      for (int i = oldPos[0]; i < newPos[0] - Math.signum(changeInY); i += Math.signum(changeInY)) {
        if (board[i][oldPos[1]] != null) {
          return false;
        }
      }
      return true;
    } else if (changeInY == 0) {
      for (int j = oldPos[1]; j < newPos[1] - Math.signum(changeInX); j += Math.signum(changeInX)) {
        if (board[oldPos[0]][j] != null) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  public boolean verifyMove(Piece[][] board, int[] oldPos, String position) {
    if (validPlacement(board, position, oldPos)) {
      String letter = Character.toString(position.charAt(0));
      String number = Character.toString(position.charAt(1));
      int num = Integer.valueOf(number);
      int x = Main.convertNumPos(num);
      int y = Main.convertLetterPos(letter);
      int changeInX = x - oldPos[1];
      int changeInY = y - oldPos[0];
      if (clearPath(board, oldPos, new int[] { y, x })) {
        if (board[y][x] != null) {
          takenPieces.add(board[y][x].toString());
        }
        return true;
      }
    }
    return false;
  }
}

class Knight extends Piece {

  public Knight(boolean isWhite) {
    super(isWhite);
  }

  public String toString() {
    return super.toString() + "N" + Colors.ANSI_RESET;
  }

  public boolean verifyMove(Piece[][] board, int[] oldPos, String position) {
    if (validPlacement(board, position, oldPos)) {
      String letter = Character.toString(position.charAt(0));
      String number = Character.toString(position.charAt(1));
      int num = Integer.valueOf(number);
      int x = Main.convertNumPos(num);
      int y = Main.convertLetterPos(letter);
      int changeInX = x - oldPos[1];
      int changeInY = y - oldPos[0];
      if ((Math.abs(changeInX) == 1 && Math.abs(changeInY) == 2)
          || (Math.abs(changeInX) == 2 && Math.abs(changeInY) == 1)) {
        if (board[y][x] != null) {
          takenPieces.add(board[y][x].toString());
        }
        return true;
      }
    }
    return false;
  }
}

class Bishop extends Piece {

  public Bishop(boolean isWhite) {
    super(isWhite);
  }

  public String toString() {
    return super.toString() + "B" + Colors.ANSI_RESET;
  }

  public boolean clearPath(Piece[][] board, int[] oldPos, int[] newPos) {
    int changeInX = newPos[1] - oldPos[1];
    int changeInY = newPos[0] - oldPos[0];
    if (changeInX == changeInY) {
      int j = oldPos[1];
      for (int i = oldPos[0]; i < newPos[0] - Math.signum(changeInY); i += Math.signum(changeInY)) {
        if (board[i][j] != null) {
          return false;
        }
        i += Math.signum(changeInY);
      }
      return true;
    }
    return false;
  }

  public boolean verifyMove(Piece[][] board, int[] oldPos, String position) {
    if (validPlacement(board, position, oldPos)) {
      String letter = Character.toString(position.charAt(0));
      String number = Character.toString(position.charAt(1));
      int num = Integer.valueOf(number);
      int x = Main.convertNumPos(num);
      int y = Main.convertLetterPos(letter);
      int changeInX = x - oldPos[1];
      int changeInY = y - oldPos[0];
      if (board[y][x] != null) {
        takenPieces.add(board[y][x].toString());
      }
      return true;
    }
    return false;
  }
}

class Pawn extends Piece {

  public boolean firstMove = true;

  public Pawn(boolean isWhite) {
    super(isWhite);
  }

  public String toString() {
    return super.toString() + "P" + Colors.ANSI_RESET;
  }

  public boolean verifyMove(Piece[][] board, int[] oldPos, String position) {
    if (validPlacement(board, position, oldPos)) {
      String letter = Character.toString(position.charAt(0));
      String number = Character.toString(position.charAt(1));
      int num = Integer.valueOf(number);
      int x = Main.convertNumPos(num);
      int y = Main.convertLetterPos(letter);
      int changeInX = x - oldPos[1];
      int changeInY = y - oldPos[0];
      int yMult = (this.isWhite) ? 1 : -1;
      if (changeInX == 0 && changeInY == yMult && board[y][x] == null) {
        firstMove = false;
        return true;
      } else if (firstMove && changeInX == 0 && changeInY == 2 * yMult && board[y][x] == null) {
        firstMove = false;
        return true;
      } else if (Math.abs(changeInX) == yMult && board[y][x] != null && board[y][x].isWhite != this.isWhite) {
        firstMove = false;
        return true;
      }
    }
    return false;
  }
}