package com.jdc.weekend.model.repo;

import java.util.Optional;

import com.jdc.weekend.model.BaseRepository;
import com.jdc.weekend.model.entity.Member;

public interface MemberRepo extends BaseRepository<Member, Integer>{

	Optional<Member> findOneByEmail(String email);
}
