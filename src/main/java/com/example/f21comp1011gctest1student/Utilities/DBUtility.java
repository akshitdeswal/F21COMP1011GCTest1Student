package com.example.f21comp1011gctest1student.Utilities;

import com.example.f21comp1011gctest1student.NetflixShow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "student";
    private static String pw = "student";
    private static String connectUrl = "jdbc:mysql://localhost:3306/javaTest";

    public static ArrayList<NetflixShow> getNetflixData()
    {


        ArrayList<NetflixShow> nfShow = new ArrayList<>();

        String sql = "Select `showId`, `type`, `title`, `rating`,`director`, `cast`  from netflix group by showId;";

        //try ensure that anything opened in the ( ... ) will be closed
        try(
                Connection conn = DriverManager.getConnection(connectUrl, user,pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
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
        return nfShow;
    }
}
