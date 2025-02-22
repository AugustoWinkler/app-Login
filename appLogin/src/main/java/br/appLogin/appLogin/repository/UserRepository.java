package br.appLogin.appLogin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.appLogin.appLogin.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	User findById(long id);

	@Query(value = "SELECT * FROM applogin.user WHERE email = :email AND password = :password", nativeQuery = true)
	User login(String email, String password);

}
