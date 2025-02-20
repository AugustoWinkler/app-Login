package br.appLogin.appLogin.repository;

import org.springframework.data.repository.CrudRepository;
import br.appLogin.appLogin.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	User findById(long id);
}
