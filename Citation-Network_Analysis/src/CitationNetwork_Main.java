import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CitationNetwork_Main 
{
	
	// Functions
	
	private ArrayList<Integer> ThreadSplitBegin (int _numOfThreads, int _numOfLines)
	{
		ArrayList<Integer> linesPerThread = new ArrayList<>();
		int numOfLinesPerThread = (int) _numOfLines / _numOfThreads;
		
		int totalNumOfLines = 0;
		for (int i = 0; i < _numOfThreads; i++) 
		{
			System.out.println(i);
			if (i == 0)
			{linesPerThread.add(0);}
			else
			{
				linesPerThread.add(totalNumOfLines + numOfLinesPerThread);
				totalNumOfLines += numOfLinesPerThread;
			}
		}
		
		return linesPerThread;
	}
	
	private ArrayList<Integer> ThreadListEnd(ArrayList<Integer> _splitBegin, int totalNumLines)
	{
		ArrayList<Integer> linesPerThread = new ArrayList<>();
		
		for (int i = 0; i < _splitBegin.size(); i++)
		{
			if (i == _splitBegin.size() - 1)
			{linesPerThread.add(totalNumLines);}
			else
			{linesPerThread.add(_splitBegin.get(i+1));}
		}
		
		return linesPerThread;
	}
	
	// Static functions

	private static ArrayList<String> ReadFile (String _filenameIn)
	{
		ArrayList<String> filecontents_AL = new ArrayList<>();
		
		try 
		{
			BufferedReader br = new BufferedReader (new FileReader(_filenameIn));
			String line = br.readLine();
			while (line != null)
			{
				filecontents_AL.add(line);
				line = br.readLine();
			}
			br.close();
		} 
		catch (IOException e) {e.printStackTrace();}
		
		return filecontents_AL;
	}
	
	// Driver Code
	public static void main (String [] Args) throws InterruptedException
	{
		Scanner sc = new Scanner (System.in);
		System.out.println("Please input a phrase that you'd like to search for in the JSON file");
		String stringToSearch = sc.next();
		
		System.out.println(stringToSearch + " was entered to be search!");
		TimeUnit.SECONDS.sleep(1);
		
		CitationNetwork_Main CNM = new CitationNetwork_Main();
		ArrayList<String> filecontent_Lines = CitationNetwork_Main.ReadFile(Args[0]);
		ArrayList<Integer> threadBeginLines = CNM.ThreadSplitBegin(8, filecontent_Lines.size());
		ArrayList<Integer> threadEndLines = CNM.ThreadListEnd(threadBeginLines, filecontent_Lines.size());
		
		System.out.println(threadBeginLines.get(0));
		
		for (int i = 0; i < threadBeginLines.size(); i++)
		{
			int beginIndex = threadBeginLines.get(i);
			int endIndex = threadEndLines.get(i);
			List<String> slicedList = filecontent_Lines.subList(beginIndex, endIndex);
			
			CitationThread thread = new CitationThread(slicedList, stringToSearch);
			thread.start();
		}
		
	}
	
	
}
