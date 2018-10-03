//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (descriptive title of the program making use of this file)
// Files:           (Maze.java, TestStack.java , MazeRunnerStack.java, StackADT.java)
// Course:          (CS300 Spring 17 )
//
// Author:          (Andrew Goethel)
// Email:           (agoethel@wisc.edu)
// Lecturer's Name: (Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Maze {
	private MazeRunnerStack path = null;
	private MazeRunnerStack solvedPath = null;
	private Boolean solved = null;
	private char[][] mazeInfo;
	private int[] startLoc;  // array containing row & col of start location
	private int[] finishLoc; // array containing row & col of finish location
	private char facing; // orientation variable, N,E,S,W are supported.
	private nick[] solvedArray; // the array of the solved stack

	/** The main method of Maze. Used for testing different mazes against the solving algorithm.
	 * @param args
	 */
	public static void main(String[] args) {
		char[][] testMaze = new char[4][3];
		testMaze[0][0] = '_';
		testMaze[0][1] = '.';
		testMaze[0][2] = '|';


		testMaze[1][0] = '.';
		testMaze[1][1] = '_';
		testMaze[1][2] = '.';


		testMaze[2][0] = '.';
		testMaze[2][1] = '|';
		testMaze[2][2] = '|';

		testMaze[3][0] = '_';
		testMaze[3][1] = '_';
		testMaze[3][2] = 'L';
		char[][] ezMaze = {{'_','.','|'},{'_','_','_'}};



		Maze testMazeClass = new Maze(testMaze);
		testMazeClass.setStart(0,0);
		testMazeClass.setFinish(0,2);

		testMazeClass.solveMaze();
		testMazeClass.displayMaze();

	}

	/** General constructor for Maze Class
	 * @param mazeInfo the char array of characters necessary to display the maze
	 */
	public Maze(char[][] mazeInfo) {
		this.mazeInfo = mazeInfo;
	}

	/** A setter method to set the start position of the Maze Object
	 * @param row  The row of the start location in the maze. 0 based.
	 * @param col The column of the start location in the maze. 0 based.
	 */
	public void setStart(int row, int col) {
		startLoc =  new int[2];
		startLoc[0] = row;
		startLoc[1] = col;
	}
	/** A setter method to set the finish position of the Maze Object
	 * @param row  The row of the finish location in the maze. 0 based.
	 * @param col The column of the finish location in the maze. 0 based.
	 */
	public void setFinish(int row, int col) {
		finishLoc =  new int[2];
		finishLoc[0] = row;
		finishLoc[1] = col;
	}
	/** A method that prints the maze to the console in ASCII graphics. Can be called after a solved maze to show steps
	 * graphically, or before the maze is solved to show the bare maze. Reads the char array mazeInfo for instructions 
	 * on what each cell should be displayed as.
	 * 
	 */
	public void displayMaze() {

		if(solved != null && solved ) {	
			System.out.println("Solution is:");
			System.out.print("+");
			// Printing the top bar of the maze no matter
			for(int i = 0 ; i < mazeInfo[0].length; ++i) {
				System.out.print("---+");
			}
			System.out.println();
			for(int i = 0; i < mazeInfo.length ; ++i) {
				for(int j = 0; j < mazeInfo[i].length ; ++j) {
					nick p = new nick(i,j);
					if(j == 0) {
						// Checking if the current cell has a Start, Finish location , or cell in the stack 
						//in, printing it if it does
						if(startLoc != null && startLoc[0] == i && startLoc[1] == j) {
							System.out.print("| S ");
							continue;
						}
						else if (finishLoc != null &&finishLoc[0] == i && finishLoc[1] == j) {
							System.out.print("| F ");
							continue;
						}
						else if(searchSolved(i,j)) {
							System.out.print("| * ");
						}
						else {
							System.out.print("|   ");
							continue;
						}
					}
					else if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '|') {
						// Checking if the current cell has a Start, Finish location , or cell in the stack 
						//in, printing it if it does
						if(startLoc != null && startLoc[0] == i && startLoc[1] == j) {
							System.out.print("| S ");
							continue;
						}
						else if (finishLoc != null &&finishLoc[0] == i && finishLoc[1] == j) {
							System.out.print("| F ");
							continue;
						}
						else if(searchSolved(i,j)) {
							System.out.print("| * ");
						}
						else {
							System.out.print("|   ");
							continue;
						}
					}
					else {
						// Checking if the current cell has a Start, Finish location , or cell in the stack 
						//in, printing it if it does
						if(startLoc != null && startLoc[0] == i && startLoc[1] == j) {
							System.out.print("  S ");
							continue;
						}
						else if(finishLoc != null && finishLoc[0] == i && finishLoc[1] == j) {
							System.out.print("  F ");
							continue;
						}
						else if(searchSolved(i,j)) {
							System.out.print("  * ");
						}
						else {
							System.out.print("    ");
						}
					}


				}
				System.out.println("|");
				// Horizontal Lines
				for(int k = 0 ; k < mazeInfo[i].length ; ++k) {
					if (k == 0) {
						System.out.print("+");
					}
					if(mazeInfo[i][k] == 'L' || mazeInfo[i][k] == '_') {
						System.out.print("---+");
					}
					else if(mazeInfo[i][k] == '.' || mazeInfo[i][k] == '|') {
						System.out.print("   +");
					}
				}
				System.out.println();
			}
			System.out.print("Path is: ");
			// a for loop printing the stack of the solution
			for (int j = 0; j < solvedArray.length; ++j) {
				if(j == 0) {
					System.out.print("["+solvedArray[j].row +"," +solvedArray[j].col+"]");

				}
				else {
					System.out.print(" --> ["+solvedArray[j].row +"," +solvedArray[j].col+"]");

				}

			}
		}
		else {	
			// A no solution / solve hasnt been called. Prints the maze without asterisks, just the maze and S & F
			if(solved != null && solved == false) {
				System.out.println("No solution could be found.");

			}
			System.out.print("+");
			for(int i = 0 ; i < mazeInfo[0].length; ++i) {
				System.out.print("---+");
			}
			System.out.println();
			for(int i = 0; i < mazeInfo.length ; ++i) {
				for(int j = 0; j < mazeInfo[i].length ; ++j) {
					if(j == 0) {
						// Checking if the current cell has a Start, Finish location in, printing it if it does
						if(startLoc != null && startLoc[0] == i && startLoc[1] == j) {
							System.out.print("| S ");
							continue;
						}
						else if (finishLoc != null &&finishLoc[0] == i && finishLoc[1] == j) {
							System.out.print("| F ");
							continue;
						}
						else {
							System.out.print("|   ");
							continue;
						}
					}
					else if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '|') {
						// Checking if the current cell has a Start, Finish location in, printing it if it does
						if(startLoc != null && startLoc[0] == i && startLoc[1] == j) {
							System.out.print("| S ");
							continue;
						}
						else if (finishLoc != null &&finishLoc[0] == i && finishLoc[1] == j) {
							System.out.print("| F ");
							continue;
						}
						else {
							System.out.print("|   ");
							continue;
						}
					}
					else {
						// Checking if the current cell has a Start, Finish location in, printing it if it does
						if(startLoc != null && startLoc[0] == i && startLoc[1] == j) {
							System.out.print("  S ");
							continue;
						}
						else if(finishLoc != null && finishLoc[0] == i && finishLoc[1] == j) {
							System.out.print("  F ");
							continue;
						}
						else {
							System.out.print("    ");
						}
					}


				}
				System.out.println("|");
				// Horizontal Line Printing
				for(int k = 0 ; k < mazeInfo[i].length ; ++k) {
					if (k == 0) {
						System.out.print("+");
					}
					if(mazeInfo[i][k] == 'L' || mazeInfo[i][k] == '_') {
						System.out.print("---+");
					}
					else if(mazeInfo[i][k] == '.' || mazeInfo[i][k] == '|') {
						System.out.print("   +");
					}
				}
				System.out.println();
			}
		}


	}
	/** A method which attempts to solve the maze. By using orientation and the right hand rule for solving mazes, 
	 * which prioritizes right then straight then left then back, it attempts to create a stack of steps to solve 
	 * the maze set in mazeInfo
	 * Maximum step counter is in place to ensure a infinite loop is not obtained
	 * 
	 */
	public void solveMaze() {
		MazeRunnerStack path = new MazeRunnerStack();
		path.push(new nick(startLoc[0],startLoc[1]));
		facing = 'E';
		int stepCounter = 1;
		// Solving Algorithm, first checks to make sure stack isnt empty before attempting a direction
		for(int i = 0 ; i < mazeInfo[0].length*mazeInfo.length*4 ; ++i) {	
			if(path.isEmpty()) {
				solved = false;
				return;
			}
			if(path.peek().row == finishLoc[0] && path.peek().col == finishLoc[1]) {
				solved = true;
				solvedArray = new nick[stepCounter];	
				// for loop for generating an array of the solved steps since the people that made this assignment
				// wont let me use getters for size
				for ( int i1 = solvedArray.length-1; i1 >= 0 ; --i1) {
					solvedArray[i1] = path.pop();

				}
				optimizePath();
				this.path = path;	
				return;
			}

			if(facing =='N') {
				// Facing North turning right
				if(path.peek().col != mazeInfo[path.peek().row].length-1 && 
						mazeInfo[path.peek().row][path.peek().col+1] != 'L' && 
						mazeInfo[path.peek().row][path.peek().col+1] != '|' ) {
					path.push(new nick(path.peek().row,path.peek().col+1));
					facing = 'E';
					++stepCounter;
					continue;
				}
				// Facing North going straight
				else if(path.peek().row != 0 && mazeInfo[path.peek().row-1][path.peek().col] != 'L' 
						&& mazeInfo[path.peek().row-1][path.peek().col] != '_' ) {
					path.push(new nick(path.peek().row-1,path.peek().col));
					facing = 'N';
					++stepCounter;

					continue;
				}
				// Facing North going left
				else if(path.peek().col != 0 && mazeInfo[path.peek().row][path.peek().col] != 'L' 
						&& mazeInfo[path.peek().row][path.peek().col] != '|' ) {
					path.push(new nick(path.peek().row,path.peek().col-1));
					facing = 'W';
					++stepCounter;

				}
				// Facing North, unable to proceed. Backtracking.
				else {
					path.pop();
					facing = 'S';
					--stepCounter;
				}
			}
			else if(facing == 'E') {
				// Facing East going right
				if(path.peek().row != mazeInfo.length-1 && 
						mazeInfo[path.peek().row][path.peek().col] != 'L' && 
						mazeInfo[path.peek().row][path.peek().col] != '_' ) {
					path.push(new nick(path.peek().row+1,path.peek().col));
					facing = 'S';
					++stepCounter;

					continue;
				}
				// Facing East going straight
				else if(path.peek().col != mazeInfo[path.peek().row].length-1 
						&& mazeInfo[path.peek().row][path.peek().col+1] != '|'
						&& mazeInfo[path.peek().row][path.peek().col+1] != 'L') {
					path.push(new nick(path.peek().row,path.peek().col+1));
					++stepCounter;

					continue;
				}
				// Facing East going left
				else if(path.peek().row != 0 && mazeInfo[path.peek().row-1][path.peek().col] != 'L' 
						&& mazeInfo[path.peek().row-1][path.peek().col] != '_' ) {
					path.push(new nick(path.peek().row-1,path.peek().col));
					++stepCounter;

					facing = 'N';
				}
				// Facing East, unable to proceed.
				else {
					path.pop();
					facing = 'W';
					--stepCounter;

				}

			}
			else if(facing == 'S') {
				// Facing S turning right
				if(path.peek().col != 0 && 
						mazeInfo[path.peek().row][path.peek().col] != '|') {
					path.push(new nick(path.peek().row,path.peek().col-1));
					facing = 'W';
					++stepCounter;

					continue;
				}
				// Facing S going straight
				else if(path.peek().col != mazeInfo[path.peek().row].length-1 
						&& mazeInfo[path.peek().row][path.peek().col] != '_' &&
						mazeInfo[path.peek().row][path.peek().col] != 'L') {
					path.push(new nick(path.peek().row+1,path.peek().col));
					++stepCounter;
					continue;
				}
				// Facing S going left
				else if(path.peek().col != mazeInfo[path.peek().row].length-1 
						&& mazeInfo[path.peek().row][path.peek().col+1] != '|' 
						&& mazeInfo[path.peek().row][path.peek().col+1] != 'L' ) {
					path.push(new nick(path.peek().row,path.peek().col+1));
					++stepCounter;

					facing = 'E';
					continue;
				}
				// Facing S, unable to proceed
				else {
					path.pop();
					facing = 'N';
					--stepCounter;

				}
			}
			else if(facing == 'W') {
				// Facing W , turning right
				if(path.peek().row != 0 && 
						mazeInfo[path.peek().row-1][path.peek().col] != 'L' && 
						mazeInfo[path.peek().row-1][path.peek().col] != '_') {

					path.push(new nick(path.peek().row-1,path.peek().col));
					facing = 'N';
					++stepCounter;

					continue;
				}
				// Facing W, going straight
				else if(path.peek().col != 0 && mazeInfo[path.peek().row][path.peek().col] != '|' &&
						mazeInfo[path.peek().row][path.peek().col] != 'L') {

					path.push(new nick(path.peek().row,path.peek().col-1));
					facing = 'W';
					++stepCounter;

					continue;
				}
				// Facing W, turning left
				else if(path.peek().row != mazeInfo.length-1  && mazeInfo[path.peek().row][path.peek().col] != '_' 
						&& mazeInfo[path.peek().row][path.peek().col] != 'L' ) {

					path.push(new nick(path.peek().row+1,path.peek().col));
					facing = 'S';
					++stepCounter;

					continue;
				}
				else {
					// Facing W, unable to proceed
					path.pop();
					facing = 'E';
					--stepCounter;
				}
			}



		}
		solved = false;


	}
	/** A method for optimizing the path solution path (I.E if the solution backtracks through itself, removes the 
	 * pathing between duplicate positions.
	 * 
	 */
	public void optimizePath() {
		nick[] optimizedArray = new nick[1];
		boolean foundDup = false;
		int dif = 0;
		int k1 = 0;
		outerloop:
			// for loop for iterating through every position's row & column and comparing them with every other 
			// positions row & column to see if there is a match.
			for ( int i = 0 ; i < solvedArray.length; ++i) {
				for (int j = 0 ; j < solvedArray.length  ; j++) {

					if(solvedArray[i].row == solvedArray[j].row && solvedArray[i].col == solvedArray[j].col && i != j) {
						//Match is found, j and i share the same coords
						dif = Math.abs(j-i); // Calculating the difference between j and i , I.E how many positions 
						// should be removed
						optimizedArray = new nick[solvedArray.length - dif];
						// for loop for placing the coords before the first coord of the matched pair and placing them
						// into the optimized array
						for(int k = 0 ; k < i ; ++k) {
							optimizedArray[k] = solvedArray[k];
							k1 =k;

						}
						// for loop for placing the coords after the second coord of the matched pair and placing them
						// into the optimized array
						int n = k1+1;
						for(int m = j ; m < solvedArray.length  ; ++m) { 

							optimizedArray[n] = solvedArray[m];
							++n;
						}
						foundDup = true;
						break outerloop;

					}
				}
			}
		// if there was a duplicate found, set the solved array to the optimized array, otherwise, return without
		// changes
		if(foundDup) {
			solvedArray = optimizedArray;
		}
		return;
	}

	/** Searches the solved path array for a position that matches the passed in coordinates.
	 * @param row row of cell to be searched for
	 * @param col column of cell to be searched for
	 * @return true/false of whether there is a cell contained in that array.
	 */ 
	public boolean searchSolved(int row , int col) {
		for ( int i = 0 ; i < solvedArray.length - 1 ; ++i) {
			if(solvedArray[i].row == row && solvedArray[i].col == col) {
				return true;
			}
		}
		return false;
	}
}
