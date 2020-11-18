
public class GameLife {
  private int rowSize;
  private int columnSize;
  private boolean[][] lifeGeneration;
  private  int numberOfMoves;
  boolean[][] nextGeneration;

  public GameLife(int rowSize, int columnSize, boolean[][] lifeGeneration, int numberOfMoves) {
    this.rowSize = rowSize;
    this.columnSize = columnSize;
    this.lifeGeneration = lifeGeneration;
    this.numberOfMoves = numberOfMoves;
    this.nextGeneration =  new boolean[rowSize][columnSize];
  }

  public void game() {
    while (numberOfMoves > 0) {
        processOfLife();
        numberOfMoves--;
      }
  }

  private void processOfLife() {
    for (int x = 0; x < rowSize; x++) {
      for (int y = 0; y < columnSize; y++) {
        int count = countNeighbors(x, y);
        nextGeneration[x][y] = lifeGeneration[x][y];
        if (count == 3) {
          nextGeneration[x][y] = true;
        } else if ((count < 2) || (count > 3)) {
          nextGeneration[x][y] = false;
        } else { nextGeneration[x][y] = nextGeneration[x][y];}
      }
    }
    for (int x = 0; x < rowSize; x++) {
      System.arraycopy(nextGeneration[x], 0, lifeGeneration[x], 0, columnSize);
    }
  }

  private int countNeighbors(int x, int y) {
    int count = 0;
    for (int dx = -1; dx < 2; dx++) {
      for (int dy = -1; dy < 2; dy++) {
        int nX = x + dx;
        int nY = y + dy;
        if (nX < 0) {
          nX =  rowSize - 1;
        }
        if (nY < 0) {
          nY =  columnSize - 1;
        }
        if (nX > rowSize - 1) {
          nX =  0;
        }
        if (nY > columnSize - 1) {
          nY =  0;
        }
        count += (lifeGeneration[nX][nY]) ? 1 : 0;
      }
    }
    if (lifeGeneration[x][y]) { count--; }
    return count;
  }

  public String printResult() {
    StringBuilder sb = new StringBuilder();
    
    for (int x = 0; x < rowSize; x++) {
      for (int y = 0; y < columnSize; y++) {

        if (lifeGeneration[x][y]) sb.append("X ");
        else if (!lifeGeneration[x][y]) {sb.append("O ");}
      }
      sb.append("\n");
    }
    String result = sb.toString().replaceAll("\\s\n", "\n");
    return result;
  }
}
