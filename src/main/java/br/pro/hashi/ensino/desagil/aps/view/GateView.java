package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


public class GateView extends FixedPanel implements ActionListener, MouseListener {

    private final Gate gate;
    private final JCheckBox input0;
    private final JCheckBox input1;
    private final Light output;
    private final Switch zero;
    private final Switch one;
    private final Switch three;
    private final Image image;
    private Color color;


    public GateView(int width, int height, Gate gate) {

        super(width, height);

        this.gate = gate;

        zero = new Switch();
        input0 = new JCheckBox();
        input0.addActionListener(this);

        one = new Switch();
        input1 = new JCheckBox();
        input1.addActionListener(this);

        three = new Switch();
        output = new Light(255, 0, 0);

        output.connect(0, three);

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        if (gate.getInputSize() == 1) {
            gate.connect(0, zero);
            add(input0, 28, 96, 17, 13);
            updateOne();
        } else if (gate.getInputSize() == 2) {
            gate.connect(0, zero);
            gate.connect(1, one);
            add(input0, 28, 63, 17, 13);
            add(input1, 28, 129, 17, 13);
            updateTwo();
        }
    }

    private void updateOne() {
        boolean inputzero;

        inputzero = input0.isSelected();

        connect(inputzero);
    }

    private void updateTwo() {
        boolean inputzero;
        boolean inputone;

        inputzero = input0.isSelected();
        inputone = input1.isSelected();

        connect(inputzero, inputone);
    }

    public void connect(boolean inputzero) {
        if (inputzero) {
            zero.turnOn();
        } else {
            zero.turnOff();
        }

        if (gate.read()) {
            three.turnOn();
        } else {
            three.turnOff();
        }
    }

    public void connect(boolean inputzero, boolean inputone) {
        if (inputzero) {
            zero.turnOn();
        } else {
            zero.turnOff();
        }

        if (inputone) {
            one.turnOn();
        } else {
            one.turnOff();
        }

        if (gate.read()) {
            three.turnOn();
        } else {
            three.turnOff();
        }
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (gate.getInputSize() == 1) {
            updateOne();
            repaint();
        } else if (gate.getInputSize() == 2) {
            updateTwo();
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();
        double Radius = 12.5;
        double xDist = Math.pow((312.5 - x), 2);
        double yDist = Math.pow((102.5 - y), 2);
        double Dist = Math.sqrt(xDist + yDist);

        if (Dist <= Radius) {
            color = JColorChooser.showDialog(this, null, color);
            output.setColor(color);
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 45, 40, 250, 125, this);

        // Desenha um quadrado cheio.
        g.setColor(output.getColor());
        g.fillOval(300, 90, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}

