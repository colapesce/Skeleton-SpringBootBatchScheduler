package org.develop.app.persistence.dao.impl;

import org.develop.app.persistence.dao.interfaces.IRuotaDao;
import org.develop.app.persistence.entities.business.Ruota;
import org.springframework.stereotype.Repository;

@Repository
public class RuotaDao extends BaseDao<Ruota, String> implements IRuotaDao{}

