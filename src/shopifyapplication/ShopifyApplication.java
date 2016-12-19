/*
I assume you do not expect us to reinvent the wheel so I shall download a  library
to parse the file and focus on the logic for the task.
Code using javax.json-1.0.4.jar from

*/
package shopifyapplication;

import java.io.FileReader;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * @author Georges
 */
public class ShopifyApplication
{

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		// TODO code application logic here
		//ArrayList<Json> obj = new ArrayList<Json>(100); 
		JSONParser parser = new JSONParser();
		JSONObject item;
		double totalPrice	= 0;
		try{
			JSONObject obj = (JSONObject)parser.parse(new FileReader("././orders.json"));
			JSONArray orders = (JSONArray)obj.get("orders");
			for(int x = 0; x < orders.size();x++){
				
				item = (JSONObject)orders.get(x);
				
				Double price = Double.parseDouble(item.get("total_price").toString());
				totalPrice	+= price;
				
				// Put fix to double accuracy issue.
				totalPrice = Math.round(totalPrice*100D)/100D;
				
			}
			//Can't find a tax and shipping prices seperate from the total price.
			//I checked
			//So I assume you want the total of the three
			//After rereading the instructions it seems this is what was mean
			System.out.println("Total Revenue: " + totalPrice);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
