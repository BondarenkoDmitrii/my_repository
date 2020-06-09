package com.bondarenko.xml;

import com.bondarenko.Person;
import com.bondarenko.Repository;
import com.bondarenko.xml.Persons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbWorker {

    private static final Logger logger = LoggerFactory.getLogger(
            JaxbWorker.class);

    public PersonsList fromXmlToObject(String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonsList.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (PersonsList) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            logger.error("Error converting xml to repository!",e);
        }
        return null;
    }

    public void convertObjectToXml(Repository<Persons> person, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonsList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            PersonsList personsList = new PersonsList();
            List<Persons> personList = person.toList();
            for (Persons element : personList) {
                logger.info(element.toString());
                personsList.getPersons().add(element);
            }
            marshaller.marshal(personsList, new File(filePath));
            logger.info("Repository successfully migrated to xml");
        } catch (JAXBException e) {
            logger.error("Error converting storage to xml!",e);
        }
    }
}
