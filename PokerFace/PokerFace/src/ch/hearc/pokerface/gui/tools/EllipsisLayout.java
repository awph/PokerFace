
package ch.hearc.pokerface.gui.tools;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * A layout manager that lays out components along an ellipsis.
 *
 * @author Danick Fort, Inspired from Cay Horstmann @ http://www.java2s.com/Code/Java/Swing-JFC/Alayoutmanagerthatlaysoutcomponentsalongacircle.htm
 */
public class EllipsisLayout implements LayoutManager
{
	public void addLayoutComponent(String name, Component parent)
	{
		if (CENTER.equals(name))
		{
			parent.setName("Centered");
			nonEllipseComp++; // Is not an element that goes on the ellipse, used to compute the angle when calculating the position of circle-laid-out components
		}
	}

	public void removeLayoutComponent(Component comp)
	{
	}

	public void setSizes(Container parent)
	{
		if (sizesSet) { return; }
		int n = parent.getComponentCount();

		preferredWidth = 0;
		preferredHeight = 0;
		minWidth = 0;
		minHeight = 0;
		maxComponentWidth = 0;
		maxComponentHeight = 0;

		// compute the maximum component widths and heights
		// and set the preferred size to the sum of the component sizes.
		for(int i = 0; i < n; i++)
		{
			Component c = parent.getComponent(i);
			if (c.isVisible())
			{
				Dimension d = c.getPreferredSize();
				maxComponentWidth = Math.max(maxComponentWidth, d.width);
				maxComponentHeight = Math.max(maxComponentHeight, d.height);
				preferredWidth += d.width;
				preferredHeight += d.height;
			}
		}
		minWidth = preferredWidth / 2;
		minHeight = preferredHeight / 2;
		sizesSet = true;
	}

	public Dimension preferredLayoutSize(Container parent)
	{
		setSizes(parent);
		Insets insets = parent.getInsets();
		int width = preferredWidth + insets.left + insets.right;
		int height = preferredHeight + insets.top + insets.bottom;
		return new Dimension(width, height);
	}

	public Dimension minimumLayoutSize(Container parent)
	{
		setSizes(parent);
		Insets insets = parent.getInsets();
		int width = minWidth + insets.left + insets.right;
		int height = minHeight + insets.top + insets.bottom;
		return new Dimension(width, height);
	}

	public void layoutContainer(Container parent)
	{
		setSizes(parent);
		// compute center of the ellipsis

		Insets insets = parent.getInsets();
		int containerWidth = parent.getSize().width - insets.left - insets.right;
		int containerHeight = parent.getSize().height - insets.top - insets.bottom;

		int xcenter = insets.left + containerWidth / 2;
		int ycenter = insets.top + containerHeight / 2;

		// compute radius of the ellipsis

		int xradius = (containerWidth - maxComponentWidth) / 2;
		int yradius = (containerHeight - maxComponentHeight) / 2;
		// int radius = Math.min(xradius, yradius); USED FOR CIRCLE LAYOUT

		// lay out components along the circle

		int n = parent.getComponentCount();

		double angleBase = 2 * Math.PI * 0 / (n - nonEllipseComp) + (Math.PI / 2);

		double angle = 0;
		Component c;
		Dimension d;
		int x = 0, y=0;

		int rlSideYOffset = containerHeight/5;
		int tbXOffset = containerWidth/4;

		switch(n - nonEllipseComp)
		{
			case 10:
				c = parent.getComponent(9);
				d = c.getPreferredSize();
				angle = 0;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x - d.width / 2, y + rlSideYOffset - d.height / 2, d.width, d.height);
			case 9:
				c = parent.getComponent(8);
				d = c.getPreferredSize();
				angle = Math.PI;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x - d.width / 2, y - rlSideYOffset - d.height / 2, d.width, d.height);
			case 8:
				c = parent.getComponent(7);
				d = c.getPreferredSize();
				angle = 0;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x - d.width / 2, y - rlSideYOffset - d.height / 2, d.width, d.height);
			case 7:
				c = parent.getComponent(6);
				d = c.getPreferredSize();
				angle = Math.PI;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x - d.width / 2, y + rlSideYOffset - d.height / 2, d.width, d.height);
			case 6:
				c = parent.getComponent(5);
				d = c.getPreferredSize();
				angle = Math.PI/2;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x + tbXOffset - d.width / 2, y - d.height / 2, d.width, d.height);
			case 5:
				c = parent.getComponent(4);
				d = c.getPreferredSize();
				angle = -Math.PI/2;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x + tbXOffset - d.width / 2, y - d.height / 2, d.width, d.height);
			case 4:
				c = parent.getComponent(3);
				d = c.getPreferredSize();
				angle = Math.PI/2;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x - tbXOffset - d.width / 2, y - d.height / 2, d.width, d.height);
			case 3:
				c = parent.getComponent(2);
				d = c.getPreferredSize();
				angle = -Math.PI/2;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x - tbXOffset - d.width / 2, y - d.height / 2, d.width, d.height);
			case 2:
				c = parent.getComponent(1);
				d = c.getPreferredSize();
				angle = -Math.PI/2;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x - d.width / 2, y - d.height / 2, d.width, d.height);
				c = parent.getComponent(0);
				d = c.getPreferredSize();
				angle = Math.PI/2;
				x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle
				c.setBounds(x - d.width / 2, y - d.height / 2, d.width, d.height);
				break;
			default:
				break;
		}

		for (int i=n-1; i < n -1 + nonEllipseComp;i++ )
		{
			c = parent.getComponent(i);
			d = c.getPreferredSize();
			c.setBounds(xcenter - d.width / 2, ycenter - d.height / 2, d.width, d.height);
		}
		/*for(int i = 0; i < n; i++)
		{
			Component c = parent.getComponent(i);
			if (c.isVisible())
			{
				double angle = 2 * Math.PI * i / (n - nonEllipseComp) + (Math.PI / 2);

				// center point of component
				int x = xcenter + (int)(Math.cos(angle) * xradius); // radius if circle
				int y = ycenter + (int)(Math.sin(angle) * yradius); // radius if circle

				// move component so that its center is (x, y)
				// and its size is its preferred size
				Dimension d = c.getPreferredSize();

				if ("Centered".equals(c.getName())) // If component has to be centered
				{
					c.setBounds(xcenter - d.width / 2, ycenter - d.height / 2, d.width, d.height);
				}
				else
				// Regular non-centered component on the ellipsis
				{
					c.setBounds(x - d.width / 2, y - d.height / 2, d.width, d.height);
				}
			}
		}*/
	}

	private int					minWidth			= 0;
	private int					minHeight			= 0;
	private int					preferredWidth		= 0;
	private int					preferredHeight		= 0;
	private boolean				sizesSet			= false;
	private int					maxComponentWidth	= 0;
	private int					maxComponentHeight	= 0;
	public static final String	CENTER				= "Center";

	//Tools
	private int					nonEllipseComp		= 0;
}
