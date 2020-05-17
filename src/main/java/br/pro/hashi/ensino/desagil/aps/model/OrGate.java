package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {

    private final NandGate nandCima;
    private final NandGate nandBaixo;
    private final NandGate nandDir;


    public OrGate() {
        super("OR", 2);
        //Criando os objetos
        nandCima = new NandGate();
        nandBaixo = new NandGate();
        nandDir = new NandGate();

        //Conectando
        nandDir.connect(0, nandCima);
        nandDir.connect(1, nandBaixo);

    }

    @Override
    public boolean read() {
        return nandDir.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        switch (inputIndex) {
            case 0:

                nandCima.connect(0, emitter);
                nandCima.connect(1, emitter);
                break;

            case 1:

                nandBaixo.connect(0, emitter);
                nandBaixo.connect(1, emitter);
                break;

            default:

                throw new IndexOutOfBoundsException(inputIndex);
        }
    }

}
