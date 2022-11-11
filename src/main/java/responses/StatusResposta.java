package responses;

public enum StatusResposta {
    SUCCESS ("Success"),
    ERROR ("Error");

    private String status;

    StatusResposta(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
