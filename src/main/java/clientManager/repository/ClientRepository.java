package clientManager.repository;

import clientManager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Agustin Perez Garcia
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}