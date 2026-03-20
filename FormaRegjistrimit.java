import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FormaRegjistrimit extends JFrame {

    private JTextField txtEmri;
    private JTextField txtMbiemri;

    private JRadioButton rbMashkull;
    private JRadioButton rbFemer;
    private ButtonGroup bgGjinia;

    private JComboBox<String> cmbQyteti;

    private JCheckBox chkJava;
    private JCheckBox chkPython;
    private JCheckBox chkCpp;
    private JCheckBox chkJavaScript;
    private JCheckBox chkKotlin;

    private JTextArea txtPembledhja;

    private static final Color BG_DARK      = new Color(15,  20,  40);
    private static final Color BG_PANEL     = new Color(22,  30,  55);
    private static final Color BG_INPUT     = new Color(30,  40,  70);
    private static final Color ACCENT_BLUE  = new Color(64, 156, 255);
    private static final Color ACCENT_CYAN  = new Color(0,  220, 195);
    private static final Color TEXT_WHITE   = new Color(230, 235, 255);
    private static final Color TEXT_MUTED   = new Color(130, 145, 185);
    private static final Color BORDER_COLOR = new Color(50,  65, 100);

    private static final Font FONT_TITLE  = new Font("Segoe UI", Font.BOLD,  22);
    private static final Font FONT_LABEL  = new Font("Segoe UI", Font.BOLD,  13);
    private static final Font FONT_INPUT  = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD,  13);
    private static final Font FONT_AREA   = new Font("Consolas",  Font.PLAIN, 13);

    public FormaRegjistrimit() {
        super("Regjistrim Kursi Programimi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(780, 680));

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(BG_DARK);
        root.setBorder(new EmptyBorder(24, 28, 24, 28));
        setContentPane(root);

        root.add(buildHeader(),    BorderLayout.NORTH);
        root.add(buildBody(),      BorderLayout.CENTER);
        root.add(buildButtonBar(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel buildHeader() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel icon  = new JLabel("{ }");
        icon.setFont(new Font("Consolas", Font.BOLD, 32));
        icon.setForeground(ACCENT_CYAN);
        icon.setBorder(new EmptyBorder(0, 0, 0, 14));

        JLabel title = new JLabel("Formë Regjistrimi");
        title.setFont(FONT_TITLE);
        title.setForeground(TEXT_WHITE);

        JLabel sub = new JLabel("Kurs i Programimit — Plotëso të dhënat dhe kliko Shfaq");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(TEXT_MUTED);

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(title);
        text.add(Box.createVerticalStrut(4));
        text.add(sub);

        p.add(icon, BorderLayout.WEST);
        p.add(text, BorderLayout.CENTER);

        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER_COLOR);
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setOpaque(false);
        wrap.add(p,   BorderLayout.CENTER);
        wrap.add(sep, BorderLayout.SOUTH);
        return wrap;
    }

    private JPanel buildBody() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(20, 0, 0, 0));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill   = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0; g.weightx = 0;
        p.add(label("Emri:"), g);
        g.gridx = 1; g.weightx = 1;
        txtEmri = inputField("p.sh. Andi");
        p.add(txtEmri, g);

        g.gridx = 0; g.gridy = 1; g.weightx = 0;
        p.add(label("Mbiemri:"), g);
        g.gridx = 1; g.weightx = 1;
        txtMbiemri = inputField("p.sh. Hoxha");
        p.add(txtMbiemri, g);

        g.gridx = 0; g.gridy = 2; g.weightx = 0;
        p.add(label("Gjinia:"), g);
        g.gridx = 1; g.weightx = 1;
        p.add(buildGjinia(), g);


        g.gridx = 0; g.gridy = 3; g.weightx = 0;
        p.add(label("Qyteti:"), g);
        g.gridx = 1; g.weightx = 1;
        p.add(buildCombo(), g);

        g.gridx = 0; g.gridy = 4; g.weightx = 0;
        g.anchor = GridBagConstraints.NORTHWEST;
        p.add(label("Gjuhë:"), g);
        g.gridx = 1; g.weightx = 1;
        g.anchor = GridBagConstraints.WEST;
        p.add(buildGjuhet(), g);


        g.gridx  = 0; g.gridy = 5;
        g.gridwidth = 2; g.weightx = 1; g.weighty = 1;
        g.fill  = GridBagConstraints.BOTH;
        g.insets = new Insets(16, 6, 6, 6);
        p.add(buildOutputArea(), g);

        return p;
    }


    private JPanel buildButtonBar() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        p.setOpaque(false);

        JButton btnPastro = buildButton("⟳  Pastro", BG_INPUT, TEXT_MUTED, BORDER_COLOR);
        JButton btnShfaq  = buildButton("▶  Shfaq",  ACCENT_BLUE, Color.WHITE, ACCENT_BLUE);

        btnShfaq.addActionListener(e -> shfaqTeDhenat());
        btnPastro.addActionListener(e -> pastroFormat());

        p.add(btnPastro);
        p.add(btnShfaq);
        return p;
    }


    private JPanel buildGjinia() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        p.setOpaque(false);

        rbMashkull = radioBtn("Mashkull");
        rbFemer    = radioBtn("Femër");
        bgGjinia   = new ButtonGroup();
        bgGjinia.add(rbMashkull);
        bgGjinia.add(rbFemer);

        p.add(rbMashkull);
        p.add(rbFemer);
        return p;
    }


    private JComboBox<String> buildCombo() {
        String[] qytetet = {"-- Zgjedh qytetin --", "Tiranë", "Durrës", "Vlorë", "Shkodër"};
        cmbQyteti = new JComboBox<>(qytetet);
        styleCombo(cmbQyteti);
        return cmbQyteti;
    }

    private JPanel buildGjuhet() {
        JPanel p = new JPanel(new GridLayout(2, 3, 10, 4));
        p.setOpaque(false);

        chkJava       = checkBox("Java");
        chkPython     = checkBox("Python");
        chkCpp        = checkBox("C++");
        chkJavaScript = checkBox("JavaScript");
        chkKotlin     = checkBox("Kotlin");
        JPanel empty  = new JPanel(); empty.setOpaque(false);

        p.add(chkJava);
        p.add(chkPython);
        p.add(chkCpp);
        p.add(chkJavaScript);
        p.add(chkKotlin);
        p.add(empty);
        return p;
    }

    private JScrollPane buildOutputArea() {
        txtPembledhja = new JTextArea(8, 40);
        txtPembledhja.setFont(FONT_AREA);
        txtPembledhja.setEditable(false);
        txtPembledhja.setLineWrap(true);
        txtPembledhja.setWrapStyleWord(true);
        txtPembledhja.setBackground(BG_INPUT);
        txtPembledhja.setForeground(ACCENT_CYAN);
        txtPembledhja.setCaretColor(ACCENT_CYAN);
        txtPembledhja.setBorder(new EmptyBorder(10, 12, 10, 12));
        txtPembledhja.setText("// Përmbledhja do të shfaqet këtu...");

        JScrollPane scroll = new JScrollPane(txtPembledhja);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        scroll.getViewport().setBackground(BG_INPUT);

        JPanel wrap = new JPanel(new BorderLayout(0, 6));
        wrap.setOpaque(false);
        JLabel lbl = label("Përmbledhja e të dhënave:");
        wrap.add(lbl, BorderLayout.NORTH);
        wrap.add(scroll, BorderLayout.CENTER);


        JPanel outer = new JPanel(new BorderLayout(0, 6));
        outer.setOpaque(false);
        outer.add(lbl,    BorderLayout.NORTH);
        outer.add(scroll, BorderLayout.CENTER);

        return scroll;
    }



    private void shfaqTeDhenat() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════╗\n");
        sb.append("║     PËRMBLEDHJA E REGJISTRIMIT       ║\n");
        sb.append("╚══════════════════════════════════════╝\n\n");

        String emri    = txtEmri.getText().trim();
        String mbiemri = txtMbiemri.getText().trim();

        if (emri.isEmpty() || mbiemri.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ju lutem plotësoni Emrin dhe Mbiemrin!",
                    "Gabim", JOptionPane.WARNING_MESSAGE);
            return;
        }

        sb.append("  Emri     : ").append(emri).append("\n");
        sb.append("  Mbiemri  : ").append(mbiemri).append("\n");

        String gjinia = rbMashkull.isSelected() ? "Mashkull"
                : rbFemer.isSelected()    ? "Femër"
                : "(nuk u zgjodh)";
        sb.append("  Gjinia   : ").append(gjinia).append("\n");

        String qyteti = cmbQyteti.getSelectedIndex() == 0
                ? "(nuk u zgjodh)"
                : (String) cmbQyteti.getSelectedItem();
        sb.append("  Qyteti   : ").append(qyteti).append("\n");

        List<String> gjuhet = new ArrayList<>();
        if (chkJava.isSelected())       gjuhet.add("Java");
        if (chkPython.isSelected())     gjuhet.add("Python");
        if (chkCpp.isSelected())        gjuhet.add("C++");
        if (chkJavaScript.isSelected()) gjuhet.add("JavaScript");
        if (chkKotlin.isSelected())     gjuhet.add("Kotlin");

        sb.append("  Gjuhët   : ")
                .append(gjuhet.isEmpty() ? "(asnjë e zgjedhur)" : String.join(", ", gjuhet))
                .append("\n");

        sb.append("\n──────────────────────────────────────\n");
        sb.append("  ✓ Regjistrimi u plotësua me sukses!\n");

        txtPembledhja.setText(sb.toString());
    }

    private void pastroFormat() {
        txtEmri.setText("");
        txtMbiemri.setText("");
        bgGjinia.clearSelection();
        cmbQyteti.setSelectedIndex(0);
        chkJava.setSelected(false);
        chkPython.setSelected(false);
        chkCpp.setSelected(false);
        chkJavaScript.setSelected(false);
        chkKotlin.setSelected(false);
        txtPembledhja.setText("// Përmbledhja do të shfaqet këtu...");
    }

    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(FONT_LABEL);
        l.setForeground(TEXT_MUTED);
        return l;
    }

    private JTextField inputField(String placeholder) {
        JTextField tf = new JTextField(20) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty()) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(TEXT_MUTED);
                    g2.setFont(FONT_INPUT.deriveFont(Font.ITALIC));
                    g2.drawString(placeholder, 10, getHeight() / 2 + 5);
                }
            }
        };
        tf.setFont(FONT_INPUT);
        tf.setForeground(TEXT_WHITE);
        tf.setBackground(BG_INPUT);
        tf.setCaretColor(ACCENT_BLUE);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                new EmptyBorder(6, 10, 6, 10)
        ));

        tf.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(ACCENT_BLUE, 2),
                        new EmptyBorder(5, 9, 5, 9)));
            }
            public void focusLost(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR, 1),
                        new EmptyBorder(6, 10, 6, 10)));
            }
        });
        return tf;
    }

    private JRadioButton radioBtn(String text) {
        JRadioButton rb = new JRadioButton(text);
        rb.setFont(FONT_INPUT);
        rb.setForeground(TEXT_WHITE);
        rb.setBackground(BG_DARK);
        rb.setFocusPainted(false);
        return rb;
    }

    private JCheckBox checkBox(String text) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(FONT_INPUT);
        cb.setForeground(TEXT_WHITE);
        cb.setBackground(BG_DARK);
        cb.setFocusPainted(false);
        return cb;
    }

    private void styleCombo(JComboBox<String> cb) {
        cb.setFont(FONT_INPUT);
        cb.setForeground(TEXT_WHITE);
        cb.setBackground(BG_INPUT);
        cb.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        ((JLabel) cb.getRenderer()).setBorder(new EmptyBorder(4, 8, 4, 8));
    }

    private JButton buildButton(String text, Color bg, Color fg, Color border) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? bg.darker() : bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                super.paintComponent(g);
            }
            @Override protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(border);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 8, 8);
            }
        };
        btn.setFont(FONT_BUTTON);
        btn.setForeground(fg);
        btn.setBackground(bg);
        btn.setBorderPainted(true);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(130, 40));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(FormaRegjistrimit::new);
    }
}