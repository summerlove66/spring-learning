package com.park.Service;

import com.park.Dao.AcctDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


public class AcctServiceImpl implements AcctService {

    private AcctDao acctDao;

    public AcctDao getAcctDao() {
        return acctDao;
    }

    public void setAcctDao(AcctDao acctDao) {
        this.acctDao = acctDao;
    }

    @Override
    public void transfer(String in, String out, double money) {
    this.acctDao.outMoney(out,money);
    int m = 6/0;
    this.acctDao.inMoney(in ,money);
    }

    @Override
    public void selectAll() {

    }
}
