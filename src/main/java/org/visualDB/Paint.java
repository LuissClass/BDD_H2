package org.visualDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Paint extends JFrame {
    private DatosService ds;

    Paint(DatosService ds) {
        this.ds = ds;

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        PanelDibujo lienzo = new PanelDibujo(ds);

        JButton botonAtras = new JButton("Atras");
        JButton botonAdelante = new JButton("Adelante");

        botonAtras.addActionListener(lienzo::volverAtras);
        botonAdelante.addActionListener(lienzo::volverAdelante);

        JPanel botones = new JPanel();

        botones.add(botonAtras);
        botones.add(botonAdelante);

        panel.add(lienzo, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Guardando...");
                lienzo.guardar();
                dispose();
            }
        });
    }

}
