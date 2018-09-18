package org.develop.app.batch.step.components.readers;

import static org.develop.app.batch.step.Utils.searchInJson;

import javax.sql.DataSource;

import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.dto.interfaces.Dto;
import org.develop.app.batch.step.Enums.PkgEnums;
import org.develop.app.batch.step.Enums.StepEnums;
import org.develop.app.batch.step.components.interfaces.ReadersInterface;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

public class DBReaderBuilder implements ReadersInterface<Dto>{

	DataSource dataSource;
	
	@Override
	public ItemReader<Dto> build(JSONObject json) throws Exception {
		
		Class<RowMapper<Dto>> mapperClass = (Class<RowMapper<Dto>>)Class.forName(PkgEnums.MAPPERS_PKG.getValue()+(String)searchInJson(json, StepEnums.MAPPER_CLASS));
		RowMapper<Dto> mapper = mapperClass.newInstance();
		
		JdbcCursorItemReader<Dto> reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource);
		reader.setRowMapper(mapper);
		reader.setSql((String)searchInJson(json, StepEnums.QUERY));
		return reader;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	
}
