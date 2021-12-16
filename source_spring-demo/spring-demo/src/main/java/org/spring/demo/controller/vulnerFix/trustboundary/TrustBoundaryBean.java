package org.spring.demo.controller.vulnerFix.trustboundary;

import javax.validation.constraints.Pattern;

public class TrustBoundaryBean {
    @Pattern(regexp = "^[a-zA-Z]\\\\w{5,17}$", message = "Username is saved in session!")
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
