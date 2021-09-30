/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SUPER_FINAL_PROJECT;
//import required package
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Shailee Jain
 */
public class DB_Project {
     static int customer_id=1;
     static String[] details = new String[6];
     static Statement stt;
     static Statement stt2;
     static ResultSet res;
     static ResultSet res2;
      static Integer[] pids=new Integer[50];
    static Integer[] quant= new Integer[50];
    static int cart_size=0;

    
     public DB_Project(int track){
                customer_id = track;
                String url ="jdbc:mysql://localhost:3306";
		String user="root";
		String password="shrev95";
                
    		try{
			//to identify MySQL driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection(url, user, password);
			//can only run 1 statement at a time, can't separate statements using ';'
			stt=con.createStatement();
			//create and select DB
  			stt.execute("use etronics;");
			
              	}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){
		Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, e);
                }
    }
     
     static String getName(int result) {
       try {
             res = stt.executeQuery("select * from customer where custid='"+result+"'");
             if (res.next())
             {return res.getString("custname");}
       } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);}
       return null;
    }
     
    // SIGN-UP
    public static int addCust(String name, String addr, String usrname, String pswd, String dob) {
       
         try {
             stt.execute("insert into customer (custname, address, password, username, dob) "
                     + "value ('"+name+"', '"+addr+"', '"+pswd+"', '"+usrname+"', '"+dob+"');");
             res = stt.executeQuery("select * from customer where custname='"+name+"'");
             if (res.next())
             {
                 String i = res.getString("custid");
                 return Integer.parseInt(i); 
             }
             return 0;
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return -1;
         }
     }
    //LOG IN
    public static int login(String username, String passwd){
         customer_id=-1;
         System.out.println(""+username+" "+passwd);
         try {
             res = stt.executeQuery("select * from customer where username='"+username+"' and password='"+passwd+"';");
             if (res.next())
        {
                //customer_id = res.getInt("custid");
                 details[0]=res.getString("custid");
                 details[1]=res.getString("custname");
                 details[2]=res.getString("address");
                 details[3]=res.getString("username");
                 details[4]=res.getString("wallet");
                 details[5]=res.getString("dob");
            
             
            return Integer.parseInt(details[0]);
        }
        
        else
            return -2;
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return -1;
         }
	
    }
    public static ResultSet listProducttype(String type) {
            try {
             if(null != type){
             switch (type) {
             case "laptop":
                res=stt.executeQuery("select * from laptop");
                 break;
             case "mobile":    
                 res=stt.executeQuery("select * from mobile");
                 break;
             case "camera":
                 res=stt.executeQuery("select * from camera");
                 break;
             case "speaker":
                 res=stt.executeQuery("select * from speaker");
                 break;   
             }
             
            }
          } catch (SQLException ex) {
                Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
            }
            return res;
        }
    public static void listBrandtype(String type){
         try {
             res=stt.executeQuery("select * from product where product.bid=(select bid from brand where bname='"+type+"');");
             while(res.next()){
                 System.out.println(res.getString("pid")+" "+res.getString("modelname")+" "+res.getString("stock")+" "+res.getString("price")+" "+res.getString("specs"));
             }} catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    }
    
     
     public static void addFriend(int fid){
           try {
                 stt.execute("insert into friendship values("+customer_id+", "+fid+")");
             } catch (SQLException ex) {
                 Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
     
     public static void deleteFriend(int fid){
         
             try {
                 stt.execute("delete from friendship where (custid="+customer_id+" AND fid="+fid+") OR (custid="+fid+" AND fid="+customer_id+")");
               
             } catch (SQLException ex) {
                 Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
     
     public static int changeUserName(String newUserName){
         try {
                 stt.execute("update customer set username='"+newUserName+"' where custid="+customer_id);
                 return 1;

         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return -1;
         }
}
     
     
     public static int changePassword(String oldPassword, char[] newPass){
         try {String token="";
              String newPassword = new String(newPass);
             res=stt.executeQuery("select password from customer where custid="+customer_id);
              while(res.next())
             {
                token = res.getString("password");
             }
             if(oldPassword.equals(token)){
                 stt.execute("update customer set password='"+newPassword+"' where custid="+customer_id);
                 return 1;
             }
            
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return -1;
         }
         return 0;
     }
     public static int addWallet(String add){
         try {
             String base="";
             res=stt.executeQuery("select * from customer where custid="+customer_id);
             while(res.next())
             {
                 base =res.getString("wallet");
             }
             int amount=Integer.parseInt(base) + Integer.parseInt(add);
             stt.execute("update customer set wallet='"+amount+"' where custid="+customer_id);
             return amount;
             
         } catch (SQLException ex) { 
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return 0;
         }
     }
     
     public static ArrayList<String> returnBrands()
    {
        ArrayList<String> brands= new ArrayList<String>();
        int i=0;
         try {
             ResultSet res=stt.executeQuery("select bname from brand");
             while(res.next())
             {
                 brands.add(res.getString("bname"));
             }
             return brands;
         } catch (SQLException ex) {
             return null;
         }
        
    }
     
     
     public static ArrayList<String> returnTypes()
    {
        ArrayList<String> types= new ArrayList<String>();
        int i=0;
         try {
             ResultSet res=stt.executeQuery("select distinct(type) 'type' from product;");
             while(res.next())
             {
                 types.add(res.getString("type"));
             }
             return types;
         } catch (SQLException ex) {
             return null;
         }
        
    }
     
     public static int checkFriend(int frid) {
         try {
             int flag=0;
             res=stt.executeQuery("select custid from friendship where fid="+frid+" and custid="+customer_id);
             if(res.next()) return 1;
             res2=stt.executeQuery("select fid from friendship where custid="+frid+" and fid="+customer_id);
             if(res2.next()) return 1; 
             return 0;
             } catch (SQLException ex) {
                Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
         }
     }  
     
     public static String[] viewProfile(String friend){   
         try {
             res=stt.executeQuery("select * from customer where custname=friend");
             
        if(res.next()){
            String[] details = new String[3];
            details[0]=res.getString("custid");
            details[1]=res.getString("custname");
            details[2]=res.getString("username");
            return details;
            
        }
        else{
            return null;
        }
       
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
         
     }
     public static String[] viewFriend(int fid){   
         try {
             res=stt.executeQuery("select * from customer where custid="+fid);
             
        if(res.next()){
            String[] details = new String[5];
            details[0]=res.getString("custid");
            details[1]=res.getString("custname");
            details[2]=res.getString("address");
            details[3]=res.getString("username");
            details[4]=res.getString("dob");
            return details;
            
        }
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
         return null;
         
     }
     
     public static int updateReview(int tid, int pid, String review){   
        try {
                 stt.execute("update transaction set prodReview='"+review+"' where tid="+tid+"AND pid="+pid);
                 return 1;

         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return -1;
         }
         
     }

     
     public static String[] returnPotentialFriend(String name)
    {
        ResultSet res;
        String[] details = new String[3];
         try {
             res=stt.executeQuery("select * from customer where custname='"+name+"'");
             while(res.next())
             {
                 details[0]=res.getString("custid");
                 details[1]=res.getString("custname");
                 details[2]=res.getString("username");
                 
             }
             return details;
             
             } catch (SQLException ex) {
             return null;
         }
     }
     
     public static ArrayList<Integer> returnFriends()
    {
        ArrayList<Integer> friends= new ArrayList<Integer>();
        ResultSet res;
        int i, token1, token2;
         try {
             res=stt.executeQuery("select * from friendship where custid="+customer_id+" OR fid="+customer_id);
             while(res.next())
             {
                 token1=res.getInt("fid");
                 token2=res.getInt("custid");
                 if(token1!=customer_id) friends.add(token1);
                 else                    friends.add(token2);
             }
             return friends;
             
             } catch (SQLException ex) {
             return null;
         }
     }
     
     public static ArrayList<Integer> returnTids()
    {
        ArrayList<Integer> tids= new ArrayList<Integer>();
        ResultSet res;
        int i, token;
         try {
             res=stt.executeQuery("select * from transaction where custid="+customer_id);
             while(res.next())
             {
                 token=res.getInt("tid");
                 tids.add(token);
                 token=res.getInt("pid");
                 tids.add(token);
                 
             }
             return tids;
             
             } catch (SQLException ex) {
             return null;
         }
     }

     
      public static ResultSet returnFriendDetails(Integer token){
         try {  
                res=stt.executeQuery("select * from customer where custid="+token);          
                return res;
                
         } catch (SQLException ex) {
             return null;
         }
}
        
    public static ResultSet returnReviews(Integer token1, Integer token2){
         try {
             res=stt.executeQuery("SELECT * FROM (SELECT transaction.tid, transaction.pid, transaction.custid, transaction.prodReview, A.modelname FROM transaction,(SELECT * FROM laptop UNION SELECT * FROM mobile UNION SELECT * FROM camera UNION SELECT * FROM speaker)A WHERE transaction.pid=A.pid)B WHERE tid="+token1+" AND pid="+token2+";");
             return res;
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
}

              
     public static ResultSet returnDetails(String type,String brand,int price)
     {
        if(brand.equals("ALL"))
        {
            if(price==0)
            {
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid;");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
   
            }
            if(price==1)
            {
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and t.price<20000;");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(price==2){
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and t.price between 20000 and 75000;");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(price==3){
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and t.price between 20000 and 75000;");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            if(price==0){
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and b.bname='"+brand+"';");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(price==1){
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and b.bname='"+brand+"' and t.price<20000;");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(price==2){
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and b.bname='"+brand+"' and t.price between 20000 and 75000;");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(price==3){
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and b.bname='"+brand+"' and t.price>75000;");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
         return res;
     }
     
     public static ResultSet returnAdmin(String type,int brand,String brand1)
     {
         String b = "";
        if(brand==0)
        {
            
                try {
                    res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid;");
                } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
   
            
        }
        
        else
        {
          try {
              //int len=brand1.length();
              //String str=brand1.substring(0, len-1);
              //System.out.print(b);
              //b= str+"%";
                    //res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and b.bname like '"+b+"');");
          res=stt.executeQuery("Select t.*,p.bid,b.bname from "+type+" t,product p,brand b where t.pid=p.pid and p.bid=b.bid and b.bname='"+brand1+"';");      
          } catch (SQLException ex) {
                    Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
                }  
        }
        return res;
     }
      public static String[][] returnBid()
    {
        String arraybid[][];
        int i=0;
        int totrow=0;
         try {
             ResultSet res=stt.executeQuery("select * from brand;");
             res.last();
             totrow = res.getRow();
             res.beforeFirst();
              arraybid = new String[totrow][2];
             while(res.next())
             {
                arraybid[i][0] = res.getString(1);
                arraybid[i][1] = res.getString(2);
                i++;
                
             }
             return arraybid;
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
        
    }
     
      public static int returnRow()
    {
        int totrow=0;
        try {
             ResultSet res=stt.executeQuery("select * from brand;");
             res.last();
             totrow = res.getRow();
             res.beforeFirst();
              
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
             return 0;
         }
    return totrow;
    }
      
      public static int max_tid()
      {
          int tid=-1;
         try {
             res=stt.executeQuery("select max(tid) t from transaction;");
             if(res.next())
             {
                 tid=res.getInt("t");
             }
             else tid=-1;
             
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
         }
         return tid;
      }
      
      public static void buy_product(int tid,int pid,int cid,int units)
      {
         try {
             stt.executeUpdate("insert into transaction values("+tid+","+pid+","+cid+",NULL,NULL,"+units+");");
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
         }
          
      }
      
      public static int get_wallet(int cid)
      {
          int x=0;
         try {
             res=stt.executeQuery("select wallet from customer where custid="+cid+";");
             if(res.next())
             x= res.getInt("wallet");
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
         }
         return x;
      }
      
      public static void set_wallet(int cid,int amt)
      {
          try {
             stt.execute("Update customer set wallet="+amt+" where custid="+cid+";");
             
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      
       public static String getReview(int pid,int fid)
     {
         String r="";
         try {
             res=stt.executeQuery("select comment from transaction where custid="+fid+" and pid="+pid+" and comment is not null order by tid desc limit 1;");
             if(res.next()) r=res.getString("comment");
         } catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
         }
         return r;
         
     }
              
   }  

