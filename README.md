# Formë Regjistrimi — Kurs Programimi

A Java Swing registration form built as a university exercise, demonstrating the use of `GridBagLayout` and core Swing components.

---

## Screenshot

> Run the program to see the dark-themed registration form with live summary output.

---

## Features

- **GridBagLayout** as the primary layout manager (FlowLayout excluded per requirements)
- Input validation — alerts the user if required fields are empty
- Live summary display in a read-only `JTextArea`
- Focus effect on text fields (blue border on focus)
- Clean dark theme with custom-painted buttons

---

## Components Used

| Component | Purpose |
|---|---|
| `JLabel` | Field labels |
| `JTextField` | Name and surname input |
| `JRadioButton` + `ButtonGroup` | Gender selection (Male / Female) |
| `JComboBox` | City selection |
| `JCheckBox` | Programming language selection |
| `JButton` | Shfaq (Show) and Pastro (Clear) actions |
| `JTextArea` + `JScrollPane` | Summary output area |

---

## Project Structure

```
FormaRegjistrimit.java   # Single-file application — all logic and UI in one class
README.md
```

---

## Requirements

- Java 8 or higher
- No external dependencies — uses only the standard `javax.swing` and `java.awt` libraries

---

## How to Run

**1. Compile**
```bash
javac FormaRegjistrimit.java
```

**2. Run**
```bash
java FormaRegjistrimit
```

---

## How It Works

1. Fill in your **first name** and **last name**
2. Select your **gender** using the radio buttons
3. Choose your **city** from the dropdown
4. Check any **programming languages** you know
5. Click **Shfaq** to display a formatted summary in the text area
6. Click **Pastro** to reset all fields back to their default state

If the name or surname field is left empty when clicking Shfaq, a warning dialog appears asking the user to complete the required fields.

---

## Layout Notes

The form uses `GridBagLayout` on the main panel with a two-column structure:

- **Column 0** — labels (`JLabel`), fixed width
- **Column 1** — input components, expands horizontally (`weightx = 1`)
- The `JTextArea` spans both columns (`gridwidth = 2`) and expands vertically (`weighty = 1`)

Inner panels for the radio buttons and checkboxes use `FlowLayout` and `GridLayout` respectively, scoped only to their own sub-panels.

---

## Exercise Context

This project was created as part of a Java GUI programming course. The exercise required building a form with real functionality using layout managers taught in class, excluding `FlowLayout` as the top-level layout.