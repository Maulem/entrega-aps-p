package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {

    private final NandGate nandEsq;
    private final NandGate nandCima;
    private final NandGate nandBaixo;
    private final NandGate nandDir;


    public XorGate() {
        super("XOR", 2);
        //Criando os objetos
        nandEsq = new NandGate();
        nandCima = new NandGate();
        nandBaixo = new NandGate();
        nandDir = new NandGate();

        //Conectando
        nandBaixo.connect(0, nandEsq);
        nandCima.connect(1, nandEsq);
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
                nandEsq.connect(0, emitter);
                break;

            case 1:

                nandEsq.connect(1, emitter);
                nandBaixo.connect(1, emitter);
                break;

            default:

                throw new IndexOutOfBoundsException(inputIndex);
        }
    }
}
