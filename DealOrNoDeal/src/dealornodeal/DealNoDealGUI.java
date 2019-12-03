/*
May Trinh, David McGinn
This programs recreates the famouse game show Deal or No Deal using Java GUI.
The interface have 26 buttons acts as 26 cases that corresponds a prize value
from $1 - $1,000,000. The programs also calculate the banker's offer using an 
algorithm that take 30% to 80% of the remaining cases depend on the number of 
turns taken. 
*/


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealornodeal;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Random;
import javax.swing.border.*;
import java.awt.geom.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author maytrinh
 */
public class DealNoDealGUI extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form DealNoDealGUI
     */
    Double[] doubles = new Double[] {1.0,2.0,5.0,10.0,25.0,50.0,75.0,100.0,200.0,300.0,400.0,500.0,750.0,1000.0,5000.0,10000.0,25000.0,50000.0,75000.0,100000.0,200000.0,300000.0,400000.0,500000.0,750000.0,1000000.0};
    ArrayList<Double> prizeArray = new ArrayList<Double>(Arrays.asList(doubles));
    int turn = 0;
    ArrayList<JLabel> jlabelList = new ArrayList<JLabel>();
    ArrayList<JButton> ButtonList = new ArrayList<JButton>();

    JLabel instruction = new JLabel();
    JButton chosen = new JButton();
    String chosenValue;
    
//  Image source: https://icon-library.net/icon/luggage-icon-5.html
    ImageIcon icon = new ImageIcon("thin-0506_suitcase_travel_baggage_luggage-512.png");
    Image img = icon.getImage();
    Image newimg = img.getScaledInstance(90, 75,  java.awt.Image.SCALE_SMOOTH);

//  Image source: https://uxwing.com/dollar-icon/
    ImageIcon dollar = new ImageIcon("Dollar-512.png");
    Image dollarimg = dollar.getImage();
    Image newDollar = dollarimg.getScaledInstance(20, 25,  java.awt.Image.SCALE_SMOOTH);

// Image source: https://pngimage.net/deal-or-no-deal-logo-png/
    ImageIcon logo = new ImageIcon("logoDealNoDeal.png");
    Image logoImg= logo.getImage();
    Image newLogo = logoImg.getScaledInstance(500, 250,  java.awt.Image.SCALE_SMOOTH);

    
    Color gold = new Color (252, 186, 3);
    AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,16,0);
        
    public DealNoDealGUI() {
        initComponents();

//---------------------------------------------------------
    //Adding Logo 
        JLabel logoLabel = new JLabel();
        logo = new ImageIcon(newLogo);
        logoLabel = new JLabel(logo);
        logoLabel.setBounds(75,0,500,75);
        jPanel1.add(logoLabel);
        
   //---------------------------------------------------------
    //Build menu bar  
        //in the constructor for a JFrame subclass:
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Build the first menu.
        menu = new JMenu("Options");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("Restart");
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DealNoDealGUI.this.dispose();
                new DealNoDealGUI().setVisible(true); 
            }
        });
        
        menu.add(menuItem);
        menuItem = new JMenuItem("Help");
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = new JFrame();
                
                String message = "<html> Based off of the poplar TV Game, the Deal or No Deal game will put you in the hot seat. "
+ "Players of Deal or No Deal choose a briefcase with a hidden amount of cash in it. The game gets tricky as 26 briefcases \n"
+"slowly get opened and your chances of having the million dollars either increases or decreases. Will you take the bankers offer or test your luck with your chosen case? "
+ "The Deal or No Deal game is great fun and \n" + "a test of nerves as you push your luck with each case number you choose. "
+ "Deal or No Deal is a fun game to play at home and see if you can win the $1,000,000. Play the game Deal or No Deal today!\n\n" 
+ "The show starts with 26 identical sealed boxes. Each contains an amount of money between $1 and $1,000,000. At the start of the game the player selects a numbered box at random.\n" 
+"The player will call out the numbers of 4 briefcases to open. These are opened one at a time and the cover next to the corresponding value on the money board is closed after each briefcase is opened.\n" +
"The bank now makes a money offer to the player to buy their briefcase.\n"
+ "OBJECTIVE: To try to make the best deal possible by either keeping the briefcase that you first choose or by getting an offer from the banker that’s worth even more.";

                JOptionPane.showMessageDialog(frame,message);
            }
        });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Credit");
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = new JFrame();
                String message = "Created by: May Trinh and David McGinn";
                JOptionPane.showMessageDialog(frame,message);
            }
        });
        menu.add(menuItem);
 //---------------------------------------------------------
        
        
//---------------------------------------------------------
//Build main inteface

        instruction.setBounds(0,80,645,40);
        instruction.setText("CHOOSE YOUR CASE");
        instruction.setFont(new Font("Helvetica", Font.BOLD, 25));
        instruction.setHorizontalAlignment(JLabel.CENTER);
        instruction.setVerticalAlignment(JLabel.CENTER);
        instruction.setBackground(gold);
        instruction.setForeground(Color.black);
        instruction.setBorder(brdr);
        instruction.setOpaque(true);
        jPanel1.add(instruction);

        
        // initialize prize text field
        for (int i = 0; i < prizeArray.size()/2; i++){
            JLabel l;
            dollar = new ImageIcon(newDollar);
            l = new JLabel(dollar);
            l.setText(Double.toString(prizeArray.get(i)));
            l.setName(Double.toString(prizeArray.get(i)));
            l.setHorizontalAlignment(JLabel.LEFT);
            l.setBounds(15,10+i*35,150,30);
            l.setBorder(brdr);
            l.setBackground(gold);
            l.setFont(new Font("Helvetica",Font.BOLD,15));
            l.setOpaque(true);
            
            jlabelList.add(l);
            add(l);
        }
        for (int i = 0; i < prizeArray.size()/2; i++){
            JLabel l;
            dollar = new ImageIcon(newDollar);
            l = new JLabel(dollar);
            l.setText(Double.toString(prizeArray.get(i+13)));
            l.setName(Double.toString(prizeArray.get(i+13)));
            l.setHorizontalAlignment(JLabel.LEFT);
            l.setBounds(860,10+i*35,150,30);
            l.setBorder(brdr);
            l.setBackground(gold);
            l.setFont(new Font("Helvetica",Font.BOLD,15));
            l.setOpaque(true);
            
            jlabelList.add(l);
            add(l);
        }
        //initialize button
        for (int i = 0; i < prizeArray.size()/4; i++){
            JButton b;
            icon = new ImageIcon(newimg);
            b = new JButton(Integer.toString(i+1),icon);
            b.setVerticalTextPosition(SwingConstants.CENTER);
            b.setHorizontalTextPosition(SwingConstants.CENTER);
            b.setName(Integer.toString(i+1));
            b.setBounds(100*i+25,130,100,75);
            b.setFont(new Font("Helvetica", Font.BOLD, 30));
            b.addActionListener(this);
            ButtonList.add(b);
            jPanel1.add(b);
        }
        
        for (int i = 0; i < prizeArray.size()/4; i++){
            JButton b;
            icon = new ImageIcon(newimg);
            b = new JButton(Integer.toString(i+7),icon);
            b.setVerticalTextPosition(SwingConstants.CENTER);
            b.setHorizontalTextPosition(SwingConstants.CENTER);
            b.setName(Integer.toString(i+7));
            b.setBounds(100*i+25,220,100,75);
            b.setFont(new Font("Helvetica", Font.BOLD, 30));
            b.addActionListener(this);
            ButtonList.add(b);
            jPanel1.add(b);
        }
        
        for (int i = 0; i < prizeArray.size()/4; i++){
            JButton b;
            icon = new ImageIcon(newimg);
            b = new JButton(Integer.toString(i+13),icon);
            b.setVerticalTextPosition(SwingConstants.CENTER);
            b.setHorizontalTextPosition(SwingConstants.CENTER);
            b.setName(Integer.toString(i+13));
            b.setBounds(100*i+25,310,100,75);
            b.setFont(new Font("Helvetica", Font.BOLD, 30));
            b.addActionListener(this);
            ButtonList.add(b);
            jPanel1.add(b);
        }
        for (int i = 0; i < prizeArray.size()/4; i++){
            JButton b;
            icon = new ImageIcon(newimg);
            b = new JButton(Integer.toString(i+19),icon);
            b.setVerticalTextPosition(SwingConstants.CENTER);
            b.setHorizontalTextPosition(SwingConstants.CENTER);
            b.setName(Integer.toString(i+19));
            b.setBounds(100*i+25,400,100,75);
            b.setFont(new Font("Helvetica", Font.BOLD, 30));
            b.addActionListener(this);
            ButtonList.add(b);
            jPanel1.add(b);
        }
        
        for (int i = 0; i < 2; i++){
            JButton b;
            icon = new ImageIcon(newimg);
            b = new JButton(Integer.toString(i+25),icon);
            b.setVerticalTextPosition(SwingConstants.CENTER);
            b.setHorizontalTextPosition(SwingConstants.CENTER);
            b.setName(Integer.toString(i+25));
            b.setBounds(100*i+25,490,100,75);
            b.setFont(new Font("Helvetica", Font.BOLD, 30));
            b.addActionListener(this);
            ButtonList.add(b);
            jPanel1.add(b);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Deal or No Deal"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DealNoDealGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DealNoDealGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DealNoDealGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DealNoDealGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DealNoDealGUI().setVisible(true); 
            }
        });
    }
    
 //---------------------------------------------------------
// Action event for button    
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String command = button.getActionCommand();


        if (turn == 0){ //first case pick
            
            chosen = button;
            chosen.setLocation(500,500);
            jPanel1.add(chosen);
            
            Random rand = new Random();
            int rand_int1 = rand.nextInt(prizeArray.size()); 
            chosenValue = Double.toString(prizeArray.get(rand_int1));
            prizeArray.remove(rand_int1);
            
            button.setEnabled(false);
            instruction.setText("PICK 4 CASES");
        }
        else if (turn % 4 == 0){ //show offer every 4 cases after the first pick
            double exp = PredictValue(prizeArray);
            double valueOffer = Offer(exp,prizeArray.size(),Collections.max(prizeArray), turn);
            System.out.println(valueOffer);
            
//            show pop up window
            JFrame frame = new JFrame();
            JPanel popup = new JPanel();

            JButton button1= new JButton("Deal");
            button1.setName("Deal");
            
            JButton button2= new JButton("No Deal");
            button2.setName("No Deal");
            
            JLabel offer = new JLabel ("BANKER OFFERS : $ "+ Double.toString(valueOffer));
            offer.setFont(new Font("Helvetica", Font.BOLD, 25));
            offer.setBorder(brdr);
            offer.setBackground(gold);
            offer.setOpaque(true);
            offer.setHorizontalAlignment(JLabel.CENTER);
            
            button2.addActionListener(new ActionListener() { //event action for no deal
                public void actionPerformed(ActionEvent e) {
                    JButton button2 = (JButton) e.getSource();
                    String command = button2.getActionCommand();
                    if(turn == 24){ //last offer made
                        removePrize(jlabelList, prizeArray);
                        
                        Random rand = new Random();
                        int rand_int1 = rand.nextInt(prizeArray.size()); 
                        String value = Double.toString(prizeArray.get(rand_int1));
        
                        offer.setText("You won $" + chosenValue + " in Case " + chosen.getName());
                        
                        JButton ok = new JButton("OK");
                        ok.setBorder(brdr);
                        ok.setBackground(gold);
                        ok.setFont(new Font("Helvetica", Font.BOLD, 20));

                        JPanel pop = new JPanel();
                        
                        ok.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                frame.setVisible(false);
                                JLabel c = null;
                                for (JLabel jlabelList1 : jlabelList) {
                                    c = jlabelList1;
                                    c.setEnabled(false);
                                }

                                JButton b = null;
                                for (JButton jButtonList : ButtonList) {
                                    b  = jButtonList;
                                    b.setEnabled(false);   
                                }
                                instruction.setText("WINNING PRIZE: $ " + chosenValue);
                            }
                        });

                        pop.add(ok);
                        pop.setBackground(Color.BLACK);
                        
                        frame.getContentPane().add(BorderLayout.SOUTH, pop);
                        frame.add(offer);
                        button1.setVisible(false);
                        button2.setVisible(false);
                        button.setVisible(false);
                    }
                    else{
                        frame.setVisible(false);
                        button.setVisible(false);
                        removePrize(jlabelList, prizeArray);
                    }
                }
            });
            
            button1.addActionListener(new ActionListener() { //event action for deal
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    instruction.setText("WINNING PRIZE: $ " + valueOffer);
                    
                    //if player take the deal, disabled all button and label
                    JLabel c = null;
                    for (JLabel jlabelList1 : jlabelList) {
                        c = jlabelList1;
                        c.setEnabled(false);
                    }
                    
                    JButton b = null;
                    for (JButton jButtonList : ButtonList) {
                        b  = jButtonList;
                        b.setEnabled(false);   
                    }
                    System.out.print("Prize value in your case: " + chosenValue);
                }
            });
            button1.setBorder(brdr);
            button1.setBackground(gold);
            button1.setFont(new Font("Helvetica", Font.BOLD, 20));
            
            button2.setBorder(brdr);
            button2.setBackground(gold);
            button2.setFont(new Font("Helvetica", Font.BOLD, 20));
            
            popup.add(button1);
            popup.add(button2);
            popup.setBackground(Color.BLACK);

            frame.getContentPane().add(BorderLayout.SOUTH, popup);
            frame.getContentPane().add(offer);
            frame.getContentPane().setBackground(Color.black);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(300, 200);
//            frame.setVisible(true);
            
            JFrame frame1 = (JFrame) SwingUtilities.getWindowAncestor(frame);
            JDialog dialog = new JDialog(frame, "Dialog", true);
            
            dialog.getContentPane().add(frame.getContentPane());
            dialog.pack();
            dialog.setSize(500,200);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
//            dialog.getContentPane().setBackground(gold);

        }

        else{
            button.setVisible(false);
            removePrize(jlabelList, prizeArray);
        }
        turn++;
    }
    
   
//---------------------------------------------------------
//remove the prize from the prize array for each chosen case
    public static void removePrize(ArrayList<JLabel> jlabelList, ArrayList<Double> prizeArray){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(prizeArray.size()); 
        String value = Double.toString(prizeArray.get(rand_int1));
        prizeArray.remove(rand_int1);
        
        JLabel c = null;
        for (JLabel jlabelList1 : jlabelList) {
            if (jlabelList1.getName().equalsIgnoreCase(value)) {
                c = jlabelList1;
//                c.setEnabled(false);
                c.setBackground(new Color(181, 143, 139));
            }
        }
    }

//---------------------------------------------------------
//    calculate banker offer
    public static double Offer(double Expected, int NumCases, double Maximum, int turn){
        double offer;
        
//offer = a percentage of the remaning case (from 30% to 80% depends on number of
// turns taken
        offer=Expected*(Double.valueOf(turn)/3.0/10.0);
        return Math.round(offer);

    }

 //---------------------------------------------------------
// calculate the expected value = average of the remaining cases
    public static double PredictValue(ArrayList<Double> prize){     
        double sum = 0.0;
          for (double i : prize) {
            sum += i;
        }
        double average = sum / prize.size();
        return average;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

//---------------------------------------------------------
// Design prize label
class TextBubbleBorder extends AbstractBorder {

    private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    private boolean left = true;
    RenderingHints hints;

    TextBubbleBorder(
            Color color) {
        this(color, 4, 8, 7);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize) {
        this.thickness = thickness;
        this.radii = radii;
        this.pointerSize = pointerSize;
        this.color = color;

        stroke = new BasicStroke(thickness);
        strokePad = thickness / 2;

        hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = radii + strokePad;
        int bottomPad = pad + pointerSize + strokePad;
        insets = new Insets(pad, pad, bottomPad, pad);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize, boolean left) {
        this(color, thickness, radii, pointerSize);
        this.left = left;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    @Override
    public void paintBorder(
            Component c,
            Graphics g,
            int x, int y,
            int width, int height) {

        Graphics2D g2 = (Graphics2D) g;

        int bottomLineY = height - thickness - pointerSize;

        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                0 + strokePad,
                0 + strokePad,
                width - thickness,
                bottomLineY,
                radii,
                radii);

        Polygon pointer = new Polygon();

        if (left) {
            // left point
            pointer.addPoint(
                    strokePad + radii + pointerPad,
                    bottomLineY);
            // right point
            pointer.addPoint(
                    strokePad + radii + pointerPad + pointerSize,
                    bottomLineY);
            // bottom point
            pointer.addPoint(
                    strokePad + radii + pointerPad + (pointerSize / 2),
                    height - strokePad);
        } else {
            // left point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad),
                    bottomLineY);
            // right point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad + pointerSize),
                    bottomLineY);
            // bottom point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad + (pointerSize / 2)),
                    height - strokePad);
        }

        Area area = new Area(bubble);
        area.add(new Area(pointer));

        g2.setRenderingHints(hints);

        // Paint the BG color of the parent, everywhere outside the clip
        // of the text bubble.
        Component parent  = c.getParent();
        if (parent!=null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(0,0,width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.draw(area);
    }
}
