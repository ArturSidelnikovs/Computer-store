package edu.app.repository.computerRepository;

import edu.app.model.computer.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    Page<Computer> findAll(Pageable pageable);
}
