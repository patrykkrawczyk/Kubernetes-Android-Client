package pro.patrykkrawczyk.kubernetesclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NavigationButton {

    private String name;
    private Class<?> activity;
}
