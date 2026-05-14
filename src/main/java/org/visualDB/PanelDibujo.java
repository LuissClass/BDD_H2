package org.visualDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelDibujo extends JPanel {
    private DatosService ds;

    private boolean dibujando = false;
    private ArrayList<Point> puntos = new ArrayList<>();
    private int limitePuntos;

    public PanelDibujo(DatosService ds) {
        this.ds = ds;

        //todo exepciones
        ds.cargarPuntos();
        puntos = ds.puntos;

        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.GREEN);

        // Activar/desactivar dibujo con clic
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dibujando = !dibujando;  // alternar modo
                if (dibujando) {
                    if (limitePuntos != puntos.size()) {
                        puntos.subList(limitePuntos, puntos.size() - 1).clear();
                    }
                    Point p = e.getPoint();
                    puntos.add(p);
                } else {
                    puntos.add(null);

                }
                limitePuntos = puntos.size();
            }
        });

        // Dibujar mientras se mueve el mouse
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (dibujando) {
                    //puntos.clear();
                    Point p = e.getPoint();
                    puntos.add(p);
                    limitePuntos = puntos.size();
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la línea siguiendo los puntos
        g.setColor(Color.RED);
        for (int i = 1; i < limitePuntos; i++) {
            Point p1 = puntos.get(i - 1);
            Point p2 = puntos.get(i);

            if (p1 != null && p2 != null) {
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    public void volverAtras(ActionEvent ae) {
        if (dibujando) {
            return;
        }

        for (int i = limitePuntos - 2; i >= 0; i--) {
            if (puntos.get(i) == null) {
                limitePuntos = i;
                break;
            }

            if (i == 0) {
                limitePuntos = 0;
            }
        }

        repaint();
    }

    public void volverAdelante(ActionEvent ae) {
        if (dibujando) {
            return;
        }

        for (int i = limitePuntos + 1; i <= puntos.size() - 1; i++) {
            if (puntos.get(i) == null) {
                limitePuntos = i;
                break;
            }
        }
        repaint();
    }

    public  void guardar() {
        ds.guardarPuntos(puntos);
    }
}
