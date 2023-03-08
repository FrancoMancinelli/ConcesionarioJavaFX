package utils;

import hibernate.persistence.models.Reparacion;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomListaWebJefe extends Pane {
    public CustomListaWebJefe(Reparacion reparacion, String color) {
        setPrefSize(983, 434);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.5));
        dropShadow.setRadius(15);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(5);
        setEffect(dropShadow);   
        
        getStyleClass().add("custom-pane");
       
        Label vehiculo = new Label(reparacion.getVehiculo().getModelo());
        vehiculo.setFont(Font.font("System", 22));
        vehiculo.setStyle("-fx-font-weight: bold;");
        vehiculo.setLayoutX(113);
        vehiculo.setLayoutY(10);

        Label idVehiculo = new Label("ID: "+ reparacion.getVehiculo().getId());
        idVehiculo.setFont(Font.font("System", 18));
        idVehiculo.setLayoutX(113);
        idVehiculo.setLayoutY(40);

        getChildren().addAll(vehiculo, idVehiculo);
        
        setStyle(color);
        
        // Agregar Pane Descripción
        Pane paneDescripcion = new Pane();
        paneDescripcion.setPrefSize(484, 190);
        paneDescripcion.setLayoutX(537);
        paneDescripcion.setLayoutY(13);
        
        paneDescripcion.setStyle("-fx-background-color: #E7E4E4; -fx-background-radius: 10px;");
        
        getChildren().add(paneDescripcion);
        
        Label tituloDesc = new Label("DETALLES");
        tituloDesc.setFont(Font.font("System", 22));
        tituloDesc.setLayoutX(14);
        tituloDesc.setLayoutY(18);
        tituloDesc.setStyle("-fx-font-weight: bold;");


        Label infoDesc = new Label("~ Espejo retrovisor laterales destruidos"
        		+ "\n~ Revisión de aceite"
        		+ "\n~ Remplazo de volante"
        		+ "\n~ Polarización de ventanas");
        infoDesc.setFont(Font.font("System", 16));
        infoDesc.setLayoutX(14);
        infoDesc.setLayoutY(45);

        paneDescripcion.getChildren().addAll(tituloDesc, infoDesc);
        
        // Agregar Pane Fechas
        Pane paneFechas = new Pane();
        paneFechas.setPrefSize(400, 120);
        paneFechas.setLayoutX(113);
        paneFechas.setLayoutY(82);
        
        paneFechas.setStyle("-fx-background-color: #E7E4E4; -fx-background-radius: 10px;");
        
        getChildren().add(paneFechas);
        
        Label tituloInicio = new Label("Fecha Inicio");
        tituloInicio.setFont(Font.font("System", 20));
        tituloInicio.setLayoutX(14);
        tituloInicio.setLayoutY(20);
        tituloInicio.setStyle("-fx-font-weight: bold;");

        
        Label tituloFin = new Label("Fecha Fin");
        tituloFin.setFont(Font.font("System", 20));
        tituloFin.setLayoutX(14);
        tituloFin.setLayoutY(68);
        tituloFin.setStyle("-fx-font-weight: bold;");

        Label infoInicio = new Label(reparacion.getFechaInicio().toString());
        infoInicio.setFont(Font.font("System", 18));
        infoInicio.setLayoutX(197);
        infoInicio.setLayoutY(20);

        Label infoFin = new Label(reparacion.getFechaFin().toString());
        infoFin.setFont(Font.font("System", 18));
        infoFin.setLayoutX(197);
        infoFin.setLayoutY(68);

        paneFechas.getChildren().addAll(tituloInicio, tituloFin, infoInicio, infoFin);
        
     // Agregar Pane Ganancia y Presupuesto
        Pane paneGanancias = new Pane();
        paneGanancias.setPrefSize(909, 75);
        paneGanancias.setLayoutX(113);
        paneGanancias.setLayoutY(218);
        
        paneGanancias.setStyle("-fx-background-color: #E7E4E4; -fx-background-radius: 10px;");
        
        getChildren().add(paneGanancias);
        
        Label tituloGanancias = new Label("Ganancias:");
        tituloGanancias.setFont(Font.font("System", 20));
        tituloGanancias.setLayoutX(14);
        tituloGanancias.setLayoutY(23);
        tituloGanancias.setStyle("-fx-font-weight: bold;");

        Label tituloPresup = new Label("Presupuesto:");
        tituloPresup.setFont(Font.font("System", 20));
        tituloPresup.setLayoutX(230);
        tituloPresup.setLayoutY(23);
        tituloPresup.setStyle("-fx-font-weight: bold;");

        Label tituloPresup2 = new Label("Presupuesto Utilizado:");
        tituloPresup2.setFont(Font.font("System", 20));
        tituloPresup2.setLayoutX(446);
        tituloPresup2.setLayoutY(23);
        tituloPresup2.setStyle("-fx-font-weight: bold;");
   
        double ganancia = reparacion.getPresupuesto_cliente() - reparacion.getPresupuesto_taller();

        Label infoGanancias = new Label(String.valueOf(ganancia)+"$");
        infoGanancias.setFont(Font.font("System", 18));
        infoGanancias.setLayoutX(125);
        infoGanancias.setLayoutY(25);
        

        Label infoPresup = new Label(reparacion.getPresupuesto_cliente().toString()+"$");
        infoPresup.setFont(Font.font("System", 18));
        infoPresup.setLayoutX(360);
        infoPresup.setLayoutY(25);
        
        Label infoPresup2 = new Label(reparacion.getPresupuesto_taller().toString()+"$");
        infoPresup2.setFont(Font.font("System", 18));
        infoPresup2.setLayoutX(660);
        infoPresup2.setLayoutY(25);

        paneGanancias.getChildren().addAll(tituloGanancias, infoGanancias, tituloPresup, infoPresup, tituloPresup2, infoPresup2);
        
        
        // Agregar Pane Piezas
        Pane panePiezas = new Pane();
        panePiezas.setPrefSize(909, 91);
        panePiezas.setLayoutX(113);
        panePiezas.setLayoutY(305);
        
        panePiezas.setStyle("-fx-background-color: #E7E4E4; -fx-background-radius: 10px;");
        
        getChildren().add(panePiezas);
        
        Label tituloPiezas = new Label("Piezas Utilizadas:");
        tituloPiezas.setFont(Font.font("System", 18));
        tituloPiezas.setLayoutX(24);
        tituloPiezas.setLayoutY(14);
        tituloPiezas.setStyle("-fx-font-weight: bold;");

        Label infoPiezas = new Label(reparacion.getDiagnostico());
        infoPiezas.setFont(Font.font("System", 16));
        infoPiezas.setLayoutX(175);
        infoPiezas.setLayoutY(14);

        panePiezas.getChildren().addAll(tituloPiezas, infoPiezas);
        
    }
}