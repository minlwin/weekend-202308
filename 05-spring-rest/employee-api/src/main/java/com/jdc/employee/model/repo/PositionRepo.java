package com.jdc.employee.model.repo;

import com.jdc.employee.model.BaseRepository;
import com.jdc.employee.model.entity.Position;

public interface PositionRepo extends BaseRepository<Position, String>{

	long countByCode(String code);

}
