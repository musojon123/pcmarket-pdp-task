package uz.pdp.appweek2task2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean isSuccess;
    private String message;
    private Integer id;

    public ApiResponse(boolean isSuccess,  String message){
        this.isSuccess = isSuccess;
        this.message = message;
    }

}
