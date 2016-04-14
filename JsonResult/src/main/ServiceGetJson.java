package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceGetJson {
	
	public String result2JSON(int n, String file_tobe_transform) throws InputShouldBePositiveException, FileNotFoundException {
		if (n < 0) {
			throw new InputShouldBePositiveException(n);
		} else if(Files.notExists(Paths.get(file_tobe_transform))){
			throw new FileNotFoundException();
		}else {
			String jsonResult = "";
			String[] s_array = file_tobe_transform.split("/");
			s_array[s_array.length - 1] = "json_result.txt";
			String my_file_name = Arrays.toString(s_array);
			my_file_name = my_file_name.substring(1, my_file_name.length() - 1).replace(", ", "/");
			Writer writer = null;
			int i = 0;
			try {
				writer = new BufferedWriter(new BufferedWriter(new FileWriter(my_file_name, true)));
				BufferedReader br = new BufferedReader(new FileReader(file_tobe_transform));
				String line;
				while ((line = br.readLine()) != null) {
					// process the line.
					JSONObject obj = string2JSON(line);
					writer.append(obj.toString() + "\n");
					jsonResult = jsonResult + obj.toString() + "\n";
					i++;
					if (i >= n) {
						break;
					}
				}
				writer.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return jsonResult;
		}
	}
	
	private static JSONObject string2JSON(String s){
		String[] names = new String[]{"airport", "city", "number_passengers"};
		JSONObject obj = new JSONObject();
		String[] s_json = s.split("\\s+");
		for(int j=0; j<s_json.length; j++){
			try {
				if(!s_json[j].matches("\\d+")){
					obj.put(names[j], s_json[j]);
				}else{
					int number = Integer.parseInt(s_json[j]);
					obj.put(names[j], number);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
}