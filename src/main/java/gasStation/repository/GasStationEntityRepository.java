package gasStation.repository;

import gasStation.domain.entity.GasStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository interface for managing GasStationEntity entities. Extends JpaRepository
 * to inherit basic CRUD operations and additional querying methods.
 */
@Repository
public interface GasStationEntityRepository extends JpaRepository<GasStationEntity, String> {

    List<GasStationEntity> findAllByName(String name);

}
