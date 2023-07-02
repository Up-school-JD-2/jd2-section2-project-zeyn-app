package games;

public class MineSweeper {
    private final int rowCount;
    private final int columnCount;
    private final int[][] mineMatrix;
    private final int mineCount;

    public MineSweeper(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        mineMatrix = new int[rowCount][columnCount];
        mineCount = rowCount * columnCount / 4;
    }

    public int getMineCount(){
        return mineCount;
    }
    public int getRowCount(){
        return rowCount;
    }
    public int getColumnCount(){
        return columnCount;
    }

    private void createMine() {
        int rowIndex, columnIndex;

        for (int i = 0; i < mineCount; i++) {
            rowIndex = (int) (Math.random() * (rowCount - 1));
            columnIndex = (int) (Math.random() * (columnCount - 1));

            while (mineMatrix[rowIndex][columnIndex] == -1) {
                rowIndex = (int) (Math.random() * (rowCount - 1)) ;
                columnIndex = (int) (Math.random() * (columnCount - 1)) ;
            }
            mineMatrix[rowIndex][columnIndex] = -1;
        }
    }

    private void calculateNeighborMine() {
        createMine();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (mineMatrix[i][j] == -1) {
                    if (i > 0) {
                        if (j > 0 && mineMatrix[i - 1][j - 1] != -1) mineMatrix[i - 1][j - 1]++;
                        if (j < columnCount - 1 && mineMatrix[i - 1][j + 1] != -1) mineMatrix[i - 1][j + 1]++;
                        if (mineMatrix[i - 1][j] != -1) mineMatrix[i - 1][j]++;
                    }
                    if (i < rowCount - 1) {
                        if (j > 0 && mineMatrix[i + 1][j - 1] != -1) mineMatrix[i + 1][j - 1]++;
                        if (j < columnCount - 1 && mineMatrix[i + 1][j + 1] != -1) mineMatrix[i + 1][j + 1]++;
                        if (mineMatrix[i + 1][j] != -1) mineMatrix[i + 1][j]++;
                    }
                    if (j > 0 && mineMatrix[i][j - 1] != -1) mineMatrix[i][j - 1]++;
                    if (j < columnCount - 1 && mineMatrix[i][j + 1] != -1) mineMatrix[i][j + 1]++;
                }
            }
        }
    }

    public String[][] createGameBoard() {
        String[][] gameBoard = new String[rowCount][columnCount];
        calculateNeighborMine();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (mineMatrix[i][j] == -1) {
                    gameBoard[i][j] = "*";
                } else {
                    gameBoard[i][j] = Integer.toString(mineMatrix[i][j]);
                }
            }
        }
        return gameBoard;
    }

    public String[][] createMaskedGameBoard() {
        String[][] gameBoard = new String[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                gameBoard[i][j] = "-";
            }
        }
        return gameBoard;
    }

    public void printGameBoard(String[][] gameBoard) {
        System.out.print(" \t");

        for(int i=0; i<columnCount; i++){
            System.out.print(i + "\t");
        }

        for (int i = 0; i < rowCount; i++) {
            System.out.print("\n" +i + "\t");
            for (int j = 0; j < columnCount; j++) {
                System.out.print(gameBoard[i][j] + "\t");
            }
        }

        System.out.println();
    }
}