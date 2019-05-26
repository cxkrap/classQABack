package com.example.classqa.blImpl.promotion;

import com.example.classqa.vo.ResponseVO;

public interface VIPServiceForBl {
    public ResponseVO getCardByUserId(int userId);
    public ResponseVO updateBalance(int id,double balance);
}
