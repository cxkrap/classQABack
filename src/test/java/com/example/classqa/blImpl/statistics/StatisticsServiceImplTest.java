package com.example.classqa.blImpl.statistics;

import com.example.ApplicationTests;
import com.example.classqa.vo.MovieTotalBoxOfficeVO;
import com.example.classqa.vo.PlacingRateVO;
import com.example.classqa.vo.ResponseVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatisticsServiceImplTest extends ApplicationTests {

    @Autowired
    private StatisticsServiceImpl statisticsServiceImpl;

    @Test
    public void testGetMoviePlacingRateByDate() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse("2019-04-21");
            ResponseVO response = statisticsServiceImpl.getMoviePlacingRateByDate(date);
            System.out.println(response.getSuccess());
            System.out.println(response.getMessage());

            List<PlacingRateVO> placingRateVOS = (List<PlacingRateVO>)response.getContent();
            for(PlacingRateVO res: placingRateVOS){
                System.out.println(res.getMovieId() + " " + res.getPlacingRate() + " " + res.getName());
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testGetPopularMovies() {
        ResponseVO response = statisticsServiceImpl.getPopularMovies(30, 100);
        System.out.println(response.getSuccess());
        System.out.println(response.getMessage());
        List<MovieTotalBoxOfficeVO> moiveTotalBoxOfficeList = (List<MovieTotalBoxOfficeVO>)response.getContent();
        for(MovieTotalBoxOfficeVO res : moiveTotalBoxOfficeList){
            System.out.println(res.getMovieId() + " " + res.getBoxOffice() + " " + res.getName());
        }
    }
}