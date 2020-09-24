import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
        printGrid(grid);

        boolean end = false;
        int turn = 1;
        char player = 'X';

        while (!end) {
            System.out.println("Enter the coordinates:");

            if (turn % 2 != 0) {
                player = 'X';
            } else {
                player = 'O';
            }
            enterCoordinates(grid, player);
            printGrid(grid);

            switch (checkWinner(grid)) {
                case "X wins":
                    System.out.println("X wins");
                    end = true;
                    break;
                case "O wins":
                    System.out.println("O wins");
                    end = true;
                    break;
                case "Draw":
                    System.out.println("Draw");
                    end = true;
                    break;
                default:
                    break;
            }
            turn++;
        }
    }

    static void printGrid(char[][] grid) {
        System.out.println("---------");
        System.out.println("| " + grid[0][0] + " " + grid[0][1] + " " + grid[0][2] + " |");
        System.out.println("| " + grid[1][0] + " " + grid[1][1] + " " + grid[1][2] + " |");
        System.out.println("| " + grid[2][0] + " " + grid[2][1] + " " + grid[2][2] + " |");
        System.out.println("---------");
    }

    static char[][] enterCoordinates(char[][] grid, char player) {
        while (true) {
            String[] coordinates = getCoordinates();
            if (coordinates[0].equals("String") || coordinates[1].equals("String")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else {
                boolean occupied = checkCell(x, y, grid);
                if (occupied) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                } else {
                    grid[3 - y][x - 1] = player;
                    return grid;
                }
            }
        }
    }

    static String[] getCoordinates() {
        Scanner scan = new Scanner(System.in);

        String[] coordinates = new String[2];
        for (int i = 0; i < 2; i++) {
            if (scan.hasNextInt()) {
                coordinates[i] = String.valueOf(scan.nextInt());
            } else if (scan.hasNext()) {
                coordinates[i] = "String";
                return coordinates;
            }
        }
        return coordinates;
    }

    static boolean checkCell(int x, int y, char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 3 - y && j == x - 1) {
                    if (grid[i][j] == 'X' || grid[i][j] == 'O') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static String checkWinner(char[][] grid) {
        String row1 = "" + grid[0][0] + grid[0][1] + grid[0][2];
        String row2 = "" + grid[1][0] + grid[1][1] + grid[1][2];
        String row3 = "" + grid[2][0] + grid[2][1] + grid[2][2];
        String col1 = "" + grid[0][0] + grid[1][0] + grid[2][0];
        String col2 = "" + grid[0][1] + grid[1][1] + grid[2][1];
        String col3 = "" + grid[0][2] + grid[1][2] + grid[2][2];
        String diagonal1 = "" + grid[0][0] + grid[1][1] + grid[2][2];
        String diagonal2 = "" + grid[2][0] + grid[1][1] + grid[0][2];

        boolean xWins = false;
        boolean oWins = false;
        if (row1.equals("XXX") || row2.equals("XXX") || row3.equals("XXX") ||
                col1.equals("XXX") || col2.equals("XXX") || col3.equals("XXX") ||
                diagonal1.equals("XXX") || diagonal2.equals("XXX")) {
            xWins = true;
        }
        if (row1.equals("OOO") || row2.equals("OOO") || row3.equals("OOO") ||
                col1.equals("OOO") || col2.equals("OOO") || col3.equals("OOO") ||
                diagonal1.equals("OOO") || diagonal2.equals("OOO")) {
            oWins = true;
        }

        int emptyCells = 9;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X' || grid[i][j] == 'O') {
                    emptyCells--;
                }
            }
        }

        if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (!xWins && !oWins && emptyCells == 0) {
            return "Draw";
        }
        return "Not finished";
    }
}