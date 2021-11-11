package com.example.f21comp1011gctest1student;

import com.example.f21comp1011gctest1student.Utilities.DBUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private ArrayList<NetflixShow> shows = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieCheckBox.setSelected(true);
        tvCheckBox.setSelected(true);
        try {
            shows = DBUtility.getNetflixData("All", "All rating");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(shows.size());
        tableView.getItems().addAll(shows);
        showIdCol.setCellValueFactory(new PropertyValueFactory<>("showId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));
        castCol.setCellValueFactory(new PropertyValueFactory<>("cast"));

        selectRatingComboBox.getItems().add("All ratings");
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
    void applyFilter(ActionEvent event) throws SQLException {
        tableView.getItems().clear();

        String type = "All";
        String currentRating = selectRatingComboBox.getSelectionModel().getSelectedItem();
        if (currentRating == null) {
            currentRating = "All ratings";
        }
        ArrayList<NetflixShow> filteredList = new ArrayList<NetflixShow>();
        filteredList.addAll(shows);
        for(NetflixShow show: shows){
            if(movieCheckBox.isSelected() && !tvCheckBox.isSelected() && !show.getType().equals("Movie")){
                filteredList.remove(show);
            }
            if(tvCheckBox.isSelected() && !movieCheckBox.isSelected() && !show.getType().equals("TV Show") ){
                filteredList.remove(show);
            }
            if(!currentRating.equals("All ratings") && !show.getRating().equals(currentRating)){
                filteredList.remove(show);
            }
            if(!tvCheckBox.isSelected() && !movieCheckBox.isSelected()){
                filteredList.remove(show);
            }
        }


        tableView.getItems().addAll(filteredList);
        updateNumberLabel();
    }
}
