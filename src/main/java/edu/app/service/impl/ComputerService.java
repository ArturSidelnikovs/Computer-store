package edu.app.service.impl;

import edu.app.model.phone.Computer;
import edu.app.repository.computerRepository.ComputerRepository;
import edu.app.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("computerService")
@Transactional
public class ComputerService implements IService<Computer> {
    private final ComputerRepository computerRepository;
    private final Logger logger = LoggerFactory.getLogger(ComputerService.class);

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Computer findById(long id) {
        Optional<Computer> byId = computerRepository.findById(id);
        if (byId.isPresent()) {
            Computer computer = byId.get();
            logger.info("Computer {} was successfully found", computer.getId());
            return computer;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Computer> findAllWithPagination(Pageable pageable) {
        Page<Computer> phones = computerRepository.findAll(pageable);
        logger.info("All phones in db was successfully found");
        return phones;
    }


    @Override
    public Computer save(Computer phone) {
        Computer computerToDb = computerRepository.save(phone);
        logger.info("Computer {} was successfully added to db", computerToDb.getId());
        return computerToDb;
    }

    @Override
    public void deleteById(long id) {
        computerRepository.deleteById(id);
        logger.info("Computer with id {} was successfully deleted form db", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Computer> findAll() {
        List<Computer> computers = computerRepository.findAll();
        logger.info("All computers was successfully found in db");
        return computers;
    }
}