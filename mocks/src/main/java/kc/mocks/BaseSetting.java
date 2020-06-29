package kc.mocks;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseSetting {

    private int port;
    private String databaseHost;
    private int databasePort;

}
