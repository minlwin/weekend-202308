package com.jdc.balance.model.repo;

import java.util.Optional;

import com.jdc.balance.model.BaseRepo;
import com.jdc.balance.model.entity.Account;

public interface AccountRepo extends BaseRepo<Account, Integer>{

	Optional<Account> findOneByLoginId(String username);
}
