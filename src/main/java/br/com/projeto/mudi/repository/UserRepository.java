package br.com.projeto.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.mudi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	User findByUsername(String username);
}
