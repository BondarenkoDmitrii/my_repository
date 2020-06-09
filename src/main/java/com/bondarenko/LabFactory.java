package com.bondarenko;

import com.bondarenko.xml.JaxbWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;

public class LabFactory implements ILabFactory {
    private static final Logger logger = LoggerFactory.getLogger(
            JaxbWorker.class);
    @Override
    public IPerson createPerson() {
        Person person = new Person();
        logger.info("Person created");
        return person;
    }

    @Override
    public IDivision createDivision() {
        Division division = new Division();
        logger.info("Division created");
        return division;
    }

    @Override
    public <T> IRepository<T> createRepository(Class<T> clazz) {
        Repository<T> repository = new Repository<T>();
        logger.info("Repository created");
        return repository;
    }

    @Override
    public IPersonRepository createPersonRepository() {
        return null;
    }
}
