/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcc.software;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 *
 * @author pradeep
 */
public class ConnectionFrame
extends JFrame {
    public static String userid;
    public static String port;
    public static String address;
    public static String toolusername;
    private JButton Insertlicense;
    private JButton btnnext;
    private JLabel jLabel1;
    private JSeparator jSeparator1;
    private JLabel lblPasswd;
    private JLabel lblUserId;
    private JLabel lbltconnectionmsg;
    private JPanel panelMainFrame;
    private JPasswordField txtPassword;
    private JTextField txtUserID;

    public ConnectionFrame() throws ParseException {
        this.setResizable(false);
        this.setIconImage(null);
        this.initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        this.setLocation(x, y);
        this.Refresh();
    }

    public void Refresh() {
        this.txtUserID.setText(userid);
    }

    private void initComponents() {
        this.panelMainFrame = new JPanel();
        this.lblUserId = new JLabel();
        this.txtUserID = new JTextField(20);
        this.btnnext = new JButton();
        this.jSeparator1 = new JSeparator();
        this.jLabel1 = new JLabel();
        this.lbltconnectionmsg = new JLabel();
        this.lblPasswd = new JLabel();
        this.txtPassword = new JPasswordField();
        this.Insertlicense = new JButton();
        this.setDefaultCloseOperation(3);
        this.setTitle("KCC SOFTWARE");
        this.setBounds(new Rectangle(0, 0, 500, 500));
        this.getContentPane().setLayout((LayoutManager)new GridLayout(1, 0));
        this.panelMainFrame.setBackground(Color.white);
        this.panelMainFrame.setBorder(BorderFactory.createLineBorder((Color)new Color(-16777216, true)));
        this.lblUserId.setFont(new Font("Times New Roman", 1, 14));
        this.lblUserId.setText("User ID");
        this.txtUserID.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                ConnectionFrame.this.txtUserIDActionPerformed(evt);
            }
        });
        this.btnnext.setText("Login");
        this.btnnext.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                ConnectionFrame.this.btnnextActionPerformed(evt);
            }
        });
        //this.jLabel1.setIcon((Icon)new ImageIcon(this.getClass().getResource("/images/Background.png")));
        this.lbltconnectionmsg.setText(" ");
        File configFile = new File("app.txt");
        if (!configFile.exists()) {
            this.lbltconnectionmsg.setText("<html><p color=red >Patch not found</p></html>");
        } else if (this.validCredentials()) {
            this.lbltconnectionmsg.setText("<html><p color=green >Patch accepted</p></html>");
        } else {
            this.lbltconnectionmsg.setText("<html><p color=red >Tool Expired</p></html>");
        }
        this.lblPasswd.setFont(new Font("Times New Roman", 1, 14));
        this.lblPasswd.setText("Password");
        this.txtPassword.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                ConnectionFrame.this.txtPasswordActionPerformed(evt);
            }
        });
        this.Insertlicense.setText("Insert License");
        this.Insertlicense.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                ConnectionFrame.this.InsertlicenseActionPerformed(evt);
            }
        });
        GroupLayout panelMainFrameLayout = new GroupLayout((Container)this.panelMainFrame);
        this.panelMainFrame.setLayout((LayoutManager)panelMainFrameLayout);
        panelMainFrameLayout.setHorizontalGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup((GroupLayout.Group)panelMainFrameLayout.createSequentialGroup().addContainerGap().addGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent((Component)this.lbltconnectionmsg, -1, 374, 32767).addGroup((GroupLayout.Group)panelMainFrameLayout.createSequentialGroup().addGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent((Component)this.jLabel1).addGroup(GroupLayout.Alignment.TRAILING, (GroupLayout.Group)panelMainFrameLayout.createSequentialGroup().addGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent((Component)this.lblPasswd, -2, 81, -2).addComponent((Component)this.lblUserId, -1, 113, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent((Component)this.txtPassword, GroupLayout.Alignment.LEADING).addComponent((Component)this.txtUserID, -2, 247, -2)))).addContainerGap()).addGroup((GroupLayout.Group)panelMainFrameLayout.createSequentialGroup().addComponent((Component)this.Insertlicense).addContainerGap(275, 32767)).addGroup((GroupLayout.Group)panelMainFrameLayout.createSequentialGroup().addComponent((Component)this.jSeparator1, -1, 364, 32767).addContainerGap())).addGroup(GroupLayout.Alignment.TRAILING, (GroupLayout.Group)panelMainFrameLayout.createSequentialGroup().addComponent((Component)this.btnnext, -2, 83, -2).addGap(142, 142, 142)))));
        panelMainFrameLayout.setVerticalGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup((GroupLayout.Group)panelMainFrameLayout.createSequentialGroup().addComponent((Component)this.jLabel1).addGap(55, 55, 55).addGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent((Component)this.txtUserID, -2, -1, -2).addComponent((Component)this.lblUserId)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup((GroupLayout.Group)panelMainFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent((Component)this.lblPasswd, -2, 20, -2).addComponent((Component)this.txtPassword, -2, -1, -2)).addGap(32, 32, 32).addComponent((Component)this.btnnext).addGap(42, 42, 42).addComponent((Component)this.Insertlicense).addGap(18, 18, 18).addComponent((Component)this.jSeparator1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent((Component)this.lbltconnectionmsg, -2, 31, -2).addContainerGap(87, 32767)));
        this.txtUserID.setDocument((Document)new SetTextFieldMethod(20));
        this.getContentPane().add((Component)this.panelMainFrame);
        this.pack();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void btnnextActionPerformed(ActionEvent evt) {
        if (this.validateLogin()) {
            Connection connection = null;
            int dbcount = 0;
            boolean error = true;
            main m=new main();
            m.setVisible(true);
            this.dispose();
            try {
                connection = DatabaseConnection.getInstance().getConnection();
                dbcount = DatabaseUtil.getDBCount(connection);
                if (dbcount > 1) {
                    JOptionPane.showMessageDialog((Component)this, (Object)"Database Server should contain only one database");
                    return;
                }
             
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog((Component)this, (Object)"Please verify Database Connection");
                return;
            }
            finally {
                DatabaseUtil.closeConnection(connection, null, null);
            }
        } else {
            JOptionPane.showMessageDialog((Component)this, (Object)"Please verify the Userid and Password");
        }
    }

    private void txtUserIDActionPerformed(ActionEvent evt) {
    }

    private void txtPasswordActionPerformed(ActionEvent evt) {
    }

    private void InsertlicenseActionPerformed(ActionEvent evt) {
        String cwd = System.getProperty((String)"user.dir");
        JFileChooser chooser = new JFileChooser(cwd);
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("select location");
        chooser.setFileSelectionMode(0);
        if (chooser.showOpenDialog((Component)this) != 0) {
            return;
        }
        File f = chooser.getSelectedFile();
        JTextArea txtSelectQuery1 = new JTextArea();
        ConnectionFrame.readin(f.toString(), (JTextComponent)txtSelectQuery1);
        try {
            ConfigSwingDemo configSwingDemo = new ConfigSwingDemo();
            try {
                configSwingDemo.saveProperties(f.getPath());
                PropertiesCache.getInstance().loadProperties();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            if (this.validCredentials()) {
                this.lbltconnectionmsg.setText("<html><p color=green >Patch accepted</p></html>");
                this.Insertlicense.setEnabled(false);
                this.btnnext.setEnabled(true);
                this.createStoreFunction();
            } else {
                this.lbltconnectionmsg.setText("<html><p color=red >Tool expired</p></html>");
                this.Insertlicense.setEnabled(true);
                this.btnnext.setEnabled(false);
            }
        }
        catch (Exception ex) {
            // empty catch block
        }
    }

    static void readin(String fn, JTextComponent pane) {
        try {
            FileReader fr = new FileReader(fn);
            pane.read((Reader)fr, null);
            fr.close();
        }
        catch (IOException e) {
            System.err.println((Object)e);
        }
    }

    private boolean validateLogin() {
        boolean flag = false;
        String txtuserid = this.txtUserID.getText();
        String pwd = String.copyValueOf((char[])this.txtPassword.getPassword());
        Map Configmap = PropertiesCache.getInstance().getConfigMap();
        String tooluserid = "";
        String tooluserpwd = "";
        if (Configmap != null) {
            tooluserid = (String)Configmap.get((Object)"Tooluser");
            tooluserpwd = (String)Configmap.get((Object)"password");
            flag = txtuserid == null || txtuserid.isEmpty() || txtuserid.trim().equals((Object)"") ? false : (pwd == null || pwd.isEmpty() || pwd.trim().equals((Object)"") ? false : txtuserid.equals((Object)tooluserid) && pwd.equals((Object)tooluserpwd));
        } else {
            flag = false;
        }
        return flag;
    }

    private void testconnection() {
        if (this.validCredentials()) {
            if (this.txtUserID.getText().isEmpty()) {
                this.lbltconnectionmsg.setText("<html><p color=red >UserID field is empty</p></html>");
            } else if (this.txtPassword.getText().isEmpty()) {
                this.lbltconnectionmsg.setText("<html><p color=red >Password field is empty</p></html>");
            } else {
                Map Configmap = PropertiesCache.getInstance().getConfigMap();
                String password = (String)Configmap.get((Object)"password");
                if (password == null || !password.equals((Object)this.txtPassword.getText())) {
                    this.lbltconnectionmsg.setText("<html><p color=red >Incorrect password</p></html>");
                    return;
                }
                this.convalidate();
            }
        }
    }

    private int convalidate() {
        userid = this.txtUserID.getText();
        try {
            if (!userid.equalsIgnoreCase(toolusername)) {
                this.lbltconnectionmsg.setText("<html><p color=red >Username field Mismatch</p></html>");
                return 0;
            }
            Connection conn = DatabaseConnection.getInstance().getConnection();
            if (conn == null) {
                this.lbltconnectionmsg.setText("<html><p color=red >Connection not set</p></html>");
                return 0;
            }
            this.lbltconnectionmsg.setText("<html><p color=green >Connection Established</p></html>");
            return 1;
        }
        catch (Exception ex) {
            ex.getStackTrace();
            this.lbltconnectionmsg.setText("<html><p color=red >Please Check Your Credentials</p></html>");
            return 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void dbdisplay() {
        String query = "select datname from pg_catalog.pg_database;";
        userid = this.txtUserID.getText();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            LinkedList list = new LinkedList();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add((Object)resultSet.getString("datname"));
            }
        }
        catch (Exception ex) {
            DatabaseUtil.closeConnection(conn, statement, resultSet);
            return;
        }
            
        catch (Throwable throwable) {
                DatabaseUtil.closeConnection(conn, statement, resultSet);
                throw throwable;
            }
        
        DatabaseUtil.closeConnection(conn, statement, resultSet);
        return;
    }

     public boolean validCredentials() {
        boolean flag = true;
        File configFile = new File("app.txt");
        if (!configFile.exists()) {
            this.lbltconnectionmsg.setText("<html><p color=red >Patch not found</p></html>");
            flag = false;
        } else {
            Map Configmap = PropertiesCache.getInstance().getConfigMap();
            if (Configmap != null) {
                toolusername = (String)Configmap.get((Object)"Tooluser");
                
                
            } else {
                flag = false;
            }
        }
        return flag;
    }
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void createStoreFunction() throws SQLException {
        Connection con = null;
        try {
            String dbname = DatabaseUtil.getDatabaseName();
            con = DatabaseConnection.getInstance().getConnection(dbname);
            String functionexistQuery = "select p.proname from pg_catalog.pg_namespace n join pg_catalog.pg_proc p on p.pronamespace =n.oid and p.proname='savingstock' where n.nspname='public' order by p.proname";
            PreparedStatement ps = null;
            ResultSet rs = null;
            boolean functionexist = false;
            ps = con.prepareStatement(functionexistQuery);
            rs = ps.executeQuery();
            if (rs.next()) {
                functionexist = true;
            }
            rs.close();
            ps.close();
            if (!functionexist) {
                String query = " CREATE OR REPLACE FUNCTION savingstock() RETURNS text AS $$ DECLARE  triggName1 RECORD  ;  triggTable1 RECORD ;  BEGIN  FOR triggName1 IN select distinct(trigger_name) from information_schema.triggers where trigger_schema = 'public' and trigger_name not in('tsearch2','app_calculate','savingstock') LOOP FOR triggTable1 IN SELECT distinct(event_object_table) from information_schema.triggers where trigger_name = triggName1.trigger_name LOOP RAISE NOTICE 'Dropping trigger: % on table: %', triggName1.trigger_name, triggTable1.event_object_table ;  EXECUTE 'DROP TRIGGER ' || triggName1.trigger_name || ' ON ' || triggTable1.event_object_table || ';' ;  END LOOP;\t END LOOP;  RETURN 'Done';  END ;  $$ LANGUAGE plpgsql SECURITY DEFINER;";
                CallableStatement st = con.prepareCall(query);
                st.execute();
                st.close();
            }
        }
        catch (Exception E) {
            E.printStackTrace();
        }
        finally {
            con.close();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        File f = new File("app.txt");
        f.deleteOnExit();
        EventQueue.invokeLater((Runnable)new Runnable(){

            public void run() {
                try {
                    ConnectionFrame connectionFrame = new ConnectionFrame();
                    connectionFrame.setVisible(true);
                    if (connectionFrame.validCredentials()) {
                        connectionFrame.Insertlicense.setEnabled(false);
                    }
                }
                catch (ParseException ex) {
                    Logger.getLogger((String)ConnectionFrame.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
                }
            }
        });
    }

    static {
        port = "5432";
        address = "localhost";
    }

}
