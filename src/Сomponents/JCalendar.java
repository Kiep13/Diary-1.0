package Сomponents;

import Main.Main;
import Main.Theme;
import Panels.PanelDay;
import Panels.TasksPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class JCalendar extends JPanel{

    public static boolean monthMode = false;

    public int realYear;
    public int realMonth;
    public int realDay;
    public int currentYear;
    public int currentMonth;
    private String cambiaYearMin = "Год";
    private String[] monthsMin = new String[]{"ЯНВАРЬ", "ФЕВРАЛЬ", "МАРТ", "АПРЕЛЬ", "МАЙ", "ИЮНЬ", "ИЮЛЬ", "АВГУСТ", "СЕНТЯБРЬ", "ОКТЯБРЬ", "НОЯБРЬ", "ДЕКАБРЬ"};
    private String[] monthsMay = new String[]{"ЯНВАРЬ", "ФЕВРАЛЬ", "МАРТ", "АПРЕЛЬ", "МАЙ", "ИЮНЬ", "ИЮЛЬ", "АВГУСТ", "СЕНТЯБРЬ", "ОКТЯБРЬ", "НОЯБРЬ", "ДЕКАБРЬ"};
    private String cambiaYearMay = "Изменить год";
    private Color colorDiaActual = Theme.color3;
    private Color colorTextDiaActual = Color.WHITE;
    private Color colorBackground = Theme.color1;
    private Color colorSelForeground = Theme.color1;
    private Color colorButtonHover = Theme.color2;
    private int altoHead = 50;
    private int altoFilas = 30;
    private Font fuenteHead = new Font("Tahoma", 1, 15);
    private Font fuenteFilas = new Font("Tahoma", 1, 12);
    private boolean textMayusculas = true;
    private String formatoFecha = "yyyy/MM/dd";
    private SimpleDateFormat dt;
    private String fecha;
    private JButton btn1;
    private JButton btn2;
    private JButton btnNext;
    private JButton btnPrev;
    private JLabel cambiarYear;
    private JPanel jPanel1;
    private JScrollPane jScrollPane2;
    private JLabel lblMonth;
    private JTable tblCalendar;
    private JTextField txtYear;
    JCalendar.tblCalendarRenderer t;

    public JCalendar() {
        this.dt = new SimpleDateFormat(this.formatoFecha);
        this.fecha = "";
        this.t = new JCalendar.tblCalendarRenderer();
        this.initComponents();
        this.tblCalendar.setCursor(new Cursor(12));
        GregorianCalendar cal = new GregorianCalendar();
        this.realDay = cal.get(5);
        this.realMonth = cal.get(2);
        this.realYear = cal.get(1);
        this.currentMonth = this.realMonth;
        this.currentYear = this.realYear;
        this.txtYear.setText(String.valueOf(this.realYear));
        this.refreshCalendar(this.realMonth, this.realYear);
    }

    private void initComponents() {
        this.jScrollPane2 = new JScrollPane();
        this.tblCalendar = new JTable();
        this.cambiarYear = new JLabel();
        this.btnPrev = Builder.createButton("<", "nextYear", 40, 20, 100, 320, Theme.color6);
        this.btnNext = Builder.createButton(">", "nextYear", 40, 20, 100, 320, Theme.color6);
        this.lblMonth = new JLabel();
        this.jPanel1 = new JPanel();
        this.btn2 = Builder.createButton("-", "previousYear",40, 20, 0, 320, Theme.color6);
        this.btn1 = Builder.createButton("+", "nextYear", 40, 20, 100, 320, Theme.color6);
        this.txtYear = new JTextField();
        this.setBackground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createLineBorder(Theme.color1, 3));
        this.jScrollPane2.setBorder((Border)null);
        this.tblCalendar.setModel(new DefaultTableModel(new Object[][]{{null, null, null, null, null, null, null}, {null, null, null, null, null, null, null}, {null, null, null, null, null, null, null}, {null, null, null, null, null, null, null}, {null, null, null, null, null, null, null}, {null, null, null, null, null, null, null}}, new String[]{"ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС"}) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tblCalendar.setRowHeight(30);
        this.tblCalendar.getTableHeader().setReorderingAllowed(false);
        this.tblCalendar.addMouseListener(new MouseAdapter() {//доработать эту бадягу
            public void mousePressed(MouseEvent evt) {
                JCalendar.this.tblCalendarMousePressed(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.tblCalendar);
        if (this.tblCalendar.getColumnModel().getColumnCount() > 0) {
            this.tblCalendar.getColumnModel().getColumn(0).setResizable(false);
            this.tblCalendar.getColumnModel().getColumn(1).setResizable(false);
            this.tblCalendar.getColumnModel().getColumn(2).setResizable(false);
            this.tblCalendar.getColumnModel().getColumn(3).setResizable(false);
            this.tblCalendar.getColumnModel().getColumn(4).setResizable(false);
            this.tblCalendar.getColumnModel().getColumn(5).setResizable(false);
            this.tblCalendar.getColumnModel().getColumn(6).setResizable(false);
        }

        this.cambiarYear.setFont(new Font("Tahoma", 1, 14));
        this.cambiarYear.setText("CAMBIAR AÑO");
        this.btnPrev.setFont(new Font("Roboto", 1, 18));
        this.btnPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JCalendar.this.btnPrevActionPerformed(evt);
            }
        });
        this.btnNext.setFont(new Font("Roboto", 1, 18));
        this.btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JCalendar.this.btnNextActionPerformed(evt);
            }
        });
        this.lblMonth.setFont(new Font("Tahoma", 1, 14));
        this.lblMonth.setHorizontalAlignment(0);
        this.lblMonth.setText("MES");
        this.jPanel1.setOpaque(false);
        this.btn2.setFont(new Font("Tahoma", 1, 18));
        this.btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JCalendar.this.btn2ActionPerformed(evt);
            }
        });
        this.btn1.setFont(new Font("Tahoma", 1, 18));
        this.btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JCalendar.this.btn1ActionPerformed(evt);
            }
        });
        this.txtYear.setFont(new Font("Tahoma", 1, 14));
        this.txtYear.setForeground(Theme.color1);
        this.txtYear.setHorizontalAlignment(0);
        this.txtYear.setText("0000");
        this.txtYear.setBorder(BorderFactory.createLineBorder(Theme.color1, 2));
        this.txtYear.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                JCalendar.this.txtYearKeyReleased(evt);
            }

            public void keyTyped(KeyEvent evt) {
                JCalendar.this.txtYearKeyTyped(evt);
            }
        });
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(
                this.txtYear, -1, 87, 32767).addGap(6, 6, 6).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                        this.btn1, -2, 47, -2).addComponent(this.btn2, -2, 47, -2))));

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.txtYear).addGroup(jPanel1Layout.createSequentialGroup().addComponent(
                this.btn1, -2, 0, 32767).addGap(2, 2, 2).addComponent(this.btn2, -2, 0, 32767)));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGap(0, 0, 0).addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane2, -2, 0, 32767).addGroup(layout.createSequentialGroup().addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addComponent(
                                this.btnPrev, -2, 47, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.lblMonth, -1, -1, 32767
                        ).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.btnNext, -2, 47, -2)).addGroup(layout.createSequentialGroup().addGap(
                                10, 10, 10).addComponent(this.cambiarYear, -1, 142, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(
                                        this.jPanel1, -2, -1, -2))).addContainerGap()))));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.TRAILING).addComponent(this.btnNext, -2, 28, -2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(
                        this.lblMonth, -2, 28, -2).addComponent(this.btnPrev, -2, 28, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(
                                this.jScrollPane2, -1, 232, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING, false).addComponent(this.cambiarYear, -1, 32, 32767).addComponent(
                                                this.jPanel1, -1, -1, 32767)).addContainerGap()));

    }

    private void btnPrevActionPerformed(ActionEvent evt) {
        if (this.currentMonth == 0) {
            this.currentMonth = 11;
            --this.currentYear;
        } else {
            --this.currentMonth;
        }

        this.refreshCalendar(this.currentMonth, this.currentYear);
    }

    private void btnNextActionPerformed(ActionEvent evt) {
        if (this.currentMonth == 11) {
            this.currentMonth = 0;
            ++this.currentYear;
        } else {
            ++this.currentMonth;
        }

        this.refreshCalendar(this.currentMonth, this.currentYear);
    }

    private void tblCalendarMousePressed(MouseEvent evt) {
        this.refreshCalendar(this.currentMonth, this.currentYear);
        int fila = this.tblCalendar.getSelectedRow();
        int columna = this.tblCalendar.getSelectedColumn();
        if (this.tblCalendar.getValueAt(fila, columna) != null) {
                String dia = this.tblCalendar.getValueAt(fila, columna).toString();
                String mes = "";

                for(int i = 0; i < this.monthsMay.length; ++i) {
                    if (this.monthsMay[i] == this.lblMonth.getText() || this.monthsMin[i] == this.lblMonth.getText()) {
                        mes = String.valueOf(i + 1);
                    }
                }

                this.fecha = this.txtYear.getText() + "-" + mes + "-" + dia + " 00:00:00.0";
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                if(monthMode == true) {
                    Main.window.remove(Main.panel);
                    Main.panel = PanelDay.getPanel(new GregorianCalendar(currentYear, currentMonth,
                            Integer.parseInt(dia)));
                    Builder.repaint(Main.panel);
                    TasksPanel.dialog.dispose();
                    TasksPanel.dialog = null;
                } else {
                    TasksPanel.dateField.setText(dia + "." + (currentMonth+1) + "." + currentYear);
                    TasksPanel.dialog.dispose();
                    TasksPanel.dialog = null;
                }
        }
    }

    private void btn2ActionPerformed(ActionEvent evt) {
        if (this.txtYear.getText().length() >= 4 && !this.txtYear.getText().equals("")) {
            int valor = Integer.parseInt(this.txtYear.getText());
            if (valor < 1000) {
                this.refreshCalendar(this.currentMonth, this.currentYear);
            } else if (valor == 1000) {
                this.refreshCalendar(this.currentMonth, this.currentYear);
            } else {
                --this.currentYear;
                this.refreshCalendar(this.currentMonth, this.currentYear);
            }
        } else {
            this.refreshCalendar(this.currentMonth, this.currentYear);
        }

    }

    private void btn1ActionPerformed(ActionEvent evt) {
        if (this.txtYear.getText().length() >= 4 && !this.txtYear.getText().equals("")) {
            int valor = Integer.parseInt(this.txtYear.getText());
            if (valor < 1000) {
                this.refreshCalendar(this.currentMonth, this.currentYear);
            } else {
                ++this.currentYear;
                this.refreshCalendar(this.currentMonth, this.currentYear);
            }
        } else {
            this.refreshCalendar(this.currentMonth, this.currentYear);
        }

    }

    private void txtYearKeyTyped(KeyEvent evt) {
        char car = evt.getKeyChar();
        if (car < '0' || car > '9') {
            evt.consume();
        }

        if (this.txtYear.getText().length() == 4) {
            evt.consume();
        }

    }

    private void txtYearKeyReleased(KeyEvent evt) {
        if (!this.txtYear.getText().equals("")) {
            this.currentYear = Integer.parseInt(this.txtYear.getText());
            this.refreshCalendar(this.currentMonth, this.currentYear);
        }
    }

    public void refreshCalendar(int month, int year) {
        this.jScrollPane2.getViewport().setBackground(Color.WHITE);
        this.btnPrev.setEnabled(true);
        this.btnNext.setEnabled(true);
        if (month == 0 && year <= this.realYear - 10) {
            this.btnPrev.setEnabled(false);
        }

        if (month == 11 && year >= this.realYear + 100) {
            this.btnNext.setEnabled(false);
        }

        if (this.textMayusculas) {
            this.lblMonth.setText(this.monthsMay[month]);
            this.cambiarYear.setText(this.cambiaYearMay);
        } else {
            this.lblMonth.setText(this.monthsMin[month]);
            this.cambiarYear.setText(this.cambiaYearMin);
        }

        this.txtYear.setText(String.valueOf(year));

        for(int i = 0; i < 6; ++i) {
            for(int j = 0; j < 7; ++j) {
                this.tblCalendar.setValueAt((Object)null, i, j);
            }
        }

        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        int nod = cal.getActualMaximum(5);
        int som = cal.get(7);

        int i;
        for(i = 1; i <= nod; ++i) {
            int row = som == 1 ? (i + som + 4) / 7 : (i + som + 4) / 7-1 ;
            int column = (i + som + 4) % 7;
            this.tblCalendar.setValueAt(i, row, column);
        }

        this.tblCalendar.setDefaultRenderer(Object.class, new JCalendar.tblCalendarRenderer());
    }

    public class tblCalendarRenderer extends DefaultTableCellRenderer {
        private Component componenete;

        public tblCalendarRenderer() {
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            this.componenete = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            this.componenete.setFont(JCalendar.this.fuenteFilas);
            this.setHorizontalAlignment(0);
            this.setBorder((Border)null);
            this.componenete.setForeground(Color.black);
            if (column != 0 && column != 6) {
                this.componenete.setBackground(Color.white);
            } else {
                this.componenete.setBackground(Color.white);
            }

            if (value != null && Integer.parseInt(value.toString()) == JCalendar.this.realDay && JCalendar.this.currentMonth == JCalendar.this.realMonth && JCalendar.this.currentYear == JCalendar.this.realYear) {
                this.componenete.setBackground(JCalendar.this.colorDiaActual);
                this.componenete.setForeground(JCalendar.this.colorTextDiaActual);
            }

            if (row == JCalendar.this.tblCalendar.getSelectedRow() && column == JCalendar.this.tblCalendar.getSelectedColumn() && value != null) {
                this.componenete.setBackground(JCalendar.this.colorBackground);
                this.componenete.setForeground(JCalendar.this.colorSelForeground);
            }

            return this.componenete;
        }
    }
}
