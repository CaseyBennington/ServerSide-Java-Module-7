package com.corejsf;

import data.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
// or import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean // or @Named
@RequestScoped
public class BidderBean {

    @Resource(name = "jdbc/vacation")
    //private DataSource ds;
    public ResultSet getAll() throws SQLException {
        //Connection conn = ds.getConnection();
        Connection conn = DBUtil.connect();
        try {
            Statement stmt = conn.createStatement();
            //ResultSet result = stmt.executeQuery("SELECT * FROM bid"); 
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM BID");
            ResultSet result = ps.executeQuery();
            // return ResultSupport.toResult(result);
            CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
            // or use an implementation from your database vendor
            crs.populate(result);
            return crs;
        } finally {
            conn.close();
        }
    }
}