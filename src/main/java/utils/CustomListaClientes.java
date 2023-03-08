package utils;

import hibernate.persistence.models.Cliente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class CustomListaClientes extends Pane {
	
	Button button = new Button();
	
	public CustomListaClientes(Cliente cliente) {
		setPrefSize(550, 60);
		setStyle("-fx-background-color: #FFFFFF;");

		
		Label label = new Label(cliente.getNombre() + " " + cliente.getApellido());

		getChildren().add(label);
		getChildren().add(button);
		
		label.setFont(new Font(24));
		label.setLayoutX(14);
		label.setLayoutY(12);

		button.setPrefSize(50, 50);
		button.setLayoutX(460);
		button.setLayoutY(5);
		button.setStyle("-fx-background-color: #F1E7CA;");
		button.setText(">");

	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	
}
