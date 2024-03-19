package org.launchcode.growsphere.data;

import org.launchcode.growsphere.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
