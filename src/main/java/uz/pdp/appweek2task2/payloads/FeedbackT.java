package uz.pdp.appweek2task2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackT {
    private Integer id;
    private String fullName;
    private String email;
    private String text;
    private Integer productId;
}
