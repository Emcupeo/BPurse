package ThongKe.BieuDo;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


public class QuanLyThongKeController {
    
    private ThongKeService thongKeService = null;
        private DecimalFormat format = new DecimalFormat("0");
    public QuanLyThongKeController() {
        thongKeService = new ThongKeServiceImpl();
    }
    
    public void setDateToChart1(JPanel jpnItem) {
        List<LopHocBean> listIten = thongKeService.getListByLopHoc();
        if (listIten != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (LopHocBean item : listIten) {
              
                dataset.addValue(Double.parseDouble(format.format(item.getTongTien())), "Tổng Tiền", item.getNgayTao());
              }
            JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ SỐ LƯỢNG HÓA ĐƠN",
                    "Thời Gian", "Số Lượng", dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }
}






