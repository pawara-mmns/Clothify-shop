package repository.custom.impl;

import javafx.collections.ObservableList;
import repository.custom.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(Object o) {
        return false;
    }

    @Override
    public ObservableList getAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Object search(String id) {
        return null;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }
}
