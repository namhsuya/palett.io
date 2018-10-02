/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmpalette;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author namhsuyA
 */
public class PalettePreviewController implements Initializable {

    @FXML
    private Canvas previewCanvas;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setPreview(Image preview){
        previewCanvas.setWidth(preview.getWidth());
        previewCanvas.setHeight(preview.getHeight());
        GraphicsContext gc = previewCanvas.getGraphicsContext2D();        
        gc.drawImage(preview, 0, 0);
    }
    
}
