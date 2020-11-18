import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameOfLife {

  public static void main(String[] args) {
    new GameOfLife().game("input.txt", "output.txt");
  }

  @SneakyThrows
  public void game(String fileNameInput, String fileNameOutput){
    Stream<String> gameStream = new BufferedReader(
            new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileNameInput))).lines();
    List<String> gameList = gameStream.collect(Collectors.toList());
    String[] properties = gameList.get(0).split(",");
    int rowSize = Integer.parseInt(properties[0]);
    int columnSize = Integer.parseInt(properties[1]);
    int numberOfMoves = Integer.parseInt(properties[2]);
    boolean[][] lifeGeneration = new boolean[rowSize][columnSize];

    for (int r=0; r < rowSize; r++) {
      String[] gameLine = gameList.get(r+1).split(" ");
      for (int c=0; c < columnSize; c++) {
        if (gameLine[c].equals("X")) {
          lifeGeneration[r][c] = true;
        } else {lifeGeneration[r][c] = false;}
      }
    }
    GameLife gameLife = new GameLife(rowSize, columnSize, lifeGeneration, numberOfMoves);
    gameLife.game();
    String result = gameLife.printResult();
    File file = Paths.get(
            getClass().getProtectionDomain().getCodeSource().getLocation().toURI()
    ).resolve(Paths.get(fileNameOutput)).toFile();
    try (FileOutputStream fos = new FileOutputStream(file)) {
      byte[] mybytes = result.getBytes();
      fos.write(mybytes);
    }
  }

}