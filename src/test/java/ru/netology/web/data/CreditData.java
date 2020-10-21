package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditData {
    private String id;
    private String bank_id;
    private String created;
    private String status;
}
