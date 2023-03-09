package utils;

import hibernate.persistence.models.Propuesta;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomListaWebEmpleado extends Pane {
    public CustomListaWebEmpleado(Propuesta propuesta) {
        setPrefSize(200, 155);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.5));
        dropShadow.setRadius(15);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(5);
        setEffect(dropShadow);   
        
        setStyle("-fx-background-color: #FFFFFF;");
        
        getStyleClass().add("custom-lista-propuestas");
       
        Label vehiculo = new Label(propuesta.getVehiculo().getModelo());
        vehiculo.setFont(Font.font("System", 22));
        vehiculo.setStyle("-fx-font-weight: bold;");
        vehiculo.setLayoutX(48);
        vehiculo.setLayoutY(10);

        Label idVehiculo = new Label("ID: "+ propuesta.getVehiculo().getId());
        idVehiculo.setFont(Font.font("System", 18));
        idVehiculo.setLayoutX(48);
        idVehiculo.setLayoutY(40);

        getChildren().addAll(vehiculo, idVehiculo);
                
        // Agregar Pane Descripción
        Pane paneDescripcion = new Pane();
        paneDescripcion.setPrefSize(380, 130);
        paneDescripcion.setLayoutX(590);
        paneDescripcion.setLayoutY(13);
        
        paneDescripcion.setStyle("-fx-background-color: #E7E4E4; -fx-background-radius: 10px;");
        
        getChildren().add(paneDescripcion);
        
        Label tituloDesc = new Label("DETALLES");
        tituloDesc.setFont(Font.font("System", 16));
        tituloDesc.setLayoutX(14);
        tituloDesc.setLayoutY(8);
        tituloDesc.setStyle("-fx-font-weight: bold;");


        Label infoDesc = new Label("~ Espejo retrovisor laterales destruidos"
        		+ "\n~ Revisión de aceite"
        		+ "\n~ Remplazo de volante"
        		+ "\n~ Polarización de ventanas");
        infoDesc.setFont(Font.font("System", 14));
        infoDesc.setLayoutX(14);
        infoDesc.setLayoutY(30);

        paneDescripcion.getChildren().addAll(tituloDesc, infoDesc);
        
        // Agregar Pane Fechas
        Pane paneFechas = new Pane();
        paneFechas.setPrefSize(265, 60);
        paneFechas.setLayoutX(305);
        paneFechas.setLayoutY(10);
        
        paneFechas.setStyle("-fx-background-color: #E7E4E4; -fx-background-radius: 10px;");
        
        getChildren().add(paneFechas);
        
        Label tituloInicio = new Label("Fecha Inicio - ");
        tituloInicio.setFont(Font.font("System", 20));
        tituloInicio.setLayoutX(14);
        tituloInicio.setLayoutY(14);
        tituloInicio.setStyle("-fx-font-weight: bold;");

        Label infoInicio = new Label(propuesta.getId().getFecha().toString());
        infoInicio.setFont(Font.font("System", 18));
        infoInicio.setLayoutX(150);
        infoInicio.setLayoutY(15);

        paneFechas.getChildren().addAll(tituloInicio, infoInicio);
        
     // Agregar Pane Ganancia y Presupuesto
        Pane paneGanancias = new Pane();
        paneGanancias.setPrefSize(540, 60);
        paneGanancias.setLayoutX(30);
        paneGanancias.setLayoutY(85);
        
        paneGanancias.setStyle("-fx-background-color: #E7E4E4; -fx-background-radius: 10px;");
        
        getChildren().add(paneGanancias);
        
        Label tituloGanancias = new Label("Ganancias:");
        tituloGanancias.setFont(Font.font("System", 20));
        tituloGanancias.setLayoutX(14);
        tituloGanancias.setLayoutY(16);
        tituloGanancias.setStyle("-fx-font-weight: bold;");

        Label tituloPresup = new Label("Estado:");
        tituloPresup.setFont(Font.font("System", 20));
        tituloPresup.setLayoutX(245);
        tituloPresup.setLayoutY(16);
        tituloPresup.setStyle("-fx-font-weight: bold;");


        Label infoGanancias = new Label(propuesta.getPrecio().toString());
        infoGanancias.setFont(Font.font("System", 18));
        infoGanancias.setLayoutX(125);
        infoGanancias.setLayoutY(18);
        

        Label infoPresup = new Label(propuesta.getVendido().toString());
        infoPresup.setFont(Font.font("System", 18));
        infoPresup.setLayoutX(320);
        infoPresup.setLayoutY(17);

        paneGanancias.getChildren().addAll(tituloGanancias, infoGanancias, tituloPresup, infoPresup);
        
    }
}