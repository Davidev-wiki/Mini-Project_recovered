package com.kosmo.kck.util.cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CalculatorGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final String ICON_PATH = "C:\\00.KOSMO93\\10.JExam\\el_java_work\\javaKosmo\\src\\img_calcu\\calc_icon.png";

	private static final String[] STRING_ARRAY_CONTROL = {"\u2190", "CE", "C"};
    private static final String[] STRING_ARRAY_DIGIT = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] STRING_ARRAY_OPERATORS = {"\u002B", "\u2212", "\u00D7", "\u00F7"}; //+, -, *, /
    private static final String STRING_DOT = ".";
    private static final String STRING_SIGN = "\u00B1";
    private static final String STRING_SQRT = "\u221A";
    private static final String STRING_PERCENTAGE = "\u0025";
    private static final String STRING_DIVISOR = "1/x";
    private static final String STRING_RESULT = "\u003D";
    private static final String LABEL_MAIN_INIT_VALUE = "0";
    private static final String LABEL_FORMULA_INIT_VALUE = " ";
    private static final String OPMODE_ADDITION = "addition";
    private static final String OPMODE_SUBTRACTION = "subtraction";
    private static final String OPMODE_MULTIPLICATION = "multiplication";
    private static final String OPMODE_DIVISION = "division";
    private static final String OPMODE_RESULT = "result";
    private static final String OPMODE_NONE = "none";
    private static final Map<String, String> OPMODE_MAP;
    private static final int MAXIMUM_DIGIT = 12;
    private static final int MINIMUM_FRACTIONAL_DIGIT = 6;

    static {
        OPMODE_MAP = new HashMap<String, String>();
        OPMODE_MAP.put(OPMODE_ADDITION, STRING_ARRAY_OPERATORS[0]);
        OPMODE_MAP.put(OPMODE_SUBTRACTION, STRING_ARRAY_OPERATORS[1]);
        OPMODE_MAP.put(OPMODE_MULTIPLICATION, STRING_ARRAY_OPERATORS[2]);
        OPMODE_MAP.put(OPMODE_DIVISION, STRING_ARRAY_OPERATORS[3]);
    }

    private Calculator calculator;

    private JPanel panelMain;
    private JPanel panelNorth;
    private JPanel panelCenter;

    private JLabel labelMain;
    private JLabel labelFormula;

    private JButton[] buttonsControl;
    private JButton[] buttonsDigit;
    private JButton[] buttonsArithOp;
    private JButton buttonDot;
    private JButton buttonSign;
    private JButton buttonSqrt;
    private JButton buttonPercentage;
    private JButton buttonDivisor;
    private JButton buttonResult;
    private ImageIcon calcIcon;

    private String previousOperation;

    public CalculatorGUI() {
        super();
        calculator = new Calculator();
        previousOperation = OPMODE_NONE;
        initGUI();
    }

    public void initGUI() {
        panelMain = drawPanelMain();
        this.getContentPane().add(panelMain);
        this.requestFocus();
        this.addKeyListener(new CalcKeyAdapter());       
        this.setTitle("Calculator");
        calcIcon = new ImageIcon(ICON_PATH);
        this.setIconImage(calcIcon.getImage());
        this.setBounds(100, 100, 240, 290);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public JPanel drawPanelMain() {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        panelMain.setBackground(new Color(0xD9E4F1));
        panelNorth = drawPanelNorth();
        panelMain.add(panelNorth, BorderLayout.NORTH);
        panelCenter = drawPanelCenter();
        panelMain.add(panelCenter, BorderLayout.CENTER);
        return panelMain;
    }

    public JPanel drawPanelNorth() {
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new GridLayout(1, 1));
        EmptyBorder margin = new EmptyBorder(10, 8, 2, 8);
        EtchedBorder etchedBorder = new EtchedBorder();
        panelNorth.setBorder(new CompoundBorder(margin, etchedBorder));
        JPanel panelCalc = new CalcPanel();
        panelCalc.setLayout(new GridLayout(2, 1, 0, 0));
        labelFormula = new JLabel(LABEL_FORMULA_INIT_VALUE);
        labelFormula.setBorder(new EmptyBorder(0, 5, 0, 5));
        labelFormula.setFont(new Font("Consolas", Font.PLAIN, 12));
        labelFormula.setHorizontalAlignment(SwingConstants.RIGHT);
        labelMain = new JLabel(LABEL_MAIN_INIT_VALUE);
        labelMain.setBorder(new EmptyBorder(0, 5, 0, 5));
        labelMain.setFont(new Font("Consolas", Font.PLAIN, 24));
        labelMain.setHorizontalAlignment(SwingConstants.RIGHT);
        panelCalc.add(labelFormula);
        panelCalc.add(labelMain);
        panelNorth.add(panelCalc);
        return panelNorth;
    }

    public JPanel drawPanelCenter() {
        JPanel panelCenter = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        panelCenter.setLayout(gbl);
        Dimension d = new Dimension(200, 165);
        panelCenter.setPreferredSize(d);
        GridBagConstraints gbc;

        Dimension buttonDimension = new Dimension(34, 27);
        Insets buttonInsets = new Insets(0, 0, 0, 0);
        Font buttonFont = new Font("Arial", Font.PLAIN, 13);
        Font digitButtonFont = new Font("Arial", Font.PLAIN, 15);
        buttonsControl = new JButton[STRING_ARRAY_CONTROL.length];
        buttonsDigit = new JButton[STRING_ARRAY_DIGIT.length];
        buttonsArithOp = new JButton[STRING_ARRAY_OPERATORS.length];
        for (int i = 0; i < buttonsControl.length; i++)
            buttonsControl[i] = initButton(STRING_ARRAY_CONTROL[i], buttonDimension, buttonInsets, buttonFont);
        for (int i = 0; i < buttonsDigit.length; i++)
            buttonsDigit[i] = initBrightButton(STRING_ARRAY_DIGIT[i], buttonDimension, buttonInsets, digitButtonFont);
        for (int i = 0; i < buttonsArithOp.length; i++)
            buttonsArithOp[i] = initButton(STRING_ARRAY_OPERATORS[i], buttonDimension, buttonInsets, buttonFont);
        buttonDot = initBrightButton(STRING_DOT, buttonDimension, buttonInsets, buttonFont);
        buttonSign = initButton(STRING_SIGN, buttonDimension, buttonInsets, buttonFont);
        buttonSqrt = initButton(STRING_SQRT, buttonDimension, buttonInsets, buttonFont);
        buttonPercentage = initButton(STRING_PERCENTAGE, buttonDimension, buttonInsets, buttonFont);
        buttonDivisor = initButton(STRING_DIVISOR, buttonDimension, buttonInsets, buttonFont);
        buttonResult = initButton(STRING_RESULT, buttonDimension, buttonInsets, buttonFont);

        Insets calcInsets = new Insets(3, 3, 3, 3);
        for (int i = 0; i < buttonsControl.length; i++) {
            gbc = new GridBagConstraints();
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = calcInsets;
            panelCenter.add(buttonsControl[i], gbc);
        }

        for (int i = 0; i < buttonsDigit.length; i++) {
            gbc = new GridBagConstraints();
            if (i == 0) {
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 2;
            } else {
                gbc.gridx = (i - 1) % 3;
                gbc.gridy = 3 - ((i - 1) / 3);
                gbc.gridwidth = 1;
            }
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = calcInsets;
            panelCenter.add(buttonsDigit[i], gbc);
        }

        for (int i = 0; i < buttonsArithOp.length; i++) {
            gbc = new GridBagConstraints();
            gbc.gridx = 3;
            gbc.gridy = 4 - i;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = calcInsets;
            panelCenter.add(buttonsArithOp[i], gbc);
        }

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = calcInsets;
        panelCenter.add(buttonDot, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = calcInsets;
        panelCenter.add(buttonSign, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = calcInsets;
        panelCenter.add(buttonSqrt, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = calcInsets;
        panelCenter.add(buttonPercentage, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = calcInsets;
        panelCenter.add(buttonDivisor, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = calcInsets;
        panelCenter.add(buttonResult, gbc);

        return panelCenter;
    }

    public JButton initButton(String name, Dimension buttonDimension, Insets buttonInsets, Font buttonFont) {
        JButton button = new JButton(name);
        button.setBackground(new Color(0xD4E0ED));
        button.setForeground(new Color(0x1F3993));
        button.setPreferredSize(buttonDimension);
        button.setMargin(buttonInsets);
        button.setFont(buttonFont);
        button.setFocusable(false);
        button.setRolloverEnabled(false);
        button.setName("dark");
        button.addActionListener(this);
        button.addMouseListener(new CalcMouseAdapter());
        return button;
    }

    public JButton initBrightButton(String name, Dimension buttonDimension, Insets buttonInsets, Font buttonFont) {
        JButton button = new JButton(name);
        button.setBackground(new Color(0xF3F8FC));
        button.setForeground(new Color(0x1F3993));
        button.setPreferredSize(buttonDimension);
        button.setMargin(buttonInsets);
        button.setFont(buttonFont);
        button.setFocusable(false);
        button.setRolloverEnabled(false);
        button.setName("bright");
        button.addActionListener(this);
        button.addMouseListener(new CalcMouseAdapter());
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "\u2190" : processBackspace();
                            break;
            case "CE"     : processClearCurrentOperand();
                            break;
            case "C"      : processClear();
                            break;
            case "0"      : 
            case "1"      :
            case "2"      : 
            case "3"      :
            case "4"      : 
            case "5"      :
            case "6"      : 
            case "7"      :
            case "8"      : 
            case "9"      : processDigit(Integer.parseInt(actionCommand));
                            break;
            case "\u002B" : processArithmeticOperation(OPMODE_ADDITION);
                            break;
            case "\u2212" : processArithmeticOperation(OPMODE_SUBTRACTION);
                            break;
            case "\u00D7" : processArithmeticOperation(OPMODE_MULTIPLICATION);
                            break;
            case "\u00F7" : processArithmeticOperation(OPMODE_DIVISION);
                            break;
            case "."      : processDot();
                            break;
            case "\u00B1" : processSign();
                            break;
            case "\u221A" : processSqrt();
                            break;
            case "\u0025" : processPercentage();
                            break;
            case "1/x"    : processDivisor();
                            break;
            case "\u003D" : processArithmeticOperation(OPMODE_RESULT);
                            break;
            default       : break;
        }
    }

    private class CalcKeyAdapter extends KeyAdapter {
        public void keyTyped(KeyEvent e) {
            char keyChar = e.getKeyChar();
            switch (keyChar) {
                case 0x8  : processBackspace();
                            break;
                case 0x1B : processClear();
                            break;
                case 0x30 :
                case 0x31 :
                case 0x32 :
                case 0x33 :
                case 0x34 :
                case 0x35 :
                case 0x36 :
                case 0x37 :
                case 0x38 :
                case 0x39 : processDigit(Character.getNumericValue(keyChar));
                            break;
                case 0x2E : processDot();
                            break;
                case 0x2B : processArithmeticOperation(OPMODE_ADDITION);
                            break;
                case 0x2D : processArithmeticOperation(OPMODE_SUBTRACTION);
                            break;
                case 0x2A : processArithmeticOperation(OPMODE_MULTIPLICATION);
                            break;
                case 0x2F : processArithmeticOperation(OPMODE_DIVISION);
                            break;
                case 0x0A : processArithmeticOperation(OPMODE_RESULT);
                            break;
                default   : break;
            }
        }
    }

    private class CalcMouseAdapter extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            Object obj = e.getSource();
            if (obj instanceof JButton) {
                JButton button = (JButton) obj;
                button.setBackground(new Color(0xFFD574));
            }
        }

        public void mouseExited(MouseEvent e) {
            Object obj = e.getSource();
            if (obj instanceof JButton) {
                JButton button = (JButton) obj;
                if ("dark".equals(button.getName())) {
                    button.setBackground(new Color(0xD4E0ED));
                } else if ("bright".equals(button.getName())) {
                    button.setBackground(new Color(0xF3F8FC));
                }
            }
        }
    }

    private class CalcPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(0xE5EEFB);
            Color color2 = Color.WHITE;
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h / 2, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    public void processBackspace() {
        String number = labelMain.getText();
        if (LABEL_MAIN_INIT_VALUE.equals(number)) {
            ;
        } else if (number.length() == 1) {
            labelMain.setText(LABEL_MAIN_INIT_VALUE);
        } else {
            labelMain.setText(number.substring(0, number.length() - 1));
        }
    }

    public void processClearCurrentOperand() {
        labelMain.setText(LABEL_MAIN_INIT_VALUE);
    }

    public void processClear() {
        calculator.clear();
        labelMain.setText(LABEL_MAIN_INIT_VALUE);
        labelFormula.setText(LABEL_FORMULA_INIT_VALUE);
        previousOperation = OPMODE_NONE;
    }

    public void processDigit(int digit) {
        String number = labelMain.getText();
        if (number.length() < MAXIMUM_DIGIT) {
            if (LABEL_MAIN_INIT_VALUE.equals(number)) {
                labelMain.setText(String.valueOf(digit));
            } else {
                labelMain.setText(number.concat(String.valueOf(digit)));
            }
        }
    }

    /* latent calculation
     * first time input : simply register acc and operator
     * and then...
     * register number and calculate, and then register operator.
     */
    public void processArithmeticOperation(String opmode) {
        String number = labelMain.getText();
        String formula = labelFormula.getText();
        double numberDouble = Double.parseDouble(number);
        if (OPMODE_NONE.equals(previousOperation) && OPMODE_RESULT.equals(opmode)) {
            ;
        } else if (!OPMODE_NONE.equals(previousOperation) && OPMODE_RESULT.equals(opmode)) {
            switch (previousOperation) {
                case OPMODE_ADDITION       : calculator.add(number);
                                             break;
                case OPMODE_SUBTRACTION    : calculator.subtract(number);
                                             break;
                case OPMODE_MULTIPLICATION : calculator.multiply(number);
                                             break;
                case OPMODE_DIVISION       : if (numberDouble == 0.0d) {
                                                JOptionPane.showMessageDialog(this, "Can't divide by zero");
                                                return;
                                             } else {
                                                calculator.divide(number);
                                             }
                                             break;
                default                    : break;
            }
            BigDecimal accumulator = calculator.getAccumulator();
            String accumulatorStr = formatPossibleLongNumber(accumulator);
            labelMain.setText(accumulatorStr);
            calculator.clear();
            labelFormula.setText(LABEL_FORMULA_INIT_VALUE);
            previousOperation = OPMODE_NONE;
        } else if (OPMODE_NONE.equals(previousOperation)) {
            calculator.add(number);
            labelMain.setText(LABEL_MAIN_INIT_VALUE);
            labelFormula.setText(number + " " + OPMODE_MAP.get(opmode) + " ");
            previousOperation = opmode;
        } else {
            switch (previousOperation) {
                case OPMODE_ADDITION       : calculator.add(number);
                                             break;
                case OPMODE_SUBTRACTION    : calculator.subtract(number);
                                             break;
                case OPMODE_MULTIPLICATION : calculator.multiply(number);
                                             break;
                case OPMODE_DIVISION       : if (numberDouble == 0.0d) {
                                                JOptionPane.showMessageDialog(this, "Can't divide by zero");
                                                return;
                                             } else {
                                                calculator.divide(number);
                                             }
                                             break;
                default                    : break;
            }
            labelMain.setText(LABEL_MAIN_INIT_VALUE);
            labelFormula.setText(formula + number + " " + OPMODE_MAP.get(opmode) + " ");
            previousOperation = opmode;
        }
    }

    public void processDot() {
        String number = labelMain.getText();
        if (!number.contains("."))
            labelMain.setText(number.concat("."));
    }

    public void processSign() {
        String number = labelMain.getText();
        if (number.charAt(0) == '-')
            labelMain.setText(number.substring(1));
        else
            labelMain.setText("-".concat(number));
            
    }

    public String formatPossibleLongNumber(BigDecimal number) {
        String numberStr = number.toPlainString();
        if (numberStr.length() > MAXIMUM_DIGIT) {
            NumberFormat formatter = new DecimalFormat("0.0E0");
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            formatter.setMinimumFractionDigits(MINIMUM_FRACTIONAL_DIGIT);
            numberStr = formatter.format(number);
        }
        return numberStr;
    }

    public void processSqrt() {
        String number = labelMain.getText();
        if (number.charAt(0) != '-') {
            BigDecimal sqrt = calculator.sqrt(number);
            String sqrtStr = formatPossibleLongNumber(sqrt);
            labelMain.setText(sqrtStr);
        } else {
            JOptionPane.showMessageDialog(this, "SQRT not allowed on negative number.");
        }
    }

    public void processPercentage() {
        String number = labelMain.getText();
        BigDecimal percentage = calculator.percentage(number);
        String percentageStr = formatPossibleLongNumber(percentage);
        labelMain.setText(percentageStr);
    }

    public void processDivisor() {
        String number = labelMain.getText();
        double numberDouble = Double.parseDouble(number);
        if (numberDouble == 0.0d) {
            JOptionPane.showMessageDialog(this, "Can't divide by zero.");
        } else {
            BigDecimal divisor = calculator.divisor(number);
            String divisorStr = formatPossibleLongNumber(divisor);
            labelMain.setText(divisorStr);
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}