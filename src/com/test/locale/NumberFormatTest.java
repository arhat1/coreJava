package com.test.locale;

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
        EventQueue.invokeLater(()->{
            JFrame frame = new NumberForamtFrame();
            frame.setTitle("数字格式化测试");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class NumberForamtFrame extends JFrame{
    private Locale[] locales;
    private double currentNumber;
    private JComboBox<String> localeCombo = new JComboBox<>();
    private JButton parseButton = new JButton("Parse");
    private JTextField numberText = new JTextField("Number");
    private JRadioButton numberRadioButton = new JRadioButton("Number");
    private JRadioButton currencyRadioButton = new JRadioButton("Currency");
    private JRadioButton percentRadioButton = new JRadioButton("Percent");
    private ButtonGroup rbGroup = new ButtonGroup();
    private NumberFormat currentNumberFormat;

    public NumberForamtFrame(){
        setLayout(new GridBagLayout());
        ActionListener listener = event->updateDisplay();
        JPanel p = new JPanel();
        addRadioButton(p, numberRadioButton, rbGroup, listener);
        addRadioButton(p, currencyRadioButton, rbGroup, listener);
        addRadioButton(p, percentRadioButton, rbGroup, listener);

        add(new JLabel("Locale: " ), new GBC(0,2).setInsets(2));
        add(p, new GBC(1,1));
        add(parseButton, new GBC(0, 2).setInsets(2));
        add(localeCombo, new GBC(1, 0).setAnchor(GBC.WEST));
        add(numberText, new GBC(1, 2).setFill(GBC.HORIZONTAL));

        locales = (Locale[]) NumberFormat.getAvailableLocales().clone();
        Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
        for (Locale loc: locales){
            localeCombo.addItem(loc.getDisplayName());
        }
        localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        currentNumber = 123456.78;
        updateDisplay();

        parseButton.addActionListener(event -> {
            String s = numberText.getText().trim();
            try{
                Number n = currentNumberFormat.parse(s);
                if(n != null){
                    currentNumber = n.doubleValue();
                    updateDisplay();
                }else{
                    numberText.setText("Parse error: " +s);
                }
            }catch (ParseException e){
                numberText.setText("Parse error: " + s);
            }
        });
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
        if (numberRadioButton.isSelected()){
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
