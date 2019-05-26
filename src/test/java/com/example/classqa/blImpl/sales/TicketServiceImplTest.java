package com.example.classqa.blImpl.sales;

import com.example.ApplicationTests;
import com.example.classqa.po.Ticket;
import com.example.classqa.vo.ResponseVO;
import com.example.classqa.vo.SeatForm;
import com.example.classqa.vo.TicketForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImplTest extends ApplicationTests {

    @Autowired
    TicketServiceImpl ticketService ;
    @Test
    public void addTicket() {
        List<SeatForm>s = new ArrayList<>();
        TicketForm form = new TicketForm();
        SeatForm seatForm = new SeatForm();
        seatForm.setColumnIndex(5);
        seatForm.setRowIndex(2);
        SeatForm seatForm1 = new SeatForm();
        seatForm1.setColumnIndex(5);
        seatForm1.setRowIndex(3);
        s.add(seatForm);
        s.add(seatForm1);
        form.setUserId(16);
        form.setScheduleId(20);
        form.setSeats(s);
        ResponseVO responseVO=ticketService.addTicket(form);
        System.out.println((int)responseVO.getContent());
    }

    @Test
    public void completeTicket() {

    }

    @Test
    public void getTicketByUser() {
        ResponseVO responseVO = ticketService.getTicketByUser(16);
        List<Ticket>tickets = (List)responseVO.getContent();
        for(Ticket ticket:tickets){
            System.out.println(ticket.getId());
        }
    }

    @Test
    public void completeByVIPCard() {
    }

    @Test
    public void cancelTicket() {
        List<Integer>list = new ArrayList<>();
        list.add(70);
        list.add(71);
        ResponseVO responseVO = ticketService.cancelTicket(list);
        System.out.println(responseVO.getMessage());
    }
}