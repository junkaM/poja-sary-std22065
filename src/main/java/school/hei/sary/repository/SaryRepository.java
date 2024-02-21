package school.hei.sary.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.sary.repository.model.OperationSary;

@Repository
public interface SaryRepository extends JpaRepository<OperationSary, String> {
  @Override
  Optional<OperationSary> findById(String id);

  @Override
  List<OperationSary> findAll();
}
