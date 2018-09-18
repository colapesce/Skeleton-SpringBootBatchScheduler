package org.develop.app.batch.step.components.writers;

import javax.persistence.EntityManagerFactory;

import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.step.components.interfaces.WritersInterface;
import org.develop.app.persistence.entities.interfaces.Entita;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;

public class JPAWriterBuilder implements WritersInterface<Entita>{

	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public ItemWriter<Entita> build(JSONObject json) {
		JpaItemWriter<Entita> writer = new JpaItemWriter<Entita>();
		writer.setEntityManagerFactory(entityManagerFactory);
		return writer;
	}
	
	public void setEntityManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}	

}
