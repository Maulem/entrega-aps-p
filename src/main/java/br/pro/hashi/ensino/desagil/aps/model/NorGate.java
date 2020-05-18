package br.pro.hashi.ensino.desagil.aps.model;

public class NorGate extends Gate {

    private final NandGate nandCima;
    private final NandGate nandBaixo;
    private final NandGate nandMeio;
    private final NandGate nandDir;


    public NorGate() {
        super("NOR", 2);
        //Criando os objetos
        nandCima = new NandGate();
        nandBaixo = new NandGate();
        nandMeio = new NandGate();
        nandDir = new NandGate();

        //Conectando
        nandMeio.connect(0, nandCima);
        nandMeio.connect(1, nandBaixo);
        nandDir.connect(0, nandMeio);
        nandDir.connect(1, nandMeio);

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
