package com.example.SeleniumDesign.data;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
		//Read Json to string
		String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/com/example/SeleniumDesign/data/data.json"),
				StandardCharsets.UTF_8);
		//string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
}
