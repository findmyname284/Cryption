package kz.findmyname284.cryption;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame("Cryption Application");
        Checkbox caesar = new Checkbox("Шифрование Цезаря");
        Checkbox vigenere = new Checkbox("Шифрование Виженера");
        Label input = new Label("Ввод");
        Label keyLabel = new Label("Ключ");
        Label output = new Label("Вывод");
        Button encrytion = new Button("Шифрование");
        Button decrytion = new Button("Дешифрование");
        Button clear = new Button("Очистка");
        Button swap = new Button("Swap");
        TextField text = new TextField(20);
        TextField key = new TextField(20);
        TextField out = new TextField(20);

        caesar.setState(true);

        caesar.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                vigenere.setState(!caesar.getState());
            }
        });

        vigenere.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                caesar.setState(!vigenere.getState());
            }
        });

        caesar.setBounds(50, 50, 150, 20);
        vigenere.setBounds(250, 50, 200, 20);
        input.setBounds(50, 70, 100, 30);
        text.setBounds(50, 100, 500, 100);
        keyLabel.setBounds(50, 200, 100, 30);
        key.setBounds(50, 230, 500, 30);
        encrytion.setBounds(100, 270, 100, 30);
        decrytion.setBounds(250, 270, 100, 30);
        clear.setBounds(400, 270, 100, 30);
        output.setBounds(50, 290, 100, 30);
        out.setBounds(50, 320, 500, 100);
        swap.setBounds(250, 440, 100, 30);

        encrytion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getText().isEmpty() || key.getText().isEmpty()) {
                    out.setText("Значение постое");
                    return;
                }
                if (caesar.getState()) {
                    try {
                        String result = CaesarCipher.encrypt(text.getText(), Integer.parseInt(key.getText()));
                        out.setText(result);
                    } catch (Exception ex) {
                        out.setText("Ошибка ключ должен быть только цифра, от 0 до 41");
                    }
                } else if (vigenere.getState()) {
                    String result = VigenereCipher.encrypt(text.getText(), key.getText());
                    out.setText(result);
                } else {
                    out.setText("Error");
                }
            }
        });

        decrytion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getText().isEmpty() || key.getText().isEmpty()) {
                    out.setText("Значение постое");
                    return;
                }
                if (caesar.getState()) {
                    try {
                        String result = CaesarCipher.decrypt(text.getText(), Integer.parseInt(key.getText()));
                        out.setText(result);
                    } catch (Exception ex) {
                        out.setText("Ошибка ключ должен быть только цифра, от 0 до 41");
                    }
                } else if (vigenere.getState()) {
                    String result = VigenereCipher.decrypt(text.getText(), key.getText());
                    out.setText(result);
                } else {
                    System.out.println("error");
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");
                key.setText("");
                out.setText("");
            }
        });

        swap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = text.getText();
                text.setText(out.getText());
                out.setText(temp);
            }
        });

        frame.setLayout(null);

        frame.add(caesar);
        frame.add(vigenere);
        frame.add(input);
        frame.add(text);
        frame.add(encrytion);
        frame.add(decrytion);
        frame.add(clear);
        frame.add(keyLabel);
        frame.add(key);
        frame.add(output);
        frame.add(out);
        frame.add(swap);

        frame.setSize(600, 500);
        frame.setResizable(false);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                frame.dispose();
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}