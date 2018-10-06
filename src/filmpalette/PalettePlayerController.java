/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmpalette;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import filmpalette.colorthief.ColorThief;
import filmpalette.colorthief.MMCQ;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.JavaFXFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

/**
 *
 * @author SYSTEM
 */
public class PalettePlayerController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane rootView;
    @FXML
    private BorderPane mBP;
    @FXML
    private VBox mediaVBox;
    @FXML
    private MediaView paletteMediaView;
    @FXML
    private FontAwesomeIconView mediaController;
    @FXML
    private JFXSlider mediaSeekBar;
    @FXML
    private JFXButton domC;
    @FXML
    private JFXButton c1;
    @FXML
    private JFXButton c2;
    @FXML
    private JFXButton c3;
    @FXML
    private JFXButton c4;
    @FXML
    private JFXButton c5;
    @FXML
    private JFXButton c6;
    @FXML
    private JFXButton c7;
    @FXML
    private JFXButton c8;
    @FXML
    private JFXButton c9;
    
    private Media filmMedia;
    private MediaPlayer filmPlayer;
    private double w, h;
    @FXML
    private Label fps;
    public void setW(double w) {
        this.w = w - 300;
    }

    public void setH(double h) {
        this.h = h - 200;
    }
    private Stage stage;
    private double fW, fH;
    private BufferedImage film;
    private File filmFile;
    private final Color[] fPalette = new Color[10];
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    private StringConverter<Double> sc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        domC.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(domC.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)domC.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
            
        });
        c1.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c1.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c1.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
        c2.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c2.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c2.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
        c3.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c3.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c3.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
        c4.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c4.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c4.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
        c5.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c5.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c5.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
        c6.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c6.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c6.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
        c7.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c7.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c7.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
        c8.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c8.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c8.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
        c9.setOnMouseClicked((MouseEvent event) -> {
            final ClipboardContent content = new ClipboardContent();
            content.putString(c9.getStyle().substring(21, 28));
            clipboard.setContent(content);
            makeText((Stage)c9.getScene().getWindow(), "Copied to clipboard", 3500, 500, 500);
        });
    }    

    @FXML
    private void closeFrame(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimizeFrame(MouseEvent event) {
        Stage s = (Stage) rootView.getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    private void chooseFile(MouseEvent event){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.flv");
        chooser.getExtensionFilters().add(extFilter);
        filmFile = chooser.showOpenDialog(null);

        String filmPath = filmFile.getAbsolutePath();
        filmPath = filmPath.replace("\\", "/");

        
        filmMedia = new Media(new File(filmPath).toURI().toString());
        if (filmPlayer != null) {
            filmPlayer.stop();
        }

        filmPlayer = new MediaPlayer(filmMedia);
        filmPlayer.setAutoPlay(true);
        filmPlayer.setRate(1.0);
        paletteMediaView.setMediaPlayer(filmPlayer);
        paletteMediaView.setPreserveRatio(true);
        mediaController.setGlyphName("PAUSE");
        stage = (Stage) rootView.getScene().getWindow();
        filmPlayer.setOnReady(() -> {
            stage = (Stage) rootView.getScene().getWindow();
            setW(stage.getWidth());
            setH(stage.getHeight());
            mediaSeekBar.setMax(filmPlayer.getMedia().getDuration().toSeconds());

            double scaleFactor = filmPlayer.getMedia().getWidth() / filmPlayer.getMedia().getHeight();

            double filmHeight = filmPlayer.getMedia().getHeight();
            fH = filmHeight;
            double filmWidth = filmPlayer.getMedia().getWidth();
            fW = filmWidth;
            double scaleX = w / filmWidth;
            double scaleY = h / filmHeight;
            System.err.println("cW/fW: " + w + "/" + filmWidth);
            System.err.println("cH/fH: " + h + "/" + filmHeight);
            System.err.println("Scale X//Y: " + scaleX + "/" + scaleY);

            double sF[] = getScaledDimensions(filmWidth, filmHeight);
            paletteMediaView.setFitWidth(sF[0]);
            paletteMediaView.setFitHeight(sF[1]);
            
            sc = new StringConverter<Double>() {
                @Override
                public String toString(Double object) {
                    long seconds = object.longValue();
                    long minutes = TimeUnit.SECONDS.toMinutes(seconds);
                    long remainingseconds = seconds - TimeUnit.MINUTES.toSeconds(minutes);
                    return String.format("%02d", minutes) + ":" + String.format("%02d", remainingseconds);
                }

                @Override
                public Double fromString(String string) {
                    return null;
                }
            };
            mediaSeekBar.setLabelFormatter(sc);
            
            filmPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
                mediaSeekBar.setValue(newValue.toSeconds());
                fps.setText(""+newValue.toSeconds());
                
                if (newValue.equals(filmPlayer.getMedia().getDuration())) {
                    mediaController.setGlyphName("PLAY_CIRCLE");
                }
                BufferedImage img = getImage(paletteMediaView);
                //BufferedImage img = getBufferedFilma();
                //MMCQ.CMap result = ColorThief.getColorMap(img, 5);
                //result.vboxes.get(0);
                //System.out.println("result: "+dominantColor);
                
                
                
                
                MMCQ.CMap result = ColorThief.getColorMap(img, 11);
                filmpalette.colorthief.MMCQ.VBox dominantColor = result.vboxes.get(0);
                
                int[] rgb = dominantColor.avg(false);
                String rgbHexString = createRGBHexString(rgb);
                fPalette[0] = Color.valueOf(rgbHexString);
                domC.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(9);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[1] = Color.valueOf(rgbHexString);
                c1.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(1);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[2] = Color.valueOf(rgbHexString);
                c2.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(2);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[3] = Color.valueOf(rgbHexString);
                c3.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(3);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[4] = Color.valueOf(rgbHexString);
                c4.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(4);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[5] = Color.valueOf(rgbHexString);
                c5.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(5);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[6] = Color.valueOf(rgbHexString);
                c6.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(6);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[7] = Color.valueOf(rgbHexString);
                c7.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(7);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[8] = Color.valueOf(rgbHexString);
                c8.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                dominantColor = result.vboxes.get(8);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[9] = Color.valueOf(rgbHexString);
                c9.setStyle("-fx-background-color:" + rgbHexString + "; ");
                
                Map<Double, Color> brightnessMap = new HashMap<>();
                for (Color color : fPalette) {
                    brightnessMap.put(color.getBrightness(), color);
                }
                int it = 0;
                Map<Double, Color> sortedMap = new TreeMap<>(brightnessMap);
                for (Map.Entry<Double, Color> entry : sortedMap.entrySet()) {                    
                    fPalette[it]=entry.getValue();
                    it++;
                }

            });
            
            mediaSeekBar.setOnMouseClicked((MouseEvent event1) -> {
                filmPlayer.seek(Duration.seconds(mediaSeekBar.getValue()));
            });
            mediaSeekBar.setOnMouseDragged((MouseEvent event2) -> {
                filmPlayer.seek(Duration.seconds(mediaSeekBar.getValue()));
            });

        });

    }

    @FXML
    private void prevFrame(MouseEvent event) {
        if (filmPlayer!=null) {
            filmPlayer.seek(Duration.seconds(mediaSeekBar.getValue()-1));
        }
    }

    @FXML
    private void controlMedia(MouseEvent event) {
        switch (mediaController.getGlyphName()) {
            case "PLAY_CIRCLE":
                filmPlayer.play();
                mediaController.setGlyphName("PAUSE");
                break;
            case "PAUSE":
                filmPlayer.pause();
                mediaController.setGlyphName("PLAY_CIRCLE");
                break;
        }
    }

    @FXML
    private void nextFrame(MouseEvent event) {if (filmPlayer!=null) {
            filmPlayer.seek(Duration.seconds(mediaSeekBar.getValue()+1));
        }
    }

    @FXML
    private void renderOutput(MouseEvent event) throws FrameGrabber.Exception {
        
        
        
        try {
            Canvas mCanvas = new Canvas(fW+10, fH+165);
            Image filma = getFilma();
            FileChooser fileChooser = new FileChooser();
            
            //Set extension filter
            FileChooser.ExtensionFilter extFilter
                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            
            //Show save file dialog
            File file = fileChooser.showSaveDialog(null);
            
            if(file != null){
                try {
                    WritableImage writableImage = new WritableImage((int)mCanvas.getWidth(), (int)mCanvas.getHeight());
                    GraphicsContext gc = mCanvas.getGraphicsContext2D();
                    gc.drawImage(filma, 5, 5, fW, fH);

                    
                    double rectY = 5.0;
                    double rectX = 5.0;
                    double rectH = fH/10;
                    double rectW = fW/10;
                    
                    
                    for (int i = 0; i < 10; i++) {
                        gc.setFill(fPalette[i]);
                        switch (i) {
                            case 0:
                                gc.fillRect(rectX, (int)fH+10, rectW-8, 150);
                                break;
                            case 9:
                                gc.fillRect(rectX-4, (int)fH+10, rectW+4, 150);
                                break;
                            default:
                                gc.fillRect(rectX-4, (int)fH+10, rectW-4, 150);
                                break;
                        }
                        
                        //gc.fillRect(rectX, (int)fH+10, rectW, 150);
                        //gc.fillRect((int)fW+10, rectY, 150, (int)(fH/10));
                        rectX=(rectX+rectW);
                        //rectY=(rectY+rectH);
                    }
                    
                    mCanvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                    makeText((Stage)c1.getScene().getWindow(), "Image Saved", 3500, 500, 500);
                    //ImageIO.write(bI, "png", file);
                    //System.err.println("finalY: "+rectY+" fH: "+fH);
                } catch (IOException ex) {
                    Logger.getLogger(PalettePlayerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PalettePlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private double[] getScaledDimensions(double filmWidth, double filmHeight) {
        double dimensions[] = new double[2];
        double longSideMax = w;
        double shortSideMax = h;
        double wRatio, hRatio;

        if (filmWidth >= filmHeight) {
            if (filmWidth <= longSideMax && filmHeight <= shortSideMax) {
                dimensions[0] = filmWidth;
                dimensions[1] = filmHeight;
                return dimensions;  // no resizing required
            }
            wRatio = longSideMax / filmWidth;
            hRatio = shortSideMax / filmHeight;
        } else {
            if (filmHeight <= longSideMax && filmWidth <= shortSideMax) {
                dimensions[0] = filmWidth;
                dimensions[1] = filmHeight;
                return dimensions;  // no resizing required
            }
            wRatio = shortSideMax / filmWidth;
            hRatio = longSideMax / filmHeight;
        }

        // hRatio and wRatio now have the scaling factors for height and width.
        // You want the smallest of the two to ensure that the resulting image
        // fits in the desired frame and maintains the aspect ratio.
        double resizeRatio = Math.min(wRatio, hRatio);

        double newHeight = filmHeight * resizeRatio;
        double newWidth = filmWidth * resizeRatio;
        dimensions[0] = newWidth;
        dimensions[1] = newHeight;
        // Now call function to resize original image to [newWidth, newHeight]
        // and return the result.

        return dimensions;
    }
    
    /**
     * @param node
     * @return Given any javafx node, return the image on the node
     */
    private  BufferedImage getImage(Node node) {
        WritableImage snapshot = node.snapshot(new SnapshotParameters(), null);
        BufferedImage buffImg = SwingFXUtils.fromFXImage(snapshot, null);
        film = buffImg;
        return buffImg;
    }
    
    private String createRGBHexString(int[] rgb) {
        String rgbHex = Integer.toHexString(rgb[0] << 16 | rgb[1] << 8 | rgb[2]);

        // Left-pad with 0s
        int length = rgbHex.length();
        if (length < 6) {
            rgbHex = "00000".substring(0, 6 - length) + rgbHex;
        }
        return "#" + rgbHex;
    }

    private Image getFilma() throws FrameGrabber.Exception {
        FFmpegFrameGrabber mFFmpegFrameGrabber = new FFmpegFrameGrabber(filmFile);
        
        mFFmpegFrameGrabber.start();
        double rate = mFFmpegFrameGrabber.getFrameRate();
        //double rate = filmPlayer.getCurrentRate();
        //System.err.println("seekbarValue "+mediaSeekBar.getValue());
        filmPlayer.getCurrentTime().toSeconds();
        mFFmpegFrameGrabber.setFrameNumber((int)(rate*Double.parseDouble(fps.getText())-5.33));
        //System.err.println("render frame number"+(Double.parseDouble(fps.getText())-5.33));
        Frame f = mFFmpegFrameGrabber.grabImage();
        JavaFXFrameConverter fXFrameConverter = new JavaFXFrameConverter();
        Image img = fXFrameConverter.convert(f);
        return img;
    }
    private BufferedImage getBufferedFilma() throws FrameGrabber.Exception {
        FFmpegFrameGrabber mFFmpegFrameGrabber = new FFmpegFrameGrabber(filmFile);
        
        mFFmpegFrameGrabber.start();
        double rate = mFFmpegFrameGrabber.getFrameRate();
        mFFmpegFrameGrabber.setFrameNumber((int)(rate*mediaSeekBar.getValue()));
        Frame f = mFFmpegFrameGrabber.grab();
        Java2DFrameConverter biConv = new Java2DFrameConverter();
        BufferedImage bI = biConv.getBufferedImage(f);
        
        return bI;
    }
    
    private void makeText(Stage ownerStage, String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay){
        Stage toastStage=new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Verdana", 36));
        text.setFill(Color.WHITE);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(0, 0, 0, 0.2); -fx-padding: 50px;");
        root.setOpacity(0);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1)); 
        fadeInTimeline.getKeyFrames().add(fadeInKey1);   
        fadeInTimeline.setOnFinished((ae) -> 
        {
            new Thread(() -> {
                try
                {
                    Thread.sleep(toastDelay);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                   Timeline fadeOutTimeline = new Timeline();
                    KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0)); 
                    fadeOutTimeline.getKeyFrames().add(fadeOutKey1);   
                    fadeOutTimeline.setOnFinished((aeb) -> toastStage.close()); 
                    fadeOutTimeline.play();
            }).start();
        }); 
        fadeInTimeline.play();
    }

    @FXML
    private void showPreview(MouseEvent event) {
        Parent root;
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("PalettePreview.fxml"));
            root = (Parent) fXMLLoader.load();
            PalettePreviewController palettePreviewController = fXMLLoader.getController();
            Stage mStage = new Stage();
            //stage.setTitle("My New Stage Title");
            mStage.setScene(new Scene(root, 700, 550));
            palettePreviewController.setPreview(getFilma());
            System.err.println("preview frame: "+mediaSeekBar.getValue());
            mStage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void chooseImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.bmp");
        chooser.getExtensionFilters().add(extFilter);
        filmFile = chooser.showOpenDialog(null);

        String filmPath = filmFile.getAbsolutePath();
        filmPath = filmPath.replace("\\", "/");
        Image still = new Image(new File(filmPath).toURI().toString());
        fW = still.getWidth();
        fH = still.getHeight();
        
        BufferedImage img = SwingFXUtils.fromFXImage(still, null);
        MMCQ.CMap result = ColorThief.getColorMap(img, 5);
        filmpalette.colorthief.MMCQ.VBox dominantColor = result.vboxes.get(0);
        
        int[] rgb = dominantColor.avg(false);
                String rgbHexString = createRGBHexString(rgb);
                fPalette[0] = Color.valueOf(rgbHexString);
                result = ColorThief.getColorMap(img, 10);
                dominantColor = result.vboxes.get(0);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[1] = Color.valueOf(rgbHexString);
                dominantColor = result.vboxes.get(1);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[2] = Color.valueOf(rgbHexString);
                dominantColor = result.vboxes.get(2);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[3] = Color.valueOf(rgbHexString);
                dominantColor = result.vboxes.get(3);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[4] = Color.valueOf(rgbHexString);
                dominantColor = result.vboxes.get(4);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[5] = Color.valueOf(rgbHexString);
                dominantColor = result.vboxes.get(5);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[6] = Color.valueOf(rgbHexString);
                dominantColor = result.vboxes.get(6);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[7] = Color.valueOf(rgbHexString);
                dominantColor = result.vboxes.get(7);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[8] = Color.valueOf(rgbHexString);                
                dominantColor = result.vboxes.get(8);
                rgb = dominantColor.avg(false);
                rgbHexString = createRGBHexString(rgb);
                fPalette[9] = Color.valueOf(rgbHexString);
                
                Map<Double, Color> brightnessMap = new HashMap<>();
                for (Color color : fPalette) {
                    brightnessMap.put(color.getBrightness(), color);
                }
                int it = 0;
                Map<Double, Color> sortedMap = new TreeMap<>(brightnessMap);
                for (Map.Entry<Double, Color> entry : sortedMap.entrySet()) {                    
                    fPalette[it]=entry.getValue();
                    it++;
                }


        
        
        Canvas mCanvas = new Canvas(fW+10, fH+165);
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
        
        if(file != null){
            try {
                WritableImage writableImage = new WritableImage((int)mCanvas.getWidth(), (int)mCanvas.getHeight());
                GraphicsContext gc = mCanvas.getGraphicsContext2D();
                gc.drawImage(still, 5, 5, fW, fH);
                
                double rectY = 5.0;
                double rectX = 5.0;
                double rectH = fH/10;
                double rectW = fW/10;
                
                
                for (int i = 0; i < 10; i++) {
                    gc.setFill(fPalette[i]);
                        switch (i) {
                        case 0:
                            gc.fillRect(rectX, (int)fH+10, rectW-8, 150);
                            break;
                        case 9:
                            gc.fillRect(rectX-4, (int)fH+10, rectW+4, 150);
                            break;
                        default:
                            gc.fillRect(rectX-4, (int)fH+10, rectW-4, 150);
                            break;
                    }
                    
                    //gc.fillRect(rectX, (int)fH+10, rectW, 150);
                    //gc.fillRect((int)fW+10, rectY, 150, (int)(fH/10));
                    rectX=(rectX+rectW);
                    //rectY=(rectY+rectH);
                }
                
                mCanvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
                makeText((Stage)c1.getScene().getWindow(), "Image Saved", 3500, 500, 500);
                //ImageIO.write(bI, "png", file);
                //System.err.println("finalY: "+rectY+" fH: "+fH);
                } catch (IOException ex) {
                    Logger.getLogger(PalettePlayerController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }
    
}
