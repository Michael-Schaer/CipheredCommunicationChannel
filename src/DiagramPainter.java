import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * A Panel to present data from DiagramData in a graphical diagram
 */
public class DiagramPainter extends JPanel
{
	private static final long serialVersionUID = 2799888028703132009L;
	private ArrayList<LogEvent> events;
	private int linePosition;
	private final int LABEL_POSITION = 120;
	private final double LABEL_FACTOR = 0.75;
	private final int DIAGRAM_ROWS = 3;
	private final int DIAGRAM_ROWS_x_2 = 2*DIAGRAM_ROWS;

	public DiagramPainter(ArrayList<LogEvent> e)
	{
		events = e;
		linePosition = 0;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Stroke stroke = new BasicStroke(5, BasicStroke.JOIN_MITER, BasicStroke.CAP_SQUARE, 0,new float[] { 3, 0 }, 0);
		g2.setStroke(stroke);
		int vPosition=0;
		double widthFactor = (getSize().width / (double)(events.get(events.size()-1).getMilliSeconds()))*LABEL_FACTOR;
		
		g2.setColor(Color.BLUE);
		g2.drawString("Produced", getSize().width-LABEL_POSITION, getSize().height/DIAGRAM_ROWS_x_2);
		g2.setColor(Color.GREEN);
		g2.drawString("Consumed", getSize().width-LABEL_POSITION, getSize().height/2);
		g2.setColor(Color.RED);
		g2.drawString("Mask generated", getSize().width-LABEL_POSITION, getSize().height/DIAGRAM_ROWS_x_2*5);
		
		
		for(LogEvent ev : events)
		{
			linePosition=(int)Math.round(widthFactor*ev.getMilliSeconds());
			if(ev.getClassToken().equals(ClassToken.Produce.toString()))
			{
				g2.setColor(Color.BLUE);
				vPosition = 0;
			}
			else if(ev.getClassToken().equals(ClassToken.Consume.toString()))
			{
				g2.setColor(Color.GREEN);
				vPosition = 1;
			}
			else if(ev.getClassToken().equals(ClassToken.MaskGen.toString()))
			{
				g2.setColor(Color.RED);
				vPosition = 2;
			}
			else
			{
				g2.setColor(Color.BLACK);
			}
			g2.drawLine(linePosition, (int)(getSize().height/DIAGRAM_ROWS*vPosition), linePosition, (int)(getSize().height/DIAGRAM_ROWS*(1+vPosition)));
			//System.out.println(ev.getEvent()+ " "+ ev.getMilliSeconds());
		}
	}
}
