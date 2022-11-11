package responses;

public class Resposta {

    private StatusResposta status;
    private Object message;

    public Resposta(StatusResposta status, Object message) {
        this.status = status;
        this.message = message;
    }


    public StatusResposta getStatus() {
        return status;
    }

    public void setStatus(StatusResposta status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }


}
