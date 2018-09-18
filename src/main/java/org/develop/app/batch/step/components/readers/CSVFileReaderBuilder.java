package org.develop.app.batch.step.components.readers;

import static org.develop.app.batch.step.Utils.searchInJson;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.dto.interfaces.Dto;
import org.develop.app.batch.step.Enums.PkgEnums;
import org.develop.app.batch.step.Enums.StepEnums;
import org.develop.app.batch.step.components.interfaces.ReadersInterface;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class CSVFileReaderBuilder implements ReadersInterface<Dto> {

	@Override
	public ItemReader<Dto> build(JSONObject json) throws Exception{

		Class<FieldSetMapper<Dto>> mapperClass = (Class<FieldSetMapper<Dto>>)Class.forName(PkgEnums.MAPPERS_PKG.getValue()+(String)searchInJson(json, StepEnums.MAPPER_CLASS));
		FieldSetMapper<Dto> mapper = mapperClass.newInstance();
		
		FlatFileItemReader<Dto> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource((String)searchInJson(json, StepEnums.RESOURCE)));
		reader.setLineMapper(new DefaultLineMapper<Dto>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						
						JSONArray jsonArray = (JSONArray)searchInJson(json, StepEnums.TOKEN_NAMES); 
						String[] tokens = new String[jsonArray.length()];
						if (jsonArray != null) { 
						   for (int i=0;i<tokens.length;i++){ 
							   tokens[i] = jsonArray.get(i).toString();
						   } 
						} 
						
						setNames(tokens);
						setDelimiter((String)searchInJson(json, StepEnums.DELIMITER));
					}
				});
				setFieldSetMapper(mapper);
			}
		});
		return reader;
	}

}
