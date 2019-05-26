package com.example.classqa.blImpl.management.schedule;

import com.example.classqa.po.ScheduleItem;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/28 12:30 AM
 */
public interface ScheduleServiceForBl {
    /**
     * 查询所有涉及到movieIdList中电影的排片信息
     * @param movieIdList
     * @return
     */
    List<ScheduleItem> getScheduleByMovieIdList(List<Integer> movieIdList);

    /**
     * 根据id查找排片信息
     * @param id
     * @return
     */
    ScheduleItem getScheduleItemById(int id);

    /**
     * 根据hall_id查找排片
     * @param hall_id
     * @return
     */
    List<ScheduleItem> getScheduleByHallID(int hall_id);

}
