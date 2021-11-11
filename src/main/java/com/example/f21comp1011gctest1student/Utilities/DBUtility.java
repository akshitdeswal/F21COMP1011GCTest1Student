package com.example.f21comp1011gctest1student.Utilities;

import com.example.f21comp1011gctest1student.NetflixShow;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "student";
    private static String pw = "student";
    private static String connectUrl = "jdbc:mysql://localhost:3306/javaTest";

    public static ArrayList<NetflixShow> getNetflixData(String showType, String showRating) throws SQLException {


        ArrayList<NetflixShow> nfShow = new ArrayList<>();

        ResultSet resultSet = null;

        String sql = "Select *  from netflix WHERE type != ? AND rating != ?";

//        if(!showType.equals("All") && showRating.equals("All ratings"))
//            sql = "SELECT * FROM netflix WHERE type = ? AND rating != ?";
//
//        else if(showType.equals("All") && !showRating.equals("All ratings"))
//            sql = "SELECT * FROM netflix WHERE type != ? AND rating = ?";
//
//        else if(!showType.equals("All") && !showRating.equals("All ratings"))
//            sql = "SELECT * FROM netflix WHERE type = ? AND rating = ?";

        //try ensure that anything opened in the ( ... ) will be closed
        try(
                Connection conn = DriverManager.getConnection(connectUrl, user,pw);
                PreparedStatement statement = conn.prepareStatement(sql);
        )

        {
            statement.setString(1, showType);
            statement.setString(2, showRating);
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                String showId = resultSet.getString("showId");
                String type = resultSet.getString("type");
                String title = resultSet.getString("title");
                String rating = resultSet.getString("rating");
                String director = resultSet.getString("director");
                String cast = resultSet.getString("cast");


                NetflixShow netflixShow = new NetflixShow(showId,type,title,rating,director,cast);


                nfShow.add(netflixShow);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            if (resultSet != null){
                resultSet.close();
            }
        }
        return nfShow;
    }



}
