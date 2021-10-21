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

    public static int getCount(){
        int count = 0;
        String sql = "Select Count(*) from netflix;";

        try(
                Connection conn = DriverManager.getConnection(connectUrl, user,pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next())
            {
            count = resultSet.getInt(1);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return count;
    }

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

    public static ArrayList<String> getCombos(){
        ArrayList<String> cRatings = new ArrayList<>();
        String sql = "Select rating\n" +
                    "from netflix \n" +
                    "where showId in(Select max(showId) from netflix group by rating ) order by rating;";

        try(
                Connection conn = DriverManager.getConnection(connectUrl, user,pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next())
            {
                cRatings.add(resultSet.getString(1));
            }
            cRatings.add("All ratings");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return cRatings;

    }

    public static ArrayList<NetflixShow> typeMovie(){
        ArrayList<NetflixShow> nfShow = new ArrayList<>();

        String sql = "Select * \n" +
                "From netflix\n" +
                "where `type` = 'Movie'\n" +
                "order by showId;";

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

    public static ArrayList<NetflixShow> typeTV(){
        ArrayList<NetflixShow> nfShow = new ArrayList<>();

        String sql = "Select * \n" +
                "From netflix\n" +
                "where `type` = 'TV Show'\n" +
                "order by showId;";

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
