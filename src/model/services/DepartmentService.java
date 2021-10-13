package model.services;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {

    private DepartmentDao dao = DaoFactory.createDepartmentDao();

    public List<Department> findAll(){
        try {
            return dao.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
