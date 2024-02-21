package school.hei.sary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.sary.repository.model.OperationSary;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaryRepository extends JpaRepository<OperationSary, String> {
    @Override
    Optional<OperationSary> findById(String id);

    @Override
    List<OperationSary> findAll();
}
