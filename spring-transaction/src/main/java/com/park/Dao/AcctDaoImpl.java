package com.park.Dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;


public class AcctDaoImpl extends JdbcDaoSupport implements AcctDao {


    /**
     *
     * @param out 转出账号
     * @param money 转出金额
     */
    @Override
    public void inMoney(String out, double money) {
        String sql ="update acct set money= money + ? where name =?";
        this.getJdbcTemplate().update(sql,money ,out);
    }

    /**
     *
     * @param in 转入账号
     * @param money 转入金额
     */
    @Override
    public void outMoney(String in, double money) {
        String sql ="update acct set money= money - ? where name =?";
        this.getJdbcTemplate().update(sql,money ,in);
    }
}
