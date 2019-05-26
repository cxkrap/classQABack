package com.example.classqa.bl.promotion;

import com.example.classqa.vo.ActivityForm;
import com.example.classqa.vo.ResponseVO;

/**
 * Created by liying on 2019/4/20.
 */
public interface ActivityService {
    
    ResponseVO publishActivity(ActivityForm activityForm);

    ResponseVO getActivities();



}
