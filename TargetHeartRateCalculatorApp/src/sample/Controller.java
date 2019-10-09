package sample;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class Controller{
    private static final NumberFormat percent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.5);
    private BigDecimal big = new BigDecimal(220);

    @FXML
    private TextField ageTF;
    @FXML
    private Label TFper;
    @FXML
    private TextField TFbpm;
    @FXML
    private TextField TFrtr;
    @FXML
    private Slider HRPS;



    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            double age = Double.parseDouble(ageTF.getText());
            BigDecimal amount = new BigDecimal(ageTF.getText());
            BigDecimal one = new BigDecimal(-1);
            double min50 = (220-age)*(0.5);
            int i = (int) min50;
            double max85 = (220-age)*(0.85);
            int j = (int) max85;
            BigDecimal gg = amount.subtract(big);
            BigDecimal gg1 = gg.multiply(one);
            BigDecimal bpm = gg1.multiply(tipPercentage);
            int a = bpm.intValue();
            TFrtr.setText("Recommended target range: "+i+" to "+j+" beats per minute");
            TFbpm.setText("Target heart rate at this level "+a+" beats per minute");
        }
        catch (NumberFormatException ex) {
            ageTF.selectAll();
            ageTF.requestFocus();
            TFrtr.setText("Recommended target range: to beats per minute");
            TFbpm.setText("Target heart rate at this level beats per minute");
        }
    }

    @FXML
    private void resetOnPressed(ActionEvent event) {
        ageTF.setText("");
        ageTF.selectAll();
        ageTF.requestFocus();
        TFrtr.setText("Recommended target range: to beats per minute");
        TFbpm.setText("Target heart rate at this level beats per minute");
    }
    public void initialize() {
        HRPS.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        tipPercentage =
                                BigDecimal.valueOf(newValue.intValue() / 100.0);
                        TFper.setText(percent.format(tipPercentage));
                    }
                }
        );
    }
}
