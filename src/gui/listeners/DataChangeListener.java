package gui.listeners;

import java.sql.SQLException;

public interface DataChangeListener {

    void onDataChanged() throws SQLException;

}
