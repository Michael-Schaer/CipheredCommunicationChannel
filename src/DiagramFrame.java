import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A JFrame to present the data collected in DiagramData.
 *
 */
public class DiagramFrame extends JFrame
{
	private static final long serialVersionUID = -7484505990806695261L;
	private DiagramPainter dia;
	private JTextArea textArea;

	public DiagramFrame(DiagramData data)
	{
		this.setLayout(new GridLayout(2, 1));

		dia = new DiagramPainter(data.getEvents());
		this.add(dia);

		textArea = new JTextArea(10, 10);
		textArea.setText("Token\tEvent\t\t\tMask     \tms\n");
		for(LogEvent s : data.getEvents())
		{
			textArea.setText(textArea.getText()+"["+s.getClassToken()+"]\t"+s.getEvent()+"\t\t"+s.getMask()+"\t"+s.getMilliSeconds()+"\n");
		}

		JScrollPane scrollBar = new JScrollPane(textArea);
		this.add(scrollBar);
	}

	public DiagramPainter getDia()
	{
		return dia;
	}
}
