package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {

    private final NandGate nandEsq;
    private final NandGate nandDir;


    public AndGate() {
        super("AND", 2);
        //Criando os objetos
        nandEsq = new NandGate();
        nandDir = new NandGate();

        //Conectando
        nandDir.connect(0, nandEsq);
        nandDir.connect(1, nandEsq);
    }

    @Override
    public boolean read() {
        return nandDir.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nandEsq.connect(inputIndex, emitter);
    }
}
