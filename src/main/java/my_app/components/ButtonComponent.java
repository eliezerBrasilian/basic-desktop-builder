package my_app.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ButtonComponent extends Button {

    VBox appearenceContainer = new VBox();
    VBox settingsContainer = new VBox();

    ObjectProperty<Node> selectedNode = new SimpleObjectProperty<>();

    public ButtonComponent() {
        super();
        selectedNode.set(this); // 👈 sempre aponta para o próprio botão
    }

    public ButtonComponent(String content) {
        super(content);
        selectedNode.set(this);
    }

    public void renderRightSideContainer(Pane father, BooleanProperty appearenceIsSelected) {

        // render inicial baseado no valor atual
        if (appearenceIsSelected.get()) {
            appearance(father);
        } else {
            settings(father);
        }

        appearenceIsSelected.addListener((o, old, v) -> {
            if (v)
                appearance(father);
            else
                settings(father);
        });

    }

    void appearance(Pane father) {
        father.getChildren().clear(); // limpa o container

        var nodes = AppearanceFactory.renderComponentes(this,
                selectedNode, "node-value-field", "bg-picker", "padding-field", "border-color-picker",
                "border-width-field", "border-radius-field",
                "font-color-size", "font-color-field", "font-weight-field");

        appearenceContainer.getChildren().setAll(nodes);

        father.getChildren().add(appearenceContainer);
    }

    void settings(Pane father) {
        father.getChildren().clear(); // limpa o container

        Text title = new Text("Button Settings");

        settingsContainer.getChildren().setAll(title);

        father.getChildren().add(settingsContainer);
    }
}
