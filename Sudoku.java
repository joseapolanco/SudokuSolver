/* A class that has a constructor that takes the given sudoku puzzle and finds the
solution(s) if possible.
 *
 */
package Sudoku;

public class Sudoku
{
    private int[][] game;

    public Sudoku(int[][] g)
    {
        game = new int[g.length][g[0].length];
        for(int i = 0; i < g.length; i++)
        {
            for(int j = 0; j < g[0].length; j++)
                game[i][j] = g[i][j];
        }
    }

    /**Method that takes the starting point  of the sudoku puzzle and utilizes
     * recursion to find the values that correspond to each box.
     *
     * @param r: starting row index
     * @param c: starting column index
     */
    public void solve(int r, int c)
    {
        if(game[r][c] > 0) //There is a number at (r, c)
        {
            move(r,c);
        }
        else //No number at (r, c) yet
        {
            for(int n = 1; n <= 9; n++)
            {
                //Check if n can be placed
                if(rowCheck(r, n) && colCheck(c, n) && blockCheck(r, c, n))
                {
                    game[r][c] = n;
                    move(r,c);
                    game[r][c] = 0;
                }
            }
        }
    }

    /**Method that checks if the given value can be placed in certain row
     *
     * @param row: Given row index
     * @param n: Given value being tested
     * @return: True if can be placed else false
     */
    private boolean rowCheck(int row, int n)
    {
        for(int c = 0; c < game[0].length; c++)
            if(game[row][c] == n)
                return false;
        return true;
    }

    /**Method that checks if the given value can be placed in certain column
     *
     * @param col: Given column index
     * @param n: Given value being tested
     * @return: True if can be placed else false
     */
    private boolean colCheck(int col, int n)
    {
        for(int r = 0; r < game.length; r++)
            if(game[r][col] == n)
                return false;
        return true;
    }

    /**Method that checks if the given value can be placed in block area
     *
     * @param r: Given row index
     * @param c Given column index
     * @param n: Given value being tested
     * @return: True if can be placed else false
     */
    private boolean blockCheck(int r, int c, int n)
    {
        int row = (r/3)*3, col = (c/3)*3; //The corner position of the block where (r,c) is
        for(int i = row; i < row+3; i++)
            for(int j = col; j < col+3; j++)
                if(game[i][j] == n)
                    return false;
        return true;
    }

    /**A method that checks if game is solved else moves to next block if a value is
     * placed.
     *
     * @param r: Row index
     * @param c: Column index
     */
    private void move(int r, int c)
    {
        if(r == game.length - 1 && c == game[0].length - 1)
            this.print();
        else
        {
            if(c < game[0].length - 1)
                solve(r, c+1);
            else
            {
                solve(r+1, 0);
            }
        }
    }

    public void print()
    {
        for(int i = 0; i < game.length; i++)
        {
            for(int j = 0; j < game[0].length; j++)
                System.out.print(game[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
