package com.corejava.local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class NumberFormatTest {
    public static void main(String[] args) {
        JFrame frame = new NumberFormatFrame();
        frame.setTitle("NumberFormatTest1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class NumberFormatFrame extends JFrame{
    private Locale[] locales;
    private double currentNumber;
    private JComboBox<String> localeCombo = new JComboBox<>();
    private JButton parseButton = new JButton("parse");
    private JTextField numberText = new JTextField(30);
    private JRadioButton numberRadioButton = new JRadioButton("Number");
    private JRadioButton currencyRadioButton = new JRadioButton("Currency");
    private JRadioButton percentRadioButton = new JRadioButton("Percent");
    private ButtonGroup rbGroup = new ButtonGroup();
    private NumberFormat currentNumberFormat;
    public NumberFormatFrame(){
        setLayout(new GridBagLayout());
        ActionListener listener = event -> updateDisplay();

        JPanel p = new JPanel();
        addRadioButton(p, numberRadioButton, rbGroup, listener);
        addRadioButton(p, currencyRadioButton, rbGroup, listener);
        addRadioButton(p, percentRadioButton, rbGroup, listener);

        add(new JLabel("Locale:" ), new GBC(0, 2).setInsets(2));
        add(p, new GBC(1,1));
        add(parseButton, new GBC(0, 2).setInsets(2));
        add(localeCombo, new GBC(1, 0).setAnchor(GBC.WEST));
        add(numberText, new GBC(1, 2).setFill(GBC.HORIZONTAL));
        locales = (Locale[]) NumberFormat.getAvailableLocales().clone();
        Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
        for(Locale loc: locales){
            localeCombo.addItem(loc.getDisplayName());
        }
        localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        currentNumber = 123456.789;
        updateDisplay();

        localeCombo.addActionListener(listener);
        parseButton.addActionListener(event ->{
            String s = numberText.getText().trim();
            try{
                Number n = currentNumberFormat.parse(s);
                if (n != null){
                    currentNumber = n.doubleValue();
                    updateDisplay();
                }else{
                    numberText.setText("Parse error: " + s);
                }
            }catch(ParseException e){
                numberText.setText("Parse error: " + s);
            }
        });
        pack();
    }

    public void addRadioButton(Container p, JRadioButton b, ButtonGroup g, ActionListener listener){
        b.setSelected(g.getButtonCount() == 0);
        b.addActionListener(listener);
        g.add(b);
        p.add(b);
    }

    public void updateDisplay(){
        Locale currentLocale = locales[localeCombo.getSelectedIndex()];
        currentNumberFormat = null;
        if(numberRadioButton.isSelected()){
            currentNumberFormat = NumberFormat.getNumberInstance(currentLocale);
        }else if(currencyRadioButton.isSelected()){
            currentNumberFormat = NumberFormat.getCurrencyInstance(currentLocale);
        }else if(percentRadioButton.isSelected()){
            currentNumberFormat = NumberFormat.getPercentInstance(currentLocale);
        }

        String formatted = currentNumberFormat.format(currentNumber);
        numberText.setText(formatted);
    }

}

class GBC extends GridBagConstraints
{
    //初始化左上角位置
    public GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }
    //初始化左上角位置和所占行数和列数
    public GBC(int gridx, int gridy, int gridwidth, int gridheight)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }
    //对齐方式
    public GBC setAnchor(int anchor)
    {
        this.anchor = anchor;
        return this;
    }
    //是否拉伸及拉伸方向
    public GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }
    //x和y方向上的增量
    public GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }
    //外部填充
    public GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }
    //外填充
    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }
    //内填充
    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}