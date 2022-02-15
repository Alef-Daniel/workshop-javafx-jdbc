package gui;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {

   @FXML
   private MenuItem menuItemSeller;

   @FXML
   private MenuItem menuItemDepartment;

   @FXML
   private MenuItem menuItemAbout;


   public void onMenuItemSellerAction(){
       loadView("/gui/SellerList.fxml",
               (SellerListController controller) -> {
                   controller.setSellerService(new SellerService());
                   try {
                       controller.updateTableView();
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
               });
   }

    public void onMenuItemDepartmentAction(){
        loadView("/gui/DepartmentList.fxml",
                (DepartmentListController controller) -> {
                    controller.setDepartmentService(new DepartmentService());
                    try {
                        controller.updateTableView();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });

    }



    public void onMenuItemAboutAction(){
        loadView("/gui/About.fxml",     x -> {});
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();
            Scene mainScene = Main.getMainScene();
            VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
            Node mainMenu =  mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVBox.getChildren());

            T controller = loader.getController();
            initializingAction.accept(controller);
        } catch (IOException e) {
            Alerts.showAlert("IO Exception","Error Load View", e.getMessage(), Alert.AlertType.ERROR);
        }
    }



}
