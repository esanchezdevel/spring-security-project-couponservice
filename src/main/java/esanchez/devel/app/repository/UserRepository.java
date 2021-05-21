package esanchez.devel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esanchez.devel.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
