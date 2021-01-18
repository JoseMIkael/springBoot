package crud.api.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import crud.api.rest.model.Alunos;

@Repository
public interface AlunosRepository extends CrudRepository<Alunos, Long> {
	List<Alunos> findByEmailContains(String email);
}
