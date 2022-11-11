package responses;

public class RespostaERRO extends Resposta {
    public RespostaERRO(Object message) {
        super(StatusResposta.ERROR, message);
    }

}
