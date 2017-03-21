package SLogo.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class creates a popup for the user to change the Turtle Environment's properties
 *
 * @author Riley Nisbet
 */

public class CanvasPropertiesDisplay {
    private static final String RESOURCE_FILEPATH = "resources/View/";
    private static final int MAX_PEN_WIDTH = 7;
    Stage myStage;
    Popup myPopup;
    private ResourceBundle viewResources;
    private Integer penColor;
    private Double penWidth;
    private Integer backgroundColorIndex;
    private CanvasView myCV;
    private Map<Integer, Color> myColorMap;

    public CanvasPropertiesDisplay(CanvasView cv, Map<Integer, Color> colorMap) {
        viewResources = ResourceBundle.getBundle(RESOURCE_FILEPATH + "View");
        myCV = cv;
        myColorMap = colorMap;

        penColor = myCV.getPenColor();
        penWidth = myCV.getPenWidth();
        backgroundColorIndex = myCV.getBackground();

        createStage();
    }

    /**
     * Show Display
     */
    public void toggleDisplay() {
        myStage.setScene(getScene());
        myStage.show();
    }

    private void setProperties() {
        myCV.setPenColor(penColor);
        myCV.setPenSize(penWidth);
        myCV.setBackground(backgroundColorIndex);
        myStage.hide();
    }

    private Scene getScene() {
        Pane root = new Pane();

        VBox displayBox = new VBox();

        HBox penColorBox = new HBox();
        Label penColorLabel = new Label(String.format(viewResources.getString("penLabel"), penColor));
        Slider penColorSlider = new Slider(0, myColorMap.size() - 1, penColor);
        penColorSlider.setOnMouseClicked(e -> {
            penColor = (int) penColorSlider.getValue();
            penColorLabel.setText(String.format(viewResources.getString("penLabel"), penColor));
        });
        setSliderProperties(penColorBox, penColorLabel, penColorSlider, 0);

        HBox penWidthBox = new HBox();
        Label penWidthLabel = new Label(String.format(viewResources.getString("widthLabel"), penWidth));
        Slider penWidthSlider = new Slider(0, MAX_PEN_WIDTH, penWidth);
        penWidthSlider.setOnMouseClicked(e -> {
            penWidth = penWidthSlider.getValue();
            penWidthLabel.setText(String.format(viewResources.getString("widthLabel"), penWidth));
        });
        setSliderProperties(penWidthBox, penWidthLabel, penWidthSlider, 10);

        HBox bgColorBox = new HBox();
        Label bgColorLabel = new Label(String.format(viewResources.getString("backgroundLabel"), backgroundColorIndex));
        Slider bgColorSlider = new Slider(0, myColorMap.size() - 1, backgroundColorIndex);//magic number 5
        bgColorSlider.setOnMouseClicked(e -> {
            backgroundColorIndex = (int) bgColorSlider.getValue();
            bgColorLabel.setText(String.format(viewResources.getString("backgroundLabel"), backgroundColorIndex));
        });
        setSliderProperties(bgColorBox, bgColorLabel, bgColorSlider, 0);

        HBox buttonsBox = new HBox();
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> myStage.close());
        buttonsBox.getChildren().add(closeButton);
        Button applyButton = new Button("Apply");
        applyButton.setOnAction(e -> setProperties());
        buttonsBox.getChildren().add(applyButton);

        displayBox.getChildren().add(penColorBox);
        displayBox.getChildren().add(penWidthBox);
        displayBox.getChildren().add(bgColorBox);
        displayBox.getChildren().add(buttonsBox);
        root.getChildren().add(displayBox);
        Scene myPopup = new Scene(root, Integer.parseInt(viewResources.getString("popupWidth")),
                Integer.parseInt(viewResources.getString("popupHeight")));
        return myPopup;
    }

    private void setSliderProperties(HBox box, Label label, Slider slider, int minorTickCount) {
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1.0);
        slider.setMinorTickCount(minorTickCount);
        slider.setSnapToTicks(true);
        box.getChildren().add(label);
        box.getChildren().add(slider);
    }

    private void createStage() {
        myStage = new Stage();
        myStage.setTitle(viewResources.getString("envPropTitle"));
    }


}
