/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mgn.obj.cal;

/**
 *
 * @author lmeans
 */
public class calendarSql {
    private static final String U2455ee55="skjdfksd",
            

            sqlSelect =
            " select c.cal_id,c.cal_rollup_id,c.cal_desc,c.cal_type,c.startDate,c.enddate,c.user_id, "+
            " c.dept_id, c.lookup_id,c.lookup_flag,c.subject_text,c.subject_body,c.moddate, "+
            " u.first_name,u.last_name,s.sys_desc "+
            " from mgn_calendar c  "+
	            " left join mgn_user u on c.user_id = u.user_id "+
	            " left join mgn_lookup_sys s on c.dept_id = s.sys_id";
            //" select c.cal_id,c.cal_rollup_id,c.cal_desc,c.cal_type,c.startDate,c.enddate,c.user_id, "+
            //" c.dept_id, c.lookup_id,c.lookup_flag,c.subject_text,c.subject_body,moddate "+
            //" from mgn_calendar c";
    
    public static String getCalSelectBydate(String dept){
        return sqlSelect+" where c.startDate >= ? and c.startDate <= ? and c.cal_type = ? "; //and c.dept_id in ("+dept+")";
    }
    public static String getCalSelectBydateNext(String dept){
        return sqlSelect+" where c.startDate > ? and c.cal_type = ? and c.moddate >= ? and c.moddate <= ? and c.dept_id in ("+dept+")";
    }
    public static String sqlGetSelectPostingList(int cnt){
            return sqlSelect+
          "  where  c.cal_type = ? and  "+
          " c.enddate >= DATE_FORMAT(now() ,'%Y-%m-%d') AND "+
          " c.startdate < DATE_FORMAT(DATE_ADD(now() ,interval "+cnt+" day),'%Y-%m-%d') "+
          " order by 13 desc";
    }
    public static String sqlGetSelectPostingListToday(int cnt){
            return sqlSelect+
          "  where  c.cal_type = ? and  "+
          " c.enddate >= DATE_FORMAT(now() ,'%Y-%m-%d') AND "+
          " c.startdate < DATE_FORMAT(DATE_ADD(now() ,interval "+cnt+" day),'%Y-%m-%d') "+
          " union "+sqlSelect+" where  c.cal_type = 0  and "+
                    " c.startDate >= DATE_FORMAT(now() ,'%Y-%m-%d') and "+
                    " c.startDate < DATE_FORMAT(DATE_ADD(now() ,interval 1 day),'%Y-%m-%d')"+
          " order by 13 desc";
    }
    
    public static final String
     sqlSelectPostCnt =
            " select count(*), DATE_FORMAT(c.moddate ,'%Y-%m-%d'),c.cal_type "+
            " from mgn_calendar c  "+
            " where c.moddate >= ? and c.moddate <= ? and c.cal_type = ? "+
            " group by DATE_FORMAT(c.moddate ,'%Y-%m-%d'),c.cal_type ",
                    
    selectSqlStartDateAndType = 
            sqlSelect+" where c.startDate >= ? and c.startDate <= ? and c.cal_type = ?",
    selectSqlEndDateAndType = 
            sqlSelect+" where c.enddate >= ? and c.enddate < ? and c.cal_type = ?",
   selectSqlModDateAndType = 
            sqlSelect+" where c.moddate >= ? and c.moddate < ? and c.cal_type = ?",
    calSelectById = 
        sqlSelect+" where c.cal_id= ?",
        calInsert =
           " INSERT INTO mgn_calendar "+
            " (cal_rollup_id,cal_desc,cal_type,startDate,enddate,user_id, "+
            " dept_id,lookup_id,lookup_flag,subject_text,subject_body) VALUES "+
            " (?,?,?,?,?,?,?,?,?,?,?)",
        calUpdate_v2 =
           " UPDATE mgn_calendar  SET "+
            " cal_rollup_id = ?,cal_desc = ?,cal_type = ?, startDate = ?, "+
            " enddate = ?, user_id = ?,dept_id = ?,lookup_id = ?,lookup_flag = ?, "+
            " subject_text = ?,subject_body = ?  WHERE cal_id = ? ",
            // ================================================================
            
            
            
            
            
        sqlCalSelect = 
    " select Cal_ID ,dept_id,CUST_ID ,	StartDate , enddate , 	short_desc, "+
	" contactName ,	Long_desc ,ADDR1 , ADDR2 ,  CITY , "+
        " STATE ,  ZIP ,  ZIP_PLUS , PHONE , FAX , "+
        " EMAIL ,	website ,access_lvl,access_dept  from  mgnCalendar "
            ;
    public static final String
      sqlCalInsert = 
          "  insert into  mgnCalendar (dept_id,CUST_ID ,	StartDate , enddate , 	short_desc, "+
	" contactName ,	Long_desc ,ADDR1 , ADDR2 ,  CITY ,STATE ,  ZIP ,  ZIP_PLUS , PHONE , FAX , "+
        " EMAIL ,website ,access_lvl,access_dept )" +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
       sqlCalUpadte =
            " update mgnCalendar set "+
           " dept_id = ?,CUST_ID  = ?,	StartDate  = ?, enddate = ? , 	short_desc = ?, "+    
	" contactName  = ?,	Long_desc  = ?,ADDR1  = ?, ADDR2  = ?,  CITY  = ?,STATE  = ?,"+
         " ZIP  = ?,  ZIP_PLUS  = ?, PHONE  = ?, FAX  = ?, "+
        " EMAIL = ? ,website  = ?,access_lvl = ?,access_dept  = ? where Cal_ID = ?"
            ;
}