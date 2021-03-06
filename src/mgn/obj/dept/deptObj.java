/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mgn.obj.dept;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mgn.obj._beans.deptBean;

import obj.db.v1.dbMgrInterface;
import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class deptObj implements Serializable{
    public  final Logger logger = (Logger) Logger.getLogger(deptObj.class);
    deptSql deptSql;
    public deptObj(){
        deptSql = new deptSql();
    }
    public  List<String> getDept(int user,  dbMgrInterface db){
        List<String> l = new ArrayList<String>();
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(deptSql.sqlDeptCustSelect, new Object[]{user});
            while(rs.next()){
                l.add(""+rs.getInt(2));
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return l;
    }
    public  void insertUserDept(int user,int dept,int access, dbMgrInterface db){
        try {
            db.updateDatabase(deptSql.sqlDeptCustInsert, new Object[]{user,dept,access});
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
    }
    public  void deleteUserDept(int user, dbMgrInterface db){
        try {
            db.updateDatabase(deptSql.sqlDeptCustDel, new Object[]{user});
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
   }
    
    
    
    private Object[] getDeptObj(deptBean b,boolean update){
        return new Object[]{
        b.getCustId(),   //CUST_ID,
        b.getDeptName(), //deptName ,
        b.getDeptDesc(), //deptDesc ,
        b.getAddr1(),//ADDR1,
        b.getAddr2(), //ADDR2,
        b.getCity(), //CITY,
        b.getState(),//STATE
        b.getZip(), //ZIP ,
        b.getZipPlus(), //ZIP_PLUS,
        b.getPhone(), //PHONE,
        b.getFax(), //FAX,
        b.getEmail(), //EMAIL,
        b.getWebsite(), //website
        update ? b.getDeptId() :null
        };
    }
}
