package com.example.classqa.blImpl.sales;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.example.classqa.bl.sales.TicketService;
import com.example.classqa.blImpl.management.hall.HallServiceForBl;
import com.example.classqa.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.classqa.blImpl.promotion.VIPServiceForBl;
import com.example.classqa.blImpl.promotion.CouponServiceForBl;
import com.example.classqa.data.sales.TicketMapper;
import com.example.classqa.po.*;
import com.example.classqa.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    @Autowired
    CouponServiceForBl couponService;
    @Autowired
    VIPServiceForBl vipService;

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        try{
            List<SeatForm> seats = ticketForm.getSeats();
            List<Ticket>tickets = new ArrayList<>();
            if(seats.size()==0)return ResponseVO.buildFailure("选票数不能为0");
            else{
                int userid = ticketForm.getUserId();
                int scheduleid = ticketForm.getScheduleId();
                for(SeatForm seat:seats){
                    Ticket ticket = new Ticket();
                    ticket.setScheduleId(scheduleid);
                    ticket.setUserId(userid);
                    ticket.setColumnIndex(seat.getColumnIndex());
                    ticket.setRowIndex(seat.getRowIndex());
                    ticket.setState(0);
                    Ticket temp = ticketMapper.selectTicketByScheduleIdAndSeat(scheduleid,seat.getColumnIndex(),seat.getRowIndex());
                    if(temp!=null){
                        return ResponseVO.buildFailure("选取的座位已经被预定！");
                    }
                    tickets.add(ticket);
                    ticketMapper.insertTicket(ticket);

                }
                return ResponseVO.buildSuccess(this.getOrderForm(tickets));
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }
    @Override
    @Transactional
    /**
     * 暂时写成根据总价和当前时间判断能否打折
     * 暂时写成获取最新的优惠券
     * 默认成Ticket的User使用的优惠券不用校验
     * 认为List里的userid都是一样的
     * 决定采用支付宝支付
     */
    public ResponseVO completeTicket(List<Integer> id, int couponId) {
        try {
            //更新票的状态
            for(int ticket_id:id){
                ticketMapper.updateTicketState(ticket_id,1);
            }
            return ResponseVO.buildSuccess();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule=scheduleService.getScheduleItemById(scheduleId);
            Hall hall=hallService.getHallById(schedule.getHallId());
            int[][] seats=new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                seats[ticket.getRowIndex()][ticket.getColumnIndex()]=1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try{
            return ResponseVO.buildSuccess(ticketMapper.selectTicketByUser(userId));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("用户不存在");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> id, int couponId) {
        try {
            Ticket temp = ticketMapper.selectTicketById(id.get(0));
            int user_id = temp.getUserId();
            ResponseVO vip_responseVO = vipService.getCardByUserId(user_id);
            if (vip_responseVO.getSuccess()) {
                VIPCard vipCard = (VIPCard)vip_responseVO.getContent();
                ResponseVO complete_VO = this.checkAndgiveCoupon(id,couponId);
                if(complete_VO.getSuccess()){
                    double fare = (double) complete_VO.getContent();
                    double balance = vipCard.getBalance();
                    balance = balance -fare;
                    if(balance <0)return ResponseVO.buildFailure("余额不足，请充值");
                    //更新余额
                    for(int ticket_id:id){
                        ticketMapper.updateTicketState(ticket_id,1);
                    }
                    vipService.updateBalance(vipCard.getId(), balance);
                    return ResponseVO.buildSuccess();
                }
                else{
                    return ResponseVO.buildFailure(complete_VO.getMessage());
                }
            }
            else{
                return ResponseVO.buildFailure("会员卡不存在");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        try{
            for (int ticket_id:id){
                Ticket ticket = ticketMapper.selectTicketById(ticket_id);
                if(ticket==null)return ResponseVO.buildFailure("取消了未锁座的票!");
                if(ticket.getState()==0)ticketMapper.deleteTicket(ticket_id);
                else {
                    return ResponseVO.buildFailure("不能取消已购买或已失效的票!");
                }
            }
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    public ResponseVO checkAndgiveCoupon(List<Integer> id, int couponId){
        try {
            double sum=0;
            for(int ticket_id:id){
                Ticket ticket = ticketMapper.selectTicketById(ticket_id);
                ScheduleItem item = scheduleService.getScheduleItemById(ticket.getScheduleId());
                sum+=item.getFare();
            }
            ResponseVO check_responseVO = couponService.checkCoupon(couponId,sum);
            if(check_responseVO.getSuccess()==true){
                sum = sum-(double)check_responseVO.getContent();
            }
            Ticket temp = ticketMapper.selectTicketById(id.get(0));
            int user_id=temp.getUserId();
            couponService.giveCoupon(user_id);
            return ResponseVO.buildSuccess(sum);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    
    @Override
    public ResponseVO refund(List<Integer>id){
        try{
            for(int ticket_id:id){
                Ticket ticket = ticketMapper.selectTicketById(ticket_id);
                if(ticket==null||ticket.getState()!=1)return ResponseVO.buildFailure("不能退未购买的票!");
                else ticketMapper.deleteTicket(ticket_id);
            }
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    public OrderFormVO getOrderForm(List<Ticket>tickets){
        //先把PO转化为VO
        OrderFormVO orderFormVO = new OrderFormVO();
        List<TicketVO>ticketVOList = new ArrayList<>();
        List<CouponVO>couponVOList = new ArrayList<>();
        int total=0;
        for(Ticket ticket:tickets) {
            //将Ticket转化为TicketVO
            TicketVO ticketVO = new TicketVO();
            ticketVO.setId(ticket.getId());
            ticketVO.setUserId(ticket.getUserId());
            ticketVO.setScheduleId(ticket.getScheduleId());
            ticketVO.setColumnIndex(ticket.getColumnIndex());
            ticketVO.setRowIndex(ticket.getRowIndex());
            ticketVO.setState(ticket.getState() + "");
            ticketVO.setTime(ticket.getTime());
            ticketVOList.add(ticketVO);

            //算总的定价
            ScheduleItem schedule = scheduleService.getScheduleItemById(ticket.getScheduleId());
            total += schedule.getFare();
        }
            //得到当前时间当前用户能用的优惠券
        int user_id = tickets.get(0).getUserId();
        List<Coupon>coupons = couponService.getCouponsInDate(user_id);
        for(Coupon coupon:coupons){
            CouponVO couponVO = new CouponVO();
            couponVO.setId(coupon.getId());
            couponVO.setDescription(coupon.getDescription());
            couponVO.setName(coupon.getName());
            couponVO.setTargetAmount(coupon.getTargetAmount());
            couponVO.setDiscountAmount(coupon.getDiscountAmount());
            couponVO.setStartTime(coupon.getStartTime());
            couponVO.setEndTime(coupon.getEndTime());
            couponVOList.add(couponVO);
        }
        orderFormVO.setTotal(total);
        orderFormVO.setCouponVOList(couponVOList);
        orderFormVO.setTicketVOList(ticketVOList);
        return orderFormVO;
    }

    @Override
    public ResponseVO makePayRequest(List<Integer> id, int couponId){
        //付款金额
        ResponseVO responseVO = this.checkAndgiveCoupon(id,couponId);
        double fare = (double)responseVO.getContent();
        //订单名称
        String orderName = "电影票订单";

        AlipayClient alipayClient = new DefaultAlipayClient(AppPayConfig.gateway,AppPayConfig.app_id
                ,AppPayConfig.merchant_private_key,"json",AppPayConfig.charset,AppPayConfig.alipay_public_key
                ,AppPayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest =  new AlipayTradePagePayRequest();
        alipayRequest.setBizContent("{\"out_trade_no\"}"+AppPayConfig.app_id
                +"\","+"\"total_amount\":\""+fare+"\"subject\":"+orderName+"\","+"\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        alipayRequest.setNotifyUrl(AppPayConfig.notify_url);
        alipayRequest.setReturnUrl(AppPayConfig.return_url);
        try{
            AlipayTradePagePayResponse result = alipayClient.pageExecute(alipayRequest);
            return ResponseVO.buildSuccess(result.getBody());
        }catch (AlipayApiException e){
            return ResponseVO.buildFailure("请求失败");
        }
    }

}
