package app.sum.bti.sign.vo;

import lombok.Data;

public class SignVO {

    @Data
    public static class SignInfo{

        private String idInput;
        private String passwdInput;
        private String emailInput;
        private String locationInput;
        private String jobInput;
    }
}
