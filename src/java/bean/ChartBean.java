package bean;

import data.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@SessionScoped
public class ChartBean implements Serializable {

    private PieChartModel pieModel;

    public ChartBean() throws SQLException {
        createPieModel();
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    private void createPieModel() throws SQLException {
        pieModel = new PieChartModel();

        int sum = 0;
        int sum2 = 0;
        int sum3 = 0;
        Connection conn = DBUtil.connect();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT count(*)FROM BID WHERE destination='S' Group by destination;");
            PreparedStatement ps2 = conn.prepareStatement("SELECT count(*)FROM BID WHERE destination='A' Group by destination;");
            PreparedStatement ps3 = conn.prepareStatement("SELECT count(*)FROM BID WHERE destination='F' Group by destination;");
            ResultSet result = ps.executeQuery();
            ResultSet result2 = ps2.executeQuery();
            ResultSet result3 = ps3.executeQuery();

            while (result.next()) {
                int c = result.getInt(1);
                sum = sum + c;
            }
            while (result2.next()) {
                int c2 = result2.getInt(1);
                sum2 = sum2 + c2;
            }
            while (result3.next()) {
                int c3 = result3.getInt(1);
                sum3 = sum3 + c3;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        pieModel.set("San Diego", sum);
        pieModel.set("Alaska", sum2);
        pieModel.set("Florida", sum3);
    }
}