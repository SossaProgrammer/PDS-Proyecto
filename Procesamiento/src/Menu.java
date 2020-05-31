
import Additionals.Texto;
import Conection.DataBase;
import Conection.Server;
import Processing.DuchaInfo;
import Processing.Persistencia;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Menu extends JFrame implements ActionListener {

    private JComboBox stratum;
    private JPanel panel, buttonPanel;
    private JButton assignStratum, showData, loadPersistence, enterPage, Exit, saveStratum, update, outTable, averages;
    private JLabel logo;
    int n = 0;

    public Menu() {
        DatosVentana();
        Botones();
    }

    public void DatosVentana() {
        this.setTitle(Texto.WINDOW_TITLE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);       
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(52,152,219));
        this.getContentPane().add(panel);      
    }

    public void Botones() {
        buttonPanel = new JPanel();
        assignStratum = new JButton(Texto.BTN1);
        showData = new JButton(Texto.BTN2);
        loadPersistence = new JButton(Texto.BTN3);
        averages = new JButton(Texto.BTN9);
        enterPage = new JButton(Texto.BTN4);
        Exit = new JButton(Texto.BTN5);
        stratum = new JComboBox();
        saveStratum = new JButton(Texto.BTN6);
        update = new JButton(Texto.BTN7);
        outTable = new JButton(Texto.BTN8);
        logo = new JLabel("logo");
        logo.setBounds(175, 20, 150, 50);
        
        ImageIcon im1 = new ImageIcon(getClass().getResource("/Resources/logo.png"));
        ImageIcon im2 = new ImageIcon(im1.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(im2);
        panel.add(logo);
        
        
        buttonPanel.setBounds(163, 100, 174, 350);
        buttonPanel.setBackground(new Color(52,152,219));
        panel.add(buttonPanel);
        buttonPanel.setLayout(null);
        
        
        assignStratum.setBounds(0, 0, 174, 25);
        assignStratum.addActionListener(this);
        
        
        showData.setBounds(0, 50, 174, 25);
        showData.addActionListener(this);
        
        
        loadPersistence.setBounds(0, 100, 174, 25);
        loadPersistence.addActionListener(this);

        
        averages.setBounds(0, 150, 174, 25);
        averages.addActionListener(this);

        
        enterPage.setBounds(0, 200, 174, 25);
        enterPage.addActionListener(this);

        Exit.setBounds(0, 250, 174, 25);
        Exit.addActionListener(this);

        buttonPanel.add(assignStratum);
        buttonPanel.add(showData);
        buttonPanel.add(loadPersistence);
        buttonPanel.add(enterPage);
        buttonPanel.add(averages);
        buttonPanel.add(Exit);
        buttonPanel.updateUI();
        panel.updateUI();
        
        
        stratum.setBounds(350, 100, 50, 25);
        for (int i = 1; i <= 6; i++) {
            stratum.addItem(i);
        }
        panel.add(stratum);
        stratum.setVisible(false);
        
        
        saveStratum.setBounds(330, 150, 100, 25);
        saveStratum.addActionListener(this);
        saveStratum.setVisible(false);
        panel.add(saveStratum);

      
        
        update.setBounds(163, 330, 174, 25);
        update.addActionListener(this);
        update.setVisible(false);
        
        
        outTable.setBounds(163, 370, 174, 25);
        outTable.addActionListener(this);
        outTable.setVisible(false);
        
        
    }

    public static void main(String[] args) {
        Texto.language();
        Menu m1 = new Menu();
        Server.recibirParametros();
        Persistencia.WriteFile();
        DataBase.conexion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == assignStratum) {
            
            buttonPanel.setLocation(103, 100);
            stratum.setVisible(true);
            saveStratum.setVisible(true);

        } else if (e.getSource() == showData) {
            
            stratum.setVisible(false);
            saveStratum.setVisible(false);
            buttonPanel.setLocation(163, 100);

            if (DuchaInfo.Estrato <= 0 || DuchaInfo.Estrato > 6) {
                JOptionPane.showMessageDialog(this, Texto.AV1, Texto.BTN2,JOptionPane.INFORMATION_MESSAGE);
            } else {
                MostrarTabla();
                DataBase.Insert();
            }
        } else if (e.getSource() == loadPersistence) {           
            Persistencia.LoadFile();
        } else if (e.getSource() == averages) {
            Promedio();
        } else if (e.getSource() == enterPage) {
            
            //Conexión con la página
        } else if (e.getSource() == Exit) {
            System.exit(0);

        } else if (e.getSource() == saveStratum) {
            
            DuchaInfo.Estrato = (int) stratum.getSelectedItem();
            for (int i = 0; i < DuchaInfo.duchas.size(); i++) {
                DuchaInfo.duchas.get(i).CalcCost();
            }
            
            buttonPanel.setLocation(163, 100);
            stratum.setVisible(false);
            saveStratum.setVisible(false);

        } else if (e.getSource() == update) {
            DuchaInfo.duchas.clear();
            Server.recibirParametros();
            MostrarTabla();
            Persistencia.WriteFile();
            DataBase.Insert();
            
        } else if (e.getSource() == outTable) {
            panel.removeAll();
            revalidate();
            repaint();
            Botones();
        }

    }
    
    public void MostrarTabla() {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn(Texto.DATE1);
        modelo.addColumn(Texto.DATE2+"(L)");
        modelo.addColumn(Texto.DATE3+"(HH:MM:SS)");
        modelo.addColumn(Texto.DATE4);

        int h = DuchaInfo.duchas.size();

        String tabla[] = new String[4];

        for (int i = 0; i < h; i++) {

            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        tabla[j] = DuchaInfo.duchas.get(i).getFecha();
                        break;
                    case 1:
                        tabla[j] = Double.toString(DuchaInfo.duchas.get(i).getGasto()) + "L";
                        ;
                        break;
                    case 2:
                        tabla[j] = DuchaInfo.duchas.get(i).getTiempoFormat();
                        break;
                    case 3:
                        tabla[j] = Double.toString(DuchaInfo.duchas.get(i).getCosto()) + "$";
                        break;

                }

            }
            modelo.addRow(tabla);
        }

        JTable t = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(t);
        scroll.setBackground(new Color(52,152,219));
        scroll.setBounds(10, 20, 480, 300);
        panel.add(scroll);
        Botones2();
        

    }

    private void Promedio() {
        int lenght = DuchaInfo.duchas.size();
        DecimalFormat format1 = new DecimalFormat("#.00");
        if (lenght > 0) {
            double average[] = new double[3];
            for (int i = 0; i < lenght; i++) {
                average[0] += DuchaInfo.duchas.get(i).getGasto();
                average[1] += DuchaInfo.duchas.get(i).getTiempo();
                average[2] += DuchaInfo.duchas.get(i).getCosto();
            }
            average[0] /= lenght;
            average[1] /= lenght;
            average[2] /= lenght;
            
            int tiempo = (int)average[1];
            int ss = tiempo % 60;
            int mm = (tiempo/60)%60;
            
            JOptionPane.showMessageDialog(this, Texto.DATE2+"(L): " + format1.format(average[0]) +
                    "\n"+ Texto.DATE3+"(MM:SS): " + String.format("%02d:%02d", mm,ss) +
                    "\n"+ Texto.DATE4+": " + format1.format(average[2]),Texto.BTN9,JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, Texto.AV3,Texto.BTN9,JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void Botones2(){   
        buttonPanel.setVisible(false);
        logo.setVisible(false);
        update.setVisible(true);
        outTable.setVisible(true);
        panel.add(update);    
        panel.add(outTable);    
    }

}
