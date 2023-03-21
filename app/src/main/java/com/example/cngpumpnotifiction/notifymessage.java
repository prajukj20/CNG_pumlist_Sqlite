package com.example.cngpumpnotifiction;

public class notifymessage {
    String  message;
    String Updated_by;
    String pump_id;


    public notifymessage(String message, String updated_by, String pump_id) {
        this.message = message;
        Updated_by = updated_by;
        this.pump_id = pump_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUpdated_by() {
        return Updated_by;
    }

    public void setUpdated_by(String updated_by) {
        Updated_by = updated_by;
    }

    public String getPump_id() {
        return pump_id;
    }

    public void setPump_id(String pump_id) {
        this.pump_id = pump_id;
    }
}
