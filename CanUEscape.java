package acmclub;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//https://github.com/OSUACM/Weekly_Events/blob/master/180217.md
public class CanUEscape {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("src/acmclub/escapeInput.txt"));
		ArrayList<String> inputLines = new ArrayList<String>();
		int totalLines = 0;
		// find total number of lines
		while (sc.hasNextLine()) {
			totalLines++;
			inputLines.add(sc.nextLine());
		}
		sc.close();
		// store input in char[]
		char[][] maze = new char[totalLines][inputLines.get(0).length()];
		for (int i = 0; i < maze.length; i++) {
			maze[i] = inputLines.get(i).toCharArray();
		}
		// find the U
		Position uPos = new Position(0, 0);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == 'U') {
					uPos = new Position(i, j);
				}
			}
		}
		// Stores if we found a way out
		boolean foundWayOut = false;
		// list of positions that can be reached. It will start at U
		// and slowly spread out.
		Queue<Position> posToCheck = new LinkedList<Position>();
		// list of all the positions that have been or are in adjacent,
		// so we don't visit them twice.
		boolean[][] seen = new boolean[maze.length][maze[0].length];

		posToCheck.add(uPos);
		seen[uPos.row][uPos.col] = true;
		while (!posToCheck.isEmpty()) {
			Position next = posToCheck.poll();
			if (next.row == 0 || next.col == 0 || next.row == maze.length - 1 || next.col == maze[0].length - 1) {
				// this is at the edge, so it is possible!
				foundWayOut = true;
			} else {
				// Note that, at this point, bounds do not need to be checked as we already have
				// ensured that this point isn't on the edge
				
				//for each direction, add a position to the list of positions to check only if 
				//it is a valid spot in the maze and we have not already added it to the list.
				if (maze[next.row][next.col - 1] == '.' && !seen[next.row][next.col - 1]) {
					Position left = new Position(next.row, next.col - 1);
					seen[next.row][next.col-1] = true;
					posToCheck.add(left);
				}
				if (maze[next.row][next.col + 1] == '.' && !seen[next.row][next.col + 1]) {
					Position right = new Position(next.row, next.col + 1);
					seen[next.row][next.col+1] = true;
					posToCheck.add(right);
				}
				if (maze[next.row - 1][next.col] == '.' && !seen[next.row - 1][next.col]) {
					Position top = new Position(next.row - 1, next.col);
					seen[next.row-1][next.col] = true;
					posToCheck.add(top);
				}
				if (maze[next.row + 1][next.col] == '.' && !seen[next.row + 1][next.col]) {
					Position bottom = new Position(next.row + 1, next.col);
					seen[next.row+1][next.col] = true;
					posToCheck.add(bottom);
				}
			}
		}
		if (foundWayOut) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}

	public static class Position {
		int row;
		int col;

		public Position(int r, int c) {
			row = r;
			col = c;
		}
	}
}
