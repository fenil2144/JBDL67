package com.example.demo.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepositoryInterf;

@Repository
public class PersonRepositoryImpl implements PersonRepositoryInterf {

    //    @Autowired
    Connection connection;

    private PreparedStatement preparedStatement;

    private static Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    public PersonRepositoryImpl(Connection connection) {
        this.connection = connection;
        createTable();
    }

    @Override
    public void createPersonStatic(Person person) {

        try {
            Statement statement = connection.createStatement();
            statement.execute("insert into person(id,first_name,last_name,age,dob) " +
                    "VALUES(1, 'Peter','David',20,'1996-02-04')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists person(id int primary key auto_increment, first_name varchar(30)," +
                    "last_name varchar(30),age int , dob varchar(12))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createPerson(Person person){

        logger.info("In createPerson:{}",person);
        try {
            this.preparedStatement = connection.prepareStatement("insert into person(first_name,last_name,age,dob) " +
                    "VALUES (?,?,?,?)");


//        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setInt(3, person.getAge());
        preparedStatement.setString(4, person.getDob());
        int result = preparedStatement.executeUpdate();
        logger.info("Out createPerson with result:{}",result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person getPersonById(int id) {

        try {
            this.preparedStatement = connection.prepareStatement("select * from person where id = ?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                return getPersonFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private Person getPersonFromResultSet(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .age(resultSet.getInt(4))
                .dob(resultSet.getString("dob"))
                .build();
    }


}
