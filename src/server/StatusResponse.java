package server;

public enum StatusResponse {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    BAD_REQUEST(400, "Bad Request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String description;

    StatusResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static StatusResponse fromCode(int code) {
        for (StatusResponse status : StatusResponse.values()) {
            if (status.getCode() == code) {
                return status;
            }
        } throw new IllegalArgumentException("Invalid status code: " + code);
    }

    @Override
    public String toString() {
        return code + " " + description;
    }

}
