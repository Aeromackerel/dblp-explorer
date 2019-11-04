import java.util.ArrayList;
import java.util.List;

public class CitationThread extends Thread
{
	// Field variables
	List<String> filecontents_Interpret;
	String phraseToSearchFor;
	
	public CitationThread (List<String> sectionToAnalyze, String _stringToSearch)
	{
		this.filecontents_Interpret = sectionToAnalyze;
		this.phraseToSearchFor = _stringToSearch;
	}
	
	public void run()
	{
		try
        {
			System.out.println(filecontents_Interpret);
			CitationJSONHelper helper = new CitationJSONHelper(filecontents_Interpret, phraseToSearchFor);
        } 
        catch (Exception e) 
        {System.out.println ("Exception is caught"); } 
	}
}
