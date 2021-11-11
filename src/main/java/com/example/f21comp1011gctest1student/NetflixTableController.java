package com.example.f21comp1011gctest1student;

import com.example.f21comp1011gctest1student.Utilities.DBUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class NetflixTableController implements Initializable {

    @FXML
    private TableView<NetflixShow> tableView;

    @FXML
    private TableColumn<NetflixShow, String> showIdCol;

    @FXML
    private TableColumn<NetflixShow, String> typeCol;

    @FXML
    private TableColumn<NetflixShow, String> titleCol;

    @FXML
    private TableColumn<NetflixShow, String> ratingCol;

    @FXML
    private TableColumn<NetflixShow, String> directorCol;

    @FXML
    private TableColumn<NetflixShow, String> castCol;

    @FXML
    private CheckBox movieCheckBox;

    @FXML
    private CheckBox tvCheckBox;

    @FXML
    private ComboBox<String> selectRatingComboBox;

    @FXML
    private Label numOfShowsLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieCheckBox.setSelected(true);
        tvCheckBox.setSelected(true);

        showIdCol.setCellValueFactory(new PropertyValueFactory<>("showId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));
        castCol.setCellValueFactory(new PropertyValueFactory<>("cast"));
        tableView.getItems().addAll(DBUtility.getNetflixData("All", "All Rating"));
        selectRatingComboBox.getItems().addAll(getCombos());
        updateNumberLabel();
    }

    private TreeSet<String> getCombos(){
        TreeSet<String> ratings = new TreeSet<String>();

        for(NetflixShow show : tableView.getItems()) {
            ratings.add(show.getRating());
        }
        return ratings;
    }

    private void updateNumberLabel(){
        numOfShowsLabel.setText(String.format("Number of movies/shows : %d",+ tableView.getItems().size()));
    }

    @FXML
    void applyFilter(ActionEvent event)  {
        tableView.getItems().clear();

        String currentRating = selectRatingComboBox.getSelectionModel().getSelectedItem();
        if (selectRatingComboBox.getSelectionModel().getSelectedItem() == null)
             currentRating = "All ratings";

        if(movieCheckBox.isSelected() && !tvCheckBox.isSelected() ){
            tableView.getItems().addAll(DBUtility.getNetflixData("TV Show", currentRating));

        }
        else if(tvCheckBox.isSelected() && !movieCheckBox.isSelected() ){
            tableView.getItems().addAll(DBUtility.getNetflixData("Movie", currentRating));
        }


        updateNumberLabel();
    }
}
