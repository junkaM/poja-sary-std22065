package school.hei.sary.repository.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OperationSary {
  @Id private String id;

  @Column(name = "transformation_timestamp")
  private Timestamp operationTimestamp;
}
