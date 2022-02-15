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

    public void saveOrUpdate(Department obj){
        if(obj.getId() == null){
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }

    public void remove(Department obj){
        dao.deleteById(obj.getId());
    }
}
