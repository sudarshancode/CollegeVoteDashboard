package clg.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.example.DatabaseConnection;

@WebServlet("/VoteChart")
public class ResultChartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null;

        // Candidate Name + Party -> Votes
        LinkedHashMap<String, Integer> barData = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> pieData = new LinkedHashMap<>();
        LinkedHashMap<String, String> candidatePartyMap = new LinkedHashMap<>();

        // Party -> Color mapping
        Map<String, Color> partyColorMap = new HashMap<>();
        partyColorMap.put("Pen Party", new Color(255, 153, 0));
        partyColorMap.put("Book Party", new Color(0, 102, 204));
        partyColorMap.put("Boat", new Color(0, 153, 0));
        partyColorMap.put("CPIM", new Color(204, 0, 0));
        partyColorMap.put("Independent", new Color(102, 102, 102));

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT vc.c_id, vc.c_name, vc.total_votes, c.c_partyname FROM vote_count vc JOIN candidate c ON vc.c_id = c.c_id";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String candidateName = rs.getString("c_name");
                int votes = rs.getInt("total_votes");
                String partyName = rs.getString("c_partyname");

                String label = candidateName + " (" + partyName + ")";
                barData.put(label, votes);
                pieData.put(partyName, pieData.getOrDefault(partyName, 0) + votes);
                candidatePartyMap.put(label, partyName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String type = request.getParameter("type");

        JFreeChart chart;

        if ("pie".equalsIgnoreCase(type)) {
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            for (Map.Entry<String, Integer> entry : pieData.entrySet()) {
                pieDataset.setValue(entry.getKey(), entry.getValue());
            }

            chart = ChartFactory.createPieChart("Vote Distribution (Party Wise)", pieDataset, true, true, false);
            PiePlot plot = (PiePlot) chart.getPlot();
            
            chart.setBackgroundPaint(Color.WHITE);
            plot.setBackgroundPaint(Color.WHITE);
            
            // Pie color
            for (Map.Entry<String, Integer> entry : pieData.entrySet()) {
                Color color = partyColorMap.getOrDefault(entry.getKey(), Color.GRAY);
                plot.setSectionPaint(entry.getKey(), color);
            }

            plot.setLabelFont(new Font("Arial", Font.BOLD, 14));
            plot.setOutlineVisible(false);

        } else {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Integer> entry : barData.entrySet()) {
                dataset.addValue(entry.getValue(), "Votes", entry.getKey());
            }

            chart = ChartFactory.createBarChart("Vote Count", "Candidates", "Votes", dataset);

            CategoryPlot plot = chart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLACK);
            plot.setOutlineStroke(new BasicStroke(1.0f));

            BarRenderer renderer = new BarRenderer() {
                @Override
                public Paint getItemPaint(int row, int column) {
                    String columnKey = (String) dataset.getColumnKey(column);
                    String partyName = candidatePartyMap.get(columnKey);
                    Color color = partyColorMap.getOrDefault(partyName, Color.GRAY);
                    return color;
                }
            };
            plot.setRenderer(renderer);

            // Handle long labels
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
        }

        response.setContentType("image/png");
        BufferedImage bufferedImage = chart.createBufferedImage(900, 600);
        ImageIO.write(bufferedImage, "png", response.getOutputStream());
    }
}
