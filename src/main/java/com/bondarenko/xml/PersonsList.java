package com.bondarenko.xml;

import com.bondarenko.Person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "List")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonsList {

    @XmlElement
    private List<Persons> personsList = new ArrayList<>();;

    public List<Persons> getPersons() {
        return personsList;
    }

    public void setPersons(List<Persons> persons) {
        this.personsList = persons;
    }

    @Override
    public String toString() {
        return "PersonsList{" +
                "personsList=" + personsList +
                '}';
    }
}
