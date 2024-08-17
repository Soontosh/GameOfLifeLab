import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

    /*
     *  This is the Model component.
     */

    private static int SIZE = 60;
    private LifeCell[][] grid;
    
    LifeView myView;
    Timer timer;

    /** Construct a new model using a particular file */
    public LifeModel(LifeView view, String fileName) throws IOException
    {       
        int r, c;
        grid = new LifeCell[SIZE][SIZE];
        for ( r = 0; r < SIZE; r++ )
            for ( c = 0; c < SIZE; c++ )
                grid[r][c] = new LifeCell();

        if ( fileName == null ) //use random population
        {                                           
            for ( r = 0; r < SIZE; r++ )
            {
                for ( c = 0; c < SIZE; c++ )
                {
                    if ( Math.random() > 0.85) //15% chance of a cell starting alive
                        grid[r][c].setAliveNow(true);
                }
            }
        }
        else
        {                 
            Scanner input = new Scanner(new File(fileName));
            int numInitialCells = input.nextInt();
            for (int count=0; count<numInitialCells; count++)
            {
                r = input.nextInt();
                c = input.nextInt();
                grid[r][c].setAliveNow(true);
            }
            input.close();
        }

        myView = view;
        myView.updateView(grid);

    }

    /** Constructor a randomized model */
    public LifeModel(LifeView view) throws IOException
    {
        this(view, null);
    }

    /** pause the simulation (the pause button in the GUI */
    public void pause()
    {
        timer.stop();
    }
    
    /** resume the simulation (the pause button in the GUI */
    public void resume()
    {
        timer.restart();
    }
    
    /** run the simulation (the pause button in the GUI */
    public void run()
    {
        timer = new Timer(50, this);
        timer.setCoalesce(true);
        timer.start();
    }

    /** called each time timer fires */
    public void actionPerformed(ActionEvent e)
    {
        oneGeneration();
        myView.updateView(grid);
    }

	/** Custom Function to Help Me Out **/
	private int countNeighbors(int row, int col) {
		int count = 0;

		// Check for neighbors
		if (row > 0 && col > 0 && grid[row - 1][col - 1].isAliveNow()) {
			count++;
		}

		if (row > 0 && grid[row - 1][col].isAliveNow()) {
			count++;
		}

		if (row > 0 && col < SIZE - 1 && grid[row - 1][col + 1].isAliveNow()) {
			count++;
		}

		if (col > 0 && grid[row][col - 1].isAliveNow()) {
			count++;
		}

		if (col < SIZE - 1 && grid[row][col + 1].isAliveNow()) {
			count++;
		}

		if (row < SIZE - 1 && col > 0 && grid[row + 1][col - 1].isAliveNow()) {
			count++;
		}

		if (row < SIZE - 1 && grid[row + 1][col].isAliveNow()) {
			count++;
		}

		if (row < SIZE - 1 && col < SIZE - 1 && grid[row + 1][col + 1].isAliveNow()) {
			count++;
		}

		return count;
	}

    /** main logic method for updating the state of the grid / simulation */
    private void oneGeneration()
    {
        LifeCell[][] newGrid = new LifeCell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int g = 0; g < SIZE; g++) {
                newGrid[i][g] = new LifeCell();
                if (grid[i][g].isAliveNow()) {
                    if (countNeighbors(i, g) < 2 || countNeighbors(i, g) > 3) {
                        newGrid[i][g].setAliveNow(false);
                    } else {
                        newGrid[i][g].setAliveNow(true);
                    }
                } else {
                    if (countNeighbors(i, g) == 3) {
                        newGrid[i][g].setAliveNow(true);
                    } else {
                        newGrid[i][g].setAliveNow(false);
                    }
                }
            }
        }
        grid = newGrid;
    }
}

