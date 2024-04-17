package com.crud.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public interface DAO<t,k>
{


    void insertar(t a) throws DAOException, SQLException;
    void modificar(t a) throws DAOException , SQLException;
    void elimanar(k id) throws DAOException,   SQLException;

    List<t> getALl() throws DAOException, SQLException;

    t getById(k id) throws DAOException, SQLException;

    List<t> getALl(String palabraClave) throws DAOException, SQLException;

}
