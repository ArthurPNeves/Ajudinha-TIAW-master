package responses;

public class RespostaSucesso extends Resposta {
    public RespostaSucesso(Object message) {
        super(StatusResposta.SUCCESS, message);
    }

}
