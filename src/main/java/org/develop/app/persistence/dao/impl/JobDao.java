package org.develop.app.persistence.dao.impl;

import org.develop.app.persistence.dao.interfaces.IJobDao;
import org.develop.app.persistence.entities.config.JobModel;
import org.springframework.stereotype.Repository;

@Repository
public class JobDao extends BaseDao<JobModel, String> implements IJobDao {

}
