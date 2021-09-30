/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SUPER_FINAL_PROJECT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RG Choukikar
 */
public class AdminFrame extends javax.swing.JFrame {
Object[] items;
static Statement stt;
    /**
     * Creates new form AdminFrame
     */
    public AdminFrame() {
        this.items = new Object[]{new Integer(0),new String(),new String(),new String(),new Integer(0)};
        new DB_Project(0);
        ArrayList<String> brands=DB_Project.returnBrands();
        ArrayList<String> types=DB_Project.returnTypes();
        initComponents();
        
        
        String url ="jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
		String user="root";
		String password="";
                
               try{ Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection(url, user, password);
			//can only run 1 statement at a time, can't separate statements using ';'
			stt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//create and select DB
  			stt.execute("use project;");
               }
               catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){
                     Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, e);
                    
		}
               
               
        jComboBox1.removeAllItems();
        jComboBox1.addItem("ALL");     
        for(int i=0;i<brands.size();i++)
        {
            jComboBox1.addItem(brands.get(i));
        
        }
        
        
        jComboBox3.removeAllItems();
        jComboBox3.addItem("ALL");
        for(int i=0;i<types.size();i++)
        {
            jComboBox3.addItem(types.get(i));
        }
    }
    
    private void removeItems()
    {
        DefaultTableModel tb=(DefaultTableModel) jTable1.getModel();
        
        tb.setNumRows(0);
        
        jTable1.setModel(tb);
        System.out.println(jTable1.getRowCount()+""+tb.getRowCount());
    }
    
    private void addItems(ResultSet r) throws SQLException
    
    {
             
        DefaultTableModel tb=(DefaultTableModel) jTable1.getModel();
        
        
       
            while(r.next())
            {
                items[0]=r.getInt("pid");
                items[1]=r.getString("modelname");
                items[2]=r.getString("specs");
                items[3]=r.getInt("stock");
                items[4]=r.getInt("price");
                
                tb.addRow(items);    
            }
        
    }
    
   public int fetchPid()
    {
         int pid=Integer.parseInt( jTextField1.getText());
         return pid;
        
    }
   
   public int fetchPrice()
    {
         return(Integer.parseInt( jTextField4.getText()));
         
    }
   
   public int fetchStock()
    {
         return(Integer.parseInt( jTextField5.getText()));
         
    }
   public String fetchPname()
    {
         return(jTextField2.getText());
         
    }
   
   public String fetchSpecs()
    {
         return(jTextField3.getText());
         
    }
   
   public String fetchType()
    {
         return(jTextField6.getText());
         
    }
   
   public String fetchBrand()
    {
         String b=jTextField7.getText();
         return b;
         
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

        
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jLabel4.setText("Options");

        jButton1.setText("Add Product");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete Product");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Update Price");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Update Stock");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("View Product");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(25, 25, 25)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel3.setText("Filters");

        jLabel2.setText("Brand :");

        jLabel5.setText("Type:");

        jLabel6.setText("Product ID:");

        jLabel7.setText("Product Name:");

        jLabel8.setText("Specification:");

        jLabel9.setText("Price:");

        jLabel10.setText("Stock:");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel1.setText("Type:");

        jLabel11.setText("Brand:");

        jButton7.setText("Update P");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setText("Update S");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jTextField1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(288, 288, 288))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton6)))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "PRODUCT ID", "PRODUCT NAME", "SPECIFICATIO", "STOCK", "PRICE"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 5, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       jPanel3.setVisible(true);
       jButton8.setVisible(false);
       jButton7.setVisible(false);
       jButton6.setVisible(false);
        int pid = fetchPid();
        int stock = fetchStock();
        int price = fetchPrice();
        String pname = fetchPname();
        String specs  = fetchSpecs();
        String bname = fetchBrand();
        String type=fetchType();
       
        int bid=0;
        boolean res = false;
        boolean res1=false;
        int i;
         try{
             
         String add[][] = returnBid();
         
       int totrow = returnRow();
 
            
        for(i=0;i<totrow;i++)
             {
              System.out.println("ADD[ VALUE " +add[i][1]);
              System.out.println("Brand:" +bname);
              int len=add[i][1].length();
              String str=add[i][1].substring(0, len-1);
              
            if( (bname.equals(str)))
             //System.out.println("Match is: " +match);
                // if(match)
               {     System.out.println("bid is " +bid);
                     bid=Integer.parseInt(add[i][0]);
                     System.out.println("bid is " +bid);
                     break;
                 }
                 
             }
     
            
             if("laptop".equals(type))
             {
              // set FOREIGN_KEY_CHECKS = 0; 
             
              stt.execute("insert into product (pid,bid,type) "
                     + "values ("+pid+","+bid+","+"'"+type+"'"+");");
              stt.execute("insert into laptop (pid,modelname,stock,price,specs) "
                     + "values ("+pid+","+"'"+pname+"'"+ ","+stock+","+price+","+"'"+specs+"'" +");");
              
            
             }
             
             if("camera".equals(type))
             {
                 stt.execute("insert into product (pid,bid,type) "
                     + "values ("+pid+","+bid+",'"+type+"');");
             stt.execute("insert into camera (modelname, stock, price, specs) "
                    + "values ("+pid+",'"+pname+"',"+stock+", "+price+","+"'"+specs+"'" +");");
             }
             if("speaker".equals(type)){
                 stt.execute("insert into product (pid,bid,type) "
                     + "values ("+pid+","+bid+",'"+type+"');");
             stt.execute("insert into speaker (modelname, stock, price, specs) "
                     + "values ("+pid+",'"+pname+"',"+stock+", "+price+", '"+specs+"');");
             }
             if("mobile".equals(type)){
                 stt.execute("insert into product (pid,bid,type) "
                     + "values ("+pid+","+bid+",'"+type+"');");
             stt.execute("insert into mobile (modelname, stock, price, specs) "
                     + "values ("+pid+",'"+pname+"',"+stock+", "+price+", '"+specs+"');");
             }
             
         } 
         catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
            
         }
                       
 

        
//r handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
       jPanel3.setVisible(true);
       jLabel7.setVisible(false);
       jLabel8.setVisible(false);
       jLabel9.setVisible(false);
       jLabel10.setVisible(false);
       jLabel11.setVisible(false);
       jTextField2.setVisible(false);
       jTextField3.setVisible(false);
       jTextField4.setVisible(false);
       jTextField5.setVisible(false);
       jTextField7.setVisible(false);
       jButton8.setVisible(true);
       jButton7.setVisible(false);
       jButton6.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jPanel3.setVisible(true);
       jLabel7.setVisible(false);
       jLabel8.setVisible(false);
       jLabel9.setVisible(true);
       jLabel10.setVisible(false);
       jLabel11.setVisible(false);
       jTextField2.setVisible(false);
       jTextField3.setVisible(false);
       jTextField4.setVisible(true);
       jTextField5.setVisible(false);
       jTextField7.setVisible(false);
       jButton8.setVisible(false);
       jButton7.setVisible(true);
       jButton6.setVisible(false);
       
        
    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       jPanel3.setVisible(true);
       jLabel7.setVisible(false);
       jLabel8.setVisible(false);
       jLabel9.setVisible(false);
       jLabel11.setVisible(false);
       jLabel10.setVisible(true);
       jTextField2.setVisible(false);
       jTextField3.setVisible(false);
       jTextField4.setVisible(false);
       jTextField7.setVisible(false);
       jTextField5.setVisible(true);
       jButton8.setVisible(false);
       jButton7.setVisible(false);
       jButton6.setVisible(true);
       
       
       
         
   
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jPanel3.setVisible(false);
   String type,brand;
    int brand1;
type=""+jComboBox3.getSelectedItem();
brand=""+jComboBox1.getSelectedItem();
brand1=jComboBox1.getSelectedIndex();
ResultSet r = null;
String[] types=new String[]{"Laptop","Camera","Mobile","Speaker"};
    
removeItems();
if(type.equals("ALL"))
{
        try {
            for(String a:types)
            {
                r=DB_Project.returnAdmin(a, brand1,brand);
                addItems(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
}
else {
        try {
            r=DB_Project.returnAdmin(type, brand1,brand);
              addItems(r);
          
// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(CustomerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                              


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         int pid=fetchPid();
        String type=fetchType();
       // match = false;
        int bid=0;
        
              try{
             if("laptop".equals(type))
             {
              // set FOREIGN_KEY_CHECKS = 0; 
             
             stt.execute("delete from laptop where pid=" +pid+";");
             stt.execute("delete from product where pid=" +pid+";");
                     
              
             // set FOREIGN_KEY_CHECKS = 1; 
            
             }
             
             if("camera".equals(type))
             {
                stt.execute("delete from camera where pid="  +pid+";");
                stt.execute("delete from product where pid="  +pid+";");
             }
             if("speaker".equals(type)){
                 stt.execute("delete from speaker where pid="  +pid+";");
                 stt.execute("delete from product where pid="  +pid+";");
             }
             if("mobile".equals(type)){
                 stt.execute("delete from mobile where pid="  +pid+";");
                 stt.execute("delete from product where pid="  +pid+";");
             }
             JOptionPane.showMessageDialog(null, "Successfully deleted");
         } 
         catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
            
         }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String lap="",mob="",sp="",cam="";
        String type=fetchType();
        int price=fetchPrice();
        int pid=fetchPid();
        
        try {
            if("laptop".equals(type))
        {
           lap = "UPDATE laptop"
				+ " SET price =" + price 
				+ " WHERE pid =" +pid;
            stt.execute(lap);
                     
         } 
             if("mobile".equals(type))
        {
           lap = "UPDATE mobile"
				+ " SET price =" + price 
				+ " WHERE pid =" +pid;
            
           stt.execute(mob);
                     
         } 
              if("speaker".equals(type))
        {
           lap = "UPDATE speaker"
				+ " SET price =" + price 
				+ " WHERE pid =" +pid;
            
           stt.execute(sp);
                     
         } 
               if("camera".equals(type))
        {
           lap = "UPDATE camera"
				+ " SET price =" + price 
				+ " WHERE pid =" +pid;
            
           stt.execute(cam);
                     
         } 
               JOptionPane.showMessageDialog(null, "Successfully update");
           
        }
        catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
            
         }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String lap="",cam="",mob="",sp="";
         int pid=fetchPid();
         int stock1=fetchStock();
         String type=fetchType();
        try {
            if("laptop".equals(type))
        {
             
            ResultSet res=stt.executeQuery("select stock from laptop where pid = " +pid);
            res.next();
              int st=Integer.parseInt(res.getString(1));
              st=st+stock1;
              
              //System.out.println(st);            
              lap = "UPDATE laptop"
				+ " SET stock =" + st 
				+ " WHERE pid =" +pid;               
                         
           stt.execute(lap);
        }
            
             if("camera".equals(type))
        {
             
            ResultSet res=stt.executeQuery("select stock from camera where pid = " +pid);
            res.next(); 
              int st=Integer.parseInt(res.getString(1));
              st=st+stock1;
              //System.out.println(st);            
              lap = "UPDATE camera"
				+ " SET stock =" + st 
				+ " WHERE pid =" +pid;               
                         
           stt.execute(cam);
        }
              if("mobile".equals(type))
        {
             
            ResultSet res=stt.executeQuery("select stock from mobile where pid=" +pid);
            res.next(); 
              int st=Integer.parseInt(res.getString(1));
              st=st+stock1;
              //System.out.println(st);            
              lap = "UPDATE mobile"
				+ " SET stock =" + st 
				+ " WHERE pid =" +pid;               
                         
           stt.execute(mob);
        }
              
               if("speaker".equals(type))
        {
             
            ResultSet res=stt.executeQuery("select stock from speaker where pid = " +pid);
            res.next(); 
              int st=Integer.parseInt(res.getString(1));
              st=st+stock1;
              //System.out.println(st);            
              lap = "UPDATE speaker"
				+ " SET stock =" + st 
				+ " WHERE pid =" +pid;               
                         
           stt.execute(sp);
        }
              JOptionPane.showMessageDialog(null, "Successfully update");
         } 
            
           
         catch (SQLException ex) {
             Logger.getLogger(DB_Project.class.getName()).log(Level.SEVERE, null, ex);
          
         }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
