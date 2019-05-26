package com.example.classqa.blImpl.statistics;

import com.example.ApplicationTests;
import com.example.classqa.vo.ResponseVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieLikeServiceImplTest extends ApplicationTests {

    @Autowired
    private MovieLikeServiceImpl movieLikeService;
    @Test
    public void likeMovie() {
        ResponseVO responseVO = movieLikeService.likeMovie(16,200);
        System.out.println(responseVO.getContent());
    }
}