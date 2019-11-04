import java.util.List;
import java.util.stream.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class CitationJSONHelper 
{
	List<String> InterpretLines;
	
	
	CitationJSONHelper(List<String> stringsToInterpret, String _stringToSearch) throws JSONException
	{
		this.InterpretLines = stringsToInterpret;
		int index = 0;
		
		// Use stream to interpret this
		// Couldn't get this to work
		@SuppressWarnings("unchecked")
		List<JSONObject> list_JSONObj = (List<JSONObject>) stringsToInterpret.stream().map(x->{
			try 
			{
			return new JSONObject(x);
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			return null;
		});
//		JSONObject jsonObject = new JSONObject((stringsToInterpret.get(index)).toString());
	}
}
