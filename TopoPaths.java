// ====================
// TopoPaths.java
// ====================

import java.io.*;
import java.util.*;

public class TopoPaths
{
	//isTopoPath is mainly Dr. Szumlanski's TopoSort code from
	//webcourses. Some alterations were made for the scope of this
	//program.
	public boolean isTopoPath(boolean [][] matrix, int vertexCount)
	{
		//initialize incoming[] array
		int cnt = 0;
		int [] incoming = new int[matrix.length];
		for (int i = 1; i < matrix.length; i++)
		{
			for (int j = 1; j < matrix.length; j++)
			{
				incoming[j] += (matrix[i][j] ? 1 : 0);
			}
		}
		//initialize Queue
		Queue<Integer> q = new ArrayDeque<Integer>();

		for (int i = 1; i < matrix.length; i++)
		{
			if (incoming[i] == 0)
			{
				q.add(i);
			}
		}
		//topologically order vertices
		while (!q.isEmpty())
		{
			int node = q.remove();
			++cnt;

			for (int i = 1; i < matrix.length; i++)
			{
				if (matrix[node][i] && --incoming[i] == 0)
				{
					q.add(i);
				}
			}
		}
		//check if topological sort is a cycle. A true
		// topological sort cannot have one.
		if (cnt != matrix.length - 1)
		{
			return false;
		}
		//check if the topological sort is also a path.
		for (int i = 1; i < vertexCount; i++)
		{
			if (!matrix[i][i + 1])
			{
				return false;
			}
		}
		//To be a topopath the graph must be both a
		//topological sort and a path.
		return true;
	}

	public static int countTopoPaths(String filename) throws Exception
	{
		Scanner in = new Scanner(new File(filename));
		int vertexCount = in.nextInt();
		boolean [][] matrix = new boolean [vertexCount + 1][vertexCount + 1];
		TopoPaths Test = new TopoPaths();
		int [] inDegrees = new int[vertexCount];
		//Initialize matrix[][]
		for(int i = 1; i <= vertexCount; i++)
		{
			int outDegrees = in.nextInt();

			for(int j = 1; j <= outDegrees; j++)
			{
				int J = in.nextInt();
				matrix[i][J] = true;
			}
		}
		//If the graph is a topopath then there is 1 of its kind,
		//else there are none.
		if (!Test.isTopoPath(matrix, vertexCount))
		{
			return 0;
		}
		else
		{
			return 1;
		}

	}

	

}